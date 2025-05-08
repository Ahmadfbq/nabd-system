<script setup>
import { ref } from 'vue'

const email = ref('')

const handleReset = () => {
  // TODO: call backend reset-password API
  alert(`Sending password reset to ${email.value}`)
}
</script>

<template>
    <form @submit.prevent="handleReset" class="space-y-4">
      <input v-model="email" type="email" placeholder="Email" class="input-style" required />
      <button type="submit" class="btn-primary w-full">Reset Password</button>
    </form>
</template>