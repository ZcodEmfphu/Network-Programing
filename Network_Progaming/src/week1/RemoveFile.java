//package week1;
//
//import java.io.File;
//
//public class RemoveFile {
//
//	public void sremoveFile(String path) { // remove file in folder but don't remove directory
//		File file = new File(path);
//		if (file.isFile()) {
//			System.out.println("Deleted file: " + file.getName());
//			file.delete();
//		}
//		if (file.isDirectory()) {
//			File[] list = file.listFiles();
//			for (File f : list) {
//				if (f.isFile()) {
//					System.out.println("Deleted file: " + f.getName() + " in " + f.getParent());
//					f.delete();
//				} else {
//					removeFile(f.getAbsolutePath());
//				}
//			}
//		}
//	}
//
//	public void remove(String path) {
//		File file = new File(path);
//		if (file.isDirectory()) {
//			File[] list = file.listFiles();
//			for (File f : list) {
//			for (File f : list) {
//			for (File f : list) {
//				if (f.isFile()) {
//					System.out.println("Delete file: " + f.getName());
//					f.delete();
//				} else {
//					System.out.println("Delete Dir: " + f.getName());
//					remove(f.getAbsolutePath());
//				}
//			}
//		}
//				file.delete();
//				return;
//	}
//
//	public static void main(String[] args) {
//		RemoveFile r = new RemoveFile();
//		String path = "D:\\Root\\Trash\\Temp\\Test";
////		r.removeFile(path);
//		r.remove(path);
////		System.out.println(r.remove(path));
//	}
//
//}
