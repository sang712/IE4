<template>
  <div class="section-mypage d-flex justify-content-center row">
    <div class="mypage-in-background d-flex flex-row">
      <div style="margin-right:100px">
        <div class="mt-3 mb-3" id="imgFileUploadInsertWrapper">
          <img class="profile-img" :src="file" />
          <p style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">프로필 사진 수정</p>
          <input style="margin-left: 40px; " @change="changeFile" type="file" id="inputFileUploadInsert" />
        <!-- <el-button style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">프로필수정</el-button> -->
        </div>
      </div>
      <div class="mypage-second-background">
        <el-form class="mypage-form">
          <el-form-item label="아이디">
            <div>{{ mypageInfo.loginId }}</div>
          </el-form-item>
          <el-form-item label="비밀번호">
            <!-- 비밀번호 부분 아무것도 안표시 되게 바꿔야하는데.. -->
            <el-input v-model="mypageInfo.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="주소">
            <el-input v-model="mypageInfo.address"></el-input>
          </el-form-item>
          <el-form-item label="전화번호">
            <el-input v-model="mypageInfo.phone"></el-input>
          </el-form-item>
          <template v-if="mypageInfo.position == '학생'">
            <el-form-item  label="부모님 전화번호">
              <el-input v-model="mypageInfo.parentPhone"></el-input>
            </el-form-item>
            <el-form-item label="비밀번호 질문">
              <div>{{ mypageInfo.passwordQuestion }}</div>
            </el-form-item>
            <el-form-item label="비밀번호 질문 답">
              <el-input v-model="mypageInfo.passwordAnswer"></el-input>
            </el-form-item>
          </template>
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
import { useStore } from 'vuex'
import { reactive, computed, toRefs } from 'vue'

export default {
  name: 'section-mypage',

  setup() {
    const store = useStore()

    const state = reactive({
      file : null
    })

    const changeFile = (fileEvent) => {
      if(fileEvent.target.file && fileEvent.target.file.length > 0){
        state.file = URL.createObjectURL(fileEvent.target.file);
      }
    }

    console.log(store.state.rootMain.classInfo);
    console.log(store.state.rootMain.mypageInfo)

    let mypageInfo = computed(function () {
      return store.state.rootMain.mypageInfo
    })



    return { ...toRefs(state), mypageInfo, changeFile }
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
    overflow-y: scroll;
  }
  .profile-img{
    margin: 35px auto;
    margin-left: 50px;
    height: 300px;
    /* background-image: url('../../../assets/images/profile-picture.png'); */
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
