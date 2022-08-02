package LSP.storeProducts.controll;

import LSP.storeProducts.model.Food;
import LSP.storeProducts.prods.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllQuality {

    private List<Storage> storages;

    public ControllQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public long dateParse(Food food) {
        long oneDate = food.getExpiredDate().toEpochDay() - food.getCreateDate().toEpochDay();
        long twoDate = LocalDate.now().toEpochDay() - food.getCreateDate().toEpochDay();
        return twoDate / 100 * oneDate;
    }

    public void redistribute(Food food) {
        for (Storage storage : storages) {
            storage.save(food, dateParse(food));
        }
    }

    public void resort(Food food) {
        List<Food> foods = new ArrayList<>();
        for (Storage storage : storages) {
            foods.addAll(storage.get());
        }
        redistribute(food);
    }
}
