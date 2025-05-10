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
  },

  async pairDevice(deviceId) {
    try {
      const response = await axios.post('/devices/pair', { deviceId })
      return response.data
    } catch (error) {
      console.error('Error pairing device:', error)
      throw error
    }
  },

  async getPairedDevices() {
    try {
      const response = await axios.get('/devices')
      return response.data
    } catch (error) {
      console.error('Error fetching paired devices:', error)
      throw error
    }
  },

  async unpairDevice(deviceId) {
    try {
      const response = await axios.delete(`/devices/${deviceId}`)
      return response.data
    } catch (error) {
      console.error('Error unpairing device:', error)
      throw error
    }
  }
}

export default healthDataService