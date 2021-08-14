<template>
  <div class="section-note row" style="align-items: center">
    <!-- <h1 class="note-title">학사일정</h1> -->
    <div class="note-content">
      <!-- <span v-for="(item, index) in schedule" :key="index">
        <h4 v-if="flag(item)">{{item.EVENT_NM}}</h4>
      </span> -->

      <!-- <div class="day">월 | &nbsp;zzzzzz</div>
      <div class="day">화 | &nbsp;zzz</div>
      <div class="day">수 | &nbsp;zzzzzzzzzz</div>
      <div class="day">목 | &nbsp;zzzzzzzzzzzzz</div>
      <div class="day">금 | &nbsp;zz</div> -->

      <div class="day">월 | &nbsp;{{ schedule[0].EVENT_NM }}</div>
      <div class="day">화 | &nbsp;{{ schedule[1].EVENT_NM }}</div>
      <div class="day">수 | &nbsp;{{ schedule[2].EVENT_NM }}</div>
      <div class="day">목 | &nbsp;{{ schedule[3].EVENT_NM }}</div>
      <div class="day">금 | &nbsp;{{ schedule[4].EVENT_NM }}</div>

    </div>
  </div>
</template>

<script>
import { reactive, computed, toRefs } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'section-note',
  setup(props, { emit }) {
    const store = useStore()

    const state = reactive({
      schedule: computed(() => store.getters['rootMain/getSchoolSchedule'].list),
      year: localStorage.getItem('classId')[0],
    })

    // 학년에 맞는 학사일정 노출
    const flag = (item) => {
      if(state.year == 1 && item.ONE_GRADE_EVENT_YN =='Y') return true;
      else if(state.year == 2 && TW_GRADE_EVENT_YN =='Y') return true;
      else if(state.year == 3 && THREE_GRADE_EVENT_YN =='Y') return true;
      else if(state.year == 4 && FR_GRADE_EVENT_YN =='Y') return true;
      else if(state.year == 5 && FIV_GRADE_EVENT_YN =='Y') return true;
      else if(state.year == 6 && SIX_GRADE_EVENT_YN =='Y') return true;
      else return false;
    }

    store.dispatch('rootMain/requestSchoolSchedule')
    .then(function (result) {
      console.log(result.data.SchoolSchedule[1].row);
      store.dispatch('rootMain/setSchoolSchedule', result.data.SchoolSchedule[1].row)
    })
    .catch(function (err) {
      console.log("requestSchoolSchedule error")
    })

    const getDate = () => {
      const today = new Date();
      return today.getDay();
    }


    return {
      getDate, flag, ...toRefs(state)
    }
  }
}
</script>

<style>
.section-note {
  background-image: url('../../../assets/images/note.png');
}
.note-title {
  color: white;
  margin-top: 10px;
  margin-left: 20px;
}
.note-content {
  color: white;
  font-size: 21px;
  text-align: center;
  /* margin: 15px 5px; */
}
.day {
  margin-bottom: 10px;
}
</style>
