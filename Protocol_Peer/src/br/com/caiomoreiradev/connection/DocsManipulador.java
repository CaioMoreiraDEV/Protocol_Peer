package br.com.caiomoreiradev.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DocsManipulador {
	
	public static void readFile(String path) {
		BufferedReader fileRead;
        String line = "";
        
		try {
			fileRead = new BufferedReader(new FileReader(path));

	        while (true) {
	        	line = fileRead.readLine();
	            if (line != null) {
	                System.out.println(line);
	            } else {
	            	break;
	            }
	        }
	        fileRead.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error reading file from servers - "+e);
		} catch (IOException e) {
			System.out.println("Error reading file from servers - "+e);
		}
	}
	
	public static void writeFile(String path, String line) {
		BufferedWriter fileWrite;
		BufferedReader fileRead;
		ArrayList<String> file = new ArrayList<>();
		String lineRead = "";
		
		try {
			fileRead = new BufferedReader(new FileReader(path));	
			while (true) {
				lineRead = fileRead.readLine();
	            if (lineRead != null) {
	            	file.add(lineRead);
	            } else {
	            	break;
	            }
	        }
			fileRead.close();
		
			fileWrite = new BufferedWriter(new FileWriter(path));
			for (String l : file) {
				fileWrite.append(l);
				fileWrite.newLine();
			}
			fileWrite.append(line);
			fileWrite.newLine();
			fileWrite.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error writing file from servers - "+e);
		} catch (IOException e) {
			System.out.println("Error writing file from servers - "+e);
		}
	}
	
	public void removeLine(String path, String line) {
		BufferedWriter fileWrite;
		BufferedReader fileRead;
		ArrayList<String> file = new ArrayList<>();
		String lineRead = "";
		try {
			fileRead = new BufferedReader(new FileReader(path));	
			while (true) {
				lineRead = fileRead.readLine();
	            if (lineRead != null) {
	            	file.add(lineRead.trim());
	            } else {
	            	break;
	            }
	        }
			fileRead.close();
		
			fileWrite = new BufferedWriter(new FileWriter(path));
			for (String l : file) {
				if(l.equals(line)) continue;
				fileWrite.append(l);
				fileWrite.newLine();
			}
			fileWrite.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error writing file from servers - "+e);
		} catch (IOException e) {
			System.out.println("Error writing file from servers - "+e);
		}
	}
}
