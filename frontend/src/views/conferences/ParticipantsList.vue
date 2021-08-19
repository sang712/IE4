<template>
  <div id="participants-list" class="col-3" style="text-align:center;display: none;">
    <button class="button" type="button" style="width:80%; height:40px; margin:10px auto; font-size:20px; padding: auto;font-weight:bold" @click="getParticipant()">참석자 명단</button>
    <div id="partlist" style="margin: auto;"></div>
  </div>
</template>

<script>
import { reactive, computed, toRefs, watch, ref } from 'vue'
import * as conference from '../main/conference.js'
import $axios from 'axios'

export default {
  name: 'ParticipantsList',
  setup() {
    const state = reactive({
      participants: '',
      partList : conference.getParticipants(),
    })
    const getParticipant = () => {
      const part = conference.getParticipants()

      const ul = document.getElementById('partlist');
      const items = ul.getElementsByTagName('li');
      if(items.length > 0){
        while(items.length>0){
          items[0].remove();
        }
      }
      part.delete(undefined)
      part.forEach((value, key, mapObject) => createParticipant({userId: key, name: value}));

    }

    const createParticipant = (participant) => {
      const participantsViews = document.getElementById('participants-views')

      const li = document.createElement("li");
      li.setAttribute('id', participant.userId);
      li.style = "display: block; border: thick double #32a1ce;font: bold 1rem sans-serif;width: 75%;font-size: 20px;padding:6px;border-radius: 20px;margin: auto;"
      const textNode = document.createTextNode( participant.name );
      li.appendChild(textNode);
      document.getElementById('partlist').appendChild(li);

      li.onclick = function(){

        let position = localStorage.getItem("position");
        let pname = participant.name.substring(0, participant.name.length-4);
        if((position == '교사') && (pname != localStorage.getItem('name'))){

          if(confirm(participant.name+ "에게 3 point 입력합니다.") == true){

            const url = '/api/conference/point/' + participant.userId;
            let token = localStorage.getItem('jwt')
            let header = {headers:{'Authorization':`Bearer ${token}`} }
            $axios.post(url, header)

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
  background-size:100% 95% ;
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
