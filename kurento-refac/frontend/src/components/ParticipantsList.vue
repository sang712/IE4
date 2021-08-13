<template>
  <div id="participants-list" class="col-3" style="display: none;">
    <div id="participants-title">참석자 명단</div>
    <div id="participants-tags"></div>
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

        let participantsTags = document.getElementById('participants-tags')
        participantsTags.remove
        participantsTags = document.createElement('div')
        participantsTags.id = 'participants-tags'

        state.participants.forEach(createParticipant);
      }
    )

    const createParticipant = (participant) => {
      const participantsTags = document.getElementById('participants-tags')

      const container = document.createElement('div')
      container.id = 'aParticipant'
      container.className = ''

      const nameTag = document.createElement('div')
      nameTag.id = 'name-tag'
      nameTag.value = participant.name

      container.appendChild(nameTag)
      participantsTags.appendChild(container)
    }
  }
}
</script>

<style>

</style>