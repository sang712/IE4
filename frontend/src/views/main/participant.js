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

import * as Conference from './conference.js'

const PARTICIPANT_MAIN_CLASS = 'participant main';
const PARTICIPANT_CLASS = 'participant';

export function Participant(name, userId) {
	this.name = name;
  this.userId = userId;
	var container = document.createElement('div');
	container.className = isPresentMainParticipant() ? PARTICIPANT_CLASS : PARTICIPANT_MAIN_CLASS;
	container.id = name;
	var span = document.createElement('span');
	span.className = 'participant-name'
	var video = document.createElement('video');
	var rtcPeer;

	rtcPeer;

	container.appendChild(video);
	container.appendChild(span);
	container.onclick = function() {
    switchContainerClass();
  }
  document.getElementById('participants').appendChild(container);

	span.appendChild(document.createTextNode(name));

	video.id = 'video-' + name;
	video.autoplay = true;
	video.controls = false;

	this.getElement = function () {
		return container;
	}

	this.getVideoElement = function () {
		return video;
	}

	function switchContainerClass() {
		if (container.className === PARTICIPANT_CLASS) {
			var elements = Array.prototype.slice.call(document.getElementsByClassName(PARTICIPANT_MAIN_CLASS));
			container.style = "display: block; border: thick double #32a1ce; width: 900px; height: 504px; margin: 5px auto;"
      elements.forEach(function (item) {
				item.className = PARTICIPANT_CLASS;
        item.style = "display: inline-block; width: 300px; height: 168px;"
			});

			container.className = PARTICIPANT_MAIN_CLASS;
		} else {
			container.className = PARTICIPANT_CLASS;
      container.style = "border: 1px solid; display: inline-block; width: 300px; height: 168px;"
		}
	}

	function isPresentMainParticipant() {
		return ((document.getElementsByClassName(PARTICIPANT_MAIN_CLASS)).length != 0);
	}

	this.offerToReceiveVideo = function (error, offerSdp, wp) {
		if (error) return console.error("sdp offer error")
		console.log('Invoking SDP offer callback function');
		var msg = {
			id: "receiveVideoFrom",
			sender: name,
      userId : localStorage.getItem("id"),
			sdpOffer: offerSdp
		};
		wp;
		Conference.sendMessage(msg);
	}

	this.onIceCandidate = function (candidate, wp) {
		console.log("Local candidate" + JSON.stringify(candidate));

		var message = {
			id: 'onIceCandidate',
			candidate: candidate,
			name: name,
      userId : localStorage.getItem("id"),
		};
		wp;
		Conference.sendMessage(message);
	}

	Object.defineProperty(this, 'rtcPeer', { writable: true });

	// 나간 참여자의 연결을 종료
	this.dispose = function () {
		console.log('Disposing participant ' + this.name);
		this.rtcPeer.dispose();
		container.parentNode.removeChild(container);
	};
}
