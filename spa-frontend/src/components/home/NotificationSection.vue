<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import notificationService from '@/services/notificationService'

const router = useRouter()
const selectedNotification = ref(null)

const notifications = ref([])

onMounted(async () => {
  try {
    const response = await notificationService.getNotifications();
    if (response.data) {
      notifications.value = response.data;
    }
  } catch (error) {
    console.error('Error fetching notifications:', error);
  }
});

const getTypeColor = (type) => {
  const colors = {
    alert: 'bg-red-100 text-red-800 border-red-200',
    info: 'bg-blue-100 text-blue-800 border-blue-200',
    success: 'bg-green-100 text-green-800 border-green-200',
    warning: 'bg-yellow-100 text-yellow-800 border-yellow-200'
  }
  return colors[type] || colors.info
}

const getTypeIcon = (type) => {
  const icons = {
    alert: '⚠️',
    info: 'ℹ️',
    success: '✅',
    warning: '⚡'
  }
  return icons[type] || icons.info
}

const markAllAsRead = async () => {
  try {
    await Promise.all(notifications.value.map(notification => 
      notificationService.markAsRead(notification.id)
    ));
    notifications.value = notifications.value.map(notification => ({
      ...notification,
      read: true
    }));
  } catch (error) {
    console.error('Error marking notifications as read:', error);
  }
};

const unreadCount = computed(() => {
  return notifications.value.filter(n => !n.read).length
})

const handleAction = async (notification) => {
  try {
    await notificationService.markAsRead(notification.id);
    notifications.value = notifications.value.map(n => 
      n.id === notification.id ? { ...n, read: true } : n
    );
    selectedNotification.value = notification;
  } catch (error) {
    console.error('Error marking notification as read:', error);
  }
};

const viewAllNotifications = () => {
  router.push('/notifications')
}

const addNotification = async (notification) => {
  try {
    const response = await notificationService.updateNotificationSettings(notification);
    if (response.data) {
      notifications.value = [response.data, ...notifications.value];
    }
  } catch (error) {
    console.error('Error adding notification:', error);
  }
};

// Expose the addNotification function to parent component
defineExpose({
  addNotification
})
</script>

<template>
  <div class="bg-white rounded-2xl shadow-sm p-6 space-y-6">
    <div class="flex justify-between items-center">
      <div class="flex items-center space-x-2">
        <h2 class="text-xl font-bold text-gray-800">Notifications</h2>
        <span v-if="unreadCount > 0" class="bg-[#8FBC8B] text-white text-xs px-2 py-1 rounded-full">
          {{ unreadCount }} new
        </span>
      </div>
      <button 
        @click="markAllAsRead"
        class="text-sm text-[#8FBC8B] hover:underline font-medium"
      >
        Mark all as read
      </button>
    </div>

    <div class="space-y-3">
      <div v-for="note in notifications" :key="note.id"
        class="bg-gray-50 rounded-xl p-3 hover:shadow-md transition-all duration-300"
        :class="{ 'opacity-75': note.read }"
      >
        <div class="flex items-center space-x-3">
          <div class="flex-shrink-0 w-8 h-8 bg-white rounded-full flex items-center justify-center shadow-sm">
            <span class="text-lg">{{ note.icon }}</span>
          </div>
          <div class="flex-1 min-w-0">
            <div class="flex items-center justify-between">
              <p class="text-sm font-medium text-gray-900">{{ note.message }}</p>
              <span :class="['text-xs px-2 py-1 rounded-full', getTypeColor(note.type)]">
                {{ note.type }}
              </span>
            </div>
            <div class="flex items-center justify-between mt-1">
              <p class="text-xs text-gray-500">{{ note.timestamp }}</p>
              <button 
                @click="handleAction(note)"
                class="text-xs text-[#8FBC8B] hover:underline font-medium"
              >
                {{ note.action }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <button 
      @click="viewAllNotifications"
      class="w-full py-3 text-[#8FBC8B] font-semibold rounded-xl border-2 border-[#8FBC8B] hover:bg-[#8FBC8B] hover:text-white transition-colors duration-300"
    >
      View All Notifications
    </button>

    <!-- Modal with Transition -->
    <Transition
      enter-active-class="transition duration-300 ease-out"
      enter-from-class="transform scale-95 opacity-0"
      enter-to-class="transform scale-100 opacity-100"
      leave-active-class="transition duration-200 ease-in"
      leave-from-class="transform scale-100 opacity-100"
      leave-to-class="transform scale-95 opacity-0"
    >
      <div v-if="selectedNotification" 
        class="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50 p-4"
        @click="selectedNotification = null"
      >
        <div class="bg-white p-6 rounded-2xl shadow-xl w-full max-w-md space-y-6"
          @click.stop
        >
          <div class="flex justify-between items-center">
            <div class="flex items-center space-x-3">
              <div class="w-10 h-10 bg-gray-50 rounded-full flex items-center justify-center">
                <span class="text-xl">{{ selectedNotification.icon }}</span>
              </div>
              <div>
                <h3 class="text-lg font-bold text-gray-800">{{ selectedNotification.type }}</h3>
                <p class="text-sm text-gray-500">{{ selectedNotification.timestamp }}</p>
              </div>
            </div>
            <button @click="selectedNotification = null" class="text-gray-500 hover:text-gray-700">
              ✕
            </button>
          </div>

          <div class="space-y-4">
            <div class="bg-gray-50 rounded-xl p-4">
              <p class="text-gray-800 font-medium">{{ selectedNotification.message }}</p>
              <p class="text-sm text-gray-600 mt-2">{{ selectedNotification.details }}</p>
            </div>

            <button 
              @click="() => {
                selectedNotification = null;
                router.push(selectedNotification.route);
              }"
              class="w-full py-3 bg-[#8FBC8B] text-white font-semibold rounded-xl hover:bg-green-600 transition-colors duration-300"
            >
              {{ selectedNotification.action }}
            </button>
          </div>
        </div>
      </div>
    </Transition>
  </div>
</template>