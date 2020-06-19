<template>
    <div style="height: 100%">
        <el-container style="height: 100%">
            <el-header>
                <Bar :id="id" :teacher="teacher"/>
            </el-header>

            <div style="width: 60%; margin: 60px auto;">
                <h2>发布作业</h2>
                <el-form :model="newHomework">
                    <el-form-item label="作业标题">
                        <el-input v-model="newHomework.title" placeholder="请输入标题"/>
                    </el-form-item>
                    <el-form-item label="作业内容">
                        <el-input :rows="4" v-model="newHomework.content" type="textarea"
                                  placeholder="请输入内容"/>
                    </el-form-item>
                </el-form>
                <el-button @click="submitHomework" type="primary" size="medium">添加</el-button>
                <el-button @click="handleClear" size="medium">清空</el-button>
            </div>

        </el-container>
    </div>
</template>

<script>
    import api from "@/api";
    import Bar from "@/components/Bar";

    export default {
        name: "AddHomework",
        components: {
            Bar
        },
        data() {
            return {
                id: this.$cookie.get("id"),
                token: this.$cookie.get("token"),
                teacher: this.$cookie.get("teacher"),
                newHomework: {
                    title: '',
                    content: '',
                }
            }
        },

        methods: {
            submitHomework: function () {
                let data = {
                    id: this.$cookie.get('id'),
                    token: this.$cookie.get('token'),
                    title: this.newHomework.title,
                    content: this.newHomework.content
                }
                api.teacherAddHomework(data).then(res => {
                    this.showHomeworkDetail = !this.showHomeworkDetail
                    if (res.state === 0) {
                        alert("发布成功！")
                        this.$router.push({path: '/teacher'});
                    }
                }).catch(reason => alert(reason))
            },
            handleClear: function () {
                this.newHomework.title = "";
                this.newHomework.content = "";
            }
        }
    }
</script>

<style scoped>

</style>