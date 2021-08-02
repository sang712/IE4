<template>
  <div class="section-news">
    <h1 class="news-title">공지사항</h1>
    <ul class="news-table">
      <li class="table-header">
        <div class="header-number">No</div>
        <div class="header-title">제목</div>
        <div class="header-author">작성자</div>
        <div class="header-date">날짜</div>
      </li>
      <li class="table-row">
        <div class="row-number">4</div>
        <div class="row-title">8월 13일 연구수업 안내</div>
        <div class="row-author">강싸피 선생님</div>
        <div class="row-date">2021.07.21</div>
      </li>
      <li class="table-row">
        <div class="row-number number">3</div>
        <div class="row-title">코로나 관련 학생 방역지침 안내</div>
        <div class="row-author">관리자</div>
        <div class="row-date">2021.07.20</div>
      </li>
      <li class="table-row">
        <div class="row-number">2</div>
        <div class="row-title">학기중 도서관 이용 안내</div>
        <div class="row-author">관리자</div>
        <div class="row-date">2021.07.05</div>
      </li>
      <li class="table-row">
        <div class="row-number">1</div>
        <div class="row-title">온라인 수업 안내</div>
        <div class="row-author">관리자</div>
        <div class="row-date">2021.07.05</div>
      </li>
    </ul>
    <!-- 페이지네이션 기능 미구현 -->
    <!-- <el-pagination
      :page-size="5"
      :pager-count="5"
      layout="prev, pager, next"
      :total="4">
    </el-pagination> -->
  </div>
</template>

<script>
export default {
  name: 'section-news'
}
</script>

<style>
.section-news {
  width: 99.8%;
  height: 100%;
  margin: 5px 1px;
  padding: 5px 5px;
  background-color: #efeee9;
}
.news-title {
  text-align: center;
  margin: 20px 0px;
}
.news-table {
  padding: 0px;
}
.news-table li {
  border-radius: 15px;
  padding: 20px 30px;
  margin: 0px 5%;
  font-size: 130%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 1px;
}
.news-table .table-header {
  background-color: #95A5A6;
  font-weight: bold;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}
.news-table .table-row {
  background-color: #ffffff;
  box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.1);
}
li .header-number, .row-number{
  flex-basis: 10%;
}
li .header-title, .row-title{
  flex-basis: 50%;
}
li .header-author, .row-author{
  text-align: center;
  flex-basis: 15%;
}
li .header-date, .row-date{
  text-align: center;
  flex-basis: 15%;
}
</style>