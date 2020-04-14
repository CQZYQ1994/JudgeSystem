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
                    prop="department"
                    label="招聘单位"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="position"
                    label="招聘职位"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="number"
                    label="招聘人数"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="requirement"
                    label="招聘需求"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="treatment"
                    label="工作待遇"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="start_time"
                    label="招聘开始时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="end_time"
                    label="招聘截止时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="招聘创建时间"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="create_name"
                    label="创建人"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="email"
                    label="联系邮箱"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="document_path"
                    label="附件资料"
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
                    path:'/JobUpdate',
                    query:{
                        id:row.id
                    }
                })
            },
            deleteJob(row){
                const _this=this
                axios.delete('http://localhost:8014/job/deleteById/'+row.id).then(function(resp){
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
            axios.get('http://localhost:8014/job/findAll/0/10').then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>