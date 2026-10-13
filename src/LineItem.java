
public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getUnitPrice() * quantity;
    }

    public String toString() {
        return String.format("%-20s %5d  $%8.2f  $%8.2f",
                product.getName(), quantity,
                product.getUnitPrice(), getTotal());
    }
}
