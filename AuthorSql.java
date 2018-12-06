package bookStore;

/**
 * Creates AuthorSql class which contains methods for making sql statements to create, fill, and delete
 * the Author table as well as methods to create queries to see what records are in the table
 * @author Kate Sanders
 *
 */
public class AuthorSql 
{
	/**
	 * Create Author table
	 * @return
	 */
	public static String createTable()
	{
		return "CREATE TABLE Author (" 
				+ "ID int not null primary key"
				+ "  GENERATED ALWAYS AS IDENTITY"
				+ "  (INCREMENT BY 1),"
				+ " Last_Name varchar(255),"
				+ " First_Name varchar(255))";
	}
	
	/**
	 * Add records to Author table
	 * @return
	 */
	public static String insertIntoTable()
	{
		return "INSERT INTO Author (Last_Name, First_Name) VALUES" 
				+ " ('Rowling', 'JK')," //1
				+ " ('Sapolsky', 'Robert')," //2 
				+ " ('Lewis', 'CS')," //3
				+ " ('Seuss', 'Dr')," //4
				+ " ('Lee', 'Harper')," //5
				
				+ " ('Austin', 'Jane')," //6
				+ " ('Orwell', 'George')," //7
				+ " ('White', 'EB'),"	//8		
				+ " ('Tolkien', 'JRR')," //9
				+ " ('Bronte', 'Charlotte')," //10
				
				+ " ('Twain', 'Mark')," //11
				+ " ('Stockett', 'Kathryn')," //12
				+ " ('Zusak', 'Markus')," //13
				+ " ('Shakespeare', 'William')," //14
				+ " ('Silverstein', 'Shel')," //15

				+ " ('Bronte', 'Emily')," //16
				+ " ('Martel', 'Yann')," //17
				+ " ('Collins', 'Suzanne')," //18
				+ " ('Dahl', 'Roald')," //19
				+ " ('Roll', 'Rich')," //20
				
				+ " ('King', 'Stephen')," //21
				+ " ('Asimov', 'Isaac')," //22
				+ " ('Bradbury', 'Ray')"; //23
	}
	
	/**
	 * Delete Author table
	 * @return
	 */
	public static String dropTable()
	{
		return "DROP TABLE Author";
	}
	
	// - - query methods - - - - - - 
	
	/**
	 * Query Author table to see all records
	 * @return
	 */
	public static String queryAll()
	{
		return "SELECT * FROM Author";
	}
	
	/**
	 * Query Author table to see all records based on author last name
	 * @param name
	 * @return
	 */
	public static String queryByLastName(String name)
	{
		return "SELECT * FROM Author WHERE Last_Name = name";
	}
	
	/**
	 * Query Author table to see all records based on author first name
	 * @param name
	 * @return
	 */
	public static String queryByFirstName(String name)
	{
		return "SELECT * FROM Author WHERE First_Name = name";
	}
	
	/**
	 * Query Author table to see all records based on author ID
	 * @param authorId
	 * @return
	 */
	public static String queryByAuthorId(int authorId)
	{
		return  "SELECT * FROM Author WHERE ID = authorId";
	}
}
