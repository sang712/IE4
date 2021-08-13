<template>
  <div class="section-myclass">
    <h1 class="myclass-title">우리반 정보</h1>
    <div class="myclass-content">
      <div class="myclass-profile">
        <img class="myclass-profile-image" :src="classMemList[0].profileImgUrl" :alt="classMemList[0].name+'선생님의 프로필 사진'">
        <div class="myclass-profile-name">교사: {{ classMemList[0].name }}</div>
      </div>
      <br>
      <div class="myclass-profile" v-for="(classMem, index) in classMemList.slice(1)" :key="index">
        <img class="myclass-profile-image" v-if="classMem.profileImgUrl != null" :src="classMem.profileImgUrl" :alt="classMem.name+'의 프로필 사진'">
        <img class="myclass-profile-image" v-else :src="'../../../assets/images/no_profile_img_student'+classMem.name.charCodeAt([1])%2+'.jpg'" :alt="classMem.name+'의 프로필 사진'">
        <div class="myclass-profile-name">이름: {{ classMem.name }}</div>
      </div>
    </div>
  </div>
</template>
<script>

import { reactive, computed, toRefs } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'section-myclass',

  setup(props, { emit }) {
    const store = useStore();

    const state = reactive({
      classMemList: computed(() => store.getters['rootMain/getClassMemList'].list)
    })

    store.dispatch('rootMain/requestClassMem', localStorage.getItem('jwt'))
    .then(function (result) {
      console.log("여기 안와?")
      console.log(result.data)
      store.dispatch('rootMain/setClassMemList', result.data)
    })
    .catch(function (err) {
      console.log("requestClassMem error")
    })

    console.log("classMemList >>>> " + state.classMemList)

    return {
      ...toRefs(state)
    }
  },
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
  text-align: center;
}
.myclass-profile {
  margin: 1% 0% 5px 1.4%;
  display: inline-block;
  width: 15%;
  max-height: 40%;
}
.myclass-profile-image {
  width: 100%;
  height: 15vw;
  border: 1px solid darkgray;
  border-radius: 10px;
  background-color: #c4c4c4;
}
</style>
