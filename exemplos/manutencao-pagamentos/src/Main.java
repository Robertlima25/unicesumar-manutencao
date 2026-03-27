public class Main {
    public static void main(String[] args) {
        ServicoPagamento gateway = new PagamentoPayPal();
        ProcessadorDeVendas processador = new ProcessadorDeVendas(gateway);

        System.out.println("=== Metodo novo (multimoedas) ===");
        processador.finalizarVenda(150.00, Moeda.BRL);
        processador.finalizarVenda(200.00, Moeda.USD);

        System.out.println();
        System.out.println("=== Metodo legado depreciado ===");
        processador.finalizarVenda(90.00);

        System.out.println();
        System.out.println("=== Validacao fail fast ===");
        processador.finalizarVenda(0.50, Moeda.BRL);
        processador.finalizarVenda(50.00, null);
    }
}
