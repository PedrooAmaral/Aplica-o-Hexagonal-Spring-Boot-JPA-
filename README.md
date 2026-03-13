# API de Produtos (Hexagonal/Ports&Adapters)

API REST simples para cadastro de produtos com Spring Boot e MySQL.

## Requisitos
- Java 21
- MySQL rodando localmente

## Estrutura (Hexagonal)

  - Domínio: modelo de negócio (`Produto`)
  - Aplicação (Ports in/out):
  - portas de entrada (casos de uso)
  - porta de saída (`ProdutoRepositoryPort`)
  - serviço de aplicação (`ProdutoService`)
  - Adapters:
  - entrada: controller REST (`ProdutoController`)
  - saída: implementação MySQL/JPA (`ProdutoMysqlRepository`, `ProdutoJpaEntity`)

Fluxo resumido:

Controller -> UseCase (porta in) -> Service -> RepositoryPort (porta out) -> Adapter MySQL -> Banco
