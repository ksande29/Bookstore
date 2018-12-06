package bookStore;
/**
 * Creates a book store app which runs in the console which was used to test out BookSql, AuthorSql,
 * BookToAuthorSql, and Queries
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BookStoreAppConsole 
{

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException 
	{
		//create tables and fill with records
		createAndFillTables(); //*****************only run once, will get an error if you try to make tables twice
		
		
		System.out.println("Welcome to the BookStore");		
		int selection = -1;
		do
		{
			//menu
			displayMainMenu();
			//get selection
			selection = getUserSelection(selection);
			//process selection
			switch (selection)
			{
			case 1: //view all books
				System.out.println("All books in the inventory:");
				executeQueries(Queries.queryDisplayAuthorTitleByTitle()); 
				break;	
			case 2: //search books	
				System.out.println("Search inventory");
				int selection2 = -1;
				do 
				{
					//display search options menu
					displaySearchOptionsMenu();
					//get user selection
					selection2 = getUserSelection(selection2);
					//process selection
					switch (selection2)
					{
						case 1:	//search by title
							int selection3 = -1;
							do
							{
								System.out.println("Search by book title: ");
								//display menu
								displaySearchByTitleMenu();
								//get user selection
								selection3 = getUserSelection(selection3);
								//process selection
								switch (selection3)	
								{
									case 1:
										System.out.println("Search by book title: ");
										System.out.print("Title: ");
										String title = sc.nextLine();
										System.out.println();
										System.out.println("All books that match the title " + title + ":");
										executeQueries(Queries.querySearchByTitle(title));
										System.out.println();
										break;	
									case 2:
										System.out.println("Search by keyword: ");
										System.out.print("Keyword: ");
										String key = sc.nextLine();
										System.out.println();
										System.out.println("All books that match the keyword " + key + ":");
										executeQueries(Queries.querySearchByKeyword(key));
										System.out.println();
									case 0: //exit 
										System.out.println("Goodbye");
										break;
									default: 
										System.out.println("Please select 0 - 5.");
										System.out.println();
								}
							}while (selection3 != 0);
							break;			
						case 2: //search by author
							int selection4 = -1;
							do 
							{
								System.out.println("Search by Author's name: ");
								//display menu
								displaySearchByAuthorMenu();
								//get user selection
								selection4 = getUserSelection(selection4);
								//process selection
								switch (selection4)
								{
									case 1: //search by author's first name
										System.out.println("Author's first Name: ");
										String first = sc.nextLine();
										System.out.println();
										System.out.println("Books by " + first + ":");
										executeQueries(Queries.querySearchByAuthorFirst(first));
										System.out.println();
										break;				
									case 2: //search by author's last name
										System.out.println("Author's last Name: ");
										String last = sc.nextLine();
										System.out.println();
										System.out.println("Books by " + last + ":");
										executeQueries(Queries.querySearchByAuthorLast(last)); 
										System.out.println();
										break;
									case 0: //exit 
										System.out.println("Goodbye");
										break;
									default: 
										System.out.println("Please select 0 - 5.");
										System.out.println();
									}
								}while (selection4 != 0);
							break;
						case 3: // search by price
							System.out.println("Costs more than (in dollars): ");
							int price1 = sc.nextInt();
							sc.nextLine();
							System.out.println("Costs less less than (in dollars): ");
							int price2 = sc.nextInt();
							System.out.println("Books that cost between " + price1 + " and " + price2 +":");
							executeQueries(Queries.querySearchByPrice(price1, price2));
							System.out.println();
							sc.nextLine();
							break;
						case 4: //search by genre
							System.out.println("Adventure, Biography, Childrens, Fantasy, Hist-Fi, Horror, "
									+ "Pol-Fi, Romance, Sci-Fi");
							System.out.println("Genre: ");
							String genre = sc.nextLine();
							System.out.println();
							System.out.println("Books of type " + genre + ":");
							executeQueries(Queries.querySearchByGenre(genre));
							System.out.println();
							break;	
						case 5: //search by publisher
							System.out.println("Publisher: ");
							String pub = sc.nextLine();
							System.out.println("Books published by " + pub + ":");
							executeQueries(Queries.querySearchByPublisher(pub));
							System.out.println();
							break;	
						case 0: //exit 
							System.out.println("Goodbye");
							break;
						default: 
							System.out.println("Please select 0 - 6.");
							System.out.println();
					}
				}while (selection2 != 0);
				break;
			case 0: //exit 
				System.out.println("Goodbye");
				break;
			default: 
				System.out.println("Please select 0 - 2.");
				System.out.println();
			}
		} while (selection != 0);
				
		
		//drop tables
		dropAllTables();	//******************************************Only run if you want to drop tables
		
		System.out.println("done");		
	}


	// - - - Methods - - - - - - - - 

	private static void displayMainMenu() 
	{
		System.out.println("Menu:");
		System.out.println("1 - View all books");
		System.out.println("2 - Search inventory");
		System.out.println("0 - Exit Program");
		System.out.println();
	}
	
	private static void displaySearchOptionsMenu() 
	{
		System.out.println("Menu: ");
		System.out.println("1 - Search by title: ");
		System.out.println("2 - Search by author: ");
		System.out.println("3 - Search by price: ");
		System.out.println("4 - Search by genre: ");
		System.out.println("5 - Search by publisher: ");
		System.out.println("0 - Back to main menu: ");
		System.out.println();
	}
	
	private static void displaySearchByTitleMenu() 
	{
		System.out.println("Menu: ");
		System.out.println("1 - Search full book title: ");
		System.out.println("2 - Search by keyword: ");
		System.out.println("0 - Back to previous: ");
		System.out.println();
	}
	
	private static void displaySearchByAuthorMenu() 
	{
		System.out.println("Menu: ");
		System.out.println("1 - Search by first name: ");
		System.out.println("2 - Search by last name: ");
		System.out.println("0 - Back to previous: ");
		System.out.println();
	}
	
	private static int getUserSelection(int selection) 
	{
		System.out.print("Selection: ");
		try 
		{
		    selection = Integer.parseInt(sc.nextLine());
		}
		catch (NumberFormatException e) 
		{
		    System.out.println("Input is not a valid integer");
		}
		return selection;
	}

	// - - - Database methods - - - - - - - - - - - - - - - - - - - 
	
	private static void createAndFillTables() throws SQLException 
	{
		executeSqlStatement(BookSql.createTable(), BookSql.insertIntoTable());	
		executeSqlStatement(AuthorSql.createTable(), AuthorSql.insertIntoTable());
		executeSqlStatement(BookToAuthorSql.createTable(), BookToAuthorSql.insertIntoTable());
	}
	
	private static void dropAllTables() throws SQLException 
	{
		executeSqlStatement(BookSql.dropTable());
		executeSqlStatement(AuthorSql.dropTable());
		executeSqlStatement(BookToAuthorSql.dropTable());
	}
	
	private static void executeSqlStatement(String... sqlStatements) throws SQLException 
	{
		try(Connection connection = DriverManager.getConnection("jdbc:derby:bookStore;create=true");
				Statement statement = connection.createStatement();) 
		{
			for (String sqlStatement : sqlStatements)
			{
				statement.execute(sqlStatement);
			}
		}
	}
	
	private static void executeQueries(String... sqlQueries) throws SQLException 
	{
		try(Connection connection = DriverManager.getConnection("jdbc:derby:bookStore");
				Statement statement = connection.createStatement();) 
		{
			for (String query : sqlQueries)
			{
				ResultSet results = statement.executeQuery(query);			
				printData(results);
				System.out.println();
			}
		}	
	}
	
	private static void printData(ResultSet results) throws SQLException 
	{
		ResultSetMetaData metaData = results.getMetaData();
		int columnCount = metaData.getColumnCount();
				
		printHeader(metaData);
		while (results.next())
		{
			for (int i = 1; i <= columnCount; i++)
			{
				System.out.printf("%-" + metaData.getColumnLabel(i).length() +"s ",
					results.getObject(i) );
			}
			System.out.println();
		}	
	}

	private static int printHeader(ResultSetMetaData metaData) throws SQLException 
	{
		int columnCount = metaData.getColumnCount();
		
		//print column headers
		for(int i = 1; i <= columnCount; i++)
		{
			System.out.print(metaData.getColumnLabel(i) + " ");
		}
		System.out.println();
		
		//print dashed line
		for (int i = 1; i <= columnCount; i++)
		{
			for (int j = 0; j < metaData.getColumnLabel(i).length(); j++) 
			{
				System.out.print("-");
			}
			if (i != columnCount)
					System.out.print("--");
		}
		System.out.println();
		return columnCount;
	}

}
