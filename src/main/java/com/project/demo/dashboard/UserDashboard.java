package com.project.demo.dashboard;

import java.util.Scanner;

import org.hibernate.SessionFactory;


import com.project.demo.dao.BookingsDao;
import com.project.demo.dao.TheatersDao;
import com.project.demo.dao.impl.BookingsDaoImpl;
import com.project.demo.dao.impl.ReviewDaoImpliment;
import com.project.demo.dao.impl.SearchMovieTheatreDaoImpliment;
import com.project.demo.dao.impl.TheatersDaoImpl;
import com.project.demo.dao.SearchMovieTheatreDao;
import com.project.demo.dao.ReviewDao;
import com.project.demo.entity.Bookings;
import com.project.demo.entity.Movies;
import com.project.demo.entity.ReviewTable;
import com.project.demo.entity.Theaters;
import com.project.demo.entity.User;
import com.project.demo.utility.HibernateUtil;

import java.util.List;
import java.util.Objects;

public class UserDashboard {
	public static final Scanner scanner = new Scanner(System.in);
	
	static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    //static BookingsDao bookingsDao = new BookingsDaoImpl(sessionFactory);
    
    

    public static void showUserDashboard(User user) {
        System.out.println("Welcome, " + user.getUname() + "!");
        int userOption;

        do {
            System.out.println("User Dashboard:");
            System.out.println("1. Search Movies");
            System.out.println("2. Search theater");
            System.out.println("3. Request Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Bookings");
            System.out.println("6. Rate A Movie");
            System.out.println("7. View Rating");
            System.out.println("8. Logout");

            System.out.print("Enter your choice: ");
			userOption = scanner.nextInt();
			
			scanner.nextLine();

            switch (userOption) {
            
	            case 1:
	            	searchByMovies();
	                break;
	            case 2:
	            	searchByTheaters();
                    break;
                case 3:
                    requestBooking(user);
                    break;

                case 4:
                	String userEmail;
                	System.out.print("Enter your Email: ");
                	userEmail = scanner.next();
                	viewBookings(userEmail);
                    break;

                case 5:
                	cancelBooking(user);
                    break;
                    
               case 6:
					rateMovie(user);
					break;
					
				case 7:
					viewUserRating();
					break;

                case 8:
                    System.out.println("Logging out from User Panel.");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (userOption != 8);
    }

	private static void viewUserRating() {
    	Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Email-id : ");
		String email = scanner.nextLine();
		ReviewDao reviewDao = new ReviewDaoImpliment();
		List<ReviewTable> seeRating = reviewDao.viewMyRating(email);
		if (seeRating.isEmpty()) {
			System.out.println("Please Enter Valid Running Movie OR Invalid Email-Id");
			return;
		} else {
			if (seeRating.get(0).getReview() == null) {
				System.out.println("\n");
				System.out.println("----------------------------------------------------------------------------");
				System.out.printf("| %-20s | %-20s | %-15s | %-8s |\n", "User Name", "Email-Id", "Movie Name", "Rating");
				System.out.println("----------------------------------------------------------------------------");
				for (ReviewTable reviewTable : seeRating) {
					System.out.printf("| %-20s | %-20s | %-15s | %-8s |\n", 
							reviewTable.getuName(),
							reviewTable.getuMail(), 
							reviewTable.getMovName(), 
							reviewTable.getRatingPoints());
				}
				System.out.println("----------------------------------------------------------------------------");
				System.out.println("\n");
			} else {
				System.out.println("\n");
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.printf("| %-20s | %-20s | %-15s | %-8s | %-15s |\n", "User Name", "Email-Id", "Movie Name", "Rating", "Review");
				System.out.println("---------------------------------------------------------------------------------------------");
				for (ReviewTable reviewTable : seeRating) {
					System.out.printf("| %-20s | %-20s | %-15s | %-8s | %-15s |\n", 
							reviewTable.getuName(),
							reviewTable.getuMail(), 
							reviewTable.getMovName(), 
							reviewTable.getRatingPoints(),
							reviewTable.getReview());
				}
				System.out.println("---------------------------------------------------------------------------------------------");
				System.out.println("\n");
			}
			
		}
	}

	private static void rateMovie(User user) {
    	ReviewTable reviewTable = new ReviewTable();
    	System.out.print("Enter your name : ");
    	String username = scanner.nextLine();
    	reviewTable.setuName(username);
    	System.out.print("Enter your email : ");
    	String useremail = scanner.nextLine();
    	reviewTable.setuMail(useremail);
    	System.out.print("Enter the name of the movie: ");
    	String moviename = scanner.next();
    	reviewTable.setMovName(moviename);
    	System.out.print("Enter the rating out of 10: ");
    	Double rating = scanner.nextDouble();
    	reviewTable.setRatingPoints(rating);
        ReviewDao reviewDao = new ReviewDaoImpliment();
        List<Movies> movieDetails = reviewDao.checkValidMovie(moviename);
        List<ReviewTable> checkReview = reviewDao.viewRating(moviename);
        if (movieDetails.isEmpty()) {
			System.out.println("\nPlease Enter Valid Running Movie\n");
			return;
//		} else if (checkReview.get(0).getReview() != null) {
//            String review = checkReview.get(0).getReview();
//            reviewTable.setReview(review);
//            reviewDao.saveRating(reviewTable);
        } else {
			reviewDao.saveRating(reviewTable);
		}
	}

	private static void searchByMovies() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Movie Name : ");
		String moviename = sc.nextLine();
		
		SearchMovieTheatreDao searchMovieTheatreDao = new SearchMovieTheatreDaoImpliment();
		List<Movies> movieDetails = searchMovieTheatreDao.searchMovie(moviename);
		List<Theaters> theatreDetails = searchMovieTheatreDao.searchTheatreByMovie(moviename);
		
		if (movieDetails.isEmpty()) {
			System.out.println("\nPlease Enter Valid Running Movie\n");
			return;
		} else {
			System.out.println("\nMovie Details");
			System.out.println("---------------");
			System.out.println("Movie Name : " + movieDetails.get(0).getMovname());
			System.out.println("Language : " + movieDetails.get(0).getMovlang());
			System.out.println("Genre : " + movieDetails.get(0).getMovgenre());
			System.out.println("Duration : " + movieDetails.get(0).getMovDuration());
			System.out.println("\n");
			System.out.println("--------------------------------------------------------------------------------");
			System.out.printf("| %-20s | %-20s | %-15s | %-12s |\n", "Theater Name", "Theater Location", "Time Slot", "Ticket Price");
			System.out.println("--------------------------------------------------------------------------------");
			for (Theaters theater_list : theatreDetails) {
			    System.out.printf("| %-20s | %-20s | %-15s | %-12s |\n",
			            theater_list.getTheatername(),
			            theater_list.getTheaterlocation(),
			            theater_list.getSlot(),
			            theater_list.getTicketprice());
			}
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("\n");
		}
	}
	
	private static void searchByTheaters() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Theatre Name : ");
		String theatrename = sc.nextLine();
		SearchMovieTheatreDao searchMovieTheatreDao = new SearchMovieTheatreDaoImpliment();
		List<Theaters> theatreDetails = searchMovieTheatreDao.searchTheatre(theatrename);
		List<Movies> movieDetails = searchMovieTheatreDao.searchMovieByTheatre(theatrename);
		System.out.println(movieDetails);
		if (theatreDetails.isEmpty()) {
			System.out.println("\nPlease Enter Valid Theatre\n");
			return;
		} else {
			System.out.println("\nTheatre Details");
			System.out.println("-----------------");
			System.out.println("Theatre Name : " + theatreDetails.get(0).getTheatername());
			System.out.println("Theatre Location : " + theatreDetails.get(0).getTheaterlocation());
			System.out.println("\n");
			System.out.println("------------------------------------------------------------------------------");
			System.out.printf("| %-20s | %-10s | %-20s | %-15s |\n", "Movie Name", "Language", "Genre", "Duration");
			System.out.println("------------------------------------------------------------------------------");
			for (Movies movie_list : movieDetails) {
				System.out.printf("| %-20s | %-10s | %-20s | %-15s |\n", 
						movie_list.getMovname(),
						movie_list.getMovlang(), 
						movie_list.getMovgenre(), 
						movie_list.getMovDuration());
			}
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("\n");
		}
	}

	public static void requestBooking(User user) {
    	
		System.out.println("Fill the following details-");
	
    	System.out.println("Enter your name : ");
        String username = scanner.nextLine();
        
        System.out.println("Enter your email : ");
        String useremail = scanner.nextLine();
        
        System.out.println("Enter the name of the movie: ");
        String movieTitle = scanner.nextLine();

        System.out.println("Enter the name of the theater: ");
        String theaterName = scanner.nextLine();

        System.out.println("Enter the slot: ");
        String slot = scanner.nextLine();
        
        BookingsDao bookingsDao = new BookingsDaoImpl(sessionFactory);
        String movieDetails = bookingsDao.isMovieAllocatedInTheater(theaterName, slot);
        
        if (Objects.equals(movieDetails, movieTitle)) {    
        	TheatersDao theaterDao = new TheatersDaoImpl(sessionFactory);
	        Theaters theater = theaterDao.getTheaterByName(theaterName, slot);
	        System.out.println("Tcket Price for each Person : "+theater.getTicketprice());
        	System.out.println("Enter no. of Ticket: ");
	        int person = scanner.nextInt();
	        
            if (theater != null) {
                double totalAmount = theater.getTicketprice() * person;

	
		        // Create a new Booking with Pending status
		        Bookings newBooking = new Bookings();
		        newBooking.setUserName(username);
		        newBooking.setUserEmail(useremail);
		        newBooking.setMovieName(movieTitle);
		        newBooking.setTheaterName(theaterName);
		        newBooking.setTheaterTimeSlot(slot);
		        newBooking.setNoOfPerson(person);
		        newBooking.setTotalAmount(totalAmount);
		        newBooking.setPaymentStatus("Pending");
	
	        
		        bookingsDao.saveBooking(newBooking);
	
		        System.out.println("Booking request sent. Booking ID: " + newBooking.getId());
		        System.out.println("Total amount to be paid: $" + totalAmount);
	        } else {
	            System.out.println("Invalid theater.");
	        }
	    } else {
	        System.out.println("Invalid input. The movie is not currently allocated in the specified theater.");
	    }
    }

    public static void viewBookings(String useremail) {
    	BookingsDao bookingsDao = new BookingsDaoImpl(sessionFactory);
        Bookings retrievedBooking =  bookingsDao.getBookingsByUser(useremail);
       
        System.out.println("\nBooking Details\n");
        System.out.println("---------------------------------------------");
        System.out.println("Booking ID: " + retrievedBooking.getId());
        System.out.println("User Name: " + retrievedBooking.getUserName());
        System.out.println("User Email: " + retrievedBooking.getUserEmail());
        System.out.println("Movie Name: " + retrievedBooking.getMovieName());
        System.out.println("Theater Name: " + retrievedBooking.getTheaterName());
        System.out.println("Time Slot: " + retrievedBooking.getTheaterTimeSlot());
        System.out.println("No. of Ticket: " + retrievedBooking.getNoOfPerson());
        double totalAmount = retrievedBooking.getNoOfPerson() * retrievedBooking.getTheater().getTicketprice();
        System.out.println("Total amount paid: " + totalAmount);
        System.out.println("Payment Status: " + retrievedBooking.getPaymentStatus());
        System.out.println("---------------------------------------------");
    }
    public static void cancelBooking(User user) {
        System.out.print("Enter the Booking ID to cancel: ");
        long bookingId = scanner.nextLong();
        BookingsDao bookingsDao = new BookingsDaoImpl(sessionFactory);
        Bookings booking = bookingsDao.getBookingById(bookingId);

        if (booking != null) {
            if ("Approved".equals(booking.getPaymentStatus())) {
                System.out.println("Cannot cancel an approved booking. Contact admin for assistance.");
            } else {
                bookingsDao.deleteBooking(booking);
                System.out.println("Booking canceled successfully!");
            }
        } else {
            System.out.println("Invalid Booking ID or the booking does not belong to the user.");
        }
    }  
}





























/*
import java.util.Scanner;

import org.hibernate.Session;

import com.project.demo.entity.User;
import com.project.demo.utility.HibernateUtil;

public class UserDashboard {

	private User user;
	
	public UserDashboard(User user) {
		this.user = user;
	}
	
	public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome " + user.getUname() + "!");
            System.out.println("1. Recommended movies near you\n2. Book a movie ticket\n3. Update your booking\n4. Cancel a booking\n5. Exit");
            
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    showRecommendedMovies();
                    break;
                case "2":
                    bookMovies();
                    break;
                case "3":
                    updateBooking();
                    break;
                case "4":
                    cancelBooking();
                    break;
                case "5":
                	System.out.println("Exited from User Section Successfully...");
    				sc.close();
    				break;
            }
        }
    }

	private void cancelBooking() {
		// TODO Auto-generated method stub
		
	}

	private void updateBooking() {
		// TODO Auto-generated method stub
		
	}

	private void bookMovies() {
		// TODO Auto-generated method stub
		
	}

	private void showRecommendedMovies() {
		// TODO Auto-generated method stub
		
	}
}
*/