/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import WebRTC from 'vue-webrtc'
import kurentoUtils from 'kurento-utils'
import * as Participant from './participant.js'

var ws = new WebSocket('wss://' + location.host + '/api/groupcall');
var participants = {};
var participantList=[];
var name;
var userId;

window.onbeforeunload = function() {
	ws.close();
};

// webRTC 시그널링 서버로 받는 이벤트 메시지 처리
ws.onmessage = function(message) {
	var parsedMessage = JSON.parse(message.data);

	// Received message: {"id":"existingParticipants","data":[]}
	console.info('Received message: ' + message.data);
	switch (parsedMessage.id) {
	case 'existingParticipants':		// 클라이언트가 현재 새로운 참가자인 경우
		onExistingParticipants(parsedMessage);
		break;
	case 'newParticipantArrived':		// 클라이언트가 기존 참여자인 경우
		onNewParticipant(parsedMessage);
		break;
	case 'participantLeft':
		onParticipantLeft(parsedMessage);
		break;
	case 'receiveVideoAnswer':
		receiveVideoResponse(parsedMessage);
		break;
	case 'iceCandidate':
		participants[parsedMessage.name].rtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
			if (error) {
				console.error("Error adding candidate: " + error);
				return;
			}
		});
		break;
	default:
		console.error('Unrecognized message', parsedMessage);
	}
}

export function register() {
	name = document.getElementById('name').value;
	var room = document.getElementById('roomName').value;
  userId = document.getElementById('userId').value;

	document.getElementById('room-header').innerText = room + "의 수업";
	document.getElementById('join').style.display = 'none';
	document.getElementById('room').style.display = 'block';
	document.getElementById('footer').style.display = 'block';

	var message = {
		id : 'joinRoom',
		name : name,
		room : room,
    userId : userId,
	}
	sendMessage(message);
}

// 새로운 참가자에 영상을 전달 받을 수신용 WebRtcPeer를 생성
export function onNewParticipant(request) {
  participantList.push({name : request.name, userId: request.userId})
  console.log("participantList >>>  " ,participantList )

	receiveVideo(request.name);
}

export function receiveVideoResponse(result) {
  participantList.push({name : request.name, userId: request.userId})
  console.log("participantList >>>  " ,participantList )
	participants[result.name].rtcPeer.processAnswer (result.sdpAnswer, function (error) {
		if (error) return console.error (error);
	});
}

export function callResponse(message) {
	if (message.response != 'accepted') {
		console.info('Call not accepted by peer. Closing call');
		stop();
	} else {
		WebRTC.webRtcPeer.processAnswer(message.sdpAnswer, function (error) {
			if (error) return console.error (error);
		});
	}
}

// 자신의 영상을 미디어서버에 전달할 송신용 WebRtcPeer를 생성.
export function onExistingParticipants(msg) {
	var constraints = {
		audio : true,
		video : {
			mandatory : {
				maxWidth : 320,
				maxFrameRate : 15,
				minFrameRate : 15
			}
		}
	};

	// gabojago registered in room [object HTMLDivElement]
	console.log(name +"//"+userId+ " registered in room " + room);
	var participant = new Participant.Participant(name, userId);
	participants[name] = participant;
	var video = participant.getVideoElement();

	var options = {
        localVideo: video,
        mediaConstraints: constraints,
        onicecandidate: participant.onIceCandidate.bind(participant)
	}

	// 자신의 영상을 미디어서버에 전달할 송신용 WebRtcPeer를 생성
	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
		function (error) {
            if(error) {
                return console.error(error);
            }
        this.generateOffer (
			participant.offerToReceiveVideo.bind(participant));
	});

	// 기존 참가자 영상을 전달 받을 수신용 WebRtcPeer를 생성.
	msg.data.forEach(receiveVideo);
}

export function leaveRoom() {
	sendMessage({
		id : 'leaveRoom'
	});

	for ( var key in participants) {
		participants[key].dispose();
	}

	document.getElementById('join').style.display = 'block';
	document.getElementById('room').style.display = 'none';
	document.getElementById('footer').style.display = 'none';

	// setTimeout(function() {
	// 	console.log('ws closed');
	// 	ws.close();
	// }, 3000);
}

// 영상을 전달 받을 수신용 WebRtcPeer 생성 함수
export function receiveVideo(sender) {
	var participant = new Participant.Participant(sender, userId);
	participants[sender] = participant;
	var video = participant.getVideoElement();

	var options = {
      remoteVideo: video,
      onicecandidate: participant.onIceCandidate.bind(participant)
    }

	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,
		function (error) {
			if(error) {
					return console.error(error);
			}
		this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});
}

// 나간 참여자에 연결을 종료 및 참여자 리스트에서 삭제
export function onParticipantLeft(request) {
	console.log('Participant ' + request.name + ' left');
	var participant = participants[request.name];
	participant.dispose();
	delete participants[request.name];
}

// Sending message: {"id":"joinRoom","name":"gabojago","room":"1"}
export function sendMessage(message) {
	var jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws.send(jsonMessage);
}

export function getParticipants() {
	console.log('참석자 명단 보내기', participants, Object.keys(participants), Object.getOwnPropertyNames(participants))
	return participants
}
