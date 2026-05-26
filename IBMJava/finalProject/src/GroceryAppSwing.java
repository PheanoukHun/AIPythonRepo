import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GroceryAppSwing extends JFrame {
    private InventorySystem inventory;
    private GroceryList cart;

    private JList<String> inventoryList;
    private JTextField quantityField;
    private JTextArea cartArea;
    private JCheckBox vetCheck, seniorCheck;

    public GroceryAppSwing() {
        super("Online Grocery Store");

        inventory = new InventorySystem("data.csv");
        cart = new GroceryList();

        initComponents();
        layoutComponents();
        addEventHandlers();

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        inventoryList = new JList<>(inventory.getNames());
        quantityField = new JTextField("1", 5);
        cartArea = new JTextArea(10, 30);
        cartArea.setEditable(false);

        vetCheck = new JCheckBox("Veteran Discount");
        seniorCheck = new JCheckBox("Senior Discount");
    }

    private void layoutComponents() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JScrollPane(inventoryList), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Quantity:"));
        controlPanel.add(quantityField);
        JButton addButton = new JButton("Add to Cart");
        controlPanel.add(addButton);

        topPanel.add(controlPanel, BorderLayout.SOUTH);

        JButton viewCartBtn = new JButton("View Cart");
        JButton avgBtn = new JButton("Show Avg Price");

        JPanel discountPanel = new JPanel();
        discountPanel.add(vetCheck);
        discountPanel.add(seniorCheck);
        JButton applyDiscountBtn = new JButton("Apply Discounts");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(viewCartBtn);
        bottomPanel.add(avgBtn);
        bottomPanel.add(applyDiscountBtn);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(cartArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Save buttons to use in handlers
        addButton.addActionListener(e -> handleAddToCart());
        viewCartBtn.addActionListener(e -> cartArea.setText(cart.toString()));
        avgBtn.addActionListener(e -> {
            float avg = cart.averagePrice();
            JOptionPane.showMessageDialog(this, "Average Price per Item: $" + String.format("%.2f", avg));
        });
        applyDiscountBtn.addActionListener(e -> {
            cart.addingDiscount(vetCheck.isSelected(), seniorCheck.isSelected());
            JOptionPane.showMessageDialog(this, "Discounts applied.");
        });
    }

    private void addEventHandlers() {
        // Already handled inline in layoutComponents()
    }

    private void handleAddToCart() {
        String selectedItem = inventoryList.getSelectedValue();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select an item.");
            return;
        }

        int index = inventoryList.getSelectedIndex();
        int available = inventory.getAmounts()[index];
        int qty;
        try {
            qty = Integer.parseInt(quantityField.getText());
            if (qty < 1 || qty > available) {
                JOptionPane.showMessageDialog(this, "Please enter a quantity between 1 and " + available);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity.");
            return;
        }

        cart.addingItem(selectedItem, qty, inventory.getPrices()[index]);
        inventory.updateInventory(index, -qty);
        JOptionPane.showMessageDialog(this, selectedItem + " added to cart.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GroceryAppSwing());
    }
}
