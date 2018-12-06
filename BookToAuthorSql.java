package bookStore;

/**
 * Creates BookToAuthorSql class which contains methods for making sql statements to create, fill, and delete
 * the BookToAuthor table as well as methods to create queries to see what records are in the table
 * Allows for a many to many relationship between book and author
 * @author Kate Sanders
 *
 */
public class BookToAuthorSql 
{
	/**
	 * Create BookToAuthor table
	 * @return
	 */
	public static String createTable()
	{
		return "CREATE TABLE BookToAuthor (" 
				+ " Book_ID int,"
				+ " Author_ID int)";
	}
	
	/**
	 * Add records to BookToAuthorTable
	 * @return
	 */
	public static String insertIntoTable()
	{
		return "INSERT INTO BookToAuthor (Book_ID, Author_ID) VALUES" 
				+ "(1, 1), " 
				+ "(2, 2), " 
				+ "(3, 3), " 
				+ "(4, 4), " 				
				+ "(5, 5), " 
				
				+ "(6, 6), " 
				+ "(7, 7), " 
				+ "(8, 1), " 
				+ "(9, 8), " 
				+ "(10, 9), "
				
				+ "(11, 10), "
				+ "(12, 7), "
				+ "(13, 11), "
				+ "(14, 12), "
				+ "(15, 13), " 
				
				+ "(16, 14), " 
				+ "(17, 1), "
				+ "(18, 15), "
				+ "(19, 16), "
				+ "(20, 11), "
				
				+ "(21, 14), "
				+ "(22, 1), "
				+ "(23, 1), "
				+ "(24, 17), "
				+ "(25, 3), "
				
				+ "(26, 18), "
				+ "(27, 19), "
				+ "(28, 18), "
				+ "(29, 9), "
				+ "(30, 9), "
				
				+ "(31, 9), "
				+ "(32, 19), "
				+ "(33, 19), "
				+ "(34, 19), "
				+ "(35, 19), "
				
				+ "(36, 19), "
				+ "(37, 19), "
				+ "(38, 15), "
				+ "(39, 15), "
				+ "(40, 4), "
				
				+ "(41, 1), "
				+ "(42, 1), "
				+ "(43, 20), "
				+ "(44, 21), "
				+ "(45, 21), "
				
				+ "(46, 21), "
				+ "(47, 21), "				
				+ "(48, 22), "
				+ "(49, 22), "
				+ "(50, 23), "
				
				+ "(51, 23) ";		
	}
	
	/**
	 * Delete BookToAuthor table
	 * @return
	 */
	public static String dropTable()
	{
		return "DROP TABLE BookToAuthor";
	}
	
	// - - query methods - - - - - - 
	
	/**
	 * Query all records in BookToAuthor table
	 * @return
	 */
	public static String queryAll()
	{
		return "SELECT * FROM BookToAuthor";
	}
}
