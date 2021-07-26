// API
import $axios from 'axios'

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/auth/login'
  let body = payload
  return $axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/users'
  let body = payload
  return $axios.post(url, body)
}

export function requestCheckId ({ state }, payload) {
  const url = `users/${payload.id}`
  console.log(url, 'requestCheckId', state)
  return $axios.get(url)
}

export function requestProfile ({ state }) {
  console.log('requestProfile', state)
  const url = '/users/me'
  return $axios.post(url)
}