package week3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Chia file thành các file thành phần
// Mọi file thành phần có kích thước partSize byte
// Tên file thành phần = path + ".001", ".002", ...

public class IOClass {

	static public void split(String path, int pSize) throws Exception {
		String dest;
		int order = 0;
		InputStream is;
		OutputStream os;
		is = new FileInputStream(path);
		boolean res = false;
		do {
			order++;
			dest = makeDestFileName(path, order);
			os = new FileOutputStream(dest);
			res = stranfer(is, os, pSize);
			os.close();
		} while (res);
		is.close();
	}

	static private String makeDestFileName(String source, int order) {
		String ext;
		if (order < 10)
			ext = ".00" + order;
		else if (order < 100)
			ext = ".0" + order;
		else
			ext = "." + order;
		return source + ext;
	}

	static private boolean stranfer(InputStream is, OutputStream os, int pSize) throws IOException {
		byte[] buff = new byte[100 * 1024];
		int remain = pSize;
		int readByte;
		int reqByte;
		while (remain > 0) {
			reqByte = (remain < buff.length) ? remain : buff.length;
			readByte = is.read(buff, 0, reqByte);
			if (readByte == -1) {
				return false;
			}
			os.write(buff, 0, readByte);
			remain -= readByte;

		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		String path = "D:\\Trash\\Temp\\Test\\thu.docx";
		int size = 1500000;
		long bt = System.currentTimeMillis();
		split(path, size);
		long et = System.currentTimeMillis();
		System.out.println(et - bt);

	}
}
