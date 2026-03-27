# Atividade 2 – Manutenção Corretiva e Evolutiva

Esta atividade foca em manutenção corretiva (correção de falhas) e manutenção evolutiva (adição de funcionalidade), preservando o funcionamento atual do sistema legado.

## Objetivos

1. Identificar e corrigir pelo menos 3 bugs reais no projeto.
2. Implementar 1 nova funcionalidade com impacto controlado.
3. Evitar quebrar fluxos existentes de cadastro, empréstimo, devolução e relatório.

## Pontos de Partida para Correção de Bugs

Sugestões de investigação com base no código atual:

- [LoanManager.returnBook](src/LoanManager.java#L90): tratar consistentemente cenários de empréstimo inexistente.
- [LoanManager.borrowBook](src/LoanManager.java#L14): revisar criação de empréstimos duplicados por canal.
- [ReportGenerator.generateSimpleReport](src/ReportGenerator.java#L9): revisar totalizadores do relatório.
- [BookManager.listBooksSimple](src/BookManager.java#L51): validar comportamento com lista vazia.
- [LegacyDatabase.countOpenLoansByBook](src/LegacyDatabase.java#L181): conferir consistência entre nome e filtro aplicado.

## Implementação Evolutiva (Escolher 1)

Exemplos de evolução:

1. Sistema de reserva de livros.
2. Histórico de empréstimos por usuário.
3. Suporte a múltiplos autores.
4. Melhorias nos relatórios (filtros adicionais, totais por categoria, etc.).

## Regras Técnicas

- Manter Java puro, sem frameworks externos.
- Preservar estrutura geral do projeto.
- Priorizar mudanças pequenas e testáveis.
- Atualizar documentação de uso e impacto da mudança.

## Checklist de Validação

Antes de entregar, validar pelo menos:

1. Compilação com `javac src/*.java`.
2. Execução de `java -cp src Main --list`.
3. Execução de `java -cp src Main --report`.
4. Teste manual do bug corrigido (com passos reproduzíveis).
5. Teste manual da nova funcionalidade.

## Formato de Entrega - Atividade 2

Data final de entrega: 16/04.

1. Branch/PR com:
   - correção de no mínimo 3 bugs
   - implementação de 1 funcionalidade nova
2. Documento de entrega (README da branch ou markdown) contendo:
   - quais bugs foram corrigidos
   - como reproduzir antes e depois
   - qual funcionalidade foi implementada
   - impactos e riscos conhecidos
3. Evidências de execução (saída de terminal ou roteiro) para:
   - compilação
   - fluxo principal
   - nova funcionalidade
4. Histórico de commits organizado por tipo de manutenção (corretiva/evolutiva).

## Regras de Submissão

### Repositório da Entrega

1. A entrega deve ser feita em um repositório público no GitHub.
2. O repositório deve ser um fork do repositório da disciplina.

### Envio por E-mail

Após publicar o repositório no GitHub, enviar o link por e-mail para:

joao.vsantos@unicesumar.edu.br

O e-mail deve seguir este formato:

Título do e-mail:

Atividade 2 Manutenção ESOFT5S

Substitua o número conforme o trabalho (Atividade 1 ou Atividade 2).

Corpo do e-mail:

1. Nome completo de cada integrante da equipe.
2. Usuário do GitHub de cada integrante.
3. Link do repositório no GitHub.

Exemplo:

Integrantes:

João Silva - github.com/joaosilva  
Maria Souza - github.com/mariasouza  
Pedro Lima - github.com/pedrolima

Repositório: https://github.com/grupo-exemplo/trabalho-mobile

### Cópia do E-mail (CC)

O e-mail deve incluir em cópia (CC) o endereço de e-mail de todos os integrantes da equipe.

### Trabalho em Grupo

Os trabalhos podem ser realizados em grupos de até 5 participantes.

### Participação Individual

Cada integrante do grupo deve possuir pelo menos um commit relevante relacionado ao desenvolvimento da atividade no repositório.

### Vídeo da Entrega

Deve ser gravado um vídeo da equipe para esta atividade.

1. Cada integrante deve explicar pelo menos uma manutenção realizada na atividade.
2. A explicação deve descrever o que foi feito e porque.
3. O vídeo deve mostrar o código correspondente à manutenção explicada.
4. O vídeo deve incluir a execução do código para demonstrar o funcionamento.

## Observação

O objetivo não é reescrever o sistema do zero, e sim simular manutenção real com evolução incremental.