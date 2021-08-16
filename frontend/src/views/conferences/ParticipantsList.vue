<template>
  <div id="participants-list" class="col-3" style="display: none;">
    <div id="participants-title">참석자 명단</div>
    <button class="button" type="button" @click="getParticipant()">참석자 가져오기</button>
    <div id="participants-views"></div>
  </div>
</template>

<script>
import { reactive, computed, toRefs, watch, ref } from 'vue'
import * as conference from '../main/conference.js'

export default {
  name: 'ParticipantsList',
  // data: {
  //   participants: ''
  // },
  // methods: {
  //   getParticipant() {
  //     console.log('버튼 클릭')
  //     // state.participants = ''
  //     this.participants = conference.getParticipants()
  //   },
  //   createParticipant(participant) {
  //     console.log("추가 되나요", participant, "참석자 이름", participant.name)
  //     const participantsViews = document.getElementById('participants-views')

  //     const container = document.createElement('div')
  //     container.id = 'aParticipant'
  //     container.className = ''

  //     const nameTag = document.createElement('div')
  //     nameTag.id = 'name-tag'
  //     nameTag.innerText = participant.name

  //     container.appendChild(nameTag)
  //     participantsViews.appendChild(container)
  //   }
  // },
  // watch: {
  //   participants: function(newParti) {
  //     console.log('참석자 받았음', this.participants)
  //       let participantsViews = document.getElementById('participants-views')
  //       participantsViews.remove
  //       participantsViews = document.createElement('div')
  //       participantsViews.id = 'participants-views'

  //       Object.getOwnPropertyNames(this.participants).forEach(
  //         function (val, idx, array) {
  //           createParticipant(val)
  //         }
  //       )
  //   }
  // },
  setup() {
    const participants = ref('');
    // const state = reactive({
    //   participants: '',
    // })

    watch(participants,
      (participants, prevParticipants) => {
        console.log('참석자 받았음', participants)
        let participantsViews = document.getElementById('participants-views')
        participantsViews.remove
        participantsViews = document.createElement('div')
        participantsViews.id = 'participants-views'

        Object.getOwnPropertyNames(participants).forEach(
          function (val, idx, array) {
            createParticipant(participants[val])
          }
        )
      }
      // (participants, prevParticipants) => {
      //   console.log('참석자 받았음', participants)
      //   let participantsViews = document.getElementById('participants-views')
      //   participantsViews.remove
      //   participantsViews = document.createElement('div')
      //   participantsViews.id = 'participants-views'

      //   Object.getOwnPropertyNames(participants).forEach(
      //     function (val, idx, array) {
      //       createParticipant(participants[val])
      //     }
      //   )
      // }
    )

    const getParticipant = () => {
      console.log('버튼 클릭')
      // state.participants = ''
      participants = conference.getParticipants()
    }

    const createParticipant = (participant) => {
      console.log("추가 되나요", participant, "참석자 이름", participant.name)
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

    return { participants, watch, getParticipant, createParticipant }
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
  min-height: 77vh;
  max-height: 77vh;
  overflow-y: auto;
}
#aParticipant {
  padding: 5px;
  margin: 2px 0px;
}
</style>
