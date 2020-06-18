<!--
<template>
    <div id="app">
        <div class="sidebar">
            <card></card>
            <list></list>
        </div>
        <div class="main">
            <message></message>
            <userText></userText>
        </div>
    </div>
</template>

<script>

    import card from '../../components/chat/card'
    import list from '../../components/chat/list'
    import message from '../../components/chat/message'
    import userText from '../../components/chat/userText'

    export default {
        name: 'MessageChat',
        data () {
            return {

            }
        },
        mounted:function() {
            this.$store.dispatch('initData');
        },
        components:{
            card,
            list,
            message,
            userText
        }
    }
</script>

<style lang="scss" scoped>
    #app {
        margin: 20px auto;
        width: 100%;
        height: 600px;
        overflow: hidden;
        border-radius: 10px;
        .sidebar, .main {
            height: 100%;
        }
        .sidebar {
            float: left;
            color: #f4f4f4;
            background-color: #2e3238;
            width: 25%;
        }
        .main {
            position: relative;
            overflow: hidden;
            background-color: #eee;
            width: 75%;
        }
    }
</style>
-->

<template>
    <div class="roomStyle">
        <div><br>系统内部专用聊天室：<br/><br/>
        <textarea id="content" v-model="content" cols="90" rows="15" readonly="readonly"></textarea><br></div>

<div >
        <input type="textarea"
                  :autosize="{ minRows: 2, maxCors: 4}"
                  placeholder="请输入内容"
                  v-model="message" ></input>
        <el-button type="button" size="mini" @click="sendMsg()">发送消息</el-button>
        <br/><br/>
        用户：<input type="text" v-model="user">
        <el-button size="mini" @click="joinRoom()">加入群聊</el-button>
        <el-button size="mini" @click="exitRoom()">退出群聊</el-button></div>
    </div>
</template>
<script>
    export default {
        name: 'pageRoom',
        data() {
            return {
                url: 'ws://'+ window.location.host + '/Room/',
                ws: null,
                user: '',
                message: '',
                content: '',

            }
        },
        created(){
        },
        methods: {
            async joinRoom(){
                if(this.ws) {
                    alert("你已经再聊天室");
                    return;
                }
                let url = this.url;
                let username = this.user;
                this.ws = new WebSocket('ws://127.0.0.1:8018/Room/' + username);  // 后端的启动端口
                this.ws.onopen = this.webscoketonopen;
                this.ws.onmessage =  this.webscoketonmessage;
                //发生错误触发
                this.ws.onerror = function () {
                    console.log("连接错误")
                };
                //正常关闭触发
                this.ws.onclose = function () {
                    console.log("连接关闭");
                };
            },
            webscoketonopen(){
                console.log("与服务器成功建立连接");
            },
            webscoketonmessage(value){
                console.log(value);
                this.content += (value.data + '\r\n') ;
            },
            exitRoom(){
                this.closeWebSocket();
            },
            sendMsg(){
                if(!this.ws) {
                    alert('你已经掉线，请重新加入');
                    return;
                }
                if(this.ws.readyState === 1){
                    this.ws.send(this.message);
                    this.message = '';
                } else {
                    alert('发送失败');
                }
            },
            closeWebSocket(){
                if(this.ws) {
                    this.ws.close();
                    this.ws = null;
                }
            },
            talking(content) {
                console.log(content);
            }
        }
    }
</script>
<style>

    .roomStyle{
        text-align: center;
        background-color:#545c64;
        margin: 0 auto;
        border: 1px solid #000;
        width:70%;
        height:100%
    }
</style>