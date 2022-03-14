package util;

public class Product {

    private String type;

    private String name;

    private int quantity;

    public Product(String type, String name, int quantity){
        this.type = type;
        this.name = name;
        this.quantity = quantity;
    }


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }


}
