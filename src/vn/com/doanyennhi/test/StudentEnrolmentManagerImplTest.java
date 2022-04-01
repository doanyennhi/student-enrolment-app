package vn.com.doanyennhi.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import vn.com.doanyennhi.models.StudentEnrolment;
import vn.com.doanyennhi.models.StudentEnrolmentManagerImpl;
import vn.com.doanyennhi.models.interfaces.Csv;
import vn.com.doanyennhi.processing.CsvImpl;
import vn.com.doanyennhi.processing.DataProcessor;


/**
 * <b> StudentEnrolmentManagerImplTest </b> is used to write unit testing for the 5
 * CRUD methods
 */
class StudentEnrolmentManagerImplTest {
  List<StudentEnrolment> enrolmentList;
  StudentEnrolmentManagerImpl manager;
  int initialSize;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    Csv csv = new CsvImpl();
    List<String[]> enrolmentData = csv.readCsv("csv/default.csv");
    DataProcessor processor = new DataProcessor();
    // initial enrolment list
    enrolmentList = processor.convertCsvToEnrolment(enrolmentData);
    initialSize = enrolmentList.size();     // initial size of list
    manager = new StudentEnrolmentManagerImpl(enrolmentList);
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    enrolmentList = new ArrayList<>();
  }

  @org.junit.jupiter.api.Test
  void add() {
    // second enrolment already existed so only 1 enrolment should be added
    assertTrue(manager.add("S102732", "COSC3321", "2020C"));
    assertFalse(manager.add("S103817", "COSC4030", "2020C"));
    assertEquals(initialSize + 1, manager.getAll().size());
  }

  @org.junit.jupiter.api.Test
  void update() {
    // as option 1 is delete, check if size of list decrease by 1
    manager.update("S101153", "COSC3321", "2021A", "1");
    assertEquals(initialSize - 1, manager.getAll().size());

    // since we just delete and then add an enrolment, the size of current enrolment list
    // should be equal to its initial size
    manager.update("S103821", "COSC4030", "2021A", "2");
    assertEquals(initialSize, manager.getAll().size());

  }

  @org.junit.jupiter.api.Test
  void delete() {
    // second enrolment doesn't exist so only 1 enrolment should be deleted
    assertTrue(manager.delete("S101163", "COSC3321", "2021A"));
    assertFalse(manager.delete("S103192", "PHYS1230", "2020B"));
    assertEquals(initialSize - 1, manager.getAll().size());
  }

  @org.junit.jupiter.api.Test
  void getOne_shouldReturnEnrolmentWithSpecifiedValueOrNull() {
    StudentEnrolment enrolment = manager.getOne("S103723", "BUS2232", "2020B");
    assertTrue(
        enrolment.getStudent().getSId().equals("S103723")
            && enrolment.getCourse().getCId().equals("BUS2232")
            && enrolment.getSemester().equals("2020B"));

    // expected to be null as enrolment doesn't exist
    StudentEnrolment anotherEnrolment = manager.getOne("S101312", "PHYS1230", "2020C");
    assertNull(anotherEnrolment);

  }

  @org.junit.jupiter.api.Test
  void getAll() {
    List<StudentEnrolment> enrolments = manager.getAll();
    assertEquals(enrolmentList.size(), enrolments.size());
  }
}