<template>
  <div>
    <form id="formulario" class="noticia-form">
      
      <InputText label="Título" name="titulo" :value="noticia.titulo" />
      <InputText label="Lide" name="lide" type="textarea" :value="noticia.lide" />
      <InputText label="Corpo" name="corpo" type="textarea" :value="noticia.corpo" />
        
      <div class="d-flex" id="botoes">
        <button class="botao-personalizado flex-grow-1" @click="submit($event)"> {{ehEdicao() ? 'Salvar' : 'Cadastrar'}} </button>
        <router-link :to="{path: '/noticias'}" class="botao-personalizado botao-cancelar col-4" id="cancelar">
          DESCARTAR
        </router-link>
      </div>
    </form>
  </div>
</template>

<script>
import InputText from '../components/forms/InputText'
import axios from 'axios'

export default {
  components: {
    InputText
  },

  data() {
    return {
      noticia: {}
    }
  },

  mounted: function() {
    if ('id' in this.$route.params) {
      axios
        .get(this.urlRequisicao+this.$route.params.id)
        .then(resposta => (this.noticia = resposta.data))
        .catch(() => {
          this.$router.push("/noticias")
        })
    }
  },

  computed: {
    urlRequisicao: function () {
      return "http://localhost:8080/noticias/"
    }
  },

  methods: {
    ehEdicao() {
      return 'id' in this.noticia;
    },

    submit($event) {
      $event.preventDefault()

      var formulario = document.getElementById('formulario');
      var formData = new FormData(formulario)
      var jsonData = JSON.stringify(Object.fromEntries(formData))
      
      const t = this

      axios({
        method: this.ehEdicao() ? 'put' : 'post',
        url: this.urlRequisicao + (this.ehEdicao() ? this.noticia.id : ''),
        data: jsonData,
        headers: {'Content-Type': 'application/json' }
        })
        .then(function (response) {
            //handle success
            t.$router.push("/noticias/"+response.data.id)
            console.log('deu certo', response);
        })
        .catch(function (response) {
            //handle error
            console.log('deu errado', response);
      })

      
    },

    cadastrar() {
      var formData = new FormData(this.elemFormulario)
      var jsonData = JSON.stringify(Object.fromEntries(formData))
      axios({
        method: 'post',
        url: this.urlRequisicao,
        data: jsonData,
        headers: {'Content-Type': 'application/json' }
        })
        .then(function (response) {
            //handle success
            console.log('deu certo', response);
        })
        .catch(function (response) {
            //handle error
            console.log('deu errado', response);
      })
    },

    salvarAlteracao() {
      var formData = new FormData(this.elemFormulario)

      axios({
        method: 'put',
        url: this.urlRequisicao,
        data: formData,
        headers: {'Content-Type': 'application/json' }
        })
        .then(function (response) {
            //handle success
            console.log('deu certo', response);
        })
        .catch(function (response) {
            //handle error
            console.log('deu errado', response);
      })
    }

  }
}

</script>

<style>
.noticia-form {
    border-top: 10px solid var(--amarelo);
}

.noticia-form div {
    margin-top: 50px;
}

.noticia-form div label {
    font-size: 25px;
    color: white;
}

.noticia-form div input[type='text'], .noticia-form div textarea {
    width: 100%;
    border: 1px solid white;
    background-color: transparent;
    color: white;
    padding: 0 15px;
}

.noticia-form div input[type='text']:focus, .noticia-form div textarea:focus {
    outline: none;
    box-shadow: 0 0 20px rgba(255, 255, 255, .4);
}

.noticia-form div input[type='text'] {
    height: 70px;
    font-size: 36px;
    font-weight: bold;
}

.noticia-form div textarea {
    padding: 10px;
    font-size: 18px;
    resize: none;
}

.noticia-form div textarea#lide {
    height: 105px;
}

.noticia-form div textarea#corpo {
    height: 280px;
}

.noticia-form .erro {
    display: flex;
    align-items: center;
    height: 70px;
    padding: 0 15px;
    font-size: 20px;
    font-weight: bold;
    color: var(--vermelho);
    border: 1px solid var(--vermelho);
}
</style>