<template>
  <div id="container">
		<div id="wrapper">
			<div id="join" class="animate join">
				<h1>방으로 들어가세욧!</h1>
				<form @submit.prevent="register" accept-charset="UTF-8">
					<p>
						<input type="text" name="name" value="" id="name"
							placeholder="Username" required>
					</p>
					<p>
						<input type="text" name="room" value="" id="roomName"
							placeholder="Room" required>
					</p>
					<p class="submit">
						<input type="submit" name="commit" value="Join!">
					</p>
				</form>
			</div>
			<div id="room" style="display: none;">
				<h2 id="room-header"></h2>
				<div id="participants"></div>
				<input type="button" id="button-leave" @mouseup="leaveRoom"
					value="Leave room">
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
.participant {
	margin: 1% 0% 5px 1.4%;
  display: inline-block;
  width: 15%;
  max-height: 40%;
	border: 1px solid;
	border-radius: 10px;
}
.participant-name {
	margin: 50px auto 0px auto;
	background-color: rgba(255, 255, 255, 0.80);
}
</style>