import api from './api';

export const userService = {
  // Register new user
  async register(userData) {
    try {
      const response = await api.post('/user/register', userData);
      return response.data;
    } catch (error) {
      console.error('Error registering user:', error);
      throw error;
    }
  },

  // Login user
  async login(credentials) {
    try {
      const response = await api.post('/user/login', credentials);
      if (response.data.token) {
        localStorage.setItem('token', response.data.token);
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
      await api.post('/user/logout');
      localStorage.removeItem('token');
    } catch (error) {
      console.error('Error logging out:', error);
      throw error;
    }
  },

  // Get user profile
  async getProfile() {
    try {
      const response = await api.get('/user/profile');
      return response.data;
    } catch (error) {
      console.error('Error fetching user profile:', error);
      throw error;
    }
  },

  // Update user profile
  async updateProfile(profileData) {
    try {
      const response = await api.put('/user/profile', profileData);
      return response.data;
    } catch (error) {
      console.error('Error updating user profile:', error);
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
  }
};