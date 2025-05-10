<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import HealthSection from '@/components/home/HealthSection.vue'
import SleepSection from '@/components/home/SleepSection.vue'
import NotificationsSection from '@/components/home/NotificationSection.vue'

const router = useRouter()
const measurements = ref([])
const loading = ref(false)
const error = ref('')
const showNotification = ref(false)

const healthSectionRef = ref(null)
const sleepSectionRef = ref(null)
const notificationsSectionRef = ref(null)

// Generate fake measurement data
const generateFakeMeasurement = () => {
  return {
    deviceId: 1,
    temperature: (36.5 + Math.random() * 0.5).toFixed(1),
    heartRate: Math.floor(60 + Math.random() * 40),
    bloodPressure: Math.floor(90 + Math.random() * 50),
    bloodSugar: Math.floor(70 + Math.random() * 30),
    oxygenLevel: Math.floor(95 + Math.random() * 5),
    timestamp: new Date().toISOString()
  }
}

// Send measurement to health data service
const sendMeasurement = async () => {
  try {
    loading.value = true
    error.value = ''
    
    const measurement = generateFakeMeasurement()
    await axios.post('http://localhost:8084/measurements', measurement)
    
    // Show notification
    showNotification.value = true
    setTimeout(() => {
      showNotification.value = false
    }, 3000)
    
    // Refresh measurements
    fetchMeasurements()
  } catch (err) {
    error.value = 'Failed to send measurement. Please try again.'
  } finally {
    loading.value = false
  }
}

// Fetch measurements from health data service
const fetchMeasurements = async () => {
  try {
    loading.value = true
    error.value = ''
    
    const response = await axios.get('http://localhost:8084/measurements')
    measurements.value = response.data
  } catch (err) {
    error.value = 'Failed to fetch measurements. Please try again.'
  } finally {
    loading.value = false
  }
}

const sendMeasurements = () => {
  if (healthSectionRef.value) {
    healthSectionRef.value.generateHealthData()
    notificationsSectionRef.value.addNotification({
      type: 'success',
      message: 'New health data received from device',
      timestamp: 'Just now',
      icon: '📱',
      read: false,
      details: 'Your device has sent new health measurements. Click to view detailed analysis.',
      action: 'View Health Data',
      route: '/health'
    })
  }
}

const recordSleep = () => {
  if (sleepSectionRef.value) {
    const sleepData = sleepSectionRef.value.generateSleepData()
    notificationsSectionRef.value.addNotification({
      type: 'info',
      message: 'New sleep record received',
      timestamp: 'Just now',
      icon: '😴',
      read: false,
      details: `You slept for ${sleepData.duration} hours with ${sleepData.quality} quality sleep. Deep sleep: ${sleepData.deepSleep}, REM: ${sleepData.remSleep}.`,
      action: 'View Sleep Analysis',
      route: '/sleep'
    })
  }
}

const isMenuOpen = ref(false)

onMounted(() => {
  fetchMeasurements()
})
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Top Navigation -->
    <nav class="bg-white shadow-sm sticky top-0 z-50">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16">
          <div class="flex items-center">
            <div class="flex-shrink-0 flex items-center">
              <span class="text-2xl">❤️</span>
              <span class="ml-2 text-xl font-bold text-[#8FBC8B]">Nabd</span>
            </div>
          </div>
          
          <div class="flex items-center space-x-4">
            <RouterLink to="/profile" class="text-gray-600 hover:text-[#8FBC8B]">
              <span class="text-lg">👤</span>
            </RouterLink>
            <button @click="isMenuOpen = !isMenuOpen" class="text-gray-600 hover:text-[#8FBC8B]">
              <span class="text-lg">⚙️</span>
            </button>
          </div>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
    <main class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <!-- Quick Actions -->
      <div class="fixed bottom-6 right-6 flex flex-col space-y-3 z-50">
        <button 
          @click="sendMeasurement" 
          :disabled="loading"
          class="relative group bg-[#8FBC8B] text-white p-4 rounded-full shadow-lg hover:scale-105 transition-all duration-300"
        >
          ❤️
          <span class="absolute right-14 top-1/2 -translate-y-1/2 text-sm bg-gray-800 text-white px-3 py-1 rounded-lg opacity-0 group-hover:opacity-100 whitespace-nowrap transition-opacity duration-300">
            Send new measurement
          </span>
        </button>

        <button 
          @click="recordSleep" 
          class="relative group bg-[#8FBC8B] text-white p-4 rounded-full shadow-lg hover:scale-105 transition-all duration-300"
        >
          😴
          <span class="absolute right-14 top-1/2 -translate-y-1/2 text-sm bg-gray-800 text-white px-3 py-1 rounded-lg opacity-0 group-hover:opacity-100 whitespace-nowrap transition-opacity duration-300">
            Record Sleep
          </span>
        </button>
      </div>

      <!-- Notification -->
      <div
        v-if="showNotification"
        class="fixed top-4 right-4 bg-green-500 text-white px-6 py-3 rounded-lg shadow-lg transform transition-all duration-300"
      >
        New measurement received and processed!
      </div>

      <!-- Error Message -->
      <div
        v-if="error"
        class="mb-6 bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative"
      >
        {{ error }}
      </div>

      <!-- Dashboard Grid -->
      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <!-- Health Data Section -->
        <div class="lg:col-span-2">
          <HealthSection ref="healthSectionRef" />
        </div>

        <!-- Right Column -->
        <div class="space-y-6">
          <!-- Sleep Pattern Section -->
          <SleepSection ref="sleepSectionRef" />
          
          <!-- Notifications Section -->
          <NotificationsSection ref="notificationsSectionRef" />
        </div>
      </div>

      <!-- Measurements Grid -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-6">
        <div
          v-for="measurement in measurements"
          :key="measurement.id"
          class="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow"
        >
          <div class="flex justify-between items-start mb-4">
            <h3 class="text-lg font-semibold text-gray-800">Measurement</h3>
            <span class="text-sm text-gray-500">
              {{ new Date(measurement.timestamp).toLocaleString() }}
            </span>
          </div>

          <div class="space-y-3">
            <div class="flex justify-between">
              <span class="text-gray-600">Temperature</span>
              <span class="font-medium">{{ measurement.temperature }}°C</span>
            </div>
            <div class="flex justify-between">
              <span class="text-gray-600">Heart Rate</span>
              <span class="font-medium">{{ measurement.heartRate }} BPM</span>
            </div>
            <div class="flex justify-between">
              <span class="text-gray-600">Blood Pressure</span>
              <span class="font-medium">{{ measurement.bloodPressure }} mmHg</span>
            </div>
            <div class="flex justify-between">
              <span class="text-gray-600">Blood Sugar</span>
              <span class="font-medium">{{ measurement.bloodSugar }} mg/dL</span>
            </div>
            <div class="flex justify-between">
              <span class="text-gray-600">Oxygen Level</span>
              <span class="font-medium">{{ measurement.oxygenLevel }}%</span>
            </div>
          </div>
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
    </main>

    <!-- Settings Menu -->
    <div 
      v-if="isMenuOpen" 
      class="fixed inset-0 bg-black bg-opacity-50 z-40"
      @click="isMenuOpen = false"
    >
      <div 
        class="absolute right-0 top-0 h-full w-80 bg-white shadow-xl p-6"
        @click.stop
      >
        <div class="flex justify-between items-center mb-6">
          <h3 class="text-lg font-semibold text-gray-800">Settings</h3>
          <button @click="isMenuOpen = false" class="text-gray-500 hover:text-gray-700">
            ✕
          </button>
        </div>
        <div class="space-y-4">
          <button class="w-full text-left px-4 py-2 rounded-lg hover:bg-gray-100 text-gray-700">
            Account Settings
          </button>
          <button class="w-full text-left px-4 py-2 rounded-lg hover:bg-gray-100 text-gray-700">
            Notification Preferences
          </button>
          <button class="w-full text-left px-4 py-2 rounded-lg hover:bg-gray-100 text-gray-700">
            Privacy Settings
          </button>
          <button class="w-full text-left px-4 py-2 rounded-lg hover:bg-gray-100 text-red-600">
            Logout
          </button>
        </div>
      </div>
    </div>
  </div>
</template>