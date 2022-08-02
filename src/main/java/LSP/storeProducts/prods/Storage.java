package LSP.storeProducts.prods;

import LSP.storeProducts.model.Food;

import java.util.List;

public interface Storage {

    List<Food> get();

    void save(Food food, long count);
}
