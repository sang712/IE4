<template>
  <!-- <div class="time-table" style="{backgroundImage : `url(${{state.classInfo.timetable}})`}"> -->
  <div class="time-table">
    <!-- <img src="require(`../../../../`${state.timetableUrl}`)" alt="이미지..."> -->
    <!-- <img :src="getImg()" alt="이미지..2."> -->
    <!-- <img src="/timetable/450f82b7-49d7-4ddb-8680-edc0f5e8e1dc.jpg" alt="이미지..2."> -->
    <img :src="getImg()" alt="이미지..2.">
  </div>
</template>

<script>
import { reactive, computed} from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'section-schedule',
  setup(props, { emit }) {
    const store = useStore()

    const state = reactive({
      timetableUrl: computed(() => store.getters['rootMain/getClassInfo']).timetable,
      // realImg: computed(() => "../../../../" + timetableUrl)
    })

    const getImg = () => {
      // console.log('../../../../' + state.timetableUrl)
      console.log(store.getters['rootMain/getClassInfo'].timetable)
      // return require(store.getters['rootMain/getClassInfo'].timetable)
      return store.getters['rootMain/getClassInfo'].timetable
    }

    return {
      getImg, state
    }

  }
}
</script>

<style>
/* .time-table {
  background-image: url('../../../assets/images/time-table.png');
} */
</style>
