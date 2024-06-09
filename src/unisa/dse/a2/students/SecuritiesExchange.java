package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String name) {
		this.name = name;
		this.brokers = new DSEListGeneric<>(); // list of brokers initialised
		this.announcements = new DSEListGeneric<>(); // list of announcements initialised
		this.companies = new HashMap<>(); // map of companies initialised
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company) {
		if(company == null || companies.containsKey(company.getCode())) { // if company is null or already belongs to the map
			return false;
		}
		companies.put(company.getCode(), company); // adds company to map using the code as the key
		return true;
	}
	


	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker) {
		if(broker != null) { // if broker is not null
			for(int i = 0; i < brokers.size(); i++) { // iterate over all brokers
				StockBroker stockbrokers = brokers.get(i); // obtain the broker at index i
				if(stockbrokers.equals(broker)) { // if current broker is the same as the broker to be added
					return false;
				}
			}
			
			return brokers.add(broker); // otherwise, add the broker and return true
		}
		return false; // returns false if broker is null
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound() throws UntradedCompanyException {
		int successfulTrades = 0; // create a counter for all successful trades (must be returned)
		for(int i = 0; i < brokers.size(); i++) { // iterate over all brokers
			StockBroker broker = brokers.get(i); // get broker at index i
			Trade nextTrade = broker.getNextTrade(); // variable to represent the next trade from queue
			
			
			if(nextTrade == null) { // if no pending trades are in the queue, go to the next broker
				continue;
			}
			
			String companyCode = nextTrade.getCompanyCode(); // get company code from trade
			ListedCompany company = companies.get(companyCode); // get the company from the map of companies
			
			if(!companies.containsKey(companyCode)){ // exception is thrown if company is not in the exchange
				throw new UntradedCompanyException("Company " + companyCode + " is not listed on the exchange.");
			}
			
			int shareQuantity = nextTrade.getShareQuantity(); // obtain share quantity of the trade
			int currentPrice = company.getCurrentPrice(); // obtain current price of the company before the trade
			String brokerName = broker.getName(); // obtain broker name
			
			company.processTrade(shareQuantity); // calls the processTrade method
			String announcementString = "Trade: " + shareQuantity + companyCode + " @ " + currentPrice + " via " + brokerName; // string representation of details of the trade
			announcements.add(announcementString); // adds the announcement to the list of announcements
			successfulTrades++; // increment the trade counter
		}
		
		return successfulTrades;
	}
	
	public int runCommandLineExchange(Scanner sc) {
		return 0;
	}
}
