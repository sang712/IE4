<template>
  <div id="participants-list" class="col-3" style="display: none;">
    <div id="participants-title">참석자 명단</div>
    <button class="button" type="button" @click="getParticipant">참석자 가져오기</button>
    <div id="participants-views"></div>
  </div>
</template>

<script>
import { reactive, computed, toRefs, watch } from 'vue'
import * as conference from '../conference.js'

export default {
  name: 'ParticipantsList',

  setup() {
    const state = reactive({
      participants: '',
    })

    watch(
      () => state.participants,
      (participants, prevParticipants) => {
        console.log("참석자 명단", participants.length)

        let participantsViews = document.getElementById('participants-views')
        participantsViews.remove
        participantsViews = document.createElement('div')
        participantsViews.id = 'participants-views'

        for (let participant in Object.getOwnPropertyNames(participants)) {
          createParticipant(participant)
        }
      }
    )
    const getParticipant = () => {
      console.log('버튼 클릭')
      state.participants = conference.getParticipants()
    }

    const createParticipant = (participant) => {
      console.log("추가 되나요")
      const participantsViews = document.getElementById('participants-views')

      const container = document.createElement('div')
      container.id = 'aParticipant'
      container.className = ''

      const nameTag = document.createElement('div')
      nameTag.id = 'name-tag'
      nameTag.innerText = participant.name

      container.appendChild(nameTag)
      participantsViews.appendChild(container)
    }

    return { state, watch, getParticipant, createParticipant }
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