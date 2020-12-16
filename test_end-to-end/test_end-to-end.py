from test_utils import *
import time
from selenium import webdriver

def inserir_noticia (noticia):
  global driver

  # Abrir formulário de nova notícia
  item_navbar_click(driver, 'botao-nova-noticia')

  # Inserir valores
  preencher_formulario(noticia)

  # Submeter formulario
  submeter_formulario(driver)

  # É esperado um alert Javascript, devemos aceitá-lo.
  aceitar_caixa_de_alerta(driver)


def preencher_formulario (noticia):# Inserir valores
  set_campo(driver, 'titulo', noticia['titulo'])
  set_campo(driver, 'lide', noticia['lide'])
  set_campo(driver, 'corpo', noticia['corpo'])
  time.sleep(.3)


driver = webdriver.Chrome('/snap/bin/chromium.chromedriver')  # Optional argument, if not specified will search path.
driver.get("http://localhost:4545")
time.sleep(1.5)


print_titulo('Iniciando teste de funcionalidades de administrador')
print('Abrindo área administrativa', end=' ')
item_navbar_click(driver, 'botao-area-administrativa')
print_ok()

print_titulo('TESTANDO INSERÇÃO DE NOTÍCIA')
print('Inserindo uma notícia', end=' ')
inserir_noticia(noticia_1)
print_ok()

print('Inserindo outra notícia', end=' ')
inserir_noticia(noticia_2)
print_ok()


print_titulo('TESTANDO VISUALIZAÇÃO (CONSULTA) DE TODAS AS NOTÍCIAS')
print('Navegando até a home para visualizar todas as notícias', end=' ')
item_navbar_click(driver, 'logo')
print_ok()


print_titulo('TESTES DE EDIÇÃO E REMOÇÃO PELA PÁGINA DE VISUALIZAÇÃO')
print('Visualizando uma notícia', end=' ')
driver.find_element_by_xpath('//a[contains(text(), "Visualizar")]').click()
time.sleep(.5)
print_ok()

print('Editando a notícia', end=' ')
driver.find_element_by_xpath('//a[contains(text(), "Editar")]').click()
time.sleep(.5)

preencher_formulario(noticia_editada)

submeter_formulario(driver)
aceitar_caixa_de_alerta(driver)
print_ok()
time.sleep(.5)

print('Excluindo a notícia', end=' ')
driver.find_element_by_xpath('//button[contains(text(), "Excluir")]').click()
time.sleep(1)
aceitar_caixa_de_alerta(driver)
print_ok()

print_titulo('TESTES DE EDIÇÃO E REMOÇÃO PELA PELA PÁGINA PRINCIPAL')
print('Editando uma notícia', end=' ')
driver.find_element_by_xpath('//a[contains(text(), "Editar")]').click()
time.sleep(.5)

preencher_formulario(noticia_editada)

submeter_formulario(driver)
aceitar_caixa_de_alerta(driver)
print_ok()
time.sleep(.5)

print('Excluindo uma notícia', end=' ')
driver.find_element_by_xpath('//button[contains(text(), "Excluir")]').click()
time.sleep(1)
aceitar_caixa_de_alerta(driver)
print_ok()

driver.close()