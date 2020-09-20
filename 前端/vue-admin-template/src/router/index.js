import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/user',
    component: Layout,
    redirect: '/user/user',
    name: 'User',
    meta: { title: '用户管理', icon: 'example' },
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/user/UserList'),
        meta: { title: '用户管理', icon: 'table' }
      },
      {
        path: 'admin',
        name: 'admin',
        component: () => import('@/views/admin/AdminList'),
        meta: { title: '管理员', icon: 'table' }
      }
    ]
  },
  {
    path: '/blog',
    component: Layout,
    redirect: '/blog/blog',
    name: 'Blog',
    meta: { title: '帖子管理', icon: 'example' },
    children: [
      {
        path: 'blog',
        name: 'blog',
        component: () => import('@/views/blog/BlogList'),
        meta: { title: '博客管理', icon: 'table' }
      },
      {
        path: 'type',
        name: 'type',
        component: () => import('@/views/type/TypeList'),
        meta: { title: '分类管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/my',
    component: Layout,
    redirect: '/my/collection',
    name: 'My',
    meta: { title: '评论与收藏', icon: 'example' },
    children: [
      {
        path: 'comment',
        name: 'comment',
        component: () => import('@/views/comment/CommentList'),
        meta: { title: '评论管理', icon: 'table' }
      },
      {
        path: 'collection',
        name: 'collection',
        component: () => import('@/views/collection/CollectionList'),
        meta: { title: '收藏管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/log',
    name: 'System',
    meta: { title: '系统管理', icon: 'example' },
    children: [
      {
        path: 'log',
        name: 'log',
        component: () => import('@/views/log/LogList'),
        meta: { title: '系统日志', icon: 'table' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
