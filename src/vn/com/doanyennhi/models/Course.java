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

  public String getCId() {
    return cId;
  }

  public String getName() {
    return name;
  }

  public int getCredits() {
    return credits;
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
