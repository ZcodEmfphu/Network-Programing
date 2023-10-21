package week2;

import java.io.File;

//1. Hiển thị cấu trúc cây của folder
//2. Thêm dung lượng của folder
//  + Mỗi cấp sẽ lưu vào 3 kí tự
//  + Thư mục hiển thi trước, in hoa
//  + File hiển thị sau, chữ thường

public class DirectoryTree {

	public static void dirTree(String path) {
		File dir = new File(path);
		if (!dir.exists())
			return;
		int level = 0;
		if (dir.isFile())
			printFile(dir, level);
		if (dir.isDirectory())
			dirTreeHelper(dir, level);
	}

	private static long dirTreeHelper(File dir, int level) {
		long total = 0;

		File[] list = dir.listFiles();
		for (File f : list) {
			if (f.isDirectory()) {
				total += dirTreeHelper(f, level + 1);
			}
		}
		for (File f : list) {
			if (f.isFile()) {
				total += printFile(f, level + 1);
			}
		}
		printDir(dir, level, total);
		return total;
	}

	private static void printDir(File dir, int level, long cap) {
		StringBuilder sb = getIndext(level);
		sb.append(dir.getName().toUpperCase());
		sb.append("|");
		sb.append(cap);
		System.out.println(sb.toString());
	}

	private static long printFile(File dir, int level) {
//		Sting buffer sẽ chậm hơn String builder, chỉ sử dụng trong quá trình nhiều luồng

		StringBuilder sb = getIndext(level);
		sb.append(dir.getName().toLowerCase());
		System.out.println(sb.toString());
		return dir.length();
	}

	public static StringBuilder getIndext(int level) {
		StringBuilder sb = new StringBuilder();
		if (level == 0) {
			sb.append("+-");
		} else {
			sb.append(" ");
			for (int i = 1; i < level; i++) {
				sb.append("| ");
			}
			sb.append("+-");
		}
		return sb;
	}

	public static void main(String[] args) {
		String path = "D:\\Trash\\Temp\\Test";
		dirTree(path);
	}
}
