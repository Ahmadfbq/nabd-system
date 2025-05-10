import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from './utils/axios'

const app = createApp(App)

// Make axios instance available globally
app.config.globalProperties.$axios = axios

app.use(router)

app.mount('#app')
