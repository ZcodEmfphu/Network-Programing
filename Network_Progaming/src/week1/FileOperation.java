package week1;

import java.io.File;
import java.io.IOException;

public class FileOperation {

  //	static public boolean remove(String path) {
//		File file = new File(path);
//		if (!file.exists()) return true;
//
//		File[] list = file.listFiles();
//		if (list!=null) for(File f:list) remove(f.getAbsolutePath());
//
//		return file.delete();
//	}
  public static boolean remove(String path) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      System.out.println("File " + file.getName() + "có không tồn tại trong thư mục");
      return false;
    } else {
      File[] list = file.listFiles();
      if (list != null) {
        for (File f : list) {
          remove(f.getAbsolutePath());
          System.out.println("File " + file.getName() + " đã được xóa trong thư mục");
        }
      }
    }
    return file.delete();

  }

  public static void main(String[] args) throws IOException {
    String path = "D:\\Trash\\Temp\\Test1";
    FileOperation.remove(path);

  }


}


