import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/home'
import ConferencesDetail from '@/views/conferences/conference-detail'
import History from '@/views/history/history'
import Main from '@/views/main/main'
import Start from '@/views/start/start'
import SectionHome from '@/views/main/main-section/section-home'
import SectionSchedule from '@/views/main/main-section/section-schedule'
import SectionNote from '@/views/main/main-section/section-note'
import SectionMvp from '@/views/main/main-section/section-mvp'
import SectionNews from '@/views/main/main-section/section-news'
import SectionMeterials from '@/views/main/main-section/section-meterials'
import SectionMyclass from '@/views/main/main-section/section-myclass'
import SectionMypage from '@/views/main/main-section/section-mypage'
import Conference from '@/views/conferences/conference.vue'

const fullMenu = require('@/views/main/menu.json')
function makeRoutesFromMenu () {
  let routes = Object.keys(fullMenu).map((key) => {
    if (key === 'home') {
      return { path: fullMenu[key].path, name: key, component: Home  }
    } else if (key === 'history') {
      return { path: fullMenu[key].path, name: key, component: History }
    } else { // menu.json 에 들어있는 로그아웃 메뉴
      return null
    }
  })
  // 로그아웃 파싱한 부분 제거
  routes = routes.filter(item => item)
  // menu 자체에는 나오지 않는 페이지 라우터에 추가(방 상세보기)
  routes.push({
    path: '/conference/:conferenceId',
    name: 'conference-detail',
    component: ConferencesDetail
  })

  // 로그인 화면 라우터
  routes.push({
    path: '/',
    name: 'start',
    component: Start,
  })

  // 메인 화면 라우터
  routes.push({
    path: '/main',
    name: 'main',
    component: Main,
    children: [
      {
        path: '',
        name: 'home',
        component: SectionHome,
        children: [
            {
              // 시간표버튼 라우터
            path: '',
            name: 'schedule',
            component: SectionSchedule,
          },
          {
            // 알림장버튼 라우터
            path: 'note',
            name: 'note',
            component: SectionNote,
          },
          {
            // MVP버튼 라우터
            path: 'mvp',
            name: 'mvp',
            component: SectionMvp,
          }
        ]
      },
      // 공지사항버튼 라우터
      {
        path: '/news',
        name: 'news',
        component: SectionNews,
      },
      // 학습자료버튼 라우터
      {
        path: '/board',
        name: 'board',
        component: SectionMeterials,
      },
      // 우리반버튼 라우터
      {
        path: '/myclass',
        name: 'myclass',
        component: SectionMyclass,
      },
      // 내정보버튼 라우터
      {
        path: '/mypage',
        name: 'mypage',
        component: SectionMypage,
      },
    ],
  })

  // 회의 참가 화면 라우터
  routes.push({
    // path: 'wss://' + location.host + '/groupcall',
    path: '/conference',
    name: 'conference',
    component: Conference,
  })
  return routes
}
const routes = makeRoutesFromMenu()

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.afterEach((to) => {
  console.log(`${to.fullPath} 로 이동`, to)
})

export default router
