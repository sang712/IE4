<template>
  <el-container class="main-wrapper">
    <main-header
      :height="`70px`"
      @openSignupDialog="onOpenSignupDialog"
      @openLoginDialog="onOpenLoginDialog"/>
    
    <!-- <el-container class="main-container">
      <el-aside class="hide-on-small" width="240px">
        <main-sidebar
          :width="`240px`"/>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container> -->
    <div class="container" style="height: 75vh; margin: 0px; padding: 0px" >
      <div class="d-flex align-items-stretch">
        <div class="side-bar col-3">          
          <div class="profile-img"></div>
          <h1>이름: 한상길</h1>
          <h1>2학년 7반 30번</h1>
          
          
        </div>
        <div class="col-9">
          <router-view></router-view>
        </div>
        

      </div>
    </div>
    <main-footer :height="`110px`"/>
  </el-container>
  <signup-dialog
    :open="signupDialogOpen"
    @closeSignupDialog="onCloseSignupDialog"/>
  <login-dialog
    :open="loginDialogOpen"
    @closeLoginDialog="onCloseLoginDialog"/>
</template>
<style>
  @import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
  @import './main.css';
  @import '../../common/css/common.css';
  @import '../../common/css/element-plus.css';


  .profile-img{
    margin: 10vh 5px;
    height: 23vh;
    background-image: url('../../assets/images/logo1.png'); 
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    border: 1px solid;
    border-radius: 10px;
  }
  .side-bar {
    background-color: #efeee9 ;
    margin: 5px 5px;
  }
</style>
<script>
import SignupDialog from './components/signup-dialog'
import LoginDialog from './components/login-dialog'
import MainHeader from './components/main-header'
import MainSidebar from './components/main-sidebar'
import MainFooter from './components/main-footer'

export default {
  name: 'Main',
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
    SignupDialog,
    LoginDialog
  },
  data () {
    return {
      loginDialogOpen: false,
      signupDialogOpen: false
    }
  },
  methods: {
    onOpenSignupDialog () {
      this.signupDialogOpen = true
    },
    onCloseSignupDialog () {
      this.signupDialogOpen = false
    },
    onOpenLoginDialog () {
      this.loginDialogOpen = true
    },
    onCloseLoginDialog () {
      this.loginDialogOpen = false
    }
  }
}
</script>
