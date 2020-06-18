<template>
   <div>
       <el-card>
       <div>
          <!-- <el-select v-model="value" @change="selectMeeting(value)" placeholder="请选择会议">
       &lt;!&ndash; <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
        </el-option>&ndash;&gt;
           <el-option label="会议1" value="1"></el-option>
           <el-option label="会议2" value="2"></el-option>
           <el-option label="会议5" value="5"></el-option>

    </el-select>-->
           <div align="center"><h4>评审投票</h4>
           <table id="main" >
           <div v-for="(item,index) in meetTimeItem">
               <tr>
                   <td>
                       <input type="radio" name="option"/>会议备选时间:{{item.meetingTime}}
                   </td>
               </tr>
               <tr>
                   <td style="width:550px">
                       <div :id="index"  style="background-color:#acd6ff; width:0; height:20px; border-width:0px;">
                       </div>
                   </td>
                   <td>
                       <label :id="label+index">{{item.vote}}</label>票
                   </td>
               </tr>


           </div>
               <tr>
                   <td><p>
                       <el-button type="submit"  @click="vote">确认投票</el-button></p>
                   </td>
               </tr>
           </table>
           </div>
       </div>
       </el-card>
   </div>
</template>

<script>
    export default {
        name: "MeetTimeVote",
        data() {
            return {

                label: 'label',
                value:'',
                reviewVote: {
                    id: '',
                    vote: '',
                },
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }, {
                    value: '选项2',
                    label: '双皮奶'
                }, {
                    value: '选项3',
                    label: '蚵仔煎'
                }, {
                    value: '选项4',
                    label: '龙须面'
                }, {
                    value: '选项5',
                    label: '北京烤鸭'
                }],
                /* ruleForm: {
                    create_name: '',
                    topic:'',
                    place:'',
                    participants:'',
                    create_time:'',
                    document_path:''
                },
                rules: {
                    create_name: [
                        { required: true, message: '请输入创建人姓名', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5个字符', trigger: 'blur' }
                    ],
                    topic: [
                        { required: true, message: '请输入会议议题', trigger: 'blur' },
                        { min: 2, max:50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
                    ],
                    place: [
                        { required: true, message: '请输入会议地点', trigger: 'blur' },
                        { min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur' }
                    ],
                    participants: [
                        { required: true, message: '请输入与会人员', trigger: 'blur' },
                        { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
                    ],
                    create_time: [
                        {  required: true, message: '请选择日期', trigger: 'change' }
                    ],

                }*/
            }
        },
        computed: {
            meetTimeItem(){
                return this.$store.state.meetTimeItem;
            }

        },
        methods: {
            selectMeeting(value){
                let _this=this
                if (value==='1') {
                    _this.findMeetTimeByInfoId(value);
                }else if (value==='2'){
                    _this.findMeetTimeByInfoId(value);
                } else if (value==='5'){
                    _this.findMeetTimeByInfoId(value);
                }
            },
            vote(){	//函数vote，当点击确认投票的时候，调用vote方法
                //for循环的条件是，所有投票选项的个数。
                let list=this.$store.state.meetTimeItem;
                let _this=this;
                for(let i = 0; i < document.getElementsByName("option").length; i++){
                    //查找到是哪个投票选项被选中
                    if(document.getElementsByName("option")[i].checked === true){
                        let width = document.getElementById(i).style.width;	//获取到当前选项的宽度。
                        width = parseInt(width);//将宽度转化为int型，因为获取到的width的单位是px
                        width += 3;//改变width的值，这里就是定义每次投票的进度条的增速
                        document.getElementById(i).style.width = width+"px";//修改原div的宽度
                        let label = "label"+i;//lable标签里面写的是当前的投票数目。
                        let num = document.getElementById(label).innerText;//获取到当前的票数
                        document.getElementById(label).innerText = ++num;//票数加1，并修改原值
                        _this.voteResult(list[i].id,document.getElementById(label).innerText)

                    }
                }
            },
            voteResult(id,vote){
                //let reviewVote = new Object();
                /*let _this=this;*/
                this.reviewVote.vote=vote;
                this.reviewVote.id=id;
                console.log(this.reviewVote);
                let url='http://localhost:8012/meetTime/updateVote';
                this.axios.put(url,this.reviewVote).then(function (resp) {

                })


            },
            findMeetTimeByInfoId(value){
                const _this=this
                let url='http://localhost:8012/meetTime/findByInfoId/'+value;
                _this.axios.get(url).then(function(resp) {
                    if (resp.status===200) {
                        //console.log(resp);
                        _this.$store.commit('initMeetTimeItem', resp.data);
                    }
                })
            }

        },
        created() {
            const _this=this
            let url='http://localhost:8012/meetTime/findCurrentMeetTime';
            _this.axios.get(url).then(function(resp) {
                if (resp.status===200) {
                    //console.log(resp);
                    _this.$store.commit('initMeetTimeItem', resp.data);
                }
            })
        }
    }
</script>

<style scoped>

</style>
