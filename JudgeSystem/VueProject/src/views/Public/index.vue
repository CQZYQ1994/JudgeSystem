
<template>
    <div id="app" >
        <el-container style="height: 580px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color:rgb(48,65,86)">
              <!--  <el-menu router :default-openeds="['2','3']" background-color="#545c64"
                         text-color="#fff"
                         active-text-color="#ffd04b">
                    <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''" v-if="item.show">
                        <template slot="title"><i class="el-icon-menu"></i>{{item.name}}</template>
                        <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path" v-if="item2.show"
                                      :class="$route.path==item2.path?'is-active':''">{{item2.name}}</el-menu-item>
                    </el-submenu>
                </el-menu>-->
                <el-menu router unique-opened  background-color="rgb(48,65,86)">
                <el-submenu :index="index+''" v-for="(item,index) in routes" v-if="item.show" :key="index" >
                    <template slot="title">
                        <i style="color: #409eff;margin-right: 5px" :class="item.iconCls"></i>
                        <span style="color: #DDDDDD">{{item.name}}</span>
                    </template>
                    <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" v-if="child.show" :key="indexj" :class="child.iconCls" style="color: #DDDDDD">
                        {{child.name}}
                    </el-menu-item>
                </el-submenu>
            </el-menu>
            </el-aside>

            <el-container >
                <el-header style="text-align: right; font-size: 12px;background-color:rgb(48,65,86)">

                    <span style="color: #DDDDDD">用户：{{token.name}}</span>

                    <el-button @click="logOut()"  type="info"  size="mini" >退出</el-button>

                </el-header>

                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!=='/'">
                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <div class="homeWelcome" v-if="this.$router.currentRoute.path==='/'">
                        欢迎来到评审管理系统！
                    </div><br>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {

        name: "home",
        data() {
            return {
                token:[],
                style:{
                    color:"white"
                }
            }
        },
        computed: {
            routes() {
                return this.$store.state.routes;
            }

        },
        methods:{

            logOut(){
                window.localStorage.clear()
                this.$store.commit('initRoutes',[])
                this.$router.push('/login')
            }

        },
        created(){
             this.token =JSON.parse( window.localStorage.getItem('token'))
        }

    }

</script>
<style scoped>
    .el-header {
        background-color: #545c64;
        color: #333;
        line-height: 60px;
    }

    .el-menu {
        color:white;
        el-menu-item: 60px !important;
    }
</style>
