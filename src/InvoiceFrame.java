
import javax.swing.*;
import java.awt.*;

public class InvoiceFrame extends JFrame {
    private JTextField titleField;
    private JTextArea addressField;
    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextArea outputArea;
    private Invoice invoice;

    public InvoiceFrame() {
        setTitle("Invoice Application");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        titleField = new JTextField("My Invoice");
        addressField = new JTextArea(3, 20);
        productField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        inputPanel.add(new JLabel("Invoice Title:"));
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Customer Address:"));
        inputPanel.add(new JScrollPane(addressField));

        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(productField);

        inputPanel.add(new JLabel("Unit Price:"));
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);

        JButton addButton = new JButton("Add Item");
        JButton displayButton = new JButton("Display Invoice");

        inputPanel.add(addButton);
        inputPanel.add(displayButton);

        JButton clearButton = new JButton("New Invoice");
        inputPanel.add(clearButton);
        inputPanel.add(new JLabel(""));

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                if (invoice == null) {
                    invoice = new Invoice(
                            titleField.getText(),
                            addressField.getText());
                }

                String name = productField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                if (name.trim().isEmpty() || price < 0 || quantity <= 0) {
                    throw new IllegalArgumentException();
                }

                Product product = new Product(name, price);
                LineItem item = new LineItem(product, quantity);
                invoice.addItem(item);

                outputArea.setText(invoice.toString());

                productField.setText("");
                priceField.setText("");
                quantityField.setText("");

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,
                        "Enter a valid product, price, and quantity.");
            }
        });

        displayButton.addActionListener(e -> {
            if (invoice == null) {
                invoice = new Invoice(
                        titleField.getText(),
                        addressField.getText());
            }
            outputArea.setText(invoice.toString());
        });

        clearButton.addActionListener(e -> {
            invoice = null;
            titleField.setText("My Invoice");
            addressField.setText("");
            productField.setText("");
            priceField.setText("");
            quantityField.setText("");
            outputArea.setText("");
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
