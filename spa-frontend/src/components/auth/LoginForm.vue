<script setup>
import { ref } from 'vue'

const email = ref('')
const password = ref('')

const emit = defineEmits(['reset-password'])

const handleLogin = () => {
  // TODO: call backend login API
  alert(`Logging in with ${email.value}`)
}

const handleResetClick = () => {
  emit('reset-password')
}
</script>

<template>
  <form @submit.prevent="handleLogin" class="space-y-6">
    <div class="space-y-4">
      <div>
        <label for="email" class="block text-sm font-medium text-gray-700 mb-1">Email</label>
        <div class="relative">
          <input 
            id="email"
            v-model="email" 
            type="email" 
            class="input-style pl-10" 
            placeholder="Enter your email"
            required 
          />
          <span class="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
            ✉️
          </span>
        </div>
      </div>

      <div>
        <label for="password" class="block text-sm font-medium text-gray-700 mb-1">Password</label>
        <div class="relative">
          <input 
            id="password"
            v-model="password" 
            type="password" 
            class="input-style pl-10" 
            placeholder="Enter your password"
            required 
          />
          <span class="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
            🔒
          </span>
        </div>
      </div>
    </div>

    <div class="flex items-center justify-between">
      <button 
        type="submit" 
        class="btn-primary px-8 py-3 text-base font-semibold"
      >
        Sign In
      </button>
      <button 
        type="button" 
        @click="handleResetClick" 
        class="text-sm text-[#8FBC8B] hover:underline font-medium"
      >
        Forgot Password?
      </button>
    </div>
  </form>
</template>

<style scoped>
.input-style {
  @apply w-full p-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent transition-all duration-300;
}
</style>