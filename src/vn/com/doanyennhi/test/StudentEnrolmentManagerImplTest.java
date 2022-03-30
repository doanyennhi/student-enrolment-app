package vn.com.doanyennhi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import vn.com.doanyennhi.models.StudentEnrolment;
import vn.com.doanyennhi.models.StudentEnrolmentManagerImpl;
import vn.com.doanyennhi.models.interfaces.Csv;
import vn.com.doanyennhi.processing.CsvImpl;
import vn.com.doanyennhi.processing.DataProcessor;

class StudentEnrolmentManagerImplTest {
  List<StudentEnrolment> enrolmentList;
  StudentEnrolmentManagerImpl manager;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    Csv csv = new CsvImpl();
    List<String[]> enrolmentData = csv.readCsv("csv/default.csv");
    DataProcessor processor = new DataProcessor();
    enrolmentList = processor.convertCsvToEnrolment(enrolmentData);
    manager = new StudentEnrolmentManagerImpl(enrolmentList);
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
  void getOne_shouldReturnEnrolmentWithSpecifiedValueOrNull() {
    StudentEnrolment enrolment = manager.getOne("S103723", "BUS2232", "2020B");
    assertTrue(
        enrolment.getStudent().getSId().equals("S103723")
            && enrolment.getCourse().getCId().equals("BUS2232")
            && enrolment.getSemester().equals("2020B"));

    StudentEnrolment anotherEnrolment = manager.getOne("S101312", "PHYS1230", "2020C");
    assertNull(anotherEnrolment);

  }

  @org.junit.jupiter.api.Test
  void getAll() {
    List<StudentEnrolment> enrolments = manager.getAll();
    assertEquals(enrolmentList, enrolments);
  }
}