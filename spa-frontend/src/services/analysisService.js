import api from './api';

export default {
  getAnalysisResults() {
    return api.get('/analysis/results');
  },
  
  requestAnalysis(data) {
    return api.post('/analysis/request', data);
  }
};