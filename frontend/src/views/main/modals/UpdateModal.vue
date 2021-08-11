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
            <ckeditor :editor="editor" v-model="editorData2" @ready="onEditorReady"></ckeditor>
          </div>
        </div>
        <!-- 기존 파일 내용 보여줌  -->
        <!-- 새로운 첨부파일은 data-fileList 로 -->
        <div v-if="file != null" class="mb-3">
          <img class="profile-img" :src="file" />
          첨부파일 : <span><div file class="fileName" :key="index">{{file.fileName}}</div></span>

        </div>
        <div class="mt-3 mb-3" id="imgFileUploadInsertWrapper">
          <img class="profile-img" :src="file" />
          <p style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">파일 변경</p>
          <input style="margin-left: 40px; " @change="changeFile" type="file" id="inputFileUploadInsert" />
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
var editorData;

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
      file: null,
      editor: ClassicEditor,
      boardType: '',
      editorData: ``,
      editorData2: computed(() => store.getters['rootMain/getBoardDetail'].content),
      boardDetail : {},
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
          console.error("querySelector" , err)
          Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
      });

      // bootstrap modal show event hook
      // UpdateModal 이 보일 때 초기화
      //let $this = this;
      // this.$el.addEventListener('show.bs.modal', function () {

      //   // $this.CKEditor.setData( $this.$store.state.freeboard.content );
      //   // 첨부 파일 관련 초기화
      //   // 수정 또는 수정 전 첨부 파일을 선택하면 그대로 남아 있다.
      //   // this.attachFile = false;
      //   // this.fileList = [];
      //   // document.querySelector("#inputFileUploadUpdate").value = '';
      // })
      setup;
    }
    const changeFile = (fileEvent) => {
      console.log("fileEvent >>>> ", fileEvent)
      if( fileEvent.target.files && fileEvent.target.files.length > 0 ){
        state.file = URL.createObjectURL(fileEvent.target.files[0]);
        console.log("file >>>> ", state.file)
      }
    }

    const onEditorReady = (editor) => {
      state.editorData = state.boardDetail.content
      console.log("onEditor Ready>>", state.editorData);
    }

    const boardUpdate = () => {
      var formData = new FormData();
      formData.append("title", state.title);
      formData.append("content", editorData.getData());
      formData.append("classId",localStorage.getItem('classId'))
      formData.append("userId",localStorage.getItem('userId'))
      formData.append("userName",localStorage.getItem('name'))

      var attachFiles = document.querySelector("#inputFileUploadInsert");
      formData.append("files", attachFiles.files[0])

      state.boardType = store.getters['rootMain/getBoardType'].type
      console.log("boardType >>> ", state.boardType)
      formData.append("boardType",state.boardType)

      store.dispatch('rootMain/requestBoardInsert', formData)
      .then(function (result){
        console.log("성공")
        closeModal();
        router.go();
      })
      .catch(function (err) {
        console.log("requestBoardInsert erre :", err)
      })
    }

    const closeModal = () => {
      emit('call-parent-update'); // no parameter
    }

    return { ...toRefs(state), onMounted, changeFile, boardUpdate ,onEditorReady   ,closeModal,}

  },
}
</script>
