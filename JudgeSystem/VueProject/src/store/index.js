import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    routes:[],
    token: {
      username: window.localStorage.getItem('token' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('token' || '[]')).username,
      id:window.localStorage.getItem('token' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('token' || '[]')).id
    }
  },
  mutations: {
    login (state, token) {
      state.token = token
      window.localStorage.setItem('token', JSON.stringify(token))
    },
    initMenu(state,menus){
      state.routes=menus;
    },
    initRoutes(state, data) {
      state.routes = data;
    }
  },
  actions: {
  },
  modules: {
  }
})
