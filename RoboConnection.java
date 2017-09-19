//Kevin Glenn

import java.net.*;
import java.io.*;

public class RoboConnection {
	public String command;
	public Socket socket;
	public PrintWriter out;

	RoboConnection() {
		try {
			socket = new Socket("lear.cs.okstate.edu", 9095);
			out = new PrintWriter(socket.getOutputStream());
			System.out.println("Successful");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void instruct(String command) {
		System.out.println(command);

		if(socket != null && out != null) {
			out.print(command);
			out.flush();
		}
		else {
			System.out.println("ERROR");
		}
	}
}