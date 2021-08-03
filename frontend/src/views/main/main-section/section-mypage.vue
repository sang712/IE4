<template>
  <div class="section-mypage d-flex justify-content-center row">
    <div class="mypage-in-background d-flex flex-row">
      <div style="margin-right:100px">
        <div class="profile-img"></div>
        <el-button style="width: 60%; height: 10%; font-size: 120% ;margin-left: 82px; ">프로필수정</el-button>     
      </div>
      <div class="mypage-second-background">

<!-- ###### 여기에다가 회원정보값을 넣으면 됩니다.###### -->
        <h2>아이디:</h2>
        <h3>dddddddddd</h3>
      </div>
    </div>
    <div class="d-flex justify-content-evenly">
      <el-button style="width: 13%; height: 70%; font-size: 120%">수정</el-button> 
      <el-button style="width: 13%; height: 70%; font-size: 120%">탈퇴</el-button> 
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
  .section-mypage {
    background-color: #efeee9 ;
    margin: 5px 5px;
    height: 66vh;
    width: 83vw;
  }
  .mypage-in-background {
    background-color: #ffffff ;
    height: 52vh;
    width: 80vw;
    margin: 15px 5px 0px 5px;
  }
  .profile-img{
    margin: 35px auto;
    margin-left: 50px;
    height: 300px;
    background-image: url('../../../assets/images/profile-picture.png'); 
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    border: 1px solid;
    border-radius: 10px;
    min-width: 230px;
    max-width: 230px;
  }
  .mypage-second-background {
    background-color: #efeee9 ;
    margin-top: 30px;
    margin-right: 60px;
    height: 430px;
    width: 55vw;
  }
</style>