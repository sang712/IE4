// API

import $axios from 'axios'

export function requestLogin ({ state }, payload) {
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  const url = '/users'
  let body = payload
  return $axios.post(url, body)
}

export function requestMyprofile (context, token) {
  const url = '/api/users'
  let header = { headers: { 'Authorization': `Bearer ${token}` } }

  $axios.get(url, header)
  .then(function (result) {
    if(result.data.position === '학생'){
      context.commit('rootMain/setStudentMypageInfo', result.data, {root: true})
    }else{
      context.commit('rootMain/setTeacherMypageInfo', result.data, {root: true})
    }
  })
  .catch(function (err) {
    console.log('에러 정보', err.response)
  })
}

export function updateStudent (context, payload){
  const url = '/api/users/student'

  let token = localStorage.getItem('jwt')
  let header = { headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'multipart/form-data' } }
  let body = payload

  $axios.post(url, body, header)
  .then(({ data }) => {
    context.commit('rootMain/setStudentMypageInfo', data, {root: true})
    Swal.fire({
      title: '성공!',
      text: '수정되었습니다!',
      icon: 'success',
    })
  })
  .catch(function (err) {
    Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
  })
}

export function updateTeacher (context, payload){
  const url = '/api/users/teacher'

  let token = localStorage.getItem('jwt')
  let header = { headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'multipart/form-data' } }
  let body = payload

  $axios.post(url, body, header)
  .then(({ data }) => {
    context.commit('rootMain/setTeacherMypageInfo', data, {root: true})
    context.commit('rootMain/setClassMotto', data.classMotto, {root: true})
    localStorage.setItem('profileImgUrl', data.profileImgUrl)
    Swal.fire({
      title: '성공!',
      text: '수정되었습니다!',
      icon: 'success',
    })
  })
  .catch(function (err) {
    console.log("updateTeacher error", err)
    Swal.fire({ title: '이런!', text: '에러가 발생했습니다.', icon: 'error', })
  })
}

export function deleteUser({ state }, payload){
  const url = '/api/users'
  let token = localStorage.getItem('jwt')
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  let body = payload

  return $axios.post(url, body, header)
}

export function requestClass ({ state }, token) {
  const url = '/api/class/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.get(url, header)
}

export function requestClassMem ({ state }, token) {
  const url = '/api/class/myclass/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }

  return $axios.get(url, header)
}

export function updateTimetable ({ state }, payload) {
  const token = localStorage.getItem('jwt')
  const url = '/api/class/timetable/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'multipart/form-data' } }
  return $axios.post(url, payload, header)
}

export function getRanking ({ state }, token) {
  const url = '/api/class/ranking/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.get(url, header)
}

export function getConferenceActive(context){
  const token = localStorage.getItem('jwt')
  const url = '/api/class/conferenceActive/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }

  return $axios.get(url, header)
}

export function updateConferenceActive(context, payload){

  const token = localStorage.getItem('jwt')
  const url = '/api/class/conferenceActive/' + localStorage.getItem('classId');
  let header = { headers: { 'Authorization': `Bearer ${token}` } }

  return $axios.patch(url, payload, header)
}

export function requestSchoolSchedule ({ state }) {
  const url = 'https://open.neis.go.kr/hub/SchoolSchedule';
  const today = new Date();
  const tYear = today.getFullYear();
  const tMonth = today.getMonth();
  const tDate = today.getDate();
  const theDayOfWeek = today.getDay();  // 요일

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

  const params = {
    KEY: '45b841d402874577b4ed221dee0ebd3a',
    Type: 'json',
    ATPT_OFCDC_SC_CODE: 'B10',   // 시도교육청코드
    SD_SCHUL_CODE: '7051125',    // 표준학교코드
    AA_FROM_YMD: startDay,       // 학사시작일자
    AA_TO_YMD: endDay,           // 학사종료일자
  };

  return $axios.get(url, {params})
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
  let token = localStorage.getItem('jwt')
  const url = '/api/board';
  return $axios.get(url, {
    params: { classId:100, boardType:"공지사항", page:payload.currentPageIndex },
    headers: { 'Authorization': `Bearer ${token}`  }
   })
}

export function requestBoardList ({ state }, payload) {
  let token = localStorage.getItem('jwt')
  const url = '/api/board';
  return $axios.get(url,  {
    params: { classId:localStorage.getItem('classId'), boardType:"학습자료", page:payload.currentPageIndex },
    headers: { 'Authorization': `Bearer ${token}`  }
   })
}

export function requestBoardDetail ({state}, payload){
  const url = '/api/board/'+ payload.boardId;
  let token = localStorage.getItem('jwt')
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.get(url, header)
}

export function deleteDetail ({state}, payload){
  const url = '/api/board/'+ payload.boardId;
  let token = localStorage.getItem('jwt')
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.delete(url, header)
}

export function setBoardDetail({ state }, response){
  state.boardDetail.boardId = response.boardId
  state.boardDetail.userId = response.userId
  state.boardDetail.title = response.title
  state.boardDetail.content = response.content
  state.boardDetail.userName = response.writer
  state.boardDetail.regDt = response.regDt
  state.boardDetail.fileName = response.fileName
  state.boardDetail.fileUrl = response.fileUrl
  if (response.userId == localStorage.getItem('id')){
    state.boardDetail.isOwner = true;
  }else{
    state.boardDetail.isOwner = false;
  }
}

export function setBoardType({state}, payload){
  state.boardType.type = payload;
}

export function requestBoardInsert({state}, payload){
  const url = '/api/board/';
  let token = localStorage.getItem('jwt')
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.post(url, payload,header)
}

export function requestBoardUpdate({state}, payload){
  const url = '/api/board/';
  let token = localStorage.getItem('jwt')
  let header = { headers: { 'Authorization': `Bearer ${token}` } }
  return $axios.patch(url, payload,header)
}

export function setClassInfo ({ state }, response) {
  state.classInfo.grade = response.grade
  state.classInfo.classNo = response.classNo
  state.classInfo.timetable = response.timetable
  state.classInfo.classMotto = response.classMotto
  state.classInfo.conferenceActive = response.conferenceActive
  state.classInfo.url = response.conferenceUrl
}

export function setClassMemList ({ state }, response) {
  state.classMemList.list = response
}

export function setRankingList ({ state }, response) {
  state.rankingList = response
}

export function setSchoolSchedule ({ state }, response) {
  state.schoolSchedule.list = response
}
