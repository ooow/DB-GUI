import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
/*
 * Created by JFormDesigner on Sun Apr 03 22:34:34 MSK 2016
 */


/**
 * @author Goga Tirkiya
 */
public class MainMenu extends JFrame {
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private DefaultTableModel dt;
    private String[] selected;
    private int row, column;
    private String forUpdate;

    public MainMenu(String[] items) {
        initComponents(items);
    }

    private void initComponents(String[] items) {
        comboBox1 = new JComboBox(items);
        scrollPane1 = new JScrollPane();
        try {
            String[] columName = Main.getColumn(items[0]);
            dt = new DefaultTableModel(Main.getData(items[0], columName.length), columName);
            table1 = new JTable();
            table1.setModel(dt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //======== this ========
        setMinimumSize(new Dimension(800, 500));
        setVisible(true);
        setTitle("\u0421\u0423\u0411\u0414 GT");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- table1 ---
        table1.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table1.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                try {
                    row = table1.getSelectedRow();
                    column = table1.getSelectedColumn();
                    forUpdate = (String) table1.getValueAt(row, column);
                    int count = table1.getColumnCount();
                    selected = new String[count];
                    for (int i = 0; i < count; i++) {
                        if (i != column)
                            selected[i] = (String) table1.getValueAt(row, i);
                    }
                } catch (ArrayIndexOutOfBoundsException e1) {
                    selected = null;
                }
                System.out.println("Selected: " + selected + ":" + row + " - " + column);
            }
        });

        //---- deleteButton ---
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));
        contentPane.add(deleteButton);
        deleteButton.setBounds(55, 400, 160, deleteButton.getPreferredSize().height);

        //---- updateButton ---
        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(e -> updateButtonActionPerformed(e));
        contentPane.add(updateButton);
        updateButton.setBounds(280, 400, 160, updateButton.getPreferredSize().height);

        //---- createButton ---
        createButton = new JButton();
        createButton.setText("Create");
        createButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createButton.addActionListener(e -> createButtonActionPerformed(e));
        contentPane.add(createButton);
        createButton.setBounds(510, 400, 160, createButton.getPreferredSize().height);

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
                            dt = new DefaultTableModel(Main.getData(items[i], columName.length), columName);
                            table1.setModel(dt);
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

    public String getItem(Object e) {
        String item = e.toString();
        item = item.substring(item.indexOf("selectedItemReminder=") + 21, item.length() - 1);
        return item;
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
    }

    private void updateButtonActionPerformed(ActionEvent e) {
        String tableName = (String) comboBox1.getSelectedItem();
        String columName = table1.getColumnName(column);
        String[] itemsName = new String[table1.getColumnCount()];
        for (int i = 0; i < itemsName.length; i++) {
            itemsName[i] = table1.getColumnName(i);
        }
        try {


            Main.update(tableName, columName, forUpdate, itemsName, selected);


        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void createButtonActionPerformed(ActionEvent e) {
    }
}
