<template>
  <div class="section-mypage d-flex justify-content-center row">
    <div class="mypage-in-background d-flex flex-row">
      <div style="margin-right:100px">
        <div class="form-group mt-3 mb-3" id="imgFileUploadInsertWrapper">
          <img class="profile-img" :src="file" />
          <p style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">프로필 사진 수정</p>
          <input style="margin-left: 40px; " @change="changeFile" type="file" id="inputFileUploadInsert" />
        </div>
      </div>
      <div class="mypage-second-background">
        <el-form class="mypage-form">
          <el-form-item label="아이디">
            <el-input v-model="mypageInfo.loginId" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="비밀번호">
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
              <el-input v-model="mypageInfo.passwordQuestion" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="비밀번호 질문 답">
              <el-input v-model="mypageInfo.passwordAnswer"></el-input>
            </el-form-item>
          </template>
          <template v-else>
            <el-form-item label="우리반 급훈">
              <el-input v-model="classMotto"></el-input>
            </el-form-item>
          </template>
        </el-form>
      </div>
    </div>
    <div class="d-flex justify-content-evenly">
      <el-button @click="updateUser" style="width: 13%; height: 50%; font-size: 120%" >수정</el-button>
      <el-button @click="clickSecession" style="width: 13%; height: 50%; font-size: 120%" >탈퇴</el-button>
    </div>
    <secession-dialog
      :open="secession.secessionDialogOpen"
      @closeSecessionDialog="onCloseSecessionDialog"/>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { reactive, computed, toRefs, ref } from 'vue'
import secessionDialog from './secession-dialog'
import { useRouter } from 'vue-router'

export default {
  name: 'section-mypage',

  components : { secessionDialog },

  setup() {
    const store = useStore()
    const router = useRouter()

    store.dispatch('rootMain/requestMyprofile', localStorage.getItem('jwt'))

    const secession = ref([
      {secessionDialogOpen: false},
    ]);

    const clickSecession = () => {
      secession.value.secessionDialogOpen = true
    };

    const onCloseSecessionDialog = () => {
      secession.value.secessionDialogOpen = false
    };

    const state = reactive({
      mypageInfo : store.getters['rootMain/getMypageInfo'],
      classMotto : store.getters['rootMain/getClassInfo'].classMotto,
      file : store.getters['rootMain/getMypageInfo'].profileImgUrl,
      nSex : localStorage.getItem('sex')
    });

    const changeFile = (fileEvent) => {
      if(fileEvent.target.files && fileEvent.target.files.length > 0){
        state.file = URL.createObjectURL(fileEvent.target.files[0]);
      }
    };

    const updateUser = () => {
      if(localStorage.getItem('position') == '학생'){
        var updateStudentData = new FormData()
        var attachFiles = document.querySelector("#inputFileUploadInsert")
        updateStudentData.append("id", localStorage.getItem('id'))
        updateStudentData.append("password", state.mypageInfo.password)
        updateStudentData.append("phone", state.mypageInfo.phone)
        updateStudentData.append("address", state.mypageInfo.address)
        updateStudentData.append("parentPhone", state.mypageInfo.parentPhone)
        updateStudentData.append("passwordAnswer", state.mypageInfo.passwordAnswer)
        updateStudentData.append("profileImgUrl", attachFiles.files[0])
        store.dispatch('rootMain/updateStudent', updateStudentData)
      }else{
        var updateTeacherData = new FormData()
        var attachFiles = document.querySelector("#inputFileUploadInsert")
        updateTeacherData.append("id", localStorage.getItem('id'))
        updateTeacherData.append("classId", localStorage.getItem('classId'))
        updateTeacherData.append("password", state.mypageInfo.password)
        updateTeacherData.append("phone", state.mypageInfo.phone)
        updateTeacherData.append("address", state.mypageInfo.address)
        updateTeacherData.append("classMotto", state.classMotto)
        updateTeacherData.append("profileImgUrl", attachFiles.files[0])
        store.dispatch('rootMain/updateTeacher', updateTeacherData)
      }
    };

    return { ...toRefs(state), changeFile, updateUser, secession, clickSecession, onCloseSecessionDialog }
  }
}
</script>

<style>
  .el-dialog {
    background-size: fill !important;
    background-color: #FFFFFF;
    background-position: center;
    background-size: 100% !important;
    width: 40vw;
    min-width: 600px !important;
    display: grid;
    align-items: center;
    justify-items: center;
    margin: 5px auto;
  }
  .section-mypage {
    background-color: #efeee9 ;
    margin: 5px 1px;
    height: 70vh;
    width: 99.8%;
    padding: 10px;
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
    margin: 20px 50px 20px 15px;
    height: 90%;
    width: 55vw;
    padding: 15px;
    overflow-y: scroll;
  }
</style>
<style scoped>
.el-button {
  height: 50%;
}
</style>
