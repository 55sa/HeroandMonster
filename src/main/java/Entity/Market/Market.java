package Entity.Market;

import Entity.Items.Item;

import java.util.List;

// Market class
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

    public void addItem(Item item){
        products.add(item);
    }

    public void deleteItem(Item item){
        for(int i=0;i<products.size();i++){
            if(item.getName().equals(products.get(i).getName())){
                products.remove(i);
            }
        }
    }
}
