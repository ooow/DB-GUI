package GUI;

import DAO.DML;
import DAO.Mapping;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * @author Goga Tirkiya
 */

public class MainMenu extends JFrame {
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel infoLabel;
    private DefaultTableModel dt;
    private Association[] associations;
    private String[] items;
    private String[] selected;
    private String forUpdate;
    private int row, column;

    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        associations = new Association().getItems();
        items = new String[associations.length];
        for (int i = 0; i < associations.length; i++) {
            items[i] = associations[i].getRusName();
        }
        comboBox1 = new JComboBox(items);
        scrollPane1 = new JScrollPane();
        infoLabel = new JLabel();
        String firstItem = associations[0].getTableName();
        refresh(firstItem);

        //======== this ========
        setMinimumSize(new Dimension(800, 500));
        setVisible(true);
        setTitle("\u0421\u0423\u0411\u0414 GT");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        infoLabel.setText("");
        contentPane.add(infoLabel);
        infoLabel.setBounds(205, 295, 450, 25);

        //---- deleteButton ---
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> deleteButtonActionPerformed());
        contentPane.add(deleteButton);
        deleteButton.setBounds(55, 400, 160, deleteButton.getPreferredSize().height);

        //---- updateButton ---
        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(e -> updateButtonActionPerformed());
        contentPane.add(updateButton);
        updateButton.setBounds(280, 400, 160, updateButton.getPreferredSize().height);

        //---- createButton ---
        createButton = new JButton();
        createButton.setText("Create");
        createButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createButton.addActionListener(e -> createButtonActionPerformed());
        contentPane.add(createButton);
        createButton.setBounds(510, 400, 160, createButton.getPreferredSize().height);

        //---- comboBox1 ----
        comboBox1.setSelectedIndex(0);
        contentPane.add(comboBox1);
        comboBox1.setBounds(15, 35, 135, 35);
        comboBox1.addActionListener(e -> cbAction(e));

        //======== scrollPane1 ========
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(205, 15, 500, 300);

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

    private void cbAction(ActionEvent e) {
        String tableName = new Association().getTableNameByRusName(getItem(e.getSource()));
        refresh(tableName);
    }

    public void refresh(String tableName) {
        try {
            String[] columns = Mapping.getColumns(tableName);
            JTableItem jTableItem = new JTableItem(columns, Mapping.getData(tableName, columns.length));
            dt = new DefaultTableModel(jTableItem.getData(), jTableItem.getColumNames());
            dt.addRow(new String[columns.length]);
            table1 = new JTable();
            table1.setModel(dt);
            table1.setCellSelectionEnabled(true);
            ListSelectionModel cellSelectionModel = table1.getSelectionModel();
            cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cellSelectionModel.addListSelectionListener(valueChanged());
            scrollPane1.setViewportView(table1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteButtonActionPerformed() {
        String tableName = new Association().getTableNameByRusName((String) comboBox1.getSelectedItem());
        String[] itemsName = new String[table1.getColumnCount()];
        for (int i = 0; i < itemsName.length; i++) {
            itemsName[i] = table1.getColumnName(i);
        }
        try {
            DML.delete(tableName, itemsName, selected);
            comboBox1.setSelectedItem(comboBox1.getSelectedItem());
        } catch (SQLException e1) {
            infoLabel.setText("Errore: Impossible remove, found dependency");
        }
    }

    private void updateButtonActionPerformed() {
        String tableName = new Association().getTableNameByRusName((String) comboBox1.getSelectedItem());
        String columName = table1.getColumnName(column);
        String[] itemsName = new String[table1.getColumnCount()];
        infoLabel.setText("");
        for (int i = 0; i < itemsName.length; i++) {
            itemsName[i] = table1.getColumnName(i);
        }
        try {
            DML.update(tableName, columName, forUpdate, itemsName, selected);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void createButtonActionPerformed() {
        String tableName = new Association().getTableNameByRusName((String) comboBox1.getSelectedItem());
        try {
            DML.insert(tableName, selected);
            comboBox1.setSelectedItem(comboBox1.getSelectedItem());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public ListSelectionListener valueChanged() {
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    row = table1.getSelectedRow();
                    column = table1.getSelectedColumn();
                    forUpdate = (String) table1.getValueAt(row, column);
                    int count = table1.getColumnCount();
                    selected = new String[count];
                    for (int i = 0; i < count; i++) {
                        selected[i] = (String) table1.getValueAt(row, i);
                    }
                    infoLabel.setText("");
                } catch (ArrayIndexOutOfBoundsException e1) {
                    selected = null;
                }
                System.out.println("Selected:" + row + " - " + column);
            }
        };
    }
}
