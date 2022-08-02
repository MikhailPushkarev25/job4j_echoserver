package LSP.storeProducts.model;

import java.time.LocalDate;

public class Chicken extends Food {

    public Chicken(String name, LocalDate expiredDate, LocalDate createDate, int price, int discount) {
        super(name, expiredDate, createDate, price, discount);
    }
}
