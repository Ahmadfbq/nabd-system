import api from './api'

const notificationService = {
  // Get all notifications
  async getNotifications() {
    try {
      const response = await api.notification.getNotifications()
      return response.data
    } catch (error) {
      console.error('Error fetching notifications:', error)
      throw error
    }
  },

  // Get unread notifications
  async getUnreadNotifications() {
    try {
      const response = await api.notification.getUnreadNotifications()
      return response.data
    } catch (error) {
      console.error('Error fetching unread notifications:', error)
      throw error
    }
  },

  // Mark notification as read
  async markAsRead(notificationId) {
    try {
      const response = await api.notification.markAsRead(notificationId)
      return response.data
    } catch (error) {
      console.error('Error marking notification as read:', error)
      throw error
    }
  },

  // Mark all notifications as read
  async markAllAsRead() {
    try {
      const response = await api.notification.markAllAsRead()
      return response.data
    } catch (error) {
      console.error('Error marking all notifications as read:', error)
      throw error
    }
  },

  // Delete notification
  async deleteNotification(notificationId) {
    try {
      const response = await api.notification.delete(notificationId)
      return response.data
    } catch (error) {
      console.error('Error deleting notification:', error)
      throw error
    }
  },

  // Get notification preferences
  async getPreferences() {
    try {
      const response = await api.notification.getPreferences()
      return response.data
    } catch (error) {
      console.error('Error fetching notification preferences:', error)
      throw error
    }
  },

  // Update notification preferences
  async updatePreferences(preferences) {
    try {
      const response = await api.notification.updatePreferences(preferences)
      return response.data
    } catch (error) {
      console.error('Error updating notification preferences:', error)
      throw error
    }
  },

  updateNotificationSettings(settings) {
    return api.notification.updateSettings(settings)
  },

  async getUnreadCount() {
    try {
      const response = await api.notification.getUnreadCount()
      return response.data
    } catch (error) {
      console.error('Error fetching unread notification count:', error)
      throw error
    }
  }
}

export default notificationService