# elodebito

## Objetivo
Criar uma API Rest para cadastrar e manipular débitos.

## Requisitos Funcionais

- [x] 1. Buscar todos os débitos
  - [x] a. Paginação e possibilidade de filtrar por data de lançamento, cpf e nome da pessoa
- [x] 2. Buscar um débito por id
- [x] 3. Buscar o valor total de todos os débitos lançados
- [x] 4. Criar um débito
  - [x] a. Campos obrigatórios
    - [x] i. data de lançamento
    - [x] ii. cpf da pessoa
    - [x] iii. nome da pessoa
    - [x] iv. parcelas
      - [x] 1. número
      - [x] 2. data de vencimento
      - [x] 3. valor
  - b. Validações:
    - [x] i. Deve ter pelo menos uma parcela
    - [x] ii. A data de lançamento deve ser menor ou igual a data atual
    - [x] iii. O número da parcela deve ser único por débito
    - [x] iv. A data de vencimento da parcela deve ser maior do que a data atual
    - [x] v. O valor da parcela deve ser maior que 0
- [x] 5. Incluir uma parcela nova em um débito já existente
- [x] 6. Alterar a data de vencimento de uma parcela
- [x] 7. Excluir um débito

## Requisitos Técnicos

- [x] ● Java e spring boot
- [x] ● Salvar em algum banco de dados relacional
- [x] ● Testes unitários e de integração

## Extras Funcionalidades

- [ ] 1. Movimentações das parcelas
  - [ ] a. Pagamento
  - [ ] b. Cancelamento
- [ ] 2. Extrato de débitos por pessoa
- [ ] 3. Extrato de débitos por situação da parcela

## Extras Técnicos

- [x] 1. Front-end
- [ ] 2. Documentação
- [x] 3. Docker
- [ ] 4. Publicação da aplicação em algum provedor (AWS, Google Cloud ou Azure)
- [ ] 5. Pipeline que roda testes e faz o build
