import api from './api';

export default {
  login(credentials) {
    return api.post('/user/login', credentials);
  },
  
  register(userData) {
    return api.post('/user/register', userData);
  },
  
  getUserProfile() {
    return api.get('/user/profile');
  },
  
  updateUserProfile(userData) {
    return api.put('/user/profile', userData);
  }
};