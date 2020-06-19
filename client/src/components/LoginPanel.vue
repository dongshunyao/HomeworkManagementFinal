<template>
    <el-card class="panel">
        <div>
            <el-form ref="loginForm" :model="form" label-width="80px" class="login-box">
                <h3 class="login-title">欢迎来到作业管理系统</h3>
                <el-form-item label="账号" prop="id">
                    <el-input type="text" placeholder="请输入学号或工号" v-model="form.id" style="width: 420px"/>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" placeholder="请输入密码" v-model="form.password" style="width: 420px"/>
                </el-form-item>
                <el-form-item label="您是" prop="job">
                    <el-select placeholder="学生 or 老师？" v-model="form.job" style="width: 420px">
                        <el-option label="学生" value="student"></el-option>
                        <el-option label="教师" value="teacher"></el-option>
                    </el-select>
                </el-form-item>

                <el-button type="primary" v-on:click="onLogin">登录</el-button>
                <el-button v-on:click="onRegister">注册</el-button>

            </el-form>
        </div>
    </el-card>
</template>

<script>
    import api from '../api';

    export default {
        name: 'LoginPanel',
        components: {},
        data() {
            return {
                form: {
                    id: '',
                    password: '',
                    job: ''
                }
            }
        },
        methods: {
            onLogin: function () {

                let formInfo = {
                    id: this.form.id,
                    password: this.form.password,
                    teacher: (this.form.job == "teacher"),
                };

                api.userLogin(formInfo).then(res => {
                    if (res.state === 0) {
                        this.$cookie.set("id", res.id);
                        this.$cookie.set("token", res.token);
                        this.$cookie.set("teacher", formInfo.teacher);
                        if (formInfo.teacher) {
                            this.$router.push({path: '/teacher'});
                        } else {
                            this.$router.push({path: '/student'});
                        }
                    } else {
                        this.$message.error(res.message);
                    }
                }).catch(msg => {
                    alert(msg);
                });
            },
            onRegister: function () {
                this.$router.push({path: '/register'});
            }
        }
    }
</script>

<style scoped>
    .panel {
        display: inline-block;
        width: 600px;
    }
</style>