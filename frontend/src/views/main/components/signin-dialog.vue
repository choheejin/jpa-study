<template>
  <div v-if="state.dialogVisible" class="login-dialog-overlay">
    <div class="login-dialog">
      <div class="login-dialog-header">
        <h3>회원가입</h3>
        <button class="close-btn" @click="handleClose">&times;</button>
      </div>
      <form @submit.prevent="clickSignin" ref="loginForm">
        <div class="form-group">
          <label for="name">이름</label>
          <input type="text" id="name" v-model="state.form.name" autocomplete="off" />
          <span v-if="errors.name" class="error">{{ errors.name }}</span>
        </div>
        <div class="form-group">
          <label for="id">아이디</label>
          <input type="text" id="id" v-model="state.form.id" autocomplete="off" />
          <span v-if="errors.id" class="error">{{ errors.id }}</span>
        </div>
        <div class="form-group">
          <label for="department">부서</label>
          <input type="text" id="department" v-model="state.form.department" autocomplete="off" />
          <span v-if="errors.department" class="error">{{ errors.department }}</span>
        </div>
        <div class="form-group">
          <label for="position">직책</label>
          <input type="text" id="position" v-model="state.form.position" autocomplete="off" />
          <span v-if="errors.position" class="error">{{ errors.position }}</span>
        </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" v-model="state.form.password" autocomplete="off" />
          <span v-if="errors.password" class="error">{{ errors.password }}</span>
        </div>
        <div class="form-group">
          <label for="validPassword">비밀번호 확인</label>
          <input type="password" id="validPassword" v-model="state.validPassword" autocomplete="off" />
          <span v-if="errors.validPassword" class="error">{{ errors.validPassword }}</span>
        </div>
        <div class="dialog-footer">
          <button type="submit" class="btn-primary">회원가입</button>
        </div>
      </form>
    </div>
  </div>
</template>

<style>
.login-dialog-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}
.login-dialog {
  background: white;
  padding: 20px;
  width: 400px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  position: relative;
}
.login-dialog-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.close-btn {
  background: none;
  border: none;
  font-size: 24px;
  cursor: pointer;
}
.form-group {
  margin-bottom: 20px;
}
.form-group label {
  display: block;
  margin-bottom: 5px;
}
.form-group input {
  width: calc(100% - 20px);
  padding: 8px 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.error {
  color: red;
  font-size: 12px;
  margin-top: 5px;
  display: block;
}
.dialog-footer {
  text-align: center;
}
.btn-primary {
  background-color: #409eff;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>

<script>
import { reactive, ref, watch } from 'vue'
import { useStore } from 'vuex'
import { requestSignin } from '../../../common/api/accountAPI';

export default {
  name: 'SigninDialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    const loginForm = ref(null)

    const state = reactive({
      form: {
        id: '',
        password: '',
        department: '',
        position: '',
        name: '',
      },
      validPassword: '',
      dialogVisible: props.open
    })

    const errors = reactive({
      id: '',
      password: '',
      department: '',
      position: '',
      name: '',
      validPassword: ''
    })

    watch(() => props.open, (newVal) => {
      state.dialogVisible = newVal
    })

    const validate = () => {
      const re = new RegExp("(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~@#$!%*?&]).*");
      let valid = true

      if (!state.form.id) {
        errors.id = '필수 입력 항목입니다'
        valid = false
      } else {
        errors.id = ''
      }

      if (!state.form.password) {
        errors.password = '필수 입력 항목입니다'
        valid = false
      } 
      else if(state.form.password.length < 9) {
        errors.password = '최소 9 글자를 입력해야 합니다'
        valid = false
      }
      else if(state.form.password.length > 16) {
        errors.password = '최대 16 글자까지 입력 가능합니다'
        valid = false
      }
      else if(!re.test(state.form.password)) {
        console.log(re.test(state.form.password));
        errors.password = '비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다'
        valid = false
      } else {
        errors.password = ''
      }

      if (!state.form.name) {
        errors.name = '필수 입력 항목입니다'
        valid = false
      } else if(state.form.name && state.form.name.length > 30) {
        errors.name = '최대 30자까지 입력 가능합니다'
        valid = false
      }
      else {
        errors.name = ''
      }


      if(state.form.position && state.form.position.length > 30) {
        errors.position = '최대 30자까지 입력 가능합니다'
        valid = false
      } else {
        errors.position = ''
      }

      if(state.form.department && state.form.department.length > 30) {
        errors.department = '최대 30자까지 입력 가능합니다'
        valid = false
      } else {
        errors.department = ''
      }

      if(!state.validPassword) {
        errors.validPassword = '필수 입력 항목입니다'
        valid = false
      }
      else if(state.validPassword !== state.form.password) {
        errors.validPassword = '입력한 비밀번호와 일치하지 않습니다'
        valid = false
      }
      else {
        errors.validPassword = ''
      }

      return valid
    }

    const clickSignin = async () => {
      if (validate()) {
        console.log('submit')
        
        const response = await requestSignin(state.form).then((res) => {
          if(res.status == 200) {
            alert("회원가입이 완료되었습니다.")
            handleClose()
          }
        }).catch((error) => {
          console.log(error);
          
          errors.id = '이미 존재하는 아이디입니다' 
        })

      } else {
        alert('Validate error!')
      }
    }

    const handleClose = () => {
      state.form.id = ''
      state.form.password = ''
      emit('closeSigninDialog')
    }

    return { state, errors, loginForm, clickSignin, handleClose }
  }
}
</script>
