package com;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.time.Month;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.package2.BankStatementAnalyzer;
import com.package2.BankStatementCSVParser;
import com.package2.BankStatementParser;
import com.package2.BankStatementProcessor;

public class MainApplication {
	
	// add basic gui
	public MainApplication(BankStatementProcessor bankStatementProcessor) {
		
		
        double totalAmount = BankStatementAnalyzer.getTotalAmount(bankStatementProcessor);
        double minumumAmount = BankStatementAnalyzer.getMinumumAmount(bankStatementProcessor);
        double maximumAmount = BankStatementAnalyzer.getTotalAmount(bankStatementProcessor);
        double januaryTotal = BankStatementAnalyzer.getTotalInMonth(bankStatementProcessor, Month.JANUARY);
        double februaryTotal = BankStatementAnalyzer.getTotalInMonth(bankStatementProcessor, Month.FEBRUARY);
        double salaryTotal = BankStatementAnalyzer.getTotalForCategory(bankStatementProcessor, "Salary");
        String monthHistogram = BankStatementAnalyzer.getHistogramForMonthOrDescription(bankStatementProcessor, "Month");
        String descriptionHistogram = BankStatementAnalyzer.getHistogramForMonthOrDescription(bankStatementProcessor, "Description");
		
		
	
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		JLabel totalTransactions = new JLabel("The total for all transactions is: " + totalAmount);
		JLabel minumumTransactions = new JLabel("The minumum for all transactions is: " + minumumAmount);
		JLabel maximumTransactions = new JLabel("The maximum for all transactions is: " + maximumAmount);
		JLabel januaryTransactions = new JLabel("The total for transactions in january is: " + januaryTotal);
		JLabel februaryTransactions = new JLabel("The total for transactions in february is: " + februaryTotal);
		JLabel salaryTransactions = new JLabel("The total for transactions with salary category is: " + salaryTotal);
		
		JLabel histogram1= new JLabel("histogram for month is: " + monthHistogram);
		JLabel histogram2= new JLabel("histogram for description is: " + descriptionHistogram);

//		JLabel salaryTransactions = new JLabel("The total for transactions with salary category is: " + salaryTotal);
		
		
		panel.setBorder(BorderFactory.createEmptyBorder(240, 240, 80, 240));
		panel.setLayout(new GridLayout(0, 1));
		
		panel.add(totalTransactions);
		panel.add(minumumTransactions);
		panel.add(maximumTransactions);
		panel.add(januaryTransactions);
		panel.add(februaryTransactions);
		panel.add(salaryTransactions);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Bank Transaction Report GUI");
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        BankStatementProcessor bankStatementProcessor = bankStatementAnalyzer.analyze(args[0], bankStatementParser);
       
		new MainApplication(bankStatementProcessor);

	}

}
