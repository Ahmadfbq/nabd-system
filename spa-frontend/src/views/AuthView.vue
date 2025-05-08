<script setup>
import { ref } from 'vue'
import LoginForm from '../components/auth/LoginForm.vue'
import RegisterForm from '../components/auth/RegisterForm.vue'
import ResetPasswordForm from '../components/auth/ResetPasswordForm.vue'

const tab = ref('login')
const showResetPassword = ref(false)

const activeTab = 'bg-[#8FBC8B] text-white shadow-md'
const inactiveTab = 'bg-gray-100 text-gray-700 hover:bg-gray-200'

const handleResetClick = () => {
  showResetPassword.value = true
}

const handleBackToLogin = () => {
  showResetPassword.value = false
}
</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-[#8FBC8B] to-green-600 p-6 relative overflow-hidden">
    <!-- Decorative elements -->
    <div class="absolute inset-0 overflow-hidden">
      <div class="absolute -top-40 -right-40 w-80 h-80 bg-white/10 rounded-full blur-3xl"></div>
      <div class="absolute -bottom-40 -left-40 w-80 h-80 bg-white/10 rounded-full blur-3xl"></div>
    </div>

    <div class="w-full max-w-md relative z-10">
      <div class="bg-white/95 backdrop-blur-sm rounded-3xl shadow-2xl p-8 md:p-10">
        <!-- Logo and Title -->
        <div class="text-center mb-8">
          <div class="w-16 h-16 mx-auto bg-[#8FBC8B] rounded-2xl shadow-lg flex items-center justify-center mb-4">
            <span class="text-2xl">❤️</span>
          </div>
          <h2 class="text-2xl font-bold text-gray-800">
            {{ showResetPassword ? 'Reset Password' : (tab === 'login' ? 'Welcome Back' : 'Create Account') }}
          </h2>
          <p class="text-gray-500 mt-2">
            {{ showResetPassword ? 'Enter your email to reset your password' : 
               (tab === 'login' ? 'Sign in to your account' : 'Join our health community') }}
          </p>
        </div>

        <div v-if="!showResetPassword">
          <!-- Tab Navigation -->
          <div class="flex space-x-2 mb-8 bg-gray-100 p-1 rounded-xl">
            <button
              class="flex-1 py-3 rounded-lg text-sm font-semibold transition-all duration-300"
              :class="tab === 'login' ? activeTab : inactiveTab"
              @click="tab = 'login'"
            >Login</button>
            <button
              class="flex-1 py-3 rounded-lg text-sm font-semibold transition-all duration-300"
              :class="tab === 'register' ? activeTab : inactiveTab"
              @click="tab = 'register'"
            >Register</button>
          </div>

          <!-- Forms -->
          <div class="space-y-6">
            <LoginForm v-if="tab === 'login'" @reset-password="handleResetClick" />
            <RegisterForm v-if="tab === 'register'" />
          </div>
        </div>

        <div v-else>
          <div class="mb-6">
            <button
              class="text-[#8FBC8B] hover:underline flex items-center font-medium"
              @click="handleBackToLogin"
            >
              <span class="mr-2">←</span> Back to Login
            </button>
          </div>
          <ResetPasswordForm />
        </div>
      </div>
    </div>
  </div>
</template>