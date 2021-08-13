// API
import $axios from 'axios'

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  console.log(state)
  const url = '/api/login'
  let body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/api/sign-up'
  let body = payload
  return $axios.post(url, body)
}

export function requestCheckId ({ state }, payload) {
  console.log(url, 'requestCheckId', state, payload)
  const url = `/api/sign-up/${payload.id}`
  return $axios.get(url)
}

export function requestProfile ({ state }) {
  console.log('requestProfile', state)
  const url = '/users/me'
  return $axios.post(url)
}
