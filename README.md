# 프로젝트 명

## 목차

[TOC]



## 프로젝트 소개

본 공통 프로젝트는 "그룹 비디오 컨퍼런스 서비스"로, WebRTC 기술을 활용하여 그룹 화상 회의 기능을 가진 반응형 웹 서비스를 개발하는 것입니다.
본격적인 공통프로젝트를 개발하기 앞서 실전형 프로젝트에 적응하고 구현을 연습하는 목적으로써 Sub PJT 1을 진행하게 되었습니다.

작업은 프론트와 백으로 나뉘어 작업을 진행하였으며, 각 그룹별로 같이 서로 의논하면서 진행했고, 서로 통신하는 부분과 필요한 사항, 요청 사항 등은 계속해서 소통하며 진행하였습니다.

<br>

## 프로젝트 명세

### 배포 환경

- __URL__ : 
- __배포 여부__ : X
- ~~__접속 가능__ : 수정 중~~
- ~~__HTTPS 적용__ : X~~
- ~~__PORT__ : // 3rd Party에서 사용하는 포트가 있다면 기입해주세요. <- 기입 후 해당 주석 삭제~~
  <br>

### 개발 환경

#### Front-end

- __Framework__ : Vue.js(Vue 3)
- __지원 환경__ : Web 
- __담당자__ : 팀장, 한상길, 팀원 강현구
  <br>

#### Back-end

- __Framework__ : Spring boot
- __Database__ : MySQL
- __담당자__ : 팀원 김윤주, 팀원 이효진, 팀원 임아연
  <br>

#### ~~Design~~

- ~~__Framework 사용__ : X~~
  - ~~[Vuetify](https://vuetifyjs.com/)~~
  - ~~[Element Plus](https://element-plus.org/)~~
- ~~__Design Tool 사용__ :~~
- ~~__담당자__ :~~
  <br>

### Design Resources

__외부 템플릿 또는 에셋__ (이미지 또는 링크 첨부)

- 무료 이미지, 아이콘, 폰트 등은 제외



~~__자체 제작 산출물__ (필요시 이미지 또는 설명 첨부)~~

- ~~LOGO~~
- ~~CardView~~
- ~~Button~~
- ~~Calendar~~
  <br>

### 프로젝트 명세



### ~~핵심 라이브러리~~

~~기본 제공하는 라이브러리 외 핵심 기능 구현에 사용한 라이브러리가 있다면 작성해주세요.   
예시 ) VR/AR 라이브러리, 애니메이션 라이브러리, 텍스트/사진/동영상 지원, 편집 라이브러리 등~~

- ~~__AR CORE__~~
  - ~~__링크__ : https://developers.google.com/ar~~
  - ~~__소개__ : 구글에서 제공하는 AR 지원 라이브러리~~
  - ~~__사용 기능__ : 이미지 인식, 이미지 위 영상 표시~~
  - ~~__담당자__ : 팀원 A, ~~

- ~~__COLOR THIEF__~~
  - ~~__링크__ : https://lokeshdhakar.com/projects/color-thief/~~
  - ~~__소개__ : 이미지에서 색상을 추출해주는 라이브러리~~
  - ~~__사용 기능__ : 커버 사진에서 색상 추출 -> 배경 색상 변경~~
  - ~~__담당자__ : 팀원 A,~~

- ~~__Animate.css__~~
  - ~~__링크__ : https://animate.style/~~
  - ~~__소개__ : css 애니메이션 지원 라이브러리~~
  - ~~__사용 기능__ : 메인 페이지 진입 시 애니메이션 사용~~
  - ~~__담당자__ : 팀원 A,~~



## 시행착오

### 프론트엔드

#. 애증의 유효성검사1

애초에 스켈레톤 코드가 element plus 라이브러리를 사용하고 있어서 이것을 토대로 form을 만들었는데 여기에 유효성 검사를 적용하는 방법이 아무리 찾아도 나오질 않아 [엘레멘트 플러스 깃헙(폼)](https://github.com/element-plus/element-plus/blob/dev/website/docs/en-US/form.md) 사이트에서 코드를 찾아서 적용하게 되었다. 

그리하야 디자인 적으로도 기능적으로도 만족스러운 유효성 검사를 완성하였지만...



#. 애증의 유효성검사2

유효성 검사를 여러개 넣게되면 element plus에서 제공하는 유효성 검사 함수가 제대로 작동하지 않는다. 그래서 생각해낸 한 가지 방법이 모든 필드당 하나의 유효성 검사를 넣는 것이다. 다만 이렇게 하면 required 검사 항목을 빼게되어서 UI측면에서 필드 이름 왼쪽에 빨간* 가 삭제되기 때문에 이 방법은 고사되었다. 다른 방법은 강구중이다. 

해결 방법을 찾았는데 문법상의 실수였다.  유효성 검사를 if분기로 처리해주는데 각 분기마다 callback 함수가 들어가야 한다. 그렇지만 if 분기를 모두 타고도 벗어나는 분기(else)에서 callback을 하지 않아 생긴 오류였다. else 분기는 유효성 검사를 통과한 결과라고 생각해서 callback이 필요없다고 생각했는데 모든 분기에 callback이 들어가야하며 하다못해 빈 callback을 넣어 주어야 했다. 이 부분도 [사용 예시(Form-Custom validation rules)](https://element-plus.org/#/en-US/component/form)에 나와 있었다.



#. axios에 headers 추가하기

jwt토큰을 이용하게 되었고 협의 끝에 개인 정보는 토큰을 통해 확인하기로 결정했다. 그렇지만...

headers에 이리 저리 넣어 봐도 500 에러가 발생하여 응답을 전혀 제대로 받을 수 없었다. 현재 { headers: { Authorization: jwt } } 와 { headers: { Authorization: (백틱)bearer + ${jwt}(백틱) }} 같은 두가지 방법을 이용하였지만 전혀 진척이 없다.

[참고한 웹사이트](https://www.codegrepper.com/code-examples/javascript/axios+send+jwt+token+header)

많은 웹사이트들이 참고했던 웹사이트 처럼 `bearer`를 붙이라고 알려 주어서 추가를 했었고 그래도 되질 않아 그짓말을 올린 블로그라고 생각했다. 하지만 `bearer`를 추가할 때 `+`를 백틱 내부에 쓰는 오류를 범해 해결이 되지 않았던 것이었다. 작성된 코드에서 `+`를 빼든 bearer와 jwt를 따로따로 묶고 +를 추가하든 했어야 했다. 결국 +를 빼니 응답이 잘 온다.



### 백엔드

#. user delete Required request body is missing 오류

@DeleteMapping에서 payload body를 실어서 @RequestBody로 받아올시 요청이 거절될 수 있다. <br>

참고 사이트 : http://blog.leekyoungil.com/?p=390

=> @PostMapping으로 고쳐주었다.

#. 탈퇴시 user와 관련된 user_point 테이블과 student table에 있는 user 정보들도 모두 없애줘야 한다. 

