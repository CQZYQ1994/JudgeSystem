<template>
   <div>
       <div  title="会议时间创建" >
        <!-- <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
             <el-form-item
                     prop="email"
                     label="邮箱"
                     :rules="[{ required: true, message: '请输入邮箱地址', trigger: 'blur' },
                     { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] } ]">
                 <el-input v-model="dynamicValidateForm.email"></el-input>
             </el-form-item>

             <el-form-item>
                 <el-button type="primary" @click="submitForm('dynamicValidateForm')">提交</el-button>
                 <el-button @click="addDomain">新增域名</el-button>
                 <el-button @click="resetForm('dynamicValidateForm')">重置</el-button>
             </el-form-item>
         </el-form>-->
        <el-form  style="width:60%"  :model="ruleForm"  ref="ruleForm" label-width="160px" class="demo-ruleForm">

            <el-form-item label="会议创建人">
                <el-input v-model="ruleForm.create_name" disabled="false"></el-input>
            </el-form-item>
            <el-form-item label="会议议题">
                <el-input v-model="ruleForm.topic" disabled="false"></el-input>
            </el-form-item>
            <el-form-item label="会议地点" >
                <el-input v-model="ruleForm.place" disabled="false"></el-input>
            </el-form-item>
            <el-form-item label="与会人员" >
                <el-input v-model="ruleForm.participants" disabled="false"></el-input>
            </el-form-item>
        </el-form>
           <el-form  style="width:60%"  :model="meetingTime"  ref="meetingTime" label-width="160px" class="demo-ruleForm2">
               <el-form-item
                    v-for="(item, index) in meetingTime"
                    :label="'会议时间' + ++index">
                   <!-- :key="item.key"-->
                    <!--:prop="'meetingTime.' + index + '.value'"
                    :rules="{  required: true, message: '请选择日期', trigger: 'change' }"-->
                   <el-date-picker
                           v-model="item.meetingTime"
                           type="datetime"
                           placeholder="选择日期时间"
                           format="yyyy年MM月dd日 HH:mm:ss"
                           value-format="yyyy-MM-dd HH:mm:ss">
                   </el-date-picker>
                  <el-button @click.prevent="removeDomain(item)">删除</el-button>
            </el-form-item>
            <!--  <el-form-item label="会议任务创建时间" >
                  <el-date-picker
                          v-model="ruleForm.create_time"
                          type="datetime"
                          placeholder="选择日期时间"
                          format="yyyy年MM月dd日 HH:mm:ss"
                          value-format="yyyy-MM-dd HH:mm:ss">
                  </el-date-picker>
              </el-form-item>

              <el-form-item label="会议附件" >
                  <el-input v-model="ruleForm.document_path"></el-input>
              </el-form-item>-->

            <el-form-item>
                <el-button @click="addDomain">新增时间</el-button>
                <el-button type="primary" @click="submitForm('meetingTime')">提交</el-button>
                <el-button @click="backTo">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
   </div>
</template>

<script>
    export default {
        name: "MeetTimeAdd",
        data(){
            return{
                meetingTime:[{
                    meetingInfoId:this.$route.query.id,
                    vote:0,
                    meetingTime:''
                }],
                ruleForm: {
                    id:'',
                    create_name: '',
                    topic:'',
                    place:'',
                    participants:'',
                    create_time:'',
                    meet_status:'',
                    meetingTime:[{
                        id:'',
                        meetingInfoId:'',
                        vote:'',
                        meetTime:''
                    }],
                },
               /* rules: {
                    name: [
                        { required: true, message: '请输入申请人姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    position: [
                        { required: true, message: '请输入申请职位', trigger: 'blur' },
                        { min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur' }
                    ],
                    review_status: [
                        { required: true, message: '请输入评审状态', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                    ],
                    start_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    end_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    review_opinion: [
                        { required: true, message: '请输入评审意见', trigger: 'blur' },
                        { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
                    ],
                    review_result: [
                        { required: true, message: '请输入评审意见', trigger: 'blur' },
                        { min: 1, max: 300, message: '长度在 1 到 300 个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    document_path: [
                        { required: true, message: '请上传附件', trigger: 'blur' }
                    ]
                },*/
            }
        },
        methods:{
            removeDomain(item) {
                let _this=this;
                let index = this.meetingTime.indexOf(item)
                if (index !== -1) {
                    this.meetingTime.splice(index, 1)
                }
            },
            addDomain() {
                let _this=this;
                this.meetingTime.push({
                    meetingInfoId:_this.$route.query.id,
                    vote:0,
                    meetTime:'',
                });
            },
            submitForm(formName) {
                const  _this=this
               /* this.$refs[formName].validate((valid) => {
                    if (valid) {*/
               let url='http://localhost:8012/meetTime/addMeetingTime';
                        _this.axios.post(url,_this.meetingTime).then(function (resp) {
                            _this.$alert(_this.ruleForm.topic+'会议时间创建成功', 'OK', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push('/MeetManage')
                                    /* console.log(resp)*/
                                }
                            });

                        })
                   /* } else {
                        console.log('error submit!!');
                        return false;
                    }*/
               // });
            },
            backTo() {
                this.$router.push('/MeetManage');
            },
        },
        created() {
            const _this=this
            let url='http://localhost:8012/meetInfo/findById/'+ _this.$route.query.id;
            _this.axios.get(url).then(function(resp) {
                _this.ruleForm=resp.data
            })

        }
    }
</script>

<style scoped>

</style>
