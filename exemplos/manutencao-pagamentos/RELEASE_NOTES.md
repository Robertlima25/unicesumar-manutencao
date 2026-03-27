# Release Notes

## [1.1.0] - 2026-03-26

### Tipo da Versao
MINOR (SemVer): adiciona nova funcionalidade sem quebrar clientes existentes.

### Novidades
- Suporte a multiplas moedas no modulo de vendas por meio de Moeda (BRL, USD, EUR).
- Novo metodo em ProcessadorDeVendas:
  - finalizarVenda(double valor, Moeda moeda)
- Validacoes fail fast para entradas invalidas sem if/else aninhado.
- Eliminacao de numeros magicos com constantes nomeadas para taxas e limites.
- Inclusao de documentacao OpenAPI em docs/openapi.yaml.

### Depreciacoes
- Metodo legado marcado como depreciado:
  - @Deprecated boolean finalizarVenda(double valor)
- Motivo: evolucao da API para suporte a multiplas moedas.
- Compatibilidade: mantida nesta versao via delegacao para o metodo novo com BRL.

### Guia de Migracao
- Preferir o uso de finalizarVenda(double valor, Moeda moeda) em novos clientes.
- Planejar remocao de chamadas ao metodo legado antes da versao 2.0.0.

### Impacto para Clientes
- Sem quebra imediata de compilacao.
- Aviso de deprecacao para orientar migracao gradual.
