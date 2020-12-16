<template>
  <component :is="tipoElemento" :to="this.admin ? undefined : routeNoticia" class="noticia item-noticia">
    <div class="data-noticia"><NoticiaData :data="noticia.data_criacao" /></div>
    <div class="titulo-noticia">{{ noticia.titulo }}</div>
    <div class="lide-noticia">{{noticia.lide}}</div>
    <button v-if="!admin" class="botao-personalizado ler-mais-noticia">+ Ler mais</button>
    <div v-else class="row m-0">
      <router-link :to="routeNoticia" class="botao-personalizado col">Visualizar</router-link>
      <router-link :to="{name: 'Editar notícia', params: {id: this.noticia.id}}" class="botao-personalizado col">Editar</router-link>
      <button @click="excluir(noticia.id)" class="botao-personalizado botao-cancelar col col-md-3">Excluir</button>
    </div>
  </component>
</template>

<script>
import NoticiaData from './NoticiaData'
import axios from 'axios'

export default {
    components: {
      NoticiaData
    },

    props: {
      noticia: Object
    },

    computed: {
      admin() {
        return this.$root.isAdmin
      },

      tipoElemento() {
        return this.admin ? 'div' : 'router-link'
      },

      routeNoticia() {
        return {
          name: this.admin ? 'Notícia (Modo administrador)' : 'Notícia',
          params: {id: this.noticia.id}
        }
      }
    },

    methods: {
      excluir(id) {
        if (confirm('Tem certeza que deseja excluir essa notícia?\n' + 
        'Essa ação não pode ser desfeita.')) {
          const t = this

            
            
          axios
          .delete("http://localhost:8080/noticias/"+id)
          .then(function() {
            t.$emit("on-delete", {id: t.noticia.id})
            alert('Notícia excluída com sucesso!')
          })
          .catch(() => {
            alert('Não foi possível excluir a notícia.\nTente novamente!')
          })
        }
      }
    }
}
</script>

<style scoped>
.item-noticia + .item-noticia {
    margin-top: 80px;
}

.item-noticia div.titulo-noticia {
    font-size: 36px!important;
    line-height: 40px!important;
    margin-top: 0!important;
}

.item-noticia div.lide-noticia {
    font-size: 18px;
    margin: 15px 0 30px!important;
    padding: 0!important;
    border: 0!important;
}

.item-noticia button:hover,
a.item-noticia:hover button {
    background: var(--amarelo);
}

.item-noticia button.ler-mais-noticia {
    width: 100%;
}

a.item-noticia:hover {
    text-decoration: none!important;
}

a.item-noticia:hover div {
    color: var(--amarelo)!important;
}
</style>