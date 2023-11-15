package week6;

public class Student {
	int stt;
	int id;
	String name;
	int year;
	double grade;

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

	public Student(int id, String name, int year, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.grade = grade;

	}

	public Student(int id, double grade) {
		super();
		this.id = id;
		this.grade = grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [ id=" + id + ", name=" + name + ", year=" + year + ", grade=" + grade + "\n" + "]";
	}

}
