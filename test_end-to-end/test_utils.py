import time

noticia_1 = {
  "titulo": "Câmara votará MP sobre vacina na quinta sem exigência de 'termo de responsabilidade', diz Maia",
  "lide": "Pela manhã, relator do texto na Câmara disse que incluiria tema na medida provisória. Governo defende que vacinados assinem termo de responsabilidade; especialistas criticam ideia.",
  "corpo": "<p>O presidente da Câmara dos Deputados, Rodrigo Maia (DEM-RJ), disse nesta terça-feira (15) que a medida provisória que prevê a compra de vacina contra a Covid-19 pelo programa internacional Covax Faciliy será votada na quinta (17) – sem a exigência de que os vacinados assinem um termo de responsabilidade.</p>\
          <p>Pela manhã, o relator da MP na Câmara, deputado Geninho Zuliani (DEM-SP), informou que a exigência do termo seria incluída no texto que tramita no Congresso. A declaração foi dada após reunião com o presidente Jair Bolsonaro no Palácio do Planalto.</p>\
          <p>Maia disse que conversou com Zuliani – ambos são do mesmo partido – e que, se quiser, o governo terá de enviar essa proposta como uma emenda. A medida é defendida por Bolsonaro, que chegou a dizer na segunda (14) que incluiria esse termo de responsabilidade em uma nova MP.</p>"
}

noticia_2 = {
  "titulo": "Doses já previstas deixam um quarto da população mundial sem vacinas contra a Covid até meados de 2022, diz pesquisa",
  "lide": "Cenário apresentado por pesquisadores na revista científica \"The BMJ\" considera a hipótese de que todas as empresas terão sucesso em seus estudos e conseguirão cumprir produção prevista.",
  "corpo": "<p>Um estudo publicado nesta terça-feira (15) na revista científica “The BMJ” coloca em números o desafio de garantir acesso às vacinas contra a Covid-19 e sinaliza que, mesmo em um cenário otimista, a produção de imunizantes já prevista deixará, ao menos, um quarto da população mundial sem vacinas até meados de 2022.</p>\
          <p>Apesar dessa previsão, os pesquisadores Anthony D. So e Joshua Woo, da Johns Hopkins Bloomberg School of Public Health, alertam que a projeção está baseada na hipótese de que todos os desenvolvedores terão sucesso nos testes e conseguirão manter o ritmo de produção.</p>\
          <p>E para que \"apenas\" um quarto da população mundial fique na fila para ser atendida com a produção prometida para a partir de 2022 é preciso que, além do sucesso nos testes, fabricação e distribuição, países que fizeram reserva de doses acima da quantidade necessária para a sua população redistribuam estoques.</p>"
}

noticia_editada = {
  "titulo": "Título editado",
  "lide": "Lide editado.",
  "corpo": "Corpo editado."
}

def item_navbar_click(driver, identificador):
    if not driver.find_element_by_id(identificador).is_displayed():
        driver.find_element_by_id('navbar-toggler').click()
        time.sleep(1)

    driver.find_element_by_id(identificador).click()
    time.sleep(.5)

def set_campo(driver, identificador, valor):
    driver.find_element_by_id(identificador).clear()
    driver.find_element_by_id(identificador).send_keys(valor)

def submeter_formulario(driver):
    driver.find_element_by_id('botao-submeter').click()
    time.sleep(1)

def aceitar_caixa_de_alerta(driver):
    driver.switch_to_alert().accept()
    time.sleep(.5)

def print_titulo(titulo):
    print('\n\033[44;30;1m{}\033[0m'.format(titulo.upper()))
    time.sleep(.1)

def print_ok():
    print('\033[92;1mOK\033[0m')
