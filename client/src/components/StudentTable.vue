<template>
    <div>
        <el-table :data="tableData" stripe border style="width: 100%">
            <el-table-column prop="homeworkId" label="作业ID"></el-table-column>
            <el-table-column prop="teacherName" label="发布者"></el-table-column>
            <el-table-column prop="title" label="作业标题"></el-table-column>
            <el-table-column prop="type" label="状态">
                <template slot-scope="scope">
                    <el-tag type="danger" v-if="scope.row.type===1">未提交</el-tag>
                    <el-tag v-if="scope.row.type===2">未批阅</el-tag>
                    <el-tag type="success" v-if="scope.row.type===3">已批阅</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="getHomeDetail(scope.row.homeworkId)" size="mini">详情</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog
                :visible.sync="showHomeworkDetail"
                title="作业详情"
                center>
            <el-form :model="newHomeworkDetail" label-position="left" label-width="120px">
                <el-form-item label="作业标题">
                    {{homeworkDetail.homeworkTitle}}
                </el-form-item>
                <el-form-item label="作业内容">
                    {{homeworkDetail.homeworkContent}}
                </el-form-item>
                <el-form-item v-if="homeworkDetail.type!==1" label="已提交标题">
                    {{homeworkDetail.submitTitle}}
                </el-form-item>
                <el-form-item v-if="homeworkDetail.type!==1" label="已提交内容">
                    {{homeworkDetail.submitContent}}
                </el-form-item>
                <div v-if="homeworkDetail.type!==3">
                    <el-form-item label="我的标题">
                        <el-input v-model="newHomeworkDetail.title" placeholder="请输入内容"/>
                    </el-form-item>
                    <el-form-item label="我的内容">
                        <el-input :rows="4" v-model="newHomeworkDetail.content" type="textarea"
                                  placeholder="请输入内容"/>
                    </el-form-item>
                </div>
                <el-form-item v-if="homeworkDetail.type===3" label="老师评分">
                    {{homeworkDetail.score}}
                </el-form-item>
                <el-form-item v-if="homeworkDetail.type===3" label="老师评语">
                    {{homeworkDetail.comment}}
                </el-form-item>
            </el-form>
            <span slot="footer">
        <span slot="center">
            <el-button v-if="homeworkDetail.type!==3" @click="submitHomework" type="primary"
                       size="medium">提交</el-button>
            <el-button @click="handleCancel" size="medium">取消</el-button>
        </span>
    </span>
        </el-dialog>
    </div>
</template>

<script>
    import api from "../api";

    export default {
        name: "StudentTable",
        data() {
            return {
                tableData: [],
                showHomeworkDetail: false,
                homeworkDetail: {
                    homeworkId: 1,
                    homeworkTitle: 'title',
                    homeworkContent: 'content',
                    type: 1,
                    submitTitle: 'submitTitle',
                    submitContent: 'submitContent',
                    score: '',
                    comment: ''
                },
                newHomeworkDetail: {
                    homeworkId: 1,
                    title: '',
                    content: ''
                }
            }
        },
        mounted() {
            this.getHomeWorkList()
        },
        methods: {
            getHomeWorkList: function () {
                let data = {
                    id: this.$cookie.get('id'),
                    token: this.$cookie.get('token')
                }
                api.studentHomeworkList(data).then(res => {
                    if (res.state === 0) {
                        this.tableData = res.list
                    }
                }).catch(reason => {
                    alert(reason)
                })
            },
            getHomeDetail: function (homeworkID) {
                this.showHomeworkDetail = !this.showHomeworkDetail
                let data = {
                    id: this.$cookie.get('id'),
                    homeworkId: homeworkID,
                    token: this.$cookie.get('token')
                }
                api.studentHomeworkInfo(data).then(res => {
                    if (res.state === 0) {
                        this.homeworkDetail.homeworkId = res.homeworkId
                        this.newHomeworkDetail.homeworkId = res.homeworkId
                        this.homeworkDetail.homeworkTitle = res.homeworkTitle
                        this.homeworkDetail.homeworkContent = res.homeworkContent
                        this.homeworkDetail.type = res.type
                        this.homeworkDetail.submitTitle = res.submitTitle
                        this.homeworkDetail.submitContent = res.submitContent
                        this.homeworkDetail.score = res.score
                        this.homeworkDetail.comment = res.comment
                    }
                }).catch(reason => {
                    alert(reason)
                })
            },
            submitHomework: function () {
                let data = {
                    id: this.$cookie.get('id'),
                    token: this.$cookie.get('token'),
                    homeworkId: this.newHomeworkDetail.homeworkId,
                    title: this.newHomeworkDetail.title,
                    content: this.newHomeworkDetail.content
                }
                api.studentHomeworkSubmit(data).then(res => {
                    this.showHomeworkDetail = !this.showHomeworkDetail
                    if (res.state === 0) {
                        alert("提交成功！")
                        this.getHomeWorkList()
                    }
                }).catch(reason => alert(reason))
            },
            handleCancel: function () {
                this.showHomeworkDetail = !this.showHomeworkDetail
            }
        }
    }
</script>

<style scoped>

</style>