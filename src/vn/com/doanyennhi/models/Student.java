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

  public String getSId() {
    return sId;
  }

  public String getName() {
    return name;
  }

  public String getBirthdate() {
    return birthdate;
  }

  @Override
  public String toString() {
    return "Student: " +
        "sId='" + sId + "'" +
        ", name='" + name + "'" +
        ", birthdate='" + birthdate + "'\n";
  }

}
