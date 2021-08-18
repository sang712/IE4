<template>
  <div id="participants-list" class="col-3" style="text-align:center;display: none;">
    <!-- <div id="participants-title">참석자 명단</div> -->
    <button class="button" type="button" style="width:80%; height:40px; margin:10px auto; font-size:20px; padding: auto;font-weight:bold" @click="getParticipant()">참석자 명단</button>
    <!-- <div id="participants-views"> -->
    <div id="partlist" style="margin: auto;"></div>
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
  setup() {
    // let participants = ref('');
    const state = reactive({
      participants: '',
      partList : conference.getParticipants(),
    })
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

    }

    const createParticipant = (participant) => {
      console.log("추가 되나요", participant, "참석자 이름", participant.name,"참석자 userId", participant.userId,)
      const participantsViews = document.getElementById('participants-views')

      const li = document.createElement("li");
      li.setAttribute('id', participant.userId);
      li.style = "display: block; border: thick double #32a1ce;font: bold 1rem sans-serif;width: 75%;font-size: 20px;padding:6px;border-radius: 20px;margin: auto;"
      const textNode = document.createTextNode( participant.name );
      li.appendChild(textNode);
      document.getElementById('partlist').appendChild(li);

      li.onclick = function(){

        console.log("클릭>>> ", participant.name);
        let position = localStorage.getItem("position");

        if((position == '교사') && (participant.name != localStorage.getItem('name'))){

          if(confirm(participant.name+ "에게 3 point 입력합니다.") == true){

            const url = '/api/conference/point/' + participant.userId;
            let token = localStorage.getItem('jwt')
            let header = {headers:{'Authorization':`Bearer ${token}`} }
            $axios.post(url, header)
            .then(function () {
              console.log('점수가 입력되었습니다.')
            })
            .catch(function (err) {
              console.log("give point error", err)
            })
          }else{
            return;
          }

        }else{

        }
      }
    }

    return { state, watch, getParticipant, createParticipant }
  }
}
</script>

<style scoped>

#participants-list {
  background-color: #ebe774;
	padding: 0;
  margin: 0;
	height: 100vh;
  background-image: url("../../assets/images/participantslist.jpg");
  background-size:490px 1100px ;
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
</style>

<style>
#partlist li:hover {
  background-color: hsl(103, 100%, 67%);
  font-weight:bold;
  cursor: pointer;
}
</style>
