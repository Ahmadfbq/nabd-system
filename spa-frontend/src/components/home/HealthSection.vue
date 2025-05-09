<script setup>
import { ref, computed } from 'vue'

const selected = ref('All')
const types = ['All', 'Heart Rate', 'Oxygen', 'Blood Pressure', 'Blood Sugar', 'Temperature']
const hasData = ref(false)

// Data structure for health readings
const healthData = ref({
  heartRate: [],
  oxygen: [],
  bloodPressure: [],
  bloodSugar: [],
  temperature: null
})

// Generate realistic health data for the last 10 hours
const generateHealthData = () => {
  const now = new Date()
  const tenHoursAgo = new Date(now.getTime() - (10 * 60 * 60 * 1000))
  
  // Heart Rate (every second, but we'll store every minute for performance)
  const heartRateData = []
  for (let time = tenHoursAgo; time <= now; time = new Date(time.getTime() + 60000)) {
    heartRateData.push({
      timestamp: time,
      value: Math.floor(60 + Math.random() * 40), // 60-100 bpm
      status: 'normal'
    })
  }
  
  // Oxygen (every 30 seconds, stored every 5 minutes)
  const oxygenData = []
  for (let time = tenHoursAgo; time <= now; time = new Date(time.getTime() + 300000)) {
    oxygenData.push({
      timestamp: time,
      value: (95 + Math.random() * 3).toFixed(1), // 95-98%
      status: 'excellent'
    })
  }
  
  // Blood Pressure (every minute, stored every 15 minutes)
  const bloodPressureData = []
  for (let time = tenHoursAgo; time <= now; time = new Date(time.getTime() + 900000)) {
    const systolic = Math.floor(110 + Math.random() * 20) // 110-130
    const diastolic = Math.floor(70 + Math.random() * 10) // 70-80
    bloodPressureData.push({
      timestamp: time,
      value: `${systolic}/${diastolic}`,
      status: 'normal'
    })
  }
  
  // Blood Sugar (every 5 minutes)
  const bloodSugarData = []
  for (let time = tenHoursAgo; time <= now; time = new Date(time.getTime() + 300000)) {
    bloodSugarData.push({
      timestamp: time,
      value: Math.floor(80 + Math.random() * 40), // 80-120 mg/dL
      status: 'normal'
    })
  }
  
  // Temperature (once per day)
  const temperatureData = {
    timestamp: new Date(now.getTime() - (8 * 60 * 60 * 1000)), // 8 hours ago (during sleep)
    value: (36.5 + Math.random() * 0.5).toFixed(1), // 36.5-37.0°C
    status: 'normal'
  }
  
  healthData.value = {
    heartRate: heartRateData,
    oxygen: oxygenData,
    bloodPressure: bloodPressureData,
    bloodSugar: bloodSugarData,
    temperature: temperatureData
  }
  
  hasData.value = true
}

const getStatusColor = (status) => {
  const colors = {
    normal: 'text-green-600',
    excellent: 'text-blue-600',
    warning: 'text-yellow-600',
    critical: 'text-red-600'
  }
  return colors[status] || colors.normal
}

const getTrendIcon = (data) => {
  if (data.length < 2) return '→'
  const last = data[data.length - 1].value
  const prev = data[data.length - 2].value
  if (typeof last === 'string') {
    const [lastSys, lastDia] = last.split('/').map(Number)
    const [prevSys, prevDia] = prev.split('/').map(Number)
    const sysDiff = lastSys - prevSys
    const diaDiff = lastDia - prevDia
    if (Math.abs(sysDiff) < 5 && Math.abs(diaDiff) < 5) return '→'
    return sysDiff > 0 ? '↗️' : '↘️'
  }
  const diff = last - prev
  if (Math.abs(diff) < 2) return '→'
  return diff > 0 ? '↗️' : '↘️'
}

const formatTime = (timestamp) => {
  return new Date(timestamp).toLocaleTimeString('en-US', { 
    hour: '2-digit', 
    minute: '2-digit',
    hour12: true 
  })
}

const getLatestReadings = computed(() => {
  if (!hasData.value) return []
  
  const latest = {
    'Heart Rate': {
      value: `${healthData.value.heartRate[healthData.value.heartRate.length - 1].value} bpm`,
      timestamp: formatTime(healthData.value.heartRate[healthData.value.heartRate.length - 1].timestamp),
      status: 'normal',
      trend: getTrendIcon(healthData.value.heartRate)
    },
    'Oxygen': {
      value: `${healthData.value.oxygen[healthData.value.oxygen.length - 1].value}%`,
      timestamp: formatTime(healthData.value.oxygen[healthData.value.oxygen.length - 1].timestamp),
      status: 'excellent',
      trend: getTrendIcon(healthData.value.oxygen)
    },
    'Blood Pressure': {
      value: healthData.value.bloodPressure[healthData.value.bloodPressure.length - 1].value,
      timestamp: formatTime(healthData.value.bloodPressure[healthData.value.bloodPressure.length - 1].timestamp),
      status: 'normal',
      trend: getTrendIcon(healthData.value.bloodPressure)
    },
    'Blood Sugar': {
      value: `${healthData.value.bloodSugar[healthData.value.bloodSugar.length - 1].value} mg/dL`,
      timestamp: formatTime(healthData.value.bloodSugar[healthData.value.bloodSugar.length - 1].timestamp),
      status: 'normal',
      trend: getTrendIcon(healthData.value.bloodSugar)
    },
    'Temperature': {
      value: `${healthData.value.temperature.value}°C`,
      timestamp: formatTime(healthData.value.temperature.timestamp),
      status: 'normal',
      trend: '→'
    }
  }
  
  if (selected.value === 'All') {
    return Object.entries(latest).map(([type, data]) => ({
      type,
      ...data
    }))
  }
  
  return [{
    type: selected.value,
    ...latest[selected.value]
  }]
})

// Expose the generate function to parent component
defineExpose({
  generateHealthData
})
</script>

<template>
  <div class="bg-white rounded-2xl shadow-sm p-6 space-y-6">
    <div class="flex justify-between items-center">
      <h2 class="text-xl font-bold text-gray-800">Health Data</h2>
      <span v-if="hasData" class="text-sm text-gray-500">
        Last updated: {{ getLatestReadings[0]?.timestamp }}
      </span>
    </div>

    <div v-if="!hasData" class="text-center py-8">
      <p class="text-gray-500">No health data available</p>
      <p class="text-sm text-gray-400 mt-2">Sync your wearable device to view health data</p>
    </div>

    <template v-else>
      <div class="flex flex-wrap gap-2">
        <button v-for="type in types" :key="type"
          @click="selected = type"
          :class="[
            'px-4 py-2 rounded-xl text-sm font-semibold transition-all duration-300',
            selected === type 
              ? 'bg-[#8FBC8B] text-white shadow-md' 
              : 'bg-gray-50 text-gray-600 hover:bg-gray-100'
          ]">
          {{ type }}
        </button>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <div v-for="entry in getLatestReadings" :key="entry.type" 
          class="bg-gray-50 rounded-xl p-4 hover:shadow-md transition-all duration-300">
          <div class="flex justify-between items-start mb-2">
            <h3 class="font-semibold text-gray-800">{{ entry.type }}</h3>
            <span :class="['text-sm font-medium', getStatusColor(entry.status)]">
              {{ entry.status }}
            </span>
          </div>
          <div class="flex items-baseline justify-between">
            <div>
              <p class="text-2xl font-bold text-gray-900">{{ entry.value }}</p>
              <p class="text-sm text-gray-500">{{ entry.timestamp }}</p>
            </div>
            <span class="text-lg">{{ entry.trend }}</span>
          </div>
        </div>
      </div>
    </template>
  </div>
</template>