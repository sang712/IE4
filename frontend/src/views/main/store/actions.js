// API

import { toHandlers } from 'vue'
import http from "@/common/lib/axios.js";

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/users'
  let body = payload
  return $axios.post(url, body)
}

export function requestMyprofile (context, token) {
  console.log('requestMyprofile')
  const url = 'http://localhost:8080/users'
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
<<<<<<< HEAD
<<<<<<< HEAD
=======
  return $axios.get(url, header)
=======
  $axios.get(url, header)
  .then(function (result) {
    console.log(result.data)
    if(result.data.position === '학생'){
      context.commit('rootMain/setStudentMypageInfo', result.data, {root: true})
      // store.dispatch('rootMain/setMypageInfo', result.data)
    }else{
      context.commit('rootMain/setTeacherMypageInfo', result.data, {root: true})
    }
  })
  .catch(function (err) {
    console.log('에러 정보', err.response)
  })
  return
>>>>>>> 1f2c59d6ccf4972e79b8e6f198cc20e08db18b85
}


export function requestClass ({ state }, token) {
  console.log('requestClass')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/class/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.get(url, header)
}

export function requestClassMem ({ state }, token) {
  console.log('requestClassMem')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/class/myclass/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
>>>>>>> 89266b88cd22546031b0f6f686b9220061fbf45f
  return $axios.get(url, header)
}

export function setMypageInfo ({ state }, response) {
  state.mypageInfo.id = response.id
  state.mypageInfo.loginId = response.loginId
  state.mypageInfo.name = response.name
  state.mypageInfo.parentPhone = response.parentPhone
  state.mypageInfo.password = response.password
  state.mypageInfo.passwordAnswer = response.passwordAnswer
  state.mypageInfo.passwordQuestion = response.passwordQuestion
  state.mypageInfo.phone = response.phone
  state.mypageInfo.snum = response.snum
  state.mypageInfo.address = response.address
}
<<<<<<< HEAD
export function setBoardList({ state }, response){
  state.board.list = response.data.content
}
export function setNewsBoardList({ state }, response){
  state.newsboard.list = response.data.content
}

=======

export function setClassInfo ({ state }, response) {
  console.log("이거 대나요?")

  state.classInfo.grade = response.grade
  state.classInfo.classNo = response.classNo
  state.classInfo.timetable = response.timetable
  state.classInfo.classMotto = response.classMotto
  state.classInfo.url = response.conferenceUrl

  console.log(response.grade + "     " + state.classInfo.grade)
}

export function setClassMemList ({ state }, response) {
  console.log("데이터 넣으러 오나?")
  console.log(response)
  state.classMemList.list = response
}
>>>>>>> 89266b88cd22546031b0f6f686b9220061fbf45f
