import api from './api';

export default {
  getHealthData() {
    return api.get('/health_data/');
  },
  
  submitNewHealthData(data) {
    return api.post('/health_data/', data);
  },
  
  getHealthDataHistory() {
    return api.get('/health_data/history');
  }
};