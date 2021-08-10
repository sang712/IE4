<template>
  <el-dialog custom-class="signup-dialog" title="회원가입" v-model="state.dialogVisible" @close="handleClose">
    <el-form class="signup-form" :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-scrollbar height="400px" style="display: flex; justify-content: center;">
        <div style="margin: 0px;">
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
        <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="grade" label="학년 / 반 / 번호" :label-width="state.formLabelWidth">
          <el-select v-model="state.form.grade" placeholder="학년" style="width: 35%">
            <el-option label="1학년" value="1"></el-option>
            <el-option label="2학년" value="2"></el-option>
            <el-option label="3학년" value="3"></el-option>
            <el-option label="4학년" value="4"></el-option>
            <el-option label="5학년" value="5"></el-option>
            <el-option label="6학년" value="6"></el-option>
          </el-select>
          <el-select v-model="state.form.class_no" placeholder="반" style="width: 30%">
            <el-option label="1반" value="01"></el-option>
            <el-option label="2반" value="02"></el-option>
            <el-option label="3반" value="03"></el-option>
            <el-option label="4반" value="04"></el-option>
            <el-option label="5반" value="05"></el-option>
            <el-option label="6반" value="06"></el-option>
          </el-select>
          <el-input v-model="state.form.snum" placeholder="번호" style="width: 35%"></el-input>
        </el-form-item>
        <el-form-item prop="sex" label="성별" :label-width="state.formLabelWidth">
          <el-radio-group v-model="state.form.sex">
            <el-radio label="남">남</el-radio>
            <el-radio label="여">여</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phone" label="휴대전화" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.phone"></el-input>
        </el-form-item>
        <el-form-item prop="address" label="주소" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.address"></el-input>
        </el-form-item>
        <el-form-item prop="password_question" label="비밀번호 질문" :label-width="state.formLabelWidth">
          <el-select v-model="state.form.password_question" placeholder="질문을 선택하세요." style="width: 100%">
            <el-option v-for="item in state.passQuestions" :key="item.question" :label="item.question" :value="item.question"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="password_answer" label="비밀번호 답변" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.password_answer"></el-input>
        </el-form-item>
      </el-scrollbar>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="clickSignup">가입하기</el-button>
      </span>
    </template>
  </el-dialog>
</template>
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
  emits: ['closeSignupDialog'],
  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)

    const checkId = (rule, value, callback) => {
      if (value.length > 20) callback(new Error('아이디는 20자 이내여야 합니다.'))
      callback()
    }

    const checkPassword = (rule, value, callback) => {
      if (value.length < 8 || value.length > 16) {
        callback(new Error('비밀번호는 8자 이상 16자 이하여야 합니다.'))
      }
      else if (!(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).$/.test(value))) {
        // if (!/[a-z]/.test(value)) callback(new Error('비밀번호는 영문 소문자를 포함해야합니다.'))}
        // else if (!(/[A-Z]/).test(value)) callback(new Error('비밀번호는 영문 대문자를 포함해야합니다.'))
        if (!(/[a-z]|[A-Z]/).test(value)) callback(new Error('비밀번호는 영문을 포함해야 합니다.'))
        else if (!(/[0-9]/).test(value)) callback(new Error('비밀번호는 숫자를 포함해야합니다.'))
        else if (!(/[$`~!@$!%*#^?&\\(\\)\-_=+]/).test(value)) callback(new Error('비밀번호는 특수문자를 포함해야합니다.'))
      }
      callback()
    }

    const checkPasswordConfirmation = (rule, value, callback) => {
      if (value != state.form.password) callback(new Error('비밀번호를 다시 입력해주세요.'))
      callback()
    }
    const checkName = (rule, value, callback) => {
      if (value.length > 10) callback(new Error('이름은 10자 이내여야 합니다.'))
      callback()
    }
    const checkSnum = (rule, value, callback) => {
      if (0 < value <= 99) callback(new Error('번호는 1이상의 숫자여야 합니다.'))
      callback()
    }

    const checkPhone = (rule, value, callback) => {
      if (!(/^010\d{3,4}\d{4}$/.test(value))) {
        if(!(/^010/.test(value))) callback(new Error('휴대전화번호는 010으로 시작해야 합니다.'))
        else if (/-/.test(value)) callback(new Error('-없이 입력해주세요.'))
        else callback(new Error('올바른 휴대전화번호를 입력해주세요.'))
      }
      callback()
    }
    const checkAddress = (rule, value, callback) => {
      if (value.length > 50) callback(new Error('주소를 50자 이내로 입력해주세요.'))
      callback()
    }
    const checkPassAnswer = (rule, value, callback) => {
      if (value.length < 2) callback(new Error('비밀번호 답변은 2자 이상이어야 합니다.'))
      callback()
    }

    // Element UI Validator
    // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
    const state = reactive({
      passQuestions: [
        { question: "가장 좋아하는 색깔은?" },
        { question: "키우고 있는 애완동물의 이름은?" },
        { question: "가장 친한 친구의 이름은?" },
        { question: "이효진의 별명은?" },
        { question: "김윤주의 별명은?" },
        { question: "임아연의 별명은?" },
        { question: "강현구의 별명은?" },
      ],
      form: {
        id: '',
        password: '',
        passwordConfirmation: '',
        name: '',
        grade: '',
        class_no: '',
        snum: '',
        sex: '',
        phone: '',
        address: '',
        password_question: '',
        password_answer: '',
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
        ],
        passwordConfirmation: [
          { required: true, message: '비밀번호를 한 번 더 입력해주세요.', trigger: 'blur' },
          { validator: checkPasswordConfirmation, trigger: 'blur'}
        ],
        name: [
          { required: true, message: '이름을 입력해주세요.', trigger: 'blur'},
          { validator: checkName, trigger: 'blur'}
        ],
        grade: [
          { required: true, message: '학년 / 반 / 번호를 입력하세요.', trigger: 'blur'},
        ],
        class_no: [
          { required: true, message: '반을 입력하세요.', trigger: 'blur'},
        ],
        snum: [
          { required: true, message: '번호를 입력하세요.', trigger: 'blur'},
          { validator: checkSnum, trigger: ['blur', 'change'] }
        ],
        sex: [
          { required: true, message: '성별을 체크하세요.', trigger: ['blur', 'change']},
        ],
        phone: [
          { required: true, message: '연락처를 입력하세요.', trigger: 'blur'},
          { validator: checkPhone, trigger: 'blur'}
        ],
        address: [
          { required: true, message: '주소를 입력해주세요.', trigger: 'blur'},
          { validator: checkAddress, trigger: 'blur'}
        ],
        password_question: [
          { required: true, message: '비밀번호확인 질문을 선택해주세요.', trigger: ['blur', 'change']},
        ],
        password_answer: [
          { required: true, message: '비밀번호확인 답변을 입력해주세요.', trigger: 'blur'},
          { validator: checkPassAnswer, trigger: 'blur'}
        ],
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(signupForm.value)
    })

    const clickSignup = function () {
      // console.log('폼', signupForm.value.model)
      // 가입하기 클릭 시 validate 체크 후 그 결과 값에 따라, 회원가입 API 호출 또는 경고창 표시
      signupForm.value.validate((valid, object) => {
        // console.log('유효성체크완료', signupForm.value.model)
        if (valid) {
          console.log('submit')
          store.dispatch('root/requestSignup', {
            loginId: state.form.id,
            password: state.form.password,
            name: state.form.name,
            classId: state.form.grade + state.form.class_no,
            snum: state.form.snum,
            sex: state.form.sex,
            parentPhone: state.form.phone,
            address: state.form.address,
            passwordQuestion: state.form.password_question,
            passwordAnswer: state.form.password_answer,
          })
          .then(function (result) {
            console.log(result)
            emit('closeSignupDialog')
            Swal.fire({
              title: '성공!',
              text: '회원가입이 완료되었습니다.',
              icon: 'success',
            })
          })
          .catch(function (err) {
            console.log('에러발생', err.response.request.status)
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
          console.log(valid)
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
        if (err.response.request.status == 409) {
          Swal.fire({
            title: '아이고!',
            text: '중복된 아이디가 있습니다.',
            icon: 'error',
          })
        } else {
          Swal.fire({
            title: '이런!',
            text: '서버 오류가 발생했습니다..',
            icon: 'error',
          })
        }
      })
    }

    const handleClose = function () {
      state.form.id = ''
      state.form.password = ''
      state.form.passwordConfirmation = ''
      state.form.name = ''
      state.form.grade = ''
      state.form.class_no = ''
      state.form.snum = ''
      state.form.sex = ''
      state.form.phone = ''
      state.form.address = ''
      state.form.password_question = ''
      state.form.password_answer = ''
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickSignup, clickCheckId, handleClose }
  }
}
</script>
<style>
@import url("https://fonts.googleapis.com/css?family=Fjalla+One&display=swap");
* {
  margin: 0;
  padding: 0; }

.signup-form {
  width: 25vw;
  min-width: 420px;
}

.el-scrollbar__view {
  display: inline;
  align-items: center;
}

.el-form-item {
  display: flex;
  width: 100%;
  font-size: 14pt;
  line-height: 28pt;
  font-family: "Fjalla One";
  margin-bottom: 28pt;
  border: none;
  border-bottom: 2px solid black;
  border-radius: 5px;
  background: #f8f4e5;
  min-width: 250px;
  outline: none;
  color: black; 
}

input:focus {
  border-bottom: 5px solid #ffa580; }

button {
  letter-spacing: 2px;
  box-shadow: 3px 3px 1px #95a4ff, 3px 3px 1px 1px black; }
button:hover {
  background: black;
  color: white;
  border: 1px solid black; }

::selection {
  background: #ffc8ff; }

input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus {
  border-bottom: 5px solid #95a4ff;
  -webkit-text-fill-color: #2A293E;
  -webkit-box-shadow: 0 0 0px 1000px #f8f4e5 inset;
  transition: background-color 5000s ease-in-out 0s; }

.el-dialog__header {
  background: rgba(255, 255, 255, 0.80);
  padding: 10px 20px !important;
  margin: 10px;
  border-radius: 10px;
}
.el-dialog__body {
  background: rgba(255, 255, 255, 0.80);
  padding: 20px !important;
  margin-bottom: 10px;
  border-radius: 20px;
}
.el-dialog__body form{
  opacity: 1.0;
}
.signup-dialog .el-form-item__error {
  margin-top: 5px;
  font-size: 12px;
  color: red;
}

.signup-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.signup-dialog .dialog-footer .el-button {
  width: 120px;
}
/* 얼럿창 화면 맨 앞으로 */
.swal2-container {
  z-index: 5000;
}
</style>