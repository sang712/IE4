// API

import { toHandlers } from 'vue'
import http from "@/common/lib/axios.js";
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

export function requestMyprofile ({state}, token) {
  console.log('requestMyprofile')
  const url = 'http://localhost:8080/users'
  let header = { headers: { 'Authorization': `Bearer ${token}` } }

  return $axios.get(url, header)

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

export function setNewsBoardList({ state }, response){
  state.newsboard.list = response.content
  state.newsboard.totalListItemCount = response.totalElements
  state.newsboard.currentPageIndex = response.pageable.pageNumber
  state.newsboard.pageLinkCount = response.totalPages
  state.newsboard.totalPageCount = response.totalPages
  state.newsboard.offset = response.pageable.offset
}

export function setBoardList({ state }, response){
  state.board.list = response.content
  state.board.totalListItemCount = response.totalElements
  state.board.currentPageIndex = response.pageable.pageNumber
  state.board.pageLinkCount = response.totalPages
  state.board.totalPageCount = response.totalPages
  state.board.offset = response.pageable.offset
}

export function requestNewsBoardList ({ state }, response) {
  console.log('requestNewsBoardList')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/board';

  return $axios.get(url,  {params:{classId:localStorage.getItem('classId'), boardType:"공지사항", page:state.newsboard.currentPageIndex}})
}

export function requestBoardList ({ state }, response) {
  console.log('requestBoardList')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/board';

  return $axios.get(url,  {params:{classId:localStorage.getItem('classId'), boardType:"학습자료", page:state.board.currentPageIndex}})
}

export function requestBoardDetail ({state}, payload){
  console.log('requestBoardDetail')
  const url = 'http://localhost:8080/board/'+ payload.boardId;
  console.log("get : ", url)
  return $axios.get(url)
}
export function setBoardDetail({ state }, response){
  console.log('setBoardDetail')
  state.boardDetail.boardId = response.boardId
  state.boardDetail.title = response.title
  state.boardDetail.content = response.content
  state.boardDetail.userName = response.writer
  state.boardDetail.regDt = response.regDt
  state.boardDetail.fileName = response.fileName
  state.boardDetail.fileUrl = response.fileUrl
}

export function setNewsMovePage ({state},pageIndex ){
  state.newsboard.currentPageIndex = pageIsndex;
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

export function setClassMemList ({ state }, response) {
  console.log("데이터 넣으러 오나?")
  console.log(response)
  state.classMemList.list = response
}
