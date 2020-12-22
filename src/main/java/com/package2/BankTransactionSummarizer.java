package com.package2;

import com.package1.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {

    double summarize(double accumulator, BankTransaction bankTransaction);
}
