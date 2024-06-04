import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Reserva> reservas = new ArrayList<>();
    private static List<Reserva> listaEspera = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu de Opções");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setIcon(resizeImageIcon("src/img/logo.jpeg", 200, 200));
        panel.add(logoLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        String[] opcoes = {"Reservar mesa", "Pesquisar reserva", "Imprimir reservas", "Imprimir lista de espera", "Cancelar reserva", "Sair"};

        for (int i = 0; i < opcoes.length; i++) {
            String opcao = opcoes[i];
            JButton button = new JButton(opcao);
            int index = i;
            button.addActionListener(e -> mostrarOpcao(index));
            buttonPanel.add(button);
        }


        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static ImageIcon resizeImageIcon(String filePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(filePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void mostrarOpcao(int opcao) {
        switch (opcao) {
            case 0:
                reservarMesa();
                break;
            case 1:
                pesquisarReserva();
                break;
            case 2:
                imprimirReservas();
                break;
            case 3:
                imprimirListaEspera();
                break;
            case 4:
                cancelarReserva();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!");
        }
    }

    private static void reservarMesa() {
        String nome = JOptionPane.showInputDialog("Nome:");
        if (nome == null) return;

        String[] tiposCliente = {"Pessoa Física", "Pessoa Jurídica"};
        int tipoCliente = JOptionPane.showOptionDialog(null, "Tipo de Cliente:", "Tipo de Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, tiposCliente, tiposCliente[0]);
        if (tipoCliente == JOptionPane.CLOSED_OPTION) return;

        Cliente cliente = null;
        if (tipoCliente == 0) {
            String cpf = JOptionPane.showInputDialog("CPF:");
            if (cpf == null) return;
            cliente = new PessoaFisica(nome, cpf);
        } else if (tipoCliente == 1) {
            String cnpj = JOptionPane.showInputDialog("CNPJ:");
            if (cnpj == null) return;
            cliente = new PessoaJuridica(nome, cnpj);
        } else {
            JOptionPane.showMessageDialog(null, "Tipo de cliente inválido!");
            return;
        }

        String[] formasPagamento = {"À vista", "Parcelado"};
        int formaPagamento = JOptionPane.showOptionDialog(null, "Forma de pagamento:", "Forma de pagamento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, formasPagamento, formasPagamento[0]);
        if (formaPagamento == JOptionPane.CLOSED_OPTION) return;
        boolean pagamentoAVista = (formaPagamento == 0);

        Reserva reserva = new Reserva(cliente, pagamentoAVista);
        if (reservas.size() < 6) {
            reservas.add(reserva);
            JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!\n" + reserva);
        } else {
            listaEspera.add(reserva);
            JOptionPane.showMessageDialog(null, "Todas as mesas estão reservadas. Você foi adicionado à lista de espera.\n" + reserva);
        }
    }

    private static void pesquisarReserva() {
        String documento = JOptionPane.showInputDialog("CPF ou CNPJ:");
        if (documento == null) return;

        for (Reserva reserva : reservas) {
            if ((reserva.getCliente() instanceof PessoaFisica && ((PessoaFisica) reserva.getCliente()).getCpf().equals(documento)) ||
                    (reserva.getCliente() instanceof PessoaJuridica && ((PessoaJuridica) reserva.getCliente()).getCnpj().equals(documento))) {
                JOptionPane.showMessageDialog(null, "Reserva encontrada:\n" + reserva);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada para o documento informado.");
    }

    private static void imprimirReservas() {
        StringBuilder sb = new StringBuilder("Reservas confirmadas:\n");
        for (int i = 0; i < Math.min(6, reservas.size()); i++) {
            sb.append(reservas.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void imprimirListaEspera() {
        StringBuilder sb = new StringBuilder("Lista de espera:\n");
        for (int i = 0; i < listaEspera.size(); i++) {
            sb.append((i + 1) + ". " + listaEspera.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void cancelarReserva() {
        String documento = JOptionPane.showInputDialog("CPF ou CNPJ:");
        if (documento == null) return;

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if ((reserva.getCliente() instanceof PessoaFisica && ((PessoaFisica) reserva.getCliente()).getCpf().equals(documento)) ||
                    (reserva.getCliente() instanceof PessoaJuridica && ((PessoaJuridica) reserva.getCliente()).getCnpj().equals(documento))) {
                reservas.remove(i);
                JOptionPane.showMessageDialog(null, "Reserva cancelada:\n" + reserva);
                if (!listaEspera.isEmpty()) {
                    reservas.add(listaEspera.remove(0));
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Nenhuma reserva encontrada para o documento informado.");
    }
}
