package LSP.storeProducts.storage;

import LSP.storeProducts.model.Food;
import LSP.storeProducts.prods.Storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {

    private List<Food> foods = new ArrayList<>();


    @Override
    public List<Food> get() {
        return foods;
    }

    @Override
    public void save(Food food, long count) {
        if (count >= 25 && count <= 75) {
            foods.add(food);
        }
    }
}
