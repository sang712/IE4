<template>
  <div class="section-news">
    <h1 class="news-title">공지사항</h1>
    <ul class="news-table">
      <li class="table-header">
        <div class="header-number">No</div>
        <div class="header-title">제목</div>
        <div class="header-author">작성자</div>
        <div class="header-date">날짜</div>
      </li>
      <li v-for="(board, index) in getNewsBoardList()" v-bind:key="index" class="table-row" >
        <div class="row-number">{{index}}</div>
        <div class="row-title">{{board.title}}</div>
        <div class="row-author">{{board.userName}}</div>
        <div class="row-date">{{board.regDt.substr(0,10)}}</div>
      </li>

    </ul>
    <!-- 페이지네이션 기능 미구현 -->
    <!-- <el-pagination
      :page-size="5"
      :pager-count="5"
      layout="prev, pager, next"
      :total="4">
    </el-pagination> -->
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import $axios from 'axios'
import { useRouter } from 'vue-router'

export default {
  name: 'section-news',

  setup() {
    const store = useStore()
    const router = useRouter()

    const getNewsBoardList = () => {

      $axios.get("http://localhost:8080/board", {params:{classId:103, boardType:"공지사항"}})
    	.then((res)=>{
    		console.log(res);
        console.log("BoardMainVue: data : ");
        console.log(res.data.content);
        this.store.state.newsboard = res.data.content
      })
    	.then((err)=>{
    		console.log(err);
      })

    }

    let newsboard = computed(function () {
      return store.state.rootMain.newsboard
    })

    return { newsboard, getNewsBoardList }

  }
}
</script>

<style>
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
