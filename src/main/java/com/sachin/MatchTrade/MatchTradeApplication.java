package com.sachin.MatchTrade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatchTradeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MatchTradeApplication.class, args);

		OrderBook orderBook = new OrderBook();

		// Adding buy and sell orders for different ticker symbols
		orderBook.addOrder(new Order("B1", Order.Type.BUY, 100.5, 10, "AAPL"));
		orderBook.addOrder(new Order("S1", Order.Type.SELL, 101.0, 5, "AAPL"));
		orderBook.addOrder(new Order("B2", Order.Type.BUY, 101.5, 8, "AAPL"));
		orderBook.addOrder(new Order("S2", Order.Type.SELL, 99.5, 3, "AAPL"));
		orderBook.addOrder(new Order("S3", Order.Type.SELL, 100.0, 7, "AAPL"));

		// Adding orders for another ticker symbol (e.g., "GOOG")
		orderBook.addOrder(new Order("B1", Order.Type.BUY, 1500.0, 5, "GOOG"));
		orderBook.addOrder(new Order("S1", Order.Type.SELL, 1510.0, 3, "GOOG"));
		orderBook.addOrder(new Order("B2", Order.Type.BUY, 1520.0, 4, "GOOG"));

		// Print the order book for AAPL
		//orderBook.printOrderBook("AAPL");

		// Print the order book for GOOG
		//orderBook.printOrderBook("GOOG");

		orderBook.matchOrders("AAPL");
		orderBook.matchOrders("GOOG");
	}
}
