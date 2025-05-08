<script setup>
import { ref } from 'vue'

const email = ref('')
const password = ref('')

const handleLogin = () => {
  // TODO: call backend login API
  alert(`Logging in with ${email.value}`)
}
</script>

<template>
    <form @submit.prevent="handleLogin" class="space-y-4">
      <input v-model="email" type="email" placeholder="Email" class="input-style" required />
      <input v-model="password" type="password" placeholder="Password" class="input-style" required />
      <button type="submit" class="btn-primary w-full">Login</button>
    </form>
</template>