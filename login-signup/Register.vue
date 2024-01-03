<template>
    <el-row class="register_form_container">
        <el-col :span="24" :xs="24" :sm="12" :md="12" :lg="12" :xl="12" :offset="6">
            <el-form class="register_form" :model="registerForm" :label-position="labelPosition"
                :label-width="formLabelWidth">
                <h1>Welcome</h1>
                <h2>网上书店管理系统 - 注册</h2>
                <el-form-item>
                    <el-input size="large" :prefix-icon="User" v-model="registerForm.username"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input size="large" :prefix-icon="Lock" v-model="registerForm.password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button class="register_btn" :loading="loading" size="large" type="success" @click="registerButton">
                        注册
                    </el-button>
                    <div class="btn_space"></div>
                    <el-tooltip effect="dark" content="已有账号？立即登录" placement="bottom">
                        <el-button class="register_btn" slot="reference" size="large" type="success" @click="loginButton">
                            返回登录
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

const router = useRouter()

let registerForm = reactive({
    username: '',
    password: '',
});

let loading = ref<boolean>(false);
let labelPosition = 'top';
let formLabelWidth = '120px';

function registerButton() {
    axios({
        method: 'post',
        url: `http://localhost:3919/serve8080/register`,
        data: {
            userName: registerForm.username,
            password: registerForm.password,
        }
    }).then(res => {
        loading.value = true
        if (res.data === 'success') {
            ElMessage({
                message: '注册成功',
                grouping: true,
                type: 'success',
                center: true,
            })
            setTimeout(() => {
                router.replace({
                    name: 'login'
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
    })
}

function loginButton() {
    // Redirect to the login page
    router.replace({
        name: 'login'
    })
}
</script>
  
<style lang="less" scoped>
.register_form_container {
    height: 100vh;
    background-color: #f5f5f5;
    display: flex;
    align-items: center;
    justify-content: center;
}

.register_form {
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

    .register_btn {
        display: inline-block;
        width: 45%;
    }

    .btn_space {
        display: inline-block;
        width: 10%;
    }

}
</style>
  