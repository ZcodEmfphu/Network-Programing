package week1;

import java.io.File;

public class FileBranch {

	public boolean fileBranch(String path) {
		File file = new File(path);
//		System.out.println("Dir: " + file.getName() + ", " + file.length());
		if (file.isFile()) {
			System.out.println("File: " + file.getName() + ", " + file.length());
			return true;
		}
		if (file.isDirectory()) {
			System.out.println("Dir: " + file.getName() + ", " + file.length());
			File[] list = file.listFiles();
			for (File f : list) {
				if (f.isFile()) {
					System.out.println("File: " + f.getName() + ", " + f.length());
				} else {
					System.out.println("Dir: " + f.getName() + ", " + f.length());
					fileBranch(f.getAbsolutePath());
				}
			}

		}

		return true;
	}

	public static void main(String[] args) {
		FileBranch fb = new FileBranch();
		String path = "D:\\Root\\Trash\\Temp\\Test";
		fb.fileBranch(path);

	}

}
