<template>
  <div class= "main-headers">
    <el-row
      class="main-header"
      :gutter="10"
      :style="{ 'height': height }">
      <div class="upper-header" >
        <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div>
        <div class="tool-wrapper">
          <div class="button-wrapper">
            <el-button  type="default" @click="clickNews" style="width: 10vw; font-size: 120%">공지사항</el-button>
            <el-button  type="default" @click="clickMeterials" style="width: 10vw; font-size: 120%">학습자료</el-button>
            <el-button  type="default" @click="clickMyclass" style="width: 10vw; font-size: 120%">우리반</el-button>
            <el-button  type="danger" @click="clickLogout" style="width: 10vw; font-size: 120%">로그아웃</el-button>
          </div>
        </div>
      </div>
    </el-row>
<!--################        급     훈         #############-->
    <el-row class="main-header" style="height: 14vh;">
      <div class="lower-header">
        <div class="class-motto">
          <h1>급훈 : {{ state.classInfo.classMotto }}</h1>
        </div>
      </div>
    </el-row>
  </div>


</template>
<script>
import { reactive, computed, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'main-header',

  props: {
    height: {
      type: String,
      default: '70px'
    }
  },

  setup() {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      isLogin: computed(() => {
        const jwt = localStorage.getItem('jwt')
        return false ? jwt == null : true
      }),
      searchValue: null,
      isCollapse: true,
      menuItems: computed(() => {
        const MenuItems = store.getters['root/getMenus']
        let keys = Object.keys(MenuItems)
        let menuArray = []
        for (let i = 0; i < keys.length; ++i) {
          let menuObject = {}
          menuObject.icon = MenuItems[keys[i]].icon
          menuObject.title = MenuItems[keys[i]].name
          menuArray.push(menuObject)
        }
        return menuArray
      }),
      activeIndex: computed(() => store.getters['root/getActiveMenuIndex']),
      classInfo: computed(() => store.getters['rootMain/getClassInfo'])
    })

    if (state.activeIndex === -1) {
      state.activeIndex = 0
      store.commit('root/setMenuActive', 0)
    }

    const menuSelect = function (param) {
      store.commit('root/setMenuActive', param)
      const MenuItems = store.getters['root/getMenus']
      let keys = Object.keys(MenuItems)
      router.push({
        name: keys[param]
      })
    }

    const clickLogo = () => {
      router.push({ name: 'schedule' })
    }

    const clickNews = () => {
      router.push({ name: 'news' })
    }

    const clickMeterials = () => {
      router.push({ name: 'board', params:{classId:103, boardType:"학습자료"}})
    }

    const clickMyclass = () => {
      router.push({ name: 'myclass' })
    }

    const clickSignup = () => {
      emit('openSignupDialog')
    }

    const clickLogin = () => {
      emit('openLoginDialog')
    }

    const clickLogout = () => {
      const jwt = localStorage.getItem('jwt')
      console.log(jwt)
      localStorage.removeItem('jwt')
      localStorage.removeItem('id')
      localStorage.removeItem('classId')
      localStorage.removeItem('name')
      localStorage.removeItem('profileImgUrl')
      localStorage.removeItem('teacherProfileImgUrl')
      localStorage.removeItem('position')
      localStorage.removeItem('sex')
      localStorage.removeItem('snum')
      localStorage.removeItem('teacherName')
      // router.push('/')
      // location.reload()
      // router.push({name:'home'})
      router.go()
    }

    const changeCollapse = () => {
      state.isCollapse = !state.isCollapse
    }

    return { state, menuSelect, clickLogo, clickNews, clickMeterials, clickMyclass, clickSignup, clickLogin, clickLogout, changeCollapse }
  }
}
</script>
<style>
  .main-headers{
    background-image: url('../../../assets/images/main-header2.jpg');
    background-position:center;

  }
  .lower-header{
    height: 100%;
  }

  .class-motto {
    height: 80%;
    background: rgba(255, 255, 255, 0.80);
    padding: 20px !important;
    margin: 10px auto;
    border-radius: 20px;
    text-align: center;

    /* vertical-align: middle; */
  }

  .main-header {
    padding: 10px 20px;
  }
  /*Mobile, Tablet*/

  .main-header.upper-header{
    display: flex;
    justify-content: space-between;
  }
  .menu-icon-wrapper {
    display: inline-block;
    vertical-align: top;
    position: relative;
    top: 14px;
  }

  .main-header .hide-on-big .logo-wrapper {
    display: inline-block;
    margin: 0 calc(50% - 51px)
  }
  .main-header .hide-on-big .logo-wrapper .ic.ic-logo {
    width: 70px;
    height: 50px;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/ssafy-logo.png');
  }
  .mobile-sidebar-wrapper {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  .mobile-sidebar-wrapper .mobile-sidebar {
    width: 240px; height: calc(100vh - 1px);
    display: inline-block;
    background-color: white;
    padding: 0 10px;
    vertical-align: top;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .mobile-sidebar-tool-wrapper {
    padding-bottom: 20px;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .mobile-sidebar-btn {
    display: block;
    margin: 0 auto;
    margin-top: 25px;
    height: 30px;
    width: 100%;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .mobile-sidebar-btn.login-btn {
    color: white;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .logo-wrapper {
    display: block
  }
  .mobile-sidebar-wrapper .mobile-sidebar .logo-wrapper .ic.ic-logo {
    width: 70px;
    height: 50px;
    margin: 0 auto;
    margin-top: 30px;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/ssafy-logo.png');
  }
  .mobile-sidebar-wrapper .mobile-sidebar-backdrop {
    width: calc(100% - 260px); height: calc(100vh - 1px);
    background-color: black;
    display: inline-block;
    opacity: 0.3;
  }
  .mobile-sidebar-wrapper .el-menu{
    margin-top: 0;
    padding-left: 0;
    height: calc(100% - 235px);
  }
  .mobile-sidebar-wrapper .el-menu .el-menu-item {
    cursor: pointer;
  }
  .mobile-sidebar-wrapper .el-menu .el-menu-item .ic {
    margin-right: 5px;
  }

  /*Desktop - Need to add Class if Need*/
  .main-header .upper-header .logo-wrapper {
    cursor: pointer;
    display: inline-block;
  }
  .main-header .upper-header .logo-wrapper .ic.ic-logo {
    width: 70px;
    height: 50px;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/ssafy-logo.png');
  }
  .main-header .upper-header .tool-wrapper {
    width: 45%;
    float: right;
  }
  .main-header .upper-header .tool-wrapper .button-wrapper {
    width: 45vw;
    float: right;
  }
  .main-header .upper-header .tool-wrapper .button-wrapper .el-button {
    width: 45%;
    height: 50px;
    cursor: pointer;
    margin-right: 1%;
  }
  .main-header .upper-header .tool-wrapper .search-field {
    width: 50%;
    height: 50px;
    max-width: 400px;
    margin-right: 2%;
    display: inline-block;
    background-color: white;
  }
  .main-header .upper-header .tool-wrapper .search-field .el-input {
    width: 100%;
    height: 100%;
  }
  .main-header .upper-header .tool-wrapper .search-field .el-input .el-input__inner {
    width: 88%;
    height: 50px;
    margin-right: 1%;
  }
  .main-header .upper-header .tool-wrapper .search-field .el-input .el-input__prefix {
    top: 5px;
  }

</style>
