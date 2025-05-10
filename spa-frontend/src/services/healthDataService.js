import api from './api'
import userService from './userService'

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
      console.log('Starting device pairing process for device:', deviceId)
      
      // For testing, use fixed IDs with correct types
      const testDeviceId = 1    // Integer
      const testUserId = 1      // Long
      
      console.log('Using test IDs - deviceId:', testDeviceId, 'userId:', testUserId)
      const response = await api.device.pair(testDeviceId, testUserId)
      console.log('Pair device response:', response.data)
      return response.data
    } catch (error) {
      console.error('Error pairing device:', error)
      if (error.response) {
        console.error('Error response:', {
          status: error.response.status,
          data: error.response.data,
          headers: error.response.headers
        })
      }
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