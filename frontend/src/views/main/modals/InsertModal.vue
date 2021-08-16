<template>
<div class="modal" tabindex="-1" id="insertModal" ref="insertModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">글 쓰기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">

        <div class="mb-3">
          <input v-model="title" type="text" class="form-control" placeholder="제목">
        </div>
        <div class="mb-3">
          <div id=divEditorInsert>
            <ckeditor :editor="editor" v-model="editorData" @ready="onEditorReady"></ckeditor>
             <!-- v-model="editorData" :config="editorConfig" -->
          </div>
        </div>
        <div class="form-check mb-3">
          <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert" >
          <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
        </div>
        <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
          <input @change="changeFile" type="file" id="inputFileUploadInsert">
          <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
            <img v-bind:src="file">
          </div>
        </div>
        </div>

      <div class="modal-footer">
        <button class="btn btn-sm btn-primary btn-outline" @click="boardInsert" data-dismiss="modal" type="button">등록</button>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import { useStore } from 'vuex'
import { reactive, computed, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import CKEditor from '@ckeditor/ckeditor5-vue'
var editorData;
export default {
  name: 'InsertModal',
  components: {
    ckeditor: CKEditor.component,
  },
  setup(props, { emit }){
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      title: '',
      attachFile: false,
      file: null,
      editor: ClassicEditor,
      editorData: '',
      boardType: '',
    })

    const onMounted = () => {
      ClassicEditor
      .create(document.querySelector('#divEditorInsert'))
      .then(editor => {
          console.log("여기서 되나? >> ", editor.getData())
          editorData = editor
          //this.editorData = editor
      })
      .catch(err => {
          console.error(err.stack);
      });

      // bootstrap modal show event hook
      // InsertModal 이 보일 때 초기화

      //this.$el.addEventListener('show.bs.modal', function () {
      //location.reload();
      setup;
      //})
    }

    const changeFile = (fileEvent) => {
      console.log("fileEvent >>>> ", fileEvent)
      if( fileEvent.target.files && fileEvent.target.files.length > 0 ){
        state.file = URL.createObjectURL(fileEvent.target.files[0]);s
        console.log("file >>>> ", state.file)
      }
      var attachFiles = document.querySelector("#inputFileUploadInsert");
      console.log("insert attachFiles >>> ",  attachFiles.files[0])
    }

    const onEditorReady = (editor) => {
      editorData = editor;
      console.log("onEditor Ready>>", editorData.getData());
    }

    const boardInsert = () => {
      var formData = new FormData();
      formData.append("title", state.title);
      formData.append("content", editorData.getData());
      formData.append("classId",localStorage.getItem('classId'))
      formData.append("userId",localStorage.getItem('id'))
      formData.append("userName",localStorage.getItem('name'))

      var attachFiles = document.querySelector("#inputFileUploadInsert");
      // console.log("attachFiles >>> ",  attachFiles)
      // console.log("attachFiles.files[0] >>> ",  attachFiles.files)
      formData.append("files", attachFiles.files[0])

      state.boardType = store.getters['rootMain/getBoardType'].type
      console.log("boardType >>> ", state.boardType)
      formData.append("boardType",state.boardType)

      store.dispatch('rootMain/requestBoardInsert', formData)
      .then(function (result){
        Swal.fire({ title: '성공', text: '게시글 작성이 완료되었습니다! ', icon: 'success', })
        console.log("성공")
        closeModal();
        router.go();
      })
      .catch(function (err) {
        Swal.fire({ title: '이런!', text: '작성 실패했습니다.', icon: 'error', })
        console.log("requestBoardInsert erre :", err)
      })
    }

    const closeModal = () => {
      emit('call-parent-insert'); // no parameter
    }
    return { ...toRefs(state),changeFile, boardInsert, closeModal, onEditorReady,onMounted }
  },
}
</script>
<style>
/* CKEditor 는 vue 와 별개로 rendering 되어서 scope 를 넣으면 반영되지 않는다. */
.ck-editor__editable {
    min-height: 300px !important;
}

.thumbnail-wrapper{
	margin-top: 5px;
}

.thumbnail-wrapper img {
	width: 100px !important;
	margin-right: 5px;
	max-width: 100%;
}
</style>
