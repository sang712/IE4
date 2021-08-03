import { createStore } from "vuex";
import root from '@/views/start/store';
import rootMain from '@/views/main/store';

export default createStore({
  modules: { root, rootMain }
});
