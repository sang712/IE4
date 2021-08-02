<template>
  <div>
    <div class="profile-img" style="max-width: 230px ; min-width: 230px;"></div>
    <h1>이름: 한상길</h1> 
    <h1>2학년 7반 30번</h1>
  </div>
</template>

<script>
export default {
  name: 'main-sidebar'
}
</script>

<style>
.profile-img{
  margin: 10vh auto;
  height: 23vh;
  background-image: url('../../../assets/images/profile-picture.png'); 
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  border: 1px solid;
  border-radius: 10px;
}
</style>