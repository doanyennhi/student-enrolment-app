package vn.com.doanyennhi.models;

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
}
