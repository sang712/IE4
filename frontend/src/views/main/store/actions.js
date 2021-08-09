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

export function requestMyprofile (context, token) {
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
  let header = { headers: { 'Content-Type': 'multipart/form-data' } }

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
