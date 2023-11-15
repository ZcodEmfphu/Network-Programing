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
	public static void main(String[] args) throws UnknownHostException, IOException {
		DataInputStream netIn;
		DataOutputStream netOut;
		
		Socket socket = new Socket("127.0.0.1",2000);
		netOut = new DataOutputStream(socket.getOutputStream());
		netIn = new DataInputStream(socket.getInputStream());
		
		
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String line = userIn.readLine();
		String source, dest;
		StringTokenizer st = new StringTokenizer(line);
		st.nextToken();
		source = st.nextToken();
		dest = st.nextToken();
		netOut.writeUTF(dest);
		netOut.flush();
		int errCode = netIn.readInt();
		if(errCode!=0) {// errro
			socket.close();
			return;
		}
		InputStream fis = new BufferedInputStream(new FileInputStream(source));
		byte[] buff = new byte[1024];
		int res;
		while((res = netIn.read(buff))!=-1) netOut.write(buff,0,res);
		netOut.flush();
	}

}
