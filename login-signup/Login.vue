<template>
    <el-row class="login_form_container">
        <el-col :span="24" :xs="24" :sm="12" :md="12" :lg="12" :xl="12" :offset="6">
            <el-form class="login_form" :model="loginForm" :label-position="labelPosition" :label-width="formLabelWidth">
                <h1>Hello</h1>
                <h2>网上书店管理系统</h2>
                <el-form-item>
                    <el-input size="large" :prefix-icon="User" v-model="loginForm.userName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input size="large" type="password" :prefix-icon="Lock" v-model="loginForm.password"
                        show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button class="login_btn" :loading="loading" size="large" type="primary" @click="loginButton">
                        登录
                    </el-button>
                    <div class="btn_space"></div>
                    <el-tooltip effect="dark" content="享受无缝的购物体验，发现阅读的乐趣" placement="bottom">
                        <el-button class="login_btn" slot="reference" size="large" type="primary" @click="registerButton">
                            没有账号？立即注册
                        </el-button>
                    </el-tooltip>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
</template>
  
<script setup lang="ts">
import { reactive, ref } from 'vue';
import { Lock, User } from '@element-plus/icons-vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user'

const router = useRouter()
const userStore = useUserStore()

let loginForm = reactive({
    userName: 'purexua',
    password: '123456',
});

let loading = ref<boolean>(false);
let labelPosition = 'top';
let formLabelWidth = '120px';

function loginButton() {
    axios({
        method: 'get',
        url: 'http://localhost:3919/serve8080/login',
        params: {
            userName: loginForm.userName,
            password: loginForm.password,
        }
    }).then(res => {
        loading.value = true
        if (res.data === 'success') {
            ElMessage({
                message: '登录成功',
                grouping: true,
                type: 'success',
                center: true,
            })

            // Save the user info to the store
            userStore.getUserInfo(loginForm.userName)

            setTimeout(() => {
                router.replace({
                    name: 'index'
                })
            }, 1000)
        } else {
            ElMessage({
                message: res.data,
                grouping: true,
                type: 'error',
                center: true,
            })
        }
    }).catch(err => {
        console.log(err)
    }).finally(() => {
        loading.value = false
    })
}

function registerButton() {
    // Redirect to the register page
    router.replace({
        name: 'register'
    })
}
</script>
  
<style lang="less" scoped>
.login_form_container {
    height: 100vh;
    background-color: #f5f5f5;
    display: flex;
    align-items: center;
    justify-content: center;
}

.login_form {
    margin-left: 62px;
    width: 80%;
    background-color: #fff;
    padding: 40px;
    color: #333;

    h1 {
        color: #333;
        font-size: 40px;
    }

    h2 {
        font-size: 20px;
        color: #333;
        margin: 20px 0px;
    }

    .login_btn {
        display: inline-block;
        width: 45%;
    }

    .btn_space {
        display: inline-block;
        width: 10%;
    }
}
</style>