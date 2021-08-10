<template>
  <div class="section-mvp">
    <div class="d-flex justify-content-evenly text-center">
      <h2 class="grade-silver col-3" >{{ ranking[1] }}</h2>
      <h2 class="grade-gold col-3" >{{ ranking[0] }}</h2>
      <h2 class="grade-bronze col-3" >{{ ranking[2] }}</h2>
    </div>
  </div>
</template>

<script>
import { reactive, computed, toRefs } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'sectionmvp',
  setup(props, { emit }) {
    const store = useStore()

    const state = reactive({
      ranking: computed(() => store.getters['rootMain/getRankingList'])
    })

    store.dispatch('rootMain/getRanking', localStorage.getItem('jwt'))
    .then(function (result) {
      console.log("dispatch getRanking")
      console.log(result.data)
      store.dispatch('rootMain/setRankingList', result.data)
    })
    .catch(function (err) {
      console.log("getRanking error")
    })

    return {
      ...toRefs(state)
    }
  }
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
