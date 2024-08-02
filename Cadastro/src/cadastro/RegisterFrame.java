package cadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private UserDatabase userDatabase;

    public RegisterFrame(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;

        setTitle("Registro");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Nome:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Sobrenome:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Confirmar Senha:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new RegisterAction());
        add(registerButton);

        JButton loginButton = new JButton("Ir para Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame(userDatabase).setVisible(true);
                dispose();
            }
        });
        add(loginButton);
    }

    private class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(RegisterFrame.this, "As senhas não coincidem");
                return;
            }

            User existingUser = userDatabase.getUserByEmail(email);
            if (existingUser != null) {
                JOptionPane.showMessageDialog(RegisterFrame.this, "Email já registrado");
                return;
            }

            User newUser = new User(firstName, lastName, email, password);
            userDatabase.addUser(newUser);
            JOptionPane.showMessageDialog(RegisterFrame.this, "Registro bem-sucedido");

            new LoginFrame(userDatabase).setVisible(true);
            dispose();
        }
    }

  public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase();
        SwingUtilities.invokeLater(() -> new RegisterFrame(userDatabase).setVisible(true));
    }
    
}

 
