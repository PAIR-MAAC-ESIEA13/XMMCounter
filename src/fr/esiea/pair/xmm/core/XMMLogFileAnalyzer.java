package fr.esiea.pair.xmm.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMMLogFileAnalyzer {

	public XMMLogFileAnalyzer() {

	}

	private static RegisterCounter getCounter(Map<String, RegisterCounter> counters, String register) {

		RegisterCounter counter = counters.get(register);

		if(counter == null) {
			counter = new RegisterCounter(register);
			counters.put(register, counter);
		}

		return counter;
	}

	public Collection<RegisterCounter> analyzeRegisters(String logFileName) throws IOException {

		XMMLogFileReader logReader = new XMMLogFileReader(logFileName);

		XMMLogLine newLine = null;

		Map<String, RegisterCounter> result = new HashMap<>();

		while((newLine = logReader.readLine()) != null) {

			System.out.println("Analyzing line " + newLine);

			XMMInstruction inst = newLine.getInstruction();
			RegisterCounter counter = null;

			switch(newLine.getOperationType()) {

			case READ : 

				if(inst.isRegister(XMMInstruction.SOURCE_ARG)) {
					counter = getCounter(result, inst.getSource());

					counter.addRead();
				}

				break;

			case WRITE:

				if(inst.isRegister(XMMInstruction.DESTINATION_ARG)) {
					counter = getCounter(result, inst.getDestination());

					counter.addWrite();
				}

				break;

			}

			System.out.println(counter);
		}

		return result.values();
	}

}
