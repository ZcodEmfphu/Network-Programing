package week1;

import java.io.File;
import java.io.IOException;

public class FileOperation {

  public static boolean remove(String path) {
    File file = new File(path);// Khai báo file

    if (!file.exists()) {// Kiểm tra file có tồn tại hay không
      System.out.println("File hoặc thư mục " + file.getName() + " không tồn tại trong thư mục");
      return false;
    } else {
      if (file.isFile()) {// Nếu là File
        if (file.delete()) {
          System.out.println("File " + file.getName() + " đã được xóa khỏi thư mục");
          return true;
        } else {
          System.out.println("Không thể xóa file " + file.getName());
          return false;
        }
      } else if (file.isDirectory()) {// Nếu là Thư mục
        File[] list = file.listFiles();
        if (list != null) {
          for (File f : list) {
            remove(f.getAbsolutePath());
          }
        }
        if (file.delete()) {
          System.out.println("Thư mục " + file.getName() + " và nội dung của nó đã được xóa");
          return true;
        } else {
          System.out.println("Không thể xóa thư mục " + file.getName());
          return false;
        }
      } else {
        System.out.println(file.getName() + " không phải là file hoặc thư mục");
        return false;
      }
    }
  }

  public static void main(String[] args) {
    String path = "D:\\Trash\\Temp\\Test";
    FileOperation.remove(path);

  }


}


