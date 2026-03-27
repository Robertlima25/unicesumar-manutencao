/**
 * Coordena a finalizacao de vendas usando um servico de pagamento.
 */
public class ProcessadorDeVendas {

    // Aqui aplicamos fuga de numeros magicos com constantes nomeadas.
    private static final double TAXA_PROCESSAMENTO_PERCENTUAL = 0.029;
    private static final double TAXA_FIXA_EM_BRL = 0.30;
    private static final double VALOR_MINIMO_VENDA = 1.00;

    // Aqui aplicamos ocultamento de informacao protegendo o estado interno.
    private final ServicoPagamento servicoPagamento;

    /**
     * Cria um processador injetando a dependencia por abstracao.
     *
     * @param servicoPagamento implementacao de servico de pagamento
     */
    public ProcessadorDeVendas(ServicoPagamento servicoPagamento) {
        // Aqui aplicamos fail fast para nao permitir construcao invalida.
        if (servicoPagamento == null) {
            throw new IllegalArgumentException("ServicoPagamento nao pode ser nulo.");
        }

        // Aqui aplicamos DI por construtor para reduzir acoplamento.
        this.servicoPagamento = servicoPagamento;
    }

    /**
     * Finaliza venda no formato legado (mantido por compatibilidade).
     *
     * @param valor valor da venda em BRL
     * @return true quando o pagamento e aprovado; false caso contrario
     * @deprecated Use {@link #finalizarVenda(double, Moeda)} para informar a moeda.
     */
    @Deprecated
    public boolean finalizarVenda(double valor) {
        return finalizarVenda(valor, Moeda.BRL);
    }

    /**
     * Finaliza uma venda com suporte a multiplas moedas.
     *
     * @param valor valor bruto da venda
     * @param moeda moeda da operacao
     * @return true quando a venda eh processada com sucesso; false em erros de validacao
     */
    public boolean finalizarVenda(double valor, Moeda moeda) {
        // Aqui aplicamos Early Return para evitar if/else aninhado.
        if (valor < VALOR_MINIMO_VENDA) {
            System.out.printf("Valor invalido. Minimo permitido: %.2f%n", VALOR_MINIMO_VENDA);
            return false;
        }

        // Aqui aplicamos Early Return para validar entradas antes da regra principal.
        if (moeda == null) {
            System.out.println("Moeda nao informada.");
            return false;
        }

        // Aqui mantemos a regra de negocio explicita via constantes nomeadas.
        double taxa = calcularTaxa(valor, moeda);
        double valorFinal = valor + taxa;

        return servicoPagamento.processar(valorFinal, moeda);
    }

    // Comentario privado: taxa fixa so existe para BRL por regra local do gateway legado.
    private double calcularTaxa(double valor, Moeda moeda) {
        double taxaPercentual = valor * TAXA_PROCESSAMENTO_PERCENTUAL;

        if (moeda == Moeda.BRL) {
            return taxaPercentual + TAXA_FIXA_EM_BRL;
        }

        return taxaPercentual;
    }
}
