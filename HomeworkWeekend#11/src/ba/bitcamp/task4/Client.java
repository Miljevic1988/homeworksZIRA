package ba.bitcamp.task4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *This class represents the client.
 *
 * @author Zeljko Miljevic
 */
public class Client {
	
	public static final int PORT = 2424;
	public static final String HOST = "localhost";

	public static void main(String[] args) {
		try {
			Socket client = new Socket(HOST, PORT);
		
			BufferedWriter write = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			write.write("PUT");
			write.newLine();
			write.flush();
			
			OutputStream os = client.getOutputStream();
			FileInputStream fileStream = new FileInputStream(new File("image.jpg"));
	
			byte[] array = new byte[1024];
			int readBytes;
			
			while ((readBytes = fileStream.read(array, 0, 1024)) > 0) {
				os.write(array, 0, readBytes);
			}
			
			fileStream.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
