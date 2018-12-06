package bookStore;

/**
 * Creates the query class which contains methods to create sql queries
 * @author Kate Sanders
 *
 */
public class Queries 
{
	/**
	 * Creates a query which shows all records
	 * @return
	 */
	//view all books
	public static String queryDisplayAuthorTitleByTitle()
	{
		return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
				+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
				+ " Book.Name AS Book_Title FROM Book"
				+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
				+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
				+ " ORDER BY Book.Name";
	}
	
	/**
	 * Creates a query which shows records by title
	 * @return
	 */
	//search by the name of the book
	public static String querySearchByTitle(String title)
	{
		return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
				+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
				+ " Book.Name AS Book_Title FROM Book"
				+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
				+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
				+ " WHERE EXISTS (SELECT Author.First_Name, Author.Last_Name, Book.Name AS Title FROM Book"
				+ " WHERE Book.Name = " + "'" + title + "')"
				+ " AND Book.Name = " + "'" + title + "'"
				+ " ORDER BY Author.Last_Name, Author.First_Name, Book.Name";
	}
	
	/**
	 * Creates a query which shows records by a keyword in the title
	 * @param keyword
	 * @return
	 */
	//search by keyword in book name
		public static String querySearchByKeyword(String keyword) 
		{
			return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
					+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
					+ " Book.Name AS Book_Title FROM Book"
					+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
					+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
					+ " WHERE EXISTS (SELECT Author.First_Name, Author.Last_Name, Book.Name AS Title FROM Book"
					+ " WHERE Book.Name LIKE " + "'%" + keyword + "%')"
					+ " AND Book.Name LIKE " + "'%" + keyword + "%'"
					+ " ORDER BY Author.Last_Name, Author.First_Name, Book.Name";
		}
	
	/**
	 * Creates a query which shows records by author's last name
	 * @param lastName
	 * @return
	 */
	//search by author last name
	public static String querySearchByAuthorLast(String lastName)
	{
		return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
				+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
				+ " Book.Name AS Book_Title FROM Book"
				+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
				+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
				+ " WHERE EXISTS (SELECT Author.First_Name, Author.Last_Name, Book.Name AS Title FROM Book"
				+ " WHERE Author.Last_Name = " + "'" + lastName + "')"
				+ " ORDER BY Author.Last_Name, Author.First_Name, Book.Name";
	}
	
	/**
	 * Creates a query which shows records by author's first name
	 * @param firstName
	 * @return
	 */
	//search by author first name
	public static String querySearchByAuthorFirst(String firstName)
	{
		return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
				+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
				+ " Book.Name AS Book_Title FROM Book"
				+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
				+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
				+ " WHERE EXISTS (SELECT Author.First_Name, Author.Last_Name, Book.Name AS Title FROM Book"
				+ " WHERE Author.First_Name = " + "'" + firstName + "')"
				+ " ORDER BY Author.Last_Name, Author.First_Name, Book.Name";
	}
	
	/**
	 * Creates a query which shows records by price
	 * @param price1
	 * @param price2
	 * @return
	 */
	//search by book price
	public static String querySearchByPrice(int price1, int price2)
	{
		return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
				+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
				+ " Book.Name AS Book_Title FROM Book"
				+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
				+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
				+ " WHERE EXISTS (SELECT * FROM BOOK WHERE Book.Price >= " + price1 
				+ " AND Book.Price <= " + price2 + ")"
				+ " AND Book.Price >= " + price1 + " AND Book.Price <= " + price2
				+ " ORDER BY Book.Price, Author.Last_Name, Author.First_Name, Book.Name";
	}
	
	/**
	 * Creates a query which shows records by publisher
	 * @param pub
	 * @return
	 */
	//search by publisher
	public static String querySearchByPublisher(String pub)
	{
		return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
				+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
				+ " Book.Name AS Book_Title FROM Book"
				+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
				+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
				+ " WHERE EXISTS ( SELECT * FROM BOOK WHERE Book.Publisher = " + "'" + pub +"')"
				+ " AND Book.Publisher = " + "'" + pub +"'"
				+ " ORDER BY Book.Publisher, Author.Last_Name, Author.First_Name, Book.Name";
	}
	
	/**
	 * Creates a query which shows records by genre
	 * @param genre
	 * @return
	 */
	//search by genre
	public static String querySearchByGenre(String genre)
	{
		return "SELECT Author.First_Name AS Author_First, Author.Last_Name AS Author_Last,"
				+ " Book.Publisher AS Book_Publisher, Book.Genre AS Book_Genre, Book.Price AS Book_Price,"
				+ " Book.Name AS Book_Title FROM Book"
				+ " JOIN BookToAuthor on BookToAuthor.Book_ID = Book.ID"
				+ " JOIN Author on Author.ID = BookToAuthor.Author_ID"
				+ " WHERE EXISTS (SELECT * FROM Book"
				+ " WHERE Book.Genre = " + "'" + genre + "')"
				+ "AND Book.Genre = " + "'" + genre +"'"
				+ " ORDER BY Book.Genre, Author.Last_Name, Author.First_Name, Book.Name";
	}
	
}
