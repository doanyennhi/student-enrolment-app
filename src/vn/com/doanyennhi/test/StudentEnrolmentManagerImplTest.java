package vn.com.doanyennhi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import vn.com.doanyennhi.models.StudentEnrolmentManagerImpl;
import vn.com.doanyennhi.models.interfaces.Csv;
import vn.com.doanyennhi.processing.CsvImpl;
import vn.com.doanyennhi.processing.DataProcessor;

class StudentEnrolmentManagerImplTest {

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    Csv csv = new CsvImpl();
    DataProcessor processor = new DataProcessor();
    List<String[]> enrolments = csv.readCsv("csv/default.csv");
    StudentEnrolmentManagerImpl manager = new StudentEnrolmentManagerImpl(processor.convertCsvToEnrolment(enrolments));
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
  }

  @org.junit.jupiter.api.Test
  void add() {
  }

  @org.junit.jupiter.api.Test
  void update() {
  }

  @org.junit.jupiter.api.Test
  void delete() {
  }

  @org.junit.jupiter.api.Test
  void getOne() {
  }

  @org.junit.jupiter.api.Test
  void getAll() {
  }
}