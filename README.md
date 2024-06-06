# Sistema de Gerenciamento de Reservas

## Descrição

Este projeto é a segunda atividade para entrega da matéria de Programação Orientada a Objetos (POO). Trata-se de um aplicativo de gerenciamento de reservas para um evento específico, onde há apenas 6 mesas disponíveis, cada uma acomodando até 4 pessoas. O aplicativo permite que clientes façam reservas, pesquisem suas reservas usando CPF ou CNPJ, imprimam a lista de reservas confirmadas, visualizem a lista de espera e cancelem suas reservas. Quando todas as mesas estão reservadas, os novos clientes são adicionados a uma lista de espera. O objetivo do aplicativo é garantir uma organização eficiente das reservas.

## Funcionalidades

1. **Reservar Mesa**: Permite que clientes (pessoas físicas ou jurídicas) façam reservas para o jantar. Se todas as mesas estiverem reservadas, os clientes serão adicionados à lista de espera.
2. **Pesquisar Reserva**: Permite que os clientes pesquisem suas reservas usando CPF ou CNPJ.
3. **Imprimir Reservas**: Exibe uma lista das reservas confirmadas para as primeiras 6 mesas.
4. **Imprimir Lista de Espera**: Exibe a lista de espera para reservas adicionais.
5. **Cancelar Reserva**: Permite que os clientes cancelem suas reservas usando CPF ou CNPJ.
6. **Sair**: Encerra o aplicativo.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Swing (JOptionPane)**: Para a interface gráfica do usuário.
- **Paradigma de Orientação a Objetos**: Para a estruturação do código.

## Estrutura do Projeto

### Cliente.java
Classe abstrata que representa um cliente. Deve ser estendida por `PessoaFisica` e `PessoaJuridica`.

### PessoaFisica.java
Representa um cliente que é uma pessoa física, com atributos nome e CPF.

### PessoaJuridica.java
Representa um cliente que é uma pessoa jurídica, com atributos nome e CNPJ.

### Pagamento.java
Interface que define o método `calcularPagamento`.

### Reserva.java
Classe que implementa a interface `Pagamento` e representa uma reserva. Inclui detalhes do cliente e a forma de pagamento.

### Main.java
Classe principal responsável por gerenciar a interface do usuário e todas as funcionalidades do aplicativo. Utiliza `JOptionPane` para interações com o usuário. Implementa a lógica para reservar mesas, pesquisar reservas, imprimir listas de reservas, visualizar a lista de espera e cancelar reservas. Exibe um menu de opções com botões para cada funcionalidade.

## Como Executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/Luigi052/poo.2024.ex2.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd poo.2024.ex2
    ```
3. Compile e execute o aplicativo:
    ```bash
    javac src/*.java
    java src.Main
    ```

## Contribuidores

- **Antonio Cesar**
- **Bruno Bretas**
- **Bruno Pereira**
- **Bruno Massaro**
- **Luigi Tomassone**

## Licença

Este projeto é licenciado sob a [Licença MIT](LICENSE).
