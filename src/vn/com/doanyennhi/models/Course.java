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

  String getCId() {
    return cId;
  }

  @Override
  public String toString() {
    return "Course: " +
        "cId = '" + cId + '\'' +
        ", name = '" + name + '\'' +
        ", credits = " + credits +
        "\n";
  }

}
