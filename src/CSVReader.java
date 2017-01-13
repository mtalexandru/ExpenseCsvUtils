/**
 * Created by malexandru on 1/13/2017.
 */

import java.io.*;
import java.util.*;

public class CSVReader {

	private static final String path = "/Users/malexandru/";
	private Set<String> walletColumns = new HashSet<>();
//		0		1		2			3		4				5		6			7				 8	   9
	//account;category;currency;amount;ref_currency_amount;type;payment_type;payment_type_local;note;date;gps_latitude;gps_longitude;gps_accuracy_in_meters;warranty_in_month;transfer

	// 9, 1, 3, 8 -- correct order of columns from wallet to buxfer

	public static void main(String[] args) {

		String walletCsvExportedFile = path + "cucu.csv";



		String line = "";
		String walletCsvSplitter = ";";
		File buxferMauCsvFile = new File(path + "buxferMau" + (new Date().getTime()) + ".csv");
		File buxferAdaCsvFile = new File(path + "buxferAda" + (new Date().getTime()) + ".csv");
		try (BufferedReader br = new BufferedReader(new FileReader(walletCsvExportedFile));
				FileWriter buxferMauCsvFileWriter = new FileWriter(buxferMauCsvFile);
				BufferedWriter buxferMauCsvBufferredWriter = new BufferedWriter(buxferMauCsvFileWriter);
			 FileWriter buxferAdaCsvFileWriter = new FileWriter(buxferAdaCsvFile);
			 BufferedWriter buxferAdaCsvBufferredWriter = new BufferedWriter(buxferAdaCsvFileWriter)
		) {



//			String header = br.readLine();
//			String[] columns = header.split(walletCsvSplitter);
//			Map<Integer,String> columnsMap = new HashMap<>();
//			System.out.println(columns[9] + ", " +
//					columns[1] + ", " +
//					columns[3] + ", " +
//					columns[8] + ", " );

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] lineSplit = line.split(walletCsvSplitter);
				String csvRow = lineSplit[9] + ", " + lineSplit[1] + ", " + lineSplit[3] + ", " + lineSplit[8] + "\n"; //TODO: use StringBuffer

				if(lineSplit[0].equalsIgnoreCase("MAU")){
					buxferMauCsvBufferredWriter.write(csvRow);
				}
				else {
					buxferAdaCsvBufferredWriter.write(csvRow);
				}

				System.out.println(lineSplit[9] + ", " + lineSplit[1] + ", " + lineSplit[3] + ", " + lineSplit[8]  );

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean contains(String key) {
		return walletColumns.contains(key);
	}

	public boolean containsAnyOf(String... keys){
		for (String key: keys){
			return contains(key);
		}
		return false;
	}

}
