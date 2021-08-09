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
            <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
          </div>
        </div>
        <div class="form-check mb-3">
          <label class="form-check-label" for="chkFileUploadInsert">파일 업로드</label>
          <input style="margin-left: 40px; " @change="changeFile" type="file" id="inputFileUploadInsert" />
          <!-- <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert"> -->
        </div>
        <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
         <!-- <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper"> -->
          <!-- <input @change="changeFile" type="file" id="inputFileUploadInsert" multiple> -->
          <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">

            <img v-bind:src="file" v-bind:key="index">
          </div>
        </div>

      <div class="modal-footer">
        <!--@click="boardInsert"-->
        <button class="btn btn-sm btn-primary btn-outline" @click="boardInsert" data-dismiss="modal" type="button">등록</button>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import { useStore } from 'vuex'
import { reactive, computed, toRefs } from 'vue'
import { createApp } from 'vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import CKEditor from '@ckeditor/ckeditor5-vue'

export default {
  name: 'InsertModal',
  components: {
    ckeditor: CKEditor.component
    },
  // data 가 최초 발생하는 곳이므로 store 를 사용하지 않아도 됨.
  data() {
        return {
          title: '',
          attachFile: false,
          file: '',
          editor: ClassicEditor,
                editorData: `위의 Tip을 참고, 작성해주세요`,
                editorConfig: {
                    // The configuration of the editor.
                }
        }
    },
    created() {
      this.title = '';
      this.editor.editorData='';
      console.log(">>>> 내용 ", this.editor)
      this.attachFile= false;
      this.file = '';
      //document.querySelector("#inputFileUploadInsert").value = '';
      const store = useStore()

    },
    changeFile(fileEvent) {
        if( fileEvent.target.files && fileEvent.target.files.length > 0 ){

          for( var i=0; i<fileEvent.target.files.length; i++ ){
            const file = fileEvent.target.files[i];
            this.file=URL.createObjectURL(file);
          }
        }
    },
    mounted() {
      ClassicEditor
        .create(document.querySelector('#divEditorInsert'))
        .then(editor => {
            this.CKEditor = editor;
            console.log("여기서 되나? >> ", this.CKEditor)
        })
        .catch(err => {
            console.error(err.stack);
        });

        // bootstrap modal show event hook
        // InsertModal 이 보일 때 초기화

        this.$el.addEventListener('show.bs.modal', function () {
        //location.reload();
        this.created;
      })
  },
  methods:{
    boardInsert(){
        var formData = new FormData();
        formData.append("title", this.title);
        formData.append("content", this.CKEditor.getData());
        console.log("form >>>> ", formData);
        // file upload
        var attachFiles = document.querySelector("#inputFileUploadInsert");
        console.log("InsertModalVue: data 1 : ");
        console.log(attachFiles);

        var cnt = attachFiles.files.length;
        for (var i = 0; i < cnt; i++) {
          formData.append("file", attachFiles.files[i]);
        }

        http.post(
          '/freeboards',
          formData,
          { headers: { 'Content-Type': 'multipart/form-data' } })
          .then(({ data }) => {
            console.log("InsertModalVue: data : ");
            console.log(data);
            if( data.result == 'login' ){
              this.$router.push("/login")
            }else{
              this.$alertify.success('글이 등록되었습니다.');
              this.closeModal();
            }
          })
          .catch((error) => {
            console.log("InsertModalVue: error ");
            console.log(error);
          });
      },
      closeModal(){
        this.$emit('call-parent-insert'); // no parameter
      },
  }
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
