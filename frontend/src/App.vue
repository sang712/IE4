<template>
  <!-- main, start, conference 로 시작 -->
  <router-view></router-view>
</template>

<script>
import { reactive, watch, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'

export default {
  name: 'App',

  data: () => ({

  }),
  setup(props,{ emit }){
    const state = reactive({
      isLogin : localStorage.getItem('jwt') == null ? false : true,
    })

    const router = useRouter()

    onBeforeMount(() =>{
      console.log(state.isLogin)
      if (state.isLogin) router.push('/main')
      else router.push('/')
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
