package vn.com.doanyennhi.models;

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
}
