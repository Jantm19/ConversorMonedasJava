//Librerias a utilizar 
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorMonedasGUI {
    private JFrame frame;
    private JTextField valorInput;
    private JComboBox<String> monedaOrigenSelect;
    private JComboBox<String> monedaDestinoSelect;
    private JTextField resultadoInput;

    public ConversorMonedasGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Conversor de Divisas");
        frame.setBounds(100, 100, 350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(20, 20, 80, 25);
        frame.getContentPane().add(lblValor);

        valorInput = new JTextField();
        valorInput.setBounds(100, 20, 100, 25);
        frame.getContentPane().add(valorInput);

        JLabel lblMonedaOrigen = new JLabel("Moneda de Origen:");
        lblMonedaOrigen.setBounds(20, 60, 150, 25);
        frame.getContentPane().add(lblMonedaOrigen);

        monedaOrigenSelect = new JComboBox<>(new String[]{"MXN", "USD", "EUR", "GBP", "JPY"});
        monedaOrigenSelect.setBounds(160, 60, 80, 25);
        frame.getContentPane().add(monedaOrigenSelect);

        JLabel lblMonedaDestino = new JLabel("Moneda de Destino:");
        lblMonedaDestino.setBounds(20, 100, 150, 25);
        frame.getContentPane().add(lblMonedaDestino);

        monedaDestinoSelect = new JComboBox<>(new String[]{"MXN", "USD", "EUR", "GBP", "JPY"});
        monedaDestinoSelect.setBounds(160, 100, 80, 25);
        frame.getContentPane().add(monedaDestinoSelect);

        JButton btnConvertir = new JButton("Convertir");
        btnConvertir.setBounds(100, 140, 100, 30);
        frame.getContentPane().add(btnConvertir);

        JLabel lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(20, 180, 80, 25);
        frame.getContentPane().add(lblResultado);

        resultadoInput = new JTextField();
        resultadoInput.setBounds(100, 180, 200, 25);
        resultadoInput.setEditable(false);
        frame.getContentPane().add(resultadoInput);

        btnConvertir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double valor = Double.parseDouble(valorInput.getText());
                String monedaOrigen = (String) monedaOrigenSelect.getSelectedItem();
                String monedaDestino = (String) monedaDestinoSelect.getSelectedItem();
                double resultado = convertirDivisas(valor, monedaOrigen, monedaDestino);
                resultadoInput.setText(valor + " " + monedaOrigen + " es " + resultado + " " + monedaDestino);
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

private double convertirDivisas(double valor, String monedaOrigen, String monedaDestino) {
    if (monedaOrigen.equals(monedaDestino)) {
        return valor; // No se necesita conversión, se devuelve el valor original
    }

    double tasaConversion = 0.0;

    switch (monedaOrigen) {
        case "MXN":
            switch (monedaDestino) {
                case "USD":
                    tasaConversion = 0.059;
                    break;
                case "EUR":
                    tasaConversion = 0.054;
                    break;
                case "GBP":
                    tasaConversion = 0.046;
                    break;
                case "JPY":
                    tasaConversion = 8.47;
                    break;
                case "WN":
                    tasaConversion = 76.29;
                    break;
                default:
                    break;
            }
            break;
        case "USD":
            if (monedaDestino.equals("MXN")) {
                tasaConversion = 17.06;
            }
            break;
        case "EUR":
            if (monedaDestino.equals("MXN")) {
                tasaConversion = 18.60;
            }
            break;
        case "GBP":
            if (monedaDestino.equals("MXN")) {
                tasaConversion = 21.65;
            }
            break;
        case "JPY":
            if (monedaDestino.equals("MXN")) {
                tasaConversion = 0.12;
            }
            break;
        case "WN":
            if (monedaDestino.equals("MXN")) {
                tasaConversion = 0.013;
            }
            break;
        default:
            break;
    }

    if (tasaConversion != 0.0) {
        return valor * tasaConversion;
    } else {
        // Si no se encuentra una tasa de conversión válida, retorna 0.0
        return 0.0;
    }
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConversorMonedasGUI();
            }
        });
    }
}
