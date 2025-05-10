<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const email = ref('')
const password = ref('')
const error = ref('')
const loading = ref(false)

const login = async () => {
  try {
    loading.value = true
    error.value = ''

    const response = await axios.post('http://localhost:8080/api/v1/auth/authenticate', {
      email: email.value,
      password: password.value
    })

    // Store tokens
    localStorage.setItem('accessToken', response.data.accessToken)
    localStorage.setItem('refreshToken', response.data.refreshToken)

    // Redirect to home
    router.push('/home')
  } catch (err) {
    error.value = err.response?.data?.message || 'Login failed. Please try again.'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-[#8FBC8B] to-green-600 p-6">
    <div class="bg-white/95 backdrop-blur-sm rounded-3xl shadow-2xl p-8 md:p-12 max-w-md w-full">
      <div class="text-center mb-8">
        <h1 class="text-3xl font-bold text-[#8FBC8B]">Welcome Back</h1>
        <p class="text-gray-600 mt-2">Please sign in to continue</p>
      </div>

      <form @submit.prevent="login" class="space-y-6">
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

        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">Password</label>
          <input
            v-model="password"
            type="password"
            required
            class="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent"
            placeholder="Enter your password"
          />
        </div>

        <div class="flex items-center justify-between">
          <router-link to="/auth/forgot-password" class="text-sm text-[#8FBC8B] hover:underline">
            Forgot password?
          </router-link>
        </div>

        <button
          type="submit"
          :disabled="loading"
          class="w-full bg-[#8FBC8B] text-white py-3 rounded-xl font-semibold hover:bg-green-600 transition-colors disabled:opacity-50"
        >
          {{ loading ? 'Signing in...' : 'Sign In' }}
        </button>

        <p v-if="error" class="text-red-500 text-sm text-center">{{ error }}</p>

        <p class="text-center text-gray-600">
          Don't have an account?
          <router-link to="/auth/register" class="text-[#8FBC8B] hover:underline">
            Sign up
          </router-link>
        </p>
      </form>
    </div>
  </div>
</template>
