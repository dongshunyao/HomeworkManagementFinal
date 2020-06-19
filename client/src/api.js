import axios from 'axios'
import qs from 'qs'

let config = {
    baseURL: '',

    userLogin: {url: '/user/login'},
    userLogout: {url: '/user/logout'},
    userRegister: {url: '/user/register'},

    studentHomeworkList: {url: '/student/homework_list'},
    studentHomeworkInfo: {url: '/student/homework_info '},
    studentHomeworkSubmit: {url: '/student/submit'},

    teacherHomeworkList: {url: '/teacher/homework_list'},
    teacherSubmitList: {url: '/teacher/submit_list'},
    teacherSubmitInfo: {url: '/teacher/submit_info'},
    teacherReview: {url: '/teacher/review'},
    teacherAddHomework: {url: '/teacher/add_homework'}
}
let api = {}

for (var elm in config) {
    api[elm] = (function (innerConfig, api) {
        return function (data) {
            return new Promise((resolve) => {
                let apiInfo = innerConfig[api]
                let config = {
                    baseURL: innerConfig.baseURL,
                    url: apiInfo.url,
                    method: "post",
                    data: qs.stringify(data),
                }
                console.log(config)
                axios(config).then((res) => {
                    try {
                        console.log(res.data);
                        resolve(res.data);
                    } catch (err) {//
                    }
                }).catch()
            })
        }
    })(config, elm)
}

export default api;