<template>
  <!-- main 으로 시작 -->
  <!-- <Main/> -->

  <!-- start 로 시작 -->
  <Main v-if="state.isLogin"/>
  <Start v-else/>
</template>

<script lang="ts">
import { reactive } from '@vue/reactivity'
import Main from './views/main/main.vue'
import Start from './views/start/start.vue'

export default {
  name: 'App',

  components: {
    Main,
    Start,
  },

  data: () => ({
    //
  }),
  setup(props,{ emit }){
    const state = reactive({
      isLogin : localStorage.getItem('jwt') == null ? false : true,
    })
    return { state }

  }
}
</script>
<style lang="scss">
Start {
  width: 100vw;
  height: 100vh;
}

@import 'node_modules/bootstrap/scss/bootstrap.scss';
</style>
