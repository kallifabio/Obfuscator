package de.kallifabio.codehider;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField intputField;
    private JTextField outputField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Gui frame = new Gui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Gui() {
        setResizable(false);
        setTitle("Obfuscator by kallifabio v1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 331, 187);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblInput = new JLabel("Input: ");
        lblInput.setBounds(10, 11, 46, 14);
        contentPane.add(lblInput);

        intputField = new JTextField();
        intputField.setBounds(10, 27, 199, 20);
        contentPane.add(intputField);
        intputField.setColumns(10);

        JButton inputBtn = new JButton(".....");
        inputBtn.setBounds(219, 28, 53, 18);
        contentPane.add(inputBtn);
        inputBtn.addActionListener((ActionEvent event) -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Input-Jar wählen");
            jfc.showOpenDialog(contentPane);
            try {
                intputField.setText(jfc.getSelectedFile().getAbsolutePath());
            } catch (Exception e1) {
                System.out.println("Fehler beim Input-Feld");
            }
        });

        JButton outputBtn = new JButton(".....");
        outputBtn.setBounds(219, 75, 53, 18);
        contentPane.add(outputBtn);
        outputBtn.addActionListener((ActionEvent event) -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Output-Ordner setzen");
            jfc.showOpenDialog(contentPane);
            try {
                outputField.setText(jfc.getSelectedFile().getAbsolutePath());
            } catch (Exception e2) {
                System.out.println("Fehler beim Output-Feld");
            }
        });

        outputField = new JTextField();
        outputField.setColumns(10);
        outputField.setBounds(10, 74, 199, 20);
        contentPane.add(outputField);

        JLabel lblOutput = new JLabel("Output: ");
        lblOutput.setBounds(10, 58, 46, 14);
        contentPane.add(lblOutput);

        JButton btnStart = new JButton("Starten");
        btnStart.setBounds(93, 105, 139, 23);
        contentPane.add(btnStart);
        btnStart.addActionListener((ActionEvent event) -> {
            if (intputField.getText().isEmpty() || outputField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Du musst alle Felder ausfüllen!");
                return;
            }
            if (!intputField.getText().endsWith(".jar")) {
                JOptionPane.showMessageDialog(null, "Deine Input-Jar muss enden mit \".jar\"");
                return;
            }
            try {
                CodeHider.hideCode(intputField.getText(), outputField.getText());
            } catch (Throwable e1) {
                System.out.println("Error!");
                e1.printStackTrace();
            }
        });
    }
}
