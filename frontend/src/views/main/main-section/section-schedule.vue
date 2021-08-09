<template>
  <!-- <div class="time-table" style="{backgroundImage : `url(${{state.classInfo.timetable}})`}"> -->
  <div class="time-table">
    <!-- <img src="require(`../../../../`${state.timetableUrl}`)" alt="이미지..."> -->
    <!-- <img :src="getImg()" alt="이미지..2."> -->
    <!-- <img src="/timetable/450f82b7-49d7-4ddb-8680-edc0f5e8e1dc.jpg" alt="이미지..2."> -->
    <img :src="getImg()" alt="이미지..2.">

    <div class="form-group mt-3 mb-3" id="imgFileUploadInsertWrapper">
          <p style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">시간표 수정</p>
          <input style="margin-left: 40px; " @change="changeFile" type="file" id="inputFileUploadInsert" />

        <!-- <el-button style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">프로필수정</el-button> -->
    </div>

    <button @click="updateTimetable()">수정</button>
  </div>
</template>

<script>
import { reactive, computed} from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'section-schedule',
  setup(props, { emit }) {
    const store = useStore()

    const state = reactive({
      timetableUrl: computed(() => store.getters['rootMain/getClassInfo']).timetable,
      newFile: ''
      // realImg: computed(() => "../../../../" + timetableUrl)
    })

    const getImg = () => {
      // console.log('../../../../' + state.timetableUrl)
      console.log(store.getters['rootMain/getClassInfo'].timetable)
      // return require(store.getters['rootMain/getClassInfo'].timetable)
      return store.getters['rootMain/getClassInfo'].timetable
    }

    const changeFile = (fileEvent) => {
      console.log(fileEvent.target.files)
      if(fileEvent.target.files && fileEvent.target.files.length > 0){
        state.newFile = URL.createObjectURL(fileEvent.target.files[0]);
      }
    }

    const updateTimetable = () => {
      var formData = new FormData();
      var attachFiles = document.querySelector("#inputFileUploadInsert");
      formData.append("file", attachFiles.files[0]);
      console.log(attachFiles.files[0])

      store.dispatch('rootMain/updateTimetable', formData, localStorage.getItem('jwt'))
      .then(function (result) {
        console.log("시간표 수정 완ㅋ")
        console.log(result.data)
        // store.dispatch('rootMain/setClassMemList', result.data)
      })
      .catch(function (err) {
        console.log("updateTimetable error")
      })
    }

    return {
      getImg, state, changeFile, updateTimetable
    }

  }
}
</script>

<style>
/* .time-table {
  background-image: url('../../../assets/images/time-table.png');
} */
</style>
