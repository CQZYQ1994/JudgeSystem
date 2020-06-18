<template>
    <div id="userText">
        <textarea placeholder="按 Ctrl + Enter 发送" v-model="content" v-on:keyup="addMessage" width="50%"></textarea>
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: 'userText',
        data() {
            return {
                content: '',
                currentUser:JSON.parse(window.localStorage.getItem('token')),
            }
        },
        computed: mapState([
            'sessions',
            'currentSession'
        ]),
        methods: {
            addMessage(e) {
                //e.keyCode === 13 &&
                if (e.ctrlKey && e.keyCode === 13 && this.content.length) {
                    let msgObj = new Object();
                    msgObj.to = this.currentSession.name;
                    msgObj.content = this.content;
                    msgObj.from=this.currentUser.username;
                    msgObj.fromNickname=this.currentUser.name;
                    msgObj.date=new Date();
                   // this.$store.state.stompClient.send('http://localhost:8016/ws/chat', {}, JSON.stringify(msgObj));
                    this.$store.state.stompClient.send('http://localhost:8016/hello', {}, JSON.stringify(msgObj));
                    this.$store.commit('addMessage', msgObj);
                    this.content = '';
                }
            }
        }
    }
</script>

<style lang="scss" scoped>
    #uesrtext {
        position: absolute;
        bottom: 0;
        right: 0;
        width: 100%;
        height: 60%;
        border-top: solid 1px #DDD;

        > textarea {
            padding: 10px;
            width: 100%;
            height: 100%;
            border: none;
            outline: none;
        }
    }
</style>