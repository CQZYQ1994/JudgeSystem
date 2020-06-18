import Vue from 'vue'
import VueRouter from 'vue-router'
import SearchUser from '../views/User/UserManage'
import AddUser from '../views/User/UserAdd'
import JobManagement from '../views/Task/TaskManage'
import JobAdd from '../views/Task/TaskAdd'
import ApplyManagement from '../views/Apply/ApplyManage'
import ApplyAdd from '../views/Apply/ApplyAdd'
import index from '../views/Public/index'
import UserUpdate from '../views/User/UserUpdate'
import JobUpdate from '../views/Task/TaskUpdate'
import ReviewManagement from '../views/Review/ReviewManage'
import ReviewAdd from '../views/Review/ReviewAdd'
import MeetManagement from '../views/Meetting/MeetManage'
import MeetAdd from '../views/Meetting/MeetAdd'
import ApplyUpdate from '../views/Apply/ApplyUpdate'
import ReviewUpdate from '../views/Review/ReviewUpdate'
import MeetUpdate from '../views/Meetting/MeetUpdate'
import login from '../views/Public/login'
import SignIn from '../views/Public/SignIn'
import SendEmail from "../views/Message/MessageEmail";
import {initMenu} from "../utils/menus";

Vue.use(VueRouter)


const Router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [

    {
      path:'/login',
      name:"登录界面",
      component:login,
      show:false
    },
    {
      path:'/SignIn',
      name:"注册界面",
      component:SignIn,
      show:false
    },{
      path: '/',
      name: 'index',
      component: index,
      show: false,
    },

  /*  {
      path:"/",
      name:"用户管理",
      component:index,
      show:true,
      redirect:"/SearchUser",
      meta:{
        requireAuth: true
      },
      children:[
        {
          path:"/SearchUser",
          name:"查询用户",
          show:true,
          component:SearchUser
        },
        {
          path:"/AddUser",
          name:"新增用户",
          show:true,
          component:AddUser
        },
        {
          path:'/UserUpdate',
          component:UserUpdate,
          show:false
        }
      ]
    },
    {
      path:"/nevigation2",
      name:"工作管理",
      component:index,
      show:true,
      children: [
        {
          path:"/JobManagement",
          name:"查询工作",
          show:true,
          component:JobManagement
        },
        {
          path:"/JobAdd",
          name:"创建工作",
          show:true,
          component:JobAdd
        },
        {
          path:'/JobUpdate',
          component:JobUpdate,
          show:false
        }
      ]
    },
    {
      path:"/nevigation3",
      name:"申请管理",
      component:index,
      show:true,
      children:[
        {
          path:"/ApplyManagement",
          name:"查询申请",
          show:true,
          component:ApplyManagement
        },
        {
          path:"/ApplyAdd",
          name:"新增申请",
          show:true,
          component:ApplyAdd
        },
        {
          path:'/ApplyUpdate',
          component:ApplyUpdate,
          show:false
        }
      ]
    },
    {
      path:"/nevigation4",
      name:"评审管理",
      component:index,
      show:true,
      children:[
        {
          path:"/ReviewManagement",
          name:"查询评审",
          show:true,
          component:ReviewManagement
        },
        {
          path:"/ReviewAdd",
          name:"新增评审",
          show:true,
          component:ReviewAdd
        },
        {
          path:"/ReviewAdd",
          name:"指定评审老师",
          show:true,
          component:ReviewAdd
        },
        {
          path:'/ReviewUpdate',
          component:ReviewUpdate,
          show:false
        }
      ]
    },
    {
      path:"/nevigation5",
      name:"会议管理",
      component:index,
      show:true,
      children:[
        {
          path:"/MeetManagement",
          name:"查询会议",
          show:true,
          component:MeetManagement
        },
        {
          path:"/MeetAdd",
          name:"新增会议",
          show:true,
          component:MeetAdd
        },
        {
          path:'/MeetUpdate',
          component:MeetUpdate,
          show:false
        }
      ]
    },
    {
      path:'/SendEmail',
      name:"邮件管理",
      component:index,
      show:true,
      children:[
        {
          path:'/SendEmail',
          name:"邮件发送",
          component:SendEmail,
          show:true
        }
      ]
    }*/


  ]
})
/*  router.beforeEach((to, from, next )=> {
  if (to.path === '/login'||to.path==='/SignIn') {
    return next();
  }
  const usertoken = window.localStorage.getItem('token');
  if (!usertoken){return next('/login');}
  else {
    initMenu(router,store);
    next();
  }
})*/


export default Router
