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
			<div id="footer">
				<div class="button-wrapper">
					<button class="button" type="button" id="button-micOn"><i class="fas fa-microphone"></i></button>
					<div class="button" type="button" id="button-micOff"><i class="fas fa-microphone-slash"></i></div>
					<span class="button" type="button" id="button-videoOn"><i class="fas fa-video"></i></span>
					<a class="button" type="button" id="button-videoOff"><i class="fas fa-video-slash"></i></a>
					<input class="button" type="button" id="button-share" value="share">
					<input class="button" type="button" id="button-session" value="session">
					<input class="button" type="button" id="button-imoji" value="imoji">
					<input class="button" type="button" id="button-more" value="more">
					<input class="button" type="button" id="button-record" value="record">
					<input class="button" type="button" id="button-leave" @mouseup="leaveRoom" value="Leave room">
				</div>
				<div class="button-wrapper2">

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
	z-index: -1;
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
	height: 30px;
    background: linear-gradient(180deg, rgba(200,200,200,1) 40%, rgba(150,150,150,1) 100%);
}
#button-wrapper > .button {
	height: 25px;
	background: white;
}
</style>