<template>
    <div>
    <el-table
            :data="tableData"
            border
            style="width: 100%">
        <el-table-column
                fixed
                prop="id"
                label="编号"
                width="50">
        </el-table-column>
        <el-table-column
                prop="name"
                label="姓名"
                width="80">
        </el-table-column>
        <el-table-column
                prop="department"
                label="单位"
                width="200">
        </el-table-column>
        <el-table-column
                prop="position"
                label="职位"
                width="80">
        </el-table-column>
        <el-table-column
                prop="username"
                label="账号"
                width="120">
        </el-table-column>
        <el-table-column
                prop="password"
                label="密码"
                width="120">
        </el-table-column>
        <el-table-column
                prop="email"
                label="邮箱"
                width="150">
        </el-table-column>
        <el-table-column
                prop="role.role_name"
                label="角色权限"
                width="100">
        </el-table-column>
        <el-table-column
                fixed="right"
                label="操作"
                width="100">
            <template slot-scope="scope">
                <el-button @click="editClick(scope.row)" icon="el-icon-edit" type="primary" circle size="small" ></el-button>
                <el-button @click="deleteUser(scope.row)" icon="el-icon-delete" type="danger"circle size="small"></el-button>
            </template>
        </el-table-column>
    </el-table>
        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="10"
                :total="40"
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    import axios from  'axios'
    export default {
        methods: {
            editClick(row)  {
                this.$router.push({
                    path:'/UserUpdate',
                    query:{
                        id:row.id
                    }
                })
            },
            deleteUser(row){
                const _this=this

                axios.delete('http://localhost:8010/user/deleteById/'+row.id).then(function(resp){

                    _this.$alert(row.name+'删除成功!', 'OK', {
                        confirmButtonText: '确定',
                        callback: action => {
                           window.location.reload()

                        }
                    });
                })

            },
          /*  page(currentPage){
                const _this = this
                axios.get('http://localhost:8010/user/findAll/'+(currentPage-1)+'/6').then(function(resp){
                    console.log(resp)
                    _this.tableData = resp.data.content
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements
                })
            }*/
        },

        data() {
            return {
                tableData: []
            }
        },
        created() {
            const _this=this
            axios.get('http://localhost:8010/user/findAll/0/10').then(function(resp){
                _this.tableData=resp.data
               /* _this.tableData = resp.data.content
                _this.pageSize = resp.data.size
                _this.total = resp.data.totalElements*/
            })
        }
    }
</script>