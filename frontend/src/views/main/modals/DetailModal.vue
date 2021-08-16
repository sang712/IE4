<template>
<div class="modal" tabindex="-1" id="detailModal" ref="detailModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">글 상세</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="table table-hover">
          <tbody>
            <tr><td>글번호</td><td>{{ boardDetail.boardId }}</td></tr>
            <tr><td>제목</td><td>{{  boardDetail.title }}</td></tr>
            <tr><td>내용</td><td v-html=" boardDetail.content"></td></tr>
            <tr><td>작성자</td><td>{{ boardDetail.userName }}</td></tr>
            <tr><td>작성일시</td><td>{{  boardDetail.regDt}}</td></tr>
            <!-- New for FileUpload -->
            <tr><td colspan="2">첨부파일</td></tr>
            <img v-if="existFileUrl" style="width: 60%; height: 10%;" v-bind:src="fileUrl">
             <tr>
              <td colspan="2">
                <span class="fileName">{{ boardDetail.fileName }}</span>
                &nbsp;&nbsp;
                <a type="button" class="btn btn-outline btn-default btn-xs" v-bind:href="boardDetail.fileUrl" download>내려받기</a>
              </td>
            </tr>
            <!-- / New for FileUpload -->
          </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button v-show="boardDetail.isOwner" @click="changeToUpdate" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">글 수정하기</button>
        <button v-show="boardDetail.isOwner" @click="changeToDelete" class="btn btn-sm btn-warning btn-outline" data-dismiss="modal" type="button">글 삭제하기</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import { useStore } from 'vuex'
import { reactive, computed, toRefs } from 'vue'

export default {
  name: 'DetailModal',
    setup(props, { emit }) {
    const store = useStore()

    const state = reactive({
      fileUrl: computed(() => store.getters['rootMain/getBoardDetail'].fileUrl),
      existFileUrl : computed(() => store.getters['rootMain/getBoardDetail'].fileUrl) !="" ? true:false,
      file: null,
      boardDetail : computed(() => store.getters['rootMain/getBoardDetail']),
    })
    const changeToUpdate = () => {
      emit( 'call-parent-change-to-update' );
    }
    const changeToDelete = () =>{
      emit( 'call-parent-change-to-delete' );
    }
    // const changeFile = (fileEvent) => {
    //   if(fileEvent.target.file && fileEvent.target.file.length > 0){
    //     state.file = URL.createObjectURL(fileEvent.target.file);
    //   }
    // }

    console.log(">" , store.state.rootMain.boardDetail)

    // let boardDetail = computed(function () {
    //   return store.state.rootMain.boardDetail
    // })



    return { ...toRefs(state), changeToUpdate, changeToDelete }
  },
}
</script>

<style>
</style>
