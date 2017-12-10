package genericCheckpointing.store;

package genericCheckpointing.store;

import java.util.ArrayList;
import java.util.List;

import genericCheckpointing.util.FileDisplayInterface;
import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.FileProcessor.Permission;
import genericCheckpointing.util.MyLogger;
import genericCheckpointing.util.MyLogger.DebugLevel;
import genericCheckpointing.util.StdoutDisplayInterface;

/**
 * Class to store all results in string format and process those results to write in
 * a file or print on console
 * @author suresh
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	List<String> testResults = new ArrayList<String>();;
	String outputFilePath;

	public Results(String outputFilePath) {
		MyLogger.writeMessage("Results Parameterized Constructor is called", DebugLevel.CONSTRUCTOR);
		this.outputFilePath = outputFilePath;
	}


	/**
	 * Store new result
	 * @param resultString
	 */
	public void storeNewResult(String resultString) {
		MyLogger.writeMessage(resultString, DebugLevel.DEBUG);
		testResults.add(resultString);
	}

	
	
	@Override
	public void writeToFile() {
		FileProcessor fileProcessor = new FileProcessor(outputFilePath, Permission.WRITE, true);
		fileProcessor.writeLines(testResults);
		fileProcessor.closeFile();
		
		MyLogger.writeMessage("Result is generated at path = " + fileProcessor.getFilePath(), DebugLevel.VERBOSE);
	}



	@Override
	public void writeToStdout() {
		for (String line : testResults) {
			System.out.println(line);
		}
	}
}


