package LSP.storeProducts.model;

import java.time.LocalDate;
import java.util.Objects;

public class Food {

    private String name;
    private LocalDate expiredDate;
    private LocalDate createDate;
    private int price;
    private int discount;

    public Food() {
    }

    public Food(String name, LocalDate expiredDate, LocalDate createDate, int price, int discount) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", expiredDate=" + expiredDate +
                ", createDate=" + createDate +
                ", price=" + price +
                ", discount=" + discount +
                "}";
    }
}
