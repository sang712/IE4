import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/home'
import ConferencesDetail from '@/views/conferences/conference-detail'
import History from '@/views/history/history'
import SectionHome from '@/views/main/main-section/section-home'
import SectionSchedule from '@/views/main/main-section/section-schedule'
import SectionNote from '@/views/main/main-section/section-note'
import SectionMvp from '@/views/main/main-section/section-mvp'
import SectionNews from '@/views/main/main-section/section-news'
import SectionMeterials from '@/views/main/main-section/section-meterials'
import SectionMyclass from '@/views/main/main-section/section-myclass'

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
    path: '/conferences/:conferenceId',
    name: 'conference-detail',
    component: ConferencesDetail
  })
  // 로그인버튼  라우터
  routes.push({
    path: '/home',
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
  })
// 공지사항버튼 라우터
  routes.push({
    path: '/news',
    name: 'news',
    component: SectionNews,
  })
// 학습자료버튼 라우터
  routes.push({
    path: '/meterials',
    name: 'meterials',
    component: SectionMeterials,
  })
// 우리반버튼 라우터
  routes.push({
    path: '/myclass',
    name: 'myclass',
    component: SectionMyclass,
  })
// // 내정보버튼 라우터
//   routes.push({
//     path: '/mypage',
//     name: 'mypage',
//     component: SectionMypage,
//   })
  return routes
}
// const router = new VueRouter({
//   routes : [
//     {
//       path: '/home',
//       name: 'home',
//       component: SectionHome,
//       children: [
//         {          
//         },
//         // {
//         //   path: 'home',
//         //   name: 'home',
//         //   component: SectionHome,
//         // }
//       ]
//     }
//   ]

// })
const routes = makeRoutesFromMenu()

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.afterEach((to) => {
  console.log(to)
})

export default router
