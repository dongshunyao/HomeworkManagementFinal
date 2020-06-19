<template>
    <div>
    </div>
</template>

<script>

    import api from "@/api";

    export default {
        name: 'Logout',
        components: {},
        data() {
            return {
                id: this.$cookie.get("id"),
                token: this.$cookie.get("token"),
                teacher: this.$cookie.get("teacher")
            }
        },
        created: function () {
            this.doLogout();
        },
        methods: {
            doLogout: function () {
                let formInfo = {
                    id: this.id,
                    token: this.token,
                    teacher: this.teacher
                };
                api.userLogout(formInfo).then(res => {
                    this.$cookie.delete("id");
                    this.$cookie.delete("token");
                    this.$cookie.delete("teacher");
                    console.log(res)
                    this.$router.push({path: '/login'});
                }).catch(msg => {
                    this.$cookie.delete("id");
                    this.$cookie.delete("token");
                    this.$cookie.delete("teacher");
                    this.$router.push({path: '/login'});
                    alert(msg);
                });
            }
        }
    }
</script>
