import api from './api';

export default {
  getNotifications() {
    return api.get('/notification/');
  },
  
  markAsRead(notificationId) {
    return api.put(`/notification/${notificationId}/read`);
  },
  
  updateNotificationSettings(settings) {
    return api.put('/notification/settings', settings);
  }
};