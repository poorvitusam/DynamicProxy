package genericCheckpointing.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.List;

import genericCheckpointing.util.MyLogger.DebugLevel;

public class FileProcessor {
	public BufferedReader bufferedReader;
	public BufferedWriter bufferedWriter;
	
	public FileProcessor() {}
	
	/**
	 * Object creation for read write from file operation
	 * @param inputFile
	 * @param outputFile
	 */
	public FileProcessor(String file, String mode, String name) {
		MyLogger.writeMessage("Contructor of FileProcessor", MyLogger.DebugLevel.CONSTRUCTOR);
		try {
			if (mode.equals("write")) {
				File opFile = new File(file);
				if(opFile.exists()) {
					opFile.delete();
				}
				FileWriter fileWriter = new FileWriter(file, true);
				bufferedWriter = new BufferedWriter(fileWriter);
				
			}
			FileReader fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

//			File ipFile = new File(file);
//			if(!ipFile.exists() && ipFile.length() > 0) {
//				System.err.println("Input file invalid");
//				System.exit(0);
//			}
			
			
		} catch (Exception e) {
			System.out.println("Exception caught");
			e.printStackTrace();
		}
	}
	
	
	
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}

	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}

	/**
	 * Close files which were opened
	 * @param mode if read or write
	 */
	public void closeFile(String mode) {
		try {
			if(mode.equals("write")) {
				bufferedWriter.close();
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println("Exception caught");
			e.printStackTrace();
		}
	}
	
	/**
	 * Read each line from file
	 * @return String after reading
	 */
	public String readLine() {
		try {
			String nextLine = bufferedReader.readLine();
			return nextLine;
		} catch (Exception e) {
			System.out.println("Exception caught");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Write line by line in file
	 * @param s
	 */
	public void writeLine(String s) {
		try {
			bufferedWriter.write(s);
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}