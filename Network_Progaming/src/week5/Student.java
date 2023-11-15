package week5;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Student {
	private int id;
	private String name;
	private int bYear;
	private double grade;

	public Student(int id, String name, int bYear, double grade) {
		this.id = id;
		this.name = name;
		this.bYear = bYear;
		this.grade = grade;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

	// write name with lenx2 byte
	public void writeFixedLengthName(DataOutput dos, int len) throws IOException {
		char c;
		for (int i = 0; i < len; i++) {
			c = (i < name.length()) ? name.charAt(i) : 0;
			dos.writeChar(c);
		}

	}

	private String readFixedLengthName(DataInput dis, int len) throws IOException {
		String res = "";
		char c;
		for (int i = 0; i < len; i++) {
			c = dis.readChar();
			if (c != 0)
				res += c;

		}
		return res;
	}

	public void writeStudentL(DataOutput dos, int len) throws IOException {
		dos.writeInt(id);
		writeFixedLengthName(dos, len);
		dos.writeInt(bYear);
		dos.writeDouble(grade);
	}

	public void readStudent(DataInput dis, int len) throws IOException {
		id = dis.readInt();
		name = readFixedLengthName(dis, len);
		bYear = dis.readInt();
		grade = dis.readDouble();
	}

	@Override
	public String toString() {
		return "id:" + id + ", name:" + name + ", bYear:" + bYear + ", grade:" + grade;
	}

	public int getId() {
		return id;
	}

	public static void main(String[] args) {
		// Example usage for Student class
		Student student = new Student(1, "John", 2000, 90.5);

		System.out.println("Original Student:");
		System.out.println(student);

	}
}
