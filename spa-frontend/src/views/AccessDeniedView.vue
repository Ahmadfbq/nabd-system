<template>
  <div class="min-h-screen bg-gray-50 flex items-center justify-center p-6">
    <div class="max-w-md w-full text-center space-y-8">
      <div class="w-24 h-24 mx-auto bg-[#8FBC8B]/10 rounded-full flex items-center justify-center">
        <span class="text-4xl">🔒</span>
      </div>
      
      <div class="space-y-4">
        <h1 class="text-4xl font-bold text-gray-800">Access Denied</h1>
        <h2 class="text-2xl font-semibold text-gray-700">Unauthorized Access</h2>
        <p class="text-gray-500">You don't have permission to access this page. Please log in or contact support if you believe this is an error.</p>
      </div>

      <div class="space-y-4">
        <RouterLink 
          to="/auth/login"
          class="btn-primary block w-full py-3 text-lg font-semibold"
        >
          Log In
        </RouterLink>
        <RouterLink 
          to="/"
          class="text-[#8FBC8B] hover:underline font-medium block"
        >
          Go Home
        </RouterLink>
      </div>
    </div>
  </div>
</template>

<style scoped>
.btn-primary {
  @apply bg-[#8FBC8B] text-white font-semibold py-2 px-4 rounded-xl hover:bg-green-600 transition-all duration-300 shadow-md;
}
</style> 