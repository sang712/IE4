<template>
  <el-dialog custom-class="login-dialog" title="로그인" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
      <el-form-item prop="loginId" label="아이디" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.loginId" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="clickLogin">로그인</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'login-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },
  emits: ['closeLoginDialog'],
  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const loginForm = ref(null)

    // Element UI Validator
    // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
    const state = reactive({
      form: {
        loginId: '',
        password: '',
        align: 'left'
      },
      rules: {
        loginId: [
          { required: true, message: '아이디를 입력해주세요.', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '비밀번호를 입력해주세요.', trigger: 'blur' }
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(loginForm.value)
    })

    const clickLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestLogin', { loginId: state.form.loginId, password: state.form.password })
          .then(function (result) {
            localStorage.setItem('jwt', result.data.accessToken)
            localStorage.setItem('id', result.data.id)
            localStorage.setItem('classId', result.data.classId)
            localStorage.setItem('name', result.data.name)
            localStorage.setItem('profileImgUrl', result.data.profileImgUrl)
            localStorage.setItem('position', result.data.position == null ? '학생' : result.data.position)
            localStorage.setItem('sex', result.data.sex)
            if(result.data.position == '학생') {
              localStorage.setItem('snum', result.data.snum)
              localStorage.setItem('teacherName', result.data.teacherName)
              localStorage.setItem('teacherProfileImgUrl', result.data.teacherProfileImgUrl)
            }
            emit('closeLoginDialog')
            router.go();
          })
          .catch(function (err) {
            const status = err.response.request.status
            if (status == 500) {
              Swal.fire({
                title: '이런!',
                text: '서버 오류가 발생했습니다..',
                icon: 'error',
              })
            } else if (status == 404) {
              Swal.fire({
                title: '이런!',
                text: '존재하지 않는 계정입니다.',
                icon: 'error',
              })
            } else if (status == 401) {
              Swal.fire({
                title: '이런!',
                text: '잘못된 비밀번호 입니다.',
                icon: 'error',
              })
            }
          })
        } else {
          Swal.fire({
            title: '이런!',
            text: '로그인에 실패했습니다.',
            icon: 'error',
          })
        }
      });
    }

    const handleClose = function () {
      state.form.loginId = ''
      state.form.password = ''
      emit('closeLoginDialog')
    }

    return { loginForm, state, clickLogin, handleClose }
  }
}
</script>
<style>

.login-dialog {
  width: 400px;
  height: 300px;
}
.login-dialog .el-dialog__headerbtn {
  float: right;
}
.login-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.login-dialog .el-form-item {
  margin-bottom: 20px;
}
.login-dialog .el-form-item__error {
  margin-top: 5px;
  font-size: 12px;
  color: red;
}
.login-dialog .el-input__suffix {
  display: none;
}
.login-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.login-dialog .dialog-footer .el-button {
  width: 120px;
}
/* 얼럿창 화면 맨 앞으로 */
.swal2-container {
  z-index: 5000;
}
</style>
