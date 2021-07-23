<template>
  <el-dialog custom-class="signup-dialog" title="회원가입" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <div class="el-form-item" style="margin: 0px;">
        <el-button @click="clickCheckId" size="mini" style="margin: 0px 0px 0px 280px">중복확인</el-button>
      </div>
      <el-form-item prop="id" label="아이디" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.id" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
      </el-form-item>
      <el-form-item prop="passwordConfirmation" label="비밀번호확인" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.passwordConfirmation" autocomplete="off" show-password></el-input>
      </el-form-item>
      <el-form-item prop="department" label="소속" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.department" autocomplete="on"></el-input>
      </el-form-item>
      <el-form-item prop="position" label="직책" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.position" autocomplete="on"></el-input>
      </el-form-item>
      <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.name" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="clickSignup">가입하기</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style>
.signup-dialog {
  width: 400px !important;
  height: 580px;
}
.signup-dialog .el-dialog__headerbtn {
  float: right;
}
.signup-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.signup-dialog .el-form-item {
  margin-bottom: 20px;
}
.signup-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.signup-dialog .el-input__suffix {
  display: none;
}
.signup-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.signup-dialog .dialog-footer .el-button {
  width: 120px;
}
.swal2-container {
  z-index: 2020;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'signup-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)
    console.log('아이디'.length)
    
    const checkId = (rule, value, callback) => {
      if (value.length > 16) {
        callback(new Error('아이디는 16자 이내여야 합니다.'));
      } 
    }

    const checkPassword = (rule, value, callback) => {
      if (value.length < 9 || value.length > 16) {
        callback(new Error('비밀번호는 9자 이상 16자 이하여야 합니다.'))
      }
      else if (!(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).$/.test(value))) {
        if (!/[a-z]/.test(value)) {
          callback(new Error('비밀번호는 영문 소문자를 포함해야합니다.'))
        }
        else if (!(/[A-Z]/).test(value)) {
          callback(new Error('비밀번호는 영문 대문자를 포함해야합니다.'))
        }
        else if (!(/[0-9]/).test(value)) {
          callback(new Error('비밀번호는 숫자를 포함해야합니다.'))
        }
        else if (!(/[$`~!@$!%*#^?&\\(\\)\-_=+]/).test(value)) {
          callback(new Error('비밀번호는 특수문자를 포함해야합니다.'))
        }
      }
    }

    const checkPasswordConfirmation = (rule, value, callback) => {
      if (value != state.form.password) {
        console.log(state.form.password, value)
        callback(new Error('비밀번호를 다시 입력해주세요.'))
      }
    }

    const checkDepartment = (rule, value, callback) => {
      if (value.length > 30) {
        callback(new Error('소속은 30자 이내여야 합니다.'))
      }
    }
    const checkPosition = (rule, value, callback) => {
      if (value.length > 30) {
        callback(new Error('직책은 30자 이내여야 합니다.'))
      }
    }
    const checkName = (rule, value, callback) => {
      if (value.length > 30) {
        callback(new Error('이름은 30자 이내여야 합니다.'))
      }
    }
    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        id: '',
        password: '',
        passwordConfirmation: '',
        department: '',
        position: '',
        name: '',
        align: 'left'
      },
      rules: {
        id: [
          { required: true, message: '아이디를 입력해주세요.', trigger: 'blur'},
          { validator: checkId, trigger: ['blur', 'change']}
        ],
        password: [
          { required: true, message: '비밀번호를 입력해주세요.', trigger: 'blur' },
          { validator: checkPassword, trigger: ['blur', 'change'] }
          // { regex: '[A-Za-z]+[0-9]+[!@#$%^&*-_]+', message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야 합니다.', trigger: 'blur'},
        ],
        passwordConfirmation: [
          { required: true, message: '비밀번호를 한 번 더 입력해주세요.', trigger: 'blur' },
          { validator: checkPasswordConfirmation, trigger: 'blur'}
        ],
        department: [
          { required: false },
          { validator: checkDepartment, trigger: 'blur'}
          // { maxLength: 30, message: '최대 30자까지 입력 가능합니다.', trigger: 'blur'},
        ],
        position: [
          { required: false },
          { validator: checkPosition, trigger: 'blur'}
          // { maxLength: 30, message: '최대 30자까지 입력 가능합니다.', trigger: 'blur'},
        ],
        name: [
          { required: true, message: '이름을 입력해주세요.', trigger: 'blur'},
          { validator: checkName, trigger: 'blur'}
          // { maxLength: 30, message: '최대 30자까지 입력 가능합니다.', trigger: 'blur'},
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(signupForm.value)
    })

    const clickSignup = function () {
      // 가입하기 클릭 시 validate 체크 후 그 결과 값에 따라, 회원가입 API 호출 또는 경고창 표시
      signupForm.value.validate((valid) => {
        if (valid) {
          console.log('submit')
          store.dispatch('root/requestSignup', { id: state.form.id, password: state.form.password, department: state.form.department, position: state.form.position, name: state.form.name })
          .then(function (result) {
            emit('closeSignupDialog')
          })
          .catch(function (err) {
            if (err.response.request.status == 500) {
              Swal.fire({
                title: '이런!',
                text: '서버 오류가 발생했습니다..',
                icon: 'error',
              })
            } else {
              Swal.fire({
                title: '이런!',
                text: '알 수 없는 이유로 회원가입에 실패 했습니다.',
                icon: 'error',
              })
            }
          })
        } else {
          Swal.fire({
            title: '이런!',
            text: '유효한 정보를 입력해주세요.',
            icon: 'error',
          })
        }
      });
    }

    const clickCheckId = function () {
      store.dispatch('root/requestCheckId', { id: state.form.id })
      .then(function (result) {
        Swal.fire({
          title: '성공!',
          text: '사용가능한 아이디입니다.',
          icon: 'success',
        })
      })
      .catch(function (err) {
        Swal.fire({
          title: '이런!',
          text: '서버 오류가 발생했습니다..',
          icon: 'error',
        })
      })
    }

    const handleClose = function () {
      state.form.id = ''
      state.form.password = ''
      state.form.passwordConfirmation = ''
      state.form.department = ''
      state.form.position = ''
      state.form.name = ''
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickSignup, clickCheckId, handleClose }
  }
}
</script>
