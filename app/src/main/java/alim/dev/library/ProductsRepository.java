package alim.dev.library;

public class ProductsRepository {
    private static final Product[] products = {
            new Product("Яблоки", "Сочные и свежие яблоки.", R.drawable.apple),
            new Product("Хлеб", "Свежий домашний хлеб.", R.drawable.bread),
            new Product("Молоко", "Натуральное молоко высшего качества.", R.drawable.milk),
            new Product("Сыр", "Твердый сыр с насыщенным вкусом.", R.drawable.cheese),
            new Product("Шоколад", "Темный шоколад с 70% какао.", R.drawable.chocolate)
    };

    public static String[] getProductNames() {
        String[] names = new String[products.length];
        for (int i = 0; i < products.length; i++) {
            names[i] = products[i].getName();
        }
        return names;
    }

    public static Product getProduct(int index) {
        return products[index];
    }
}
