package GUI;

import DAO.DML;
import DAO.Mapping;
import DAO.SQLRequestProcessing;

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
    private JButton requestButton1;
    private JButton requestButton2;
    private JButton requestButton3;
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel backgroundLabel;
    private JLabel infoLabel;
    private JLabel requestLabel;
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
        backgroundLabel = new JLabel();
        infoLabel = new JLabel();
        requestLabel = new JLabel();
        String firstItem = associations[0].getTableName();
        refresh(firstItem);

        //======== this ========
        setMinimumSize(new Dimension(800, 500));
/*        setResizable(false);*/
        setVisible(true);
        setTitle("\u0421\u0423\u0411\u0414 GT");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        infoLabel.setFont(new Font("Calibri", table1.getFont().getStyle(), table1.getFont().getSize() + 1));
        infoLabel.setForeground(Color.white);
        contentPane.add(infoLabel);
        infoLabel.setBounds(150, 430, 600, 25);

        //---- label2 ----
        requestLabel.setFont(new Font("Calibri", table1.getFont().getStyle(), table1.getFont().getSize() + 1));
        requestLabel.setForeground(Color.white);
        contentPane.add(requestLabel);
        requestLabel.setBounds(50, 350, 700, 25);

        //---- deleteButton ---
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> deleteButtonActionPerformed());
        contentPane.add(deleteButton);
        deleteButton.setBounds(55, 400, 160, 25);

        //---- updateButton ---
        updateButton = new JButton();
        updateButton.setText("Update");
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(e -> updateButtonActionPerformed());
        contentPane.add(updateButton);
        updateButton.setBounds(280, 400, 160, 25);

        //---- createButton ---
        createButton = new JButton();
        createButton.setText("Create");
        createButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createButton.addActionListener(e -> createButtonActionPerformed());
        contentPane.add(createButton);
        createButton.setBounds(510, 400, 160, 25);

        //---- requestButton1 ---
        requestButton1 = new JButton();
        requestButton1.setText("Подсчет статистики");
        requestButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        requestButton1.addActionListener(e -> requestButton1ActionPerformed());

        contentPane.add(requestButton1);
        requestButton1.setBounds(15, 220, 160, 25);

        //---- requestButton2 ---
        requestButton2 = new JButton();
        requestButton2.setText("Бухгалтерский отчет");
        requestButton2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        requestButton2.addActionListener(e -> requestButton2ActionPerformed());
        contentPane.add(requestButton2);
        requestButton2.setBounds(15, 250, 160, 25);

        //---- requestButton3 ---
        requestButton3 = new JButton();
        requestButton3.setText("Типы абонементов");
        requestButton3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        requestButton3.addActionListener(e -> requestButton3ActionPerformed());
        contentPane.add(requestButton3);
        requestButton3.setBounds(15, 280, 160, 25);

        //---- comboBox1 ----
        comboBox1.setSelectedIndex(0);
        contentPane.add(comboBox1);
        comboBox1.setBounds(15, 35, 135, 35);
        comboBox1.addActionListener(e -> cbAction(e));

        //======== scrollPane1 ========
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(205, 15, 550, 300);

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

        //---- backgroundLabel ----
        backgroundLabel.setIcon(new ImageIcon("C:\\Users\\\u0413\u043e\u0433\u0430\\IdeaProjects\\DB-GUI\\backgound.jpg"));
        contentPane.add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 800, 500);
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
            table1.setBackground(new Color(102, 102, 102));
            table1.setFillsViewportHeight(true);
            table1.setFont(new Font("Calibri", table1.getFont().getStyle(), table1.getFont().getSize() + 1));
            table1.setForeground(Color.white);
            ListSelectionModel cellSelectionModel = table1.getSelectionModel();
            cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cellSelectionModel.addListSelectionListener(valueChanged());
            scrollPane1.setViewportView(table1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void deleteButtonActionPerformed() {
        boolean flag = true;
        String tableName = new Association().getTableNameByRusName((String) comboBox1.getSelectedItem());
        String[] itemsName = new String[table1.getColumnCount()];
        for (int i = 0; i < itemsName.length; i++) {
            itemsName[i] = table1.getColumnName(i);
        }
        try {
            DML.delete(tableName, itemsName, selected);
            comboBox1.setSelectedItem(comboBox1.getSelectedItem());
        } catch (SQLException e) {
            flag = false;
            infoLabel.setText(e.getMessage());
        } catch (NullPointerException e) {
            flag = false;
            infoLabel.setText("Выделите поле");
        } finally {
            if (flag)
                infoLabel.setText("Запись успешно удалена");
        }
    }

    private void updateButtonActionPerformed() {
        boolean flag = true;
        String tableName = new Association().getTableNameByRusName((String) comboBox1.getSelectedItem());
        String columName = table1.getColumnName(column);
        String[] itemsName = new String[table1.getColumnCount()];
        infoLabel.setText("");
        for (int i = 0; i < itemsName.length; i++) {
            itemsName[i] = table1.getColumnName(i);
        }
        try {
            if (tableName.equals("ClientsView")) {
                DML.delete(tableName, itemsName, selected);
                DML.insert(tableName, selected);
            } else {
                DML.update(tableName, columName, forUpdate, itemsName, selected);
            }
            comboBox1.setSelectedItem(comboBox1.getSelectedItem());
        } catch (SQLException e) {
            flag = false;
            infoLabel.setText(e.getMessage());
        } catch (NullPointerException e) {
            flag = false;
            infoLabel.setText("Выделите поле");
        } finally {
            if (flag)
                infoLabel.setText("Запись успешно изменена");
        }
    }

    private void createButtonActionPerformed() {
        boolean flag = true;
        String tableName = new Association().getTableNameByRusName((String) comboBox1.getSelectedItem());
        try {
            DML.insert(tableName, selected);
            comboBox1.setSelectedItem(comboBox1.getSelectedItem());
        } catch (SQLException e) {
            flag = false;
            infoLabel.setText(e.getMessage());
        } catch (NullPointerException e) {
            flag = false;
            infoLabel.setText("Выделите поле");
        } finally {
            if (flag)
                infoLabel.setText("Запись успешно добавлена");
        }
    }

    private void requestButton1ActionPerformed() {
        String[] statistic = new SQLRequestProcessing().getStatistic();
        String out = "Клиентов:   " + statistic[0] + "            Женщин:   " + statistic[1] +
                "            Мужчин:   " + statistic[2];
        requestLabel.setText(out);
    }

    private void requestButton2ActionPerformed() {
        String[] statistic = new SQLRequestProcessing().getReport();
        String out = "Абонементов продано:   " + statistic[0] + "            Общая сумма:   " + statistic[1] +
                "            Безлимитных:   " + statistic[2] + "            Ограниченных:   " + statistic[3];
        requestLabel.setText(out);
    }

    private void requestButton3ActionPerformed() {
        String[] statistic = new SQLRequestProcessing().getSpace();
        String out = "Абонементов на 360:   " + statistic[0] + "            Абонементов на 180:   " + statistic[1] +
                "            Абонементов на 30:   " + statistic[2];
        requestLabel.setText(out);
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
