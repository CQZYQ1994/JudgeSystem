import Vue from 'vue'
import axios from './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import {initMenu} from './utils/menus'
//将token加入接口请求头

//axios.defaults.baseURL = 'http://localhost:8010/user/'
/*   axios.interceptors.request.use(config => {
    console.log(config)
    // config.headers.Authorization=window.localStorage.getItem('token')
    return config
})*/

Vue.config.productionTip = false





//前端路由拦截判断
router.beforeEach((to, from, next )=> {
  if (to.path === '/login'||to.path==='/SignIn') {
    return next();
  }
  const usertoken = window.localStorage.getItem('token');
  if (!usertoken){return next('/login');}
  else {
    initMenu(router,store);
    next();
  }
})
/*router.beforeEach((to, from, next) => {
      if (to.meta.requireAuth) {
        if (store.state.user.account) {
          next()
        } else {
          next({
            path: '/login',
            query: {redirect: to.fullPath}
          })
        }
      } else {
        next()
      }
    }
)*/

/*new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})*/
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')