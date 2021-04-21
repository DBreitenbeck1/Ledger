package com.ledger.Ledger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;

public class FileManager {


	private File file;
	private FileWriter writer;
	private FileReader reader;
	private PrintWriter pw;
	private BufferedReader BR;
	
	
	private Formatter x;
	
	public FileManager(String fileName) throws IOException{
		file = new File("src/resources/"+fileName+".txt");
		writer = new FileWriter(file, true);
		reader = new FileReader(file);
		BR = new BufferedReader(reader);
		pw = new PrintWriter(writer);
	}
	
	public FileManager(String fileName, boolean clear) throws IOException{
		file = new File("src/resource/"+fileName+".txt");
		writer = new FileWriter(file, clear);
		pw = new PrintWriter(writer);
	}
	

	public void writeToFile(String text) throws IOException {
		pw.println(text);
		
	}
	
	public void close() {
		pw.close();
	}
	
	public void clearFile() throws IOException {
		writer = new FileWriter(file, false);
	}
	
	
	public void createFile(String title) {
		try {
			this.file=new File ("src/resource/"+title+".txt");
			x=new Formatter(file);
			
		} catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	
	public String readFile() throws IOException {
		return BR.readLine();
	}
	
	public ArrayList<String> readToArray() throws IOException{
		ArrayList<String> L = new ArrayList<>();
		String line;
		while((line=BR.readLine())!=null) {
		L.add(line);
		}
		return L;
	}
	
	public ArrayList<String> readToPartialArray(int limit) throws IOException{
		ArrayList<String> L = new ArrayList<>();
		String line;
		for (int i = 0;i<limit;i++) {
			if((line=BR.readLine())!=null) {
				L.add(line);
			}
		}
		return L;
	}
	
	public ArrayList<String> readToNumArray() throws IOException{
		if(this.file.getName().equals("Characters.txt")) {
		return readToPartialArray(10);
		}
		else {
			System.out.println(this.file.getName());
			System.out.println("incorrect file name");
			return null;
		}
	}
	
	
	public String searchFile(String search) throws IOException {
		String line;
		String result="Not Found";
		while((line=BR.readLine())!=null) {
			if(line.equals(search)) {
				result=line;
				return result;
			} 
				
		}
		return result;
		
	}
	
	public File getFile() {
		return file;
	}
	
	
	public void writeClose() throws IOException {
		writer.close();
	}
	
}
