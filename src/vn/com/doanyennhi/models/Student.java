package vn.com.doanyennhi.models;


// implement Comparable
// override compareTo
public class Student {

  private String sId;
  private String name;
  private String birthdate;

  public Student(String sId, String name, String birthdate) {
    this.sId = sId;
    this.name = name;
    this.birthdate = birthdate;
  }

  String getSId() {
    return sId;
  }

  @Override
  public String toString() {
    return "Student: " +
        "sId='" + sId + "'" +
        ", name='" + name + "'" +
        ", birthdate='" + birthdate + "'\n";
  }

}
