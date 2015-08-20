package ba.bitcamp.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents the BitResponse.
 * 
 * @author Zeljko Miljevic
 */
public class BitResponse {

	// Declaring properties.
	private String message;
	private String timestamp;

	/**
	 * Empty constructor.
	 */
	public BitResponse() {
		this.message = null;
		this.timestamp = null;
	}

	/**
	 * Constructor.
	 */
	public BitResponse(String message) {
		this.message = message;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		this.timestamp = dateFormat.format(date);
	}

	/**
	 * Prints the BitResponse object.
	 */
	public String toString() {
		return String.format("Message: %s, Timestamp: %s", message, timestamp);
	}

	/*
	 * Get and set methods.
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}