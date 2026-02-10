// IMPORTS SECTION

// Import statements for file handling and collections (File handling, ArrayList)
import java.io.*;
import java.util.*;

// PERSON CLASS - ABSTRACT BASE CLASS

// Abstract parent class for all person types (Abstraction, Inheritance, Class)
abstract class Person {
    // Instance variables with protected access modifier (Variable - instance, Access modifiers, Data types)
    protected int id;
    protected String name;
    protected String email;

    // Constructor to initialize person object (Methods)
    public Person(int id, String name, String email){
        // Using 'this' keyword to resolve variable shadowing (Variable - shadowing)
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Abstract method to be implemented by subclasses (Methods - abstract)
    public abstract void displayInfo();
}

// CLIENT CLASS - REPRESENTS A CLIENT

// Client class extending Person (Inheritance, Class, Encapsulation)
class Client extends Person {
    // Private instance variable (Access modifiers, Variable - instance, Data types - non primitive)
    private String companyName;

    // Constructor with parameters (Methods)
    public Client(int id, String name, String email, String companyName) {
        // Calling parent constructor (Inheritance)
        super(id, name, email);
        this.companyName = companyName;
    }

    // Getter method for encapsulation (Methods - instance, Encapsulation)
    public String getCompanyName() {
        return companyName;
    }

    // Overriding abstract method from parent class (Method overriding, Polymorphism)
    public void displayInfo() {
        // Display all client information (Operators - concatenation)
        System.out.println("Client ID: " + id);
        System.out.println(" | Name: " + name);
        System.out.println(" | Email: " + email);
        System.out.println(" | Company: " + companyName);
    }
}

// EMPLOYEE CLASS - REPRESENTS AN EMPLOYEE

// Employee class extending Person (Inheritance, Class)
class Employee extends Person {
    // Private instance variables (Access modifiers, Variable - instance, Data types)
    private String position;
    private double hourlyRate;

    // Constructor (Methods)
    public Employee(int id, String name, String email, String position, double hourlyRate) {
        super(id, name, email);
        this.position = position;
        this.hourlyRate = hourlyRate;
    }

    // Getter methods (Methods - instance, Encapsulation)
    public String getPosition() {
        return position;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    // Overriding displayInfo method (Method overriding, Polymorphism)
    public void displayInfo() {
        // Display all employee information (Operators - concatenation)
        System.out.println("Employee ID: " + id);
        System.out.println(" | Name: " + name);
        System.out.println(" | Email: " + email);
        System.out.println(" | Position: " + position);
        System.out.println(" | Rate: PHP " + hourlyRate + "/hr");
    }
}

// SERVICE CLASS - REPRESENTS A SERVICE OFFERING

// Service class for managing service information (Class, Encapsulation)
class Service {
    // Private instance variables (Access modifiers, Variable - instance, Data types)
    private int serviceId;
    private String serviceName;
    private double price;

    // Constructor (Methods)
    public Service(int serviceId, String serviceName, double price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
    }

    // Getter methods (Methods - instance, Encapsulation)
    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    // Display service details (Methods - instance)
    public void displayInfo() {
        // Display all service information (Operators - concatenation)
        System.out.println("Service ID: " + serviceId);
        System.out.println(" | Service: " + serviceName);
        System.out.println(" | Price: PHP " + price);
    }
}

// SERVICE REQUEST CLASS - REPRESENTS A TRANSACTION

// ServiceRequest class to manage service transactions (Class, Encapsulation)
class ServiceRequest { 
    // Private instance variables (Access modifiers, Variable - instance, Data types)
    private int requestId; 
    private Client client; 
    private Service service; 
    private Employee employee; 
    private double hoursWorked;
    private Double computedPrice; // Stores the final computed price (null if not computed yet)

    // Constructor (Methods)
    public ServiceRequest(int requestId, Client client, Service service, Employee employee, double hoursWorked) {
        this.requestId = requestId;
        this.client = client;
        this.service = service;
        this.employee = employee;
        this.hoursWorked = hoursWorked;
        this.computedPrice = null;
    }

    // Getter methods for all properties (Methods - instance, Encapsulation)
    public int getRequestId() {
        return requestId;
    }

    public Client getClient() {
        return client;
    }

    public Service getService() {
        return service;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public Double getComputedPrice() {
        return computedPrice;
    }

    public void setComputedPrice(double price) {
        this.computedPrice = price;
    }

    // Setter method for employee (Methods - instance, Encapsulation)
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Calculate total cost without discount (Methods - instance, Operators - arithmetic)
    public double calculateTotalCost() {
        // Check if employee is assigned (Control statement - if)
        if (employee == null) {
            System.out.println("[WARNING] Employee not assigned yet!");
            return service.getPrice() * hoursWorked;
        }
        // Formula: (service price + employee rate) * hours worked (Operators - arithmetic)
        return (service.getPrice() + employee.getHourlyRate()) * hoursWorked;
    }

    // Overloaded method to calculate cost with discount (Method overloading, Operators - arithmetic)
    public double calculateTotalCost(double discount) {
        // Subtract discount from original total (Operators - arithmetic)
        return calculateTotalCost() - discount;
    }

}

// MAIN APPLICATION CLASS - ENTRY POINT AND MENU SYSTEM

// Main application class (Class, Access modifiers - public)
public class MainApp {

    // Static ArrayList variables to store application data (Variable - static, ArrayList, Data types - non primitive)
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Service> services = new ArrayList<>();
    static ArrayList<ServiceRequest> serviceRequests = new ArrayList<>();

    // MAIN METHOD - PROGRAM ENTRY POINT

    // Main method - entry point of the program (Methods - static)
    public static void main(String[] args) {
        // Local variable for user input (Variable - local, Scanner, Data types - non primitive)
        Scanner inputScanner = new Scanner(System.in);

        // LOAD EXISTING DATA FROM FILES

        // Load data from files on startup (Methods - static)
        loadClientsFromFile();
        loadEmployeesFromFile();
        loadServicesFromFile();
        loadServiceRequestsFromFile();

        // MAIN MENU LOOP

        // Local variable for menu choice (Variable - local, Data types - primitive)
        int mainMenuChoice;
        // Main menu loop (Loops - do-while)
        do {
            // Display main menu options
            System.out.println("--- IT Service Management System ---");
            System.out.println("1. Manage Clients");
            System.out.println("2. Manage Employees");
            System.out.println("3. Manage Services");
            System.out.println("4. Service Transactions");
            System.out.println("5. Reports");
            System.out.println("0. Exit");

            // Get user's menu choice (Scanner)
            System.out.print("\nEnter your choice: ");
            mainMenuChoice = inputScanner.nextInt();
            inputScanner.nextLine();

            // Switch statement for menu navigation (Control statement - switch)
            switch (mainMenuChoice) {
                // CASE 1: CLIENT MANAGEMENT
                case 1:
                    // Client management submenu (Variable - local)
                    int clientMenuChoice;
                    do {
                        // Display client submenu
                        System.out.println("\n--- Manage Clients ---");
                        System.out.println("1. Add Client");
                        System.out.println("2. View Clients");
                        System.out.println("0. Back");
                        
                        // Get submenu choice
                        System.out.print("\nEnter choice: ");
                        clientMenuChoice = inputScanner.nextInt();
                        inputScanner.nextLine();

                        switch (clientMenuChoice) {
                            case 1:
                                // --- ADD NEW CLIENT ---
                                // Collect client information from user (Variable - local)
                                int clientId;
                                // Loop until valid client ID (Loops - do-while)
                                do {
                                    System.out.print("Client ID (positive number or 0 to cancel): ");
                                    clientId = inputScanner.nextInt();
                                    inputScanner.nextLine();
                                    
                                    if (clientId == 0) {
                                        System.out.println("[CANCELLED] Client creation cancelled.");
                                        break;
                                    }
                                    
                                    if (clientId < 0) {
                                        System.out.println("[ERROR] ID must be a positive number! Please try again.");
                                    }
                                } while (clientId < 0);
                                
                                if (clientId == 0) break;

                                String clientName;
                                // Loop until valid name (non-empty)
                                do {
                                    System.out.print("Name (or type 'cancel' to cancel): ");
                                    clientName = inputScanner.nextLine().trim();
                                    
                                    if (clientName.equalsIgnoreCase("cancel")) {
                                        System.out.println("[CANCELLED] Client creation cancelled.");
                                        break;
                                    }
                                    
                                    if (clientName.isEmpty()) {
                                        System.out.println("[ERROR] Name cannot be empty! Please try again.");
                                    }
                                } while (clientName.isEmpty());
                                
                                if (clientName.equalsIgnoreCase("cancel")) break;

                                String clientEmail;
                                // Loop until valid email (non-empty)
                                do {
                                    System.out.print("Email (or type 'cancel' to cancel): ");
                                    clientEmail = inputScanner.nextLine().trim();
                                    
                                    if (clientEmail.equalsIgnoreCase("cancel")) {
                                        System.out.println("[CANCELLED] Client creation cancelled.");
                                        break;
                                    }
                                    
                                    if (clientEmail.isEmpty()) {
                                        System.out.println("[ERROR] Email cannot be empty! Please try again.");
                                    }
                                } while (clientEmail.isEmpty());
                                
                                if (clientEmail.equalsIgnoreCase("cancel")) break;

                                String clientCompany;
                                // Loop until valid company name (non-empty)
                                do {
                                    System.out.print("Company Name (or type 'cancel' to cancel): ");
                                    clientCompany = inputScanner.nextLine().trim();
                                    
                                    if (clientCompany.equalsIgnoreCase("cancel")) {
                                        System.out.println("[CANCELLED] Client creation cancelled.");
                                        break;
                                    }
                                    
                                    if (clientCompany.isEmpty()) {
                                        System.out.println("[ERROR] Company name cannot be empty! Please try again.");
                                    }
                                } while (clientCompany.isEmpty());
                                
                                if (clientCompany.equalsIgnoreCase("cancel")) break;

                                // Creating and adding client object to ArrayList (Object, ArrayList methods - add)
                                clients.add(new Client(clientId, clientName, clientEmail, clientCompany));
                                System.out.println("\n[SUCCESS] Client added successfully!");
                                break;

                            case 2:
                                // --- VIEW ALL CLIENTS ---
                                // Check if list is empty using ternary operator (Operators - ternary, ArrayList methods - isEmpty)
                                System.out.println(clients.isEmpty() ? "\n[ERROR] No clients found." : "");
                                
                                // Using if statement and logical NOT operator (Control statement - if, Operators - logical)
                                if (!clients.isEmpty()) {
                                    // Enhanced for loop to iterate through clients (Loops - for-each)
                                    for (Client client : clients) {
                                        // Polymorphic method call (Polymorphism)
                                        client.displayInfo();
                                    }
                                }
                                break;

                            case 0:
                                System.out.println("Returning to main menu...\n");
                                break;

                            default:
                                System.out.println("[ERROR] Invalid choice! Please try again.");
                        }

                    } while (clientMenuChoice != 0);
                    break;

                // CASE 2: EMPLOYEE MANAGEMENT
                case 2:
                    // Employee management submenu
                    int employeeMenuChoice;
                    do {
                        System.out.println("\n--- Manage Employees ---");
                        System.out.println("1. Add Employee");
                        System.out.println("2. View Employees");
                        System.out.println("0. Back");

                        System.out.print("\nEnter choice: ");
                        employeeMenuChoice = inputScanner.nextInt();
                        inputScanner.nextLine();

                        switch (employeeMenuChoice) {
                            case 1:
                                // --- ADD NEW EMPLOYEE ---
                                // Collect employee information from user
                                int empId;
                                // Loop until valid employee ID (Loops - do-while)
                                do {
                                    System.out.print("Employee ID (positive number or 0 to cancel): ");
                                    empId = inputScanner.nextInt();
                                    inputScanner.nextLine();
                                    
                                    if (empId == 0) {
                                        System.out.println("[CANCELLED] Employee creation cancelled.");
                                        break;
                                    }
                                    
                                    if (empId < 0) {
                                        System.out.println("[ERROR] ID must be a positive number! Please try again.");
                                    }
                                } while (empId < 0);
                                
                                if (empId == 0) break;

                                String empName;
                                // Loop until valid name (non-empty)
                                do {
                                    System.out.print("Name (or type 'cancel' to cancel): ");
                                    empName = inputScanner.nextLine().trim();
                                    
                                    if (empName.equalsIgnoreCase("cancel")) {
                                        System.out.println("[CANCELLED] Employee creation cancelled.");
                                        break;
                                    }
                                    
                                    if (empName.isEmpty()) {
                                        System.out.println("[ERROR] Name cannot be empty! Please try again.");
                                    }
                                } while (empName.isEmpty());
                                
                                if (empName.equalsIgnoreCase("cancel")) break;

                                String empEmail;
                                // Loop until valid email (non-empty)
                                do {
                                    System.out.print("Email (or type 'cancel' to cancel): ");
                                    empEmail = inputScanner.nextLine().trim();
                                    
                                    if (empEmail.equalsIgnoreCase("cancel")) {
                                        System.out.println("[CANCELLED] Employee creation cancelled.");
                                        break;
                                    }
                                    
                                    if (empEmail.isEmpty()) {
                                        System.out.println("[ERROR] Email cannot be empty! Please try again.");
                                    }
                                } while (empEmail.isEmpty());
                                
                                if (empEmail.equalsIgnoreCase("cancel")) break;

                                String empPosition;
                                // Loop until valid position (non-empty)
                                do {
                                    System.out.print("Position (or type 'cancel' to cancel): ");
                                    empPosition = inputScanner.nextLine().trim();
                                    
                                    if (empPosition.equalsIgnoreCase("cancel")) {
                                        System.out.println("[CANCELLED] Employee creation cancelled.");
                                        break;
                                    }
                                    
                                    if (empPosition.isEmpty()) {
                                        System.out.println("[ERROR] Position cannot be empty! Please try again.");
                                    }
                                } while (empPosition.isEmpty());
                                
                                if (empPosition.equalsIgnoreCase("cancel")) break;

                                double empRate;
                                // Loop until valid hourly rate (positive number)
                                do {
                                    System.out.print("Hourly Rate (positive number or 0 to cancel): ");
                                    empRate = inputScanner.nextDouble();
                                    inputScanner.nextLine();
                                    
                                    if (empRate == 0) {
                                        System.out.println("[CANCELLED] Employee creation cancelled.");
                                        break;
                                    }
                                    
                                    if (empRate < 0) {
                                        System.out.println("[ERROR] Hourly rate must be positive! Please try again.");
                                    }
                                } while (empRate < 0);
                                
                                if (empRate == 0) break;
                                
                                // Add employee object to ArrayList (ArrayList methods - add)
                                employees.add(new Employee(empId, empName, empEmail, empPosition, empRate));
                                System.out.println("\n[SUCCESS] Employee added successfully!");
                                break;

                            case 2:
                                // --- VIEW ALL EMPLOYEES ---
                                // Ternary operator for validation (Operators - ternary, ArrayList methods - isEmpty)
                                System.out.println(employees.isEmpty() ? "\n[ERROR] No employees found." : "");
                                if (!employees.isEmpty()) {
                                    for (Employee employee : employees) {
                                        employee.displayInfo();
                                    }
                                }
                                break;

                            case 0:
                                System.out.println("Returning to main menu...\n");
                                break;

                            default:
                                System.out.println("[ERROR] Invalid choice! Please try again.");
                        }

                    } while (employeeMenuChoice != 0);
                    break;

                // CASE 3: SERVICE MANAGEMENT
                case 3:
                    int serviceMenuChoice;
                	    do {
                	        System.out.println("\n--- Manage Services ---");
                	        System.out.println("1. Add Service");
                	        System.out.println("2. View Services");
                	        System.out.println("0. Back");

                	        System.out.print("\nEnter choice: ");
                	        serviceMenuChoice = inputScanner.nextInt();
                	        inputScanner.nextLine();

                	        switch (serviceMenuChoice) {
                	            case 1:
                	                // --- ADD NEW SERVICE ---
                	                // Collect service information from user
                	                int serId;
                	                // Loop until valid service ID (Loops - do-while)
                	                do {
                	                    System.out.print("Service ID (positive number or 0 to cancel): ");
                	                    serId = inputScanner.nextInt();
                	                    inputScanner.nextLine();
                	                    
                	                    if (serId == 0) {
                	                        System.out.println("[CANCELLED] Service creation cancelled.");
                	                        break;
                	                    }
                	                    
                	                    if (serId < 0) {
                	                        System.out.println("[ERROR] ID must be a positive number! Please try again.");
                	                    }
                	                } while (serId < 0);
                	                
                	                if (serId == 0) break;

                	                String serName;
                	                // Loop until valid service name (non-empty)
                	                do {
                	                    System.out.print("Service Name (or type 'cancel' to cancel): ");
                	                    serName = inputScanner.nextLine().trim();
                	                    
                	                    if (serName.equalsIgnoreCase("cancel")) {
                	                        System.out.println("[CANCELLED] Service creation cancelled.");
                	                        break;
                	                    }
                	                    
                	                    if (serName.isEmpty()) {
                	                        System.out.println("[ERROR] Service name cannot be empty! Please try again.");
                	                    }
                	                } while (serName.isEmpty());
                	                
                	                if (serName.equalsIgnoreCase("cancel")) break;

                	                double serPrice;
                	                // Loop until valid service price (positive number)
                	                do {
                	                    System.out.print("Service Price (positive number or 0 to cancel): ");
                	                    serPrice = inputScanner.nextDouble();
                	                    inputScanner.nextLine();
                	                    
                	                    if (serPrice == 0) {
                	                        System.out.println("[CANCELLED] Service creation cancelled.");
                	                        break;
                	                    }
                	                    
                	                    if (serPrice < 0) {
                	                        System.out.println("[ERROR] Service price must be positive! Please try again.");
                	                    }
                	                } while (serPrice < 0);
                	                
                	                if (serPrice == 0) break;

                	                services.add(new Service(serId, serName, serPrice));
                	                System.out.println("\n[SUCCESS] Service added successfully!");
                	                break;

                	            case 2:
                	                // --- VIEW ALL SERVICES ---
                	                // Validate if services exist
                	                System.out.println(services.isEmpty() ? "\n[ERROR] No services found." : "");
                	                if (!services.isEmpty()) {
                	                    for (Service service : services) {
                	                        service.displayInfo();
                	                    }
                	                }
                	                break;

                	            case 0:
                	                System.out.println("Returning to main menu...\n");
                	                break;

                	            default:
                	                System.out.println("[ERROR] Invalid choice! Please try again.");
                	        }

                	    } while (serviceMenuChoice != 0);
                    break;

                // CASE 4: SERVICE TRANSACTIONS
                case 4:
                    // Service transaction management
                    int transactionMenuChoice;
                    do { 
                        System.out.println("\n--- Manage Service Transactions ---");
                        System.out.println("1. Create Service Request");
                        System.out.println("2. Assign Employee to Client");
                        System.out.println("3. Compute Total Cost");
                        System.out.println("0. Back");

                        System.out.print("\nEnter your choice: ");
                        transactionMenuChoice = inputScanner.nextInt();
                        inputScanner.nextLine();

                        switch (transactionMenuChoice) {
                            case 1:
                                // CREATE SERVICE REQUEST
                                // Validate that all required data exists before creating request
                                // Ternary operators for validation (Operators - ternary, Control statement - if)
                                String missingData = clients.isEmpty() ? "clients" : 
                                                    (services.isEmpty() ? "services" : "");
                                // If-else statement with continue (Control statement - if-else, Loop control - continue)
                                if (!missingData.isEmpty()) {
                                    System.out.println("\n[ERROR] Cannot create service request!");
                                    System.out.println("Please ensure there are " + missingData + ".");
                                    continue;
                                }

                                // --- STEP 1: SELECT CLIENT ---
                                System.out.println("\n--- Step 1/3: Select Client ---");
                                int clientChoice;
                                // Loop until valid client selection (Loops - do-while, Control statement)
                                do {
                                    // Traditional for loop (Loops - for, ArrayList methods - size, get)
                                    for (int i = 0; i < clients.size(); i++) {
                                        // Get element from ArrayList (ArrayList methods - get)
                                        Client client = clients.get(i);
                                        System.out.printf("%d. %s (ID: %d, Company: %s)%n", 
                                            i + 1, client.name, client.id, client.getCompanyName());
                                    }
                                    System.out.print("Select Client (1-" + clients.size() + " or 0 to cancel): ");
                                    // Arithmetic operation and assignment (Operators - arithmetic, assignment)
                                    clientChoice = inputScanner.nextInt() - 1;
                                    inputScanner.nextLine();
                                    
                                    // Check if user wants to cancel (Control statement - if)
                                    if (clientChoice == -1) {
                                        System.out.println("[CANCELLED] Service request creation cancelled.");
                                        break;
                                    }
                                    
                                    // Ternary with relational and logical operators (Operators - ternary, relational, logical)
                                    String clientError = (clientChoice < 0 || clientChoice >= clients.size()) ? 
                                        "[ERROR] Invalid client selection! Please try again." : "";
                                    if (!clientError.isEmpty()) {
                                        System.out.println(clientError);
                                    }
                                } while (clientChoice < 0 || clientChoice >= clients.size());
                                
                                // If cancelled, skip to next iteration
                                if (clientChoice == -1) continue;

                                // --- STEP 2: SELECT SERVICE ---
                                System.out.println("\n--- Step 2/3: Select Service ---");
                                int serviceChoice;
                                // Loop until valid service selection (Loops - do-while)
                                do {
                                    for (int i = 0; i < services.size(); i++) {
                                        Service service = services.get(i);
                                        System.out.printf("%d. %s (ID: %d, Base Price: PHP %.2f)%n", 
                                            i + 1, service.getServiceName(), service.getServiceId(), service.getPrice());
                                    }
                                    System.out.print("Select Service (1-" + services.size() + " or 0 to cancel): ");
                                    serviceChoice = inputScanner.nextInt() - 1;
                                    inputScanner.nextLine();
                                    
                                    if (serviceChoice == -1) {
                                        System.out.println("[CANCELLED] Service request creation cancelled.");
                                        break;
                                    }
                                    
                                    String serviceError = (serviceChoice < 0 || serviceChoice >= services.size()) ? 
                                        "[ERROR] Invalid service selection! Please try again." : "";
                                    if (!serviceError.isEmpty()) {
                                        System.out.println(serviceError);
                                    }
                                } while (serviceChoice < 0 || serviceChoice >= services.size());
                                
                                if (serviceChoice == -1) continue;

                                // --- STEP 3: ENTER HOURS WORKED ---
                                System.out.println("\n--- Step 3/3: Enter Hours ---");
                                double hoursWorked;
                                // Loop until valid hours input (Loops - do-while)
                                do {
                                    System.out.print("Hours Worked (greater than 0, or 0 to cancel): ");
                                    hoursWorked = inputScanner.nextDouble();
                                    inputScanner.nextLine();
                                    
                                    if (hoursWorked == 0) {
                                        System.out.println("[CANCELLED] Service request creation cancelled.");
                                        break;
                                    }
                                    
                                    if (hoursWorked < 0) {
                                        System.out.println("[ERROR] Hours must be greater than 0! Please try again.");
                                    }
                                } while (hoursWorked < 0);
                                
                                if (hoursWorked == 0) continue;

                                // Create new ServiceRequest object without employee (Object)
                                ServiceRequest newRequest = new ServiceRequest(
                                    serviceRequests.size() + 1,
                                    clients.get(clientChoice),
                                    services.get(serviceChoice),
                                    null, // Employee will be assigned later
                                    hoursWorked
                                );
                                // Add to ArrayList (ArrayList methods - add)
                                serviceRequests.add(newRequest);
                                
                                // Success confirmation
                                System.out.println("\n[SUCCESS] Service request created!");
                                System.out.printf("Request ID: #%d%n", newRequest.getRequestId());
                                System.out.println("Note: Please assign an employee to this request in option 2.");
                                break;

                            case 2:
                                // ASSIGN EMPLOYEE TO SERVICE REQUEST
                                // Validate required data exists
                                String missingResource = serviceRequests.isEmpty() ? "service requests" : 
                                                        (employees.isEmpty() ? "employees" : "");
                                if (!missingResource.isEmpty()) {
                                    System.out.println("\n[ERROR] Cannot assign employee!");
                                    System.out.println("Please ensure there are " + missingResource + ".");
                                    continue;
                                }

                                // --- STEP 1: SELECT SERVICE REQUEST ---
                                System.out.println("\n--- Step 1/2: Select Service Request ---");
                                int assignRequestChoice;
                                // Loop until valid request selection (Loops - do-while)
                                do {
                                    for (int i = 0; i < serviceRequests.size(); i++) {
                                        ServiceRequest request = serviceRequests.get(i);
                                        String empStatus = (request.getEmployee() == null) ? "No employee assigned" : 
                                                          "Employee: " + request.getEmployee().name;
                                        System.out.printf("%d. Request #%d - %s (%s)%n", 
                                            i + 1, request.getRequestId(), request.getClient().name, empStatus);
                                    }
                                    System.out.print("Select Service Request (1-" + serviceRequests.size() + " or 0 to cancel): ");
                                    assignRequestChoice = inputScanner.nextInt() - 1;
                                    inputScanner.nextLine();
                                    
                                    if (assignRequestChoice == -1) {
                                        System.out.println("[CANCELLED] Employee assignment cancelled.");
                                        break;
                                    }
                                    
                                    String assignRequestError = (assignRequestChoice < 0 || assignRequestChoice >= serviceRequests.size()) ? 
                                        "[ERROR] Invalid service request selection! Please try again." : "";
                                    if (!assignRequestError.isEmpty()) {
                                        System.out.println(assignRequestError);
                                    }
                                } while (assignRequestChoice < 0 || assignRequestChoice >= serviceRequests.size());
                                
                                if (assignRequestChoice == -1) continue;

                                // --- STEP 2: SELECT EMPLOYEE ---
                                System.out.println("\n--- Step 2/2: Select Employee ---");
                                int assignEmployeeChoice;
                                // Loop until valid employee selection (Loops - do-while)
                                do {
                                    for (int i = 0; i < employees.size(); i++) {
                                        Employee employee = employees.get(i);
                                        System.out.printf("%d. %s (ID: %d, Position: %s, Rate: PHP %.2f/hr)%n", 
                                            i + 1, employee.name, employee.id, employee.getPosition(), employee.getHourlyRate());
                                    }
                                    System.out.print("Select Employee (1-" + employees.size() + " or 0 to cancel): ");
                                    assignEmployeeChoice = inputScanner.nextInt() - 1;
                                    inputScanner.nextLine();
                                    
                                    if (assignEmployeeChoice == -1) {
                                        System.out.println("[CANCELLED] Employee assignment cancelled.");
                                        break;
                                    }
                                    
                                    String assignEmployeeError = (assignEmployeeChoice < 0 || assignEmployeeChoice >= employees.size()) ? 
                                        "[ERROR] Invalid employee selection! Please try again." : "";
                                    if (!assignEmployeeError.isEmpty()) {
                                        System.out.println(assignEmployeeError);
                                    }
                                } while (assignEmployeeChoice < 0 || assignEmployeeChoice >= employees.size());
                                
                                if (assignEmployeeChoice == -1) continue;

                                // Assign employee to the service request
                                ServiceRequest selectedRequest = serviceRequests.get(assignRequestChoice);
                                Employee selectedEmployee = employees.get(assignEmployeeChoice);
                                selectedRequest.setEmployee(selectedEmployee);
                                
                                System.out.println("\n[SUCCESS] Employee assigned!");
                                System.out.printf("Employee: %s%n", selectedEmployee.name);
                                System.out.printf("Assigned to: Request #%d (%s)%n", 
                                    selectedRequest.getRequestId(), selectedRequest.getClient().name);
                                break;

                            case 3:
                                // COMPUTE TOTAL COST
                                // Validate using isEmpty method (ArrayList methods - isEmpty, Control statement - if)
                                if (serviceRequests.isEmpty()) {
                                    System.out.println("\n[ERROR] No service requests found!");
                                    System.out.println("Please create a service request first.");
                                    continue;
                                }

                                // --- STEP 1: SELECT SERVICE REQUEST TO COMPUTE ---
                                System.out.println("\n--- Select Service Request to Compute ---");
                                int requestChoice;
                                // Loop until valid request selection (Loops - do-while)
                                do {
                                    for (int i = 0; i < serviceRequests.size(); i++) {
                                        ServiceRequest request = serviceRequests.get(i);
                                        String priceStatus = (request.getComputedPrice() == null) ? "Not computed" : 
                                                            String.format("PHP %.2f", request.getComputedPrice());
                                        String empStatus = (request.getEmployee() == null) ? "No employee" : 
                                                          request.getEmployee().name;
                                        System.out.printf("%d. Request #%d - %s | Employee: %s | Price: %s%n", 
                                            i + 1, request.getRequestId(), request.getClient().name, 
                                            empStatus, priceStatus);
                                    }
                                    System.out.print("Choice (1-" + serviceRequests.size() + " or 0 to cancel): ");
                                    requestChoice = inputScanner.nextInt() - 1;
                                    inputScanner.nextLine();
                                    
                                    if (requestChoice == -1) {
                                        System.out.println("[CANCELLED] Cost computation cancelled.");
                                        break;
                                    }
                                    
                                    String requestError = (requestChoice < 0 || requestChoice >= serviceRequests.size()) ? 
                                        "[ERROR] Invalid service request selection! Please try again." : "";
                                    if (!requestError.isEmpty()) {
                                        System.out.println(requestError);
                                    }
                                } while (requestChoice < 0 || requestChoice >= serviceRequests.size());
                                
                                if (requestChoice == -1) continue;

                                // Get selected service request
                                ServiceRequest selected = serviceRequests.get(requestChoice);
                                
                                // Check if employee is assigned
                                if (selected.getEmployee() == null) {
                                    System.out.println("\n[ERROR] Cannot compute cost!");
                                    System.out.println("Please assign an employee to this request first (Option 2).");
                                    continue;
                                }
                                
                                // --- STEP 2: DISPLAY COST COMPUTATION OPTIONS ---
                                System.out.println("\n--- Compute Total Cost ---");
                                int costChoice;
                                // Loop until valid cost option selection (Loops - do-while)
                                do {
                                    System.out.println("1. Without Discount");
                                    System.out.println("2. With Discount");
                                    System.out.println("0. Cancel");
                                    System.out.print("Enter your choice: ");
                                    costChoice = inputScanner.nextInt();
                                    inputScanner.nextLine();
                                    
                                    if (costChoice == 0) {
                                        System.out.println("[CANCELLED] Cost computation cancelled.");
                                        break;
                                    }
                                    
                                    if (costChoice < 0 || costChoice > 2) {
                                        System.out.println("[ERROR] Invalid choice! Please select 1, 2, or 0.");
                                    }
                                } while (costChoice < 0 || costChoice > 2);
                                
                                if (costChoice == 0) continue;
                                
                                // If-else if-else control statement (Control statement - if else if)
                                if (costChoice == 1) {
                                    // --- CALCULATE WITHOUT DISCOUNT ---
                                    double originalPrice = selected.calculateTotalCost();
                                    selected.setComputedPrice(originalPrice); // Save the price
                                    
                                    System.out.println("\n========== Cost Summary ==========");
                                    System.out.printf("Total Cost (No Discount): PHP %.2f%n", originalPrice);
                                    System.out.println("==================================");
                                    System.out.println("[SUCCESS] Price saved to service request!");
                                    
                                } else if (costChoice == 2) {
                                    // --- CALCULATE WITH DISCOUNT ---
                                    // Get discount percentage from user
                                    double discountPercent;
                                    // Loop until valid discount percentage (Loops - do-while)
                                    do {
                                        System.out.print("Enter discount percentage (1-100 or 0 to cancel): ");
                                        discountPercent = inputScanner.nextDouble();
                                        inputScanner.nextLine();
                                        
                                        if (discountPercent == 0) {
                                            System.out.println("[CANCELLED] Discount calculation cancelled.");
                                            break;
                                        }
                                        
                                        // Ternary operator with relational operators (Operators - ternary, relational)
                                        String validationError = (discountPercent < 1 || discountPercent > 100) ? 
                                            "[ERROR] Invalid percentage! Must be between 1 and 100. Please try again." : "";
                                        if (!validationError.isEmpty()) {
                                            System.out.println(validationError);
                                        }
                                    } while (discountPercent < 1 || discountPercent > 100);
                                    
                                    if (discountPercent == 0) continue;
                                    
                                    // Arithmetic calculations (Operators - arithmetic)
                                    double originalCost = selected.calculateTotalCost();
                                    double discountAmount = (discountPercent / 100) * originalCost;
                                    // Calling overloaded method (Method overloading)
                                    double finalCost = selected.calculateTotalCost(discountAmount);
                                    selected.setComputedPrice(finalCost); // Save the discounted price
                                    
                                    System.out.println("\n========== Cost Summary ==========");
                                    System.out.printf("Original Cost:  PHP %.2f%n", originalCost);
                                    System.out.printf("Discount:       %.0f%% (PHP %.2f)%n", 
                                        discountPercent, discountAmount);
                                    System.out.printf("Final Cost:     PHP %.2f%n", finalCost);
                                    System.out.println("==================================");
                                    System.out.println("[SUCCESS] Discounted price saved to service request!");
                                }
                                break;

                            case 0:
                                System.out.println("Returning to main menu...\n");
                                break;

                            default:
                                System.out.println("[ERROR] Invalid choice! Please try again.");
                                break;
                        }
                    } while (transactionMenuChoice != 0);
                    break;

                // CASE 5: REPORTS
                case 5:
                    // Reports menu
                    int reportMenuChoice;
                    do { 
                        System.out.println("\n--- Manage Reports ---");
                        System.out.println("1. Total Clients");
                        System.out.println("2. Total Services Rendered");
                        System.out.println("3. Total Revenue");
                        System.out.println("0. Back");

                        System.out.print("\nEnter your choice: ");
                        reportMenuChoice = inputScanner.nextInt();
                        inputScanner.nextLine();

                        switch (reportMenuChoice) {
                            case 1:
                                // --- TOTAL CLIENTS REPORT ---
                                // Display total using size method (ArrayList methods - size)
                                System.out.println("\n========== Client Report ==========");
                                System.out.printf("Total Clients: %d%n", clients.size());
                                System.out.println("===================================");
                                break;

                            case 2:
                                // Display total services rendered
                                System.out.println("\n========== Service Report =========");
                                System.out.printf("Total Services Rendered: %d%n", serviceRequests.size());
                                System.out.println("===================================");
                                break;

                            case 3:
                                // --- TOTAL REVENUE REPORT ---
                                // Initialize accumulator variable (Variable - local)
                                double totalRevenue = 0;
                                // For-each loop to calculate sum (Loops - for-each, Operators - assignment)
                                for (ServiceRequest serviceRequest : serviceRequests) {
                                    // Compound assignment operator (Operators - assignment)
                                    totalRevenue += serviceRequest.calculateTotalCost();
                                }
                                System.out.println("\n========== Revenue Report =========");
                                System.out.printf("Total Revenue: PHP %.2f%n", totalRevenue);
                                System.out.println("===================================");
                                break;

                            case 0:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("[ERROR] Invalid choice! Please try again.");
                                break;
                        }
                    } while (reportMenuChoice != 0);
                    break;
                
                // CASE 0: EXIT PROGRAM
                case 0:
                    // Save all data before exit
                    saveClientsToFile();
                    saveEmployeesToFile();
                    saveServicesToFile();
                    saveServiceRequestsToFile();
                    System.out.println("Exiting... Goodbye!"); 
                    break;

                default:
                    System.out.println("[ERROR] Invalid choice! Please try again."); 
                    break;
            }

        } while (mainMenuChoice != 0);
        
        // Close scanner resource to prevent memory leaks
        inputScanner.close();
    }
    
    // FILE SAVING METHODS
    
    // Save clients data to file (Methods - static, File handling)
    static void saveClientsToFile() {
        // Try-with-resources for automatic resource management (Exceptions - try-catch, File handling - BufferedWriter, FileWriter)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clients.txt"))) {
            // For-each loop to write each client (Loops - for-each)
            for (Client client : clients) {
                // Write client data as comma-separated values (Operators - concatenation)
                writer.write(client.id + "," + client.name + "," + client.email + "," + client.getCompanyName());
                writer.newLine();
            }
            System.out.println("[SUCCESS] Clients data saved successfully.");
        } catch (IOException e) {
            // Catch checked exception (Exceptions - checked exceptions)
            System.out.println("[ERROR] Failed to save clients - " + e.getMessage());
        }
    }

    // Save employees data to file (Methods - static, File handling)
    static void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"))) {
            // Write each employee to file
            for (Employee employee : employees) {
                // Write employee data as comma-separated values (Operators - concatenation)
                writer.write(employee.id + "," + employee.name + "," + employee.email + "," + employee.getPosition() + "," + employee.getHourlyRate());
                writer.newLine();
            }
            System.out.println("[SUCCESS] Employees data saved successfully.");
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save employees - " + e.getMessage());
        }
    }

    // Save services data to file (Methods - static, File handling)
    static void saveServicesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("services.txt"))) {
            // Write each service to file
            for (Service service : services) {
                // Write service data as comma-separated values (Operators - concatenation)
                writer.write(service.getServiceId() + "," + service.getServiceName() + "," + service.getPrice());
                writer.newLine();
            }
            System.out.println("[SUCCESS] Services data saved successfully.");
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save services - " + e.getMessage());
        }
    }

    // Save service requests data to file (Methods - static, File handling)
    static void saveServiceRequestsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("service_requests.txt"))) {
            // Write each service request to file
            for (ServiceRequest serviceRequest : serviceRequests) {
                // Write service request data with foreign keys (Operators - concatenation)
                writer.write(serviceRequest.getRequestId() + "," + 
                    serviceRequest.getClient().id + "," + 
                    serviceRequest.getService().getServiceId() + "," + 
                    serviceRequest.getEmployee().id + "," +
                    serviceRequest.getHoursWorked());
                writer.newLine();
            }
            System.out.println("[SUCCESS] Service requests data saved successfully.");
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save service requests - " + e.getMessage());
        }
    }

    // FILE LOADING METHODS
    
    // Load clients data from file (Methods - static, File handling)
    static void loadClientsFromFile() {
        // Create File object (File handling - File)
        File file = new File("clients.txt");
        
        // Check if file exists (Control statement - if, Loop control - return)
        if (!file.exists()) {
            return; // Exit method if file doesn't exist
        }
        // Try-with-resources using BufferedReader and FileReader (File handling - BufferedReader, FileReader)
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            // While loop to read file lines (Loops - while)
            while ((line = reader.readLine()) != null) {
                String clientData = line.trim();
                
                // Continue to next iteration if empty (Loop control - continue)
                if (clientData.isEmpty()) continue;
                
                // Split string into array (Array)
                String[] data = clientData.split(",");
                
                // Validate array length (Control statement - if-else)
                if (data.length == 4) {
                    // Parse data and create client object (Exceptions - unchecked exceptions)
                    clients.add(new Client(Integer.parseInt(data[0]), data[1], data[2], data[3]));
                } else {
                    System.out.println("[ERROR] Skipping invalid client data.");
                }
            }
            
            System.out.println("[SUCCESS] Clients data loaded successfully.");
        } catch (FileNotFoundException e) {
            // Multiple catch blocks for different exception types (Exceptions - checked exceptions)
            System.out.println("[ERROR] Clients file was deleted during loading.");
        } catch (NumberFormatException e) {
            // Catching unchecked exception (Exceptions - unchecked exceptions)
            System.out.println("[ERROR] Invalid client data format - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load clients: " + e.getMessage());
        }
    }

    // Load employees data from file (Methods - static, File handling)
    static void loadEmployeesFromFile() {
        File file = new File("employees.txt");
        
        // Check if file exists before attempting to load
        if (!file.exists()) {
            return; 
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String employeeData = line.trim();
                if (employeeData.isEmpty()) continue;
                String[] data = employeeData.split(",");
                if (data.length == 5) {
                    employees.add(new Employee(Integer.parseInt(data[0]), data[1], data[2], data[3], Double.parseDouble(data[4])));
                } else {
                    System.out.println("[ERROR] Skipping invalid employee data.");
                }
            }
            System.out.println("[SUCCESS] Employees data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Employees file was deleted during loading.");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid employee data format - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load employees: " + e.getMessage());
        }
    }

    // Load services data from file (Methods - static, File handling)
    static void loadServicesFromFile() {
        File file = new File("services.txt");
        
        // Check if file exists before attempting to load
        if (!file.exists()) {
            return; 
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String serviceData = line.trim();
                if (serviceData.isEmpty()) continue;
                String[] data = serviceData.split(",");
                if (data.length == 3) {
                    services.add(new Service(Integer.parseInt(data[0]), data[1], Double.parseDouble(data[2])));
                } else {
                    System.out.println("[ERROR] Skipping invalid service data.");
                }
            }
            System.out.println("[SUCCESS] Services data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Services file was deleted during loading.");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid service data format - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load services: " + e.getMessage());
        }
    }

    // Load service requests data from file (Methods - static, File handling)
    static void loadServiceRequestsFromFile() {
        File file = new File("service_requests.txt");
        
        // Check if file exists before attempting to load
        if (!file.exists()) {
            return; 
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String serviceRequestData = line.trim();
                if (serviceRequestData.isEmpty()) continue;
                
                String[] data = serviceRequestData.split(",");
                
                if (data.length == 5) {
                    // Using stream API to find related objects by ID (Operators - relational)
                    Client client = clients.stream().filter(clientInfo -> clientInfo.id == Integer.parseInt(data[1])).findFirst().orElse(null);
                    Service service = services.stream().filter(serviceInfo -> serviceInfo.getServiceId() == Integer.parseInt(data[2])).findFirst().orElse(null);
                    Employee employee = employees.stream().filter(employeeInfo -> employeeInfo.id == Integer.parseInt(data[3])).findFirst().orElse(null);
                    if (client != null && service != null && employee != null) {
                        serviceRequests.add(new ServiceRequest(Integer.parseInt(data[0]), client, service, employee, Double.parseDouble(data[4])));
                    } else {
                        System.out.println("[ERROR] Skipping service request with invalid client, service, or employee reference.");
                    }
                } else {
                    System.out.println("[ERROR] Skipping invalid service request data.");
                }
            }
            System.out.println("[SUCCESS] Service requests data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Service requests file was deleted during loading.");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid service request data format - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] Failed to load service requests: " + e.getMessage());
        }
    }
}