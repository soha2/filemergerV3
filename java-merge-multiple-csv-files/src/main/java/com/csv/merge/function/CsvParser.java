package com.csv.merge.function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.csv.merge.entity.Csvfile;

public class CsvParser {
	public static List<Csvfile> getRecodrsFromACsv(File file, List<String> keys) throws IOException {
		//reading file and storing into an object and return
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<Csvfile> records = new ArrayList<>();
		boolean isHeader = true;

		String line = null;
		while ((line = br.readLine()) != null) {
			if (isHeader) {// first line is header
				isHeader = false;
				continue;
			}
			Csvfile record = new Csvfile(file.getName());
			String[] lineSplit = line.split(",");
			for (int i = 0; i < lineSplit.length; i++) {
				record.put(keys.get(i), lineSplit[i]);
			}
			records.add(record);
		}

		br.close();

		return records;
	}

	public static List<String> getHeadersFromACsv(File file) throws IOException {
		//reading the file header and returning in an object
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<String> headers = null;

		String line = null;
		while ((line = br.readLine()) != null) {
			String[] lineSplit = line.split(",");
			headers = new ArrayList<>(Arrays.asList(lineSplit));
			break;
		}

		br.close();

		return headers;
	}

	public static void writeToCsv(final File file, final Set<String> headers, final List<Csvfile> records)
			throws IOException {
		//writing in final output file from the data reciever from the records and header
		FileWriter csvWriter = new FileWriter(file);

		// write headers
		String sep = "";
		String[] headersArr = headers.toArray(new String[headers.size()]);
		for (String header : headersArr) {
			csvWriter.append(sep);
			csvWriter.append(header);
			sep = ",";
		}

		csvWriter.append("\n");

		// write records at each line
		for (Csvfile record : records) {
			sep = "";
			for (int i = 0; i < headersArr.length; i++) {
				csvWriter.append(sep);
				csvWriter.append(record.get(headersArr[i]));
				sep = ",";
			}
			csvWriter.append("\n");
		}
		
		System.out.println("Done");

		csvWriter.flush();
		csvWriter.close();
	}
}
