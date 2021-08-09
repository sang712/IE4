<template>
  <div class="section-news">
    <h1 class="news-title">공지사항</h1>
    <ul class="news-table">
      <li class="table-header">
        <!-- <div class="header-number">No</div> -->
        <div class="header-title" style="text-align:center">제목</div>
        <div class="header-author">작성자</div>
        <div class="header-date">날짜</div>
      </li>
      <li v-for="(board, index) in newsboard.list" @click="getboardDetail(board.id)" v-bind:key="index" class="table-row">
        <!-- <div class="row-number">{{board.id}}</div>-->
        <div class="row-title">{{board.title}}</div>
        <div class="row-author">{{board.userName}}</div>
        <div class="row-date">{{board.regDt.substr(0,10)}}</div>
      </li>
    </ul>
    <div class="lower-sidebar">
      <el-pagination 
        :page-size="newsboard.limit" 
        :pager-count="5" 
        layout="prev, pager, next" 
        :total="newsboard.totalListItemCount"></el-pagination>
      <el-button class="mypage-button" @click="showInsertModal">글 작성하기</el-button>
    </div>
    <detail-modal></detail-modal>
    <insert-modal></insert-modal>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import sectionPagination from "./section-pagination.vue"
import DetailModal from "../modals/DetailModal.vue"
import InsertModal from "../modals/InsertModal.vue"
//import VModal from 'vue-js-modal';

import { Modal } from 'bootstrap';

export default {
  name: 'section-news',
  components:{
    sectionPagination,
    DetailModal,
    InsertModal,
  },
  data(){
    return{
      detailModal: null,
      insertModal: null,
      newsboard: {},
      boardDetail: {},
      currentPage: 1,
    }
  },
  created() {
    const store = useStore()

    store.dispatch('rootMain/requestNewsBoardList', localStorage.getItem('jwt'))
    .then(function (result) {
      console.log("갖고온 데이터는 말이지")
      console.log(result.data)
      console.log("현재페이지 : ", result.data.pageable.pageNumber)
      console.log("총 데이터 수 : ", result.data.totalElements, "총 페이지 : ", result.data.totalPages)
      store.dispatch('rootMain/setNewsBoardList', result.data)
    })
    .catch(function (err) {
      console.log("requestNewsBoardList error")
    })

    this.newsboard = store.getters['rootMain/getNewsBoardList']
    console.log(">>>>> newboard:", this.newsboard)

  },
  movePage(pageIndex){
      console.log("BoardMainVue : movePage : pageIndex : " + pageIndex );
      store.dispatch('rootMain/setNewsMovePage', pageIndex.$ref.detailModal);
    console.log("가져왔니? : " , this.detailModal)
  },
  mounted() {
    this.detailModal = new Modal(document.getElementById('detailModal'));
    this.insertModal = new Modal(document.getElementById('insertModal'));
  },
  methods:{
    getboardDetail(boardId){
      console.log("접근은 한거니?", boardId)
      this.$store.dispatch('rootMain/requestBoardDetail', {boardId:boardId})
      .then( (result) => {
        console.log("상세정보 : ", result.data)
        this.$store.dispatch('rootMain/setBoardDetail', result.data)
        .then( (res)=>{
          this.boardDetail = this.$store.getters['rootMain/getBoardDetail']
          console.log("boardDetail 정보는 : ", this.boardDetail)
          this.detailModal.show();
        })
      })
      .catch(function (err) {
        console.log("requestNewsBoardList error")
        console.log(err)
      })
    },
    showInsertModal(){
      this.insertModal.show();
    },
    closeAfterInsert(){
      this.insertModal.hide();
      //this.freeboardList();
    },
  },
}
</script>

<style scoped>
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
