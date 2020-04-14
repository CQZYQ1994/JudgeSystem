import Vue from 'vue';
import Router from 'vue-router';
import Login from '../views/login/';

//所有权限通用路由表
//如首页和登录页和一些不用权限的公用页面
export const constantRouterMap = [
    { path: '/login', component: Login },
    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        name: '首页',
        children: [{ path: 'dashboard', component: dashboard }]
    },
]
//异步挂载的路由
//动态需要根据权限加载的路由表
export const asyncRouterMap = [
    {
        path: '/permission',
        component: Layout,
        name: '权限测试',
        meta: { role: ['admin','super_editor'] }, //页面需要的权限
        children: [
            {
                path: 'index',
                component: Permission,
                name: '权限测试页',
                meta: { role: ['admin','super_editor'] }  //页面需要的权限
            }]
    },
    { path: '*', redirect: '/404', hidden: true }
];



