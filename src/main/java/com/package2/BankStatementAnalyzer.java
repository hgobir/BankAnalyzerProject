package com.package2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

import com.package1.BankTransaction;

public class BankStatementAnalyzer {

	private static final String RESOURCES = "src/main/resources/";
	//using interface to decouple
	
    public BankStatementProcessor analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
        
        return bankStatementProcessor;
    }
    
    
    public static double getTotalAmount(final BankStatementProcessor bankStatementProcessor) {
    	return bankStatementProcessor.calculateTotalAmount();
    }
    
    public static double getMinumumAmount(final BankStatementProcessor bankStatementProcessor) {
    	return bankStatementProcessor.calculateMinimumAmount();
    }
    
    public static double getMaximumAmount(final BankStatementProcessor bankStatementProcessor) {
    	return bankStatementProcessor.calculateMaximumAmount();
    }
    
    public static double getTotalInMonth(final BankStatementProcessor bankStatementProcessor, final Month month) {
    	return bankStatementProcessor.calculateTotalInMonth(month);
    }
    
    public static double getTotalForCategory(final BankStatementProcessor bankStatementProcessor, final String category) {
    	return bankStatementProcessor.calculateTotalForCategory(category);
    }
    
    public static String getHistogramForMonthOrDescription(final BankStatementProcessor bankStatementProcessor, final String monthOrDescription) {
    	return bankStatementProcessor.calculateHistogramForMonthOrDescription(monthOrDescription);
    }
    
 
    

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transactions is "
                + getTotalAmount(bankStatementProcessor));
        
        System.out.println("The minimum for all transactions is "
                + getMinumumAmount(bankStatementProcessor));
        
        System.out.println("The maximum for all transactions is "
                + getMaximumAmount(bankStatementProcessor));

        System.out.println("The total for transactions in January is "
                + getTotalInMonth(bankStatementProcessor, Month.JANUARY));

        System.out.println("The total for transactions in February is "
                + getTotalInMonth(bankStatementProcessor, Month.FEBRUARY));

        System.out.println("The total salary received is "
                + getTotalForCategory(bankStatementProcessor, "Salary"));
        
        System.out.println("Histogram grouped by month is " + "\n"
                + getHistogramForMonthOrDescription(bankStatementProcessor, "Month"));
        
        System.out.println("Histogram grouped by description is " + "\n"
                + getHistogramForMonthOrDescription(bankStatementProcessor, "Description"));
    }

}
