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
    updateProfile: (data) => axios.put('/api/v1/users/me', data),
    updateEmergencyContact: (data) => axios.put('/api/v1/users/emergency-contact', data),
    getEmergencyContact: () => axios.get('/api/v1/users/emergency-contact'),
    updateNotificationPreferences: (preferences) => 
      axios.put('/api/v1/users/notification-preferences', preferences),
    getNotificationPreferences: () => 
      axios.get('/api/v1/users/notification-preferences')
  },

  // Device endpoints
  device: {
    pair: (deviceId) => axios.post('/api/v1/devices/pair', { deviceId }),
    unpair: (deviceId) => axios.delete(`/api/v1/devices/${deviceId}`),
    getPairedDevices: () => axios.get('/api/v1/devices'),
    getDeviceStatus: (deviceId) => axios.get(`/api/v1/devices/${deviceId}/status`)
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

// Request interceptor
api.interceptors.request.use(
  config => {
    // Get token from localStorage if you're using JWT
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    console.error('Request error:', error);
    return Promise.reject(error);
  }
);

// Response interceptor
api.interceptors.response.use(
  response => response,
  error => {
    if (error.response) {
      const { status } = error.response;
      
      switch (status) {
        case 401:
          // Unauthorized - clear token and redirect to login
          localStorage.removeItem('token');
          router.push('/login');
          break;
        case 403:
          // Forbidden - redirect to access denied page
          router.push('/access-denied');
          break;
        case 404:
          // Not found - redirect to 404 page
          router.push('/not-found');
          break;
        case 500:
          // Server error - show error message
          console.error('Server error:', error.response.data);
          break;
        default:
          console.error('API error:', error.response.data);
      }
    } else if (error.request) {
      // The request was made but no response was received
      console.error('No response received:', error.request);
    } else {
      // Something happened in setting up the request
      console.error('Request setup error:', error.message);
    }
    
    return Promise.reject(error);
  }
);

// Add response type definitions
export const ApiResponse = {
  SUCCESS: 'success',
  ERROR: 'error',
  LOADING: 'loading'
};

export default api;