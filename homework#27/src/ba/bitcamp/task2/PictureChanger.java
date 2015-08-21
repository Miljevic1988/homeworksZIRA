package ba.bitcamp.task2;

import javax.swing.JFrame;

/**
 * Represents JFrame that contains main panel on which are image and button.
 * 
 * @author Zeljko Miljevic
 */
public class PictureChanger extends JFrame {
	private static final long serialVersionUID = -2760450028790468192L;

	/**
	 * Constructor that opens main JFrame.
	 */
	public PictureChanger() {

		ChangerGui panel = new ChangerGui();

		add(panel);

		setSize(panel.getImage().getWidth(), panel.getImage().getHeight());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Picture changer");
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		// Calling constructor.
		new PictureChanger();
	}
}
