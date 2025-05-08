<script> 
    import { ref } from 'vue'
    import LoginForm from '../components/auth/LoginForm.vue'
    import RegisterForm from '../components/auth/RegisterForm.vue'
    import ResetPasswordForm from '../components/auth/ResetPasswordForm.vue'
    
    const tab = ref('login')

    const activeTab = 'bg-[#8FBC8B] text-white'
    const inactiveTab = 'bg-gray-100 text-gray-700 hover:bg-gray-200'
</script>

<template>
    <div class="min-h-screen flex items-center justify-center bg-white">
        <div class="w-full max-w-md p-6 rounded-2xl shadow-xl border border-gray-200">
            <div class="flex justify-center mb-6">
                <!-- <img src="/logo.png" alt="Logo" class="h-12"> -->
            </div>
            <div class="flex space-x-2 mb-6">
                <button
                  class="flex-1 py-2 rounded-xl text-sm font-semibold"
                  :class="tab === 'login' ? activeTab : inactiveTab"
                  @click="tab = 'login'"
                >Login</button>
                <button
                  class="flex-1 py-2 rounded-xl text-sm font-semibold"
                  :class="tab === 'register' ? activeTab : inactiveTab"
                  @click="tab = 'register'"
                >Register</button>
                <button
                  class="flex-1 py-2 rounded-xl text-sm font-semibold"
                  :class="tab === 'reset' ? activeTab : inactiveTab"
                  @click="tab = 'reset'"
                >Reset</button>
              </div>
        
              <div>
                <LoginForm v-if="tab === 'login'" />
                <RegisterForm v-if="tab === 'register'" />
                <ResetPasswordForm v-if="tab === 'reset'" />
              </div>
        </div>
    </div>

</template>