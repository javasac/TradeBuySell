package com.sachin.MatchTrade.service;
import com.sachin.MatchTrade.model.BondOrder;
import com.sachin.MatchTrade.model.Trade;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.PriorityQueue;

@Service
public class OrderService
{
    private PriorityQueue<BondOrder> buy = new PriorityQueue<BondOrder>(Comparator.comparing(BondOrder::getTicker));
    private PriorityQueue<BondOrder> sell = new PriorityQueue<BondOrder>(Comparator.comparing(BondOrder::getTicker));

    public boolean submitOrder(BondOrder order)
    {
        if (order.getSide() == BondOrder.Side.BUY)
        {
            buy.add(order);
            this.displayBuySell(buy);
        }
        if (order.getSide() == BondOrder.Side.SELL)
        {
            sell.add(order);
            this.displayBuySell(sell);
        }
        return true;
    }

    // method to display the Queue
    public void displayBuySell(PriorityQueue pq)
    {
        pq.forEach(order -> System.out.println(order));
    }

    private void processOrder(BondOrder order)
    {
        BondOrder buyOrder = null;
        BondOrder sellOrder = null;
        int quantity = 0;

        Trade trade = createTrade(buyOrder, sellOrder, quantity);
        System.out.println("New Trade happened: " + trade.toString());
    }

    private Trade createTrade(BondOrder buyOrder, BondOrder sellOrder, int quantity)
    {
        Trade trade = new Trade();

        trade.setId(buyOrder.getId() + "-" + sellOrder.getId());
        trade.setTicker(sellOrder.getTicker());
        trade.setPrice(sellOrder.getPrice());
        trade.setQuantity(quantity);
        trade.setBuyerOrderId(buyOrder.getId());

        return trade;
    }
}

