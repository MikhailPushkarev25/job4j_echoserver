package LSP.storeProducts.model;

import java.time.LocalDate;

public class Milk extends Food {

    public Milk(String name, LocalDate expiredDate, LocalDate createDate, int price, int discount) {
        super(name, expiredDate, createDate, price, discount);
    }
}
