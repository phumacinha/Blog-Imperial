<template>
  <router-link :to="{name: 'Notícia', params: {id: noticia.id}}" class="noticia item-noticia">
    <div class="data-noticia"><NoticiaData :data="noticia.data_criacao" /></div>
    <div class="titulo-noticia">{{ noticia.titulo }}</div>
    <div class="lide-noticia">{{noticia.lide}}</div>
    <button class="botao-personalizado ler-mais-noticia">+ Ler mais</button>
  </router-link>
</template>

<script>
import NoticiaData from './NoticiaData'

export default {
    components: {
      NoticiaData
    },

    props: {
      noticia: Object
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