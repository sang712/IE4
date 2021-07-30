<template>
  <div class="section-meterials">
    <h1 class="meterials-title">학습자료</h1>
    <ul class="meterials-table">
      <li class="table-header">
        <div class="header-number">No</div>
        <div class="header-title">제목</div>
        <div class="header-author">작성자</div>
        <div class="header-date">날짜</div>
      </li>
      <li class="table-row">
        <div class="row-number">4</div>
        <div class="row-title">[사회] 8월 13일 연구수업 자료</div>
        <div class="row-author">강싸피 선생님</div>
        <div class="row-date">2021.07.24</div>
      </li>
      <li class="table-row">
        <div class="row-number number">3</div>
        <div class="row-title">[영어] 3주차 영단어</div>
        <div class="row-author">강싸피 선생님</div>
        <div class="row-date">2021.07.23</div>
      </li>
      <li class="table-row">
        <div class="row-number">2</div>
        <div class="row-title">[음악] 수행평가 참고 사이트</div>
        <div class="row-author">강싸피 선생님</div>
        <div class="row-date">2021.07.21</div>
      </li>
      <li class="table-row">
        <div class="row-number">1</div>
        <div class="row-title">[수학] 비례식 연습문제</div>
        <div class="row-author">강싸피 선생님</div>
        <div class="row-date">2021.07.21</div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'section-meterials'
}
</script>

<style>
.section-meterials {
  width: 99.8%;
  height: 100%;
  margin: 5px 1px;
  padding: 5px 5px;
  background-color: #efeee9;
}
.meterials-title {
  text-align: center;
  margin: 20px 0px;
}
.meterials-title {
  text-align: center;
  margin: 20px 0px;
}
.meterials-table {
  padding: 0px;
}
.meterials-table li {
  border-radius: 15px;
  padding: 20px 30px;
  margin: 0px 5%;
  font-size: 130%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 1px;
}
.meterials-table .table-header {
  background-color: #95A5A6;
  font-weight: bold;
  text-transform: uppercase;
  letter-spacing: 0.03em;
}
.meterials-table .table-row {
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