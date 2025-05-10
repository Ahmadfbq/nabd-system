import axios from '@/utils/axios'

const notificationService = {
  // Get all notifications
  async getNotifications() {
    try {
      const response = await axios.get('/notifications')
      return response.data
    } catch (error) {
      console.error('Error fetching notifications:', error)
      throw error
    }
  },

  // Get unread notifications
  async getUnreadNotifications() {
    try {
      const response = await axios.get('/notifications/unread')
      return response.data
    } catch (error) {
      console.error('Error fetching unread notifications:', error)
      throw error
    }
  },

  // Mark notification as read
  async markAsRead(notificationId) {
    try {
      const response = await axios.put(`/notifications/${notificationId}/read`)
      return response.data
    } catch (error) {
      console.error('Error marking notification as read:', error)
      throw error
    }
  },

  // Mark all notifications as read
  async markAllAsRead() {
    try {
      const response = await axios.put('/notifications/read-all')
      return response.data
    } catch (error) {
      console.error('Error marking all notifications as read:', error)
      throw error
    }
  },

  // Delete notification
  async deleteNotification(notificationId) {
    try {
      const response = await axios.delete(`/notifications/${notificationId}`)
      return response.data
    } catch (error) {
      console.error('Error deleting notification:', error)
      throw error
    }
  },

  // Get notification preferences
  async getPreferences() {
    try {
      const response = await axios.get('/notifications/preferences')
      return response.data
    } catch (error) {
      console.error('Error fetching notification preferences:', error)
      throw error
    }
  },

  // Update notification preferences
  async updatePreferences(preferences) {
    try {
      const response = await axios.put('/notifications/preferences', preferences)
      return response.data
    } catch (error) {
      console.error('Error updating notification preferences:', error)
      throw error
    }
  },

  updateNotificationSettings(settings) {
    return axios.put('/notifications/settings', settings)
  }
}

export default notificationService