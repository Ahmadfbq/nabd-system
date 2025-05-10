<script setup>
import { ref } from 'vue'
import axios from 'axios'

const email = ref('')
const error = ref('')
const success = ref('')
const loading = ref(false)

const resetPassword = async () => {
  try {
    loading.value = true
    error.value = ''
    success.value = ''
    
    await axios.post('http://localhost:8080/api/v1/auth/reset-password', {
      email: email.value
    })

    success.value = 'Password reset instructions have been sent to your email.'
  } catch (err) {
    error.value = err.response?.data?.message || 'Failed to send reset instructions. Please try again.'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="min-h-screen w-full flex items-center justify-center bg-gradient-to-br from-[#8FBC8B] to-green-600 p-0 md:p-6">
    <div class="bg-white/95 backdrop-blur-sm rounded-3xl shadow-2xl px-4 py-8 sm:p-8 md:p-12 w-full max-w-lg text-center space-y-8 relative z-10 transition-all duration-300">
      <div class="w-16 h-16 mx-auto bg-[#8FBC8B] rounded-2xl shadow-lg flex items-center justify-center mb-2">
        <span class="text-3xl">❤️</span>
      </div>
      <div>
        <h1 class="text-3xl sm:text-4xl font-extrabold text-[#4B7156] mb-2">Reset Password</h1>
        <p class="text-gray-600 text-base sm:text-lg">Enter your email to receive reset instructions</p>
      </div>
      <form @submit.prevent="resetPassword" class="space-y-6">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">Email</label>
          <input
            v-model="email"
            type="email"
            required
            class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
            placeholder="Enter your email"
          />
        </div>
        <button
          type="submit"
          :disabled="loading"
          class="w-full bg-[#8FBC8B] text-white py-3 rounded-xl font-semibold hover:bg-green-600 transition-all duration-200 focus:outline-none focus:ring-2 focus:ring-[#8FBC8B] focus:ring-offset-2"
        >
          {{ loading ? 'Sending...' : 'Send Reset Instructions' }}
        </button>
        <p v-if="error" class="text-red-500 text-sm text-center">{{ error }}</p>
        <p v-if="success" class="text-green-500 text-sm text-center">{{ success }}</p>
        <p class="text-center text-gray-600">
          Remember your password?
          <router-link to="/auth/login" class="text-[#8FBC8B] hover:underline">
            Sign in
          </router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<style scoped>
body {
  font-family: 'Inter', 'Segoe UI', Arial, sans-serif;
}
</style> 