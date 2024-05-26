# BootcampBase2Mantis

## Índice

- Propósito
- Passo a passo
- Bibliotecas Utilizadas
- Colaboradores

## Propósito

Este projeto de automação tem o objetivo de realizar testes automatizados da funcionalidade de login e criação de tarefa(issue report) no sistema Mantis.

## Passo a passo

1. Realiza o login no sistema Mantis.
    - Acessa a URL do Mantis.
    - Insere o nome de usuário e clica em "Login".
    - Insere a senha e clica em "Login".

2. Cria uma nova tarefa preenchendo todos os campos necessários.
    - Clica no botão "Report issue".
    - Seleciona campo "Categoria".
    - Anexa uma imagem de erro.
        - Obtém o caminho do diretório de trabalho atual.
        - Encontra o elemento de input do tipo file e insere o caminho da imagem.
    - Seleciona campo "Reproducibilidade".
    - Seleciona campo "Prioridade".
    - Preenche campo "Resumo".
    - Preenche campo "Descrição".
    - Preenche campo "Passos para Reproduzir".
    - Preenche campo "Informação Adicional".
    - Clica no botão "Submeter Problema" para criar a tarefa.

3. Verifica se a tarefa foi criada com sucesso.
    - Espera pela mensagem de status da operação.
    - Valida se a mensagem contém "SUCCESSFUL" ou "SUCESSO".

## Bibliotecas Utilizadas

- Selenium WebDriver
- JUnit
- WebDriverWait
- ChromeDriver

## Colaboradores

| Arley Novais                                  |
|-----------------------------------------------|
| ![Foto de Arley](https://gitlab.com/uploads/-/system/user/avatar/14731363/avatar.png?width=400) |

