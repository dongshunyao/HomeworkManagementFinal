<template>
    <div style="height: 100%">
        <el-container style="height: 100%">
            <el-header>
                <Bar :id="id" :teacher="teacher"/>
            </el-header>

            <div style="width: 80%; margin: 10px auto;">
                <student-table></student-table>
            </div>

        </el-container>
    </div>
</template>

<script>
    import Bar from "@/components/Bar";
    import StudentTable from "../components/StudentTable";

    export default {
        name: 'Student',
        components: {
            StudentTable,
            Bar
        },
        data() {
            return {
                id: this.$cookie.get("id"),
                token: this.$cookie.get("token"),
                teacher: this.$cookie.get("teacher")
            }
        },
        methods: {}
    }
</script>
