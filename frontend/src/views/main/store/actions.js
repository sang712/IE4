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
  const url = 'http://localhost:8080/users/' + localStorage.getItem('id')
  let header = { headers: { 'Content-Type': 'multipart/form-data' } }

  $axios.post(url, payload, header)
  .then(({ data }) => {
    console.log('성공하면 출력해!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
    console.log(data)
    // if(data.position === '학생'){
    //   context.commit('rootMain/setStudentMypageInfo', data, {root: true})
    //   // store.dispatch('rootMain/setMypageInfo', result.data)
    // }else{
    //   context.commit('rootMain/setTeacherMypageInfo', data, {root: true})
    // }
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
  const url = 'http://localhost:8080/users?id=' + localStorage.getItem('id') + '&classId=' + localStorage.getItem('classId')
  let header = { headers: { "Content-Type": "multipart/form-data" } }

  $axios.post(url, payload, header)
  .then(({ data }) => {
    console.log(data)
    // if(data.position === '학생'){
    //   context.commit('rootMain/setStudentMypageInfo', data, {root: true})
    //   // store.dispatch('rootMain/setMypageInfo', result.data)
    // }else{
    //   context.commit('rootMain/setTeacherMypageInfo', data, {root: true})
    // }
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
  // let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.post(url, payload)
}

export function getRanking ({ state }, token) {
  console.log('getRanking')
  console.log(localStorage.getItem('classId'))
  const url = 'http://localhost:8080/class/ranking/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.get(url, header)
}

export function requestSchoolSchedule ({ state }) {
  console.log('requestSchoolSchedule')
  const url = 'https://open.neis.go.kr/hub/SchoolSchedule';
  const today = new Date();
  const tYear = today.getFullYear();
  const tMonth = today.getMonth();
  const tDate = today.getDate();
  const theDayOfWeek = today.getDay();  // 요일
  console.log("theDayOfWeek " + theDayOfWeek);

  // 해당일 기준 n일 전후 구하기
  // https://falsy.me/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%A1%9C-%EC%9D%B4%EB%B2%88%EC%A3%BC-7%EC%9D%BC-%EA%B5%AC%ED%95%98%EA%B8%B0/

  // 월요일
  var resultDay = new Date(tYear, tMonth, tDate + (1 - theDayOfWeek));
  var yyyy = resultDay.getFullYear();
  var mm = Number(resultDay.getMonth()) + 1;
  var dd = resultDay.getDate();

  mm = String(mm).length === 1 ? '0' + mm : mm;
  dd = String(dd).length === 1 ? '0' + dd : dd;

  const startDay = yyyy + '' + mm + '' + dd;

  // 금요일
  var resultDay = new Date(tYear, tMonth, tDate + (5 - theDayOfWeek));
  var yyyy = resultDay.getFullYear();
  var mm = Number(resultDay.getMonth()) + 1;
  var dd = resultDay.getDate();

  mm = String(mm).length === 1 ? '0' + mm : mm;
  dd = String(dd).length === 1 ? '0' + dd : dd;

  const endDay = yyyy + '' + mm + '' + dd;

  console.log(startDay + "   " + endDay)

  const params = {
    KEY: '45b841d402874577b4ed221dee0ebd3a',
    Type: 'json',
    // pindex: 1,
    // pSize: 1,
    ATPT_OFCDC_SC_CODE: 'B10',   // 시도교육청코드
    SD_SCHUL_CODE: '7051125',    // 표준학교코드
    AA_FROM_YMD: startDay,       // 학사시작일자
    AA_TO_YMD: endDay,           // 학사종료일자
  };

  return $axios.get(url, {params})
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

export function setNewsMovePage ({state}, pageIndex ){
  state.newsboard.currentPageIndex = pageIsndex;
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

export function setSchoolSchedule ({ state }, response) {
  console.log(response)
  state.schoolSchedule.list = response

  // state.schoolSchedule.event_nm = response.event_nm
  // state.schoolSchedule.one_grade_event_yn = response.one_grade_event_yn
  // state.schoolSchedule.tw_grade_event_yn = response.tw_grade_event_yn
  // state.schoolSchedule.three_grade_event_yn = response.three_grade_event_yn
  // state.schoolSchedule.fr_grade_event_yn = response.fr_grade_event_yn
  // state.schoolSchedule.fiv_grade_event_yn = response.fiv_grade_event_yn
  // state.schoolSchedule.six_grade_event_yn = response.six_grade_event_yn

}
