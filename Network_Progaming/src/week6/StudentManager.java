package week6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentManager {

	public static List<Student> loadData(String stFile, String gradeFile, String charSet)
			throws NumberFormatException, IOException {
		List<Student> list = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(stFile)));
		String line, token;
		StringTokenizer st;
		int id;
		String name;
		int year;
		double grade;
		while ((line = reader.readLine()) != null) {
			st = new StringTokenizer(line, "\t");
			token = st.nextToken();
			id = Integer.parseInt(st.nextToken());
			name = st.nextToken();
			year = Integer.parseInt(st.nextToken());
			list.add(new Student(id, name, year));
		}
		reader.close();

		reader = new BufferedReader(new InputStreamReader(new FileInputStream(gradeFile)));
		while ((line = reader.readLine()) != null) {
			st = new StringTokenizer(line, "\t");
			id = Integer.parseInt(st.nextToken());
			int count = 0;
			double value = 0;
			while (st.hasMoreTokens()) {
				count++;
				value += Double.parseDouble(st.nextToken());
			}
		}
		reader.close();

		return list;
	}

	public static void export(List<Student> list, String textFile, String charset) {

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		String stFile = "D:\\Trash\\Test\\sinhvien.txt";
		String gradeFile = "D:\\Trash\\Test\\diem.txt";
		String charSet = "UTF-8";

		List<Student> list = loadData(stFile, gradeFile, charSet);
		System.out.println(list);
	}

}
