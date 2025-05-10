<template>
  <div class="min-h-screen w-full flex items-center justify-center bg-gradient-to-br from-[#8FBC8B] to-green-600 p-0 md:p-6">
    <div class="bg-white/95 backdrop-blur-sm rounded-3xl shadow-2xl px-4 py-8 sm:p-8 md:p-12 w-full max-w-lg text-center space-y-8 relative z-10 transition-all duration-300">
      <!-- Logo -->
      <div class="w-16 h-16 mx-auto bg-[#8FBC8B] rounded-2xl shadow-lg flex items-center justify-center mb-2">
        <span class="text-3xl">❤️</span>
      </div>

      <div>
        <h1 class="text-3xl sm:text-4xl font-extrabold text-[#4B7156] mb-2">Welcome to <span class="text-[#8FBC8B]">Nabd</span></h1>
        <p class="text-gray-600 text-base sm:text-lg">Your personal health monitoring companion</p>
      </div>

      <RouterLink 
        to="/auth/register" 
        class="block w-full py-3 text-lg font-semibold bg-[#8FBC8B] text-white rounded-xl shadow-md hover:bg-green-600 transition-all duration-200 focus:outline-none focus:ring-2 focus:ring-[#8FBC8B] focus:ring-offset-2 mb-2"
      >
        Get Started
      </RouterLink>
      <p class="text-sm text-gray-500 mb-4">
        Already have an account? 
        <RouterLink 
          to="/auth/login" 
          class="text-[#8FBC8B] hover:underline font-medium"
        >
          Login here
        </RouterLink>
      </p>

      <!-- Features preview -->
      <div class="grid grid-cols-1 sm:grid-cols-2 gap-4 pt-4 border-t border-gray-100">
        <div class="flex flex-col items-center p-4 rounded-xl bg-gray-50 hover:bg-gray-100 transition-colors">
          <span class="text-2xl mb-1">📊</span>
          <span class="text-base font-medium text-gray-700">Health Tracking</span>
        </div>
        <div class="flex flex-col items-center p-4 rounded-xl bg-gray-50 hover:bg-gray-100 transition-colors">
          <span class="text-2xl mb-1">💤</span>
          <span class="text-base font-medium text-gray-700">Sleep Analysis</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
</script>

<style scoped>
body {
  font-family: 'Inter', 'Segoe UI', Arial, sans-serif;
}
</style> 