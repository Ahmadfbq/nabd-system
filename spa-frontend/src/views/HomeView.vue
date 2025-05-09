<script setup>
import { ref } from 'vue'
import HealthSection from '@/components/home/HealthSection.vue'
import SleepSection from '@/components/home/SleepSection.vue'
import NotificationsSection from '@/components/home/NotificationSection.vue'

const healthSectionRef = ref(null)
const sleepSectionRef = ref(null)
const notificationsSectionRef = ref(null)

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
          @click="sendMeasurements" 
          class="relative group bg-[#8FBC8B] text-white p-4 rounded-full shadow-lg hover:scale-105 transition-all duration-300"
        >
          📡
          <span class="absolute right-14 top-1/2 -translate-y-1/2 text-sm bg-gray-800 text-white px-3 py-1 rounded-lg opacity-0 group-hover:opacity-100 whitespace-nowrap transition-opacity duration-300">
            Sync Device Data
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