<template>
  <el-dialog custom-class="secession-dialog" title="계정 탈퇴" v-model="state.dialogVisible" @close="handleClose">
    <span>진심으로 탈퇴를 원하신다면 비밀번호를 다시 한번 입력해주세요.</span>
    <el-form :model="state.form" :rules="state.rules" ref="secessionForm" :label-position="state.form.align">
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="clickSecessionConfirm">Confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style>
.secession-dialog {
  width: 400px;
  height: 300px;
}
.secession-dialog .el-dialog__headerbtn {
  float: right;
}
.secession-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.secession-dialog .el-form-item {
  margin-bottom: 20px;
}
.secession-dialog .el-form-item__error {
  margin-top: 5px;
  font-size: 12px;
  color: red;
}
.secession-dialog .el-input__suffix {
  display: none;
}
.secession-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.secession-dialog .dialog-footer .el-button {
  width: 120px;
}
/* 얼럿창 화면 맨 앞으로 */
.swal2-container {
  z-index: 5000;
}
</style>

<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'secession-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }){
    const store = useStore()
    const router = useRouter()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함 <return>
    const secessionForm = ref(null)

    // Element UI Validator
    // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
    const state = reactive({
      form: {
        password: '',
        align: 'left'
      },
      rules: {
        password: [
          { required: true, message: '비밀번호를 입력해주세요.', trigger: 'blur'}
        ],
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(loginForm.value)
    })

    const clickSecessionConfirm = function() {
      secessionForm.value.validate((valid) => {
        if (valid){
          console.log(state.form.password)
          store.dispatch('rootMain/deleteUser', { "password" : state.form.password }, localStorage.getItem('jwt'))
          .then(function (result) {
            localStorage.removeItem('jwt')
            localStorage.removeItem('id')
            localStorage.removeItem('classId')
            localStorage.removeItem('name')
            localStorage.removeItem('profileImgUrl')
            localStorage.removeItem('teacherProfileImgUrl')
            localStorage.removeItem('position')
            localStorage.removeItem('sex')
            localStorage.removeItem('snum')
            localStorage.removeItem('teacherName')
            emit('closeSecessionDialog')
            router.go()
            Swal.fire({
                  title: '성공!',
                  text: '계정이 삭제되었습니다..',
                  icon: 'error',
            }) // 같이 쓸수있으려나,,
          })
          .catch(function (err) {
            const status = err.response.request.status
            if (status == 500) {
                Swal.fire({
                  title: '이런!',
                  text: '서버 오류가 발생했습니다..',
                  icon: 'error',
                })
              } else {
                Swal.fire({
                  title: '이런!',
                  text: '계정탈퇴에 실패했습니다..',
                  icon: 'error',
                })
              }
          })
        } else{
          Swal.fire({
            title: '실패!',
            text: '비밀번호를 입력해주세요.',
            icon: 'error',
          })
        }
      });
    }

    const handleClose = function () {
      state.form.password = ''
      emit('closeSecessionDialog')
    }

    return { secessionForm, state, clickSecessionConfirm, handleClose }
  }
}

</script>
