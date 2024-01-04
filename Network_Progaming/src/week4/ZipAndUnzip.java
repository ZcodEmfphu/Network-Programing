package week4;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ZipAndUnzip {

  public static List<File> getRegularFile(String path) {
    List<File> regFile = new ArrayList<File>();
    File file = new File(path);
    File[] list = file.listFiles();
    for (File f : list) {
      if (f.isFile()) {
        regFile.add(f);
        System.out.println(f.getName() + " đã được thêm!");
      }
    }
    return regFile;
  }

  public static void packDir(String path, String pack) throws IOException {
    List<File> regFile = getRegularFile(path);
    RandomAccessFile raf = new RandomAccessFile(pack, "rw");
    long[] FEPos = new long[regFile.size()];
    raf.writeInt(regFile.size());

    int index = 0;
    long pos = 0;

    for (File f : regFile) {
      FEPos[index++] = raf.getFilePointer();
      raf.writeLong(pos);
      raf.writeLong(f.length());
      raf.writeUTF(f.getName());
      pos += f.length();
    }
    index = 0;
    for (File f : regFile) {
      pos = raf.getFilePointer();
      raf.seek(FEPos[index++]);
      raf.writeLong(pos);
      raf.seek(pos);

      byte[] buff = new byte[1024 * 1000];
      int readByte;
      FileInputStream fis = new FileInputStream(f);
      while ((readByte = fis.read(buff)) != -1) {
        raf.write(buff, 0, readByte);
      }
      fis.close();
    }
    raf.close();
    System.out.println("Đóng gói thành công!");
    File packedFile = new File(pack);
    long packedFileSize = packedFile.length();
    System.out.println("Kích thước của thư mục đóng gói: " + packedFileSize + " bytes");
  }

  public static void packFile(String path, String pack) throws IOException {
    RandomAccessFile raf = new RandomAccessFile(pack, "rw");
    File file = new File(path);
    raf.writeLong(0);
    raf.writeLong(file.length());
    raf.writeUTF(file.getName());
    byte[] buff = new byte[1024 * 1000];
    int readByte;
    FileInputStream fis = new FileInputStream(file);
    while ((readByte = fis.read(buff)) != -1) {
      raf.write(buff, 0, readByte);
    }
    fis.close();
    raf.close();
  }

  public static void unpack(String pack, String extractFile, String dest)
      throws IOException {
    RandomAccessFile raf = new RandomAccessFile(pack, "r");
    long pos;
    long size;
    String name;
    int fileNo = raf.readInt();
    for (int i = 0; i < fileNo; i++) {
      pos = raf.readLong();
      size = raf.readLong();
      name = raf.readUTF();
      if (name.equalsIgnoreCase(extractFile)) {
        FileOutputStream fos = new FileOutputStream(dest);
        raf.seek(pos);
        for (long k = 0; k < size; k++) {
          fos.write(raf.read());
        }
        fos.close();
        break;
      }
    }
    raf.close();
  }


  public static void main(String[] args) throws IOException {
    String path = "D:\\Trash\\Temp\\Test";
    String pack = "D:\\Trash\\Temp\\Test.zip";
//    getRegularFile(path);
//    packDir(path, pack);

    String extractFile = "Test";
    String dest = "D:\\Trash\\Temp\\test_cop.pdf";

    unpack(pack, extractFile, dest);
  }

}
