import { createRouter, createWebHistory } from 'vue-router'
import AuthView from '../views/AuthView.vue'
import HomeView from '../views/HomeView.vue'
import ProfileView from '../views/ProfileView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'welcome',
      component: () => import('../views/WelcomeView.vue')
    },
    {
      path: '/auth',
      name: 'auth',
      component: AuthView,
      meta: { requiresAuth: false },
      children: [
        {
          path: '',  // Default child route
          redirect: 'login'
        },
        {
          path: 'login',
          name: 'login',
          component: () => import('../components/auth/LoginForm.vue')
        },
        {
          path: 'register',
          name: 'register',
          component: () => import('../components/auth/RegisterForm.vue')
        }
      ]
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: () => import('../views/DashboardView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/health-data',
      name: 'health-data',
      component: () => import('../views/HealthDataView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/sleep-analysis',
      name: 'sleep-analysis',
      component: () => import('../views/SleepAnalysisView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/notifications',
      name: 'notifications',
      component: () => import('../views/NotificationsView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView,
      meta: { requiresAuth: true }
    },
    {
      path: '/access-denied',
      name: 'access-denied',
      component: () => import('../views/AccessDeniedView.vue')
    },
    {
      path: '/not-found',
      name: 'not-found',
      component: () => import('../views/NotFoundView.vue')
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/not-found'
    }
  ]
})

// Navigation guard
router.beforeEach((to, from, next) => {
  const isAuthenticated = !!localStorage.getItem('token')
  
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/auth/login')
  } else if (to.path === '/auth' && isAuthenticated) {
    next('/dashboard')
  } else {
    next()
  }
})

export default router