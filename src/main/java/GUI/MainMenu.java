package GUI;

/**
 * Created by Гога on 18.04.2016.
 */


import DAO.ViewTable;
import Model.*;
import hibernate.HibernateConfig;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class MainMenu extends JFrame {
    private JButton deleteButton;
    private JButton updateButton;
    private JButton createButton;
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel infoLabel;
    private DefaultTableModel dt;
    private String selectedId;
    private String[] selected;
    private String forUpdate;
    private int row, column;
    private String[] items;

    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        Association[] tablesName = new ViewTable().getTablesName();
        items = new String[tablesName.length];
        for (int i = 0; i < tablesName.length; i++) {
            items[i] = tablesName[i].getName();
        }
        comboBox1 = new JComboBox(items);
        scrollPane1 = new JScrollPane();
        infoLabel = new JLabel();
        JTableItem jTableItem = new ViewTable().getTable(new Client());
        dt = new DefaultTableModel(jTableItem.getItems(), jTableItem.getColumNames());
        dt.addRow(new String[items.length]);
        table1 = new JTable();
        table1.setModel(dt);

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

        //---- table1 ---
        table1.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table1.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(valueChanged());

        //---- deleteButton ---
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
/*        deleteButton.addActionListener(e -> deleteButtonActionPerformed(e));*/

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

/*        createButton.addActionListener(e -> createButtonActionPerformed(e));*/
        contentPane.add(createButton);
        createButton.setBounds(510, 400, 160, createButton.getPreferredSize().height);

        //---- comboBox1 ----
        comboBox1.setSelectedIndex(0);
        contentPane.add(comboBox1);
        comboBox1.setBounds(15, 35, 135, 35);
        comboBox1.addActionListener(e -> cbAction(e));

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

    /*    private void deleteButtonActionPerformed(ActionEvent e) {
            String tableName = (String) comboBox1.getSelectedItem();
            String[] itemsName = new String[table1.getColumnCount()];
            for (int i = 0; i < itemsName.length; i++) {
                itemsName[i] = table1.getColumnName(i);
            }
            try {
                Main.delete(tableName, itemsName, selected);
                comboBox1.setSelectedItem(comboBox1.getSelectedItem());
            } catch (SQLException e1) {
                infoLabel.setText("Errore: Impossible remove, found dependency");
            }
        }
*/
    private void updateButtonActionPerformed(ActionEvent e) {
        String tableName = (String) comboBox1.getSelectedItem();
        String columName = table1.getColumnName(column);
        String[] itemsName = new String[table1.getColumnCount()];
        infoLabel.setText("");
        for (int i = 0; i < itemsName.length; i++) {
            itemsName[i] = table1.getColumnName(i);
        }
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Association[] tablesName = new ViewTable().getTablesName();
        Model classs = null;
        for (int i = 0; i < tablesName.length; i++) {
            if (tableName.equals(tablesName[i].getName())) {
                classs = tablesName[i].getModel();
            }
        }
        String sql = "UPDATE " + classs.getClassName() + " SET " + columName + " = " + "'" + forUpdate + "'" +
                " WHERE id = " + "'" + selectedId + "'";
        System.out.println(sql);
        try {
            session.getTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.executeUpdate();
        } catch (Exception ee) {
            session.getTransaction().rollback();
            infoLabel.setText("Невозможно выполнить операцию");
        } finally {
            session.close();
        }
    }

    private void createButtonActionPerformed(ActionEvent e) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        String tableName = (String) comboBox1.getSelectedItem();
        Association[] tablesName = new ViewTable().getTablesName();
        Model classs = null;
        for (int i = 0; i < tablesName.length; i++) {
            if (tableName.equals(tablesName[i].getName())) {
                classs = tablesName[i].getModel();
            }
        }
        String sql = "INSERT INTO " + classs.getClassName() + " VALUES (";
        for (int i = 0; i < selected.length; i++) {
            sql += "'" + selected[i] + "'";
            if (selected.length - i > 1)
                sql += ", ";
            else sql += ")";
        }
        try {
            session.getTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.executeUpdate();
        } catch (Exception ee) {
            session.getTransaction().rollback();
            infoLabel.setText("Невозможно выполнить операцию");
        } finally {
            session.close();
        }
    }

    private void cbAction(ActionEvent e) {
        JTableItem jTableItem = null;
        String item = getItem(e.getSource());
        Association[] tablesName = new ViewTable().getTablesName();
        for (int i = 0; i < tablesName.length; i++) {
            if (item.equals(tablesName[i].getName())) {
                jTableItem = new ViewTable().getTable(tablesName[i].getModel());
            }
        }
        dt = new DefaultTableModel(jTableItem.getItems(), jTableItem.getColumNames());
        dt.addRow(new String[items.length]);
        table1.setModel(dt);
        scrollPane1.setViewportView(table1);
        infoLabel.setText("");
    }


    public ListSelectionListener valueChanged() {
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    row = table1.getSelectedRow();
                    column = table1.getSelectedColumn();
                    forUpdate = (String) table1.getValueAt(row, column);
                    selectedId = (String) table1.getValueAt(row, 0);
                    infoLabel.setText("");
                    int count = table1.getColumnCount();
                    selected = new String[count];
                    for (int i = 0; i < count; i++) {
                        selected[i] = (String) table1.getValueAt(row, i);
                    }
                } catch (ArrayIndexOutOfBoundsException e1) {
                    selectedId = null;
                    infoLabel.setText("Выделите поле");
                }
                System.out.println("Selected: " + row + " - " + column);
            }
        };
    }
}