package com.package3;

import com.package1.SummaryStatistics;

public class JsonExporter implements Exporter {

	@Override
	public String export(SummaryStatistics summaryStatistics) {
		String result = "\"summaryStatistics\":{";
		result += "\"sum\": "+summaryStatistics.getSum() + ",";
		result += "\"average\": "+summaryStatistics.getAverage() + ",";
		result += "\"max\": "+summaryStatistics.getMax() + ",";
		result += "\"min\": "+summaryStatistics.getMin() + ",";
		result += "}";
		return result;
	}

}
