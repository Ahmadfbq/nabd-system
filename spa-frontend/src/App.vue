<script setup>
import { RouterView } from 'vue-router'
import { ref, computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const isWelcomePage = computed(() => route.path === '/')
const isAuthPage = computed(() => route.path.startsWith('/auth/'))

// Navigation items
const navItems = [
  { name: 'Home', path: '/home', icon: '🏠' },
  { name: 'Profile', path: '/profile', icon: '👤' }
]

const isSidebarOpen = ref(true)
const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value
}

const handleLogout = () => {
  localStorage.removeItem('accessToken')
  localStorage.removeItem('refreshToken')
  window.location.href = '/'
}
</script>

<template>
  <!-- Welcome/Auth Pages -->
  <div v-if="isWelcomePage || isAuthPage">
    <RouterView />
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

          <!-- Logout Button -->
          <button
            @click="handleLogout"
            class="w-full flex items-center space-x-3 p-3 rounded-xl text-gray-600 hover:bg-gray-100 transition-colors duration-200"
          >
            <span class="text-xl">🚪</span>
            <span v-if="isSidebarOpen" class="font-medium">Logout</span>
          </button>
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