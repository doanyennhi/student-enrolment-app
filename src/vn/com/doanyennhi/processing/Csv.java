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
   * method to read data from CSV file provided
   * @param path of CSV file we want to read
   * @return list of arrays containing the data of each line in the file
   */
  public static List<String[]> readCsv(String path) {
    List<String[]> data = new ArrayList<String[]>();

    try {
      Scanner sc = new Scanner(new File(path));

      while (sc.hasNextLine()) {
        data.add(sc.nextLine().split(","));
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return data;
  }

  public static void writeCsv() {

  }
}
