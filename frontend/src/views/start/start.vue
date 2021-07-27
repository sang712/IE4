<template>
  <div class="start-page">
    <div class="start-background"></div>
    <!-- <img src="@/assets/images/startpage.png" alt="엑박떴지롱~" > -->
    <div style="display: flex; flex-direction: column; align-items: center;">
      <el-button class="start-button" type="default" @click="clickLogin" size="medium" round>로그인</el-button>
      <el-button class="start-button" type="default" @click="clickSignup" size="medium" round>회원가입</el-button>
    </div>
    <signup-dialog
      :open="signupDialogOpen"
      @closeSignupDialog="onCloseSignupDialog"/>
    <login-dialog
      :open="loginDialogOpen"
      @closeLoginDialog="onCloseLoginDialog"/>
  </div>
</template>

<style>
@import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";

.start-page {
  width: 100%;
  height: 100%;
  background-color: #EFEEE9;
}
.start-background {
  width: 100%;
  height: 75%;
  background-size: contain;
  background-image: url('../../assets/images/startpage.png');
  background-repeat: no-repeat;
  background-position: center;
  object-fit: contain !important;
}
.start-button {
  font-size: large;
  padding: 15px 40px !important;
  margin: 10px 10px;
  border-width: 0.75em;
  border-color: #C4C4C4;
}
.start-button:hover {
  border: 13px solid #DEDEDE;
}
</style>

<script>
import SignupDialog from './components/signup-dialog'
import LoginDialog from './components/login-dialog'

export default {
  name: 'Start',
  components: {
    SignupDialog,
    LoginDialog,
  },
  data () {
    return {
      loginDialogOpen: false,
      signupDialogOpen: false
    }
  },
  methods: {
    clickSignup () {
      this.signupDialogOpen = true
    },
    onCloseSignupDialog () {
      this.signupDialogOpen = false
    },
    clickLogin () {
      this. loginDialogOpen = true
    },
    onCloseLoginDialog () {
      this.loginDialogOpen = false
    },
  }
}
</script>
