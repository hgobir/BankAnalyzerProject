package com.package2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.package1.BankTransaction;

public class BankStatementProcessorTest {
	
//	private List<BankTransaction> testTransactions = new ArrayList<BankTransaction>();
	private BankStatementProcessor statementProcessor = null;

	@Before
	public void setUp() {
		
		List<BankTransaction> testTransactions = new ArrayList<BankTransaction>();
		LocalDate date1 = LocalDate.of(2020, 1, 8);
		final BankTransaction transaction1 = new BankTransaction(date1, 1000d, "Tescos");
		testTransactions.add(transaction1);
		LocalDate date2 = LocalDate.of(2020, 1, 6);
		final BankTransaction transaction2 = new BankTransaction(date2, 1000d, "Tescos");
		testTransactions.add(transaction2);		
		LocalDate date3 = LocalDate.of(2020, 1, 7);
		final BankTransaction transaction3 = new BankTransaction(date3, 1000d, "Tescos");
		testTransactions.add(transaction3);
		LocalDate date4 = LocalDate.of(2020, 3, 8);
		final BankTransaction transaction4 = new BankTransaction(date4, 1000d, "Morrisons");
		testTransactions.add(transaction4);
		LocalDate date5 = LocalDate.of(2020, 3, 13);
		final BankTransaction transaction5 = new BankTransaction(date5, 1000d, "Morrisons");
		testTransactions.add(transaction5);
		LocalDate date6 = LocalDate.of(2020, 5, 20);
		final BankTransaction transaction6 = new BankTransaction(date6, 1000d, "Asda");
		testTransactions.add(transaction6);
		LocalDate date7 = LocalDate.of(2020, 10, 30);
		final BankTransaction transaction7 = new BankTransaction(date7, 1000d, "Asda");
		testTransactions.add(transaction7);
		LocalDate date8 = LocalDate.of(2020, 12, 2);
		final BankTransaction transaction8 = new BankTransaction(date8, 1000d, "Asda");
		testTransactions.add(transaction8);
		LocalDate date9 = LocalDate.of(2020, 12, 3);
		final BankTransaction transaction9 = new BankTransaction(date9, 1000d, "Asda");
		testTransactions.add(transaction9);
		LocalDate date10 = LocalDate.of(2020, 12, 19);
		final BankTransaction transaction10 = new BankTransaction(date10, 1000d, "Aldi");
		testTransactions.add(transaction10);
		LocalDate date11 = LocalDate.of(2020, 12, 25);
		final BankTransaction transaction11 = new BankTransaction(date11, 1000d, "Sainsburys");
		testTransactions.add(transaction11);
		
		statementProcessor = new BankStatementProcessor(testTransactions);
	}
	
	
	@Test
	public void shouldDisplayHistogramGroupedByMonth() {
		System.out.println(statementProcessor.calculateHistogramForMonthOrDescription("Description") );
		System.out.println(System.lineSeparator());
		System.out.println(statementProcessor.calculateHistogramForMonthOrDescription("Month") );

	}

}
