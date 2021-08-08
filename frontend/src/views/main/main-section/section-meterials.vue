<template>
  <div class="section-meterials">
    <h1 class="meterials-title">학습자료</h1>
    <ul class="meterials-table">
      <li class="table-header">
        <div class="header-number">No</div>
        <div class="header-title">제목</div>
        <div class="header-author">작성자</div>
        <div class="header-date">작성일</div>
      </li>
      <li v-for="(board, index) in board" v-bind:key="index" class="table-row" >
        <div class="row-number">{{index}}</div>
        <div class="row-title">{{board.title}}</div>
        <div class="row-author">{{board.userName}}</div>
        <div class="row-date">{{board.regDt.substr(0,10)}}</div>
      </li>
    </ul>
  </div>
</template>

<script>
import $axios from 'axios'
import { useStore } from 'vuex'

export default {
  name: 'section-meterials',
  data(){
    return{
      board:[],
    }
  },
  created() {
    const store = useStore()

    store.dispatch('rootMain/requestBoardList', localStorage.getItem('jwt'))
    .then(function (result) {
      console.log("갖고온 리스트는 말이지")
      console.log(result.data.content)
      store.dispatch('rootMain/setBoardList', result.data.content)
    })
    .catch(function (err) {
      console.log("requestBoardList error")
    })

    this.board = store.getters['rootMain/getBoardList'].list
    console.log(this.board)
  },

}
</script>

<style>
.section-meterials {
  width: 99.8%;
  height: 100%;
  margin: 5px 1px;
  padding: 5px 5px;
  background-color: #efeee9;
}
.meterials-title {
  text-align: center;
  margin: 20px 0px;
}
.meterials-title {
  text-align: center;
  margin: 20px 0px;
}
.meterials-table {
  padding: 0px;
}
.meterials-table li {
  border-radius: 15px;
  padding: 20px 30px;
  margin: 0px 5%;
  font-size: 130%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 1px;
}
.meterials-table .table-header {
  background-color: #95A5A6;
  font-weight: bold;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}
.meterials-table .table-row {
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
