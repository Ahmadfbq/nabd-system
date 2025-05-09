<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const height = ref('')
const weight = ref('')
const age = ref('')
const isEditingEmergency = ref(false)
const emergencyContact = ref({
  name: '',
  phone: ''
})

const submitHealthData = () => {
  console.log('Health Data Submitted:', {
    height: height.value,
    weight: weight.value,
    age: age.value,
  })
  // TODO: Send to backend
}

const handleLogout = () => {
  // TODO: Implement logout logic
  router.push('/')
}

const handleDeleteAccount = () => {
  if (confirm('Are you sure you want to delete your account? This action cannot be undone.')) {
    // TODO: Implement account deletion
    router.push('/')
  }
}
</script>

<template>
  <div class="min-h-screen bg-gray-50">
    <!-- Top Navigation -->
    <nav class="bg-white shadow-sm">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between h-16">
          <div class="flex items-center">
            <button @click="router.push('/home')" class="text-gray-600 hover:text-[#8FBC8B]">
              <span class="text-lg">←</span>
            </button>
            <div class="flex-shrink-0 flex items-center ml-4">
              <span class="text-2xl">👤</span>
              <span class="ml-2 text-xl font-bold text-[#8FBC8B]">Profile</span>
            </div>
          </div>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
    <main class="max-w-3xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="space-y-6">
        <!-- User Info Card -->
        <div class="bg-white rounded-2xl shadow-sm p-6 space-y-6">
          <div class="flex items-center space-x-4">
            <div class="w-20 h-20 bg-[#8FBC8B] rounded-full flex items-center justify-center">
              <span class="text-3xl">👤</span>
            </div>
            <div>
              <h2 class="text-2xl font-bold text-gray-800">Ahmad Ebreeq</h2>
              <p class="text-gray-500">ahmad@example.com</p>
              <p class="text-gray-500">+966 5xxxxxxx</p>
            </div>
          </div>

          <div class="border-t border-gray-100 pt-6">
            <div class="flex justify-between items-center mb-4">
              <h3 class="text-lg font-semibold text-gray-800">Emergency Contact</h3>
              <button 
                @click="isEditingEmergency = !isEditingEmergency"
                class="text-[#8FBC8B] hover:underline text-sm"
              >
                {{ isEditingEmergency ? 'Cancel' : 'Edit' }}
              </button>
            </div>

            <div v-if="isEditingEmergency" class="space-y-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Contact Name</label>
                <input 
                  v-model="emergencyContact.name"
                  type="text"
                  class="input-style"
                  placeholder="Enter contact name"
                />
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Phone Number</label>
                <input 
                  v-model="emergencyContact.phone"
                  type="tel"
                  class="input-style"
                  placeholder="Enter phone number"
                />
              </div>
              <button class="btn-primary w-full">Save Contact</button>
            </div>
            <div v-else class="space-y-2">
              <p class="text-gray-600">
                <span class="font-medium">Name:</span> 
                {{ emergencyContact.name || '—' }}
              </p>
              <p class="text-gray-600">
                <span class="font-medium">Phone:</span> 
                {{ emergencyContact.phone || '—' }}
              </p>
            </div>
          </div>
        </div>

        <!-- Health Data Card -->
        <div class="bg-white rounded-2xl shadow-sm p-6">
          <h2 class="text-xl font-bold text-gray-800 mb-6">Health Data</h2>
          <form @submit.prevent="submitHealthData" class="space-y-4">
            <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Height (cm)</label>
                <input 
                  v-model="height"
                  type="number"
                  class="input-style"
                  placeholder="Enter height"
                />
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Weight (kg)</label>
                <input 
                  v-model="weight"
                  type="number"
                  class="input-style"
                  placeholder="Enter weight"
                />
              </div>
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">Age</label>
                <input 
                  v-model="age"
                  type="number"
                  class="input-style"
                  placeholder="Enter age"
                />
              </div>
            </div>
            <button type="submit" class="btn-primary w-full">Save Health Data</button>
          </form>
        </div>

        <!-- Device Info Card -->
        <div class="bg-white rounded-2xl shadow-sm p-6">
          <h2 class="text-xl font-bold text-gray-800 mb-4">Device Info</h2>
          <div class="flex justify-between items-center">
            <div>
              <p class="text-gray-600">
                <span class="font-medium">Device ID:</span> #12345678
              </p>
              <p class="text-sm text-gray-500 mt-1">Last synced: 2 hours ago</p>
            </div>
            <button class="btn-primary">Pair New Device</button>
          </div>
        </div>

        <!-- Action Buttons -->
        <div class="space-y-4">
          <button 
            @click="handleLogout"
            class="w-full py-3 px-4 bg-[#8FBC8B] text-white font-semibold rounded-xl hover:bg-green-600 transition-colors duration-300"
          >
            Logout
          </button>
          <button 
            @click="handleDeleteAccount"
            class="w-full py-3 px-4 bg-red-500 text-white font-semibold rounded-xl hover:bg-red-600 transition-colors duration-300"
          >
            Delete Account
          </button>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
.input-style {
  @apply w-full p-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent transition-all duration-300;
}

.btn-primary {
  @apply bg-[#8FBC8B] text-white font-semibold py-2 px-4 rounded-xl hover:bg-green-600 transition-all duration-300;
}
</style>