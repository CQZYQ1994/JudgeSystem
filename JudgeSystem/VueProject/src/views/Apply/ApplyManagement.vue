<template>
    <div>
        <el-table
                :data="tableData1"
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
                    label="申请人姓名"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="apply_position"
                    label="申请职位"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="联系邮箱"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="time1"
                    label="就学时间"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="school1"
                    label="就读学校"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="education_level"
                    label="攻读学位"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="time2"
                    label="就学时间"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="school2"
                    label="就读学校"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="education_level2"
                    label="攻读学位"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="申请创建时间"
                    width="120">
            </el-table-column>

            <el-table-column
                    prop="document_path"
                    label="简历附件"
                    width="100">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="editClick(scope.row)" icon="el-icon-edit" type="primary" circle size="small" ></el-button>
                    <el-button @click="deleteJob(scope.row)" icon="el-icon-delete" type="danger"circle size="small"></el-button>
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
                    path:'/ApplyUpdate',
                    query:{
                        id:row.id
                    }
                })
            },
            deleteJob(row){
                const _this=this
                axios.delete('http://localhost:8015/apply/deleteById/'+row.id).then(function(resp){
                    _this.$alert(row.id+'删除成功!', 'OK', {
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
                tableData1: []
            }
        },
        created() {
            const _this=this
            axios.get('http://localhost:8015/apply/findAll/0/10').then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>