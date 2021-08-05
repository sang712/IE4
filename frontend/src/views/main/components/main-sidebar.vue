<template>
  <div class="main-sidebar">
    <div class="profile-img"></div>
    <h2 v-if="position=='학생'">{{ nGrade }}학년 {{ nClass }}반 30번</h2>
    <h2 v-else>{{ nGrade }}학년 {{ nClass }}반 담임</h2>
    <h2 v-if="position=='학생'">한상길 학생</h2>
    <h2 v-else>한상길 선생님</h2>

    <hr style="margin-top: 45px;">
    <div class="lower-sidebar d-flex justify-content-evenly align-items-center">
      <el-button class="mypage-button" @click="clickMypage">내 정보</el-button>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
  name: 'main-sidebar',

  data () {
    return {
      position : localStorage.getItem('position'),
      nGrade : localStorage.getItem('classId') == null ? 0 : localStorage.getItem('classId')[0],
      nClass : localStorage.getItem('classId') == null ? 0 : localStorage.getItem('classId')[2]
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()

    const clickMypage = () => {
      store.dispatch('rootMain/requestMyprofile', localStorage.getItem('jwt'))
      .then(function (result) {
        store.dispatch('rootMain/setMypageInfo', result.data)
      })
      .catch(function (err) {
        console.log('에러 정보', err.response)
      })
      router.push({ name: 'mypage' })
    }

    return { clickMypage }
  }
}
</script>

<style>
.main-sidebar .profile-img{
  margin: 35px auto;
  height: 300px;
  background-image: url('../../../assets/images/profile-picture.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border: 1px solid;
  border-radius: 10px;
  min-width: 230px;
  max-width: 230px;
}
.lower-sidebar {
  margin-top: 3vh;
}
.mypage-button {
  width: 60%;
  height: 20%;
  font-size: 120%;
}
</style>
