<template>
<div class="modal" tabindex="-1" id="updateModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">글 수정</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">

        <div class="mb-3">
          <input v-model="boardDetail.title" type="text" class="form-control" placeholder="제목">
        </div>
        <div class="mb-3">
          <div id=divEditorUpdate>
            <ckeditor :editor="editor" v-model="boardDetail.content"></ckeditor>
          </div>
        </div>
        <div>
          <p style="text-align:left">첨부 파일  {{fileUrl}}</p>
        </div>
        <div class="form-check mb-3">
          <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert" >
          <label class="form-check-label" for="chkFileUploadInsert">파일 추가</label>
        </div>
        <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
          <input @change="changeFile2" type="file" id="inputFileUploadInsert2">
          <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
            <img v-bind:src="file2">
          </div>
        </div>

      </div>
      <div class="modal-footer">
        <button @click="boardUpdate" class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">수정</button>
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

export default {
  name: 'UpdateModal',
  components: {
    ckeditor: CKEditor.component,
  },
  setup(props, { emit }){
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      title: '',
      attachFile: false,
      fileUrl: computed(() => store.getters['rootMain/getBoardDetail'].fileUrl),
      file2: null,
      editor: ClassicEditor,
      fileName: computed(() => store.getters['rootMain/getBoardDetail'].fileName),
      boardType: computed(() => store.getters['rootMain/getBoardType'].type),
      editorData: computed(() => store.getters['rootMain/getBoardDetail'].content),
      boardDetail : computed(() => store.getters['rootMain/getBoardDetail']),
    })

    state.boardDetail = store.getters['rootMain/getBoardDetail']

    const onMounted = () => {
      ClassicEditor
      .create(document.querySelector('#divEditorUpdate'))
      .then(editor => {
        editorData = editor
        Swal.fire({ title: '성공!', text: '수정이 완료되었습니다.', icon: 'success', })
      })
      .catch(err => {
          Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
      });
      setup;
    }

    const changeFile2 = (fileEvent) => {
      if( fileEvent.target.files && fileEvent.target.files.length > 0 ){
        state.file2 = URL.createObjectURL(fileEvent.target.files[0]);
      }
    }


    const boardUpdate = () => {
      var formData = new FormData();
      formData.append("id", state.boardDetail.boardId)
      formData.append("title", state.boardDetail.title)
      formData.append("content", state.editorData);
      formData.append("classId",localStorage.getItem('classId'))
      formData.append("userId",localStorage.getItem('id'))
      formData.append("userName",localStorage.getItem('name'))

      var attachFiles = document.querySelector("#inputFileUploadInsert2");
      if (attachFiles != null){
        formData.append("files", attachFiles.files[0])
      }
      formData.append("boardType",state.boardType)

       store.dispatch('rootMain/requestBoardUpdate', formData)
       .then(function (result){
         Swal.fire({ title: '성공', text: '게시글 수정 완료되었습니다! ', icon: 'success', })
         closeModal();
         router.go();
       })
       .catch(function (err) {
         Swal.fire({ title: '실패', text: '게시글 수정 실패했습니다.', icon: 'error', })
       })
    }

    const closeModal = () => {
      document.getElementById("inputFileUploadInsert").value = "";
      emit('call-parent-update'); // no parameters
    }

    return { ...toRefs(state), onMounted, boardUpdate, closeModal , changeFile2 }

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
