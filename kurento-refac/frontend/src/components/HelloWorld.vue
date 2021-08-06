<template>
  <div id="container">
		<div id="wrapper">
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
					<p class="submit">
						<input type="submit" name="commit" value="Join!">
					</p>
				</form>
			</div>
			<div id="room" style="display: none;">
				<h2 id="room-header" style="margin: 10px 0px;"></h2>
				<div id="participants"></div>
			</div>
			<div id="footer" style="display: none;">
				<div class="button-wrapper">
					<button class="button" type="button" id="button-micOn"><i class="fas fa-microphone"></i></button>
					<button class="button" type="button" id="button-micOff"><i class="fas fa-microphone-slash"></i></button>
					<button class="button" type="button" id="button-videoOn"><i class="fas fa-video"></i></button>
					<button class="button" type="button" id="button-videoOff"><i class="fas fa-video-slash"></i></button>
					<button class="button" type="button" id="button-share"><i class="far fa-share-square"></i></button>
					<button class="button" type="button" id="button-session"><i class="fas fa-th-large"></i></button>
					<button class="button" type="button" id="button-imoji"><i class="far fa-grin-beam-sweat"></i></button>
					<button class="button" type="button" id="button-more"><i class="fas fa-ellipsis-h"></i></button>
					<button class="button" type="button" id="button-record"><i class="far fa-dot-circle"></i></button>
					<button class="button" type="button" id="button-leave" @mouseup="leaveRoom" value="Leave room"><i class="fas fa-door-open"></i></button>
				</div>
				<div class="button-wrapper2">
					<button class="button" type="button" id="button-users"><i class="far fa-user"></i></button>
					<button class="button" type="button" id="button-chatting"><i class="far fa-comment-dots"></i></button>
					<button class="button" type="button" id="button-more"><i class="fas fa-ellipsis-h"/></button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import * as conference from '../conference.js'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  methods : {
    register() {
      conference.register()
    },
    leaveRoom() {
      conference.leaveRoom()
			location.reload();
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
button {
	height: 30px;
	background: white;
	border-radius: 15px;
	border: 0px;
	width: 55px;
	margin: auto 2px;
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
.button-wrapper2 {
	margin: 0px;
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
</style>
<style>
#participants {
	
}
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
	height: 40px;
	background: linear-gradient(180deg, rgba(200,200,200,1) 40%, rgba(150,150,150,1) 100%);
}
.button-wrapper, .button-wrapper2 {
	display: inline-block;
	margin: 5px;
	position: absolute;
	right: 0;
}
</style>