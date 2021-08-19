<template>
  <div class="time-table">
    <img :src="timetableUrl!=null ? timetableUrl : 'ie4_upload/timetable/no-schedule.png'" alt="이미지..2." style="width:100%; height:100%">
    <div v-if="position=='교사'" class="form-group mt-3 mb-3" id="imgFileUploadInsertWrapper" style="margin: 0px auto; text-align: center">
      <span style="width: 20%; font-size: 120%;"><b>시간표 수정 |</b></span>
      <input style="width:50%; margin-left: 20px; " @change="changeFile" type="file" id="inputFileUploadInsert" />
      <button style="width: 13%" @click="updateTimetable()">수정</button>
    </div>
  </div>
</template>

<script>
import { reactive, computed, toRefs } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'section-schedule',
  data () {
    return {
      position : localStorage.getItem('position')
    }
  },
  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      timetableUrl: computed(() => store.getters['rootMain/getClassInfo'].timetable),
      newFile: ''
    })

    const changeFile = (fileEvent) => {
      if(fileEvent.target.files && fileEvent.target.files.length > 0){
        state.newFile = URL.createObjectURL(fileEvent.target.files[0]);
      }
    }

    const updateTimetable = (context) => {
      var formData = new FormData();
      var attachFiles = document.querySelector("#inputFileUploadInsert");
      formData.append("file", attachFiles.files[0]);

      store.dispatch('rootMain/updateTimetable', formData, localStorage.getItem('jwt'))
      .then(function (result) {
        store.commit('rootMain/setTimeTable', result.data)
        Swal.fire({ title: '성공!', text: '시간표가 수정되었습니다.', icon: 'success', })
        router.go()
      })
      .catch(function (err) {
        console.log("updateTimetable error", err)
        Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
      })
    }

    return {
      changeFile, updateTimetable, ...toRefs(state)
    }

  }
}
</script>
