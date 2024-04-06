import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/list',
    name: 'ListView',
    component: () => import(/* webpackChunkName: "list" */ '../views/ListView.vue')
  },
  {
    path: '/menu',
    name: 'menu',
    component: () => import(/* webpackChunkName: "list" */ '../views/MenuView.vue')
  },
  {
    path: '/list/save',
    name: 'SaveView',
    component: () => import(/* webpackChunkName: "list" */ '../views/SaveView.vue')
  },
  {
    path: '/list/create',
    name: 'CreateView',
    component: () => import(/* webpackChunkName: "list" */ '../views/CreateView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
