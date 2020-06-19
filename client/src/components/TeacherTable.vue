<template>
    <div>
        <el-table :data="tableData" stripe border style="width: 100%">
            <el-table-column prop="homeworkId" label="作业ID"></el-table-column>
            <el-table-column prop="title" label="作业标题"></el-table-column>
            <el-table-column prop="submitCount" label="提交数"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="toSubmitList(scope.row.homeworkId)" size="mini">批阅作业</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import api from "../api";

    export default {
        name: "TeacherTable",
        data() {
            return {
                tableData: []
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
                api.teacherHomeworkList(data).then(res => {
                    if (res.state === 0) {
                        this.tableData = res.list
                    }
                }).catch(reason => {
                    alert(reason)
                })
            },
            toSubmitList: function (homeworkId) {
                this.$cookie.set("homeworkId", homeworkId);
                this.$router.push({path: '/teacher/homework_list'});
            }
        }
    }
</script>

<style scoped>

</style>