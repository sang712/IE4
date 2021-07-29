<template>
  <div class="d-flex align-items-stretch ">
    <div class="col d-flex justify-content-center row">
      1번창
      <div class="meeting-preview d-flex justify-content-center row">
        <div class="host-profile" style="margin-top: 5%"> </div>
        <h1 class="text-center">자상길 선생님</h1>
        <h1 class="text-center">2학년 7반 수업을 시작합니다.</h1>
      </div>
      <el-button style="width: 25%; height: 10%; font-size: 140% ">참여하기</el-button>
    </div> 
    <div class="col">
      2번 창
      <div class="d-flex justify-content-center row">
        <div class="d-flex justify-content-evenly align-items-center" style="height: 15%" >
          <el-button style="width: 22%; height: 50%; font-size: 120%">시간표</el-button>
          <el-button style="width: 22%; height: 50%; font-size: 120%">알림창</el-button>
          <el-button style="width: 22%; height: 50%; font-size: 120%">MVP</el-button>             
        </div>
        <div class="notice-content" style="margin-top:25px"></div>
      </div>

    </div> 

  </div>
</template>

<script>
export default {
  name: 'section-home', 
}
</script>

<style>
 .col {
    background-color: #efeee9 ;
    margin: 5px 5px;
    height: 50vh;
  }
  .host-profile{
    width: 200px;
    height: 200px;
    background-image: url('../../../assets/images/logo1.png'); 
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    border: 1px solid;
    border-radius: 100px;
  }

  .meeting-preview{
    height: 35vh;
    width: 39vw;
    background-color: #ffffff ;
    margin: 5px 5px;
  }
  .notice-content{
    height: 41vh;
    width: 32vw;
    background-color: #ffffff ;
    margin: 5px 5px;
  }
</style>