import axios from 'axios'
import router from '@/router'

// Create axios instance
const instance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

// Request interceptor
instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('accessToken')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// Response interceptor
instance.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config

    // If error is 401 and we haven't tried to refresh token yet
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true

      try {
        // Try to refresh token
        const refreshToken = localStorage.getItem('refreshToken')
        if (!refreshToken) {
          throw new Error('No refresh token available')
        }

        const response = await axios.post('http://localhost:8080/api/v1/auth/refresh-token', null, {
          headers: {
            Authorization: `Bearer ${refreshToken}`
          }
        })

        // Update tokens
        if (response.data.accessToken && response.data.refreshToken) {
          localStorage.setItem('accessToken', response.data.accessToken)
          localStorage.setItem('refreshToken', response.data.refreshToken)

          // Retry original request with new token
          originalRequest.headers.Authorization = `Bearer ${response.data.accessToken}`
          return instance(originalRequest)
        } else {
          throw new Error('Invalid token response')
        }
      } catch (refreshError) {
        // If refresh fails, logout and redirect to login
        localStorage.removeItem('accessToken')
        localStorage.removeItem('refreshToken')
        router.push('/auth/login')
        return Promise.reject(refreshError)
      }
    }

    return Promise.reject(error)
  }
)

export default instance 