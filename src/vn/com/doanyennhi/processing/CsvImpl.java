package vn.com.doanyennhi.processing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vn.com.doanyennhi.models.interfaces.Csv;

/**
 * <b> CsvImpl </b> class is used to implement operations with CSV files.
 */
public class CsvImpl implements Csv {

  /**
   * method to read data from provided CSV file
   * @param path of CSV file we want to read
   * @return list of string arrays containing the data of each line in the file
   * @throws IOException exception related to file path
   */
  public List<String[]> readCsv(String path) throws IOException {
    List<String[]> dataList = new ArrayList<String[]>();

      Scanner sc = new Scanner(new File(path));

      while (sc.hasNextLine()) {
        // split string of data into array, add the array of data to dataList
        // each string array in dataList now contains data of each line in csv file
        dataList.add(sc.nextLine().split(","));
      }
    return dataList;
  }

  public void writeCsv(String path, List<String[]> enrolmentDataList) throws IOException {
    PrintWriter writer = new PrintWriter(path);

    // convert from array of strings to one string of data separated by comma
    for (String[] enrolment: enrolmentDataList) {
      StringBuilder line = new StringBuilder();

      for (int i = 0; i < enrolment.length; i++) {
        if (i == enrolment.length - 1) {
          line.append(enrolment[i]).append("\n");
        }
        line.append(enrolment[i]).append(",");
      }
      writer.write(line.toString());  // write each line of data string to CSV file
    }
    writer.close();
    System.out.printf("Report has been saved successfully to %s!", path);
  }
}
