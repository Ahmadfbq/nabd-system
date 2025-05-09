import axios from 'axios';
import router from '../router';

// Create axios instance with default config
const api = axios.create({
  baseURL: '/api', // This will go through Nginx
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
});

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