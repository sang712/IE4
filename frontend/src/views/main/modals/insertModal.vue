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
          <div id=divEditorInsert></div>
        </div>
        <div class="form-check mb-3">
          <!-- <input v-model="attachFile" class="form-check-input" type="checkbox" value="" id="chkFileUploadInsert"> -->
          <label class="form-check-label" for="chkFileUploadInsert">파일 업로드</label>
          <input style="margin-left: 40px; " @change="changeFile" type="file" id="inputFileUploadInsert" />
        </div>
        <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
        <!-- <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
          <input @change="changeFile" type="file" id="inputFileUploadInsert" multiple>
          <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">

            <img v-for="(file, index) in fileList" v-bind:src="file" v-bind:key="index">
          </div>
        </div>-->
      </div>
      <div class="modal-footer">
        <!--@click="boardInsert"-->
        <button class="btn btn-sm btn-primary btn-outline" data-dismiss="modal" type="button">등록</button>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import { useStore } from 'vuex'
import { reactive, computed, toRefs } from 'vue'

export default {
  name: 'InsertModal',
  // data 가 최초 발생하는 곳이므로 store 를 사용하지 않아도 됨.
  data() {
        return {
          title: '',
          CKEditor: '',
          attachFile: false,
          fileList: []
        }
    },
    setup() {
    const store = useStore()

    const state = reactive({
      attachFile : false
    })

    return { ...toRefs(state) }
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
