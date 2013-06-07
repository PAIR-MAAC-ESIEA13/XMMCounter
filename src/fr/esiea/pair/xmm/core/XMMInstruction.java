package fr.esiea.pair.xmm.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMMInstruction {
	
	//(xmm((word ptr \\[e[ds]i(\\+0x[0-9A-F]{2})?\\])|([0-9]))) //Old
	private static final Pattern argsRegex = Pattern.compile("(xmm([0-9]))");
	
	public static final int SOURCE_ARG 	 = 1;
	public static final int DESTINATION_ARG = 0;
	
	private final String instruction;
	
	private final String[] arguments;
	
	public XMMInstruction(String inst, String args) {
	
		this.instruction = inst;
	
		this.arguments = args.split(", ");
	}
	
	public String getInstruction() {
		return this.instruction;
	}
	
	public String getSource() {
		return this.arguments[SOURCE_ARG];
	}
	
	public String getDestination() {
		return this.arguments[DESTINATION_ARG];
	}
	
	public boolean isRegister(int arg) {
		Matcher argMatcher = argsRegex.matcher(this.arguments[arg]);
		
		argMatcher.matches();
		
		return argMatcher.matches();
	}
	
	public String toString() {
		return String.format("%s %s, %s", this.instruction, this.arguments[0], this.arguments[1]);
	}
}
