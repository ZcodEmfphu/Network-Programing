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

	private static void dirTreeHelper(File dir, int level) {
		printDir(dir, level);
		File[] list = dir.listFiles();
		for (File f : list) {
			if (f.isDirectory()) {
				dirTreeHelper(f, level + 1);
			}
		}
		for (File f : list) {
			if (f.isFile()) {
				printFile(f, level + 1);
			}
		}

	}

	private static void printDir(File dir, int level) {
		StringBuilder sb = new StringBuilder();
		if (level == 0) {
			sb.append("+-");
		} else {
			sb.append("   ");
			for (int i = 1; i < level; i++) {
				sb.append("| ");
			}
			sb.append("+-");
		}
		sb.append(dir.getName().toUpperCase());
		System.out.println(sb.toString());
	}

	private static void printFile(File dir, int level) {
//		Sting buffer sẽ chậm hơn String builder, chỉ sử dụng trong quá trình nhiều luồng

		StringBuilder sb = new StringBuilder();
		if (level == 0) {
			sb.append("+-");
			sb.append(dir.getName().toLowerCase());
		} else {
			sb.append(" ");
			for (int i = 1; i < level; i++) {
				sb.append("| ");
			}
			sb.append("+-");
		}
		sb.append(dir.getName().toLowerCase());
		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		String path = "D:\\Trash\\Temp\\Test";
		dirTree(path);
	}
}
