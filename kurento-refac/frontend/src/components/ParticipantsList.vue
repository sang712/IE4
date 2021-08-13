<template>
  <div id="participants-list" class="col-3" style="display: none;">
    <div id="participants-title">참석자 명단</div>
    <div id="participants-views"></div>
  </div>
</template>

<script>
import { reactive, computed, toRefs } from 'vue'
import * as conference from '../conference.js'

export default {
  name: 'ParticipantsList',

  setup() {
    const state = reactive({
      participants: conference.getParticipants(),
    })

    watch(
      () => state.participants,
      () => {
        console.log("참석자 명단", state.participants)

        let participantsViews = document.getElementById('participants-views')
        participantsViews.remove
        participantsViews = document.createElement('div')
        participantsViews.id = 'participants-views'

        state.participants.forEach(createParticipant);
      }
    )

    const createParticipant = (participant) => {
      const participantsViews = document.getElementById('participants-views')

      const container = document.createElement('div')
      container.id = 'aParticipant'
      container.className = ''

      const nameTag = document.createElement('div')
      nameTag.id = 'name-tag'
      nameTag.value = participant.name

      container.appendChild(nameTag)
      participantsViews.appendChild(container)
    }
  }
}
</script>

<style scoped>
#participants-list {
  background-color: #ffffff;
	padding: 0;
  margin: 0;
	height: 100vh;
}
#participants-views {
  background-color: white;
  width: 100%;
  height: 100%;
}
#aParticipant {
  padding: 5px;
  margin: 2px 0px;
}
</style>