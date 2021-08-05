// API
import $axios from 'axios'

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

export function requestMyprofile ({ state }, token) {
  console.log('requestMyprofile')
  const url = 'http://localhost:8080/users'
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.get(url, header)
}

export function requestClass ({ state }, token) {
  console.log('requestClass')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/class/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
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

export function setClassInfo ({ state }, response) {
  console.log("이거 대나요?")

  state.classInfo.grade = response.grade
  state.classInfo.classNo = response.classNo
  state.classInfo.timetable = response.timetable
  state.classInfo.classMotto = response.classMotto
  state.classInfo.url = response.conferenceUrl

  console.log(response.grade + "     " + state.classInfo.grade)
}
