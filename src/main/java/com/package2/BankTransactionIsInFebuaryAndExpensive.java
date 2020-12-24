package com.package2;

import java.time.Month;

import com.package1.BankTransaction;

public class BankTransactionIsInFebuaryAndExpensive implements BankTransactionFilter {

	public boolean test(BankTransaction bankTransaction) {
		return bankTransaction.getDate().getMonth() == Month.FEBRUARY && bankTransaction.getAmount() >= 1_000;	
       }

}
