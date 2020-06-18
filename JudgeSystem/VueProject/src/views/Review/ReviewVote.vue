<template><div>
    <div  align="center">
        <h4>评审投票</h4>
        <table id="main" >
            <div>
            <div v-for="(item,index) in reviewItem">
                <tr>
                    <td>
                        <input type="radio" name="option"/>{{item.name}}
                    </td>
                </tr>
                <tr>
                    <td style="width:550px">
                        <div :id="index"  style="background-color:#acd6ff; width:0; height:20px; border-width:0px;">
                        </div>
                    </td>
                    <td>
                        <label :id="label+index">{{item.review_vote}}</label>票
                    </td>
                </tr>

            </div>
           <!-- <tr>
                <td>
                    <input type="radio" name="option" checked="checked"/>猫和老鼠
                </td>
            </tr>
            <tr>
                <td style="width:550px">
                    <div id=0 style="background-color:#acd6ff; width:0; height:20px; border-width:0px;">
                    </div>
                </td>
                <td>
                    <label id="label0">0</label>票
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="option"  />海贼王
                </td>
            </tr>
            <tr >
                <td>
                    <div id=1 style="background-color:#0066cc; height:20px; width:0; border-width:0px; border-color:blue; border-style:solid ; text-align:right;" ></div>

                </td>
                <td>
                    <label id="label1">0</label>票
                </td>
            </tr>
            <tr >
                <td>
                    <input type="radio" name="option"  />喜羊羊
                </td>
            </tr>
            <tr >
                <td>
                    <div id=2 style="background-color:#ff7575; height:20px;width:0;  border-width:0px; border-color:blue; border-style:solid ; text-align:right;" ></div>

                </td>
                <td>
                    <label id="label2">0</label>票
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="option" />其他
                </td>
            </tr>
            <tr >
                <td>
                    <div id=3 style="background-color:#5cadad;height:20px;width:0; border-width:0px; border-color:blue; border-style:solid ; text-align:right;" ></div>

                </td>
                <td>
                    <label id="label3">0</label>票
                </td>
            </tr>-->

            <tr>
                <td><p>
                    <el-button type="submit"  @click="vote">确认投票</el-button></p>
                </td>
            </tr>
            </div>
        </table>
    </div>
</div>
</template>
<script>
    export default {
        data() {
            return {
                applyerList:[
                    {id:1,name:'小张',voteResult:0},
                    {id:2,name:'小李',voteResult:0},
                    {id:3,name:'小吴',voteResult:0},
                    {id:5,name:'李测试',voteResult:0},
                    {id:6,name:'张晓霞',voteResult:0},
                    ],
                label:'label',
                reviewVote:{
                    id:'',
                    name:'',
                    review_vote:''
                },
                count:0,
               // reviewItem:[]
            }
        },
        computed: {
            reviewItem(){
                return this.$store.state.reviewItem;
            }

        },
        mounted() {
        },
        methods: {
            vote(){	//函数vote，当点击确认投票的时候，调用vote方法
                //for循环的条件是，所有投票选项的个数。
                let list=this.$store.state.reviewItem;
                const _this=this;
                let maxVote=document.getElementsByName("option").length;
                console.log(_this.count)
                console.log(maxVote)
                if (_this.count<=maxVote) {
                    _this.count=_this.count+1;
                    console.log(_this.count)
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
                        _this.voteResult(list[i].name,document.getElementById(label).innerText,list[i].id)

                    }
                }
                }else {
                    alert("投票次数超出")
                }
            },
            voteResult(name,vote,id){
                //let reviewVote = new Object();
                /*let _this=this;*/
                this.reviewVote.name=name;
                this.reviewVote.review_vote=vote;
                this.reviewVote.id=id;
                let url='http://localhost:8015/apply/updateVoteByName';
                this.axios.put(url,this.reviewVote).then(function (resp) {

                })


            }


            },
        created() {
            const _this=this
            let url='http://localhost:8015/apply/findCurrentApp';
            _this.axios.get(url).then(function(resp) {
               if (resp.status===200) {
                //console.log(resp);
                _this.$store.commit('initReviewItem', resp.data);
               }
            })
        }


    }
</script>
<style>

</style>
