<script setup>
import { ref, computed, onMounted } from 'vue'
import healthDataService from '@/services/healthDataService'

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

onMounted(async () => {
  try {
    const response = await healthDataService.getHealthData();
    if (response.data) {
      healthData.value = response.data;
      hasData.value = true;
    }
  } catch (error) {
    console.error('Error fetching health data:', error);
  }
});

const generateHealthData = async () => {
  try {
    const mockData = {
      heartRate: [{ timestamp: new Date(), value: 75, status: 'normal' }],
      oxygen: [{ timestamp: new Date(), value: 97, status: 'excellent' }],
      bloodPressure: [{ timestamp: new Date(), value: '120/80', status: 'normal' }],
      bloodSugar: [{ timestamp: new Date(), value: 100, status: 'normal' }],
      temperature: { timestamp: new Date(), value: 36.8, status: 'normal' }
    };
    await healthDataService.submitNewHealthData(mockData);
    const response = await healthDataService.getHealthData();
    if (response.data) {
      healthData.value = response.data;
      hasData.value = true;
    }
  } catch (error) {
    console.error('Error syncing health data:', error);
  }
};

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

      <div class="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-3 gap-4">
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