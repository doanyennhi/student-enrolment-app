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
   * method to read data from provided CSV file. Exit system if there is exception
   * @param path of CSV file we want to read
   * @return list of string arrays containing the data of each line in the file
   */
  public List<String[]> readCsv(String path) {
    List<String[]> dataList = new ArrayList<String[]>();

    try {
      Scanner sc = new Scanner(new File(path));
      while (sc.hasNextLine()) {
        // split string of data into array, add the array of data to dataList
        // each string array in dataList now contains data of each line in csv file
        dataList.add(sc.nextLine().split(","));
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(0);     // close system if there is problem with file path
    }
    return dataList;
  }


  /**
   * Write data to the provided path. Exit system if there's exception
   * @param path file we want to write to
   * @param dataList list of string arrays containing data to be written
   */
  public void writeCsv(String path, List<String[]> dataList) {
    try {
      PrintWriter writer = new PrintWriter(path);

      // convert from array of strings to one string of data separated by comma
      for (String[] enrolment: dataList) {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < enrolment.length; i++) {
          if (i == enrolment.length - 1) {
            line.append(enrolment[i]).append("\n");
          } else {
            line.append(enrolment[i]).append(",");
          }
        }
        writer.write(line.toString());  // write each line of data string to CSV file
      }
      writer.close();
      System.out.printf("Report has been saved successfully to %s!", path);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(0);     // close system if there is problem with file path
    }
  }
}
