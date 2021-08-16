<template>
  <div class="section-mypage d-flex justify-content-center row">
    <div class="mypage-in-background d-flex flex-row">
      <div style="margin-right:100px">
        <div class="form-group mt-3 mb-3" id="imgFileUploadInsertWrapper">
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
          <template v-else>
            <el-form-item label="우리반 급훈">
              <el-input v-model="mypageInfo.classMotto"></el-input>
            </el-form-item>
          </template>
        </el-form>
        <!-- <h2>아이디:</h2>
        <h3>{{ mypageInfo.loginId }}</h3> -->
      </div>
    </div>
    <div class="d-flex justify-content-evenly">
      <el-button @click="updateUser" style="width: 13%; height: 70%; font-size: 120%" >수정</el-button>
      <el-button @click="clickSecession" style="width: 13%; height: 70%; font-size: 120%" >탈퇴</el-button>
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

    console.log(store.state.rootMain.classInfo)
    console.log(store.state.rootMain.mypageInfo)

    // let mypageInfo = computed(function () {
    //   return store.state.rootMain.mypageInfo
    // });

    const state = reactive({
      mypageInfo : computed(() => store.getters['rootMain/getMypageInfo']),
      file : store.getters['rootMain/getMypageInfo'].profileImgUrl,
      nSex : localStorage.getItem('sex')
    });

    const changeFile = (fileEvent) => {
      if(fileEvent.target.files && fileEvent.target.files.length > 0){
        state.file = URL.createObjectURL(fileEvent.target.files[0]);
      }
    };

    const updateUser = () => {
      console.log(document.querySelector("#inputFileUploadInsert").files[0])
      console.log(localStorage.getItem('position'))
      console.log(store.state.rootMain.mypageInfo.password)
      if(localStorage.getItem('position') == '학생'){
        var updateStudentData = new FormData()
        var attachFiles = document.querySelector("#inputFileUploadInsert")
        updateStudentData.append("id", localStorage.getItem('id'))
        updateStudentData.append("password", store.state.rootMain.mypageInfo.password)
        updateStudentData.append("phone", store.state.rootMain.mypageInfo.phone)
        updateStudentData.append("address", store.state.rootMain.mypageInfo.address)
        updateStudentData.append("parentPhone", store.state.rootMain.mypageInfo.parentPhone)
        updateStudentData.append("passwordAnswer", store.state.rootMain.mypageInfo.passwordAnswer)
        updateStudentData.append("profileImgUrl", attachFiles.files[0])
        store.dispatch('rootMain/updateStudent', updateStudentData)
        .then(() => {
          router.go()
        })
      }else{
        var updateTeacherData = new FormData()
        var attachFiles = document.querySelector("#inputFileUploadInsert")
        updateTeacherData.append("id", localStorage.getItem('id'))
        updateTeacherData.append("classId", localStorage.getItem('classId'))
        updateTeacherData.append("password", store.state.rootMain.mypageInfo.password)
        updateTeacherData.append("phone", store.state.rootMain.mypageInfo.phone)
        updateTeacherData.append("address", store.state.rootMain.mypageInfo.address)
        updateTeacherData.append("classMotto", store.state.rootMain.mypageInfo.classMotto)
        updateTeacherData.append("profileImgUrl", attachFiles.files[0])
        store.dispatch('rootMain/updateTeacher', updateTeacherData)
        .then(() => {
          router.go()
        })
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
    /* background-image: url('../../assets/images/signuppage1.png') !important; */
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
