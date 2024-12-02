//package view;
//
//import javax.swing.*;
//import java.awt.*;
//import interface_adapter.change_password.ChangePasswordController;
//
//public class ChangePasswordView extends JPanel {
//    public ChangePasswordView() {
//        this.setLayout(new GridLayout(3, 1));
//
//        JLabel label = new JLabel("Enter New Password:");
//        JPasswordField passwordField = new JPasswordField();
//        JButton submitButton = new JButton("Submit");
//
//        submitButton.addActionListener(e -> {
//            String newPassword = new String(passwordField.getPassword());
//            ChangePasswordController controller = new ChangePasswordController();
//            controller.execute(newPassword, passwordField.getText());
//        });
//
//        this.add(label);
//        this.add(passwordField);
//        this.add(submitButton);
//    }
//}