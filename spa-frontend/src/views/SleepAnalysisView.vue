<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <h1 class="text-2xl font-bold text-gray-800">Sleep Analysis</h1>
      <div class="flex items-center space-x-4">
        <select v-model="selectedTimeRange" 
                class="px-4 py-2 rounded-lg border border-gray-200 focus:outline-none focus:ring-2 focus:ring-[#8FBC8B]">
          <option value="day">Today</option>
          <option value="week">This Week</option>
          <option value="month">This Month</option>
          <option value="year">This Year</option>
        </select>
        <button @click="refreshData" class="p-2 hover:bg-gray-100 rounded-lg transition-colors">
          <span class="text-xl">🔄</span>
        </button>
      </div>
    </div>

    <!-- Sleep Stats -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
      <div v-for="stat in sleepStats" :key="stat.title" 
           class="bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition-shadow">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">{{ stat.title }}</p>
            <p class="text-2xl font-bold text-gray-800 mt-1">{{ stat.value }}</p>
          </div>
          <div class="w-12 h-12 rounded-lg bg-[#8FBC8B]/10 flex items-center justify-center">
            <span class="text-xl">{{ stat.icon }}</span>
          </div>
        </div>
        <div class="mt-4 flex items-center text-sm" :class="stat.trend > 0 ? 'text-green-500' : 'text-red-500'">
          <span>{{ stat.trend > 0 ? '↑' : '↓' }}</span>
          <span class="ml-1">{{ Math.abs(stat.trend) }}%</span>
          <span class="ml-1 text-gray-500">vs last week</span>
        </div>
      </div>
    </div>

    <!-- Sleep Pattern Chart -->
    <div class="bg-white p-6 rounded-xl shadow-sm">
      <h2 class="text-lg font-semibold text-gray-800 mb-4">Sleep Pattern</h2>
      <div class="h-96 flex items-center justify-center bg-gray-50 rounded-lg">
        <p class="text-gray-500">Sleep pattern chart will be implemented here</p>
      </div>
    </div>

    <!-- Sleep Quality Analysis -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- Sleep Stages -->
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">Sleep Stages</h2>
        <div class="space-y-4">
          <div v-for="stage in sleepStages" :key="stage.name" class="p-4 bg-gray-50 rounded-lg">
            <div class="flex items-center justify-between mb-2">
              <div class="flex items-center space-x-3">
                <span class="text-xl">{{ stage.icon }}</span>
                <p class="font-medium text-gray-800">{{ stage.name }}</p>
              </div>
              <p class="text-lg font-semibold text-gray-800">{{ stage.duration }}</p>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div class="bg-[#8FBC8B] h-2 rounded-full" :style="{ width: stage.percentage + '%' }"></div>
            </div>
          </div>
        </div>
      </div>

      <!-- Sleep Quality Factors -->
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">Sleep Quality Factors</h2>
        <div class="space-y-4">
          <div v-for="factor in sleepFactors" :key="factor.name" class="p-4 bg-gray-50 rounded-lg">
            <div class="flex items-start space-x-4">
              <div class="w-10 h-10 rounded-lg bg-[#8FBC8B]/10 flex items-center justify-center flex-shrink-0">
                <span class="text-xl">{{ factor.icon }}</span>
              </div>
              <div>
                <p class="font-medium text-gray-800">{{ factor.name }}</p>
                <p class="text-sm text-gray-600 mt-1">{{ factor.description }}</p>
                <div class="mt-2 flex items-center text-sm" :class="factor.impact > 0 ? 'text-green-500' : 'text-red-500'">
                  <span>{{ factor.impact > 0 ? 'Positive' : 'Negative' }} Impact</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const selectedTimeRange = ref('week')

const sleepStats = ref([
  { title: 'Total Sleep Time', value: '7h 30m', icon: '⏰', trend: 2.5 },
  { title: 'Sleep Quality', value: '85%', icon: '⭐', trend: 5.2 },
  { title: 'Deep Sleep', value: '1h 45m', icon: '💤', trend: -1.2 },
  { title: 'Sleep Efficiency', value: '92%', icon: '📈', trend: 3.1 }
])

const sleepStages = ref([
  { name: 'Deep Sleep', duration: '1h 45m', percentage: 23, icon: '💤' },
  { name: 'Light Sleep', duration: '4h 15m', percentage: 57, icon: '😴' },
  { name: 'REM Sleep', duration: '1h 30m', percentage: 20, icon: '🌙' }
])

const sleepFactors = ref([
  { 
    name: 'Room Temperature', 
    description: 'Optimal room temperature maintained throughout the night',
    impact: 1,
    icon: '🌡️'
  },
  { 
    name: 'Noise Level', 
    description: 'Minimal noise disturbances detected',
    impact: 1,
    icon: '🔇'
  },
  { 
    name: 'Light Exposure', 
    description: 'Some light exposure detected during sleep',
    impact: -1,
    icon: '💡'
  }
])

const refreshData = () => {
  // TODO: Implement data refresh logic
}

onMounted(() => {
  // TODO: Fetch initial data
})
</script> 