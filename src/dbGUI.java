import javax.swing.event.*;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
/*
 * Created by JFormDesigner on Thu Mar 31 12:26:45 MSK 2016
 */

/**
 * @author unknown
 */
public class dbGUI extends JFrame {
    private boolean active = false;

    public dbGUI() {
        initComponents();
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        progressBar1.setStringPainted(true);
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);
        try {
            progressBar1.setValue(100);
            Main.run(getLogin(), getPass());
        } catch (SQLException e1) {
            label1.setText("Invalid login or password");
        }
    }

    private void progressBar1StateChanged(ChangeEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Goga Tirkiya
        loginButton = new JButton();
        passwordField1 = new JPasswordField();
        formattedTextField1 = new JFormattedTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        progressBar1 = new JProgressBar();

        //======== this ========
        setMinimumSize(new Dimension(500, 370));
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        setName("startFrame");
        setVisible(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- loginButton ----
        loginButton.setText("Singin");
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));
        contentPane.add(loginButton);
        loginButton.setBounds(155, 205, 155, loginButton.getPreferredSize().height);
        contentPane.add(passwordField1);
        passwordField1.setBounds(155, 155, 160, 30);
        contentPane.add(formattedTextField1);
        formattedTextField1.setBounds(155, 115, 160, 30);

        //---- label1 ----
        label1.setText("Connect  to  server");
        label1.setForeground(Color.blue);
        contentPane.add(label1);
        label1.setBounds(180, 70, 110, 31);

        //---- label2 ----
        label2.setText("Login");
        contentPane.add(label2);
        label2.setBounds(80, 115, 60, 30);

        //---- label3 ----
        label3.setText("Passowrd");
        contentPane.add(label3);
        label3.setBounds(80, 155, 60, 30);

        //---- progressBar1 ----
        progressBar1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        contentPane.add(progressBar1);
        progressBar1.setBounds(120, 340, 251, progressBar1.getPreferredSize().height);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Goga Tirkiya
    private JButton loginButton;
    private JPasswordField passwordField1;
    private JFormattedTextField formattedTextField1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JProgressBar progressBar1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public String getLogin() {
        return formattedTextField1.getText();
    }

    public String getPass() {
        return passwordField1.getText();
    }
}
