package com.sachin.MatchTrade;

public class Order
{
    public enum Type
    {
        BUY, SELL
    }

    private String id;
    private Type type;
    private double price;
    private int quantity;
    private String tickerSymbol; // Added ticker symbol to orders

    public Order(String id, Type type, double price, int quantity, String tickerSymbol)
    {
        this.id = id;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.tickerSymbol = tickerSymbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return type + " order: " + quantity + " @ " + price + " for " + tickerSymbol;
    }
}
