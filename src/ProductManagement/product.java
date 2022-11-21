package ProductManagement;

import java.util.Scanner;

public class product {


    String productName;
    int quantity;
    int price;
    String description;

    public product(){
        productName = "";
        quantity = 0;
        price = 0;
        description = "";
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void createProduct(){
        Scanner ProductScanner = new Scanner(System.in);
        System.out.println("Enter price price of the product:");
        int price = ProductScanner.nextInt();
        System.out.println("Enter Quantity of the product:");
        int quantity  = ProductScanner.nextInt();
        this.price = price;
        this.quantity = quantity;
    }
    public void  checkProduct(){
        Scanner ProductScanner = new Scanner(System.in);
        System.out.println("Enter name of the product:");
        String name = ProductScanner.nextLine();
        name = name.trim();
        this.productName = name;
        System.out.println("Enter description of the product:");
        String description = ProductScanner.nextLine();
        this.description = description;
    }
}
