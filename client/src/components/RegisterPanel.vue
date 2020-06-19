<template>
    <el-card class="panel">
        <div>
            <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
                <h3 class="login-title">注册</h3>
                <el-form-item label="账号" prop="id">
                    <el-input type="text" placeholder="请输入学号或工号" v-model="form.id" style="width: 420px"/>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input type="text" placeholder="请输入姓名" v-model="form.name" style="width: 420px"/>
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
                <el-button type="primary" v-on:click="onRegister">注册</el-button>
            </el-form>
        </div>
    </el-card>
</template>

<script>
    import api from '../api';

    export default {
        name: 'RegisterPanel',
        components: {},
        data() {
            return {
                form: {
                    id: '',
                    name: '',
                    password: '',
                    job: ''
                }
            }
        },
        methods: {
            onRegister: function () {
                let formInfo = {
                    id: this.form.id,
                    name: this.form.name,
                    password: this.form.password,
                    teacher: (this.form.job == "teacher" ? true : false),
                };

                api.userRegister(formInfo).then(res => {
                    if (res.state === 0) {
                        this.$alert('注册成功', '恭喜', {
                            confirmButtonText: '确定',
                            callback: action => {
                                this.$router.push({path: '/login'});
                                console.log(action)
                            }
                        });
                    } else {
                        alert(res.message);
                    }
                }).catch(msg => {
                    alert(msg);
                });
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