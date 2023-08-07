package com.csv.merge.main;

import java.io.File;
import java.util.*;

import com.csv.merge.entity.Csvfile;
import com.csv.merge.function.CsvParser;

import java.io.IOException;

public class CsvApplication {

	public static void main(String[] args) throws IOException {
		// to take input from the user
		Scanner sc= new Scanner(System.in); 
		System.out.print("Enter location of the first file - ");  
		String a = sc.nextLine();  
		File csv1 = new File(a);
		System.out.print("Enter location of the second file - ");  
		String b = sc.nextLine();  
		File csv2 = new File(b);
		
        //getting headers from file1 and storing in a list
		List<String> csv1Headers = CsvParser.getHeadersFromACsv(csv1);
		//getting headers from file2 and storing in a list
		List<String> csv2Headers = CsvParser.getHeadersFromACsv(csv2);
		

		//storing the headers from list1 and 2 into all Csv header list
		List<String> allCsvHeaders = new ArrayList<>();
		allCsvHeaders.addAll(csv1Headers);
		allCsvHeaders.addAll(csv2Headers);
		

		Set<String> uniqueHeaders = new HashSet<>(allCsvHeaders);

		List<Csvfile> csv1Records = CsvParser.getRecodrsFromACsv(csv1, csv1Headers);
		List<Csvfile> csv2Records = CsvParser.getRecodrsFromACsv(csv2, csv2Headers);

		List<Csvfile> allCsvRecords = new ArrayList<>();
		allCsvRecords.addAll(csv1Records);
		allCsvRecords.addAll(csv2Records);

		System.out.print("Enter location of the merged file - ");  
		String c = sc.nextLine(); 
		CsvParser.writeToCsv(new File(c), uniqueHeaders, allCsvRecords);
		sc.close();
	}
}
