export function setPlatform (state, isDesktop) {
  state.isDesktopPlatform = isDesktop
}

export function setMenuActive (state, index) {
	console.log('setMenuActive', state,index)
	const keys = Object.keys(state.menus)
	state.activeMenu = keys[index]
}

export function setMenuActiveMenuName (state, menuName) {
	state.activeMenu = menuName
}

export function setTimeTable(state, payload){
  state.classInfo.timetable = payload
}

export function setClassMotto(state, payload){
  state.classInfo.classMotto = payload
}

export function setStudentMypageInfo (state, payload) {
  state.mypageInfo.id = payload.id
  state.mypageInfo.loginId = payload.loginId
  state.mypageInfo.name = payload.name
  state.mypageInfo.position = payload.position
  state.mypageInfo.parentPhone = payload.parentPhone
  state.mypageInfo.password = null
  state.mypageInfo.passwordAnswer = payload.passwordAnswer
  state.mypageInfo.passwordQuestion = payload.passwordQuestion
  state.mypageInfo.phone = payload.phone
  state.mypageInfo.snum = payload.snum
  state.mypageInfo.sex = payload.sex
  state.mypageInfo.address = payload.address
  state.mypageInfo.profileImgUrl = payload.profileImgUrl
}

export function setTeacherMypageInfo (state, payload) {
  state.mypageInfo.id = payload.id
  state.mypageInfo.loginId = payload.loginId
  state.mypageInfo.name = payload.name
  state.mypageInfo.position = payload.position
  state.mypageInfo.password = null
  state.mypageInfo.phone = payload.phone
  state.mypageInfo.address = payload.address
  state.mypageInfo.sex = payload.sex
  state.mypageInfo.profileImgUrl = payload.profileImgUrl
}
