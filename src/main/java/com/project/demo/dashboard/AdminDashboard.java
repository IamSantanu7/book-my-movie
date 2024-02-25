package com.project.demo.dashboard;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
import org.hibernate.SessionFactory;

import com.project.demo.dao.BookingsDao;
import com.project.demo.dao.MovieTheaterMappingDao;
import com.project.demo.dao.MoviesDao;
import com.project.demo.dao.TheatersDao;
import com.project.demo.dao.impl.BookingsDaoImpl;
import com.project.demo.dao.impl.MovieTheaterMappingDaoImpl;
import com.project.demo.dao.impl.MoviesDaoImpl;
import com.project.demo.dao.impl.ReviewDaoImpliment;
import com.project.demo.dao.impl.TheatersDaoImpl;
import com.project.demo.dao.ReviewDao;
import com.project.demo.entity.Admin;
import com.project.demo.entity.Bookings;
import com.project.demo.entity.Movies;
import com.project.demo.entity.ReviewTable;
import com.project.demo.entity.Theaters;
import com.project.demo.utility.HibernateUtil;


/*
 * Santanu
 */

public class AdminDashboard {
	
	public static final Scanner sc = new Scanner(System.in);
	
	public static MoviesDao moviesDao = new MoviesDaoImpl(HibernateUtil.getSessionFactory());
	public static TheatersDao theatersDao = new TheatersDaoImpl(HibernateUtil.getSessionFactory());
	public static MovieTheaterMappingDao movieTheaterMappingDao = new MovieTheaterMappingDaoImpl(HibernateUtil.getSessionFactory());
	public static BookingsDao bookingsDao = new BookingsDaoImpl(HibernateUtil.getSessionFactory());
	
	public static final String[] AVAILABLE_SLOTS = {"09:00 AM", "12:30 PM", "04:00 PM", "07:30 PM"};
	
	//private Session session;
	private Admin admin;
	//private MoviesDao moviesDao;
	
	public AdminDashboard(Admin admin) {
		this.admin = admin;
	}
	
	public void showAdminDashboard() {
	    int choice;
	    while (true) {
	        System.out.println("Welcome, " + admin.getAname() + "!");
	        System.out.println("1. Add movies\n2. Update movies\n3. Delete movies\n4. Get a specific Movie Details\n5. Get all Movie Details\n6. Add theaters\n7. Update theaters\n8. Delete theaters\n9. Get all Theater Details\n10. Assign a Movie to a theater\n11. Get Movies by Theater Id\n12. Get Movies by Theater Name\n13. Get Theaters By Movie Id\n14. Get Theaters By Movie Name\n15. Update payment status\n16. View Bookings\n17. Approve Bookings\n18. View all Ratings\n19. View all Ratings of a Movie\n20. Delete a Movie Rating\n21. Update Movie Review Status\n22. Logout");

	        try {
	            System.out.println("Enter your choice: ");
	            choice = sc.nextInt();
	            sc.nextLine(); // Consume the newline character

	            switch (choice) {
	                case 1:
	                    addMovies();
	                    break;
	                case 2:
	                    updateMovies();
	                    break;
	                case 3:
	                    deleteMovies();
	                    break;
	                case 4:
	                    getAMovieDetails();
	                    break;
	                case 5:
	                    getAllMovieDetails();
	                    break;
	                case 6:
	                    addTheaters();
	                    break;
	                case 7:
	                    updateTheaters();
	                    break;
	                case 8:
	                    deleteTheaters();
	                    break;
	                case 9:
	                    getAllTheaterDetails();
	                    break;
	                case 10:
	                    addMovieToTheater();
	                    break;
	                case 11:
	                    getMoviesByTheaterId();
	                    break;
	                case 12:
	                    getMoviesByTheaterName();
	                    break;
	                case 13:
	                    getTheatersByMovieId();
	                    break;
	                case 14:
	                    getTheatersByMovieName();
	                    break;
	                case 15:
	                    updatePaymentStatus();
	                    break;
	                case 16:
	                	viewAllBookings();
	                    break;
	                case 17:
	                	approveBooking();
	                    break;
	                case 18:
	                	viewAllRatings();
	                	break;
	                case 19:
	                	viewAllRatingsOfAMovie();
	                	break;
	                case 20:
	                	deleteMovieRating();
	                	break;
	                case 21:
	                	updateMovieReviewStatus();
						break;
	                case 22:
	                    System.out.println("Exited from Admin Section Successfully...");
	                    return;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	                    break;
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a valid integer.");
	            sc.nextLine(); // Consume the invalid input
	        }
	    }
	}



	private static void addMovies() {
		Movies movie = new Movies();
		
		System.out.print("Enter movie title: ");
		String movName = sc.nextLine();
		movie.setMovname(movName);
		System.out.print("Enter movie genre: ");
		String movGenre = sc.nextLine();
		movie.setMovgenre(movGenre);
		System.out.print("Enter movie language: ");
		String movLang = sc.nextLine();
		movie.setMovlang(movLang);
		System.out.print("Enter movie duration: ");
		String movDuration = sc.nextLine();
		movie.setMovDuration(movDuration);
		System.out.println(movie);
		boolean isMovieAdded = moviesDao.addMovie(movie);
	    if (isMovieAdded) {
	        System.out.println("Record of movie saved successfully");
	    } else {
	        System.out.println("Something went wrong while adding the movie.");
	    }
	}
	
	private static void updateMovies() {
	    System.out.print("Enter movie title to update: ");
	    String movname = sc.nextLine();
	    
	    // Assuming you want to update genre, language, and duration for simplicity
	    System.out.print("Enter new movie genre: ");
	    String newGenre = sc.nextLine();
	    System.out.print("Enter new movie language: ");
	    String newLang = sc.nextLine();
	    System.out.print("Enter movie duration: ");
		String movDuration = sc.nextLine();

	    Movies updatedMovie = new Movies();
	    updatedMovie.setMovname(movname);
	    updatedMovie.setMovgenre(newGenre);
	    updatedMovie.setMovlang(newLang);
	    updatedMovie.setMovDuration(movDuration);

	    boolean isMovieUpdated = moviesDao.updateMovie(updatedMovie);

	    if (isMovieUpdated) {
	        System.out.println("Record of movie updated successfully");
	    } else {
	        System.out.println("Something went wrong while updating the movie.");
	    }
	}
	
	private static void deleteMovies() {
	    System.out.print("Enter movie title to delete: ");
	    String movname = sc.nextLine();

	    boolean isMovieDeleted = moviesDao.deleteMovie(movname);

	    if (isMovieDeleted) {
	        System.out.println("Record of movie deleted successfully");
	    } else {
	        System.out.println("Something went wrong while deleting the movie.");
	    }
	}

	private static void getAMovieDetails() {
	    System.out.print("Enter movie title to get details: ");
	    String movname = sc.nextLine();
	    
	    boolean isMovieFound = moviesDao.getAMovieDetails(movname);
		
	    if (isMovieFound) {
	        System.out.println("Movie details has been found.");
	    } else {
	        System.out.println("Something went wrong while fetching the movie.");
	    }
	}
		
	private static void getAllMovieDetails() {
		System.out.println("All the movies and their details: ");
		System.out.println("----------------------------------------------------------------------------------");
		
	    boolean isMovieListFound = moviesDao.getAllMovieDetails();
	    
	    if (isMovieListFound) {
	        System.out.println("All movie details has been listed one after one.");
	    } else {
	        System.out.println("Movie list is empty.");
	    }
	}


	
	private static void addTheaters() {
		Theaters theater = new Theaters();
		
		System.out.print("Enter theater name: ");
		String theatername = sc.nextLine();
		theater.setTheatername(theatername);
		System.out.print("Enter theater location: ");
		String theaterlocation = sc.nextLine();
		theater.setTheaterlocation(theaterlocation);
		
		System.out.println("Available Slots:");
        for (int i = 0; i < AVAILABLE_SLOTS.length; i++) {
            System.out.println((i + 1) + ". " + AVAILABLE_SLOTS[i]);
        }

        System.out.print("Choose slot (1-" + AVAILABLE_SLOTS.length + "): ");
        int slotChoice = sc.nextInt();

        if (slotChoice >= 1 && slotChoice <= AVAILABLE_SLOTS.length) {
        	theater.setSlot(AVAILABLE_SLOTS[slotChoice - 1]);
        } else {
            System.out.println("Invalid slot choice. Using default slot.");
            theater.setSlot("N/A");
        }
		
		System.out.print("Enter ticket price: ");
		Double ticketprice = sc.nextDouble();
		theater.setTicketprice(ticketprice);
		
		System.out.println(theater);
		
		boolean isTheaterAdded = theatersDao.addTheater(theater);
		
	    if (isTheaterAdded) {
	        System.out.println("Record of theater saved successfully");
	    } else {
	        System.out.println("Something went wrong while adding the theater.");
	    }		
	}
	
	private static void updateTheaters() {
		// TODO Auto-generated method stub
		
	}
	
	private static void deleteTheaters() {
	    System.out.print("Enter theater name to delete: ");
	    String theatername = sc.nextLine();

		boolean isTheaterDeleted = theatersDao.deleteTheater(theatername);

	    if (isTheaterDeleted) {
	        System.out.println("Record of theater deleted successfully");
	    } else {
	        System.out.println("Something went wrong while deleting the theater.");
	    }	
	}
		
	private static void getAllTheaterDetails() {
		System.out.println("All the theaters and their details: ");
		System.out.println("----------------------------------------------------------------------------------");
		
		boolean isTheaterListFound = theatersDao.getAllTheaterDetails();
	    
	    if (isTheaterListFound) {
	        System.out.println("All theater details has been listed one after one.");
	    } else {
	        System.out.println("Theater list is empty.");
	    }
		
	}


	
	private static void addMovieToTheater() {
		
		System.out.print("Enter movie id: ");
		long movid = sc.nextLong();
		
		System.out.print("Enter theater id: ");
		long theaterid = sc.nextLong();
		
		boolean isMovieAssignDone = movieTheaterMappingDao.addMovieToTheater(movid, theaterid);
		
		if(isMovieAssignDone) {
			System.out.println("Done");
		}else {
			System.out.println("Not Done");
		}
		
	}
	
	private static void getMoviesByTheaterId() {

		System.out.print("Enter theater id: ");
		long theaterid = sc.nextLong();
		
		List<String> movieList = movieTheaterMappingDao.getMoviesByTheaterId(theaterid);
		
		if(movieList.size() > 0) {
			System.out.println("Done");
			System.out.println("Movies associated with theater id "+theaterid+" are listed below-");
			System.out.println(movieList);
		}else {
			System.out.println("Not done, Either theaterid is incorrect or there is No Movies Running at this theater");
		}
		
	}
		
	private static void getMoviesByTheaterName() {
		
		System.out.print("Enter theater name: ");
		String theatername = sc.nextLine();
		
		List<String> movieList = movieTheaterMappingDao.getMoviesByTheaterName(theatername);
		
		if(movieList.size() > 0) {
			System.out.println("Done");
			System.out.println("Movies associated with theater id "+theatername+" are listed below-");
			System.out.println(movieList);
		}else {
			System.out.println("Not done, Either the name of the theater is incorrect or there is No Movies Running at this theater");
		}
		
	}
	
	private static void getTheatersByMovieId() {
		
		System.out.print("Enter movie id: ");
		long movid = sc.nextLong();
		
		List<String> theaterList = movieTheaterMappingDao.getTheatersByMovieId(movid);
		
		if(theaterList.size() > 0) {
			System.out.println("Done");
			System.out.println("Theaters associated with movie id "+movid+" are listed below-");
			System.out.println(theaterList);
		}else {
			System.out.println("Not done, Either movie id is incorrect or there is No Theaters where the Movie is Running");
		}
	}
		
	private static void getTheatersByMovieName() {
		
		System.out.print("Enter movie title: ");
	    String movname = sc.nextLine();
	    
	    List<String> theaterList = movieTheaterMappingDao.getTheatersByMovieName(movname);
		
		if(theaterList.size() > 0) {
			System.out.println("Done");
			System.out.println("Theaters associated with movie name "+movname+" are listed below-");
			System.out.println(theaterList);
		}else {
			System.out.println("Not done, Either name of the movie is incorrect or there is No Theaters where the Movie is Running");
		}
	}

	
	private void viewAllBookings() {
		
		List<Bookings> booking_list = bookingsDao.getAllBookings();
		
		System.out.println("All the booking details: ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-20s | %-20s | %-15s | %-12s | %-20s | %-20s | %-20s | %-12s | %-15s |\n", "Booking Id", "User Name", "Email-Id", "Movie Name", "Theater Name", "Theater Time Slot", "No of Ticket", "Total Amount", "Payment Status");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (Bookings booking : booking_list) {
			System.out.printf("| %-20s | %-20s | %-15s | %-12s | %-20s | %-20s | %-20s | %-12s | %-15s |\n",
					booking.getId(), 
					booking.getUserName(),
					booking.getUserEmail(),
					booking.getMovieName(),
					booking.getTheaterName(),
					booking.getTheater().getSlot(), 
					booking.getNoOfPerson(),
					booking.getTotalAmount(),
					booking.getPaymentStatus());
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		/*if (isBookingListFound) {
	        System.out.println("All booking details has been listed one after one.");
	    } else {
	        System.out.println("No bookings found.");
	    }*/
	}
	
	private void approveBooking() {
		
		System.out.print("Enter the Booking ID to approve: ");
        long bookingId = sc.nextLong();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        BookingsDao bookingDao = new BookingsDaoImpl(sessionFactory);
        Bookings booking = bookingDao.getBookingById(bookingId);

        if (booking != null && booking.getPaymentStatus().equals("Pending")) {
            booking.setPaymentStatus("Approved");
            bookingDao.updateBooking(booking);
            System.out.println("Booking Approved successfully!");
        } else {
            System.out.println("Invalid Booking ID or Booking is already approved.");
        }
		
	}
	

	private static void updatePaymentStatus() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void viewAllRatings() {
		ReviewDao reviewDao = new ReviewDaoImpliment();
		List<ReviewTable> seeAllRating = reviewDao.viewAllRating();
		System.out.println("\nAll Ratings : ");
		if (seeAllRating.isEmpty()) {
			System.out.println("\nNo Ratings Available\n");
		} else {
			System.out.println("\n");
			System.out.println("-------------------------------------------------------------------------------------------------------");
			System.out.printf("| %-20s | %-20s | %-15s | %-12s | %-20s |\n", "User Name", "Email-Id", "Movie Name", "Rating", "Review");
			System.out.println("-------------------------------------------------------------------------------------------------------");
			for (ReviewTable reviewTable : seeAllRating) {
				if (reviewTable.getReview() == null) {
					System.out.printf("| %-20s | %-20s | %-15s | %-12s | %-20s |\n", 
							reviewTable.getuName(),
							reviewTable.getuMail(), 
							reviewTable.getMovName(), 
							reviewTable.getRatingPoints(),
							"No Reviews Available");
				} else {
					System.out.printf("| %-20s | %-20s | %-15s | %-12s | %-20s |\n", 
							reviewTable.getuName(),
							reviewTable.getuMail(), 
							reviewTable.getMovName(), 
							reviewTable.getRatingPoints(),
							reviewTable.getReview());
				}
			}
			System.out.println("-------------------------------------------------------------------------------------------------------");
			System.out.println("\n");
		}
	}
	
	private void viewAllRatingsOfAMovie() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Movie Name : ");
		String moviename = scanner.nextLine();
		ReviewDao reviewDao = new ReviewDaoImpliment();
		Double seeRating = reviewDao.viewAllRatingMovie(moviename);
		List<ReviewTable> seeReview = reviewDao.viewRating(moviename);
		if (seeRating == null) {
			System.out.println("\nPlease Enter Valid Running Movie\n");
		} else {
			if (seeReview.get(0).getReview() == null) {
				System.out.println("\n");
				System.out.println("-----------------------------------------------------------");
				System.out.printf("| %-20s | %-10s | %-20s |\n", "Movie Name", "Rating", "Review");
				System.out.println("-----------------------------------------------------------");
				System.out.printf("| %-20s | %-10s | %-20s |\n", 
						moviename, 
						seeRating, 
						"No Reviews Available");
				System.out.println("-----------------------------------------------------------");
				System.out.println("\n");
			} else {
				System.out.println("\n");
				System.out.println("-----------------------------------------------------------");
				System.out.printf("| %-20s | %-10s | %-20s |\n", "Movie Name", "Rating", "Review");
				System.out.println("-----------------------------------------------------------");
				System.out.printf("| %-20s | %-10s | %-20s |\n", 
						moviename, 
						seeRating, 
						seeReview.get(0).getReview());
				System.out.println("-----------------------------------------------------------");
				System.out.println("\n");
			}
		}
	}

	private void deleteMovieRating() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Movie Name : ");
		String moviename = scanner.nextLine();
		ReviewDao reviewDao = new ReviewDaoImpliment();
		List<ReviewTable> checkMovie = reviewDao.viewRating(moviename);
		if (checkMovie.isEmpty()) {
			System.out.println("\nAlready Deleted or No Such Movie Available\n");
		} else {
			reviewDao.deleteRating(moviename);
			System.out.println("\nMovie Rating or Review Deleted Successfully\n");
		}
	}
	
	private void updateMovieReviewStatus() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Movie Name : ");
		String moviename = scanner.nextLine();
		System.out.print("Enter Review : ");
		String review = scanner.nextLine();
		ReviewDao reviewDao = new ReviewDaoImpliment();
		List<ReviewTable> checkMovie = reviewDao.viewRating(moviename);
		if (checkMovie.isEmpty()) {
			System.out.println("\nNo Such Movie Available\n");
		} else {
			reviewDao.updateReview(moviename, review);
			System.out.println("\nMovie Review Updated Successfully\n");
		}
	}

}

