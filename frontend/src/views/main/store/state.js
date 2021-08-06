import { reactive } from 'vue';

// ROOT STATE 변수 정의 및 기본값 대입
const menuData = require('@/views/main/menu.json')

/**
 * 플랫폼 관련 정보로 데스크탑인지, 모바일인지 판별 - 하이브리드 앱 대비
 */
function getIsDesktop() {
  var userAgent = window.navigator.userAgent,
      platform = window.navigator.platform,
      macosPlatforms = ['Macintosh', 'MacIntel', 'MacPPC', 'Mac68K'],
      windowsPlatforms = ['Win32', 'Win64', 'Windows', 'WinCE'],
      iosPlatforms = ['iPhone', 'iPad', 'iPod'],
      os = null;

  if (macosPlatforms.indexOf(platform) !== -1) { //Desktop - Mac
    return true
  } else if (iosPlatforms.indexOf(platform) !== -1) { // IOS
    return false
  } else if (windowsPlatforms.indexOf(platform) !== -1) { //Desktop - window
    return true
  } else if (/Android/.test(userAgent)) { //Android
    return false
  } else if (!os && /Linux/.test(platform)) { //Linux
    return true
  }

  return os;
}

const IsDesktop = getIsDesktop()

const mypageInfo = reactive({
  id: '',
  loginId: '',
  name: '',
  position: '',
  parentPhone: '',
  password: '',
  passwordAnswer: '',
  passwordQuestion: '',
  phone: '',
  snum: '',
  address: '',
  sex: '',
  classMotto: ''
})

const classInfo = reactive({
  grade: '',
  classNo: '',
  timetable: '',
  classMotto: '',
  url: ''
})

const classMemList = reactive({
  list: [],

  name: '',
  profileImgUrl: ''
})

const board={
  //list
  list: [],
  limit: 10,
  offset: 0,
  searchWord: "",

  //detail
  no: 0,
  boardId: 0,
  title: "",
  content: "",
  userName: "",
  regDt: {}
}

const newsboard=reactive({
  //list
  list: [],
  limit: 10,
  offset: 0,
  searchWord: "",

  //detail
  no: 0,
  boardId: 0,
  title: "",
  content: "",
  userName: "",
  regDt: {}
})

export default {
  isDesktopPlatform: IsDesktop,
  activeMenu: 'home',
  menus: menuData,
  mypageInfo,
<<<<<<< HEAD
  board,
  newsboard,
=======
  classInfo,
  classMemList,
>>>>>>> 89266b88cd22546031b0f6f686b9220061fbf45f
}
