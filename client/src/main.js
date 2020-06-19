import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import axios from 'axios'
import VueCookie from 'vue-cookie'
import VueAxios from 'vue-axios'

import './element/element.js'

Vue.config.productionTip = false
Vue.use(VueCookie)
Vue.use(VueAxios, axios)

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next()
})

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')