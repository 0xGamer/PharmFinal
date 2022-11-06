package info;

public class Med {
    public int cost;
    public String name, uses, company, quantity;

    public Med(String name, String company, String quantity, String uses, int cost) {
        this.uses = uses;
        this.name = name;
        this.company = company;
        this.cost = cost;
        this.quantity = quantity;
    }
}