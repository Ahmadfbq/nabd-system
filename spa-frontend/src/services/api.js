import axios from '@/utils/axios'
import router from '../router';

// Create axios instance with default config
const api = {
  // Auth endpoints
  auth: {
    login: (credentials) => axios.post('/api/v1/auth/authenticate', credentials),
    register: (userData) => axios.post('/api/v1/auth/register', userData),
    refreshToken: () => axios.post('/api/v1/auth/refresh-token'),
    resetPassword: (email) => axios.post('/api/v1/auth/reset-password', { email }),
    changePassword: (data) => axios.post('/api/v1/auth/change-password', data)
  },

  // User endpoints
  user: {
    getProfile: () => axios.get('/api/v1/users/me'),
    updateProfile: (userId, data) => axios.put(`/api/v1/users/${userId}`, data),
    addEmergencyContact: (contact) => axios.post('/api/v1/emergency-contacts', contact),
    getEmergencyContact: () => axios.get('/api/v1/users/emergency-contact'),
    updateNotificationPreferences: (preferences) =>
      axios.put('/api/v1/users/notification-preferences', preferences),
    getNotificationPreferences: () =>
      axios.get('/api/v1/users/notification-preferences')
  },

  // Device endpoints
  device: {
    pair: (deviceId, userId) => {
      console.log('API: Calling pair endpoint with deviceId:', deviceId, 'userId:', userId)
      // Ensure deviceId is a number
      const numericDeviceId = Number(deviceId)
      const numericUserId = Number(userId)

      if (isNaN(numericDeviceId) || isNaN(numericUserId)) {
        return Promise.reject(new Error('Invalid device ID or user ID'))
      }

      return axios.post(`/api/v1/wearable-devices/${numericDeviceId}/pair/${numericUserId}`)
    },
    unpair: (deviceId) => {
      console.log('API: Calling unpair endpoint for deviceId:', deviceId)
      const numericDeviceId = Number(deviceId)
      if (isNaN(numericDeviceId)) {
        return Promise.reject(new Error('Invalid device ID'))
      }
      return axios.delete(`/api/v1/wearable-devices/${numericDeviceId}`)
    },
    getPairedDevices: () => {
      console.log('API: Fetching paired devices')
      return axios.get('/api/v1/wearable-devices')
    },
    getDeviceStatus: (deviceId) => {
      console.log('API: Getting status for deviceId:', deviceId)
      const numericDeviceId = Number(deviceId)
      if (isNaN(numericDeviceId)) {
        return Promise.reject(new Error('Invalid device ID'))
      }
      return axios.get(`/api/v1/wearable-devices/${numericDeviceId}/status`)
    }
  },

  // Health data endpoints
  health: {
    getMeasurements: (params) => axios.get('/api/v1/measurements', { params }),
    sendMeasurement: (data) => axios.post('/api/v1/measurements', data),
    getLatestMeasurements: () => axios.get('/api/v1/measurements/latest'),
    getMeasurementHistory: (params) =>
      axios.get('/api/v1/measurements/history', { params }),
    getHealthInsights: () => axios.get('/api/v1/health/insights'),
    getHealthTrends: (params) =>
      axios.get('/api/v1/health/trends', { params })
  },

  // Analysis endpoints
  analysis: {
    getSleepAnalysis: (params) =>
      axios.get('/api/v1/analysis/sleep', { params }),
    getHealthAnalysis: (params) =>
      axios.get('/api/v1/analysis/health', { params }),
    getTrends: (params) =>
      axios.get('/api/v1/analysis/trends', { params })
  },

  // Notification endpoints
  notification: {
    getNotifications: () => axios.get('/api/v1/notifications'),
    markAsRead: (notificationId) =>
      axios.put(`/api/v1/notifications/${notificationId}/read`),
    markAllAsRead: () =>
      axios.put('/api/v1/notifications/read-all'),
    deleteNotification: (notificationId) =>
      axios.delete(`/api/v1/notifications/${notificationId}`),
    getUnreadCount: () =>
      axios.get('/api/v1/notifications/unread/count')
  }
}

// Add response type definitions
export const ApiResponse = {
  SUCCESS: 'success',
  ERROR: 'error',
  LOADING: 'loading'
};

console.log('API service being exported:', api)

export default api;
