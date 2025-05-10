import api from './api';

const userService = {
  // Register new user
  async register(userData) {
    try {
      const response = await api.auth.register(userData);
      console.log('Register response:', response.data);
      
      // Check for both camelCase and snake_case token properties
      const accessToken = response.data.accessToken || response.data.access_token;
      const refreshToken = response.data.refreshToken || response.data.refresh_token;
      
      if (accessToken && refreshToken) {
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
      } else {
        console.error('Token structure:', response.data);
        throw new Error('No tokens received from server');
      }
      return response.data;
    } catch (error) {
      console.error('Error registering:', error);
      throw error;
    }
  },

  // Login user
  async login(credentials) {
    try {
      const response = await api.auth.login(credentials);
      console.log('Login response:', response.data);
      
      // Check for both camelCase and snake_case token properties
      const accessToken = response.data.accessToken || response.data.access_token;
      const refreshToken = response.data.refreshToken || response.data.refresh_token;
      
      if (accessToken && refreshToken) {
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
      } else {
        console.error('Token structure:', response.data);
        throw new Error('No tokens received from server');
      }
      return response.data;
    } catch (error) {
      console.error('Error logging in:', error);
      throw error;
    }
  },

  // Logout user
  async logout() {
    try {
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
      return true;
    } catch (error) {
      console.error('Error logging out:', error);
      throw error;
    }
  },

  // Check if user is authenticated
  isAuthenticated() {
    return !!localStorage.getItem('accessToken');
  },

  // Get access token
  getAccessToken() {
    return localStorage.getItem('accessToken');
  },

  // Get refresh token
  getRefreshToken() {
    return localStorage.getItem('refreshToken');
  },

  // Get user profile
  async getProfile() {
    try {
      const response = await api.user.getProfile();
      return response.data;
    } catch (error) {
      console.error('Error fetching profile:', error);
      throw error;
    }
  },

  // Update user profile
  async updateProfile(profileData) {
    try {
      const response = await api.user.updateProfile(profileData);
      return response.data;
    } catch (error) {
      console.error('Error updating profile:', error);
      throw error;
    }
  },

  // Change password
  async changePassword(passwordData) {
    try {
      const response = await api.put('/user/change-password', passwordData);
      return response.data;
    } catch (error) {
      console.error('Error changing password:', error);
      throw error;
    }
  },

  // Get user settings
  async getSettings() {
    try {
      const response = await api.get('/user/settings');
      return response.data;
    } catch (error) {
      console.error('Error fetching user settings:', error);
      throw error;
    }
  },

  // Update user settings
  async updateSettings(settings) {
    try {
      const response = await api.put('/user/settings', settings);
      return response.data;
    } catch (error) {
      console.error('Error updating user settings:', error);
      throw error;
    }
  },

  async resetPassword(email) {
    try {
      const response = await api.auth.resetPassword(email);
      return response.data;
    } catch (error) {
      console.error('Error resetting password:', error);
      throw error;
    }
  },

  async updateEmergencyContact(contactData) {
    try {
      const response = await api.user.updateEmergencyContact(contactData);
      return response.data;
    } catch (error) {
      console.error('Error updating emergency contact:', error);
      throw error;
    }
  },

  async getNotifications() {
    try {
      const response = await api.notification.getNotifications();
      return response.data;
    } catch (error) {
      console.error('Error fetching notifications:', error);
      throw error;
    }
  },

  async markNotificationAsRead(notificationId) {
    try {
      const response = await api.notification.markAsRead(notificationId);
      return response.data;
    } catch (error) {
      console.error('Error marking notification as read:', error);
      throw error;
    }
  }
};

export default userService;