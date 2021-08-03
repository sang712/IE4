<template>
  <div class="main-sidebar">
    <div class="profile-img"></div>
    <h2>이름: 한상길</h2> 
    <h2>2학년 7반 30번</h2>
    <hr style="margin-top: 45px;">
    <div class="lower-sidebar d-flex justify-content-evenly align-items-center">
      <el-button class="mypage-button" @click="clickMypage">내 정보</el-button>   
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'main-sidebar',

  setup(props, { emit }) {
    const router = useRouter()

    const clickMypage = () => {
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