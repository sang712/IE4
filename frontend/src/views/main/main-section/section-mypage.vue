<template>
  <div class="section-mypage d-flex justify-content-center row">
    <div class="mypage-in-background d-flex flex-row">
      <div style="margin-right:100px">
        <div class="profile-img"></div>
        <el-button style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">프로필수정</el-button>     
      </div>
      <div class="mypage-second-background">
        <el-form class="mypage-form" >
          <el-form-item label="아이디">
            <div>{{ mypageInfo.loginId }}</div>
          </el-form-item>
          <el-form-item label="비밀번호">
            <el-input v-model="mypageInfo.password" show-password></el-input>
          </el-form-item>
        </el-form>
        <!-- <h2>아이디:</h2>
        <h3>{{ mypageInfo.loginId }}</h3> -->
      </div>
    </div>
    <div class="d-flex justify-content-evenly">
      <el-button style="width: 13%; height: 70%; font-size: 120%">수정</el-button> 
      <el-button style="width: 13%; height: 70%; font-size: 120%">탈퇴</el-button> 
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'section-mypage',

  setup() {
    const store = useStore()

    let mypageInfo = computed(function () {
      return store.state.rootMain.mypageInfo
    })

    return { mypageInfo, }
  }
}
</script>

<style>
  .section-mypage {
    background-color: #efeee9 ;
    margin: 5px 5px;
    height: 66vh;
    width: 83vw;
  }
  .mypage-in-background {
    background-color: #ffffff ;
    height: 52vh;
    width: 80vw;
    margin: 15px 5px 0px 5px;
  }
  .profile-img{
    margin: 35px auto;
    margin-left: 50px;
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
  .mypage-second-background {
    background-color: #efeee9 ;
    margin-top: 30px;
    margin-right: 60px;
    height: 430px;
    width: 55vw;
  }
</style>