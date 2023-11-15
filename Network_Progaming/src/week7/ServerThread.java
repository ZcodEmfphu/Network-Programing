package week7;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	Socket socket;
	DataInputStream netIn;
	DataOutputStream netOut;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		netOut = new DataOutputStream(socket.getOutputStream());
		netIn = new DataInputStream(socket.getInputStream());
	}

	public void run() {
		try {
			String destFile = netIn.readUTF();
			int errCode = 0; // ok
			try {
				OutputStream fos = new BufferedOutputStream(new FileOutputStream(destFile));

				netOut.writeInt(errCode);
				netOut.flush();

				byte[] buff = new byte[1024];
				int res;
				while ((res = netIn.read(buff)) != -1)
					fos.write(buff, 0, res);

			} catch (FileNotFoundException e) { // err
				errCode = 1;
				netOut.writeInt(errCode);
				netOut.flush();
				socket.close();
				return;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
