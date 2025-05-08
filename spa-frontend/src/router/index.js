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
      component: () => import('../App.vue')
    },
    {
      path: '/auth',
      name: 'auth',
      component: AuthView,
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
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView
    }
  ]
})

export default router
