

import java.util.ArrayList;

public class Invoice {
    private String title;
    private String customerAddress;
    private ArrayList<LineItem> items;

    public Invoice(String title, String customerAddress) {
        this.title = title;
        this.customerAddress = customerAddress;
        items = new ArrayList<>();
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public double getTotalDue() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(title).append("\n\n");
        result.append("Customer Address:\n");
        result.append(customerAddress).append("\n\n");
        result.append("Product              Qty   Unit Price     Total\n");
        result.append("-----------------------------------------------------\n");

        for (LineItem item : items) {
            result.append(item).append("\n");
        }

        result.append("-----------------------------------------------------\n");
        result.append(String.format("TOTAL AMOUNT DUE: $%.2f", getTotalDue()));

        return result.toString();
    }
}
