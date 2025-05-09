import api from './api';

export const healthDataService = {
  // Get health measurements
  async getMeasurements(timeRange = 'week') {
    try {
      const response = await api.get(`/health_data/?timeRange=${timeRange}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching health measurements:', error);
      throw error;
    }
  },

  // Get latest measurements
  async getLatestMeasurements() {
    try {
      const response = await api.get('/health_data/latest');
      return response.data;
    } catch (error) {
      console.error('Error fetching latest measurements:', error);
      throw error;
    }
  },

  // Get health insights
  async getHealthInsights() {
    try {
      const response = await api.get('/health_data/insights');
      return response.data;
    } catch (error) {
      console.error('Error fetching health insights:', error);
      throw error;
    }
  },

  // Add new measurement
  async addMeasurement(measurement) {
    try {
      const response = await api.post('/health_data/', measurement);
      return response.data;
    } catch (error) {
      console.error('Error adding measurement:', error);
      throw error;
    }
  },

  // Get measurement statistics
  async getStatistics(timeRange = 'week') {
    try {
      const response = await api.get(`/health_data/statistics?timeRange=${timeRange}`);
      return response.data;
    } catch (error) {
      console.error('Error fetching statistics:', error);
      throw error;
    }
  }
};