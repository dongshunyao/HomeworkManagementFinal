import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/page/Login.vue'
import Register from "@/page/Register"
import Logout from "@/page/Logout";
import Student from "@/page/Student";
import Teacher from "@/page/Teacher";
import TeacherSubmit from "@/page/TeacherSubmit";
import AddHomework from "@/page/AddHomework";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: Login,
        meta: {title: '登录'}
    },
    {
        path: '/login',
        component: Login,
        meta: {title: '登录'}
    },
    {
        path: '/register',
        component: Register,
        meta: {title: '注册'}
    },
    {
        path: '/logout',
        component: Logout,
        meta: {title: '登出'}
    },
    {
        path: '/student',
        component: Student,
        meta: {title: '学生主页'}
    },
    {
        path: '/teacher',
        component: Teacher,
        meta: {title: '老师主页'}
    },
    {
        path: '/teacher/homework_list',
        component: TeacherSubmit,
        meta: {title: '提交列表'}
    },
    {
        path: '/teacher/add_homework',
        component: AddHomework,
        meta: {title: '发布作业'}
    },
]

const router = new VueRouter({
    base: process.env.BASE_URL,
    routes
})

export default router
