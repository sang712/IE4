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

export function requestMyprofile ({ state }, token) {
  console.log('requestMyprofile')
  const url = 'http://localhost:8080/users'
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
export function setBoardList({ state }, response){
  state.board.list = response.data.content
}
export function setNewsBoardList({ state }, response){
  state.newsboard.list = response.data.content
}

