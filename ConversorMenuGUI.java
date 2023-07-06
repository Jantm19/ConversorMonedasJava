import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorMenuGUI {
    private JFrame frame;

    public ConversorMenuGUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Conversor de Unidades");
        frame.setBounds(100, 100, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnConversorDivisas = new JButton("Conversor de Divisas");
        btnConversorDivisas.setBounds(50, 30, 200, 30);
        frame.getContentPane().add(btnConversorDivisas);

        JButton btnConversorKilos = new JButton("Conversor de Kilogramos");
        btnConversorKilos.setBounds(50, 80, 200, 30);
        frame.getContentPane().add(btnConversorKilos);

        btnConversorDivisas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra el menú actual
                abrirConversorDivisas();
            }
        });

        btnConversorKilos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cierra el menú actual
                abrirConversorKilos();
            }
        });

        frame.setVisible(true);
    }

    private void abrirConversorDivisas() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConversorMonedasGUI();
            }
        });
    }

    private void abrirConversorKilos() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConversorKilosGUI();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConversorMenuGUI();
            }
        });
    }
}
