package ru.lesruss.Class;

public class Price {
    private String Name;
    private String Description;
    private String Cost;
    private String Page;

    public Price(String name, String description, String cost, String page) {
        Name = name;
        Description = description;
        Cost = cost;
        Page = page;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String page) {
        Page = page;
    }
}
