<template>
  <!-- main, start, conference 로 시작 -->
  <router-view></router-view>
  
  <!-- <Main v-if="state.isLogin"/>
  <Start v-else/> -->
</template>

<script>
import { reactive } from '@vue/reactivity'
import { useRouter } from 'vue-router'

export default {
  name: 'App',

  data: () => ({
    //
  }),
  setup(props,{ emit }){
    const state = reactive({
      isLogin : localStorage.getItem('jwt') == null ? false : true,
    })

    const router = useRouter()

    watch(isLogin, (val, oldVal) => {
      if (val) router.push({ name: main })
      else router.push({ name: start })
    })

    return { state }

  }
}
</script>
<style>

Start {
  width: 100vw;
  height: 100vh;
}

</style>
