package week6;

public class Student {
	private int stt;
	private int id;
	private String name;
	private int year;
	private double grade;

	public Student(int stt, int id, String name, int year, double grade) {
		super();
		this.stt = stt;
		this.id = id;
		this.name = name;
		this.year = year;
		this.grade = grade;
	}

	public Student(int id, String name, int year) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;

	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [stt=" + stt + ", id=" + id + ", name=" + name + ", year=" + year + ", grade=" + grade + "]";
	}

}
