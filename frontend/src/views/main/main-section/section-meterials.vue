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
      <li v-for="(board, index) in listGetters" v-bind:key="index" class="table-row" >
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

export default {
  name: 'section-meterials',
  computed:{

    listGetters(){
      return this.$store.getters.getBoardList; // no getBoardList()
    },
  },
  methods: {
    boardList(){
      //this.$store.dispatch('rootMain/boardList');
      //JSON.parse( localStorage.getItem('vuex'))['content'].searchWord = '';
    },
    created(){
      $axios.get("http://localhost:8080/board", {params:{classId:103, boardType:"학습자료"}})
    	.then((res)=>{
    		console.log(res);
        console.log("BoardMainVue: data : ");
        console.log(res.data.content);
        this.$store.state.boardList = res.data.content
      })
    	.then((err)=>{
    		console.log(err);
      })
    },
  }
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
