<template>
  <div>
    <NoticiaItem :noticia="noticia" v-for="noticia in noticias" :key="noticia.id" @on-delete="excluir($event)" />
    <div v-if="noticias.length == 0" class="nenhuma">Nenhuma notícia cadastrada.</div>
  </div>
</template>

<script>
import NoticiaItem from '../components/noticia/NoticiaItem'
import axios from 'axios'

export default {
  components: {
    NoticiaItem
  },

  data: function () {
    return {
      noticias: []
    }
  },

  created: function() {
    axios.get("http://localhost:8080/noticias")
      .then(resposta => (this.noticias = resposta.data))
  },

  methods: {
    excluir($event) {
      this.noticias = this.noticias.filter(noticia => noticia.id != $event.id)
      console.log('oi');
    }
  }
}
</script>

<style>
  div.nenhuma {
    border-top: var(--borda-amarela);
    padding-top: 20px;
    color: white;
    font-size: 30px;
  }
</style>