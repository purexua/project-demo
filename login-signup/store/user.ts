import axios from 'axios'
import { defineStore } from 'pinia'
import { reactive, watch } from 'vue'

export const useUserStore = defineStore('user', () => {
    //state
    let user = reactive({
        userId: '',
        userName: '',
        password: '',
        realName: '',
        email: '',
        address: '',
        phoneNumber: '',
        role: '',
        wallet: '',
        registerTime: '',
    })
    //actions
    function getUserInfo(userName: string) {
        axios(
            {
                method: 'get',
                url: `http://localhost:3919/serve8080/user/${userName}`,
            }
        ).then((res) => {
            user.userId = res.data.userId
            user.userName = res.data.userName
            user.password = res.data.password
            user.realName = res.data.realName
            user.email = res.data.email
            user.address = res.data.address
            user.phoneNumber = res.data.phoneNumber
            user.role = res.data.role
            user.wallet = res.data.wallet
            user.registerTime = res.data.registerTime
        })
    }

    //reset state
    function $reset() {
        user.userId = ''
        user.userName = ''
        user.password = ''
        user.realName = ''
        user.email = ''
        user.address = ''
        user.phoneNumber = ''
        user.role = ''
        user.wallet = ''
        user.registerTime = ''
    }

    return { user, getUserInfo, $reset }
})