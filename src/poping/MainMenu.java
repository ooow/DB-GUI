/*
 * Created by JFormDesigner on Tue Apr 05 00:46:53 MSK 2016
 */

package poping;

import java.awt.*;
import javax.swing.*;

/**
 * @author Goga Tirkiya
 */
public class MainMenu extends JFrame {
    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Goga Tirkiya
        comboBox1 = new JComboBox();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(800, 500));
        setVisible(true);
        setTitle("\u0421\u0423\u0411\u0414 GT");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- comboBox1 ----
        comboBox1.setSelectedIndex(0);
        contentPane.add(comboBox1);
        comboBox1.setBounds(15, 35, 135, 35);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(205, 15, 450, 255);

        //---- button1 ----
        button1.setText("text");
        contentPane.add(button1);
        button1.setBounds(55, 445, 145, button1.getPreferredSize().height);

        //---- button2 ----
        button2.setText("text");
        contentPane.add(button2);
        button2.setBounds(265, 445, 145, 32);

        //---- button3 ----
        button3.setText("text");
        contentPane.add(button3);
        button3.setBounds(490, 445, 145, 32);

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
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
