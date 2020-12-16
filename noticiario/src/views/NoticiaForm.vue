<template>
  <div>
    <form id="formulario" class="noticia-form">
      <div v-show="erros.length > 0" id="erros">
        <ul>
          <li v-for="(erro, index) in erros" :key="index">{{erro}}</li>
        </ul>
      </div>
      <InputText label="Título" name="titulo" :value="noticia.titulo" />
      <InputText label="Lide" name="lide" type="textarea" :value="noticia.lide" />
      <InputText label="Corpo" name="corpo" type="textarea" :value="noticia.corpo" />
        
      <div class="d-flex label" id="botoes">
        <button class="botao-personalizado flex-grow-1" @click="submit($event)" id="botao-submeter"> {{ehEdicao ? 'Salvar' : 'Cadastrar'}} </button>
        <button @click="discard" class="botao-personalizado botao-descartar col-4" id="botao-descartar">
          DESCARTAR
        </button>
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
      noticia: {},
      erros: [],
      paginaAnterior: '/admin'
    }
  },
  
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.paginaAnterior = from.path == '/' ? '/admin' : from.path
    })
  },

  created: function() {
    if (this.ehEdicao) {
      axios
        .get(this.urlRequisicao+this.$route.params.id)
        .then(resposta => (this.noticia = resposta.data))
        .catch(() => {
          this.$router.push("/noticias")
        })
    } else {
      this.noticia = {}
    }
  },

  computed: {
    urlRequisicao: function () {
      return "http://localhost:8080/noticias/"
    },

    ehEdicao: function () {
      return ('id' in this.$route.params
              && this.$route.path.includes('editar'));
    }
  },

  methods: {
    discard($event) {
      $event.preventDefault()

      if (confirm('Tem certeza que deseja descartar '
      + this.ehEdicao ? 'as alterações' : 'a nova notícia')
      + '?') {
        this.$router.push(this.paginaAnterior)
      }
    },

    scrollTop() {
      window.scrollTo(0, 0)
    },

    submit($event) {
      $event.preventDefault()

      this.erros = []

      var formulario = document.getElementById('formulario');
      var formData = new FormData(formulario)
      var jsonData = JSON.stringify(Object.fromEntries(formData))
      
      const t = this

      axios({
        method: this.ehEdicao ? 'put' : 'post',
        url: this.urlRequisicao + (this.ehEdicao ? this.noticia.id : ''),
        data: jsonData,
        headers: {'Content-Type': 'application/json' }
        })
        .then(response => {
            alert((t.ehEdicao ? 'Alterações salvas' : 'Notícia cadastrada') + ' com sucesso!')
            t.$router.push("/admin/noticias/"+response.data.id)
        })
        .catch(error => {
            if (error.response.status === 400) {
              t.erros = error.response.data
              t.scrollTop()
            }
            else {
              alert('Ocorreu um erro ao ' + (t.ehEdicao ? 'salvar as alterações.' : 'cadastrar nova notícia')
              + '.\nPor favor, tente novamente!')
            }
      })

      
    }

  }
}

</script>

<style>
.noticia-form {
    border-top: 10px solid var(--amarelo);
}

.noticia-form div.label {
    margin-top: 50px;
}

.noticia-form div.label label {
    font-size: 25px;
    color: white;
}

.noticia-form div.label input[type='text'], .noticia-form div.label textarea {
    width: 100%;
    border: 1px solid white;
    background-color: transparent;
    color: white;
    padding: 0 15px;
}

.noticia-form div.label input[type='text']:focus, .noticia-form div.label textarea:focus {
    outline: none;
    box-shadow: 0 0 20px rgba(255, 255, 255, .4);
}

.noticia-form div.label input[type='text'] {
    height: 70px;
    font-size: 36px;
    font-weight: bold;
}

.noticia-form div.label textarea {
    padding: 10px;
    font-size: 18px;
    resize: none;
}

.noticia-form div.label textarea#lide {
    height: 105px;
}

.noticia-form div.label textarea#corpo {
    height: 280px;
}

.noticia-form div#erros {
    display: flex;
    align-items: center;
    padding: 20px 15px;
    margin-top: 50px;
    font-size: 20px;
    font-weight: bold;
    color: var(--vermelho);
    border: 1px solid var(--vermelho);
}

.noticia-form div#erros ul {
  margin: 0;
}

.noticia-form .botao-personalizado.botao-descartar {
    opacity: .5;
}

.noticia-form .botao-personalizado.botao-descartar:hover {
    opacity: 1;
    background-color: white;
}
</style>