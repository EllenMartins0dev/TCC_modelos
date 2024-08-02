package cadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private UserDatabase userDatabase;

    public LoginFrame(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;

        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());
        add(loginButton);

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame(userDatabase).setVisible(true);
                dispose();
            }
        });
        add(registerButton);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            User user = userDatabase.getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login bem-sucedido");
                // Aqui você pode abrir uma nova tela após o login bem-sucedido
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Email ou senha incorretos");
            }
        }
    }
    
}

