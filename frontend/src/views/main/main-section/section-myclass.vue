<template>
  <div class="section-myclass">
    <h1 class="myclass-title">우리반 정보</h1>
    <div class="myclass-content">
      <div class="myclass-profile">
        <img class="myclass-profile-image" src="#" alt="선생님">
        <div class="myclass-profile-name">성함: OOO선생님</div>
      </div>
      <div class="myclass-profile" v-for="(classMem, index) in classMemList" :key="index">
        <img class="myclass-profile-image" v-if="classMem.profileImgUrl != null" :src="classMem.profileImgUrl" alt="item.name">
        <img class="myclass-profile-image" v-else src="/profileImg/no_profile_img_girl.png" alt="이미지 없음">
        <div class="myclass-profile-name">이름: {{ classMem.name }}</div>
      </div>
    </div>
  </div>
</template>
<script>

import { useStore } from 'vuex'

export default {
  name: 'section-myclass',
  data() {
    return {
      items:
      [
        { id:1, image: require('../../../assets/images/sample-image.png') },
        { id:2, image: require('../../../assets/images/sample-image.png') },
        { id:3, image: require('../../../assets/images/sample-image.png') },
        { id:4, image: require('../../../assets/images/sample-image.png') },
        { id:5, image: require('../../../assets/images/sample-image.png') },
        { id:6, image: require('../../../assets/images/sample-image.png') },
        { id:7, image: require('../../../assets/images/sample-image.png') },
        { id:8, image: require('../../../assets/images/sample-image.png') },
        { id:9, image: require('../../../assets/images/sample-image.png') },
        { id:10, image: require('../../../assets/images/sample-image.png') },
        { id:11, image: require('../../../assets/images/sample-image.png') },
        { id:12, image: require('../../../assets/images/sample-image.png') },
        { id:13, image: require('../../../assets/images/sample-image.png') },
        { id:14, image: require('../../../assets/images/sample-image.png') },
        { id:15, image: require('../../../assets/images/sample-image.png') },
        { id:16, image: require('../../../assets/images/sample-image.png') },
      ],
      classMemList: [],
    }
  },
  created() {
    const store = useStore()

    store.dispatch('rootMain/requestClassMem', localStorage.getItem('jwt'))
    .then(function (result) {
      console.log("여기 안와?")
      console.log(result.data)
      store.dispatch('rootMain/setClassMemList', result.data)
    })
    .catch(function (err) {
      console.log("requestClassMem error")
    })

    this.classMemList = store.getters['rootMain/getClassMemList'].list
  }
}
</script>
<style>
.section-myclass {
  width: 99.8%;
  height: 100%;
  margin: 5px 1px;
  padding: 5px 5px;
  background-color: #efeee9;
}
.myclass-title {
  text-align: center;
  margin: 20px 0px;
}
.myclass-content {
  margin: auto auto;
  background-color: rgba(255, 255, 255, 0.8);
  width: 90%;
  height: 85%;
  overflow-y: scroll;
}
.myclass-profile {
  margin: 1% 0% 5px 1.4%;
  display: inline-block;
  width: 15%;
  max-height: 40%;
}
.myclass-profile-image {
  width: 100%;
  background-color: #C4C4C4;
}
</style>
