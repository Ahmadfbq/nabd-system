<script setup>
import { ref } from 'vue'

const height = ref('')
const weight = ref('')
const age = ref('')

const submitHealthData = () => {
  console.log('Health Data Submitted:', {
    height: height.value,
    weight: weight.value,
    age: age.value,
  })
  // TODO: Send to backend
}
</script>

<template>
    <div class="min-h-screen bg-white p-6 space-y-10">
      <!-- User Info -->
      <div class="bg-gray-100 p-4 rounded-xl shadow space-y-2">
        <h2 class="text-xl font-bold text-[#8FBC8B]">User Info</h2>
        <p><strong>Name:</strong> Ahmad Ebreeq</p>
        <p><strong>Email:</strong> ahmad@example.com</p>
        <p><strong>Phone:</strong> +966 5xxxxxxx</p>
        <button class="text-sm text-[#8FBC8B] underline">Reset Password</button>
        <div class="mt-2">
          <h3 class="font-semibold">Emergency Contact</h3>
          <p><strong>Name:</strong> —</p>
          <p><strong>Phone:</strong> —</p>
          <button class="text-sm text-[#8FBC8B] underline mt-1">Edit/Add Contact</button>
        </div>
      </div>
  
      <!-- Health Data Section (Editable) -->
      <div class="bg-gray-100 p-4 rounded-xl shadow space-y-4">
        <h2 class="text-xl font-bold text-[#8FBC8B]">Health Data</h2>
        <form @submit.prevent="submitHealthData" class="space-y-3">
          <div>
            <label class="block text-sm font-medium">Height (cm)</label>
            <input type="number" v-model="height" class="input-field" />
          </div>
          <div>
            <label class="block text-sm font-medium">Weight (kg)</label>
            <input type="number" v-model="weight" class="input-field" />
          </div>
          <div>
            <label class="block text-sm font-medium">Age</label>
            <input type="number" v-model="age" class="input-field" />
          </div>
          <button type="submit" class="bg-[#8FBC8B] text-white px-4 py-2 rounded-xl shadow">Save</button>
        </form>
      </div>
  
      <!-- Device Info -->
      <div class="bg-gray-100 p-4 rounded-xl shadow space-y-2">
        <h2 class="text-xl font-bold text-[#8FBC8B]">Device Info</h2>
        <p><strong>Device ID:</strong> #12345678</p>
        <button class="text-sm text-[#8FBC8B] underline">Pair Device</button>
      </div>
  
      <!-- Action Buttons -->
      <div class="flex flex-col gap-4 mt-6">
        <button class="bg-[#8FBC8B] text-white py-2 rounded-xl shadow">Logout</button>
        <button class="bg-red-500 text-white py-2 rounded-xl shadow">Delete Account</button>
      </div>
    </div>
</template>
  
  <style scoped>
  .input-field {
    @apply w-full px-3 py-2 border rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-[#8FBC8B];
  }
</style>