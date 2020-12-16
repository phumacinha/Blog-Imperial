<template>
  <div class="noticia">
    <div class="data-noticia"><NoticiaData :data="noticia.data_criacao" /></div>
    <div class="titulo-noticia">{{noticia.titulo}}</div>
    <div class="lide-noticia">{{noticia.lide}}</div>
    <hr>
    <div v-if="admin" class="row m-0">
      <router-link :to="{name: 'Editar notícia', params: {id: this.noticia.id}}" class="botao-personalizado col">Editar</router-link>
      <button @click="excluir(noticia.id)" class="botao-personalizado botao-cancelar col col-md-4">Excluir</button>
    </div>
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

  computed: {
    admin() {
      return this.$root.isAdmin
    }
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
  },

  methods: {
      excluir(id) {
        if (confirm('Tem certeza que deseja excluir essa notícia?\n' + 
        'Essa ação não pode ser desfeita.')) {

          const t = this

          axios
          .delete("http://localhost:8080/noticias/"+id)
          .then(function() {
            t.$router.push('/admin/noticias')
            alert('Notícia excluída com sucesso!')
          })
          .catch(function(response) {
            console.log(response)
            alert('Não foi possível excluir a notícia.\nTente novamente!')
          })

        }
      }
    }
}
</script>

<style scoped>
.row {
  margin-bottom: 40px!important;
}
</style>