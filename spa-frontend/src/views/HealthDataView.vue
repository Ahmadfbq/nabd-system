<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <h1 class="text-2xl font-bold text-gray-800">Health Data</h1>
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

    <!-- Main Chart -->
    <div class="bg-white p-6 rounded-xl shadow-sm">
      <div class="flex justify-between items-center mb-6">
        <h2 class="text-lg font-semibold text-gray-800">Health Metrics Overview</h2>
        <div class="flex space-x-2">
          <button v-for="metric in metrics" 
                  :key="metric.id"
                  @click="selectedMetric = metric.id"
                  class="px-4 py-2 rounded-lg transition-colors"
                  :class="selectedMetric === metric.id ? 'bg-[#8FBC8B] text-white' : 'bg-gray-100 text-gray-600 hover:bg-gray-200'">
            {{ metric.name }}
          </button>
        </div>
      </div>
      <div class="h-96 flex items-center justify-center bg-gray-50 rounded-lg">
        <p class="text-gray-500">Chart will be implemented here</p>
      </div>
    </div>

    <!-- Data Grid -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- Latest Measurements -->
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">Latest Measurements</h2>
        <div class="space-y-4">
          <div v-for="measurement in latestMeasurements" :key="measurement.id" 
               class="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
            <div class="flex items-center space-x-4">
              <div class="w-10 h-10 rounded-lg bg-[#8FBC8B]/10 flex items-center justify-center">
                <span class="text-xl">{{ measurement.icon }}</span>
              </div>
              <div>
                <p class="font-medium text-gray-800">{{ measurement.type }}</p>
                <p class="text-sm text-gray-500">{{ measurement.time }}</p>
              </div>
            </div>
            <p class="text-lg font-semibold text-gray-800">{{ measurement.value }}</p>
          </div>
        </div>
      </div>

      <!-- Health Insights -->
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">Health Insights</h2>
        <div class="space-y-4">
          <div v-for="insight in healthInsights" :key="insight.id" 
               class="p-4 bg-gray-50 rounded-lg">
            <div class="flex items-start space-x-4">
              <div class="w-10 h-10 rounded-lg bg-[#8FBC8B]/10 flex items-center justify-center flex-shrink-0">
                <span class="text-xl">{{ insight.icon }}</span>
              </div>
              <div>
                <p class="font-medium text-gray-800">{{ insight.title }}</p>
                <p class="text-sm text-gray-600 mt-1">{{ insight.description }}</p>
                <div class="mt-2 flex items-center text-sm" :class="insight.trend > 0 ? 'text-green-500' : 'text-red-500'">
                  <span>{{ insight.trend > 0 ? '↑' : '↓' }}</span>
                  <span class="ml-1">{{ Math.abs(insight.trend) }}%</span>
                  <span class="ml-1 text-gray-500">vs previous period</span>
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
const selectedMetric = ref('heartRate')

const metrics = [
  { id: 'heartRate', name: 'Heart Rate' },
  { id: 'bloodPressure', name: 'Blood Pressure' },
  { id: 'temperature', name: 'Temperature' },
  { id: 'oxygen', name: 'Oxygen Level' }
]

const latestMeasurements = ref([
  { id: 1, type: 'Heart Rate', value: '72 BPM', icon: '❤️', time: '5 minutes ago' },
  { id: 2, type: 'Blood Pressure', value: '120/80', icon: '🫀', time: '1 hour ago' },
  { id: 3, type: 'Temperature', value: '36.6°C', icon: '🌡️', time: '2 hours ago' },
  { id: 4, type: 'Oxygen Level', value: '98%', icon: '🫁', time: '3 hours ago' }
])

const healthInsights = ref([
  { 
    id: 1, 
    icon: '❤️', 
    title: 'Heart Rate Stability', 
    description: 'Your heart rate has been stable within normal range for the past week.',
    trend: 2.5
  },
  { 
    id: 2, 
    icon: '🫀', 
    title: 'Blood Pressure Trend', 
    description: 'Blood pressure readings show a slight improvement compared to last week.',
    trend: -1.2
  },
  { 
    id: 3, 
    icon: '🌡️', 
    title: 'Temperature Pattern', 
    description: 'Body temperature remains within normal range with minimal fluctuations.',
    trend: 0.5
  }
])

const refreshData = () => {
  // TODO: Implement data refresh logic
}

onMounted(() => {
  // TODO: Fetch initial data
})
</script> 