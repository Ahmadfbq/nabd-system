<script setup>
import { ref, computed } from 'vue'

const selected = ref('All')
const types = ['All', 'Heart Rate', 'Oxygen', 'Blood Pressure', 'Blood Sugar', 'Temperature']

const healthData = ref([
  { id: 1, type: 'Heart Rate', value: '72 bpm', timestamp: '10:12 AM' },
  { id: 2, type: 'Oxygen', value: '97%', timestamp: '10:00 AM' },
  { id: 3, type: 'Blood Pressure', value: '120/80', timestamp: '9:58 AM' },
])

const filteredData = computed(() => {
  if (selected.value === 'All') return healthData.value
  return healthData.value.filter(d => d.type === selected.value)
})
</script>

<template>
  <div class="bg-gray-100 p-4 rounded-xl shadow space-y-4">
    <h2 class="text-xl font-bold text-[#8FBC8B]">Health Data</h2>

    <div class="flex flex-wrap gap-2">
      <button v-for="type in types" :key="type"
        @click="selected = type"
        :class="['px-3 py-1 rounded-full text-sm font-semibold', selected === type ? 'bg-[#8FBC8B] text-white' : 'bg-white border']">
        {{ type }}
      </button>
    </div>

    <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-3">
      <div v-for="entry in filteredData" :key="entry.id" class="p-3 bg-white border rounded-xl shadow-sm">
        <p class="font-medium">{{ entry.type }}</p>
        <p class="text-sm text-gray-500">{{ entry.value }} @ {{ entry.timestamp }}</p>
      </div>
    </div>
  </div>
</template>

