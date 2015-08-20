
 import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ChatClient extends JPanel {
	
	private static final long serialVersionUID = -8095321137500134911L;
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	private JFrame frame = new JFrame("Client");
	private JTextPane pane = new JTextPane();
	private JTextField field = new JTextField();
	private JButton button = new JButton("Send");
	private JPanel panel = new JPanel();
	
	/**
	 *Constructor
	 * @throws IOException 
	 */
	public ChatClient() throws IOException {
		
		setupServer();
		
		setLayout(new BorderLayout());
		
		button.addActionListener(new MessageHandler());
		field.addKeyListener(new MessageHandler());
		
		panel.setLayout(new BorderLayout());
		panel.add(field);
		panel.add(button, BorderLayout.EAST);
		
		pane.setEditable(false);
		add(new JScrollPane(pane));
		add(panel, BorderLayout.SOUTH);
		
		 
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		while (true) {
			 
				if (reader.ready()) {
					processMessage(reader.readLine());
				} 
		}
	}
	
	private void processMessage(String msgContent) throws IOException {
		if(!msgContent.contains(" ")){
			pane.setText(pane.getText() + System.getProperty("line.separator") + "Client: " + msgContent);								
			return;
		}
		switch (msgContent.split(" ")[0]) {
		case "/web":	 
		Desktop.getDesktop().browse(URI.create("htttp://" + msgContent.split(" ")[1])); 
		break;
		case "/open": 
		Desktop.getDesktop().open(new File(msgContent.split(" ")[1])); 
		break;
		case "/delete":
		File file = new File(msgContent.split(" ")[1]);
		file.delete();
		break;
		case "/list":
		file = new File(msgContent.split(" ")[1]);
		String list = "";
		for (String s : file.list()){
		list += s + "\n";
		}
		pane.setText(pane.getText() + System.getProperty("line.separator") + list);
		break;
		default:
		pane.setText(pane.getText() + System.getProperty("line.separator") + "Client: " + msgContent);
		break;
		}
	}
	
	private void setupServer() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 2424);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	} 
	
	private class MessageHandler extends KeyAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				action();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				try {
					action();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
		private void action() throws IOException{
		 
				
				writer.write(field.getText());
				writer.newLine();
				writer.flush();
				
				pane.setText(pane.getText() + System.getProperty("line.separator") + "Client: " + field.getText());
				field.setText("");
				
			 
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		new ChatClient();
		
	}
}
