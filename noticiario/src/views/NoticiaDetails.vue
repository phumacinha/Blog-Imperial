<template>
  <div class="noticia">
    <div class="data-noticia"><NoticiaData :data="noticia.data_criacao" /></div>
    <div class="titulo-noticia">{{noticia.titulo}}</div>
    <div class="lide-noticia">{{noticia.lide}}</div>
    <hr>
    <div class="corpo-noticia" v-html="noticia.corpo"></div>
  </div>
</template>

<script>
import NoticiaData from '@/components/noticia/NoticiaData'
import axios from 'axios'

export default {
  components: {
    NoticiaData
  },

  data() {
    return {
      noticia: []
    }
  },

  mounted: function() {
    axios
      .get("http://localhost:8080/noticias/"+this.$route.params.id)
      .then(resposta => (this.noticia = resposta.data))
      .catch(() => {
        this.$router.push("/noticias")
      })
  }
}
</script>

<style scoped>
.noticia {
  color: red;
  font-size: 100px;
}
</style>