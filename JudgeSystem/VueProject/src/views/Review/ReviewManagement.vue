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
                    prop="applyer"
                    label="申请人姓名"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="apply_position"
                    label="申请职位"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="review_status"
                    label="评审状态"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="start_time"
                    label="建议评审开始时间"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="end_time"
                    label="建议评审截止时间"
                    width="140">
            </el-table-column>
            <el-table-column
                    prop="review_opinion"
                    label="评审意见"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="review_result"
                    label="评审结果"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="create_time"
                    label="评审创建时间"
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
                    path:'/ReviewUpdate',
                    query:{
                        id:row.id
                    }
                })
            },
            deleteJob(row){
                const _this=this
                axios.delete('http://localhost:8013/judge/deleteById/'+row.id).then(function(resp){
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
            axios.get('http://localhost:8013/judge/findAll/0/10').then(function(resp){
                _this.tableData1=resp.data
            })
        }
    }
</script>