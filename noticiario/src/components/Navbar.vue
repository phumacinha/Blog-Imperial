<template>
  <header>
    <b-navbar toggleable="lg" fixed="top">
      <b-container fluid="lg">
        <b-navbar-brand>
          <router-link :to="(admin ? '/admin' : '')+'/noticias'" id="logo" class="row">
            <div class="col imagem">
              <img src="../assets/images/logo.png" height="70" />
            </div>
            <div class="col p-0 texto">
              <div>
              <span>Imperial</span><br>
              <span>Press</span>
              </div>
            </div>
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
}

header nav .navbar-brand {
  margin-right: 40px;
}

header nav .nav-link {
  color: black!important;
  margin: 2px 10px;
  padding: 2px 10px!important;
  text-transform: uppercase;
}

header nav .nav-link:hover, header nav .link-button a {
  color: white!important;
  background-color: black;
}

header nav #logo {
  color: black;
  font-family: var(--font-montserrat);
  font-weight: 900;
  font-size: 22px;
  line-height: 22px;
}

header nav #logo:hover {
  text-decoration: none!important;
}


header nav #logo:hover .imagem {
  transform: scale(1.05);
}

header nav #logo .texto {
  display: flex;
  align-items: center;
}

header nav #logo .texto span {
  position: relative;
  display: inline-block;
  padding: 4px 6px;
}

header nav #logo .texto br + span {
  padding-top: 0;
}

header nav #logo .texto span::before {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: white;
  content: "";
  z-index: -1;
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