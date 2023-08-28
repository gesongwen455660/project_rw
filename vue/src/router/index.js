import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PublicPage from '../views/PublicPage.vue';
import Login from '../views/Login.vue'
import OrderDemo from "@/views/OrderDemo";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'front',
    component: PublicPage
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/admin',
    name: 'admin',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/HomeView.vue')
  },
  {
    path: '/orderDemo',
    name: 'orderDemo',
    component: OrderDemo
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
