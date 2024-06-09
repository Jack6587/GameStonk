package unisa.dse.a2.students;

import java.util.PriorityQueue;

public class StockBroker {

	/**
	 * List of pending trades to be completed. Must store a generic type.
	 */
	private PriorityQueue<Trade> pendingTrades = new PriorityQueue<Trade>();
	
	/**
	 * List of stocks this stock broker is "watching"
	 */
	private DSEListGeneric<String> watchList = new DSEListGeneric<String>();

	/**
	 * returns a DEEP copy of the watchlist. Changes to the list returned from here
	 * should NOT change the list stored by this broker
	 * @return
	 */
	public DSEListGeneric<String> getWatchlist() {
		return new DSEListGeneric<String>(watchList); // returns a new list as a copy of the current list
	}
	
	/**
	 * Adds the company code to the watchlist if it's not null and not already in there
	 * @param companyCode
	 * @return true if added
	 */
	public boolean addWatchlist(String companyCode) {
		if(companyCode != null || !watchList.contains(companyCode)) { // if company code exists and does not already belong to the watchlist
			return watchList.add(companyCode); // adds company code to the watchlist
		}
		return false; // return false otherwise
	}
	
	private String name;

	/**
	 * Name of the stock brokerage firm
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Should store the broker's name and ensure the broker is setup ready to use
	 * @param name
	 */
	public StockBroker(String name) {
		this.name = name; // initialise name of broker
	}
	
	/**
	 * Adds the Trade to the pendingTrades list if it's not null and not already in there
	 * @param companyCode
	 * @return true if added
	 */
	public boolean placeOrder(Trade order) {
		if(order != null && !pendingTrades.contains(order)) { // if order is not null and does not belong to the list of pending trades
			return pendingTrades.add(order); // adds order to pending trades
		}
		return false; // returns false otherwise
	}
	
	/**
	 * Gets, removes, and returns the next trade to process
	 * @return Trade to process
	 */
	public Trade getNextTrade() {
		return pendingTrades.poll(); // remove and return the head of the queue
	}
	
	/**
	 * @return Number of pending trades
	 */
	public int getPendingTradeCount() {
		return pendingTrades.size(); // return the size of the pending trades queue
	}

	/**
	 * Do not modify this equals, it is used for testing purposes
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockBroker other = (StockBroker) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
