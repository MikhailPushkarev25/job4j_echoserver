package LSP.storeProducts.controll;

import LSP.storeProducts.model.Food;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import LSP.storeProducts.model.Milk;
import LSP.storeProducts.prods.Storage;
import LSP.storeProducts.storage.Shop;
import LSP.storeProducts.storage.Trash;
import LSP.storeProducts.storage.Warehouse;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;



public class ControllQualityTest {

    @Test
    public void whenStorageProductsToMin25() {
        Food milk = new Milk("Milk", LocalDate.of(2022, 7, 2),
                LocalDate.of(2022, 5, 25), 100, 0);
        List<Storage> list = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllQuality controllQuality = new ControllQuality(list);
        controllQuality.resort(milk);
        assertThat(warehouse.get(), is(List.of(milk)));
        assertThat(shop.get(), is(List.of()));
        assertThat(trash.get(), is(List.of()));

    }

    @Test
    public void whenStorageProducts() {
        Food milk = new Milk("Milk", LocalDate.of(2022, 5, 2),
                LocalDate.of(2022, 11, 25), 50, 0);
        List<Storage> list = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        list.add(warehouse);
        list.add(shop);
        list.add(trash);
        ControllQuality controllQuality = new ControllQuality(list);
        controllQuality.resort(milk);
        assertThat(trash.get(), is(List.of(milk)));

    }
}