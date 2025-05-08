<script setup>
import HealthSection from '../components/home/HealthSection.vue'
import SleepSection from '../components/home/SleepSection.vue'
import NotificationsSection from '../components/home/NotificationSection.vue'

const sendMeasurements = () => {
  // TODO: call backend endpoint to invoke sendData
  alert("Simulating data sent from wearable device.")
}

const recordSleep = () => {
  // TODO: call backend endpoint to record sleep data
  alert("Recording last night's sleep data.")
}
</script>

<template>
  <div class="min-h-screen bg-white p-6 space-y-10">
    <!-- Top buttons -->
    <div class="fixed top-4 right-4 flex flex-col space-y-2 items-end z-50">
      <button @click="sendMeasurements" class="relative group bg-[#8FBC8B] text-white p-3 rounded-full shadow-xl hover:scale-105 transition">
        📡
        <span class="absolute top-12 right-0 text-sm bg-gray-700 text-white px-2 py-1 rounded opacity-0 group-hover:opacity-100">
          Wearable sent data
        </span>
      </button>

      <button @click="recordSleep" class="relative group bg-[#8FBC8B] text-white p-3 rounded-full shadow-xl hover:scale-105 transition">
        😴
        <span class="absolute top-12 right-0 text-sm bg-gray-700 text-white px-2 py-1 rounded opacity-0 group-hover:opacity-100">
          Record sleep
        </span>
      </button>
    </div>

    <!-- Health Data -->
    <HealthSection />

    <!-- Sleep Pattern -->
    <SleepSection />

    <!-- Notifications -->
    <NotificationsSection />
  </div>
</template>