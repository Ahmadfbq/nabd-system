<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const user = ref(null)
const loading = ref(false)
const error = ref('')
const success = ref('')

// Form data
const formData = ref({
  name: '',
  email: '',
  phone: '',
  height: '',
  weight: '',
  emergencyEnabled: false
})

// Password change form
const passwordForm = ref({
  currentPassword: '',
  newPassword: '',
  confirmPassword: ''
})

// Fetch user profile
const fetchProfile = async () => {
  try {
    loading.value = true
    error.value = ''
    
    const response = await axios.get('http://localhost:8080/api/v1/users/me')
    user.value = response.data
    
    // Update form data
    formData.value = {
      name: user.value.name,
      email: user.value.email,
      phone: user.value.phone || '',
      height: user.value.height || '',
      weight: user.value.weight || '',
      emergencyEnabled: user.value.emergencyEnabled || false
    }
  } catch (err) {
    error.value = 'Failed to fetch profile. Please try again.'
  } finally {
    loading.value = false
  }
}

// Update profile
const updateProfile = async () => {
  try {
    loading.value = true
    error.value = ''
    success.value = ''
    
    await axios.put('http://localhost:8080/api/v1/users/me', formData.value)
    success.value = 'Profile updated successfully'
    
    // Refresh profile
    await fetchProfile()
  } catch (err) {
    error.value = err.response?.data?.message || 'Failed to update profile. Please try again.'
  } finally {
    loading.value = false
  }
}

// Change password
const changePassword = async () => {
  try {
    if (passwordForm.value.newPassword !== passwordForm.value.confirmPassword) {
      error.value = 'New passwords do not match'
      return
    }

    loading.value = true
    error.value = ''
    success.value = ''
    
    await axios.post('http://localhost:8080/api/v1/users/change-password', {
      currentPassword: passwordForm.value.currentPassword,
      newPassword: passwordForm.value.newPassword,
      confirmationPassword: passwordForm.value.confirmPassword
    })
    
    success.value = 'Password changed successfully'
    passwordForm.value = {
      currentPassword: '',
      newPassword: '',
      confirmPassword: ''
    }
  } catch (err) {
    error.value = err.response?.data?.message || 'Failed to change password. Please try again.'
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchProfile()
})
</script>

<template>
  <div class="min-h-screen bg-gray-50 p-8">
    <div class="max-w-4xl mx-auto">
      <!-- Header -->
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-800">Profile Settings</h1>
        <p class="text-gray-600 mt-2">Manage your account settings and preferences</p>
      </div>

      <!-- Success Message -->
      <div
        v-if="success"
        class="mb-6 bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative"
      >
        {{ success }}
      </div>

      <!-- Error Message -->
      <div
        v-if="error"
        class="mb-6 bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative"
      >
        {{ error }}
      </div>

      <!-- Profile Form -->
      <div class="bg-white rounded-xl shadow-md p-6 mb-8">
        <h2 class="text-xl font-semibold text-gray-800 mb-6">Personal Information</h2>
        
        <form @submit.prevent="updateProfile" class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Full Name</label>
              <input
                v-model="formData.name"
                type="text"
                required
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Email</label>
              <input
                v-model="formData.email"
                type="email"
                required
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Phone</label>
              <input
                v-model="formData.phone"
                type="tel"
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Height (cm)</label>
              <input
                v-model="formData.height"
                type="number"
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Weight (kg)</label>
              <input
                v-model="formData.weight"
                type="number"
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div class="flex items-center">
              <input
                v-model="formData.emergencyEnabled"
                type="checkbox"
                class="h-4 w-4 text-[#8FBC8B] focus:ring-[#8FBC8B] border-gray-300 rounded"
              />
              <label class="ml-2 block text-sm text-gray-700">
                Enable Emergency Notifications
              </label>
            </div>
          </div>

          <div class="flex justify-end">
            <button
              type="submit"
              :disabled="loading"
              class="bg-[#8FBC8B] text-white px-6 py-3 rounded-xl font-semibold hover:bg-green-600 transition-colors disabled:opacity-50"
            >
              {{ loading ? 'Saving...' : 'Save Changes' }}
            </button>
          </div>
        </form>
      </div>

      <!-- Password Change Form -->
      <div class="bg-white rounded-xl shadow-md p-6">
        <h2 class="text-xl font-semibold text-gray-800 mb-6">Change Password</h2>
        
        <form @submit.prevent="changePassword" class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Current Password</label>
              <input
                v-model="passwordForm.currentPassword"
                type="password"
                required
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">New Password</label>
              <input
                v-model="passwordForm.newPassword"
                type="password"
                required
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Confirm New Password</label>
              <input
                v-model="passwordForm.confirmPassword"
                type="password"
                required
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>
          </div>

          <div class="flex justify-end">
            <button
              type="submit"
              :disabled="loading"
              class="bg-[#8FBC8B] text-white px-6 py-3 rounded-xl font-semibold hover:bg-green-600 transition-colors disabled:opacity-50"
            >
              {{ loading ? 'Changing...' : 'Change Password' }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- Loading State -->
    <div
      v-if="loading"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center"
    >
      <div class="bg-white p-6 rounded-xl shadow-lg">
        <div class="animate-spin rounded-full h-12 w-12 border-4 border-[#8FBC8B] border-t-transparent"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.input-style {
  @apply w-full p-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent transition-all duration-300;
}

.btn-primary {
  @apply bg-[#8FBC8B] text-white font-semibold py-2 px-4 rounded-xl hover:bg-green-600 transition-all duration-300;
}
</style>