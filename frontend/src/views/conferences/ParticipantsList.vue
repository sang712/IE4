<template>
  <div id="participants-list" class="col-3" style="display: none;">
    <div id="participants-title">참석자 명단</div>
    <button class="button" type="button" @click="getParticipant()">참석자 가져오기</button>
    <!-- <div id="participants-views"> -->
    <ol id="partlist"></ol>
      <!-- <li class="table-header">
        <div class="header-title" style="text-align:left">no</div>
        <div class="header-title" style="text-align:center">이름</div>
        <div class="header-author">userId</div>
      </li> -->
      <!-- <li v-for="(person, index) in partList" v-bind:key="person.userId" class="table-row">
        <div class="row-title">{{index}}</div>
        <div class="row-title">{{person.name}}</div>
        <div class="row-author">{{person.userId}}</div>
      </li> -->
    <!-- </div> -->
  </div>
</template>

<script>
import { reactive, computed, toRefs, watch, ref } from 'vue'
import * as conference from '../main/conference.js'
import $axios from 'axios'

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
    // let participants = ref('');
    const state = reactive({
      participants: '',
      partList : conference.getParticipants(),
    })

    // watch(participants,
    //   (participants, prevParticipants) => {
    //     console.log('참석자 받았음', participants)
    //     let participantsViews = document.getElementById('participants-views')
    //     participantsViews.remove
    //     participantsViews = document.createElement('div')
    //     participantsViews.id = 'participants-views'

    //     Object.getOwnPropertyNames(participants).forEach(
    //       function (val, idx, array) {
    //         createParticipant(participants[val])
    //       }
    //     ),
    //     { deep: true }
    //   }
    //   // (participants, prevParticipants) => {
    //   //   console.log('참석자 받았음', participants)
    //   //   let participantsViews = document.getElementById('participants-views')
    //   //   participantsViews.remove
    //   //   participantsViews = document.createElement('div')
    //   //   participantsViews.id = 'participants-views'

    //   //   Object.getOwnPropertyNames(participants).forEach(
    //   //     function (val, idx, array) {
    //   //       createParticipant(participants[val])
    //   //     }
    //   //   )
    //   // }
    // )

    const getParticipant = () => {
      console.log('버튼 클릭')
      // state.participants = ''
      // state.participants = conference.getParticipants()
      const part = conference.getParticipants()
      console.log("conference.getParticipants() part >>>>> ", part)
      console.log("conference.getParticipants() state.partList >>>>> ", state.partList)

      const ol = document.getElementById('partlist');
      const items = ol.getElementsByTagName('li');
      console.log("items 길이는 >>>> ", items.length);
      if(items.length > 0){
        while(items.length>0){
          items[0].remove();
        }
      }
      part.delete(undefined)
      part.forEach((value, key, mapObject) => createParticipant({userId: key, name: value}));
      // let participantsViews = document.getElementById('participants-views')
      //   participantsViews.remove
      //   participantsViews = document.createElement('div')
      //   participantsViews.id = 'participants-views'

        // Object.getOwnPropertyNames(part).forEach(
        //   function (val, idx, array) {
        //     createParticipant(part[val])
        //   }
        // )
        //part.delete(undefined)
        //state.partList = part;
        //part.forEach((value, key, mapObject) => createParticipant({userId: key, name: value}));

    }

    const createParticipant = (participant) => {
      console.log("추가 되나요", participant, "참석자 이름", participant.name,"참석자 userId", participant.userId,)
      const participantsViews = document.getElementById('participants-views')

      const li = document.createElement("li");
      li.setAttribute('id', participant.userId);
      //li.setAttribute('@click', getboardDetail(board.id));
      const textNode = document.createTextNode("이름 : " + participant.name+ ", userId : "+ participant.userId);
      li.appendChild(textNode);
      document.getElementById('partlist').appendChild(li);
      li.onclick = function(){
        console.log("클릭 성공 >>> ", participant.name);
        const url = '/api/conference/';
        let token = localStorage.getItem('jwt')
        let header = { headers: { 'Authorization': `Bearer ${token}` } }

        $axios.post(url, participant.userId)
        .then(function (result) {
          console.log("점수주기 성공?")
        })
        .catch(function (err) {
          console.log("점수주기 error",err)
        })
        }
      // const container = document.createElement('div')
      // container.id = 'aParticipant'
      // container.className = ''

      // const nameTag = document.createElement('div')
      // nameTag.id = 'name-tag'
      // nameTag.innerText = participant.name

      // container.appendChild(nameTag)
      // participantsViews.appendChild(container)
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
  min-height: 77vh;
  max-height: 77vh;
  overflow-y: auto;
}
#aParticipant {
  padding: 5px;
  margin: 2px 0px;
}

.lower-sidebar {
  position: relative;
  width: 90%;
  min-height: 60px;
  margin: auto 5%;
}
.lower-sidebar .el-pagination {
  display: inline-block;
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translate(-50%, 0);
}
.lower-sidebar .el-button {
  display: inline-block;
  position: absolute;
  width: 8vw;
  height: 40px;
  right: 5px;
  padding: 0px;
}
#partlist li {
  display: inline-block;
  padding: 5px 0px 5px 5px;
  margin-bottom: 5px;
  border-bottom: 1px solid #efefef;
  font-size: 12px;
}
#partlist li:last-child {
    border-bottom: 0px;
}
#partlist li:hover{
  border-top-color:tomato;
  border-bottom-color:tomato;
  background-color:#FFE5D4;
  font-weight:bold;
  cursor:  pointer;
}
</style>
