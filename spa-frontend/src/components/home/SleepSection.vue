<script setup>
import { ref, computed } from 'vue'

const selectedDay = ref(null)
const sleepWeek = ref([
  { day: 'Sun', fullDay: 'Sunday', date: '2024-03-17', duration: 7.5, quality: 'Good', restful: true, deepSleep: '2h 15m', lightSleep: '4h 45m', remSleep: '30m' },
  { day: 'Mon', fullDay: 'Monday', date: '2024-03-18', duration: 6, quality: 'Average', restful: false, deepSleep: '1h 30m', lightSleep: '3h 45m', remSleep: '45m' },
  { day: 'Tue', fullDay: 'Tuesday', date: '2024-03-19', duration: 8, quality: 'Excellent', restful: true, deepSleep: '2h 30m', lightSleep: '4h 30m', remSleep: '1h' },
  { day: 'Wed', fullDay: 'Wednesday', date: '2024-03-20', duration: 5.5, quality: 'Poor', restful: false, deepSleep: '1h 15m', lightSleep: '3h 30m', remSleep: '45m' },
  { day: 'Thu', fullDay: 'Thursday', date: '2024-03-21', duration: 7, quality: 'Good', restful: true, deepSleep: '2h', lightSleep: '4h', remSleep: '1h' },
  { day: 'Fri', fullDay: 'Friday', date: '2024-03-22', duration: 6.5, quality: 'Good', restful: true, deepSleep: '1h 45m', lightSleep: '4h', remSleep: '45m' },
  { day: 'Sat', fullDay: 'Saturday', date: '2024-03-23', duration: 8.5, quality: 'Excellent', restful: true, deepSleep: '2h 45m', lightSleep: '4h 45m', remSleep: '1h' },
])

const getQualityColor = (quality) => {
  const colors = {
    'Excellent': 'bg-blue-100 text-blue-800',
    'Good': 'bg-green-100 text-green-800',
    'Average': 'bg-yellow-100 text-yellow-800',
    'Poor': 'bg-red-100 text-red-800'
  }
  return colors[quality] || colors.Average
}

const averageSleep = computed(() => {
  const total = sleepWeek.value.reduce((sum, day) => sum + day.duration, 0)
  return (total / sleepWeek.value.length).toFixed(1)
})

const formatDate = (dateString) => {
  const date = new Date(dateString)
  return date.toLocaleDateString('en-US', { month: 'long', day: 'numeric', year: 'numeric' })
}

const generateSleepData = () => {
  const now = new Date()
  const yesterday = new Date(now.getTime() - (24 * 60 * 60 * 1000))
  
  // Generate random sleep data for yesterday
  const duration = (6 + Math.random() * 3).toFixed(1) // 6-9 hours
  const deepSleep = Math.floor(1 + Math.random() * 2) // 1-3 hours
  const lightSleep = Math.floor(3 + Math.random() * 2) // 3-5 hours
  const remSleep = Math.floor(0.5 + Math.random() * 1) // 0.5-1.5 hours
  
  // Determine quality based on duration and deep sleep
  let quality = 'Average'
  if (duration >= 7.5 && deepSleep >= 2) quality = 'Excellent'
  else if (duration >= 7 && deepSleep >= 1.5) quality = 'Good'
  else if (duration < 6) quality = 'Poor'
  
  const newSleepData = {
    day: yesterday.toLocaleDateString('en-US', { weekday: 'short' }),
    fullDay: yesterday.toLocaleDateString('en-US', { weekday: 'long' }),
    date: yesterday.toISOString().split('T')[0],
    duration: parseFloat(duration),
    quality,
    restful: quality !== 'Poor',
    deepSleep: `${deepSleep}h ${Math.floor(Math.random() * 60)}m`,
    lightSleep: `${lightSleep}h ${Math.floor(Math.random() * 60)}m`,
    remSleep: `${remSleep}h ${Math.floor(Math.random() * 60)}m`
  }
  
  // Update the sleep week data
  sleepWeek.value = [newSleepData, ...sleepWeek.value.slice(0, 6)]
  
  return newSleepData
}

// Expose the generate function to parent component
defineExpose({
  generateSleepData
})
</script>

<template>
  <div class="bg-white rounded-2xl shadow-sm p-6 space-y-6">
    <div class="flex justify-between items-center">
      <h2 class="text-xl font-bold text-gray-800">Sleep Pattern</h2>
      <div class="text-sm text-gray-500">
        Avg: <span class="font-semibold">{{ averageSleep }}h</span>
      </div>
    </div>

    <div class="overflow-x-auto pb-2">
      <div class="grid grid-cols-7 gap-4 min-w-[800px]">
        <div
          v-for="day in sleepWeek"
          :key="day.date"
          @click="selectedDay = day"
          class="bg-gray-50 rounded-xl p-4 cursor-pointer hover:shadow-md transition-all duration-300 h-40 flex flex-col justify-between"
        >
          <div>
            <p class="font-semibold text-gray-800 text-lg">{{ day.day }}</p>
          </div>
          <div class="text-center">
            <p class="text-2xl font-bold text-[#8FBC8B]">{{ day.duration }}h</p>
            <span :class="['text-sm px-3 py-1 rounded-full inline-block mt-2', getQualityColor(day.quality)]">
              {{ day.quality }}
            </span>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal with Transition -->
    <Transition
      enter-active-class="transition duration-300 ease-out"
      enter-from-class="transform scale-95 opacity-0"
      enter-to-class="transform scale-100 opacity-100"
      leave-active-class="transition duration-200 ease-in"
      leave-from-class="transform scale-100 opacity-100"
      leave-to-class="transform scale-95 opacity-0"
    >
      <div v-if="selectedDay" 
        class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50 p-4"
        @click="selectedDay = null"
      >
        <div class="bg-white p-6 rounded-2xl shadow-xl w-full max-w-md space-y-6"
          @click.stop
        >
          <div class="flex justify-between items-center">
            <div>
              <h3 class="text-xl font-bold text-gray-800">{{ selectedDay.fullDay }}</h3>
              <p class="text-gray-500">{{ formatDate(selectedDay.date) }}</p>
            </div>
            <button @click="selectedDay = null" class="text-gray-500 hover:text-gray-700">
              ✕
            </button>
          </div>

          <div class="space-y-4">
            <div class="bg-gray-50 rounded-xl p-4">
              <div class="flex justify-between items-center mb-2">
                <span class="text-gray-600">Total Duration</span>
                <span class="text-2xl font-bold text-[#8FBC8B]">{{ selectedDay.duration }}h</span>
              </div>
              <div class="h-2 bg-gray-200 rounded-full overflow-hidden">
                <div class="h-full bg-[#8FBC8B]" :style="{ width: `${(selectedDay.duration / 8) * 100}%` }"></div>
              </div>
            </div>

            <div class="grid grid-cols-2 gap-4">
              <div class="bg-gray-50 rounded-xl p-4">
                <p class="text-sm text-gray-600 mb-1">Deep Sleep</p>
                <p class="text-lg font-semibold text-gray-800">{{ selectedDay.deepSleep }}</p>
              </div>
              <div class="bg-gray-50 rounded-xl p-4">
                <p class="text-sm text-gray-600 mb-1">Light Sleep</p>
                <p class="text-lg font-semibold text-gray-800">{{ selectedDay.lightSleep }}</p>
              </div>
              <div class="bg-gray-50 rounded-xl p-4">
                <p class="text-sm text-gray-600 mb-1">REM Sleep</p>
                <p class="text-lg font-semibold text-gray-800">{{ selectedDay.remSleep }}</p>
              </div>
              <div class="bg-gray-50 rounded-xl p-4">
                <p class="text-sm text-gray-600 mb-1">Quality</p>
                <span :class="['text-sm px-2 py-1 rounded-full', getQualityColor(selectedDay.quality)]">
                  {{ selectedDay.quality }}
                </span>
              </div>
            </div>
          </div>

          <button 
            @click="selectedDay = null" 
            class="w-full py-3 bg-[#8FBC8B] text-white font-semibold rounded-xl hover:bg-green-600 transition-colors duration-300"
          >
            Close
          </button>
        </div>
      </div>
    </Transition>
  </div>
</template>
  