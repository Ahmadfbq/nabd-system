<template>
  <div class="dashboard container">
    <div class="dashboard-grid">
      <div class="health-data-section">
        <h2>Your Health Data</h2>
        <div v-if="loading">Loading...</div>
        <div v-else-if="error" class="error-message">{{ error }}</div>
        <div v-else class="card-grid">
          <div v-for="(item, index) in healthData" :key="index" class="health-data-card">
            <h3>{{ item.type }}</h3>
            <p>Value: {{ item.value }} {{ item.unit }}</p>
            <p>Date: {{ new Date(item.timestamp).toLocaleString() }}</p>
          </div>
        </div>
        <button @click="fetchHealthData" class="button">Refresh Data</button>
      </div>
      
      <div class="notifications-section">
        <h2>Notifications</h2>
        <div v-if="notificationsLoading">Loading...</div>
        <div v-else-if="notificationsError" class="error-message">{{ notificationsError }}</div>
        <div v-else>
          <div v-for="notification in notifications" :key="notification.id" class="notification-item">
            <h4>{{ notification.title }}</h4>
            <p>{{ notification.message }}</p>
            <span class="notification-date">{{ new Date(notification.timestamp).toLocaleString() }}</span>
            <button @click="markAsRead(notification.id)" v-if="!notification.read" class="button small">
              Mark as Read
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import healthDataService from '../services/healthDataService';
import notificationService from '../services/notificationService';

export default {
  name: 'Dashboard',
  data() {
    return {
      healthData: [],
      loading: true,
      error: null,
      notifications: [],
      notificationsLoading: true,
      notificationsError: null
    };
  },
  mounted() {
    this.fetchHealthData();
    this.fetchNotifications();
  },
  methods: {
    async fetchHealthData() {
      this.loading = true;
      this.error = null;
      try {
        const response = await healthDataService.getHealthData();
        this.healthData = response.data;
      } catch (err) {
        this.error = 'Failed to load health data. Please try again.';
        console.error(err);
      } finally {
        this.loading = false;
      }
    },
    async fetchNotifications() {
      this.notificationsLoading = true;
      this.notificationsError = null;
      try {
        const response = await notificationService.getNotifications();
        this.notifications = response.data;
      } catch (err) {
        this.notificationsError = 'Failed to load notifications. Please try again.';
        console.error(err);
      } finally {
        this.notificationsLoading = false;
      }
    },
    async markAsRead(notificationId) {
      try {
        await notificationService.markAsRead(notificationId);
        // Update the local notification as read
        const notification = this.notifications.find(n => n.id === notificationId);
        if (notification) {
          notification.read = true;
        }
      } catch (err) {
        console.error('Failed to mark notification as read:', err);
      }
    }
  }
};
</script>

<style>
@import '../assets/styles/responsive.css';

/* Additional component-specific styles */
.health-data-card {
  background: #fff;
  border-radius: 8px;
  padding: 1rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.notification-item {
  background: #f8f9fa;
  border-left: 4px solid #4c85f4;
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 4px;
}

.notification-item.read {
  border-left-color: #aaa;
  opacity: 0.7;
}

.notification-date {
  display: block;
  font-size: 0.8rem;
  color: #777;
  margin-top: 0.5rem;
}

.button {
  background: #4c85f4;
  color: white;
  border: none;
  padding: 0.75rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 500;
}

.button.small {
  padding: 0.35rem 0.7rem;
  font-size: 0.8rem;
}

.button:hover {
  background: #3a6fd6;
}

.error-message {
  color: #d63031;
  background: #ffeded;
  padding: 0.75rem;
  border-radius: 4px;
  margin: 1rem 0;
}
</style>