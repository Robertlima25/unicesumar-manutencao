/**
 * Implementacao concreta do servico de pagamento para o gateway PayPal.
 */
public class PagamentoPayPal implements ServicoPagamento {

    @Override
    public boolean processar(double valor, Moeda moeda) {
        System.out.printf("[PayPal] Processando pagamento de %.2f %s%n", valor, moeda);

        // Simulacao simples de aprovacao para fins didaticos.
        return valor > 0;
    }
}
