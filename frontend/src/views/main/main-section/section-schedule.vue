<template>
  <!-- <div class="time-table" style="{backgroundImage : `url(${{state.classInfo.timetable}})`}"> -->
  <div class="time-table">
    <!-- <div :style="{backgroundImage : `url(${timetableUrl})`}"> -->
      <!-- <img src="require(`../../../../`${state.timetableUrl}`)" alt="이미지..."> -->
      <!-- <img :src="getImg()" alt="이미지..2."> -->
      <!-- <img src="/timetable/450f82b7-49d7-4ddb-8680-edc0f5e8e1dc.jpg" alt="이미지..2."> -->
    <img :src="timetableUrl" alt="이미지..2." style="width:100%; height:100%">
    <!-- </div> -->
    <div v-if="position=='교사'" class="form-group mt-3 mb-3" id="imgFileUploadInsertWrapper" style="margin: 0px auto; text-align: center">
      <span style="width: 20%; font-size: 120%;"><b>시간표 수정 |</b></span>
      <input style="width:40%; margin-left: 20px; " @change="changeFile" type="file" id="inputFileUploadInsert" />
      <button style="width: 13%" @click="updateTimetable()">수정</button>
    </div>
  </div>
</template>

<script>
import { reactive, computed, toRefs } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'section-schedule',
  data () {
    return {
      position : localStorage.getItem('position')
    }
  },
  setup(props, { emit }) {
    const store = useStore()

    const state = reactive({
      timetableUrl: computed(() => store.getters['rootMain/getClassInfo'].timetable),
      newFile: ''
      // realImg: computed(() => "../../../../" + timetableUrl)
    })

    const getImg = () => {
      // console.log('../../../../' + state.timetableUrl)
      console.log('imgUrl', store.getters['rootMain/getClassInfo'].timetable)
      // return require(store.getters['rootMain/getClassInfo'].timetable)
      this.imgUrl = store.getters['rootMain/getClassInfo'].timetable;
      return store.getters['rootMain/getClassInfo'].timetable
    }

    const changeFile = (fileEvent) => {
      console.log('fileEvent.target.files: ', fileEvent.target.files)
      if(fileEvent.target.files && fileEvent.target.files.length > 0){
        state.newFile = URL.createObjectURL(fileEvent.target.files[0]);
      }
    }

    const updateTimetable = (context) => {
      var formData = new FormData();
      var attachFiles = document.querySelector("#inputFileUploadInsert");
      formData.append("file", attachFiles.files[0]);
      console.log('files[0]:', attachFiles.files[0])

      store.dispatch('rootMain/updateTimetable', formData, localStorage.getItem('jwt'))
      .then(function (result) {
        console.log("시간표 수정 완료", result.data)
        store.commit('rootMain/setTimeTable', result.data)
        // store.state.classInfo.timetable = result.data
        Swal.fire({ title: '성공!', text: '시간표가 수정되었습니다.', icon: 'success', })
      })
      .catch(function (err) {
        console.log("updateTimetable error", err)
        Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
      })
    }

    return {
      getImg, changeFile, updateTimetable, ...toRefs(state)
    }

  }
}
</script>
