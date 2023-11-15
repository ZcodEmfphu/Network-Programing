package week6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentManager {

	public static List<Student> loadData(String stFile, String gradeFile, String charSet)
			throws NumberFormatException, IOException {
		List<Student> list = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(stFile), charSet));
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
			double values = 0;
			while (st.hasMoreTokens()) {
				count++;
				values += Double.parseDouble(st.nextToken());
			}
			grade = values / count;
		}

		reader.close();

		return list;
	}

	public static void export(List<Student> list, String textFile, String charset)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter pw = new PrintWriter(textFile, charset);
		for (Student st : list) {
			String line = st.id + "\t" + st.name + "\t" + st.year + "\t" + st.grade;
			pw.println(line);
		}
		pw.close();

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		String stFile = "D:\\Project\\Network-Programing\\Network_Progaming\\Test\\Tuan6\\sinhvien.txt";
		String gradeFile = "D:\\Project\\Network-Programing\\Network_Progaming\\Test\\Tuan6\\diem.txt";
		String charSet = "UTF-8";

		List<Student> list = loadData(stFile, gradeFile, charSet);

//		System.out.println(list);

		String expFile = "D:\\Project\\Network-Programing\\Network_Progaming\\Test\\Tuan6\\export.txt";
		String expCharSet = "UTF-16";
		export(list, expFile, expCharSet);
	}

}
