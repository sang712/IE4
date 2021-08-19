<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
      <li v-if="prev" class="page-item">
        <a class="page-link" href="#" aria-label="Previous" @click="paginationChanged(startPageIndex - 1)">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li v-for="index in ( endPageIndex-startPageIndex + 1 )" :key="index"
          v-bind:class="{active: (startPageIndex + index - 1 == $store.state.board.currentPageIndex)}" class="page-item">
        <a @click="paginationChanged(startPageIndex + index - 1)"
           class="page-link" href="#">{{ startPageIndex + index - 1 }}</a> <!-- href 는 그대로, 커서 모양 유지-->
      </li>
      <li v-if="next" class="page-item">
        <a class="page-link" href="#" aria-label="Next" @click="paginationChanged(endPageIndex + 1)">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>
<script>

import { useStore } from 'vuex'

export default {
  name: "sectionPagination",
  data() {
    return {};
  },
  computed: {
    pageCount: function(){
      const store = useStore()
      return store.getters['rootMain/getNewsBoardList'].totalPageCount
    },
    startPageIndex: function(){
      if( (store.state.newsboard.currentPageIndex % store.state.newsboard.pageLinkCount) == 0 ){ //10, 20...맨마지막
        return ((store.state.newsboard.currentPageIndex / store.state.newsboard.pageLinkCount)-1)*store.state.newsboard.pageLinkCount + 1
      }else{
        return  Math.floor(store.state.newsboard.currentPageIndex / store.state.newsboard.pageLinkCount)*store.state.newsboard.pageLinkCount + 1
      }
    },
    endPageIndex: function(){
      const store = useStore()
      // 아래 코드는 Error in render: "RangeError: Maximum call stack size exceeded" 발생, this.endPageIndex 계산 할 때, 자기 자신 값을 단순 비교만하더라도 에러 발생
      // if( this.endPageIndex < this.pageCount ) return this.pageCount;

      let ret = 0;
      if( (store.state.newsboard.currentPageIndex % store.state.newsboard.pageLinkCount) == 0 ){ //10, 20...맨마지막
        ret = ((store.state.newsboard.currentPageIndex / store.state.newsboard.pageLinkCount)-1)*store.state.newsboard.pageLinkCount + store.state.newsboard.pageLinkCount;
      }else{
        ret = Math.floor(store.state.newsboard.currentPageIndex / store.state.newsboard.pageLinkCount)*store.state.newsboard.pageLinkCount + store.state.newsboard.pageLinkCount;
      }
      // 위 오류나는 코드를 아래와 같이 비교해서 처리
      return ( ret > this.pageCount ) ? this.pageCount : ret;
    },
    prev: function(){
      const store = useStore()
      if( store.state.newsboard.currentPageIndex <= store.state.newsboard.pageLinkCount ){
        return false;
      }else{
        return true;
      }
    },
    next: function(){
      if( this.endPageIndex >=  this.pageCount){
        return false;
      }else{
        return true;
      }
    }
  },
  methods:{
    created(){
      const store = useStore()
    },
    // 부모에게 event 전달
    paginationChanged(pageIndex){
      setTimeout(function(){
        location.reload();
      });
      this.$emit('call-parent', pageIndex);
    }
  },

}
</script>
