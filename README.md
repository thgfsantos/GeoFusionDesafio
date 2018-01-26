# GeoFusionDesafio

Pré-Requisitos
- Java 8 ou superior
- Junit 4
- Firefox Browser: Firefox 56 ou superior
- Driver Firefox: geckodriver 0.19 ou superior
- Selenium: selenium-server-standalone-3.8-1.jar
- Linux Ubuntu

Estrutura dos Testes
- packages:
  - Geo/src/desafio/geo/tech/page -> Encontra-se as classes que representam a página web.
  - Geo/src/desafio/geo/tech/test -> Classes que contem os casos de testes automatizados.

Execução dos Testes
- OBS: Antes da execução dos testes, verifica se geckodriver (driver do Firefox) está na pasta /tmp/. Caso o geckodriver esteja em outro diretório será necessário alterar as classes de tests InitialPageTest/AddNewProductTest/ListProductTest.
- via IDE
   - É possível executar os testes usando Junit e executando a classe IntegrationSuitTest.java dentro package test.
   - É possível executar cada classe de teste individual com Junit (InitialPageTest/AddNewProductTest/ListProductTest).
