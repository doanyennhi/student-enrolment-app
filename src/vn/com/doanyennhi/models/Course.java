package vn.com.doanyennhi.models;

import java.util.Objects;

public class Course {
  private String cId;
  private String name;
  private int credits;

  public Course(String cId, String name, int credits) {
    this.cId = cId;
    this.name = name;
    this.credits = credits;
  }

  String getcId() {
    return cId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Course course = (Course) o;
    return Objects.equals(cId, course.cId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cId, name, credits);
  }
}
