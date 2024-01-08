package week7;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Client {

    public static void main(String[] args) {
      try {
        DataOutputStream netOut;
        DataInputStream netIn;

        // Kết nối tới server qua Socket
        Socket socket = new Socket("127.0.0.1", 2000);
        netOut = new DataOutputStream(socket.getOutputStream());
        netIn = new DataInputStream(socket.getInputStream());

        // Đọc đường dẫn của tập tin nguồn và đích từ người dùng
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        String line = userIn.readLine();
        String source, dest;

        // Phân tách thông tin từ dòng đầu vào của người dùng
        StringTokenizer st = new StringTokenizer(line);
        if (st.hasMoreTokens()) {
          st.nextToken(); // Bỏ qua từ khóa đầu tiên (chẳng hạn "SEND")
        }

        if (st.hasMoreTokens()) {
          source = st.nextToken();
        } else {
          System.out.println("Thiếu thông tin về tập tin nguồn.");
          socket.close();
          return;
        }

        if (st.hasMoreTokens()) {
          dest = st.nextToken();
        } else {
          System.out.println("Thiếu thông tin về tập tin đích.");
          socket.close();
          return;
        }

        // Gửi đường dẫn của tập tin đích đến server
        netOut.writeUTF(dest);
        netOut.flush();

        // Đọc mã lỗi từ server để xác nhận việc chuẩn bị nhận dữ liệu
        int errCode = netIn.readInt();

        // Nếu mã lỗi khác 0, đóng kết nối và thoát khỏi chương trình
        if (errCode != 0) {
          System.out.println("Server báo lỗi. Đóng kết nối.");
          socket.close();
          return;
        }

        // Mở luồng để đọc dữ liệu từ tập tin nguồn
        try (InputStream fis = new BufferedInputStream(new FileInputStream(source))) {
          byte[] buff = new byte[1024];
          int res;

          // Đọc và gửi dữ liệu từ tập tin đến server
          while ((res = fis.read(buff)) != -1) {
            netOut.write(buff, 0, res);
            netOut.flush();
          }
        }

        // Đóng luồng và kết nối sau khi gửi xong
        socket.close();
      } catch (UnknownHostException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

}


