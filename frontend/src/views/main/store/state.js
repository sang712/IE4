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
  profileImgUrl: '',
  phone: '',
  snum: '',
  address: '',
  sex: '',
  classMotto: ''
})

const studentInfo = reactive({

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

const board = reactive({
  //list
  list: [],
  limit: 5,
  offset: 0,
  searchWord: "",

  //pagination
  listRowCount: 5,
  pageLinkCount: 10,
  currentPageIndex: 2,
  totalListItemCount : 0,
  totalPageCount : 5,

  //detail
  no: 0,
  boardId: 0,
  title: "",
  content: "",
  userName: "",
  regDt: {},
  isOwner:true,
})

const newsboard = reactive({
  //list
  list: [],
  limit: 5,
  offset: 0,
  searchWord: "",

  //pagination
  listRowCount: 5,
  pageLinkCount: 10,
  currentPageIndex: 2,
  totalListItemCount : 0,
  totalPageCount : 5,

  //detail
  no: 0,
  boardId: 0,
  title: "",
  content: "",
  userName: "",
  regDt: {},
  isOwner:true,
})

const boardDetail = reactive({
    //detail
    no: 0,
    boardId: 0,
    title: "",
    content: "",
    userName: "",
    regDt: {},

    fileName: "",
    fileUrl:"",
    isOwner:true,
})

const rankingList = reactive({})

const schoolSchedule = reactive({
  list: [],

  event_nm: "",                     // 행사명
  one_grade_event_yn: "",           // 1학년행사여부
  tw_grade_event_yn: "",            // 2학년행사여부
  three_grade_event_yn: "",         // 3학년행사여부
  fr_grade_event_yn: "",            // 4학년행사여부
  fiv_grade_event_yn: "",           // 5학년행사여부
  six_grade_event_yn: "",           // 6학년행사여부
})

export default {
  isDesktopPlatform: IsDesktop,
  activeMenu: 'home',
  menus: menuData,
  mypageInfo,
  board,
  newsboard,
  classInfo,
  classMemList,
  boardDetail,
  rankingList,
  schoolSchedule,
}
