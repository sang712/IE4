<template>
  <div class="section-home d-flex align-items-stretch">
    <div class="col d-flex justify-content-center row">
      <div class="meeting-preview d-flex justify-content-center row" >
        <div class="host-profile" v-if="position=='학생'" :style="{backgroundImage:`url(${nTeacherProfileImgUrl})`}"></div>
        <div class="conference-img" v-else style="margin-top: 5%"></div>
        <h1 class="text-center" v-if="position=='학생'">{{ nTeacherName }} 선생님</h1>
        <h1 class="text-center">{{ nGrade }}학년 {{ nClass }}반 수업을 시작합니다.</h1>
      </div>
      <el-button v-if="position=='학생'" style="width: 25%; height: 10%; font-size: 140% " @click="joinCon()">참여하기</el-button>
      <el-button v-else style="width: 25%; height: 10%; font-size: 140% " @click="createCon()">방만들기</el-button>
    </div>
    <div class="col">
      <div class="d-flex justify-content-center row" style="--bs-gutter-x: 0; margin: auto 0px;">
        <div class="notice-button-wrapper d-flex justify-content-evenly align-items-center" >
          <el-button @click="clickSchedule" style="width: 22%; height: 50%; font-size: 120%">시간표</el-button>
          <el-button @click="clickNote" style="width: 22%; height: 50%; font-size: 120%">학사일정</el-button>
          <el-button @click="clickMvp" style="width: 22%; height: 50%; font-size: 120%">MVP</el-button>
        </div>
        <router-view class='notice-content'></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default {
  name: 'section-home',

  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      position : localStorage.getItem('position'),
      nGrade : localStorage.getItem('classId')[0],
      nClass : localStorage.getItem('classId')[2],
      nTeacherName : localStorage.getItem('teacherName'),
      nTeacherProfileImgUrl : localStorage.getItem('teacherProfileImgUrl'),
      conferenceActive : computed(() => store.getters['rootMain/getClassInfo'].conferenceActive)
    })

    const clickSchedule = () => {
      router.push({ name: 'schedule' })
    }

    const clickNote = () => {
      router.push({ name: 'note' })
    }

    const clickMvp = () => {
      router.push({ name: 'mvp' })
    }

    const joinCon = () => {
      store.dispatch('rootMain/getConferenceActive')
      .then(function (result) {
        store.commit('rootMain/setConferenceActive', result.data, {root: true})

        if(state.conferenceActive == "open"){
          router.push({ name : 'conference', params: { userId : localStorage.getItem('id'), name: localStorage.getItem('name'), grade: state.nGrade, class: state.nClass } })
        }else{ //close
          Swal.fire({ title: '잠깐!', text: '아직 수업이 시작하지 않았어요! 조금만 기다려주세요~', icon: 'warning', })
        }
      })
      .catch(function (err) {
        Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
      })

    }

    const createCon = () => {
      store.dispatch('rootMain/updateConferenceActive', { conferenceActive : 'open'})
      .then(({ data }) => {
        store.commit('rootMain/setConferenceActive', data, {root: true})
        router.push({ name : 'conference', params: { userId : localStorage.getItem('id'), name: localStorage.getItem('name') + " 선생님", grade: state.nGrade, class: state.nClass } })
      })
      .catch(function (err) {
        Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
      })
    }

    return { ...toRefs(state), clickSchedule, clickNote, clickMvp, joinCon, createCon }
  }

}
</script>

<style>
.section-home {
  height: 100%;
}
.col {
  background-color: #efeee9 ;
  margin: 5px 5px;
  height: 100%;
}
.host-profile{
  width: 200px;
  height: 200px;
  margin: 35px auto;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border: 1px solid;
  border-radius: 100px;
}
.conference-img{
  width: 200px;
  height: 200px;
  margin: 35px auto;
  background-image: url('../../../assets/images/conference-start.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}
.meeting-preview{
  height: 46vh;
  width: 39vw;
  background-color: #ffffff ;
  margin: 25px 5px 0px 5px;
}
.notice-content{
  height: 46vh;
  width: 35vw;
  background-color: #ffffff ;
  margin: 20px 5px;
  background-size: contain;
  background-position: center;
  background-repeat: no-repeat;
}
.notice-button-wrapper{
  height: 15%;
  margin-top: 20px;
}
</style>
