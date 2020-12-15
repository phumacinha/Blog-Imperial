<template>
  <span>
    Publicado em {{dataFormatada}}
  </span>
</template>

<script>
import moment from 'moment'


export default {
  
  props: {
    data: String
  },

  computed: {
    dataFormatada: function () {
      return moment(this.data).locale('pt-br').format('DD [de] MMMM [de] YYYY [às] HH[h]mm')
    }
  }

}
</script>