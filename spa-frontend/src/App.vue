<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const isWelcomePage = computed(() => route.path === '/')
const isAuthPage = computed(() => route.path.startsWith('/auth/'))

// Navigation items
const navItems = [
  { name: 'Dashboard', path: '/dashboard', icon: '📊' },
  { name: 'Health Data', path: '/health-data', icon: '❤️' },
  { name: 'Sleep Analysis', path: '/sleep-analysis', icon: '💤' },
  { name: 'Notifications', path: '/notifications', icon: '🔔' },
  { name: 'Profile', path: '/profile', icon: '👤' }
]

const isSidebarOpen = ref(true)
const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value
}
</script>

<template>
  <!-- Welcome/Auth Pages -->
  <div v-if="isWelcomePage || isAuthPage" class="min-h-screen bg-gradient-to-br from-[#8FBC8B] to-green-600 flex items-center justify-center p-6 relative overflow-hidden">
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

  <!-- Main App Layout -->
  <div v-else class="min-h-screen bg-gray-50">
    <!-- Sidebar -->
    <aside 
      class="fixed top-0 left-0 h-full bg-white shadow-lg transition-all duration-300 z-50"
      :class="isSidebarOpen ? 'w-64' : 'w-20'"
    >
      <div class="p-4 flex items-center justify-between">
        <div class="flex items-center space-x-3">
          <div class="w-10 h-10 bg-[#8FBC8B] rounded-xl flex items-center justify-center">
            <span class="text-xl">❤️</span>
          </div>
          <h1 v-if="isSidebarOpen" class="text-xl font-bold text-[#8FBC8B]">Nabd</h1>
        </div>
        <button @click="toggleSidebar" class="p-2 rounded-lg hover:bg-gray-100">
          <span class="text-xl">{{ isSidebarOpen ? '←' : '→' }}</span>
        </button>
      </div>

      <nav class="mt-8">
        <div class="px-4 space-y-2">
          <RouterLink
            v-for="item in navItems"
            :key="item.path"
            :to="item.path"
            class="flex items-center space-x-3 p-3 rounded-xl transition-colors duration-200"
            :class="[
              route.path === item.path 
                ? 'bg-[#8FBC8B] text-white' 
                : 'text-gray-600 hover:bg-gray-100'
            ]"
          >
            <span class="text-xl">{{ item.icon }}</span>
            <span v-if="isSidebarOpen" class="font-medium">{{ item.name }}</span>
          </RouterLink>
        </div>
      </nav>
    </aside>

    <!-- Main Content -->
    <main 
      class="transition-all duration-300"
      :class="isSidebarOpen ? 'ml-64' : 'ml-20'"
    >
      <div class="p-8">
        <RouterView />
      </div>
    </main>
  </div>
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

/* Custom scrollbar */
::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #8FBC8B;
  border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
  background: #7daa78;
}
</style>