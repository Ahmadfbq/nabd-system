import axios from 'axios';

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
  error => Promise.reject(error)
);

// Response interceptor
api.interceptors.response.use(
  response => response,
  error => {
    // Handle common errors (401, 403, etc.)
    if (error.response) {
      // The request was made and the server responded with a status code
      // that falls out of the range of 2xx
      if (error.response.status === 401) {
        // Handle unauthorized (redirect to login, etc.)
        localStorage.removeItem('token');
        // Redirect to login page if using Vue Router
        // router.push('/login');
      }
    }
    return Promise.reject(error);
  }
);

export default api;