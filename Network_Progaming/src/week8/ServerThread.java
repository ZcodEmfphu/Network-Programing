package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread extends Thread {
	public Socket socket;
	public BufferedReader netIn;
	PrintWriter netOut;
	char operator;
	double operade1, operade2;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream());
	}

	public void run() {
		String request, response;
		try {
			while (true) {
				request = netIn.readLine();
				if (request.equalsIgnoreCase("Exit")) {
					break;
				}
				requestAnalys(request);
				double res = doRequest();
				response = request + "=" + res;
				netOut.println(response);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestAnalys(String request) {
		StringTokenizer st = new StringTokenizer(request, "+-*/");
		String str1 = st.nextToken();
		String str2 = st.nextToken();
		this.operator = request.charAt(str1.length());
		this.operade1 = Double.parseDouble(str1.trim());
		this.operade2 = Double.parseDouble(str2.trim());
	}

	public double doRequest() {
		double res = 0;
		switch (this.operator) {
		case '+':
			res = operade1 + operade2;
			break;
		case '-':
			res = operade1 - operade2;
			break;
		case '*':
			res = operade1 * operade2;
			break;
		case '/':
			res = operade1 / operade2;
			break;
		}
		return res;
	}
}
