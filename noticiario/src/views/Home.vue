<template>
  <div>
    <NoticiaItem :noticia="noticia" v-for="noticia in noticias" :key="noticia.id" />
  </div>
</template>

<script>
import NoticiaItem from '../components/noticia/NoticiaItem'
import axios from 'axios'

export default {
  components: {
    NoticiaItem
  },

  data() {
    return {
      noticias: []
    }
  },

  created: function() {
    axios.get("http://localhost:8080/noticias")
      .then(resposta => (this.noticias = resposta.data))
  }
}
</script>

<style>

</style>