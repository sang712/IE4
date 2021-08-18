<template>
  <div id="container">
		<div id="wrapper" class="d-flex justify-content-center">
			<div id="join" class="animate join">
				<h1>수업에 입장하세요!</h1>
				<form @submit.prevent="register" accept-charset="UTF-8">
					<p>
						<input type="text" name="name" value="" id="name"
							placeholder="Username" required>
					</p>
					<p>
						<input type="text" name="room" value="1" id="roomName"
							placeholder="Room" required>
					</p>
          <p style="display: none;">
						<input type="text" name="userId" value="" id="userId"
							placeholder="userId" required>
					</p>
					<p class="submit">
						<input type="submit" name="commit" value="Join!">
					</p>
				</form>
			</div>
			<div id="room"  style="display: none;" >
				<h2 id="room-header" style="margin: 10px 0px;"></h2>
				<div id="participants"></div>
			</div>
			<div id="chatbox" class="col-3" style="display: none;">
				<div class="main-wrapper">
					<div class="cgl-live-chat">
						<div class="chat-wrapper">
							<div class="chat-title">우리반 채팅</div>
							<div class="chat-view">
								<div class="chat-item d-flex">
										<img src="https://img6.yna.co.kr/photo/cms/2020/12/03/41/PCM20201203000041990_P2.jpg" alt="">
										<div class="p-1">
											<div class="d-flex align-items-end">
												<div class="sender-name"><a href="#">박서준</a></div>
												<div class="chat-time">09:54</div>
											</div>
											<div class="chat-text">안녕하세요</div>
										</div>
								</div>
								<div class="chat-item d-flex">
									<img src="http://www.nbnnews.co.kr/news/photo/201904/253101_305383_2748.jpg" alt="">
									<div class="p-1">
										<div class="d-flex align-items-end">
											<div class="sender-name admin-tag">박민영 선생님</div>
											<div class="chat-time">09:54<i class="fas fa-thumbtack"></i></div>
										</div>
										<div class="chat-text">애들아 반가워 ~ !</div>
									</div>
								</div>
								<div class="chat-item d-flex">
									<img class="chat-profile-img" src="http://www.sisaweek.com/news/photo/201801/101675_82604_3521.jpg" alt="">
									<div class="chat-text-wrapper p-1">
										<div class="chat-name-tag d-flex align-items-end">
											<div class="sender-name"><a href="#">강호동</a></div>
											<div class="chat-time">09:54<i class="fas fa-thumbtack"></i></div>
										</div>
										<div class="chat-text">안녕하십니까</div>
									</div>
								</div>
							</div>
							<div class="chat-message">
								<form action="#">
									<div class="input-group align-items-center">
										<input type="text" class="form-control" placeholder="여기에 메시지를 입력하세요">
										<div class="input-group-append">
											<button class="" type="button" id="button-addon2">
												<img src="https://github.com/suryavmds/Live-chat-HTML-design--like-YT-chat-/blob/master/assets/img/send-btn.svg?raw=true" alt="">
											</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<ParticipantsList />
			<div id="footer" style="display: none;">
				<div class="button-wrapper">
					<button class="button" @click="micOn" type="button" id="button-micOn"><i class="fas fa-microphone"></i></button>
					<button class="button" @click="micOff" type="button" id="button-micOff"><i class="fas fa-microphone-slash"></i></button>
					<button class="button" @click="displayOn" type="button" id="button-videoOn"><i class="fas fa-video"></i></button>
					<button class="button" @click="displayOff" type="button" id="button-videoOff"><i class="fas fa-video-slash"></i></button>
					<button class="button" @click="share()" type="button" id="button-share"><i class="far fa-share-square"></i></button>
					<button class="button" type="button" id="button-session"><i class="fas fa-th-large"></i></button>
					<button class="button" type="button" id="button-imoji"><i class="far fa-grin-beam-sweat"></i></button>
					<button class="button" type="button" id="button-more"><i class="fas fa-ellipsis-h"></i></button>
					<button class="button" type="button" id="button-record"><i class="far fa-dot-circle"></i></button>
					<button class="button" type="button" id="button-leave" @mouseup="leaveRoom" value="Leave room"><i class="fas fa-door-open"></i></button>
				</div>
				<div class="button-wrapper2">
					<button class="button" @click="openParticipantsList" type="button" id="button-users"><i class="far fa-user"></i></button>
					<button class="button" @click="openChatBox" type="button" id="button-chatting"><i class="far fa-comment-dots"></i></button>
					<button class="button" type="button" id="button-more"><i class="fas fa-ellipsis-h"/></button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { reactive } from '@vue/reactivity'
import * as conference from '../main/conference.js'
import ParticipantsList from './ParticipantsList.vue'
import ScreenHandler from './screen-handler.js';
import MediaHandler from './media-handler.js'
import PeerHandler from './peer-handler'
import { useStore } from 'vuex'

export default {
  name: 'Conference',
	components: {
		ParticipantsList,
	},
  props: {
    msg: String,
		name: '',
		grade: '',
    userId: 0,
		class: '',
  },
	data() {
		return {
			constraints : {
				audio : true,
				video : {
					mandatory : {
						maxWidth : 320,
						maxFrameRate : 15,
						minFrameRate : 15
					}
				},
			},
      screenHandler: '',
			mediaHandler:'',
			peerHandler:'',
			localStream:'',
			store:'',
		}
	},
  methods : {
    register() {
      conference.register(false)
    },
    leaveRoom() {
      conference.leaveRoom(false)

      if(localStorage.getItem('position') == "교사"){
        this.store.dispatch('rootMain/updateConferenceActive', { conferenceActive : 'close'})
        .then(({ data }) => {
          console.log('updateConferenceActive complete')
          console.log(data)
          store.commit('rootMain/setConferenceActive', data, {root: true})
        })
        .catch(function (err) {
          console.log("updateConferenceActive error", err)
          Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
        })
      }

			location.reload();
    },
		openChatBox(){
			if (document.getElementById('chatbox').style.display == 'none')
			{
				document.getElementById('room').className = 'col-9'
				document.getElementById('chatbox').className = 'col-3'
				document.getElementById('chatbox').style.display = 'block'
				document.getElementById('participants-list').className = 'col-3'
				document.getElementById('participants-list').style.display = 'none'
				}
			else{
				document.getElementById('chatbox').style.display = 'none'
				document.getElementById('room').className = 'col-12'
				document.getElementById('chatbox').className = ''
			}
		},
		openParticipantsList() {
			if (document.getElementById('participants-list').style.display == 'none')
			{
				document.getElementById('room').className = 'col-9'
				document.getElementById('participants-list').className = 'col-3'
				document.getElementById('participants-list').style.display = 'block'
				document.getElementById('chatbox').className = 'col-3'
				document.getElementById('chatbox').style.display = 'none'
				}
			else{
				document.getElementById('room').className = 'col-12'
				document.getElementById('participants-list').style.display = 'none'
				document.getElementById('participants-list').className = ''
			}
		},
		async getLocalStream() {
			peerHandler = new PeerHandler

			try {
				const stream = await peerHandler.getUserMedia({
					audio: false,
					video: true,
				});
				this.localStream = stream;
			} catch (err) {
				error(err);
			}
		},

		displayOff() {
			// const localStream = this.getLocalStream()
			console.log('pauseVideo', arguments);
			this.localStream.getVideoTracks()[0].enabled = false;

  	},
		displayOn() {
			// const localStream = this.getLocalStream()
			console.log('resumeVideo', arguments);
			this.localStream.getVideoTracks()[0].enabled = true;

		},
		micOn() {
			// const localStream = this.getLocalStream()
			console.log('unmuteAudio', arguments);
			this.localStream.getAudioTracks()[0].enabled = true;

  	},
		micOff() {
			// const localStream = this.getLocalStream()
			console.log('muteAudio', arguments);
			this.localStream.getAudioTracks()[0].enabled = false;

  	},
		// stopStreamedVideo(videoElem) {
		// 	const stream = videoElem.srcObject;
		// 	const tracks = stream.getTracks();

		// 	tracks.forEach(function(track) {
		// 		track.stop();
		// 	});

		// 	videoElem.srcObject = null;
		// }
    onLocalStream(stream) {
      console.log('onLocalStream', stream);

      const $video = document.querySelector('#video-'+this.name);
      $video.srcObject = stream;

      // this.name = name;
      // this.userId = userId;
      var container = document.createElement('div');
      container.className = 'participant';
      container.id = 'screenShare';
      var span = document.createElement('span');
      span.className = 'participant-name'
      var video = document.createElement('video');
      var rtcPeer;

      rtcPeer;

      container.appendChild(video);
      container.appendChild(span);
      container.onclick = switchContainerClass;
      document.getElementById('participants').appendChild(container);

      span.appendChild(document.createTextNode(screenShare));

      const $svideo = document.querySelector('#video-'+'screenShare');
      $svideo.srcObject = stream;
    },

    async share() {
      // const screenHandler = new ScreenHandler();
      const stream = await this.screenHandler.start(); //return => localStream
      this.onLocalStream(stream);

      // conference.sendingScreen(stream);
      // conference.register(true)
      // conference.onShareScreen(stream);

      // conference.leaveRoom(true);

      // const nameTag = document.getElementById('name')
			// nameTag.value = this.name
			// const classTag = document.getElementById('roomName')
			// classTag.value = this.grade + '학년 ' + this.class + '반'
      // const userIdTag = document.getElementById('userId')
      // userIdTag.value = this.userId
      // conference.register(true);
    },
  },
	mounted: function () {
    this.store = useStore()

		console.log('마운트 되었음')
    this.screenHandler = new ScreenHandler();

		if (this.name !== '') {
			const nameTag = document.getElementById('name')
			nameTag.value = this.name
			const classTag = document.getElementById('roomName')
			classTag.value = this.grade + '학년 ' + this.class + '반'
      const userIdTag = document.getElementById('userId')
      userIdTag.value = this.userId

			this.register()
		}
	}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.button {
	height: 30px;
	background: white;
	border: 0px;
	width: 55px;
	margin: auto 2px;
}
.button-wrapper .button {
	border-radius: 15px;
}
#button-micOff, #button-videoOff, #button-leave {
	color: red;
}
#button-micOn, #button-videoOn {
	color: limegreen;
}
.button i {
	font-size: 20px;
}
.button-wrapper2 .button {
	width: 45px;
}
#button-users {
	border-radius: 12px 0px 0px 12px;
	margin: 0px;
}
#button-chagtting {
	border-radius: 0;
	margin: 0px;
}
.button-wrapper2 #button-more {
	border-radius: 0px 12px 12px 0px;
	margin: 0px;
}
.button:hover {
    background-color: whitesmoke;
}
.button:active {
    box-shadow: inset -2px -2px 3px rgba(255, 255, 255, .6),
                inset 2px 2px 3px rgba(0, 0, 0, .6);
}
.button-wrapper, .button-wrapper2 {
	display: inline-block;
	margin: 10px;
}
.button-wrapper2 {
	position: absolute;
	right: 10px;
}
</style>
<style scoped>
#room{
	min-width: 310px;
}
/* chatting */

#chatbox{
  background-color: #ffffff;
	padding: 0;
  margin: 0;
	height: 100vh;
}
 .chat-profile-img{

}

.chat-text-wrapper{

}

.chat-name-tag{

}

.main-wrapper{
	/* background-color: #080A0D;
	height: 100vh;
	width: 100%; */
	display: grid;
	place-items:center;
	height: 100%;
}
::-webkit-scrollbar {
  width: 6px;
  height: 4px;
  background-color: #27282F;
}

::-webkit-scrollbar-thumb {
  cursor: pointer;
  background: #40404B;
  border-radius: 30px;
}
/*-----------------------라이브 채팅-------------------------*/
.cgl-live-chat{
	width: 100%;
	height: 100%;
}
.cgl-live-chat .chat-wrapper {
	background-color: #141921;
	border: 0px solid #141921;
	box-sizing: border-box;
	height: 100%;
	width: 100%;
	position: relative;
}

.cgl-live-chat .chat-wrapper .chat-title {
	padding: 10px;
	font-family: Hind Madurai;
	font-style: normal;
	font-weight: normal;
	font-size: 20px;
	line-height: 28px;
	color: #9FADC5;
}

.cgl-live-chat .chat-wrapper .chat-view {
	background-color: #11151A;
	display: flex;
	flex-direction: column;
	min-height: 77vh;
	max-height: 77vh;
	overflow-y: auto;
	/*    justify-content: flex-end;*/
}

.cgl-live-chat .chat-wrapper .chat-item {
	padding: 5px;
}

.cgl-live-chat .chat-wrapper .chat-view img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
}

.cgl-live-chat .chat-wrapper .chat-view .sender-name a {
	font-family: Hind Madurai;
	font-style: normal;
	font-weight: bold;
	font-size: 14px;
	line-height: 14px;
	color: #ADADAD;
}

.cgl-live-chat .chat-wrapper .chat-view .sender-name a:hover {
	color: #FFFFFF;
	font-weight: bold;
}

.cgl-live-chat .chat-wrapper .chat-view .admin-tag a {
	background-color: #4368EA !important;
	color: #FFFFFF !important;
	border-radius: 9px;
	padding: 0 8px;
}

.cgl-live-chat .chat-wrapper .chat-view .chat-time {
	font-family: Hind Madurai;
	font-style: normal;
	font-weight: 500;
	font-size: 12px;
	line-height: 11px;
	padding-left: 8px;
	padding-right: 8px;
	color: #585C61;
}

.cgl-live-chat .chat-wrapper .chat-view .chat-time i {
	display: none;
}

.cgl-live-chat .chat-wrapper .chat-view .chat-pinned {
	border: 2px solid #7389DC;
	box-sizing: border-box;
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	background-color: #11151A;
}

.chat-sticky {
	position: fixed;
	top: 0;
	width: 100%;
}

.cgl-live-chat .chat-wrapper .chat-view .chat-pinned .chat-time i {
	display: inline;
}

.cgl-live-chat .chat-wrapper .chat-view .chat-text {
	font-family: Hind Madurai;
	font-style: normal;
	font-weight: lighter;
	font-size: 14px;
	line-height: 17px;
	padding: 3px;
	color: #9FADC5;
}

.cgl-live-chat .chat-wrapper .chat-message {
	background-color: #23272D;
	padding: 6px;
	position: absolute;
	width: 100%;
	bottom: 50px;
}

.cgl-live-chat .chat-wrapper .chat-message input {
	background-color: transparent;
	border: none !important;
	font-family: Hind Madurai;
	font-style: normal;
	font-weight: 300;
	font-size: 16px;
	line-height: 22px;
	color: #585C61;
	outline: none !important;
}

.cgl-live-chat .chat-wrapper .chat-message button {
	background-color: transparent;
	border: none;
	outline: none;
}
.cgl-live-chat .chat-wrapper .dropdown-menu{
	background-color: #141921;
}
.cgl-live-chat .chat-wrapper .dropdown-menu .dropdown-item{
	color: #9FADC5;
	font-family: Hind Madurai;
	font-style: normal;
	font-weight: 300;
}
.cgl-live-chat .chat-wrapper .dropdown-menu .dropdown-item:hover{
	background-color: #1C232E;
}
@media(max-width:450px) {
	.cgl-live-chat .chat-wrapper .chat-view {
		max-height: 300px;
	}

	.cgl-live-chat .chat-wrapper .chat-view img {
		width: 30px;
		height: 30px;
	}

	.cgl-live-chat .chat-wrapper .chat-view .sender-name a {
		font-size: 10px;
		line-height: 10px;
	}

	.cgl-live-chat .chat-wrapper .chat-view .chat-time {
		font-weight: 500;
		font-size: 10px;
		line-height: 10px;
		padding-left: 8px;
		padding-right: 8px;
 	}

	.cgl-live-chat .chat-wrapper .chat-view .chat-text {
		font-size: 12px;
		line-height: 12px;
		padding: 3px;
	}
}

.dropdown-btn{
	background-color: transparent;
	color: #585C61;
	border: none;
	outline: none !important;
}

</style>
<style>

.participant {
	margin: 0px 2px;
  display: inline-block;
  width: 300px;
	height: 168px;
	border: 1px solid;
	border-radius: 10px;
	overflow: hidden;
	position: relative;
}
video {
	width: 100%;
	height: 100%;
	object-fit: cover;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: lightgrey;
}
.participant-name {
	color: white;
	font-weight: bold;
	background: rgba(150,150,150, 0.8);
	z-index: 1;
	width: 100%;
	float: left;
	position: absolute;
	bottom: 0%;
	left: 50%;
	transform: translate(-50%, 0);
}
#footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 50px;
	background: linear-gradient(180deg, rgba(200,200,200,1) 40%, rgba(150,150,150,1) 100%);
}

</style>
