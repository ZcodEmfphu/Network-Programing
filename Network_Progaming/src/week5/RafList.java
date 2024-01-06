package week5;

import java.io.*;

public class RafList {
//	static private final int NAME_LEN = 25;
//	RandomAccessFile raf;
//
//	int count = 0;
//	int recSize = 0;
//	int len;
//	long headSize = 8;
//
//	public RafList(String file) throws IOException {
//		raf = new RandomAccessFile(file, "rw");
//		if (raf.length() > 0) { // exist -> read header
//			count = raf.readInt();
//			recSize = raf.readInt();
//			len = (recSize - 16) / 2;
//		} else {// create new file
//			count = 0;
//			recSize = NAME_LEN * 2 + 16;
//			len = NAME_LEN;
//			headSize = 8;
//			raf.writeInt(count);
//			raf.writeInt(recSize);
//		}
//	}
//
//	public void add(Student st) throws IOException {
//		count++;
//		raf.seek(raf.length());
//		st.writeStudentL(raf, len);
//		raf.seek(0);
//		raf.writeInt(count);
//	}
//
//	public Student get(int index) throws IOException {
//		Student st = null;
//		if (index >= count)
//			return null;
//		long pos = headSize + index * recSize;
//		raf.seek(pos);
//		st = new Student();
//		st.readStudent(raf, len);
//		return st;
//	}
//
//	public void updateStudent(int index, Student st) throws IOException {
//		if (index >= count)
//			return;
//		long pos = headSize + index * recSize;
//		raf.seek(pos);
//		st.writeStudentL(raf, len);
//	}
//
//	public Student findById(int id) throws IOException {
//		for (int i = 0; i < count; i++) {
//			Student st = get(i);
//			if (st.getId() == id) {
//				return st;
//			}
//		}
//		return null;
//	}
//
//	public static void main(String[] args) {
//		try {
//			// Example usage for RafList class
//			RafList rafList = new RafList("students.dat");
//
//			// Adding a student to the list
//			Student student = new Student(1, "John", 2000, 90.5);
//			rafList.add(student);
//
//			// Retrieving a student by index
//			Student retrievedStudent = rafList.get(0);
//			System.out.println("Retrieved Student:");
//			System.out.println(retrievedStudent);
//
//			// Updating a student
//			Student updatedStudent = new Student(1, " John", 2001, 95.0);
//
//			rafList.updateStudent(0, updatedStudent);
//
//			// Retrieving the updated student
//			retrievedStudent = rafList.get(0);
//			System.out.println("\nUpdated Student:");
//			System.out.println(retrievedStudent);
//
//			// Finding a student by ID
//			int searchId = 1;
//			Student foundStudent = rafList.findById(searchId);
//			if (foundStudent != null) {
//				System.out.println("\nFound Student with ID " + searchId + ":");
//				System.out.println(foundStudent);
//			} else {
//				System.out.println("\nStudent with ID " + searchId + " not found.");
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
