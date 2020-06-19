<template>
    <div>
        <div class="back"></div>
        <el-container>
            <el-main>
                <LoginPanel style="margin-top:80px"/>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import LoginPanel from '../components/LoginPanel'

    export default {
        name: 'Login',
        components: {LoginPanel},
        data() {
            return {}
        },
        methods: {}
    }
</script>

<style scoped>
    .back {
        background-color: #91d7b2;
        height: 100%;
        position: fixed;
        width: 100%;
        top: 0;
        left: 0;
        z-index: -1
    }
</style>