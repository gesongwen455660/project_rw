import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import 'D:/project/vue/src/assets/globe.css'
import request from "@/utils/request";
Vue.config.productionTip = false

Vue.use(ElementUI);

Vue.prototype.request = request
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
