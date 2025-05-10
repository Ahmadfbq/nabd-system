import axios from '@/utils/axios'

const analysisService = {
  async getSleepAnalysis() {
    try {
      const response = await axios.get('/analysis/sleep')
      return response.data
    } catch (error) {
      console.error('Error fetching sleep analysis:', error)
      throw error
    }
  },

  async getHealthInsights() {
    try {
      const response = await axios.get('/analysis/health-insights')
      return response.data
    } catch (error) {
      console.error('Error fetching health insights:', error)
      throw error
    }
  },

  async getTrends(timeRange = 'week') {
    try {
      const response = await axios.get(`/analysis/trends?timeRange=${timeRange}`)
      return response.data
    } catch (error) {
      console.error('Error fetching trends:', error)
      throw error
    }
  }
}

export default analysisService