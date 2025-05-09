<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'
import  { Dashboard } from './components/Dashboard.vue';

const route = useRoute()
const isWelcomePage = computed(() => route.path === '/')
</script>

<template>
  <div v-if="isWelcomePage" class="min-h-screen bg-gradient-to-br from-[#8FBC8B] to-green-600 flex items-center justify-center p-6 relative overflow-hidden">
    <!-- Decorative elements -->
    <div class="absolute inset-0 overflow-hidden">
      <div class="absolute -top-40 -right-40 w-80 h-80 bg-white/10 rounded-full blur-3xl"></div>
      <div class="absolute -bottom-40 -left-40 w-80 h-80 bg-white/10 rounded-full blur-3xl"></div>
    </div>

    <div class="bg-white/95 backdrop-blur-sm rounded-3xl shadow-2xl p-8 md:p-12 max-w-md w-full text-center space-y-8 relative z-10 transform transition-all duration-300 hover:scale-[1.02]">
      <!-- Logo placeholder -->
      <div class="w-20 h-20 mx-auto bg-[#8FBC8B] rounded-2xl shadow-lg flex items-center justify-center">
        <span class="text-3xl">❤️</span>
      </div>

      <div class="space-y-4">
        <h1 class="text-4xl md:text-5xl font-bold text-[#8FBC8B] tracking-tight">Welcome to Nabd</h1>
        <p class="text-gray-600 text-lg">Your personal health monitoring companion</p>
      </div>

      <div class="space-y-6">
        <RouterLink 
          to="/auth/register" 
          class="btn-primary block w-full py-4 text-lg font-semibold transform transition-all duration-300 hover:scale-[1.02] hover:shadow-lg"
        >
          Get Started
        </RouterLink>
        <p class="text-sm text-gray-500">
          Already have an account? 
          <RouterLink 
            to="/auth/login" 
            class="text-[#8FBC8B] hover:underline font-medium"
          >
            Login here
          </RouterLink>
        </p>
      </div>

      <Dashboard />
      <!-- Features preview -->
      <div class="grid grid-cols-2 gap-4 pt-6 border-t border-gray-100">
        <div class="text-center p-4 rounded-xl bg-gray-50">
          <span class="text-2xl mb-2 block">📊</span>
          <p class="text-sm text-gray-600">Health Tracking</p>
        </div>
        <div class="text-center p-4 rounded-xl bg-gray-50">
          <span class="text-2xl mb-2 block">💤</span>
          <p class="text-sm text-gray-600">Sleep Analysis</p>
        </div>
      </div>
    </div>
  </div>
  <RouterView v-else />
</template>

<style>
body {
  margin: 0;
  font-family: 'Inter', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.btn-primary {
  @apply bg-[#8FBC8B] text-white font-semibold py-2 px-4 rounded-xl hover:bg-green-600 transition-all duration-300 shadow-md;
}
</style>