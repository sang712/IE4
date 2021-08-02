<template>
  <div class="section-mvp">
    <div class="d-flex justify-content-evenly text-center">
      <h2 class="grade-silver col-3" >임아연</h2>
      <h2 class="grade-gold col-3" >임징크</h2>
      <h2 class="grade-bronze col-3" >임제트</h2>      
    </div>
  </div>
</template>

<script>
export default {
  name: 'sectionmvp'
}
</script>

<style>
.grade-silver{
  font-weight: bold;
  text-align: right;
  margin-top: 16.5vh;
  margin-left: 5px;
  text-shadow: 2px 2px 0px silver;
}

.grade-gold{
  font-weight: bold;
  text-align: center;
  margin-top: 9.5vh;
  text-shadow: 2px 2px 0px gold;
}
.grade-bronze{
  font-weight: bold;
  text-align: left;
  margin-top: 16.5vh;
  text-shadow: 2px 2px 0px burlywood;
}
.section-mvp {
  background-image: url('../../../assets/images/mvp.jpg');
}
.mvp-title {
  color: white;
  margin-top: 10px;
  margin-left: 20px;
}
.mvp-content {
  color: rgb(3, 0, 0);
  font-size: 20px;
  text-align: center;
  margin: 15px 5px;
}
</style>