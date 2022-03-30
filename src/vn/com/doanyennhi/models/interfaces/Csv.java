package vn.com.doanyennhi.models.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * <b> Csv </b> interface contains methods to interact with CSV file.
 */
public interface Csv {

  List<String[]> readCsv(String path);

  void writeCsv(String path, List<String[]> enrolmentDataList);

}
