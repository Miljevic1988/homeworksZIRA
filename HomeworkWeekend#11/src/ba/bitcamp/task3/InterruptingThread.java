package ba.bitcamp.task3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * This class represents GUI application.
 * 
 * @author Zeljko Miljevic
 */
public class InterruptingThread extends JPanel {
	
	private static final long serialVersionUID = -6091936492830652980L;
	
	private JFrame frame = new JFrame("Interrupting a Thread");
	private JLabel label = new JLabel();
	private JButton button = new JButton("STOP");
	private JScrollPane pane = new JScrollPane(label);

	private MyThread t;
	private boolean isRunning = true;

	public static final int LETTER_A = 97;
	public static final int LETTER_Z = 123;

	/**
	 * Constructor.
	 */
	public InterruptingThread() {
		
		t = new MyThread();
		t.start();

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isRunning = false;
			}
		});

		frame.add(this);
		frame.setLayout(new BorderLayout());
	
		label.setHorizontalAlignment(SwingConstants.CENTER);

		frame.add(pane);
		frame.add(button, BorderLayout.SOUTH);
		
		frame.setSize(300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		new InterruptingThread();
	}

	/**
	 *This inner class extends thread class.
	 *
	 */
	private class MyThread extends Thread {

		public void run() {
			
			String str = "";
			int letter = LETTER_A;

			while (isRunning && letter <= LETTER_Z) {
				str += (char) letter++;
				label.setText(str);

				if (letter == LETTER_Z) {
					letter = LETTER_A;
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
