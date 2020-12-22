package com.package2;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.package1.BankTransaction;

public class BankStatementProcessor {

	private final List<BankTransaction> bankTransactions;
	private final String key = " * |";

	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public double calculateMinimumAmount() {

		double runningTotal = Double.MAX_VALUE;

		for (final BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getAmount() < runningTotal) {
				runningTotal = bankTransaction.getAmount();
			}
		}
		return runningTotal;
	}

	public double calculateMaximumAmount() {

		double runningTotal = Double.MIN_VALUE;

		for (final BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getAmount() > runningTotal) {
				runningTotal = bankTransaction.getAmount();
			}
		}
		return runningTotal;
	}

	public double calculateTotalAmount() {
		double total = 0;
		for (final BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}



	public double calculateTotalForCategory(final String category) {
		double total = 0;
		for (final BankTransaction bankTransaction : bankTransactions) {
			if (bankTransaction.getDescription().equals(category)) {
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}

	public String calculateHistogramForMonthOrDescription(final String monthOrDescription) {
		Map<Month, Integer> monthsMap = createEmptyMonthMap();
		Map<String, Integer> descriptionMap = new HashMap<String, Integer>();
		String drawHistogram = "";

		if (monthOrDescription.equals("Month")) {
//			System.out.println("going down month route!");
			for (final BankTransaction bankTransaction : bankTransactions) {
				Integer monthValue = monthsMap.get(bankTransaction.getDate().getMonth());
				monthsMap.put(bankTransaction.getDate().getMonth(), ++monthValue);
			}
			drawHistogram = drawMonthHistogramString(monthsMap);
		} else {
//			System.out.println("going down description route!");
			for (final BankTransaction bankTransaction : bankTransactions) {
				if (descriptionMap.containsKey(bankTransaction.getDescription())) {
					Integer descriptionValue = descriptionMap.get(bankTransaction.getDescription());
					descriptionMap.put(bankTransaction.getDescription(), ++descriptionValue);
				} else {
					descriptionMap.put(bankTransaction.getDescription(), 1);
				}
			}
			drawHistogram = drawDescriptionHistogramString(descriptionMap);
		}
		return drawHistogram;
	}

//	instead of using different implementation selection is abstracted out to a BankTransactuibFilter interface which decouples iteration logic from business logic
//	public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
//	    final List<BankTransaction> result = new ArrayList<BankTransaction>();
//	    for(final BankTransaction bankTransaction: bankTransactions) {
//	        if(bankTransaction.getAmount() >= amount) {
//	            result.add(bankTransaction);
//	        }
//	    }
//	    return result;
//	}
//	
//	
//	public List<BankTransaction> findTransactionsInMonth(final Month month) {
//	    final List<BankTransaction> result = new ArrayList<BankTransaction>();
//	    for(final BankTransaction bankTransaction: bankTransactions) {
//	        if(bankTransaction.getDate().getMonth() == month) {
//	            result.add(bankTransaction);
//	        }
//	    }
//	    return result;
//	}
//	
//	public List<BankTransaction> findTransactionsInMonthAndGreater(final Month month, final int amount) {
//	    final List<BankTransaction> result = new ArrayList<BankTransaction>();
//	    for(final BankTransaction bankTransaction: bankTransactions) {
//	        if(bankTransaction.getDate().getMonth() == month && bankTransaction.getAmount() >= amount) {
//	            result.add(bankTransaction);
//	        }
//	    }
//	    return result;
//	}

//	new implementations can be flexibly introduced by passing subclasses of BankTransactionFilter that contain different implementations into argument using open/closed principle
	// -------------------------USING FUNCIONAL INTERFACE AND IMPLEMENTED VIA
	// LAMBDAS
	// ------------------------ LEARN HOW TO DO THIS!!!! - USE THIS FOR REPORTS
	// FUNCTIONALITY!
	public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
		final List<BankTransaction> result = new ArrayList<>();
		for (final BankTransaction bankTransaction : bankTransactions) {
			if (bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
		return bankTransactions;
	}

	// -------------------------USING FUNCIONAL INTERFACE AND IMPLEMENTED VIA
	// LAMBDAS
	public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
		double result = 0;
		for (final BankTransaction bankTransaction : bankTransactions) {
			result = bankTransactionSummarizer.summarize(result, bankTransaction);
		}
		return result;
	}
	
	// USES LAMBDAS INSTEAD
	public double calculateTotalInMonth(final Month month) {
		return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDate().getMonth() == month
				? acc + bankTransaction.getAmount()
				: acc);
	}
//    public double calculateTotalInMonth(final Month month) {
//        double total = 0;
//        for(final BankTransaction bankTransaction: bankTransactions) {
//            if(bankTransaction.getDate().getMonth() == month) {
//                total += bankTransaction.getAmount();
//            }
//        }
//        return total;
//    }
	public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
		return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
	}

	private String drawDescriptionHistogramString(Map<String, Integer> descriptionMap) {

		String histogram = "";

		for (Entry<String, Integer> entry : descriptionMap.entrySet()) {
			String description = entry.getKey().toString();
			String parsedDescription = String.format("%-10s", description);
			int number = entry.getValue();
			String numberOfKeys = "";
			for (int i = 0; i < number; i++) {
				numberOfKeys = numberOfKeys + key;
			}
			histogram = histogram + parsedDescription + "|" + numberOfKeys + System.lineSeparator();
		}
		return histogram;
	}

	private String drawMonthHistogramString(Map<Month, Integer> monthsMap) {
		String histogram = "";

		for (Entry<Month, Integer> entry : monthsMap.entrySet()) {
			String month = entry.getKey().toString();
			String parsedMonth = String.format("%-10s", month);
			int number = entry.getValue();
			String numberOfKeys = "";
			for (int i = 0; i < number; i++) {
				numberOfKeys = numberOfKeys + key;
			}
			histogram = histogram + parsedMonth + "|" + numberOfKeys + System.lineSeparator();
		}
		return histogram;
	}

	private Map<Month, Integer> createEmptyMonthMap() {

		Map<Month, Integer> initMap = new HashMap<Month, Integer>();

		initMap.put(Month.JANUARY, 0);
		initMap.put(Month.FEBRUARY, 0);
		initMap.put(Month.MARCH, 0);
		initMap.put(Month.APRIL, 0);
		initMap.put(Month.MAY, 0);
		initMap.put(Month.JUNE, 0);
		initMap.put(Month.JULY, 0);
		initMap.put(Month.AUGUST, 0);
		initMap.put(Month.SEPTEMBER, 0);
		initMap.put(Month.OCTOBER, 0);
		initMap.put(Month.NOVEMBER, 0);
		initMap.put(Month.DECEMBER, 0);

		return initMap;
	}
}
