package ru.lesruss.Class;

import java.util.Objects;

public class ChartClass {
    private Price price;
    private int Count;


    public ChartClass(Price price, int count) {
        this.price = price;
        Count = count;

    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartClass that = (ChartClass) o;
        return Count == that.Count &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, Count);
    }

    @Override
    public String toString() {
        return "ChartClass{" +
                "price=" + price +
                ", Count=" + Count +
                '}';
    }
}
