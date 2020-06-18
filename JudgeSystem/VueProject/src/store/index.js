import Vue from 'vue'
import Vuex from 'vuex'
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'
import axios from 'axios'
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    routes:[],
    sessions:{},
    users:[],
    tokenKey:'',
    reviewItem:[],
    meetTimeItem:[],
    currentSession:null,
    currentUser:JSON.parse(window.localStorage.getItem('token')),
    token: {
      username: window.localStorage.getItem('token' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('token' || '[]')).username,
      id:window.localStorage.getItem('token' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('token' || '[]')).id
    },
    filterKey:'',
    /*msgList:[],
    isDotMap:new Map(),
    currentFriend:{},*/
    stompClient:null,
    //nfDot:false,
    isDot:{}
  },
  mutations: {
    /*INIT_CURRENTUSER(state,user){
      state.currentUser=user;
    },*/
    login (state, token) {
      state.token = token
      window.localStorage.setItem('token', JSON.stringify(token))
    },
    initMenu(state,menus){
      state.routes=menus;
    },
    initTokenKey(state,tokenKey){
      state.tokenKey=tokenKey;
    },
    initReviewItem(state,data){
      state.reviewItem=data;
    },
    initMeetTimeItem(state,data){
      state.meetTimeItem=data;
    },
    initRoutes(state, data) {
      state.routes = data;
    },
   /* changeCurrentSession(state, currentSession) {
      Vue.set(state.isDot, state.currentUser.name + '#' + currentSession.name, false);
      state.currentSession = currentSession;
    },
    addMessage(state, msg) {
      let mss = state.sessions[state.currentUser.name + '#' + msg.to];
      if (!mss) {
        // state.sessions[state.currentHr.username+'#'+msg.to] = [];
        Vue.set(state.sessions, state.currentUser.name + '#' + msg.to, []);
      }
      state.sessions[state.currentUser.name + '#' + msg.to].push({
        content: msg.content,
        date: new Date(),
        self: !msg.notSelf
      })
    },
    initCurrentUser(state,currentUser){
      state.currentUser=currentUser;
      window.localStorage.setItem('currentUser', JSON.stringify(currentUser))
    },*/
   /* toggleNFDot(state,newValue){
      state.nfDot=newValue;
    },
    updateMsgList(state,newMsgList){
      state.msgList=newMsgList;
    },
    updateCurrentFriend(state,newFriend){
      state.currentFriend=newFriend;
    },
    addValue2DotMap(state,key){
      state.isDotMap.set(key,"您有未读消息");
    },
    removeValueDotMap(state,key){
      state.isDotMap.delete(key);
    },*/

   /* INIT_DATA(state) {
      //浏览器本地的历史聊天记录可以在这里完成
      let data = window.localStorage.getItem('vue-chat-session');
      if (data) {
        state.sessions = JSON.parse(data);
      }
    },
    INIT_USER(state, data) {
      state.users = data;
     // window.localStorage.setItem('data', JSON.stringify(user))

    }*/
  },

  actions: {
  /*  connect(context){
      context.state.stompClient = Stomp.over(new SockJS("http://localhost:8016/ws/endpointChat"));
      context.state.stompClient.connect({}, frame=> {
        context.state.stompClient.subscribe("/user/queue/chat", message=> {
        let msg = JSON.parse(message.body);
          var oldMsg = window.localStorage.getItem(context.state.token.username + "#" + msg.from);
          if (oldMsg == null) {
            oldMsg = [];
            oldMsg.push(msg);
            window.localStorage.setItem(context.state.token.username + "#" + msg.from, JSON.stringify(oldMsg))
          } else {
            var oldMsgJson = JSON.parse(oldMsg);
            oldMsgJson.push(msg);
            window.localStorage.setItem(context.state.token.username + "#" + msg.from, JSON.stringify(oldMsgJson))
          }
          if (msg.from !==context.state.currentFriend.username) {
            context.commit("addValue2DotMap", "isDot#" + context.state.token.username + "#" + msg.from);
          }
          //更新msgList
          var oldMsg2 = window.localStorage.getItem(context.state.token.username + "#" + context.state.currentFriend.username);
          if (oldMsg2 == null) {
            context.commit('updateMsgList', []);
          } else {
            context.commit('updateMsgList', JSON.parse(oldMsg2));
          }
        });
        context.state.stompClient.subscribe("/topic/nf",message=>{

        });
      }, failedMsg=> {

      });
    },*/
   /* connect(context) {
      context.state.stompClient = Stomp.over(new SockJS('http://localhost:8016/ws/endpointChat'));
      context.state.stompClient.connect({}, success => {
        context.state.stompClient.subscribe('http://localhost:8016/user/queue/chat', msg => {
          let receiveMsg = JSON.parse(msg.body);
          if (!context.state.currentSession || receiveMsg.from !== context.state.currentSession.name) {
            Notification.info({
              title: '【' + receiveMsg.fromNickname + '】发来一条消息',
              message: receiveMsg.content.length > 10 ? receiveMsg.content.substr(0, 10) : receiveMsg.content,
              position: 'bottom-right'
            })
            Vue.set(context.state.isDot, context.state.currentUser.name + '#' + receiveMsg.from, true);
          }
          receiveMsg.notSelf = true;
          receiveMsg.to = receiveMsg.from;
          context.commit('addMessage', receiveMsg);
        });
        context.state.stompClient.subscribe('http://localhost:8016/topic/greetings', msg => {
            console.log(JSON.parse(msg));
        });
      }, error => {

      })
    },*/
   /* initData(context) {
      context.commit('INIT_DATA');
      const Http=new Vue;
      const ID=JSON.parse(window.localStorage.getItem('token')).id;
      let url='http://localhost:8010/user/getAllUserExceptCurrentUser/'+ID;

      Http.axios.get(url).then(function (resp) {
        if (resp.status===200){
          //console.log(resp)
          context.commit('INIT_USER',resp.data);
        }
      })
    },*/
   /* store:watch(function (state) {
      return state.sessions
    }, function (val) {
      localStorage.setItem('vue-chat-session', JSON.stringify(val));
    }, {
      deep: true
    }),*/

  },

  modules: {
  }
})
