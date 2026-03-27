/**
 * Define o contrato de processamento de pagamentos.
 * Implementacoes concretas podem encapsular integracoes com gateways diferentes.
 */
public interface ServicoPagamento {

    /**
     * Processa um pagamento com valor e moeda informados.
     *
     * @param valor valor final da transacao ja com taxas aplicadas
     * @param moeda moeda da transacao
     * @return true quando o gateway aprova o pagamento; false caso contrario
     */
    boolean processar(double valor, Moeda moeda);
}
