<template>
    <div id="nav">
        <el-menu default-active="1" class="el-menu-demo" mode="horizontal">
            <el-menu-item>
                <b class="LF" style="font-size: 24px">作业管理系统</b>
            </el-menu-item>
            <el-menu-item index="1" class="LF" @click="homeClick">我的作业</el-menu-item>
            <el-menu-item index="2" class="LF" v-if="teacher == 'true'" @click="addClick">发布作业</el-menu-item>
            <el-menu-item index="3" @click="doExit">登出</el-menu-item>

            <el-submenu index="1" style="float: right">
                <template slot="title">
                    <i class="el-icon-user"></i>
                    <a class="LF">我的 ID 为 {{ id }} </a>
                </template>
                <el-menu-item @click="doExit">登出</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: 'Bar',
        props: ['id', 'teacher'],
        data() {
            return {}
        },
        methods: {
            doExit: function () {
                this.$confirm('确认登出？')
                    .then(() => {
                        this.$router.push({path: '/logout'});
                    })
                    .catch(() => {
                    });
            },
            addClick: function () {
                this.$router.push({path: '/teacher/add_homework'});
            },
            homeClick: function () {
                if (this.teacher) {
                    this.$router.push({path: '/teacher'});
                } else {
                    this.$router.push({path: '/student'});
                }
            }
        }
    }
</script>

<style scoped>
</style>
