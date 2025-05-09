<script setup>
import { ref, computed, onMounted } from 'vue'
import analysisService from '@/services/analysisService'

const selectedDay = ref(null)
const sleepWeek = ref([])

onMounted(async () => {
  try {
    const response = await analysisService.getAnalysisResults();
    if (response.data) {
      sleepWeek.value = response.data;
    }
  } catch (error) {
    console.error('Error fetching sleep data:', error);
  }
});

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

const generateSleepData = async () => {
  try {
    const mockData = {
      day: new Date().toLocaleDateString('en-US', { weekday: 'short' }),
      fullDay: new Date().toLocaleDateString('en-US', { weekday: 'long' }),
      date: new Date().toISOString().split('T')[0],
      duration: 7.5,
      quality: 'Good',
      restful: true,
      deepSleep: '2h 15m',
      lightSleep: '4h 45m',
      remSleep: '30m'
    };
    await analysisService.requestAnalysis(mockData);
    const response = await analysisService.getAnalysisResults();
    if (response.data) {
      sleepWeek.value = response.data;
    }
  } catch (error) {
    console.error('Error recording sleep data:', error);
  }
};

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
  