package com.yodist.yourktm.util;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvUtil {
	
	public static List<String[]> parsePerLine(Reader reader) throws Exception {
	    List<String[]> list = new ArrayList<>();
	    CSVReader csvReader = new CSVReader(reader);
	    String[] line;
	    while ((line = csvReader.readNext()) != null) {
	        list.add(line);
	    }
	    reader.close();
	    csvReader.close();
	    return list;
	}
	
}
