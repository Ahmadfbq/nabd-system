<script setup>
  import { ref } from 'vue'
  
  const selectedDay = ref(null)
  
  const sleepWeek = [
    { day: 'Sun', duration: 7.5, quality: 'Good', restful: true },
    { day: 'Mon', duration: 6, quality: 'Average', restful: false },
    { day: 'Tue', duration: 8, quality: 'Excellent', restful: true },
    { day: 'Wed', duration: 5.5, quality: 'Poor', restful: false },
    { day: 'Thu', duration: 7, quality: 'Good', restful: true },
    { day: 'Fri', duration: 6.5, quality: 'Good', restful: true },
    { day: 'Sat', duration: 8.5, quality: 'Excellent', restful: true },
  ]
</script>
  

<template>
    <div class="bg-gray-100 p-4 rounded-xl shadow space-y-4">
      <h2 class="text-xl font-bold text-[#8FBC8B]">Sleep Pattern</h2>
  
      <div class="grid grid-cols-7 gap-2">
        <div
          v-for="day in sleepWeek"
          :key="day.day"
          @click="selectedDay = day"
          class="bg-white border rounded-xl p-3 cursor-pointer hover:bg-gray-200"
        >
          <p class="font-semibold">{{ day.day }}</p>
          <p class="text-sm">{{ day.duration }} hrs</p>
        </div>
      </div>
  
      <!-- Modal -->
      <div v-if="selectedDay" class="fixed inset-0 bg-black bg-opacity-30 flex justify-center items-center z-50">
        <div class="bg-white p-6 rounded-xl shadow-xl w-80 text-center space-y-4">
          <h3 class="text-lg font-semibold text-[#8FBC8B]">Sleep Details - {{ selectedDay.day }}</h3>
          <p><strong>Duration:</strong> {{ selectedDay.duration }} hrs</p>
          <p><strong>Quality:</strong> {{ selectedDay.quality }}</p>
          <p><strong>Restful:</strong> {{ selectedDay.restful ? 'Yes' : 'No' }}</p>
          <button @click="selectedDay = null" class="btn-primary w-full">Close</button>
        </div>
      </div>
    </div>
</template>
  
