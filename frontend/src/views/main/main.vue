<template>
  <el-container class="main-wrapper">
    <main-header
      :height="`70px`"
      @openSignupDialog="onOpenSignupDialog"
      @openLoginDialog="onOpenLoginDialog"/>
      <div class="d-flex align-items-stretch">
        <div class="side-bar">
          <main-sidebar/>
        </div>
        <div class="main-content" >
          <router-view></router-view>
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

  .side-bar {
    background-color: #efeee9 ;
    margin: 5px 5px;
    width: 300px;
    height: 70vh;
  }
  .main-content {
    width: 100%;
    height: 70vh;
  }
.el-button--mini {
  min-height: 28px;
  padding: 7px 15px;
  font-size: 12px;
  border-radius: calc(var(--el-border-radius-base) - 1px);
}
.el-button {
  padding: 12px 2%;
}
</style>
<script>
import MainHeader from './components/main-header'
import MainFooter from './components/main-footer'
import MainSidebar from './components/main-sidebar'
import { useStore } from 'vuex'

export default {
  name: 'Main',
  components: {
    MainHeader,
    MainFooter,
    MainSidebar,
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
  },
  created() {
    const store = useStore()

    store.dispatch('rootMain/requestClass', localStorage.getItem('jwt'))
    .then(function (result) {
      store.dispatch('rootMain/setClassInfo', result.data)
      console.log(result.data)
    })
    .catch(function (err) {
      console.log("error")
    })
  },

}
</script>
