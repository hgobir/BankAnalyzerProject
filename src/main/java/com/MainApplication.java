package com;

import java.io.IOException;

import com.package2.BankStatementAnalyzer;
import com.package2.BankStatementCSVParser;
import com.package2.BankStatementParser;

public class MainApplication {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);

	}

}
