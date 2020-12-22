package com.package2;

import java.util.List;

import com.package1.BankTransaction;

public interface BankStatementParser {

    BankTransaction parseFrom(String line);
    List<BankTransaction> parseLinesFrom(List<String> lines);
    
}
