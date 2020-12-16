<template>
  <div id="app">
    <Navbar />
    <NavAux titulo="Notícias" />

    <b-container fluid="lg">
      <router-view />
    </b-container>

  </div>
</template>

<script>

import Navbar from './components/Navbar'
import NavAux from './components/NavAux'

export default {

  name: 'App',

  components: {
    Navbar,
    NavAux
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,400;0,700;0,900;1,400;1,700&family=Teko:wght@400;500&display=swap');
@import url('https://fonts.googleapis.com/icon?family=Material+Icons');

body {
  background-color: black;
  font-family: var(--font-montserrat);
  margin-top: 96px;
  margin-bottom: 100px;
}
</style>
