<template>
  <header>
    <b-navbar toggleable="lg" fixed="top">
      <b-container fluid="lg">
        <b-navbar-brand>
          <router-link :to="(admin ? '/admin' : '')+'/noticias'" id="logo">
            <img src="../assets/images/logo.png" height="70" />
          </router-link>
        </b-navbar-brand>

        <b-navbar-toggle target="menu-principal" id="navbar-toggler">
            MENU
        </b-navbar-toggle>

        <b-collapse is-nav id="menu-principal">
          <b-navbar-nav>
            <b-nav-item :to="(admin ? '/admin' : '')+'/noticias'">Notícias</b-nav-item>
            <b-nav-item :to="'#'">Sobre</b-nav-item>
            <b-nav-item :to="'#'">Esportes</b-nav-item>
            <b-nav-item :to="'#'">Torneios</b-nav-item>
            <b-nav-item v-if="!admin" :to="{path: '/admin' + rota}" class="link-button" id="botao-area-administrativa"><i class="material-icons md-light">lock</i> Área administrativa</b-nav-item>
            <b-nav-item v-if="admin" :to="{path: '/admin/nova-noticia'}" class="link-button" id="botao-nova-noticia"><i class="material-icons md-light">add</i> Nova notícia</b-nav-item>
            <b-nav-item v-if="admin" :to="{path: rota}" class="link-button"><i class="material-icons md-light">keyboard_return</i> Logout</b-nav-item>
          </b-navbar-nav>
        </b-collapse>
      </b-container>
    </b-navbar>
  </header>
</template>

<script>
export default {

  computed: {
    admin () {
      return this.$root.isAdmin
    },

    rota () {
      return this.$route.path.split("/admin").pop()
    }
  }

}
</script>

<style scoped>
header nav {
  background-color: var(--amarelo)!important;
  font-family: var(--font-teko);
  font-size: 24px;
  text-transform: uppercase;
}

header nav .navbar-brand {
  margin-right: 40px;
}

header nav .nav-link {
  color: black!important;
  margin: 2px 10px;
  padding: 2px 10px!important;
}

header nav .nav-link:hover, header nav .link-button a {
  color: white!important;
  background-color: black;
}

header nav .link-button a {
  display: flex;
  align-items: center;
}

header nav .link-button a:hover {
  color: var(--amarelo)!important;
}

header nav .link-button i {
  font-size: 16px;
  position: relative;
  top: -1px;
  margin-right: 5px;
}

header nav .navbar-toggler {
  color: var(--amarelo)!important;
  background-color: black;
  height: 60px!important;
  transition: background .2s;
}

header nav .navbar-toggler:active, header nav .navbar-toggler:focus {
  background-color: transparent;
  color: black!important;
}
</style>