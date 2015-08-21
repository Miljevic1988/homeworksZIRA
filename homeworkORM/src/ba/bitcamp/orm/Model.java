package ba.bitcamp.orm;

public abstract class Model {

	private String tableName;

	/**
	 * Constructor
	 * 
	 * @param tableName
	 */
	public Model(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Returns string representing a query that selects a row by its primary
	 * key.
	 */
	public String findByPk(Integer pk) {
		return "SELECT * FROM " + tableName + " WHERE id = " + pk;
	}

	/**
	 * Returns string representing a query that selects a row where attribute a1
	 * equals value a2.
	 */
	public String findByAttribute(String a1, String a2) {
		return "SELECT * FROM " + tableName + " WHERE " + a1 + " = " + a2;
	}

}
