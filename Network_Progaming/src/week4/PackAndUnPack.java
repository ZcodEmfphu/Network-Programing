package week4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class PackAndUnPack {

	static private List<File> getRegularFile(String folder) {
		List<File> regFile = new ArrayList<File>();
		File dir = new File(folder);
		File[] list = dir.listFiles();
		for (File f : list) {
			if (f.isFile()) {
				regFile.add(f);
			}
		}
		return regFile;
	}

	static public void pack(String folder, String packFile) throws IOException {
		List<File> regFile = getRegularFile(folder);
		RandomAccessFile raf = new RandomAccessFile(packFile, "rw");

		long[] FEPos = new long[regFile.size()];
		raf.writeInt(regFile.size());
		int index = 0;
		long pos = 0;
		long hPos = 0;
		for (File f : regFile) {
			FEPos[index++] = raf.getFilePointer();
			raf.writeLong(pos);
			raf.writeLong(f.length());
			raf.writeUTF(f.getName());
		}
		index = 0;
		for (File f : regFile) {
			pos = raf.getFilePointer();
			raf.seek(FEPos[index++]);
			raf.writeLong(pos);
			raf.seek(pos);

			byte[] buff = new byte[102400];
			int byteRead;
			FileInputStream fis = new FileInputStream(f);
			while ((byteRead = fis.read(buff)) != -1) {
				raf.write(buff, 0, byteRead);
			}
			fis.close();
		}
		raf.close();
		System.out.println("Packing Succes !");
	}

	static public void unpack(String packFile, String extractFile, String dest) throws IOException {

		RandomAccessFile raf = new RandomAccessFile(packFile, "r");
		long pos, size;
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
		System.out.println("Unpack succses !");
	}

	public static void main(String[] args) throws IOException {
		String folder = "D:\\Trash\\Test";
		String packedFile = "D:\\Trash\\Test.pack";

		pack(folder, packedFile);

		String extractFile = "D:\\Trash\\Test\\Lesson1.pdf";
		String dest = "D:\\Trash\\Test\\Lesson1_copy.pdf";

//		unpack(packedFile, extractFile, dest);
	}

}
