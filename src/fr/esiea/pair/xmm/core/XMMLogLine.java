package fr.esiea.pair.xmm.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMMLogLine {
	
	public enum OperationType {
		READ, WRITE
	}
	
	private static final Pattern lineRegex = Pattern.compile("Instrumented (write|read)[ ]+on ins ([A-Z0-9]+) ([a-z2]+) (.*)");
	
	private static final int OPERATION_TYPE = 1;
	private static final int ADDRESS 		= 2;
	private static final int INSTRUCTION	= 3;
	private static final int ARGUMENTS		= 4;
	
	
	private final Matcher lineMatcher;
	
	public XMMLogLine(String line) {
		
		this.lineMatcher = lineRegex.matcher(line);
		
		if(!this.lineMatcher.matches())
			throw new IllegalArgumentException("Invalid line : '"+ line +"'");
	}
	
	public OperationType getOperationType() {

		return OperationType.valueOf(
				this.lineMatcher.group(OPERATION_TYPE).toUpperCase()
			);
	}
	
	public String getAddress() {
		return "0x" + this.lineMatcher.group(ADDRESS);
	}
	
	public XMMInstruction getInstruction() {
		return new XMMInstruction(
				this.lineMatcher.group(INSTRUCTION), 
				this.lineMatcher.group(ARGUMENTS)
			);
	}
	
	public String toString() { 
	
		return String.format("Type: %s, Addr: %s, Inst: %s", getOperationType(), getAddress(), getInstruction());
	}
}
