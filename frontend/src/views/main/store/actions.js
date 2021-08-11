// API

import { toHandlers } from 'vue'
import http from "@/common/lib/axios.js";
import $axios from 'axios'
import { routerKey } from 'vue-router';
import { useRouter } from 'vue-router'

const router = useRouter()

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
}

export function updateStudent (context, payload){
  console.log('updateStudent')
  console.log(context.state)
  console.log(typeof(payload.password))
  const url = 'http://localhost:8080/users/student'
  // let header = { headers: { 'Content-Type': 'multipart/form-data' } }

  $axios.post(url, payload)
  .then(({ data }) => {
    console.log('성공하면 출력해!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
    console.log(data)

    Swal.fire({
      title: '성공!',
      text: '수정되었습니다!',
      icon: 'success',
    })
    router.go()

  })
  .catch(function (err) {
    const status = err.response.request.status
    if (status == 500) {
      Swal.fire({
        title: '이런!',
        text: '서버 오류가 발생했습니다..',
        icon: 'error',
      })
    } else{
      Swal.fire({
        title: '이런!',
        text: '수정에 실패했습니다..',
        icon: 'error',
      })
    }
  })
}

export function updateTeacher (context, payload){
  console.log('updateTeacher')
  console.log(context.state)
  const url = 'http://localhost:8080/users/teacher'
  // let header = { headers: { "Content-Type": "multipart/form-data" } }

  $axios.post(url, payload)
  .then(({ data }) => {
    console.log(data)

    Swal.fire({
      title: '성공!',
      text: '수정되었습니다!',
      icon: 'success',
    })

  })
  .catch(function (err) {
    const status = err.response.request.status
    if (status == 500) {
      Swal.fire({
        title: '이런!',
        text: '서버 오류가 발생했습니다..',
        icon: 'error',
      })
    } else{
      Swal.fire({
        title: '이런!',
        text: '수정에 실패했습니다..',
        icon: 'error',
      })
    }
  })
}

export function deleteUser({ state }, payload, token){
  console.log('deleteUser', state, payload)
  console.log(state)

  const url = 'http://localhost:8080/users/'
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  let body = payload
  return $axios.delete(url, body, header)
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

export function updateTimetable ({ state }, payload, token) {
  console.log('updateTimetable')
  console.log(localStorage.getItem('classId'))
  console.log(payload);
  const url = 'http://localhost:8080/class/timetable/' + localStorage.getItem('classId');
  //let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.post(url, payload)
}

export function getRanking ({ state }, token) {
  console.log('getRanking')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/class/ranking/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.get(url, header)
}

export function setNewsBoardList({ state }, response){
  state.newsboard.list = response.content
  state.newsboard.totalListItemCount = response.totalElements
  state.newsboard.currentPageIndex = response.pageNumber+1
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

export function requestNewsBoardList ({ state }, payload) {
  console.log('requestNewsBoardList')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/board';

  return $axios.get(url,  {params:{classId:localStorage.getItem('classId'), boardType:"공지사항", page:payload.currentPageIndex}})
}

export function requestBoardList ({ state }, payload) {
  console.log('requestBoardList')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/board';

  return $axios.get(url,  {params:{classId:localStorage.getItem('classId'), boardType:"학습자료", page:payload.currentPageIndex}})
}

export function requestBoardDetail ({state}, payload){
  console.log('requestBoardDetail')
  const url = 'http://localhost:8080/board/'+ payload.boardId;
  console.log("get : ", url)
  return $axios.get(url)
}
export function deleteDetail ({state}, payload){
  console.log('deleteDetail')
  console.log(">>>",payload, "// ", payload.boardId)
  const url = 'http://localhost:8080/board/'+ payload.boardId;
  console.log("delete : ", url)
  return $axios.delete(url)
}
export function setBoardDetail({ state }, response){
  console.log('setBoardDetail response: ', response)
  state.boardDetail.boardId = response.boardId
  state.boardDetail.userId = response.userId
  state.boardDetail.title = response.title
  state.boardDetail.content = response.content
  state.boardDetail.userName = response.writer
  state.boardDetail.regDt = response.regDt
  state.boardDetail.fileName = response.fileName
  state.boardDetail.fileUrl = response.fileUrl
}
export function setBoardType({state}, payload){
  console.log("setBoardType")
  state.boardType.type = payload;
}
export function requestBoardInsert({state}, payload){
  console.log('requestBoardInsert')
  const url = 'http://localhost:8080/board/';
  return $axios.post(url, payload)
}
export function requestBoardUpdate({state}, payload){
  console.log('requestBoardUpdate')
  const url = 'http://localhost:8080/board/';
  return $axios.patch(url, payload)
}

export function setClassInfo ({ state }, response) {
  state.classInfo.grade = response.grade
  state.classInfo.classNo = response.classNo
  state.classInfo.timetable = response.timetable
  state.classInfo.classMotto = response.classMotto
  state.classInfo.url = response.conferenceUrl

  console.log(response.grade + "     " + state.classInfo.grade)
}

export function setClassMemList ({ state }, response) {
  console.log(response)
  state.classMemList.list = response
}

export function setRankingList ({ state }, response) {
  console.log(response)
  state.rankingList = response
}
