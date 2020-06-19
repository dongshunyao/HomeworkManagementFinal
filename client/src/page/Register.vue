<template>
    <div>
        <div class="back"></div>
        <el-container>
            <el-main>
                <RegisterPanel style="margin-top:80px"/>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import RegisterPanel from "@/components/RegisterPanel";

    export default {
        name: 'Register',
        components: {RegisterPanel},
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