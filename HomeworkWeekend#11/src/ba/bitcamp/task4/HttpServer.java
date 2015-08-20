package ba.bitcamp.task4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class represents the HTTP server.
 * 
 * @author Zeljko Miljevic
 */
public class HttpServer {

	public static final int PORT = 2424;

	public static void main(String[] args) {
		
		ArrayList<File> files = new ArrayList<File>();
		
		ServerSocket server;
		
		try {
			server = new ServerSocket(PORT);
			System.out.println("server started");

			while (true) {
				Socket client = server.accept();
	
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
				String line = "";

				try {
			
					line = reader.readLine();
				
					if (line.contains("GET")) {
						String[] get = line.split(" ");
						
						String requestRoute = get[1];
				
						if (requestRoute.equals("/") || requestRoute.equals("/favicon.ico")) {
							BufferedReader htmlReader = new BufferedReader(new FileReader(new File("index.html")));
							BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
					
							String htmlText = "";
			
							while (htmlReader.ready()) {
								htmlText += htmlReader.readLine();
							}
					
							for (File f : files) {
								htmlText += "<h3 align='center'><a href = /" + f.toString() + ">"
										+ f.getName().substring(0, 11) + "</a></h3>";
							}
						
							htmlText += "</body></html>";
						
							htmlWriter.write(htmlText);
							htmlWriter.newLine();
							htmlWriter.close();
							htmlReader.close();
						
						} else {
							String fileName = requestRoute.substring(1);
						
							OutputStream clientWriter = client.getOutputStream();
							FileInputStream clientReader = new FileInputStream(new File(fileName));
						
							byte[] buffer = new byte[1024];
							int readBytes1;
				
							while ((readBytes1 = clientReader.read(buffer, 0, buffer.length)) > 0) {
								clientWriter.write(buffer, 0, readBytes1);
							}
						
							clientWriter.close();
							clientReader.close();
						}
					} else if (line.contains("PUT")) {
						String date = Calendar.getInstance().getTime() + "";
						String time = date.split(" ")[3];
						String name = "image" + time.split(":")[0] + time.split(":")[1] + time.split(":")[2];
				
						InputStream is = client.getInputStream();
						File file = new File(name + ".jpg");
						FileOutputStream fileSave = new FileOutputStream(file);
						
						byte[] array = new byte[1024];
						int readBytes;
				
						while ((readBytes = is.read(array, 0, 1024)) > 0) {
							fileSave.write(array, 0, readBytes);
						}
				
						is.close();
						fileSave.close();
						files.add(file);
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
