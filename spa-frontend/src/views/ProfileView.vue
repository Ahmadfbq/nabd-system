<script setup>
import { ref, onMounted } from 'vue'
import api from '@/services/api'
import healthDataService from '@/services/healthDataService'
import userService from '@/services/userService'
import { useRouter } from 'vue-router'

const user = ref(null)
const loading = ref(false)
const error = ref('')
const success = ref('')
const userId = ref(null)

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

// Add these refs in the setup
const deviceId = ref('')
const pairedDevices = ref([])
const emergencyContact = ref({
  name: '',
  phone: '',
  relationship: '',
  enabled: false
})

const router = useRouter()

// Fetch user profile
const fetchProfile = async () => {
  try {
    loading.value = true
    error.value = ''

    const response = await api.user.getProfile()
    user.value = response.data
    userId.value = response.data.id
    // Update form data
    formData.value = {
      id: user.value.id,
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

    // const userId = formData.value.id
    await api.user.updateProfile(userId.value, formData.value)

    success.value = 'Profile updated successfully'
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

    await api.auth.changePassword({
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

// Add these methods in the setup
const pairDevice = async () => {
  try {
    if (!deviceId.value) {
      error.value = 'Please enter a device ID'
      return
    }

    loading.value = true
    error.value = ''
    success.value = ''

    await healthDataService.pairDevice(deviceId.value, userId.value)
    success.value = 'Device paired successfully'
    deviceId.value = ''
    await fetchPairedDevices()
  } catch (err) {
    console.error('Pair device error:', err)
    if (err.message === 'User profile not found') {
      error.value = 'Please complete your profile before pairing a device'
    } else {
      error.value = err.response?.data?.message || 'Failed to pair device. Please try again.'
    }
  } finally {
    loading.value = false
  }
}

const fetchPairedDevices = async () => {
  try {
    const devices = await healthDataService.getPairedDevices()
    pairedDevices.value = devices
  } catch (err) {
    console.error('Error fetching paired devices:', err)
  }
}

const unpairDevice = async (deviceId) => {
  try {
    loading.value = true
    error.value = ''
    await healthDataService.unpairDevice(deviceId)
    success.value = 'Device unpaired successfully'
    await fetchPairedDevices()
  } catch (err) {
    error.value = err.response?.data?.message || 'Failed to unpair device'
  } finally {
    loading.value = false
  }
}

const updateEmergencyContact = async () => {
  try {
    loading.value = true
    error.value = ''
    await api.user.updateEmergencyContact(emergencyContact.value)
    success.value = 'Emergency contact updated successfully'
  } catch (err) {
    error.value = err.response?.data?.message || 'Failed to update emergency contact'
  } finally {
    loading.value = false
  }
}

onMounted(async () => {
  // Check authentication
  if (!userService.isAuthenticated()) {
    console.error('User is not authenticated')
    error.value = 'Please log in to access this page'
    return
  }

  try {
    await fetchProfile()
    await fetchPairedDevices()
  } catch (err) {
    console.error('Error in onMounted:', err)
    if (err.response?.status === 403) {
      error.value = 'Your session has expired. Please log in again.'
      // Optionally redirect to login
      // router.push('/auth/login')
    }
  }
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

      <!-- Device Management -->
      <div class="bg-white rounded-xl shadow-md p-6 mb-8">
        <h2 class="text-xl font-semibold text-gray-800 mb-6">Device Management</h2>

        <div class="space-y-6">
          <!-- Pair New Device -->
          <div class="flex items-end space-x-4">
            <div class="flex-1">
              <label class="block text-sm font-medium text-gray-700 mb-2">Device ID</label>
              <input
                v-model="deviceId"
                type="text"
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
                placeholder="Enter device ID"
              />
            </div>
            <button
              @click="pairDevice"
              :disabled="loading || !deviceId"
              class="bg-[#8FBC8B] text-white px-6 py-3 rounded-xl font-semibold hover:bg-green-600 transition-colors disabled:opacity-50"
            >
              Pair Device
            </button>
          </div>

          <!-- Paired Devices List -->
          <div v-if="pairedDevices.length > 0" class="space-y-4">
            <h3 class="text-lg font-medium text-gray-800">Paired Devices</h3>
            <div class="grid gap-4">
              <div
                v-for="device in pairedDevices"
                :key="device.id"
                class="flex items-center justify-between p-4 bg-gray-50 rounded-xl"
              >
                <div>
                  <p class="font-medium text-gray-800">Device {{ device.id }}</p>
                  <p class="text-sm text-gray-600">Last connected: {{ new Date(device.lastConnected).toLocaleString() }}</p>
                </div>
                <button
                  @click="unpairDevice(device.id)"
                  class="text-red-600 hover:text-red-800"
                >
                  Unpair
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Emergency Contact Section -->
      <div class="bg-white rounded-xl shadow-md p-6">
        <h2 class="text-xl font-semibold text-gray-800 mb-6">Emergency Contact</h2>

        <form @submit.prevent="updateEmergencyContact" class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Contact Name</label>
              <input
                v-model="emergencyContact.name"
                type="text"
                required
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">Phone Number</label>
              <input
                v-model="emergencyContact.phone"
                type="tel"
                required
                class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
              />
            </div>



            <div class="flex items-center">
              <input
                v-model="emergencyContact.enabled"
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
              {{ loading ? 'Saving...' : 'Save Emergency Contact' }}
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
