# Exemplo de Manutenção de Software - Módulo de Pagamentos

Este projeto demonstra, de forma didática, a evolução de um módulo de pagamentos de e-commerce com foco em manutenibilidade.

## Conceitos Cobertos

- Capítulo 2: early return (fail fast) e fuga de números mágicos
- Capítulo 3: Javadoc, comentários privados e documentação REST em OpenAPI/Swagger
- Capítulo 4: ocultamento de informação, interfaces, injeção de dependências e depreciação
- Apêndice A: Conventional Commits

## Livro da Disciplina

Referência oficial da disciplina:

- https://manutencaosoftware.org

## Estrutura

- src/Moeda.java: enum para múltiplas moedas
- src/ServicoPagamento.java: contrato (abstração)
- src/PagamentoPayPal.java: implementação concreta de gateway
- src/ProcessadorDeVendas.java: classe principal com regras de negócio
- src/Main.java: demonstração de execução
- docs/openapi.yaml: documentação da API REST
- RELEASE_NOTES.md: release notes com SemVer

## Como Compilar e Executar

Compilar:

```bash
javac src/*.java
```

Executar:

```bash
java -cp src Main
```

## Arquitetura (Resumo)

ProcessadorDeVendas depende da interface ServicoPagamento, recebida por construtor.
Isso permite trocar PagamentoPayPal por outra implementação sem alterar o consumidor.

Fluxo:

1. Main instancia uma implementação concreta de ServicoPagamento.
2. Main injeta a dependência em ProcessadorDeVendas.
3. ProcessadorDeVendas valida entradas com early return.
4. ProcessadorDeVendas calcula taxa sem números mágicos.
5. ServicoPagamento processa a venda no gateway.

## Evolução de API sem Quebra Imediata

- Método antigo: finalizarVenda(double valor) marcado com @Deprecated
- Método novo: finalizarVenda(double valor, Moeda moeda)
- Compatibilidade: o método antigo delega para o novo com Moeda.BRL

## OpenAPI

A especificação REST desta versão está em:

- docs/openapi.yaml

Ela documenta endpoints de finalização de venda, contrato JSON e códigos HTTP (200 e 400).

## Exemplo de Conventional Commit

```text
feat(pagamentos): adicionar suporte a múltiplas moedas no processador de vendas

- criar assinatura finalizarVenda(double, Moeda)
- manter método legado com @Deprecated para compatibilidade
- substituir números mágicos por constantes nomeadas

BREAKING CHANGE: método legado finalizarVenda(double) será removido na versão 2.0.0
```
