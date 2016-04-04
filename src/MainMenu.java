import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun Apr 03 22:34:34 MSK 2016
 */


/**
 * @author Goga Tirkiya
 */
public class MainMenu extends JFrame {
    public MainMenu(String[] items) {
        initComponents(items);
    }

    private void initComponents(String[] items) {
        comboBox1 = new JComboBox(items);
        scrollPane1 = new JScrollPane();
        try {
            String[] columName = Main.getColumn(items[0]);
            table1 = new JTable(Main.getData(items[0], columName.length), columName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = getItem(e.getSource());
                for (int i = 0; i < items.length; i++) {
                    if (item.equals(items[i])) {
                        try {
                            String[] columName = Main.getColumn(items[i]);
                            table1 = new JTable(Main.getData(items[i], columName.length), columName);
                            scrollPane1.setViewportView(table1);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(205, 15, 450, 255);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
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
    }

    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;

    public String getItem(Object e) {
        String item = e.toString();
        item = item.substring(item.indexOf("selectedItemReminder=") + 21, item.length() - 1);
        return item;
    }
}
