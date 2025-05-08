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
    <form @submit.prevent="handleRegister" class="space-y-4">
      <input v-model="name" type="text" placeholder="Name" class="input-style" required />
      <input v-model="email" type="email" placeholder="Email" class="input-style" required />
      <input v-model="password" type="password" placeholder="Password" class="input-style" required />
      <button type="submit" class="btn-primary w-full">Register</button>
    </form>
</template>