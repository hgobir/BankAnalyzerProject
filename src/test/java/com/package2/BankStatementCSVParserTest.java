package com.package2;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.package1.BankTransaction;

public class BankStatementCSVParserTest {
	
    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "30-01-2017,-50,Tesco";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected  = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        final double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());    
    }
    
    @Test
    public void shouldParseMultipleLinesCorrectly() {        
    	final String line0 = "30-01-2017,-50,Tesco";
        final String line1 = "30-01-2018,50,Asda";
        final String line2 = "30-01-2019,-60,Sainsburys";
        final String line3 = "30-01-2020,60,Morrisons";
        
        List<String> multipleLines = new ArrayList<String>();
        
        multipleLines.add(line0);
        multipleLines.add(line1);
        multipleLines.add(line2);
        multipleLines.add(line3);
        
        List<BankTransaction> result = statementParser.parseLinesFrom(multipleLines);
        
        Assert.assertEquals(4, result.size());
    }

}
