<script setup>
import { ref } from 'vue'

const name = ref('')
const email = ref('')
const password = ref('')

const handleRegister = () => {
  // TODO: call backend register API
  alert(`Registering user: ${name.value}`)
}
</script>

<template>
  <form @submit.prevent="handleRegister" class="space-y-6">
    <div class="space-y-4">
      <div>
        <label for="name" class="block text-sm font-medium text-gray-700 mb-1">Full Name</label>
        <div class="relative">
          <input 
            id="name"
            v-model="name" 
            type="text" 
            class="input-style pl-10" 
            placeholder="Enter your full name"
            required 
          />
          <span class="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
            👤
          </span>
        </div>
      </div>

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
            placeholder="Create a password"
            required 
          />
          <span class="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400">
            🔒
          </span>
        </div>
      </div>
    </div>

    <button 
      type="submit" 
      class="btn-primary w-full py-3 text-base font-semibold"
    >
      Create Account
    </button>

    <p class="text-sm text-gray-500 text-center">
      By creating an account, you agree to our 
      <a href="#" class="text-[#8FBC8B] hover:underline">Terms of Service</a> and 
      <a href="#" class="text-[#8FBC8B] hover:underline">Privacy Policy</a>
    </p>
  </form>
</template>

<style scoped>
.input-style {
  @apply w-full p-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-[#8FBC8B] focus:border-transparent transition-all duration-300;
}
</style>