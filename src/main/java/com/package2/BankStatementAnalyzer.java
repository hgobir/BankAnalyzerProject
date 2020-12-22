package com.package2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.package1.BankTransaction;

public class BankStatementAnalyzer {

	private static final String RESOURCES = "src/main/resources/";
	//using interface to decouple
	
    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }
    

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transactions is "
                + bankStatementProcessor.calculateTotalAmount());
        
        System.out.println("The minimum for all transactions is "
                + bankStatementProcessor.calculateMinimumAmount());
        
        System.out.println("The maximum for all transactions is "
                + bankStatementProcessor.calculateMaximumAmount() );

        System.out.println("The total for transactions in January is "
                + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));

        System.out.println("The total for transactions in February is "
                + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received is "
                + bankStatementProcessor.calculateTotalForCategory("Salary"));
        
        System.out.println("Histogram grouped by month is " + "\n"
                + bankStatementProcessor.calculateHistogramForMonthOrDescription("Month"));
        
        System.out.println("Histogram grouped by description is " + "\n"
                + bankStatementProcessor.calculateHistogramForMonthOrDescription("Description"));
    }

}
