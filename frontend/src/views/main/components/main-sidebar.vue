<template>
  <div class="main-sidebar">
    <div class="profile-img" :style="{backgroundImage:`url(${profileImgUrl})`}">
    </div>
    <h2 v-if="nPosition=='학생'">{{ nGrade }}학년 {{ nClass }}반 {{nSnum}}번</h2>
    <h2 v-else>{{ nGrade }}학년 {{ nClass }}반 담임</h2>
    <h2 v-if="nPosition=='학생'">{{nName}} 학생</h2>
    <h2 v-else>{{nName}} 선생님</h2>

    <hr style="margin: 4vh 0;">
    <div class="lower-sidebar d-flex justify-content-evenly align-items-center">
      <el-button class="mypage-button" @click="clickMypage">내 정보</el-button>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { reactive, computed, toRefs } from 'vue'


export default {
  name: 'main-sidebar',

  setup() {
    const store = useStore()
    const router = useRouter()

    store.commit('rootMain/setProfileImgUrl', localStorage.getItem('profileImgUrl'))

    const state = reactive({
      jwt : localStorage.getItem('jwt'),
      nPosition : localStorage.getItem('position'),
      nGrade : localStorage.getItem('classId')[0],
      nClass : localStorage.getItem('classId')[2],
      nName : localStorage.getItem('name'),
      nProfileImgUrl : computed (() => store.getters['rootMain/getMypageInfo'].profileImgUrl),
      nSnum : localStorage.getItem('snum'),
      nSex : localStorage.getItem('sex')
    });

    const profileImgUrl = computed(() => {
       console.log('state.nProfileImgUrl >>>>>>>' , state.nProfileImgUrl)
      if(state.nProfileImgUrl == null || state.nProfileImgUrl == 'null') {
        console.log('profileImgUrl >>  if null  안으로 들어옴')
        console.log('state.nProfileImgUrl >> ', state.nProfileImgUrl)
        if(state.nSex == '남' && state.nPosition == '교사') {
          return 'ie4_upload/profileImg/no_profile_img_man.png';
        } else if(state.nSex == '여' && state.nPosition == '교사') {
            return 'ie4_upload/profileImg/no_profile_img_woman.png';
        } else if(state.nPosition == '학생') {
            return state.nName.charCodeAt([1])%2 ? 'ie4_upload/profileImg/no_profile_img_student1.jpg' : 'ie4_upload/profileImg/no_profile_img_student0.jpg'
        }
      }else{
        console.log('profileImgUrl >> else 안으로 들어옴')
        console.log('state.nProfileImgUrl >> ', state.nProfileImgUrl)
        return state.nProfileImgUrl;
      }
    });

    const clickMypage = () => {
      router.push({ name: 'mypage' })
    }

    return { clickMypage,  ...toRefs(state), profileImgUrl }
  }
}
</script>

<style>
.main-sidebar .profile-img{
  margin: 35px auto;
  height: 300px;
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
.lower-sidebar .el-button {
    display: inline-block;
    width: 8vw;
    height: 40px;
    padding: 0;
}
.mypage-button {
  width: 60%;
  height: 20%;
  font-size: 120%;
}
</style>
