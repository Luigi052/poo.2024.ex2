public class Reserva implements Pagamento {
    private Cliente cliente;
    private boolean pagamentoAVista;

    public Reserva(Cliente cliente, boolean pagamentoAVista) {
        this.cliente = cliente;
        this.pagamentoAVista = pagamentoAVista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isPagamentoAVista() {
        return pagamentoAVista;
    }

    public void setPagamentoAVista(boolean pagamentoAVista) {
        this.pagamentoAVista = pagamentoAVista;
    }

    @Override
    public double calcularPagamento() {
        return pagamentoAVista ? 3200.0 * 0.9 : 3200.0;
    }

    @Override
    public String toString() {
        String tipoCliente = (cliente instanceof PessoaFisica) ? "Pessoa Física" : "Pessoa Jurídica";
        String pagamento = pagamentoAVista ? "à vista" : "parcelado";
        return tipoCliente + " - " + cliente.getNome() + ", Pagamento: " + pagamento;
    }
}
