<script setup>
import { ref } from 'vue'

const email = ref('')

const handleReset = () => {
  // TODO: call backend reset-password API
  alert(`Sending password reset to ${email.value}`)
}
</script>

<template>
  <form @submit.prevent="handleReset" class="space-y-6">
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
    </div>

    <div class="space-y-4">
      <button 
        type="submit" 
        class="btn-primary w-full py-3 text-base font-semibold"
      >
        Send Reset Link
      </button>

      <p class="text-sm text-gray-500 text-center">
        We'll send you an email with instructions to reset your password.
      </p>
    </div>
  </form>
</template>

<style scoped>
.input-style {
  @apply w-full p-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent transition-all duration-300;
}
</style>