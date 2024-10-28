package Repository;

import Entity.Items.Item;
import Entity.Items.Type;

import java.util.HashMap;

// Factory interface for items
public interface ItemFactory {
    Item createItem(String name, int price, int level, Type type, HashMap<String, Object> info);
}

