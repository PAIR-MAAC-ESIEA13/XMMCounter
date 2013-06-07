package fr.esiea.pair.xmm.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class XMMLogFileReader {

	private final BufferedReader logFileReader;
	
	public XMMLogFileReader(String fileName) throws FileNotFoundException {
	
		this.logFileReader = new BufferedReader(new FileReader(fileName));
	}
	
	public XMMLogLine readLine() throws IOException {
		
		String newLine = this.logFileReader.readLine();
		
		if(newLine == null) 
			return null;
		
		return new XMMLogLine(newLine);
	}
	
}
