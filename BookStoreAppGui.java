package bookStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Color;

/**
 * Creates a graphical book store app which allows for viewing records in Book and Author tables
 * and to query results based on book title, author name, price, genre, and publisher
 * @author Kate
 *
 */
public class BookStoreAppGui extends JFrame 
{
	private JPanel contentPane;
	static JTextArea txt;
	static JScrollPane scroll;
	
	private JPanel searchOptionsPanel;
	private JPanel searchTitlePanel;
	private JPanel searchFullTitlePanel;
	private JPanel searchKeywordPanel;
	private JPanel searchAuthorPanel;
	private JPanel searchAuthorFirstPanel;
	private JPanel searchAuthorLastPanel;
	private JPanel searchPricePanel;
	private JPanel searchGenrePanel;
	private JPanel searchPublisherPanel;

	private String input = "";
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException 
	{
		
		createAndFillTables();
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					BookStoreAppGui frame = new BookStoreAppGui();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		//dropAllTables();
	}

	/**
	 * Create the frame.
	 */
	public BookStoreAppGui() 
	{
		setUpMainWindow();	
		makeTitle();
		makeCenterDisplay();
		makeBtnPanel();
		
		makeSearchPanel();
		makeSearchTitlePanel();
		makeSearchAuthorPanel();
		makeSearchPricePanel();
		makeSearchGenrePanel();		
		makeSearchPublisherPanel();	
		makeSearchFullTitlePanel();
		makeSearchKeywordPanel();
		makeSearchAuthorFirstPanel();
		makeSearchAuthorLastPanel();
	}
	
	
	// - display methods - - - - - - - - - - - - - - - - - - - - - - - - -
	
	/**
	 * Set up main JFrame display
	 */
	private void setUpMainWindow() 
	{
		//close button inactive so the tables are deleted on close preventing an error
		//when the program is reopened and calls the create tables methods
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1500, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	/**
	 * Create the Book Store title label
	 */
	private void makeTitle() 
	{
		JLabel lblNewLabel = new JLabel("Book Store");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 80));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}
	
	/**
	 * Create center display which shows records
	 */
	private void makeCenterDisplay() 
	{
		txt = new JTextArea();
		txt.setEditable(false);
		txt.setBorder(new EmptyBorder(20, 20, 20, 20));
		txt.setFont(new Font("Courier New", Font.PLAIN, 13));
		scroll = new JScrollPane(txt);
		scroll.setBackground(Color.BLACK);
		scroll.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(scroll, BorderLayout.CENTER);
	}
	
	/**
	 * Create button panel which has buttons for viewing all books, searching, and closing the app
	 */
	// - make bottom button panel - - - - - - - - - - - - - - - - - - - - - - - - -
	private void makeBtnPanel() 
	{
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.BLACK);
		btnPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		
		makeShowAllBtn(btnPanel);
		makeSearchBtn(btnPanel);
		makeExitBtn(btnPanel);
	}

		/**
		 * Create a button which shows all books in the database
		 * @param btnPanel
		 */
		private void makeShowAllBtn(JPanel btnPanel) 
		{
			JButton btnShowAllBooks = new JButton("Show All Books");
			btnShowAllBooks.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnShowAllBooks.setBackground(Color.WHITE);
			btnShowAllBooks.setBorder(new EmptyBorder(20, 20, 20, 20));
			btnShowAllBooks.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					searchOptionsPanel.setVisible(false);
					try 
					{
						executeQueries(Queries.queryDisplayAuthorTitleByTitle());
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
			btnPanel.add(btnShowAllBooks);
		}
		
		/**
		 * Create a button to search through the books in the database
		 * @param btnPanel
		 */
		private void makeSearchBtn(JPanel btnPanel) 
		{
			JButton btnSearchBooks = new JButton("Search Books");
			btnSearchBooks.setBackground(Color.WHITE);
			btnSearchBooks.setBorder(new EmptyBorder(20, 20, 20, 20));
			btnSearchBooks.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnSearchBooks.setForeground(Color.BLACK);
			btnSearchBooks.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					contentPane.add(searchOptionsPanel,BorderLayout.EAST);
					searchOptionsPanel.setVisible(true);
					searchTitlePanel.setVisible(false);
					searchAuthorPanel.setVisible(false);
					searchPricePanel.setVisible(false);
					searchGenrePanel.setVisible(false);
					searchPublisherPanel.setVisible(false);
					searchFullTitlePanel.setVisible(false);
					searchKeywordPanel.setVisible(false);
					searchAuthorFirstPanel.setVisible(false);
					searchAuthorLastPanel.setVisible(false);
					input = "";
					txt.setText("");
				}
			});
			btnPanel.add(btnSearchBooks);
		}
		
		/**
		 * Create a button which closes the application and removes all the tables from the database
		 * @param btnPanel
		 */
		private void makeExitBtn(JPanel btnPanel) 
		{
			JButton btnExit = new JButton("Exit Program");
			btnExit.setBackground(Color.WHITE);
			btnExit.setBorder(new EmptyBorder(20, 20, 20, 20));
			btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnExit.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						dropAllTables();
					} catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
					System.exit(0);
				}
			});
			btnPanel.add(btnExit);
		}

		// - Search panel methods - - - - - - - - - - - - - - - - - - - - -  
		/**
		 * Creates the search bar with buttons to search based on book title, author name,
		 * price, genre, and publisher
		 */
		private void makeSearchPanel() 
		{
			searchOptionsPanel = new JPanel();
			searchOptionsPanel.setLayout(new GridLayout(10, 1, 0, 0));
			searchOptionsPanel.setVisible(false);
			searchOptionsPanel.setBorder(new EmptyBorder(20, 0, 20, 20));
			searchOptionsPanel.setBackground(Color.BLACK);
			
			makeSearchByTitleBtn();
			makeSearchByAuthorBtn(); 
			makeSearchByPriceBtn(); 
			makeSearchByGenreBtn(); 
			makeSearchByPublisherBtn(); 
		}

			/**
			 * Create a button to search by title
			 */
			private void makeSearchByTitleBtn() 
			{
				JButton btnSearchByTitle = new JButton("Search by Title");
				btnSearchByTitle.setBackground(Color.WHITE);
				btnSearchByTitle.setForeground(Color.BLACK); 
				btnSearchByTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
				searchOptionsPanel.add(btnSearchByTitle);
				btnSearchByTitle.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						searchOptionsPanel.setVisible(false);
						contentPane.add(searchTitlePanel, BorderLayout.EAST);
						searchTitlePanel.setVisible(true);
					}
				});
			}
	
			/**
			 * Create a button to search by author
			 */
			private void makeSearchByAuthorBtn() 
			{
				JButton btnSearchByAuthor = new JButton("Search by Author");
				btnSearchByAuthor.setBackground(Color.WHITE);
				btnSearchByAuthor.setForeground(Color.BLACK); 
				btnSearchByAuthor.setFont(new Font("Tahoma", Font.BOLD, 16));
				searchOptionsPanel.add(btnSearchByAuthor);
				btnSearchByAuthor.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						searchOptionsPanel.setVisible(false);
						contentPane.add(searchAuthorPanel, BorderLayout.EAST);
						searchAuthorPanel.setVisible(true);
					}
				});
			}
	
			/**
			 * Create a button to search by price
			 */
			private void makeSearchByPriceBtn() 
			{
				JButton btnSearchByPrice = new JButton("Search by Price");
				btnSearchByPrice.setBackground(Color.WHITE);
				btnSearchByPrice.setForeground(Color.BLACK); 
				btnSearchByPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
				searchOptionsPanel.add(btnSearchByPrice);
				btnSearchByPrice.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						searchOptionsPanel.setVisible(false);
						contentPane.add(searchPricePanel, BorderLayout.EAST);
						searchPricePanel.setVisible(true);
					}
				});
			}
	
			/**
			 * Create a button to search by genre
			 */
			private void makeSearchByGenreBtn() 
			{
				JButton btnSearchByGenre = new JButton("Search by Genre");
				btnSearchByGenre.setBackground(Color.WHITE);
				btnSearchByGenre.setForeground(Color.BLACK); 
				btnSearchByGenre.setFont(new Font("Tahoma", Font.BOLD, 16));
				searchOptionsPanel.add(btnSearchByGenre);
				btnSearchByGenre.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						searchOptionsPanel.setVisible(false);
						contentPane.add(searchGenrePanel, BorderLayout.EAST);
						searchGenrePanel.setVisible(true);
					}
				});
			}
	
			/**
			 * Create a button to search by publisher
			 */
			private void makeSearchByPublisherBtn() 
			{
				JButton btnSearchByPublisher = new JButton("Search by Publisher");
				btnSearchByPublisher.setBackground(Color.WHITE);
				btnSearchByPublisher.setForeground(Color.BLACK); 
				btnSearchByPublisher.setFont(new Font("Tahoma", Font.BOLD, 16));
				searchOptionsPanel.add(btnSearchByPublisher);
				btnSearchByPublisher.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						searchOptionsPanel.setVisible(false);
						contentPane.add(searchPublisherPanel, BorderLayout.EAST);
						searchPublisherPanel.setVisible(true);
					}
				});
			}

	
	// - Search title panel methods - - - - - - - - - - - - - - - - - - - - - -		
	/**
	 * Create a panel which shows search by title options which has buttons forsearching
	 * by the full title or by a keyword
	 */
	private void makeSearchTitlePanel() 
	{
		searchTitlePanel = new JPanel();
		searchTitlePanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchTitlePanel.setVisible(false);
		searchTitlePanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchTitlePanel.setBackground(Color.BLACK);
		
		makeBtnSearchByFullTitle();
		makeBtnSearchByKeyword();
	}

		/**
		 * Create a button to search by full title
		 */
		private void makeBtnSearchByFullTitle() 
		{
			JButton btnSearchByFullTitle = new JButton("Search by Full Title");
			btnSearchByFullTitle.setBackground(Color.WHITE);
			btnSearchByFullTitle.setForeground(Color.BLACK); 
			btnSearchByFullTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchTitlePanel.add(btnSearchByFullTitle);
			btnSearchByFullTitle.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{				
					contentPane.add(searchFullTitlePanel,BorderLayout.EAST);
					searchFullTitlePanel.setVisible(true);
					searchTitlePanel.setVisible(false);
				}
			});
		}
	
		/**
		 * Create a button to search by a keyword in the title
		 */
		private void makeBtnSearchByKeyword() 
		{
			JButton btnSearchByKeyword = new JButton("Search by Keyword");
			btnSearchByKeyword.setBackground(Color.WHITE);
			btnSearchByKeyword.setForeground(Color.BLACK); 
			btnSearchByKeyword.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchTitlePanel.add(btnSearchByKeyword);
			btnSearchByKeyword.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{				
					contentPane.add(searchKeywordPanel,BorderLayout.EAST);
					searchKeywordPanel.setVisible(true);
					searchTitlePanel.setVisible(false);
				}
			});
		}
	
	// - Search author panel methods - - - - - - - - - - - - - - - - - - - - - -	
	/**
	 * Create the search by author panel which has buttons to search by first or last name
	 */
	private void makeSearchAuthorPanel() 
	{
		searchAuthorPanel = new JPanel();
		searchAuthorPanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchAuthorPanel.setVisible(false);
		searchAuthorPanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchAuthorPanel.setBackground(Color.BLACK);
		
		makeBtnSearchByFirst();		
		makeBtnSearchByLast();
	}

		/**
		 * Create a button to search by author first name
		 */
		private void makeBtnSearchByFirst() 
		{
			JButton btnSearchByFirst = new JButton("Search by First Name");
			searchAuthorPanel.add(btnSearchByFirst);
			btnSearchByFirst.setBackground(Color.WHITE);
			btnSearchByFirst.setForeground(Color.BLACK); 
			btnSearchByFirst.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnSearchByFirst.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{				
					contentPane.add(searchAuthorFirstPanel,BorderLayout.EAST);
					searchAuthorFirstPanel.setVisible(true);
					searchAuthorPanel.setVisible(false); 
				}
			});
		}
		
		/**
		 * Create a button to search by author last name
		 */
		private void makeBtnSearchByLast() 
		{
			JButton btnSearchByLast = new JButton("Search by Last Name");
			btnSearchByLast.setBackground(Color.WHITE);
			btnSearchByLast.setForeground(Color.BLACK); 
			btnSearchByLast.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchAuthorPanel.add(btnSearchByLast);	
			btnSearchByLast.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{				
					contentPane.add(searchAuthorLastPanel,BorderLayout.EAST);
					searchAuthorLastPanel.setVisible(true);
					searchAuthorPanel.setVisible(false);
				}
			});
		}

	// - Search price panel methods - - - - - - - - - - - - - - - - - - - - - -	
	/**
	 * Create search by price panel which has buttons to search based on different 
	 * price ranges
	 */
	private void makeSearchPricePanel() 
	{
		searchPricePanel = new JPanel();
		searchPricePanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchPricePanel.setVisible(false); 
		searchPricePanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchPricePanel.setBackground(Color.BLACK);
		
		makeBtnZeroTen();
		makeBtnTenTwenty();
		makeBtnTwentyThirty();
		makeBtnThirtyFourty();
	}

		/**
		 * Create a button to search by price between 0 - 9.99
		 */
		private void makeBtnZeroTen() 
		{
			JButton btnZeroTen = new JButton("$0.00 - 9.99");
			searchPricePanel.add(btnZeroTen);
			btnZeroTen.setBackground(Color.WHITE);
			btnZeroTen.setForeground(Color.BLACK); 
			btnZeroTen.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnZeroTen.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPrice(0, 9)); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by price between 10 - 9.99 
		 */
		private void makeBtnTenTwenty() 
		{
			JButton btnTenTwenty = new JButton("$10.00 - 10.99");
			btnTenTwenty.setBackground(Color.WHITE);
			btnTenTwenty.setForeground(Color.BLACK); 
			btnTenTwenty.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPricePanel.add(btnTenTwenty);
			btnTenTwenty.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPrice(10, 19)); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by price between 20 - 29.99
		 */
		private void makeBtnTwentyThirty() 
		{
			JButton btnTwentyThirty = new JButton("$20.00 - 20.99");
			btnTwentyThirty.setBackground(Color.WHITE);
			btnTwentyThirty.setForeground(Color.BLACK); 
			btnTwentyThirty.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPricePanel.add(btnTwentyThirty);
			btnTwentyThirty.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPrice(20, 29)); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
		
		/**
		 * Create a button to search by price between 30 - 39.99
		 */
		private void makeBtnThirtyFourty() 
		{
			JButton btnThirtyFourty = new JButton("$30.00 - 30.99");
			btnThirtyFourty.setBackground(Color.WHITE);
			btnThirtyFourty.setForeground(Color.BLACK); 
			btnThirtyFourty.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPricePanel.add(btnThirtyFourty);
			btnThirtyFourty.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPrice(30, 39)); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
	// - Search genre panel methods - - - - - - - - - - - - - - - - - - - - - - - - -	
	/**
	 * Creates the search by genre panel which has buttons for each genre in the
	 * database
	 */
		private void makeSearchGenrePanel() 
	{
		searchGenrePanel = new JPanel();
		searchGenrePanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchGenrePanel.setVisible(false);
		searchGenrePanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchGenrePanel.setBackground(Color.BLACK);
		
		makeBtnAdv();		
		makeBtnBio();
		makeBtnChild();
		makeBtnFant();
		makeBtnHist();
		makeBtnHor();
		makeBtnPol();
		makeBtnRom();
		makeBtnSci();
	}

		/**
		 * Create a button to search by the adventure genre
		 */
		private void makeBtnAdv() 
		{
			JButton btnAdv = new JButton("Adventure");
			btnAdv.setBackground(Color.WHITE);
			btnAdv.setForeground(Color.BLACK); 
			btnAdv.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnAdv);
			btnAdv.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Adventure")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by the biography genre
		 */
		private void makeBtnBio() 
		{
			JButton btnBio = new JButton("Biography");
			btnBio.setBackground(Color.WHITE);
			btnBio.setForeground(Color.BLACK); 
			btnBio.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnBio);
			btnBio.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Biography")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by the children's genre
		 */
		private void makeBtnChild() 
		{
			JButton btnChild = new JButton("Children's");
			btnChild.setBackground(Color.WHITE);
			btnChild.setForeground(Color.BLACK); 
			btnChild.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnChild);
			btnChild.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Childrens")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by the fantasy genre
		 */
		private void makeBtnFant() 
		{
			JButton btnFant = new JButton("Fantasy");
			btnFant.setBackground(Color.WHITE);
			btnFant.setForeground(Color.BLACK); 
			btnFant.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnFant);
			btnFant.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Fantasy")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by the historical-fiction genre
		 */
		private void makeBtnHist() 
		{
			JButton btnHist = new JButton("Historical Fiction");
			btnHist.setBackground(Color.WHITE);
			btnHist.setForeground(Color.BLACK); 
			btnHist.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnHist);
			btnHist.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Hist-Fi")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by the horror genre
		 */
		private void makeBtnHor() 
		{
			JButton btnHor = new JButton("Horror");
			btnHor.setBackground(Color.WHITE);
			btnHor.setForeground(Color.BLACK); 
			btnHor.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnHor);
			btnHor.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Horror")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by the political-fiction genre
		 */
		private void makeBtnPol() 
		{
			JButton btnPol = new JButton("Political Fiction");
			btnPol.setBackground(Color.WHITE);
			btnPol.setForeground(Color.BLACK); 
			btnPol.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnPol);
			btnPol.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Pol-Fi")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}

		/**
		 * Create a button to search by the romance genre
		 */
		private void makeBtnRom() 
		{
			JButton btnRom = new JButton("Romance");
			btnRom.setBackground(Color.WHITE);
			btnRom.setForeground(Color.BLACK); 
			btnRom.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnRom);
			btnRom.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Romance")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
		/**
		 * Create a button to search by the science-fiction genre
		 */
		private void makeBtnSci() 
		{
			JButton btnSci = new JButton("Science Fiction");
			btnSci.setBackground(Color.WHITE);
			btnSci.setForeground(Color.BLACK); 
			btnSci.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchGenrePanel.add(btnSci);
			btnSci.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByGenre("Sci-Fi")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
	// - Search publisher panel methods - - - - - - - - - - - - - - - - - - - - - - - - -
	/**
	 * Creates the search by publisher panel which has buttons to search by each
	 * publisher in the database
	 */
		private void makeSearchPublisherPanel() 
	{
		searchPublisherPanel = new JPanel();
		searchPublisherPanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchPublisherPanel.setVisible(false); 
		searchPublisherPanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchPublisherPanel.setBackground(Color.BLACK);
		
		makeBloomsburyBtn();		
		makeHarmonyBtn();	
		makeHarperCollinsBtn();	
		makeRandomHouseBtn();	
		makeScholasticBtn();	
		makeScribnerBtn();	
		makeSterlingBtn();
	}

		/**
		 * Create a button to search for records with a publisher of Bloomsbury
		 */
		private void makeBloomsburyBtn() 
		{
			JButton BloomsburyBtn = new JButton("Bloomsbury");	
			BloomsburyBtn.setBackground(Color.WHITE);
			BloomsburyBtn.setForeground(Color.BLACK); 
			BloomsburyBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPublisherPanel.add(BloomsburyBtn);
			BloomsburyBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPublisher("Bloomsbury")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}

		/**
		 * Create a button to search for records with a publisher of Harmony
		 */
		private void makeHarmonyBtn() 
		{
			JButton HarmonyBtn = new JButton("Harmony");
			HarmonyBtn.setBackground(Color.WHITE);
			HarmonyBtn.setForeground(Color.BLACK); 
			HarmonyBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPublisherPanel.add(HarmonyBtn);
			HarmonyBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPublisher("Harmony")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}

		/**
		 * Create a button to search for records with a publisher of HarperCollins
		 */
		private void makeHarperCollinsBtn() 
		{
			JButton HarperCollinsBtn = new JButton("HarperCollins");
			HarperCollinsBtn.setBackground(Color.WHITE);
			HarperCollinsBtn.setForeground(Color.BLACK); 
			HarperCollinsBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPublisherPanel.add(HarperCollinsBtn);
			HarperCollinsBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPublisher("HarperCollins")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}

		/**
		 * Create a button to search for records with a publisher of Random House
		 */
		private void makeRandomHouseBtn() 
		{
			JButton RandomHouseBtn = new JButton("Random House");
			RandomHouseBtn.setBackground(Color.WHITE);
			RandomHouseBtn.setForeground(Color.BLACK); 
			RandomHouseBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPublisherPanel.add(RandomHouseBtn);
			RandomHouseBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPublisher("Random House")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}

		/**
		 * Create a button to search for records with a publisher of Scholastic
		 */
		private void makeScholasticBtn() 
		{
			JButton ScholasticBtn = new JButton("Scholastic");	
			ScholasticBtn.setBackground(Color.WHITE);
			ScholasticBtn.setForeground(Color.BLACK); 
			ScholasticBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPublisherPanel.add(ScholasticBtn);
			ScholasticBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPublisher("Scholastic")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}

		/**
		 * Create a button to search for records with a publisher of Scribner
		 */
		private void makeScribnerBtn() 
		{
			JButton ScribnerBtn = new JButton("Scribner");
			ScribnerBtn.setBackground(Color.WHITE);
			ScribnerBtn.setForeground(Color.BLACK); 
			ScribnerBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPublisherPanel.add(ScribnerBtn);
			ScribnerBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPublisher("Scribner")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}

		/**
		 * Create a button to search for records with a publisher of Sterling
		 */
		private void makeSterlingBtn() 
		{
			JButton SterlingBtn = new JButton("Sterling");	
			SterlingBtn.setBackground(Color.WHITE);
			SterlingBtn.setForeground(Color.BLACK); 
			SterlingBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			searchPublisherPanel.add(SterlingBtn);
			SterlingBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					try 
					{
						executeQueries(Queries.querySearchByPublisher("Sterling")); 
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
		}
	
	// - Search full title panel methods - - - - - - - - - - - - - - - - - - - - - - - - -
	/**
	 * Creates the search by full title panel which has an input for user selection and a
	 * submit button
	 */
	private void makeSearchFullTitlePanel() 
	{
		searchFullTitlePanel = new JPanel();
		searchFullTitlePanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchFullTitlePanel.setVisible(false);
		searchFullTitlePanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchFullTitlePanel.setBackground(Color.BLACK);
		
		makeLblTitle();
		JTextField inputTitle = makeTextInputTitle();
		makeSubmitFullBtn(inputTitle);
	}

		/**
		 * Creates a label which prompts a user to enter their selection
		 */
		private void makeLblTitle() 
		{
			JLabel title = new JLabel();
			title.setText("Enter full book title: ");
			title.setForeground(Color.WHITE);
			title.setFont(new Font("Tahoma", Font.BOLD, 20));
			searchFullTitlePanel.add(title);
		}
	
		/**
		 * Creates a text bow where a user can enter their selection
		 * @return
		 */
		private JTextField makeTextInputTitle() 
		{
			JTextField inputTitle = new JTextField();
			searchFullTitlePanel.add(inputTitle);
			return inputTitle;
		}
	
		/**
		 * Creates a submit button
		 * @param inputTitle
		 */
		private void makeSubmitFullBtn(JTextField inputTitle) 
		{
			JButton submitFullBtn = new JButton("submit"); 
			submitFullBtn.setBackground(Color.WHITE);
			submitFullBtn.setForeground(Color.BLACK); 
			submitFullBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			submitFullBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					input = inputTitle.getText();
					try 
					{
						executeQueries(Queries.querySearchByTitle(input));
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
			searchFullTitlePanel.add(submitFullBtn);
		}
	
	// - Search keyword panel methods - - - - - - - - - - - - - - - - 
	/** Creates the search by keyword panel which has an input for user selection and a
	* submit button
	*/
	private void makeSearchKeywordPanel() 
	{
		searchKeywordPanel = new JPanel();
		searchKeywordPanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchKeywordPanel.setVisible(false);
		searchKeywordPanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchKeywordPanel.setBackground(Color.BLACK);
		
		makeLblKeyword();
		JTextField inputKeyword = makeTxtInputKeyword();
		makeSubmitKeywordBtn(inputKeyword);
	}

		/**
		 * Creates a label to prompt the user to enter their selection
		 */
		private void makeLblKeyword() 
		{
			JLabel keyword = new JLabel();
			keyword.setText("Enter the keyword: ");
			keyword.setForeground(Color.WHITE);
			keyword.setFont(new Font("Tahoma", Font.BOLD, 20));
			searchKeywordPanel.add(keyword);
		}
	
		/**
		 * Creates a text bow where the user can enter their selection
		 * @return
		 */
		private JTextField makeTxtInputKeyword() 
		{
			JTextField inputKeyword = new JTextField();
			searchKeywordPanel.add(inputKeyword);
			return inputKeyword;
		}
	
		/**
		 * creates a submit button
		 * @param inputKeyword
		 */
		private void makeSubmitKeywordBtn(JTextField inputKeyword) 
		{
			JButton submitKeywordBtn = new JButton("submit"); 
			submitKeywordBtn.setBackground(Color.WHITE);
			submitKeywordBtn.setForeground(Color.BLACK); 
			submitKeywordBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			submitKeywordBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					input = inputKeyword.getText();
					try 
					{
						executeQueries(Queries.querySearchByKeyword(input));
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
			searchKeywordPanel.add(submitKeywordBtn);
		}
	
	// make search author first panel - - - - - - - - - - - - - - - - - - - 	
	/** Creates the search by author's first panel which has an input for user selection and a
	* submit button
	*/
	private void makeSearchAuthorFirstPanel() 
	{
		searchAuthorFirstPanel = new JPanel();
		searchAuthorFirstPanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchAuthorFirstPanel.setVisible(false);
		searchAuthorFirstPanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchAuthorFirstPanel.setBackground(Color.BLACK);
		
		makeLblFirst();
		JTextField inputFirst = makeTxtInputFirst();
		makeSubmitFirstBtn(inputFirst);
	}

		/**
		 * Creates a label which prompts the user to enter their selection
		 */
		private void makeLblFirst() 
		{
			JLabel first = new JLabel();
			first.setText("Author's first name: ");
			first.setForeground(Color.WHITE);
			first.setFont(new Font("Tahoma", Font.BOLD, 20));
			searchAuthorFirstPanel.add(first);
		}
	
		/**
		 * Creates a text box where the user can enter their selection
		 * @return
		 */
		private JTextField makeTxtInputFirst() 
		{
			JTextField inputFirst = new JTextField();
			searchAuthorFirstPanel.add(inputFirst);
			return inputFirst;
		}
	
		/**
		 * Creates a submit button
		 * @param inputFirst
		 */
		private void makeSubmitFirstBtn(JTextField inputFirst) 
		{
			JButton submitFirstBtn = new JButton("submit"); 
			submitFirstBtn.setBackground(Color.WHITE);
			submitFirstBtn.setForeground(Color.BLACK); 
			submitFirstBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			submitFirstBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					input = inputFirst.getText();
					try 
					{
						executeQueries(Queries.querySearchByAuthorFirst(input));
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
			searchAuthorFirstPanel.add(submitFirstBtn);
		}
	
	// - make search author last panel - - - - - - - - - - - - - - - - - - -
	/** Creates the search by author's last name panel which has an input for user selection and a
	* submit button
	*/	
	private void makeSearchAuthorLastPanel() 
	{
		searchAuthorLastPanel = new JPanel();
		searchAuthorLastPanel.setLayout(new GridLayout(10, 1, 0, 0));
		searchAuthorLastPanel.setVisible(false);
		searchAuthorLastPanel.setBorder(new EmptyBorder(20, 0, 20, 20));
		searchAuthorLastPanel.setBackground(Color.BLACK);
		
		makeLblLast();
		JTextField inputLast = makeTxtInputLast();
		makeSubmitLastBtn(inputLast);
	}

		/**
		 * Creates a label which prompts the user for input
		 */
		private void makeLblLast() 
		{
			JLabel last = new JLabel();
			last.setText("Author's last name: ");
			last.setForeground(Color.WHITE);
			last.setFont(new Font("Tahoma", Font.BOLD, 20));
			searchAuthorLastPanel.add(last);
		}
	
		/**
		 * Creates a text box where a user can enter their input
		 * @return
		 */
		private JTextField makeTxtInputLast() 
		{
			JTextField inputLast = new JTextField();
			searchAuthorLastPanel.add(inputLast);
			return inputLast;
		}
	
		/**
		 * creates a submit button
		 * @param inputLast
		 */
		private void makeSubmitLastBtn(JTextField inputLast) 
		{
			JButton submitLastBtn = new JButton("submit"); 
			submitLastBtn.setBackground(Color.WHITE);
			submitLastBtn.setForeground(Color.BLACK); 
			submitLastBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
			submitLastBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					input = inputLast.getText();
					try 
					{
						executeQueries(Queries.querySearchByAuthorLast(input));
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} 
				}
			});
			searchAuthorLastPanel.add(submitLastBtn);
		}
	
	
	// Database Methods - - - - - - - - - - - - - - - - - - - - - - -
	/**
	 * Creates and enters records into all tables in the database
	 * @throws SQLException
	 */
		private static void createAndFillTables() throws SQLException 
	{
		executeSqlStatement(BookSql.createTable(), BookSql.insertIntoTable());	
		executeSqlStatement(AuthorSql.createTable(), AuthorSql.insertIntoTable());
		executeSqlStatement(BookToAuthorSql.createTable(), BookToAuthorSql.insertIntoTable());
	}
	
	/**
	 * Deletes all tables from the database
	 * @throws SQLException
	 */
	private static void dropAllTables() throws SQLException 
	{
		executeSqlStatement(BookSql.dropTable());
		executeSqlStatement(AuthorSql.dropTable());
		executeSqlStatement(BookToAuthorSql.dropTable());
	}
	
	/**
	 * Executes a SQL statement
	 * @param sqlStatements
	 * @throws SQLException
	 */
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
	
	/**
	 * Executes a SQL query
	 * @param sqlQueries
	 * @throws SQLException
	 */
	private static void executeQueries(String... sqlQueries) throws SQLException 
	{
		try(Connection connection = DriverManager.getConnection("jdbc:derby:bookStore");
				Statement statement = connection.createStatement();) 
		{
			for (String query : sqlQueries)
			{
				ResultSet results = statement.executeQuery(query);			
				printData(results);
			}
		}	
	}
	
	/** 
	 * Prints out data from the database to the center display in the app
	 * @param results
	 * @throws SQLException
	 */
	private static void printData(ResultSet results) throws SQLException 
	{
		ResultSetMetaData metaData = results.getMetaData();
		int columnCount = metaData.getColumnCount();
		String output = "";
		
		printHeader(metaData);
		while (results.next())
		{
			for (int i = 1; i <= columnCount; i++)
			{
				output = output + String.format("%-" + metaData.getColumnLabel(i).length() +"s	",
					results.getObject(i) );
			}
			output = output + "\n";
		}
		txt.append(output);
	}

	/**
	 * Prints out the header of each column to the main display in the app
	 * @param metaData
	 * @return
	 * @throws SQLException
	 */
	private static int printHeader(ResultSetMetaData metaData) throws SQLException 
	{
		int columnCount = metaData.getColumnCount();
		String output = "";
		
		//print column headers
		for(int i = 1; i <= columnCount; i++)
		{
			output = output + String.format(metaData.getColumnLabel(i) + "	");
		}
		output = output + "\n";
		
		//print dashed line
		for (int i = 1; i <= columnCount; i++)
		{
			for (int j = 0; j < metaData.getColumnLabel(i).length() * 2; j++) 
			{
				output = output + "-";
			}
			if (i != columnCount)
					output = output + "--";
		}
		output = output + "\n";
		
		txt.setText(output);
		return columnCount;
	}

}


