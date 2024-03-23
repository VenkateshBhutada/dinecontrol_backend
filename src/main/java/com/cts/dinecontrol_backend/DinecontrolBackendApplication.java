package com.cts.dinecontrol_backend;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.hibernate.mapping.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.dinecontrol_backend.Service.ManagerService;
import com.cts.dinecontrol_backend.Service.MenuItemService;
import com.cts.dinecontrol_backend.Service.TableReservationService;
import com.cts.dinecontrol_backend.Service.TableTypeService;
import com.cts.dinecontrol_backend.Service.UserService;
import com.cts.dinecontrol_backend.dtolayer.LoginManagerDTO;
import com.cts.dinecontrol_backend.dtolayer.LoginUserDTO;
import com.cts.dinecontrol_backend.dtolayer.ManagerResponseDTO;
import com.cts.dinecontrol_backend.dtolayer.UserResponseDTO;
import com.cts.dinecontrol_backend.models.Manager;
import com.cts.dinecontrol_backend.models.MenuItem;
import com.cts.dinecontrol_backend.models.TableReservation;
import com.cts.dinecontrol_backend.models.TableType;
import com.cts.dinecontrol_backend.models.User;

import lombok.extern.slf4j.Slf4j;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DinecontrolBackendApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DinecontrolBackendApplication.class, args);

        UserService userService = context.getBean(UserService.class);
        ManagerService managerService = context.getBean(ManagerService.class);
        MenuItemService menuItemService = context.getBean(MenuItemService.class);
        TableTypeService tableTypeService = context.getBean(TableTypeService.class);
        TableReservationService reservationService = context.getBean(TableReservationService.class);

        System.out.println("=======================       Welcome to Dine Control!       =======================\n");
        System.out.println("       Get ready to explore our vast Menu and step into our delicious Cusine.");
        System.out.println("                       Now you can Reserve Tables from Home.");
        
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect the option that suits you:");
            System.out.println("1. User");
            System.out.println("2. Manager");
            System.out.println("3. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    userFlow(userService, menuItemService, tableTypeService, reservationService);
                    break;
                case 2:
                    managerFlow(managerService, menuItemService, tableTypeService,reservationService );
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again choosing the correct option.");
            }
        }
    }

    
    //USER LOGIN
    
    
    
    public static void userFlow(UserService userService,MenuItemService menuItemService, TableTypeService tableTypeService, TableReservationService reservationService) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nUser Login:");

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Register as a new user");
            System.out.println("2. Login as an existing user");
            System.out.println("3. Back to main menu");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    registerUser(userService);
                    break;
                case 2:
                	System.out.print("\nEnter your Email: ");
            		String email = scanner.next();
            		
            		System.out.print("Enter your Password: ");
            		String password = scanner.next();
            		
				
				UserResponseDTO response = userService.loginUser(new LoginUserDTO(email, password));
            		
            		if(response.status().value() == 200) {
            			
            			System.out.println(response.message());
            			userLoggedInActions(userService, response, menuItemService, tableTypeService, reservationService);
            		}else {
            			System.out.println(response.status().value()+" "+response.message());
            		}
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again selecting the proper option.");
            }
        }
    }
    
    
    
    
    public static void userLoggedInActions(UserService userService,  UserResponseDTO userResponseDTO, MenuItemService menuItemService, TableTypeService tableTypeService, TableReservationService reservationService) {
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("Users' Options:");
	        System.out.println("1. View Menu");
	        System.out.println("2. Reserve Table");
	        System.out.println("3. Reservation Status");
//	        System.out.println("4. Veg Food");
	        System.out.println("4. Logout");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
//	            	getAllMenuItems(userService, userResponseDTO, menuItemService);
	            	viewMenu(userService, userResponseDTO, menuItemService);
	                break;
	            case 2:
	                bookTable(userResponseDTO, userService, tableTypeService, reservationService);
	                break;
	            case 3: 
	            	reservationStatus( reservationService, userResponseDTO);
	            	break;
//	            case 4:
//	            	getAllMenuItemsVeg(userService, userResponseDTO, menuItemService);
//	            	break;
	            case 4:
	                System.out.println("Logging out.......\n");
	                return;
	            default:
	                System.out.println("Invalid option. Please try again choosing proper option.");
	        }
	    }
	}
    
  private static void viewMenu(UserService userService, UserResponseDTO userResponseDTO,
			MenuItemService menuItemService) {
	  Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("\nMENU OPTION:");
	        System.out.println("1. View All Menu");
	        System.out.println("2. View VEG Menu only");
	        System.out.println("3. View NON-VEG Menu only");
	        
	        System.out.println("4. Back to User Options");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
	            	getAllMenuItems(userService, userResponseDTO, menuItemService);
	                break;
	            case 2:
	            	getAllMenuItemsVeg(userService, userResponseDTO, menuItemService);
	                break;
	            case 3:
	            	getAllMenuItemsNonVeg(userService, userResponseDTO, menuItemService);
	                break;

	            case 4:
	              System.out.println("Logging you out.......\n");
	                return;
	            default:
	                System.out.println("Invalid option. Please try again choosing proper option.");
	        }
	    }
		
		
	}


private static void getAllMenuItemsNonVeg(UserService userService, UserResponseDTO userResponseDTO,
		MenuItemService menuItemService) {
	  List<MenuItem> menuItems = menuItemService.getMenuItemsWithNonVeg();

		if (menuItems.isEmpty()) {
		    System.out.println("No menu items found. Kindly login after some time.");
		} else {
		    System.out.println("\n-------------------------------------           MENU ITEMS           -------------------------------------\n");
		    System.out.printf("%-10s %-20s %-23s %-10s %-15s %-30s\n", "Item ID", "Group Name", "Name", "Price", "Taste", "Photo URL");
		    for (MenuItem menu : menuItems) {
		        System.out.printf("%-10s %-20s %-23s %-10.2f %-15s %-30s\n",
		                menu.getItemId(), menu.getGroupName(), menu.getName(),
		                menu.getPrice(), menu.getTaste(), menu.getPhotoUrl());
		    }
		    System.out.println("\n");
		}
	
}


private static void getAllMenuItemsVeg(UserService userService, UserResponseDTO userResponseDTO,
			MenuItemService menuItemService) {
	  List<MenuItem> menuItems = menuItemService.getMenuItemsWithVeg();

		if (menuItems.isEmpty()) {
		    System.out.println("No menu items found. Kindly login after some time.");
		} else {
		    System.out.println("\n-------------------------------------           MENU ITEMS           -------------------------------------\n");
		    System.out.printf("%-10s %-20s %-23s %-10s %-15s %-30s\n", "Item ID", "Group Name", "Name", "Price", "Taste", "Photo URL");
		    for (MenuItem menu : menuItems) {
		        System.out.printf("%-10s %-20s %-23s %-10.2f %-15s %-30s\n",
		                menu.getItemId(), menu.getGroupName(), menu.getName(),
		                menu.getPrice(), menu.getTaste(), menu.getPhotoUrl());
		    }
		    System.out.println("\n");
		}
		
	}


private static void reservationStatus(TableReservationService reservationService, UserResponseDTO userResponseDTO) {
    	
	  List<TableReservation> reservations = reservationService.getReservationsByUserId(userResponseDTO.userId());

		if (reservations.isEmpty()) {
		    System.out.println("No reservation requests found.");
		} else {
		    System.out.println("\nAll Reservation Requests:\n");
		    System.out.printf("%-5s %-15s %-10s %-13s %-13s %-10s\n", "RId", "User Name", "Table Id", "Date", "Time", "Status");
		    System.out.println("--------------------------------------------------------------------------");
		    for (TableReservation reservation : reservations) {
		        System.out.printf("%-5d %-15s %-10d %-13s %-13s %-10s\n",
		                reservation.getReservationId(), reservation.getUserName(),
		                reservation.getTableId(), reservation.getReservationDate(),
		                reservation.getReservationTime(), reservation.getStatus());
		    }
		}
    	 
		
	}




	private static void getAllMenuItems(UserService userService, UserResponseDTO userResponseDTO,
			MenuItemService menuItemService) {
		
		List<MenuItem> menuItems = menuItemService.getAllMenuItems();

		if (menuItems.isEmpty()) {
		    System.out.println("No menu items found. Kindly login after some time.");
		} else {
		    System.out.println("\n-------------------------------------           MENU ITEMS           -------------------------------------\n");
		    System.out.printf("%-10s %-20s %-23s %-10s %-15s %-30s\n", "Item ID", "Group Name", "Name", "Price", "Taste", "Photo URL");
		    for (MenuItem menu : menuItems) {
		        System.out.printf("%-10s %-20s %-23s %-10.2f %-15s %-30s\n",
		                menu.getItemId(), menu.getGroupName(), menu.getName(),
		                menu.getPrice(), menu.getTaste(), menu.getPhotoUrl());
		    }
		    System.out.println("\n");
		}
		       


//		 List<MenuItem> menuItems = menuItemService.getAllMenuItems();
//
//		    if (menuItems.isEmpty()) {
//		        System.out.println("No menu items found. Kindly login after some time.");
//		    } else {
//		        System.out.println("\n--------------------------------       MENU ITEMS       --------------------------------\n");
//		        for (MenuItem menu : menuItems) {
//		            System.out.println(menu.getItemId()+ "\t"+ menu.getGroupName()+ "\t\t\t"+ menu.getName()+ "\t\t\t"+ menu.getPrice()+ "\t"+ menu.getTaste()+ "\t"+ menu.getPhotoUrl());
//		            
//		        }
//		        System.out.println("\n");
//		    }
//	
	}

	private static void bookTable( UserResponseDTO userResponseDTO, UserService userService, TableTypeService tableTypeService, TableReservationService reservationService) {
		Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("\nBook Table:");
	        System.out.println("1. View Tables");
	        System.out.println("2. Table Reservation");
	        
	        System.out.println("3. Back to User Options");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
	            	viewAllTables(tableTypeService);
	                break;
	            case 2:
	            	reserveTable(reservationService, userResponseDTO);
	                // Implement table management logic
	                break;
//	            case 3:
//	            	viewTableReservation(reservationService, userResponseDTO);
	            case 3:
	              System.out.println("Logging you out.......\n");
	                return;
	            default:
	                System.out.println("Invalid option. Please try again choosing proper option.");
	        }
	    }
		
	}
	
//	private static void viewTableReservation(TableReservationService reservationService,
//			UserResponseDTO userResponseDTO) {
//		
//		
//		
//	}


	private static void reserveTable(TableReservationService reservationService, UserResponseDTO userResponseDTO) {
		 Scanner scanner = new Scanner(System.in);

		    TableReservation reservation = new TableReservation();

		    reservation.setUserId(userResponseDTO.userId());
		    
		    System.out.println("\nEnter Your Name: ");
		    String userName = scanner.next();
		    reservation.setUserName(userName);
		    
		    
		    System.out.println("Enter Table ID: ");
		    int tableId = scanner.nextInt();
		    reservation.setTableId(tableId);
		    
		    Date reservationDateObj = null;
		    boolean isReservationDateTaken = false;
		   while(!isReservationDateTaken) {
			   try {
			    	
			    	System.out.println("Enter Reservation date (DD/MM/YYYY): ");
				    String reservationDate = scanner.next();
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				    reservationDateObj = Date.valueOf(LocalDate.parse(reservationDate, formatter));
				    isReservationDateTaken = true;
				   
				} catch (Exception e) {
					System.out.println("Please enter Reservation Date in mentioned format only");
					
				}
		   }
		    
		    reservation.setReservationDate(reservationDateObj);
		    
		    Time timeObj = null;
		    boolean isTimeTaken = false;
		    
		    while(!isTimeTaken) {
		    	try {
		    		System.out.println("Enter Reservation time HH:MM (24Hrs Format): ");
		    		String time = scanner.next();
		    		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("HH:MM");
		    		
		    		timeObj =  Time.valueOf(LocalTime.parse(time, fomatter));
		    		
		    		isTimeTaken = true;
		    		
					
				} catch (Exception e) {
					System.out.println("Please enter Resevation time in mentioned format only");
				}
		    }
		    reservation.setReservationTime(timeObj);
		    
		    
//
		    reservationService.makeReservation(reservation);
	    System.out.println("\nReservation request sent successfully!");
	    System.out.println("Kindly Check for your Reservation Status after a hour.");
//		
	}


	
	

	

	public static void registerUser(UserService service) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter your Name: ");
        String name = scanner.next();

        System.out.println("Enter your Email: ");
        String email = scanner.next();

        System.out.println("Enter your Password: ");
        String password = scanner.next();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        service.registerUser(user);
        //System.out.println("Great! User registered successfully!");

       
    }
	
	

  



	public static void managerFlow(ManagerService managerService, MenuItemService menuItemService, TableTypeService tableTypeService, TableReservationService reservationService) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nManager Login:");

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Register as a new manager");
            System.out.println("2. Login as an existing manager");
            System.out.println("3. Back to main menu");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                	registerManager(managerService);
                    // Register new manager
                    // Implement registration logic
                    break;
                case 2:
                    // Login as existing manager
                	System.out.print("\nEnter your Email: ");
            		String email = scanner.next();
            		
            		System.out.print("Enter your Password: ");
            		String password = scanner.next();
            		
				//ManagerService service = null;
				ManagerResponseDTO response = managerService.loginManager(new LoginManagerDTO(email, password));
            		
            		if(response.status().value() == 200) {
            			System.out.println(response.message());
            			
						managerLoggedInActions(managerService, response, menuItemService, tableTypeService, reservationService);
            		}else {
            			System.out.println(response.status().value()+" "+response.message());
            		}
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again choosing proper option.");
            }
        }
    }
	 public static void registerManager(ManagerService service) {
	        Scanner scanner = new Scanner(System.in);
	        
	        System.out.println("\nEnter your Name: ");
	        String name = scanner.next();

	        System.out.println("Enter your Email: ");
	        String email = scanner.next();

	        System.out.println("Enter your Password: ");
	        String password = scanner.next();
	        
	        Manager manager = new Manager();
	        manager.setName(name);
	        manager.setEmail(email);
	        manager.setPassword(password);

	        service.registerManager(manager);
	        System.out.println("Great! Manager registered successfully!\n");

	        
	    }
	
	
	
	public static void managerLoggedInActions(ManagerService managerService,  ManagerResponseDTO managerResponseDTO, MenuItemService menuItemService, TableTypeService tableTypeService, TableReservationService reservationService) {
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("Manager Options:");
	        System.out.println("1. Manage Menu");
	        System.out.println("2. Manage Tables");
	        System.out.println("3. Logout");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
	                manageMenu(managerService, managerResponseDTO, menuItemService,  reservationService);
	                break;
	            case 2:
	                manageTables(managerService, tableTypeService, reservationService);
	                break;
	            case 3:
	                System.out.println("Logging out.....\n");
	                return;
	            default:
	                System.out.println("Invalid option. Please try again choosing correct option.");
	        }
	    }
	}
	private static void manageMenu(ManagerService managerService,ManagerResponseDTO managerResponseDTO, MenuItemService menuItemService, TableReservationService reservationService) {
		Scanner scanner = new Scanner(System.in);
		int managerId = -1; 

	    while (true) {
	        System.out.println("\nManager Options:");
	        System.out.println("1. Add Food Item in Menu ");
	        System.out.println("2. Update Food Item in Menu");
	        System.out.println("3. Delete Food Item in Menu");
	        System.out.println("4. View Menu Items");
	        System.out.println("5. Logout");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
				
				addMenuItem(managerService, managerResponseDTO, menuItemService);
	                break;
	            case 2:
	            	 updateMenuItem(managerService,managerResponseDTO, menuItemService);
	                
	                break;
	            case 3:
	            	 deleteMenuItem(menuItemService ,managerResponseDTO, managerService);
	                // Implement delete menu item logic
	                break;
	            case 4:
	            	getAllMenuItems(managerService ,managerResponseDTO, menuItemService);
	                // Implement view menu items logic
	                break;
	            case 5:
	                System.out.println("Logging out.....\n");
	                return;
	            default:
	                System.out.println("Invalid option. Please try again choosing correct option.");
	        }
	    }
	}

	public static void addMenuItem(ManagerService managerService, ManagerResponseDTO managerResponseDTO, MenuItemService menuItemService) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("\nStart Adding Menu Below:");
	    System.out.print("Enter Item Name: ");
	    String name = scanner.nextLine();

	    System.out.print("Enter Group Name: ");
	    String groupName = scanner.next();

	    System.out.print("Enter Price: ");
	    double price = scanner.nextDouble();

	    System.out.print("Enter Taste: ");
	    String taste = scanner.next();

	    System.out.print("Enter Photo URL: ");
	    String photoUrl = scanner.next();

	    MenuItem menuItem = new MenuItem();
	    menuItem.setName(name);
	    menuItem.setGroupName(groupName);
	    menuItem.setPrice(price);
	    menuItem.setTaste(taste);
	    menuItem.setPhotoUrl(photoUrl);
	    menuItem.setManagerid(managerResponseDTO.managerid());

	    menuItemService.addMenuItem(menuItem);
	    System.out.println("Menu item added successfully!\n");
	}
	public static void updateMenuItem(ManagerService managerService, ManagerResponseDTO managerResponseDTO, MenuItemService menuItemService) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter Item ID to update: ");
	    int itemId = scanner.nextInt();

	    // Check if item exists
	    MenuItem existingMenuItem = menuItemService.getMenuItemById(itemId);
	    if (existingMenuItem == null) {
	        System.out.println("Menu item with ID " + itemId + " does not exist.");
	        return;
	    }

	    System.out.println("Enter new Item Name: ");
	    String name = scanner.next();

	    System.out.println("Enter new Group Name: ");
	    String groupName = scanner.next();

	    System.out.println("Enter new Price: ");
	    double price = scanner.nextDouble();

	    System.out.println("Enter new Taste: ");
	    String taste = scanner.next();

	    System.out.println("Enter new Photo URL: ");
	    String photoUrl = scanner.next();

	    MenuItem updatedMenuItem = new MenuItem();
	    updatedMenuItem.setItemId(itemId);
	    updatedMenuItem.setName(name);
	    updatedMenuItem.setGroupName(groupName);
	    updatedMenuItem.setPrice(price);
	    updatedMenuItem.setTaste(taste);
	    updatedMenuItem.setPhotoUrl(photoUrl);

	    menuItemService.updateMenuItem(updatedMenuItem);
	    System.out.println("Menu item updated successfully!");
	}

	public static void deleteMenuItem(MenuItemService menuItemService ,ManagerResponseDTO managerResponseDTO,ManagerService managerService) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter Item ID to delete: ");
	    int itemId = scanner.nextInt();

	    menuItemService.deleteMenuItem(itemId);
	    System.out.println("Menu item deleted successfully!");
	}

	public static void getAllMenuItems(ManagerService managerService, ManagerResponseDTO managerResponseDTO, MenuItemService menuItemService) {
		List<MenuItem> menuItems = menuItemService.getAllMenuItems();

		if (menuItems.isEmpty()) {
		    System.out.println("No menu items found. Kindly login after some time.");
		} else {
		    System.out.println("\n-------------------------------------           MENU ITEMS           -------------------------------------\n");
		    System.out.printf("%-10s %-20s %-23s %-10s %-15s %-30s\n", "Item ID", "Group Name", "Name", "Price", "Taste", "Photo URL");
		    for (MenuItem menu : menuItems) {
		        System.out.printf("%-10s %-20s %-23s %-10.2f %-15s %-30s\n",
		                menu.getItemId(), menu.getGroupName(), menu.getName(),
		                menu.getPrice(), menu.getTaste(), menu.getPhotoUrl());
		    }
		    System.out.println("\n");
		}
		       
	}


 

	public static void manageTables(ManagerService managerService, TableTypeService tableTypeService, TableReservationService reservationService) {
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("Table Options:");
	        System.out.println("1. Manage Table Types");
	        System.out.println("2. Show Request for Table Reservations");
	        System.out.println("3. Accept/ Decline Table Request");
	        System.out.println("4. Back to Manager Options");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
	                manageTables(tableTypeService);
	                break;
	            case 2:
	            	viewAllReservations(reservationService);
	                break;
	            case 3:
	            	acceptDeclineRequest(reservationService);
	            break;
	            case 4:
	            	System.out.println("Logging out......");
	            	return;
	            
	            default:
	                System.out.println("Invalid option. Please try again.");
	        }
	    }
	}

	private static void acceptDeclineRequest(TableReservationService reservationService) {
		Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("Table Confirmation:");
	        System.out.println("1. Accept Request");
	        System.out.println("2. Decline Request");
	      
	        System.out.println("3. Back to Manager Options");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
	                acceptTable(reservationService);
	                break;
	            case 2:
	            	declineTable(reservationService);
	                break;
	            
	            case 3:
	            	System.out.println("Logging out......\n");
	            	return;
	            
	            default:
	                System.out.println("Invalid option. Please try again.");
	        }
	        
	    }
	}
		
		


	private static void declineTable(TableReservationService reservationService) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Reservation Id need to be Declined:");
		 
		 int rsid = scanner.nextInt();
//		 reservationService.updateReservation(null);

		 
		 
		 if(reservationService.confirmTable1(rsid)) {
			 System.out.println("Success");
		 }else {
			 System.out.println("Failed");
		 }
		
	}


	private static void acceptTable(TableReservationService reservationService) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Reservation Id need to be Accepted:");
		 
		 int rsid = scanner.nextInt();
//		 
		 if(reservationService.confirmTable(rsid)) {
			 System.out.println("Success");
		 }else {
			 System.out.println("Failed");
		 }
	
		
	}


	public static void viewAllReservations(TableReservationService reservationService) {

		
		List<TableReservation> reservations = reservationService.getAllReservations();

		if (reservations.isEmpty()) {
		    System.out.println("No reservation requests found.");
		} else {
		    System.out.println("\nAll Reservation Requests:\n");
		    System.out.printf("%-5s %-15s %-10s %-10s %-10s %-10s\n", "RId", "User Name", "Table Id", "Date", "Time", "Status");
		    for (TableReservation reservation : reservations) {
		        System.out.printf("%-5d %-15s %-10d %-10s %-10s %-10s\n",
		                reservation.getReservationId(), reservation.getUserName(),
		                reservation.getTableId(), reservation.getReservationDate(),
		                reservation.getReservationTime(), reservation.getStatus());
		    }
		}
		        
	}


	public static void manageTables(TableTypeService tableTypeService) {
	    Scanner scanner = new Scanner(System.in);

	    while (true) {
	        System.out.println("\nTable Management Options:");
	        System.out.println("1. Add Table");
	        System.out.println("2. Delete Table");
	        System.out.println("3. View All Tables");
	        System.out.println("4. Back to Table Options\n");

	        int option = scanner.nextInt();

	        switch (option) {
	            case 1:
	                addTable(tableTypeService);
	                break;
	            case 2:
	                deleteTable(tableTypeService);
	                break;
	            case 3:
	                viewAllTables(tableTypeService);
	                break;
	            case 4:
	            	System.out.println("Logging out.....\n");
	                return; // Return to Table Options
	            default:
	                System.out.println("Invalid option. Please try again choosing correct option.");
	        }
	    }
	}

	public static void addTable(TableTypeService tableTypeService) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Enter Table Location: ");
	    String typeName = scanner.nextLine();

	    System.out.println("Capacity: ");
	    int capacity = scanner.nextInt();

	    TableType table = new TableType();
	    table.setTypeName(typeName);
	    table.setCapacity(capacity);

	     tableTypeService.addTableType(table);
	    System.out.println("Table added successfully!");
	}

	public static void viewAllTables(TableTypeService tableTypeService) {

		List<TableType> tables = tableTypeService.getAllTableTypes();

		if (tables.isEmpty()) {
		    System.out.println("No tables found.");
		} else {
		    System.out.println("\n--------------------- TABLES AVAILABLE ----------------------\n");
		    System.out.printf("%-10s %-20s %-10s\n", "Table ID", "Table Position", "Capacity");
		    for (TableType table : tables) {
		        System.out.printf("%-10d %-20s %-10d\n", table.getTypeId(), table.getTypeName(), table.getCapacity());
		    }
		}
	}

	public static void deleteTable(TableTypeService tableTypeService) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter Table ID to delete: ");
	    int tableId = scanner.nextInt();

	   tableTypeService.deleteTableType(tableId);
	    System.out.println("Table deleted successfully!");
	}

}


    

