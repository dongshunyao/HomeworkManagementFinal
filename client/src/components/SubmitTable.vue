<template>
    <div>
        <el-table :data="tableData" stripe border style="width: 100%">
            <el-table-column prop="submitId" label="提交ID"></el-table-column>
            <el-table-column prop="studentId" label="学号"></el-table-column>
            <el-table-column prop="studentName" label="姓名"></el-table-column>
            <el-table-column prop="type" label="状态">
                <template slot-scope="scope">
                    <el-tag type="danger" v-if="scope.row.type===1">未提交</el-tag>
                    <el-tag v-if="scope.row.type===2">未批阅</el-tag>
                    <el-tag type="success" v-if="scope.row.type===3">已批阅</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="getSubmitDetail(scope.row.submitId)" size="mini">批阅</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog
                :visible.sync="showHomeworkDetail"
                title="提交详情"
                center>
            <el-form :model="reviewDetail" label-position="left" label-width="120px">
                <el-form-item label="学号">
                    {{submitDetail.studentId}}
                </el-form-item>
                <el-form-item v-if="submitDetail.type!==1" label="提交标题">
                    {{submitDetail.submitTitle}}
                </el-form-item>
                <el-form-item v-if="submitDetail.type!==1" label="提交内容">
                    {{submitDetail.submitContent}}
                </el-form-item>

                <el-form-item v-if="submitDetail.type===3" label="老师评分">
                    {{submitDetail.score}}
                </el-form-item>
                <el-form-item v-if="submitDetail.type===3" label="老师评语">
                    {{submitDetail.comment}}
                </el-form-item>

                <el-form-item label="我的评分">
                    <el-input v-model="reviewDetail.score" placeholder="请输入内容"/>
                </el-form-item>
                <el-form-item label="我的评语">
                    <el-input :rows="4" v-model="reviewDetail.comment" type="textarea"
                              placeholder="请输入内容"/>
                </el-form-item>
            </el-form>
            <span slot="footer">
        <span slot="center">
            <el-button @click="reviewHomework" type="primary" size="medium">提交</el-button>
            <el-button @click="handleCancel" size="medium">取消</el-button>
        </span>
    </span>
        </el-dialog>
    </div>
</template>

<script>
    import api from "../api";

    export default {
        name: "SubmitTable",
        data() {
            return {
                homeworkId: 0,
                tableData: [],
                showHomeworkDetail: false,
                submitDetail: {
                    submitId: 1,
                    studentId: 1,
                    type: 1,
                    submitTitle: '',
                    submitContent: '',
                    score: '',
                    comment: ''
                },
                reviewDetail: {
                    submitId: 1,
                    score: 100,
                    comment: ''
                }
            }
        },
        mounted() {
            this.homeworkId = this.$cookie.get("homeworkId");
            this.getSubmitList()
        },
        methods: {
            getSubmitList: function () {
                let data = {
                    id: this.$cookie.get('id'),
                    homeworkId: this.homeworkId,
                    token: this.$cookie.get('token')
                }
                api.teacherSubmitList(data).then(res => {
                    if (res.state === 0) {
                        this.tableData = res.list
                    }
                }).catch(reason => {
                    alert(reason)
                })
            },
            getSubmitDetail: function (submitId) {
                this.showHomeworkDetail = !this.showHomeworkDetail
                let data = {
                    id: this.$cookie.get('id'),
                    submitId: submitId,
                    token: this.$cookie.get('token')
                }
                api.teacherSubmitInfo(data).then(res => {
                    if (res.state === 0) {
                        this.submitDetail.submitId = res.submitId
                        this.reviewDetail.submitId = res.submitId
                        this.submitDetail.studentId = res.studentId
                        this.submitDetail.type = res.type
                        this.submitDetail.submitTitle = res.submitTitle
                        this.submitDetail.submitContent = res.submitContent
                        this.submitDetail.score = res.score
                        this.submitDetail.comment = res.comment
                    }
                }).catch(reason => {
                    alert(reason)
                })
            },
            reviewHomework: function () {
                let data = {
                    id: this.$cookie.get('id'),
                    token: this.$cookie.get('token'),
                    score: this.reviewDetail.score,
                    comment: this.reviewDetail.comment,
                    submitId: this.reviewDetail.submitId
                }
                api.teacherReview(data).then(res => {
                    this.showHomeworkDetail = !this.showHomeworkDetail
                    if (res.state === 0) {
                        alert("批阅成功！")
                        this.getSubmitList()
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