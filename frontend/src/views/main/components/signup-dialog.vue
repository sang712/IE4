<template>
  <el-dialog custom-class="signup-dialog" title="회원가입" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
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
  height: 550px;
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
          { required: true, message: 'Please input ID', trigger: 'blur' },
          // { maxLength: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'blur'},
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' },
          // { minLength: 9, message: '최소 9글자를 입력해야 합니다.', trigger: 'blur'},
          // { maxLength: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'blur'},
          // { regex: '[A-Za-z]+[0-9]+[!@#$%^&*-_]+', message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야 합니다.', trigger: 'blur'},
        ],
        passwordConfirmation: [
          { required: true, message: 'Please input passwordConfirmation', trigger: 'blur' },
        ],
        department: [
          { required: false },
          // { maxLength: 30, message: '최대 30자까지 입력 가능합니다.', trigger: 'blur'},
        ],
        position: [
          { required: false },
          // { maxLength: 30, message: '최대 30자까지 입력 가능합니다.', trigger: 'blur'},
        ],
        name: [
          { required: true, message: 'Please input name', trigger: 'blur'},
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

    const handleClose = function () {
      state.form.id = ''
      state.form.password = ''
      state.form.passwordConfirmation = ''
      state.form.department = ''
      state.form.position = ''
      state.form.name = ''
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickSignup, handleClose }
  }
}
</script>
