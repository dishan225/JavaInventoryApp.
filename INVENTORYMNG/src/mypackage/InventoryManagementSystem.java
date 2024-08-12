package mypackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class InventoryManagementSystem extends JFrame {

    private static final long serialVersionUID = 1L;

    private Inventory inventory;
    private JTextField txtId, txtName, txtQty, txtPrice, txtOrderQty;
    private JTextPane displayPane;

    public InventoryManagementSystem() {
        inventory = new Inventory();
        createUI();
    }

    private void createUI() {
        setTitle("Inventory Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));

        JLabel lblId = new JLabel("Item ID:", SwingConstants.CENTER);
        JLabel lblName = new JLabel("Item Name:", SwingConstants.CENTER);
        JLabel lblQty = new JLabel("Quantity:", SwingConstants.CENTER);
        JLabel lblPrice = new JLabel("Price:", SwingConstants.CENTER);
        JLabel lblOrderQty = new JLabel("Order Quantity:", SwingConstants.CENTER);
        JLabel empty1 = new JLabel("");
        JLabel empty2 = new JLabel("");

        txtId = new JTextField();
        txtName = new JTextField();
        txtQty = new JTextField();
        txtPrice = new JTextField();
        txtOrderQty = new JTextField();

        JButton btnAdd = new JButton("Add Item");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        JButton btnRemove = new JButton("Remove Item");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });

        JButton btnSearch = new JButton("Search Item");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchItem();
            }
        });

        JButton btnUpdate = new JButton("Update Item");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateItem();
            }
        });

        JButton btnView = new JButton("View All Items");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewItems();
            }
        });

        JButton btnOrder = new JButton("Order Item");
        btnOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderItem();
            }
        });

        JButton btnMostSold = new JButton("View Most Sold Item");
        btnMostSold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewMostSoldItem();
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        displayPane = new JTextPane();
        displayPane.setEditable(false);
        centerText(displayPane);

        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblPrice);
        panel.add(txtPrice);
        panel.add(lblQty);
        panel.add(txtQty);
        panel.add(lblOrderQty);
        panel.add(txtOrderQty);
        panel.add(empty1);
        panel.add(empty2);
        panel.add(btnAdd);
        panel.add(btnRemove);
        panel.add(btnSearch);
        panel.add(btnUpdate);
        panel.add(btnView);
        panel.add(btnOrder);
        panel.add(btnMostSold);
        panel.add(btnReset);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayPane), BorderLayout.CENTER);
    }

    private void addItem() {
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        Item item = new Item(id, name, price, qty);
        inventory.addItem(item);
        appendToPane(displayPane, "Item added: " + item + "\n");

        clearFields();
    }

    private void removeItem() {
        int id = Integer.parseInt(txtId.getText());

        Item item = inventory.searchItem(id);
        if (item != null) {
            inventory.removeItem(id);
            appendToPane(displayPane, "Item removed: " + id + "\n");
        } else {
            appendToPane(displayPane, "Unavailable item: " + id + "\n");
        }

        clearFields();
    }

    private void searchItem() {
        int id = Integer.parseInt(txtId.getText());
        Item item = inventory.searchItem(id);
        if (item != null) {
            appendToPane(displayPane, "Item found: " + item + "\n");
        } else {
            appendToPane(displayPane, "Item not found: " + id + "\n");
        }

        clearFields();
    }

    private void updateItem() {
        int id = Integer.parseInt(txtId.getText());
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());

        Item item = inventory.searchItem(id);
        if (item != null) {
            inventory.updateItem(id, price, qty);
            appendToPane(displayPane, "Item updated: " + id + "\n");
        } else {
            appendToPane(displayPane, "Unavailable item: " + id + "\n");
        }

        clearFields();
    }

    private void ViewItems() {
        List<Item> allItems = inventory.getAllItems();

        if (allItems.isEmpty()) {
            appendToPane(displayPane, "No items in the inventory.\n");
        } else {
            appendToPane(displayPane, "All items in inventory:\n");
            for (Item item : allItems) {
                appendToPane(displayPane, item + "\n");
            }
        }
    }

    private void OrderItem() {
        int id = Integer.parseInt(txtId.getText());
        int qty = Integer.parseInt(txtOrderQty.getText());
        inventory.orderItem(id, qty);
        appendToPane(displayPane, "Product ordered: ID: " + id + ", Quantity: " + qty + "\n");

        clearFields();
    }

    private void ViewMostSoldItem() {
        Item mostSold = inventory.getMostSoldItem();
        if (mostSold != null) {
            appendToPane(displayPane, "Most sold item: " + mostSold + "\n");
        } else {
            appendToPane(displayPane, "No items sold yet.\n ");
        }

        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        txtOrderQty.setText("");
    }

    private void reset() {
        clearFields();
        displayPane.setText("");
    }

    private void centerText(JTextPane textPane) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    private void appendToPane(JTextPane textPane, String msg) {
        StyledDocument doc = textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), msg, null);
            centerText(textPane);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InventoryManagementSystem frame = new InventoryManagementSystem();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
