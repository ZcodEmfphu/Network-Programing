package week7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) throws IOException {
    // Tạo ServerSocket để lắng nghe kết nối trên cổng 2000
    ServerSocket serverSocket = new ServerSocket(2000);
    System.out.println("Server Connected !"); // Thông báo rằng server đã kết nối thành công

    // Vòng lặp vô hạn để chấp nhận kết nối từ client
    while (true) {
      // Chấp nhận kết nối từ một client và tạo đối tượng Socket để tương tác với client
      Socket socket = serverSocket.accept();

      // Tạo một đối tượng ServerThread để xử lý kết nối từ client
      ServerThread serverThread = new ServerThread(socket);

      // Khởi động luồng để bắt đầu xử lý kết nối
      serverThread.start();
    }
  }

}
