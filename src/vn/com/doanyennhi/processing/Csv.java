package vn.com.doanyennhi.processing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <b> Csv </b> class is used to perform operations with CSV files.
 */
public class Csv {

  /**
   * method to read data from provided CSV file
   * @param path of CSV file we want to read
   * @return list of string arrays containing the data of each line in the file
   */
  public static List<String[]> readCsv(String path) {
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
    }
    return dataList;
  }

  public static void writeCsv() {

  }
}
