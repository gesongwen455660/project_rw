import Vue from 'vue';
import Router from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import PublicPage from '@/views/PublicPage.vue';
import Login from "@/views/Login.vue";
import OrderDemo from "@/views/OrderDemo";
import Supplier from "@/views/Supplier";
Vue.use(Router);

const router = new Router({
    routes: [
        {
            path: '/',
            redirect: '/public' // 设置默认路由重定向到 '/public'
        },
        {
            path: '/public',
            name: 'public',
            component: PublicPage
        },
        {
            path: '/admin',
            name: 'admin',
            component: HomeView
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/orderDemo',
            name: 'orderDemo',
            component: OrderDemo
        },
        {
            path: '/supplier',
            name: 'supplier',
            component: Supplier
        },
    ]
});

export default router;