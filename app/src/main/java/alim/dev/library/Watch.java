package alim.dev.library;

public class Watch {
    private final String brand;
    private final String price;
    private final String description;
    private final int imageResId;

    public Watch(String brand, String price, String description, int imageResId) {
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
