import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConversorKilosGUI {
    private JFrame frame;
    private JTextField valorInput;
    private JComboBox<String> unidadOrigenSelect;
    private JComboBox<String> unidadDestinoSelect;
    private JTextField resultadoInput;

    private final DecimalFormat decimalFormat;

    public ConversorKilosGUI() {
        decimalFormat = new DecimalFormat("#.00");
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Conversor de Kilos");
        frame.setBounds(100, 100, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(20, 20, 80, 25);
        frame.getContentPane().add(lblValor);

        valorInput = new JTextField();
        valorInput.setBounds(100, 20, 100, 25);
        frame.getContentPane().add(valorInput);

        JLabel lblUnidadOrigen = new JLabel("Unidad de Origen:");
        lblUnidadOrigen.setBounds(20, 60, 150, 25);
        frame.getContentPane().add(lblUnidadOrigen);

        unidadOrigenSelect = new JComboBox<>(new String[]{"Kg", "Gramos", "Libras", "Onzas"});
        unidadOrigenSelect.setBounds(160, 60, 100, 25);
        frame.getContentPane().add(unidadOrigenSelect);

        JLabel lblUnidadDestino = new JLabel("Unidad de Destino:");
        lblUnidadDestino.setBounds(20, 100, 150, 25);
        frame.getContentPane().add(lblUnidadDestino);

        unidadDestinoSelect = new JComboBox<>(new String[]{"Kg", "Gramos", "Libras", "Onzas"});
        unidadDestinoSelect.setBounds(160, 100, 100, 25);
        frame.getContentPane().add(unidadDestinoSelect);

        JButton btnConvertir = new JButton("Convertir");
        btnConvertir.setBounds(100, 140, 100, 30);
        frame.getContentPane().add(btnConvertir);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(20, 180, 80, 25);
        frame.getContentPane().add(lblResultado);

        resultadoInput = new JTextField();
        resultadoInput.setBounds(100, 180, 350, 45);
        resultadoInput.setEditable(false);
        frame.getContentPane().add(resultadoInput);

        btnConvertir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double valor = Double.parseDouble(valorInput.getText());
                String unidadOrigen = (String) unidadOrigenSelect.getSelectedItem();
                String unidadDestino = (String) unidadDestinoSelect.getSelectedItem();
                double resultado = convertirUnidades(valor, unidadOrigen, unidadDestino);
                resultadoInput.setText(valor + " " + unidadOrigen + " es " + decimalFormat.format(resultado) + " " + unidadDestino);
            }
        });
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuPrincipal = new JMenu("Menú Principal");
        menuBar.add(menuPrincipal);

        JMenuItem regresarItem = new JMenuItem("Regresar");
        menuPrincipal.add(regresarItem);

        regresarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra la ventana actual
                abrirMenuPrincipal();
            }
        });

        frame.setVisible(true);
    }
    private void abrirMenuPrincipal() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConversorMenuGUI();
            }
        });
    }

    private double convertirUnidades(double valor, String unidadOrigen, String unidadDestino) {
    if (unidadOrigen.equals(unidadDestino)) {
        return valor; // No se necesita conversión, se devuelve el valor original
    }

    double resultado = 0.0;

    switch (unidadOrigen) {
        case "Kg":
            switch (unidadDestino) {
                case "Gramos":
                    resultado = valor * 1000;
                    break;
                case "Libras":
                    resultado = valor * 2.20462;
                    break;
                case "Onzas":
                    resultado = valor * 35.274;
                    break;
                default:
                    break;
            }
            break;
        case "Gramos":
            switch (unidadDestino) {
                case "Kg":
                    resultado = valor / 1000;
                    break;
                case "Libras":
                    resultado = valor * 0.00220462;
                    break;
                case "Onzas":
                    resultado = valor * 0.035274;
                    break;
                default:
                    break;
            }
            break;
        case "Libras":
            switch (unidadDestino) {
                case "Kg":
                    resultado = valor * 0.453592;
                    break;
                case "Gramos":
                    resultado = valor * 453.592;
                    break;
                case "Onzas":
                    resultado = valor * 16;
                    break;
                default:
                    break;
            }
            break;
        case "Onzas":
            switch (unidadDestino) {
                case "Kg":
                    resultado = valor * 0.0283495;
                    break;
                case "Gramos":
                    resultado = valor * 28.3495;
                    break;
                case "Libras":
                    resultado = valor * 0.0625;
                    break;
                default:
                    break;
            }
            break;
        default:
            break;
    }

    return resultado;
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new ConversorKilosGUI();
        }
    });
}
}
