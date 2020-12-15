<template>
  <b-container fluid="lg" class="navegacao-auxiliar">
    <span class="active" v-if="!voltar">{{titulo}}</span>
    <span v-else>
      <router-link :to="{path: '/noticias' }">
        <i class="large material-icons md-light">chevron_left</i> Voltar para notícias
      </router-link>
    </span>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      voltar: false,
      titulo: 'Notícia'
    }
  },

  created: function() {
    this.checkRouteName()
  },

  watch: {
    $route () {
      this.checkRouteName()
    }
  },

  methods: {
    checkRouteName() {
      this.titulo = this.$route.name
      if (this.titulo == 'Notícia') this.voltar = true
      else this.voltar = false
    }
  }
}
</script>

<style scoped>
div.navegacao-auxiliar {
  background-color: transparent!important;
  height: 120px;
  display: flex;
  align-items: center;
}

div.navegacao-auxiliar span {
  color: white!important;
}

div.navegacao-auxiliar span.active {
  font-weight: bold;
}

div.navegacao-auxiliar span a {
  color: white;
  text-decoration: none;
  display: flex;
}

div.navegacao-auxiliar span a i {
  font-size: 22px!important;
}

div.navegacao-auxiliar span a:hover {
  color: var(--amarelo);
}
</style>