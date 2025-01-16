
import VueAxios from 'vue-axios'
import axios from 'axios'
import { useRouter } from 'vue-router'
// import config from '../config'

const BASE_URL = 'http://localhost:8080/api/v1'
const DEFAULT_ACCEPT_TYPE = 'application/json'
const router = useRouter();

axios.defaults.baseURL = BASE_URL
axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE

axios.interceptors.request.use((config) => {
    const token = localStorage.getItem('ssafy-token');

    if(token != null) {
        config.headers.Authorization = `Bearer ${token}`;
        console.log("인터셉터 작동");
    }
    return config;
})

axios.interceptors.response.use((res) => {
    return res;
}, (error) => {
    if(error.status == 401) {
        alert("세션이 유효하지 않습니다");
        router.push("/");
    }
    if(error.status == 403) {
        alert("접근 권한이 없습니다");
        router.push("/");
    }
    return Promise.reject(error);
})

export default { VueAxios, axios }
