package fr.esiea.pair.xmm.core;

public class RegisterCounter {

	private final String name;
	
	private int reads;
	private int writes;
	
	
	public RegisterCounter(String register) {

		this.name = register;
		
		this.reads = 0;
		
		this.writes = 0;
	}

	public void addRead() {
		this.reads++;
	}

	public void addWrite() {
		this.writes++;
	}

	
	public String toString() {
		
		return String.format("{%s [r: %d, w: %d]}", this.name, this.reads, this.writes);
		
	}
}
