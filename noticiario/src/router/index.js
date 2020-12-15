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
      name: 'Notícias',
      path: '/',
      alias: '/noticias',
      component: Home
    },
    {
      name: 'Notícia',
      path: '/noticias/:id',
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