import api from './api'

const healthDataService = {
  async getMeasurements(params) {
    try {
      const response = await api.health.getMeasurements(params)
      return response.data
    } catch (error) {
      console.error('Error fetching measurements:', error)
      throw error
    }
  },

  async sendMeasurement(measurement) {
    try {
      const response = await api.health.sendMeasurement(measurement)
      return response.data
    } catch (error) {
      console.error('Error sending measurement:', error)
      throw error
    }
  },

  async pairDevice(deviceId) {
    try {
      const response = await api.device.pair(deviceId)
      return response.data
    } catch (error) {
      console.error('Error pairing device:', error)
      throw error
    }
  },

  async getPairedDevices() {
    try {
      const response = await api.device.getPairedDevices()
      return response.data
    } catch (error) {
      console.error('Error fetching paired devices:', error)
      throw error
    }
  },

  async unpairDevice(deviceId) {
    try {
      const response = await api.device.unpair(deviceId)
      return response.data
    } catch (error) {
      console.error('Error unpairing device:', error)
      throw error
    }
  },

  async getHealthInsights() {
    try {
      const response = await api.health.getHealthInsights()
      return response.data
    } catch (error) {
      console.error('Error fetching health insights:', error)
      throw error
    }
  },

  async getHealthTrends(params) {
    try {
      const response = await api.health.getHealthTrends(params)
      return response.data
    } catch (error) {
      console.error('Error fetching health trends:', error)
      throw error
    }
  }
}

export default healthDataService