<script setup>
const notifications = [
  'Heart rate alert: above normal at 3:10 PM',
  'New sleep record received',
  'Device paired successfully',
]
</script>

<template>
    <div class="bg-gray-100 p-4 rounded-xl shadow space-y-4">
      <h2 class="text-xl font-bold text-[#8FBC8B]">Notifications</h2>
      <ul class="space-y-2">
        <li
          v-for="(note, index) in notifications"
          :key="index"
          class="bg-white border rounded-xl px-4 py-2 text-sm"
        >
          {{ note }}
        </li>
      </ul>
    </div>
</template>
  
