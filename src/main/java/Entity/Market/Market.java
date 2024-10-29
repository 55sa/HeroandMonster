package Entity.Market;

import Entity.Items.Item;

import java.util.List;

public class Market {
    private List<Item> products;

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    public Market(List<Item> products) {
        this.products = products;
    }
}
