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
    <!-- :page-size="newsboard.limit" :current-page="currentPage" -->
    <div class="lower-sidebar">
      <el-pagination
        :pager-count="5"
        :page-size="newsboard.limit"
        layout="prev, pager, next"
        :total="newsboard.totalListItemCount" @current-change="pageUpdated"></el-pagination>
      <el-button class="mypage-button" @click="showInsertModal">글 작성하기</el-button>
    </div>
    <div>
    </div>
    <!-- v-on:call-parent-change-to-update="changeToUpdate" -->
    <detail-modal v-on:call-parent-change-to-update="changeToUpdate" v-on:call-parent-change-to-delete="changeToDelete(boardDetail.boardId)"></detail-modal>
    <insert-modal v-on:call-parent-insert="closeAfterInsert"></insert-modal>
    <update-modal v-on:call-parent-update="closeAfterUpdate"></update-modal>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { reactive, computed, toRefs } from 'vue'
import { useRouter } from 'vue-router'

import sectionPagination from "./section-pagination.vue"
import DetailModal from "../modals/DetailModal.vue"
import InsertModal from "../modals/InsertModal.vue"
import UpdateModal from '../modals/UpdateModal.vue'

//import VModal from 'vue-js-modal';

import { Modal } from 'bootstrap';

export default {
  name: 'section-news',
  components:{
    sectionPagination,
    DetailModal,
    InsertModal,
    UpdateModal,
  },
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      detailModal: null,
      insertModal: null,
      updateMadal : null,
      newsboard: {},
      boardDetail: {},
      currentPage: 2,
      page : 1,
      boardType: '공지사항'
    });
    store.dispatch('rootMain/setBoardType', state.boardType)
    .then(function (result) {
      console.log("boardType 저장 >> ", state.boardType)
    })
    .catch(function (err) {
      console.log("setBoardType error")
    })

    store.dispatch('rootMain/requestNewsBoardList', localStorage.getItem('jwt'))
    .then(function (result) {
      console.log("갖고온 데이터는 말이지")
      console.log(result.data)
      console.log("총 데이터 수 : ", result.data.totalElements, "총 페이지 : ", result.data.totalPages)
      store.dispatch('rootMain/setNewsBoardList', result.data)

      state.newsboard = store.getters['rootMain/getNewsBoardList']
    })
    .catch(function (err) {
      console.log("requestNewsBoardList error")
    })

    const onMounted = () => {
      state.detailModal = new Modal(document.getElementById('detailModal'));
      state.insertModal = new Modal(document.getElementById('insertModal'));
      state.updateModal = new Modal(document.getElementById('updateModal'));
    }

    const getboardDetail = (boardId) => {
      console.log("접근은 한거니?", boardId)
      store.dispatch('rootMain/requestBoardDetail', {boardId:boardId})
      .then( (result) => {
        console.log("상세정보 : ", result.data)
        store.dispatch('rootMain/setBoardDetail', result.data)
        .then( (res)=>{
          state.boardDetail = store.getters['rootMain/getBoardDetail']
          console.log("boardDetail 정보는 : ", state.boardDetail)
          state.detailModal = new Modal(document.getElementById('detailModal'));
          state.detailModal.show()
        })
      })
      .catch(function (err) {
        console.log("requestBoardDetail error")
        console.log(err)
      })
    }

    const pageUpdated = (val) => {
      state.page = val
      console.log("업데이트 페이지 : page", state.page)
      store.dispatch('rootMain/requestNewsBoardList', {currentPageIndex : state.page})
      .then(function (result) {
        console.log("갖고온 데이터는 말이지")
        console.log(result.data)
        console.log("현재페이지 : ", result.data.pageable.pageNumber)
        console.log("총 데이터 수 : ", result.data.totalElements, "총 페이지 : ", result.data.totalPages)
        store.dispatch('rootMain/setNewsBoardList', result.data)
        state.newsboard = store.getters['rootMain/getNewsBoardList']

      })
      .catch(function (err) {
        console.log("requestNewsBoardList error")
        console.log(err)
      })
    }
    const showInsertModal = () => {
      state.insertModal = new Modal(document.getElementById('insertModal'));
      state.insertModal.show();
    }
    const closeAfterInsert = () => {
      state.insertModal.hide();
      //this.freeboardList();
    }
    const changeToDelete = (boardId) => {
      console.log('삭제시도!')
      store.dispatch('rootMain/deleteDetail', {boardId : boardId})
      .then(function (result) {
        console.log("삭제성공")
        console.log(result.data)
        state.detailModal.hide()
        router.go()
      })
      .catch(function (err) {
        console.log("deleteDetail error")
        console.log(err)
      })
    }
    const changeToUpdate = () => {
      state.detailModal.hide();
      state.updateModal = new Modal(document.getElementById('updateModal'));
      state.updateModal.show();
    }
    const closeAfterUpdate = () => {
      state.updateModal.hide();
      router.go()
    }

    return { ...toRefs(state), onMounted, getboardDetail, pageUpdated, showInsertModal, closeAfterInsert, changeToDelete, changeToUpdate, closeAfterUpdate }
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
