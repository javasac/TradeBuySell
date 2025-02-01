package com.sachin.MatchTrade;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class OrderBook
{
    private Map<String, PriorityQueue<Order>> buyOrdersMap;
    private Map<String, PriorityQueue<Order>> sellOrdersMap;

    public OrderBook()
    {
        buyOrdersMap = new HashMap<>();
        sellOrdersMap = new HashMap<>();
    }

    // add a Buy or Sell Order
    public synchronized void addOrder(Order order)
    {
        if (order.getType() == Order.Type.BUY)
        {
            buyOrdersMap.computeIfAbsent(order.getTickerSymbol(), k -> new PriorityQueue<>((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()))).add(order);
        }
        else
        {
            sellOrdersMap.computeIfAbsent(order.getTickerSymbol(), k -> new PriorityQueue<>((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()))).add(order);
        }
        //matchOrders(order.getTickerSymbol());
    }

    private void printTicker(PriorityQueue<Order> pq)
    {
        Iterator<Order> it = pq.iterator();
        while (it.hasNext())
        {
            System.out.println(it.next());
        }
    }

    public synchronized void matchOrders(String tickerSymbol)
    {
        PriorityQueue<Order> buyOrders = buyOrdersMap.get(tickerSymbol);
        PriorityQueue<Order> sellOrders = sellOrdersMap.get(tickerSymbol);

        this.printTicker(buyOrders);
        this.printTicker(sellOrders);

        if (buyOrders == null || sellOrders == null)
        {
            return; // No orders to match
        }

        Order buyOrder = null;
        Order sellOrder = null;

        while (!buyOrders.isEmpty() && !sellOrders.isEmpty())
        {
            buyOrder = buyOrders.peek();
            sellOrder = sellOrders.peek();

            if (buyOrder.getPrice() >= sellOrder.getPrice())
            {
                int tradeQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                double tradePrice = sellOrder.getPrice();

                System.out.println("Trade executed: " + tradeQuantity + " units of " + tickerSymbol + " at " + tradePrice);

                buyOrder.reduceQuantity(tradeQuantity);
                sellOrder.reduceQuantity(tradeQuantity);

                if (buyOrder.getQuantity() == 0)
                {
                    buyOrders.poll();
                }
                if (sellOrder.getQuantity() == 0)
                {
                    sellOrders.poll();
                }
            }
            else
            {
                break; // No more matching possible
            }
        }
    }

    public void printOrderBook(String tickerSymbol)
    {
        System.out.println("Order Book for " + tickerSymbol + ":");
        System.out.println("Buy Orders:");

        PriorityQueue<Order> buyOrders = buyOrdersMap.get(tickerSymbol);

        if (buyOrders != null)
        {
            buyOrders.forEach(order -> System.out.println(order));
        }

        System.out.println("Sell Orders:");
        PriorityQueue<Order> sellOrders = sellOrdersMap.get(tickerSymbol);

        if (sellOrders != null)
        {
            sellOrders.forEach(order -> System.out.println(order));
        }
    }
}
