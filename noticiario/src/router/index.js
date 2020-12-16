import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '@/views/Home'
import NoticiaDetails from '@/views/NoticiaDetails'
import NoticiaForm from '@/views/NoticiaForm'

Vue.use(VueRouter)

export const routes = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/noticias'
    },
    {
      path: '/admin',
      redirect: '/admin/noticias'
    },
    {
      name: 'Notícias',
      path: '/:admin?/noticias/',
      alias: '/',
      component: Home
    },
    {
      name: 'Notícia',
      path: '/noticias/:id',
      component: NoticiaDetails
    },
    {
      name: 'Notícia (Modo administrador)',
      path: '/admin/noticias/:id',
      component: NoticiaDetails
    },
    {
      name: 'Cadastrar notícia',
      path: '/admin/nova-noticia',
      component: NoticiaForm
    },
    {
      name: 'Editar notícia',
      path: '/admin/editar-noticia/:id',
      component: NoticiaForm
    }
  ]
})

export default routes