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
			<div id="room" class="col-12" style="display: none; overflow-y: scroll;" >
				<h2 id="room-header" style="margin: 10px auto;"></h2>
				<div id="participants">
					<div class="flex-spacer" style="flex-basis: 100%; height:0; order: -1;"></div>
				</div>
			</div>
			<ParticipantsList />
			<div id="footer" style="display: none;">
				<div class="button-wrapper">
					<button class="button" @mouseup="leaveRoom" type="button" id="button-leave" value="Leave room"><i class="fas fa-door-open"></i></button>
					<button class="button" @click="openParticipantsList" type="button" id="button-users"><i class="far fa-user"></i></button>
				</div>
				<div class="button-wrapper2">
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import * as conference from '../main/conference.js'
import ParticipantsList from './ParticipantsList.vue'
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
			store:'',
		}
	},
  methods : {
    register() {
      conference.register()
      .then(() => {
        setInterval(conference.sendMessage(
          message = {
          id : 'reconnect',
          name : this.name,
          room : room,
          userId : userId,
        }), 30000);
      })
    },
    leaveRoom() {
      conference.leaveRoom()

      if(localStorage.getItem('position') == "교사"){
        this.store.dispatch('rootMain/updateConferenceActive', { conferenceActive : 'close'})
        .then(({ data }) => {
          store.commit('rootMain/setConferenceActive', data, {root: true})
        })
        .catch(function (err) {
          Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
        })
      }

			location.reload();
    },
		openParticipantsList() {
			if (document.getElementById('participants-list').style.display == 'none')
			{
				document.getElementById('room').className = 'col-9'
				document.getElementById('room').style.display = 'grid'
				document.getElementById('participants-list').className = 'col-3'
				document.getElementById('participants-list').style.display = 'block'
				document.getElementById('chatbox').className = 'col-3'
				document.getElementById('chatbox').style.display = 'none'
				}
			else{
				document.getElementById('room').className = 'col-12'
				document.getElementById('room').style.display = 'grid'
				document.getElementById('participants-list').className = ''
				document.getElementById('participants-list').style.display = 'none'
			}
		},
  },
	mounted: function () {
    this.store = useStore()

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
.button:hover {
	color: black;
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
.button-wrapper {
	position: absolute;
	left: 50%;
	transform: translate(-50%);
}
</style>
<style scoped>
#room{
	min-width: 310px;
	background-color: #EFEEE9;
	height: 100vh;
	display: grid;
	justify-content: center;
	align-content: start;
}

</style>
<style>
.participant {
	margin: 2px 2px;
  display: inline-block;
  width: 300px;
	height: 168px;
	border: 1px solid;
	border-radius: 10px;
	overflow: hidden;
	position: relative;
}
#participants {
	padding-bottom: 50px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
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
	z-index: 0;
	width: 100%;
	float: left;
	position: absolute;
	bottom: 0%;
	left: 50%;
	transform: translate(-50%, 0);
	text-align: center;
}
#footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 50px;
	background: linear-gradient(180deg, rgba(200,200,200,1) 40%, rgba(150,150,150,1) 100%);
}
#room::-webkit-scrollbar {
	width: 0px;
	background-color: lightgray;
}
</style>
