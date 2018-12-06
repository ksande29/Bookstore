package bookStore;

/**
 * Creates BookSql class which contains methods for making sql statements to create, fill, and delete
 * the Book table as well as methods to create queries to see what records are in the table
 * @author Kate Sanders
 *
 */
public class BookSql 
{
	/**
	 * Create Book table
	 * @return
	 */
	public static String createTable()
	{
		return "CREATE TABLE Book (" 
				+ "ID int not null primary key"
				+ "  GENERATED ALWAYS AS IDENTITY"
				+ "  (INCREMENT BY 1),"
				+ " Name varchar(255),"
				+ " Publisher varchar(255),"
				+ " Genre varchar(255),"
				+ " Price int)";
	}
	
	/**
	 * Add records to book table
	 * @return
	 */
	public static String insertIntoTable()
	{
		return "INSERT INTO Book (Name, Publisher, Genre, Price) VALUES" 
				+ " ('Harry Potter and the Goblet of Fire', 'Bloomsbury', 'Fantasy', 27)," //1 J. K. Rowling
				+ " ('A Primates Memoir', 'Scribner', 'Biography', 14)," //2 Robert Sapolsky
				+ " ('The Lion the Witch and the Wardrobe', 'HarperCollins', 'Fantasy', 10)," //3 C.S. Lewis
				+ " ('The Cat in the Hat', 'Random House', 'Childrens', 2)," //4 Dr. Seuss
				+ " ('To Kill a MockingBird', 'HarperCollins', 'Hist-Fi', 10)," //5 Harper Lee
				
				+ " ('Pride and Prejudice', 'HarperCollins', 'Romance', 6)," //6 Jane Austin
				+ " ('1984', 'Random House', 'Sci-Fi', 11)," //7 George Orwell
				+ " ('Harry Potter and the Sorcerers Stone', 'HarperCollins', 'Fantasy', 18)," //8 JK Rowling
				+ " ('Charlottes Web', 'HarperCollins', 'Childrens', 6)," //9 EB White
				+ " ('The Hobbit', 'HarperCollins', 'Fantasy', 18)," //10 JRR Tolkien
				
				+ " ('Jane Eyre', 'Random House', 'Romance', 4)," //11 Charlotte Bronte
				+ " ('Animal Farm', 'Random House', 'Pol-Fi', 15)," //12 George Orwell
				+ " ('The Adventures of Huckleberry Finn', 'Sterling', 'Adventure', 8)," //13 Mark Twain
				+ " ('The Help', 'Random House', 'Hist-Fi', 13)," //14 Kathryn Stockett
				+ " ('The Book Thief', 'Random House', 'Hist-Fi', 14)," //15 Markus Zusak
				
				+ " ('Romeo and Juliet', 'HarperCollins', 'Tragedy', 8)," //16 William Shakespere
				+ " ('Harry Potter and the Deathly Hallows', 'Bloomsbury', 'Fantasy', 28)," //17 JK Rowling
				+ " ('Where the Sidewalk Ends', 'HarperCollins', 'Childrens', 15)," //18 Shel Silverstein
				+ " ('Wuthering Heights', 'HarperCollins', 'Romance', 4)," //19 Emily Bronte
				+ " ('The Adventures of Tom Sawyer', 'Sterling', 'Adventure', 7)," //20 Mark Twain
				
				+ " ('Macbeth', 'HarperCollins', 'Tragedy', 6)," //21 William Shakespeare
				+ " ('Harry Potter and the Prisoner of Azkaban', 'Bloomsbury', 'Fantasy', 23)," //22 JK Rowling
				+ " ('Harry Potter and the Half Blood Prince', 'Bloomsbury', 'Fantasy', 22)," //23 JK Rowling
				+ " ('Life of Pi', 'Random House', 'Fantasy', 12)," //24 Yann Martel
				+ " ('The Chronicles of Narnia', 'HarperCollins', 'Fantasy', 20)," //25 CS Lewis
				
				+ " ('Catching Fire', 'Scholastic', 'Sci-Fi', 12)," //26 Suzanne Collins
				+ " ('Charlie and the Chocolate Factory', 'Random House', 'Childrens', 9)," //27 Roald Dahl
				+ " ('Mockingjay', 'Scholastic', 'Sci-Fi', 10)," //28 Suzanne Collins
				+ " ('The Fellowship of the Ring', 'HarperCollins', 'Fantasy', 15)," //29 JRR Tolkein
				+ " ('The Two Towers', 'HarperCollins', 'Fantasy', 13)," //30 JRR Tolkein
				
				+ " ('The Return of the King', 'HarperCollins', 'Fantasy', 16)," //31 JRR Tolkein
				+ " ('Matilda', 'Random House', 'Childrens', 8)," //32 Roald Dahl
				+ " ('The Witches', 'Random House', 'Childrens', 6)," //33 Roald Dahl
				+ " ('The BFG', 'Random House', 'Childrens', 7)," //34 Roald Dahl
				+ " ('Boy', 'Random House', 'Childrens', 8)," //35 Roald Dahl
				
				+ " ('James and the Giant Peach', 'Random House', 'Childrens', 6)," //36 Roald Dahl
				+ " ('Going Solo', 'Random House', 'Childrens', 5)," //37 Roald Dahl
				+ " ('The Giving Tree', 'HarperCollins', 'Childrens', 11)," //38 Shel Silverstein		
				+ " ('Falling Up', 'HarperCollins', 'Childrens', 17)," //39 Shel Silverstein
				+ " ('Green Eggs and Ham', 'Random House', 'Childrens', 2)," //40 Dr Seuss
				
				+ " ('Fantastic Beasts and Where to Find Them', 'Bloomsbury', 'Fantasy', 28)," //41 JK Rowling
				+ " ('Harry Potter and the Order of the Phoenix', 'Bloomsbury', 'Fantasy', 12)," //42 JK Rowling
				+ " ('Finding Ultra', 'Harmony', 'Biography', 15)," //43 Rich Roll
				+ " ('The Shining', 'Random House', 'Horror', 6)," //44 Stephen King
				+ " ('IT', 'Random House', 'Horror', 8)," //45 Stephen King
				
				+ " ('The Dead Zone', 'Random House', 'Horror', 5)," //46 Stephen King
				+ " ('The Stand', 'Random House', 'Horror', 5)," //47 Stephen King		
				+ " ('Foundation', 'HarperCollins', 'Sci-Fi', 10)," //48 Isaac Asimov
				+ " ('I Robot', 'HarperCollins', 'Sci-Fi', 11)," //49 Isaac Asimov
				+ " ('Fahrenheit 451', 'Random House', 'Sci-Fi', 6)," //50 Ray Bradbury
				
				+ " ('The Martian Chronicles', 'Random House', 'Sci-Fi', 8)"; //51 Ray Bradbury		
	}
	
	/**
	 * Delete book table
	 * @return
	 */
	public static String dropTable()
	{
		return "DROP TABLE Book";
	}
	
	// - - query methods - - - - - - 
	
	/** 
	 * Query Book table to see all records
	 * @return
	 */
	public static String queryAll()
	{
		return "SELECT * FROM Book";
	}
	
	/**
	 * Query Book table by genre
	 * @param gen
	 * @return
	 */
	public static String queryByGenre(String gen)
	{
		String query = "SELECT * FROM book WHERE genre = " + gen;
		return query;
	}
	
	/**
	 * Query Book table by Author ID
	 * @param authorId
	 * @return
	 */
	public static String queryByAuthorID(int authorId)
	{
		String query = "SELECT * FROM Book "
		+ "INNER JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID "
		+ "WHERE BookToAuthor.Author_ID = " + authorId; 
		return query;
	}
	
	/**
	 * Query Book table by price
	 * @param price1
	 * @param price2
	 * @return
	 */
	public static String queryByPrice(int price1, int price2)
	{
		String query = "SELECT * FROM book WHERE price > " + price1 
				+ " & <" + price2;
		return query;
	}
	
	public static String queryByPublisher(String pub)
	{
		String query = "SELECT * FROM book WHERE publisher = " + pub;
		return query;
	}

}
