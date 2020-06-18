<template>
    <div id="list">
        <ul style="padding-left: 0px">
            <li v-for="item in users" :class="{ active: currentSession?item.name === currentSession.name:false}"
                v-on:click="changeCurrentSession(item)">
               <img class="avatar" :src="item.name" :alt="item.name">
                <el-badge :is-dot="isDot[user.name+'#'+item.name]"><p class="name">{{item.name}}</p></el-badge>
            </li>
        </ul>
    </div>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: 'list',
        data() {
            return {
                user:JSON.parse(window.localStorage.getItem("token")),
                users1:[]
            }
        },
        computed: mapState([
            'users',
            'isDot',
            'currentSession'
        ]),
        methods: {
            changeCurrentSession (currentSession) {
                this.$store.commit('changeCurrentSession', currentSession)
            },

        },
        /*created() {
            const _this=this
            //window.localStorage.getItem("token").id;
            const ID=JSON.parse(window.localStorage.getItem('token')).id;
            //console.log(users)
            _this.axios.get('http://localhost:8010/user/getAllUserExceptCurrentUser/'+ID).then(function(resp){
                _this.users1=resp.data
                /!* _this.tableData = resp.data.content
                 _this.pageSize = resp.data.size
                 _this.total = resp.data.totalElements*!/
            })
        }*/
    }
</script>

<style lang="scss" scoped>
    #list {
        li {
            padding: 16px 15px;
            border-bottom: 1px solid #292C33;
            cursor: pointer;
            list-style: none;

            &:hover {
                background-color: rgba(255, 255, 255, 0.03);
            }
        }

        li.active { /*注意这个是.不是冒号:*/
            background-color: rgba(255, 255, 255, 0.1);
        }

        .avatar {
            border-radius: 2px;
            width: 30px;
            height: 30px;
            vertical-align: middle;
        }

        .name {
            display: inline-block;
            margin-left: 15px;
            margin-top: 0px;
            margin-bottom: 0px;
        }
    }
</style>