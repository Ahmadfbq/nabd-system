import api from './api';

export const analysisService = {
  // Get sleep analysis data
  async getSleepAnalysis(timeRange = 'week') {
    try {
      const response = await api.get(`/analysis/sleep/?timeRange=${timeRange}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching sleep analysis:', error);
      throw error;
    }
  },

  // Get sleep statistics
  async getSleepStatistics(timeRange = 'week') {
    try {
      const response = await api.get(`/analysis/sleep/statistics?timeRange=${timeRange}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching sleep statistics:', error);
      throw error;
    }
  },

  // Get sleep stages
  async getSleepStages(date) {
    try {
      const response = await api.get(`/analysis/sleep/stages?date=${date}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching sleep stages:', error);
      throw error;
    }
  },

  // Get sleep quality factors
  async getSleepQualityFactors(date) {
    try {
      const response = await api.get(`/analysis/sleep/quality-factors?date=${date}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching sleep quality factors:', error);
      throw error;
    }
  },

  // Get sleep pattern
  async getSleepPattern(timeRange = 'week') {
    try {
      const response = await api.get(`/analysis/sleep/pattern?timeRange=${timeRange}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching sleep pattern:', error);
      throw error;
    }
  },

  getAnalysisResults() {
    return api.get('/analysis/results');
  },
  
  requestAnalysis(data) {
    return api.post('/analysis/request', data);
  }
};