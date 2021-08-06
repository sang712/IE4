<template>
  <div class="section-home d-flex align-items-stretch">
    <div class="col d-flex justify-content-center row">
      <div class="meeting-preview d-flex justify-content-center row" >
        <div class="host-profile" style="margin-top: 5%"> </div>
        <h1 class="text-center" v-if="position=='학생'">{{ nTeacherName }} 선생님</h1>
        <h1 class="text-center">{{ nGrade }}학년 {{ nClass }}반 수업을 시작합니다.</h1>
      </div>
      <el-button v-if="position=='학생'" style="width: 25%; height: 10%; font-size: 140% ">참여하기</el-button>
      <el-button v-else style="width: 25%; height: 10%; font-size: 140% ">방만들기</el-button>
    </div>
    <div class="col">
      <div class="d-flex justify-content-center row">
        <div class="notice-button-wrapper d-flex justify-content-evenly align-items-center" >
          <el-button @click="clickSchedule" style="width: 22%; height: 50%; font-size: 120%">시간표</el-button>
          <el-button @click="clickNote" style="width: 22%; height: 50%; font-size: 120%">알림장</el-button>
          <el-button @click="clickMvp" style="width: 22%; height: 50%; font-size: 120%">MVP</el-button>
        </div>
        <router-view class='notice-content'></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'section-home',

  data () {
    return {
      position : localStorage.getItem('position'),
      nGrade : localStorage.getItem('classId')[0],
      nClass : localStorage.getItem('classId')[2],
      nTeacherName : localStorage.getItem('teacherName')
    }
  },

  setup(props, { emit }) {
    const router = useRouter()

    const clickSchedule = () => {
      router.push({ name: 'schedule' })
    }

    const clickNote = () => {
      router.push({ name: 'note' })
    }

    const clickMvp = () => {
      router.push({ name: 'mvp' })
    }
    return { clickSchedule, clickNote, clickMvp }
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
  background-image: url('../../../assets/images/profile-picture.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border: 1px solid;
  border-radius: 100px;
}
.meeting-preview{
  height: 42vh;
  width: 39vw;
  background-color: #ffffff ;
  margin: 25px 5px 0px 5px;
}
.notice-content{
  height: 41vh;
  width: 32vw;
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
