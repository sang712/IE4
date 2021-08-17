<template>
  <div id="participants-list" class="col-3" style="display: none;">
    <div id="participants-title">참석자 명단</div>
    <button class="button" type="button" @click="getParticipant()">참석자 가져오기</button>
    <!-- <div id="participants-views"> -->
    <ul id="partlist"></ul>
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

      const ul = document.getElementById('partlist');
      const items = ul.getElementsByTagName('li');
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
.section-news {
  width: 99.8%;
  height: 100%;
  margin: 5px 1px;
  padding: 5px 5px;
  background-color: #efeee9;
}
.news-title {
  text-align: center;
  margin: 20px 0px;
}
.news-table {
  min-height: 430px;
  padding: 0px;
}
.news-table li {
  border-radius: 15px;
  padding: 20px 30px;
  margin: 0px 5%;
  font-size: 130%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 1px;
}
.news-table .table-header {
  background-color: #95A5A6;
  font-weight: bold;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}
.news-table .table-row {
  background-color: #ffffff;
  box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.1);
}
li .header-number, .row-number{
  flex-basis: 10%;
}
li .header-title, .row-title{
  flex-basis: 50%;
}
li .header-author, .row-author{
  text-align: center;
  flex-basis: 15%;
}
li .header-date, .row-date{
  text-align: center;
  flex-basis: 15%;
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
</style>
