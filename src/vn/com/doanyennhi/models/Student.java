package vn.com.doanyennhi.models;

import java.util.Objects;

public class Student {
  private String sId;
  private String name;
  private String birthdate;

  public Student(String sId, String name, String birthdate) {
    this.sId = sId;
    this.name = name;
    this.birthdate = birthdate;
  }

  String getsId() {
    return sId;
  }

  @Override
  public String toString() {
    return "Student{" +
        "sId='" + sId + '\'' +
        ", name='" + name + '\'' +
        ", birthdate='" + birthdate + '\n';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    // two Students are the same if they have same ID
    return Objects.equals(sId, student.sId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sId, name, birthdate);
  }
}

