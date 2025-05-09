<template>
  <div class="space-y-6">
    <!-- Header -->
    <div class="flex justify-between items-center">
      <h1 class="text-2xl font-bold text-gray-800">Dashboard</h1>
      <div class="flex items-center space-x-4">
        <span class="text-sm text-gray-500">Last updated: {{ lastUpdated }}</span>
        <button @click="refreshData" class="p-2 hover:bg-gray-100 rounded-lg transition-colors">
          <span class="text-xl">🔄</span>
        </button>
      </div>
    </div>

    <!-- Stats Grid -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
      <div v-for="stat in stats" :key="stat.title" 
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

    <!-- Charts Section -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- Health Data Chart -->
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">Health Data Overview</h2>
        <div class="h-64 flex items-center justify-center bg-gray-50 rounded-lg">
          <p class="text-gray-500">Chart will be implemented here</p>
        </div>
      </div>

      <!-- Sleep Analysis Chart -->
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">Sleep Analysis</h2>
        <div class="h-64 flex items-center justify-center bg-gray-50 rounded-lg">
          <p class="text-gray-500">Chart will be implemented here</p>
        </div>
      </div>
    </div>

    <!-- Recent Activity -->
    <div class="bg-white p-6 rounded-xl shadow-sm">
      <h2 class="text-lg font-semibold text-gray-800 mb-4">Recent Activity</h2>
      <div class="space-y-4">
        <div v-for="activity in recentActivities" :key="activity.id" 
             class="flex items-center space-x-4 p-4 hover:bg-gray-50 rounded-lg transition-colors">
          <div class="w-10 h-10 rounded-lg bg-[#8FBC8B]/10 flex items-center justify-center">
            <span class="text-xl">{{ activity.icon }}</span>
          </div>
          <div class="flex-1">
            <p class="font-medium text-gray-800">{{ activity.title }}</p>
            <p class="text-sm text-gray-500">{{ activity.description }}</p>
          </div>
          <span class="text-sm text-gray-500">{{ activity.time }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const lastUpdated = ref(new Date().toLocaleString())
const stats = ref([
  { title: 'Average Heart Rate', value: '72 BPM', icon: '❤️', trend: 2.5 },
  { title: 'Sleep Duration', value: '7.5 hrs', icon: '💤', trend: -1.2 },
  { title: 'Steps Today', value: '8,432', icon: '👣', trend: 5.8 },
  { title: 'Calories Burned', value: '2,150', icon: '🔥', trend: 3.1 }
])

const recentActivities = ref([
  { id: 1, icon: '❤️', title: 'Heart Rate Alert', description: 'Heart rate exceeded normal range', time: '5 min ago' },
  { id: 2, icon: '💤', title: 'Sleep Analysis Complete', description: 'Sleep quality: Good', time: '1 hour ago' },
  { id: 3, icon: '👣', title: 'Daily Goal Achieved', description: 'Reached 10,000 steps', time: '2 hours ago' }
])

const refreshData = () => {
  // TODO: Implement data refresh logic
  lastUpdated.value = new Date().toLocaleString()
}

onMounted(() => {
  // TODO: Fetch initial data
})
</script> 