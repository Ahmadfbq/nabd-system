import axios from '@/utils/axios'

const healthDataService = {
  async getMeasurements() {
    try {
      const response = await axios.get('/measurements')
      return response.data
    } catch (error) {
      console.error('Error fetching measurements:', error)
      throw error
    }
  },

  async sendMeasurement(measurement) {
    try {
      const response = await axios.post('/measurements', measurement)
      return response.data
    } catch (error) {
      console.error('Error sending measurement:', error)
      throw error
    }
  }
}

export default healthDataService