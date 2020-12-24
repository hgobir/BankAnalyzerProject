package com.package3;

import com.package1.SummaryStatistics;

public class XmlExporter implements Exporter {

	@Override
	public String export(SummaryStatistics summaryStatistics) {
		
		String result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
		result += "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"";
		result +="xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
		result +="xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">";
		
		result += "<bankTransactionReport>";
		result += "<summaryStatistics>";
		result += "<sum>" + summaryStatistics.getSum() +"</sum>";
		result += "<average>" + summaryStatistics.getAverage() +"</average>";
		result += "<max>" + summaryStatistics.getMax() +"</max>";
		result += "<min>" + summaryStatistics.getMin() +"</min>";
		result += "</summaryStatistics>";
		result += "</bankTransactionReport>";
		result += "</project>";
		return result;
	}

}
