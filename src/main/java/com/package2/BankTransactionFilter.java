package com.package2;

import com.package1.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
	
	boolean test(BankTransaction bankTransaction);

}
