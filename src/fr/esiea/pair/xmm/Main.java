package fr.esiea.pair.xmm;

import java.io.IOException;

import fr.esiea.pair.xmm.core.RegisterCounter;
import fr.esiea.pair.xmm.core.XMMLogFileAnalyzer;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		if(args.length<1) {
			System.out.println("Arg is missing !");
			return;
		}
		
		XMMLogFileAnalyzer analyzer = new XMMLogFileAnalyzer();
		
		for(RegisterCounter counter : analyzer.analyzeRegisters(args[0]))
			System.out.println(counter);

	}

}
