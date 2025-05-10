import api from './api'

const analysisService = {
  async getSleepAnalysis(params) {
    try {
      const response = await api.analysis.getSleepAnalysis(params)
      return response.data
    } catch (error) {
      console.error('Error fetching sleep analysis:', error)
      throw error
    }
  },

  async getHealthInsights() {
    try {
      const response = await api.analysis.getHealthInsights()
      return response.data
    } catch (error) {
      console.error('Error fetching health insights:', error)
      throw error
    }
  },

  async getHealthTrends(params) {
    try {
      const response = await api.analysis.getHealthTrends(params)
      return response.data
    } catch (error) {
      console.error('Error fetching health trends:', error)
      throw error
    }
  },

  async getAnomalies(params) {
    try {
      const response = await api.analysis.getAnomalies(params)
      return response.data
    } catch (error) {
      console.error('Error fetching anomalies:', error)
      throw error
    }
  },

  async getRecommendations() {
    try {
      const response = await api.analysis.getRecommendations()
      return response.data
    } catch (error) {
      console.error('Error fetching recommendations:', error)
      throw error
    }
  }
}

export default analysisService