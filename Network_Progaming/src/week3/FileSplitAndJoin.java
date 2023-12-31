package week3;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSplitAndJoin {


  public static String makeDestFileName(String source, int order) {
    String ext;
    if (order < 10) {
      ext = ".00" + order;
    } else if (order < 100) {
      ext = ".0" + order;
    } else {
      ext = "." + order;
    }
    return source + ext;
  }


  public static boolean transfer(InputStream is, OutputStream os, int pSize) throws IOException {
    // Khởi tạo mảng buffer với kích thước 100 KB
    byte[] buff = new byte[1024 * 100];
    // Số lượng dữ liệu còn lại cần chuyển
    int remain = pSize;
    // Số lượng byte đọc được từ InputStream trong mỗi lần đọc
    int readByte;
    // Số lượng byte yêu cầu đọc trong mỗi lần đọc
    int reqByte;

    // Lặp cho đến khi tất cả dữ liệu cần chuyển đã được xử lý
    while (remain > 0) {
      // Xác định số lượng byte yêu cầu đọc trong lần đọc hiện tại
      if (remain < buff.length) {
        // Nếu dữ liệu còn lại ít hơn kích thước của buffer, đọc hết dữ liệu còn lại
        reqByte = remain;
      } else {
        // Nếu dữ liệu còn lại lớn hơn hoặc bằng kích thước của buffer, đọc một lượng dữ liệu bằng kích thước của buffer
        reqByte = buff.length;
      }

      // Đọc dữ liệu từ InputStream vào buffer
      readByte = is.read(buff, 0, reqByte);

      // Kiểm tra nếu gặp sự cố trong quá trình đọc (reached end of stream - trả về -1)
      if (readByte == -1) {
        // Trả về false để báo hiệu sự cố
        return false;
      }

      // Ghi dữ liệu từ buffer vào OutputStream
      os.write(buff, 0, reqByte);

      // Cập nhật số lượng dữ liệu còn lại để xử lý
      remain -= readByte;
    }

    // Trả về true khi quá trình chuyển dữ liệu hoàn thành thành công
    return true;
  }


  public static void split(String path, int pSize) throws IOException {
    String dest;
    int order = 0;
    InputStream is;
    OutputStream os;
    is = new FileInputStream(path);
    boolean res = false;

    do {
      dest = makeDestFileName(path, order);
      os = new FileOutputStream(dest);
      res = transfer(is, os, pSize);
      os.close();
    } while (res);
    is.close();

  }
  public static void main(String[] args) throws IOException {
    String path = "D:\\Trash\\Temp\\Test\\The_Datawarehouse_Toolkit_2ndEd.pdf";
    int pSize = 1500000;
    long bt = System.currentTimeMillis();
    split(path, pSize);
    long et = System.currentTimeMillis();
    System.out.println("Spliting in: " + (et-bt) + " ms");
  }
}
