package vn.com.doanyennhi.models.interfaces;

import java.util.List;

public interface Csv {
  // TODO: add comment
  List<String[]> readCsv(String path);

  void writeCsv();

}
