import axios from 'axios'
import router from '@/router'
import userService from '@/services/userService'

// Create axios instance
const instance = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

// Request interceptor
instance.interceptors.request.use(
  (config) => {
    const token = userService.getAccessToken()
    console.log('Request interceptor - Token:', token ? 'Present' : 'Missing')
    console.log('Request URL:', config.url)
    console.log('Request Method:', config.method)
    
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
      console.log('Authorization header set:', config.headers.Authorization)
    } else {
      console.warn('No access token available for request')
    }
    return config
  },
  (error) => {
    console.error('Request interceptor error:', error)
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
        const currentRefreshToken = userService.getRefreshToken()
        if (!currentRefreshToken) {
          console.error('No refresh token available')
          throw new Error('No refresh token available')
        }

        // Use the instance for refresh token request
        const response = await instance.post('/api/v1/auth/refresh-token', null, {
          headers: {
            Authorization: `Bearer ${currentRefreshToken}`
          }
        })

        console.log('Refresh token response:', response.data)

        // Check for both camelCase and snake_case token properties
        const newAccessToken = response.data.accessToken || response.data.access_token
        const newRefreshToken = response.data.refreshToken || response.data.refresh_token

        // Update tokens
        if (newAccessToken && newRefreshToken) {
          localStorage.setItem('accessToken', newAccessToken)
          localStorage.setItem('refreshToken', newRefreshToken)

          // Retry original request with new token
          originalRequest.headers.Authorization = `Bearer ${newAccessToken}`
          return instance(originalRequest)
        } else {
          console.error('Invalid token response:', response.data)
          throw new Error('Invalid token response')
        }
      } catch (refreshError) {
        console.error('Token refresh failed:', refreshError)
        // If refresh fails, logout and redirect to login
        await userService.logout()
        router.push('/auth/login')
        return Promise.reject(refreshError)
      }
    }

    // Log other errors
    if (error.response) {
      console.error('API Error:', {
        status: error.response.status,
        data: error.response.data,
        config: error.config
      })
    } else if (error.request) {
      console.error('No response received:', error.request)
    } else {
      console.error('Error setting up request:', error.message)
    }

    return Promise.reject(error)
  }
)

export default instance 