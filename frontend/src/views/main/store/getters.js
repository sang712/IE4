// 플랫폼 관련 정보 가져오기
export function getIsDesktopPlatform (state) {
  return state.isDesktopPlatform
}
// 메뉴 객체 가져오기
export function getMenus (state) {
	return state.menus
}
// Active된 메뉴 인덱스 가져오기
export function getActiveMenuIndex (state) {
	const keys = Object.keys(state.menus)
	return keys.findIndex(item => item === state.activeMenu)
}
export function getBoardList(state) {
  return state.board;
}
export function getNewsBoardList(state) {
  return state.newsboard;
}
export function getBoardDetail(state) {
  return state.boardDetail;
}
// classInfo 가져오기
export function getClassInfo (state) {
	return state.classInfo
}
// classMemList 가져오기
export function getClassMemList (state) {
	return state.classMemList
}
export function getBoardType (state) {
  return state.boardType
}
// rankingList 가져오기
export function getRankingList (state) {
	return state.rankingList
}
// rankingList 가져오기
export function getSchoolSchedule (state) {
	return state.schoolSchedule
}

// myInfo 가져오기
export function getMypageInfo (state) {
  return state.mypageInfo
}
