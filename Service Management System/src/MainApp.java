// ===========================================================================================
// IMPORTS SECTION
// ===========================================================================================
// Import statements for file handling and collections (File handling, ArrayList)
import java.io.*;
import java.util.*;

// ===========================================================================================
// PERSON CLASS - ABSTRACT BASE CLASS
// ===========================================================================================
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

// ===========================================================================================
// CLIENT CLASS - REPRESENTS A CLIENT/CUSTOMER
// ===========================================================================================
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

// ===========================================================================================
// EMPLOYEE CLASS - REPRESENTS AN EMPLOYEE/SERVICE PROVIDER
// ===========================================================================================
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
        System.out.println(" | Rate: ₱" + hourlyRate + "/hr");
    }
}

// ===========================================================================================
// SERVICE CLASS - REPRESENTS A SERVICE OFFERING
// ===========================================================================================
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
        System.out.println(" | Price: ₱" + price);
    }
}

// ===========================================================================================
// SERVICE REQUEST CLASS - REPRESENTS A TRANSACTION/BOOKING
// ===========================================================================================
// ServiceRequest class to manage service transactions (Class, Encapsulation)
class ServiceRequest { 
    // Private instance variables (Access modifiers, Variable - instance, Data types)
    private int requestId; 
    private Client client; 
    private Service service; 
    private Employee employee; 
    private double hoursWorked; 

    // Constructor (Methods)
    public ServiceRequest(int requestId, Client client, Service service, Employee employee, double hoursWorked) {
        this.requestId = requestId;
        this.client = client;
        this.service = service;
        this.employee = employee;
        this.hoursWorked = hoursWorked;
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

    // Calculate total cost without discount (Methods - instance, Operators - arithmetic)
    public double calculateTotalCost() {
        // Formula: (service price + employee rate) * hours worked (Operators - arithmetic)
        return (service.getPrice() + employee.getHourlyRate()) * hoursWorked;
    }

    // Overloaded method to calculate cost with discount (Method overloading, Operators - arithmetic)
    public double calculateTotalCost(double discount) {
        // Subtract discount from original total (Operators - arithmetic)
        return calculateTotalCost() - discount;
    }

}

// ===========================================================================================
// MAIN APPLICATION CLASS - ENTRY POINT AND MENU SYSTEM
// ===========================================================================================
// Main application class (Class, Access modifiers - public)
public class MainApp {

    // Static ArrayList variables to store application data (Variable - static, ArrayList, Data types - non primitive)
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Service> services = new ArrayList<>();
    static ArrayList<ServiceRequest> serviceRequests = new ArrayList<>();

    // ===========================================================================================
    // MAIN METHOD - PROGRAM ENTRY POINT
    // ===========================================================================================
    // Main method - entry point of the program (Methods - static)
    public static void main(String[] args) {
        // Local variable for user input (Variable - local, Scanner, Data types - non primitive)
        Scanner inputScanner = new Scanner(System.in);

        // ===========================================================================================
        // LOAD EXISTING DATA FROM FILES
        // ===========================================================================================
        // Load data from files on startup (Methods - static)
        loadClientsFromFile();
        loadEmployeesFromFile();
        loadServicesFromFile();
        loadServiceRequestsFromFile();

        // ===========================================================================================
        // MAIN MENU LOOP
        // ===========================================================================================
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
                // ===========================================================================================
                // CASE 1: CLIENT MANAGEMENT
                // ===========================================================================================
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
                                System.out.print("Client ID: ");
                                int clientId = inputScanner.nextInt();
                                inputScanner.nextLine();

                                System.out.print("Name: ");
                                String clientName = inputScanner.nextLine();

                                System.out.print("Email: ");
                                String clientEmail = inputScanner.nextLine();

                                System.out.print("Company Name: ");
                                String clientCompany = inputScanner.nextLine();

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
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("[ERROR] Invalid choice! Please try again.");
                        }

                    } while (clientMenuChoice != 0);
                    break;

                // ===========================================================================================
                // CASE 2: EMPLOYEE MANAGEMENT
                // ===========================================================================================
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
                                System.out.print("Employee ID: ");
                                int empId = inputScanner.nextInt();
                                inputScanner.nextLine();

                                System.out.print("Name: ");
                                String empName = inputScanner.nextLine();

                                System.out.print("Email: ");
                                String empEmail = inputScanner.nextLine();

                                System.out.print("Position: ");
                                String empPosition = inputScanner.nextLine();

                                System.out.print("Hourly Rate: ");
                                double empRate = inputScanner.nextDouble();
                                
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
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("[ERROR] Invalid choice! Please try again.");
                        }

                    } while (employeeMenuChoice != 0);
                    break;

                // ===========================================================================================
                // CASE 3: SERVICE MANAGEMENT
                // ===========================================================================================
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
                	                System.out.print("Service ID: ");
                	                int serId = inputScanner.nextInt();
                	                inputScanner.nextLine();

                	                System.out.print("Service Name: ");
                	                String serName = inputScanner.nextLine();

                	                System.out.print("Service Price: ");
                	                double serPrice = inputScanner.nextDouble();

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
                	                System.out.println("Returning to main menu...");
                	                break;

                	            default:
                	                System.out.println("[ERROR] Invalid choice! Please try again.");
                	        }

                	    } while (serviceMenuChoice != 0);
                    break;

                // ===========================================================================================
                // CASE 4: SERVICE TRANSACTIONS
                // ===========================================================================================
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
                                // -----------------------------------------------------------------------
                                // CREATE SERVICE REQUEST
                                // -----------------------------------------------------------------------
                                // Validate that all required data exists before creating request
                                // Nested ternary operators for validation (Operators - ternary, Control statement - nested if)
                                String missingData = clients.isEmpty() ? "clients" : 
                                                    (employees.isEmpty() ? "employees" : 
                                                    (services.isEmpty() ? "services" : ""));
                                // If-else statement with continue (Control statement - if-else, Loop control - continue)
                                if (!missingData.isEmpty()) {
                                    System.out.println("\n[ERROR] Cannot create service request!");
                                    System.out.println("Please ensure there are " + missingData + ".");
                                    continue;
                                }

                                // --- STEP 1: SELECT CLIENT ---
                                System.out.println("\n--- Step 1/4: Select Client ---");
                                // Traditional for loop (Loops - for, ArrayList methods - size, get)
                                for (int i = 0; i < clients.size(); i++) {
                                    // Get element from ArrayList (ArrayList methods - get)
                                    Client client = clients.get(i);
                                    System.out.printf("%d. %s (ID: %d, Company: %s)%n", 
                                        i + 1, client.name, client.id, client.getCompanyName());
                                }
                                System.out.print("Select Client (1-" + clients.size() + "): ");
                                // Arithmetic operation and assignment (Operators - arithmetic, assignment)
                                int clientChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                // Ternary with relational and logical operators (Operators - ternary, relational, logical)
                                String clientError = (clientChoice < 0 || clientChoice >= clients.size()) ? 
                                    "[ERROR] Invalid client selection!" : "";
                                if (!clientError.isEmpty()) {
                                    System.out.println(clientError);
                                    continue;
                                }

                                // --- STEP 2: SELECT SERVICE ---
                                System.out.println("\n--- Step 2/4: Select Service ---");
                                for (int i = 0; i < services.size(); i++) {
                                    Service service = services.get(i);
                                    System.out.printf("%d. %s (ID: %d, Base Price: ₱ %.2f)%n", 
                                        i + 1, service.getServiceName(), service.getServiceId(), service.getPrice());
                                }
                                System.out.print("Select Service (1-" + services.size() + "): ");
                                int serviceChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                String serviceError = (serviceChoice < 0 || serviceChoice >= services.size()) ? 
                                    "[ERROR] Invalid service selection!" : "";
                                if (!serviceError.isEmpty()) {
                                    System.out.println(serviceError);
                                    continue;
                                }

                                // --- STEP 3: SELECT EMPLOYEE ---
                                System.out.println("\n--- Step 3/4: Select Employee ---");
                                for (int i = 0; i < employees.size(); i++) {
                                    Employee employee = employees.get(i);
                                    System.out.printf("%d. %s (ID: %d, Position: %s, Rate: ₱ %.2f/hr)%n", 
                                        i + 1, employee.name, employee.id, employee.getPosition(), employee.getHourlyRate());
                                }
                                System.out.print("Select Employee (1-" + employees.size() + "): ");
                                int employeeChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                String employeeError = (employeeChoice < 0 || employeeChoice >= employees.size()) ? 
                                    "[ERROR] Invalid employee selection!" : "";
                                if (!employeeError.isEmpty()) {
                                    System.out.println(employeeError);
                                    continue;
                                }

                                // --- STEP 4: ENTER HOURS WORKED ---
                                System.out.println("\n--- Step 4/4: Enter Hours ---");
                                System.out.print("Hours Worked: ");
                                double hoursWorked = inputScanner.nextDouble();
                                inputScanner.nextLine();

                                // Create new ServiceRequest object (Object)
                                ServiceRequest newRequest = new ServiceRequest(
                                    serviceRequests.size() + 1,
                                    clients.get(clientChoice),
                                    services.get(serviceChoice),
                                    employees.get(employeeChoice),
                                    hoursWorked
                                );
                                // Add to ArrayList (ArrayList methods - add)
                                serviceRequests.add(newRequest);
                                
                                // Success confirmation
                                System.out.println("\n[SUCCESS] Service request created!");
                                System.out.printf("Request ID: #%d%n", newRequest.getRequestId());
                                System.out.printf("Total Cost: ₱ %.2f%n", newRequest.calculateTotalCost());
                                break;

                            case 2:
                                // Validate required data exists
                                String missingResource = clients.isEmpty() ? "clients" : 
                                                        (employees.isEmpty() ? "employees" : "");
                                if (!missingResource.isEmpty()) {
                                    System.out.println("\n[ERROR] Cannot assign employee!");
                                    System.out.println("Please ensure there are " + missingResource + ".");
                                    continue;
                                }

                                // --- STEP 1: SELECT CLIENT ---
                                System.out.println("\n--- Step 1/2: Select Client ---");
                                for (int i = 0; i < clients.size(); i++) {
                                    Client client = clients.get(i);
                                    System.out.printf("%d. %s (ID: %d, Company: %s)%n", 
                                        i + 1, client.name, client.id, client.getCompanyName());
                                }
                                System.out.print("Select Client (1-" + clients.size() + "): ");
                                int assignClientChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                String assignClientError = (assignClientChoice < 0 || assignClientChoice >= clients.size()) ? 
                                    "[ERROR] Invalid client selection!" : "";
                                if (!assignClientError.isEmpty()) {
                                    System.out.println(assignClientError);
                                    continue;
                                }

                                // --- STEP 2: SELECT EMPLOYEE ---
                                System.out.println("\n--- Step 2/2: Select Employee ---");
                                for (int i = 0; i < employees.size(); i++) {
                                    Employee employee = employees.get(i);
                                    System.out.printf("%d. %s (ID: %d, Position: %s, Rate: ₱ %.2f/hr)%n", 
                                        i + 1, employee.name, employee.id, employee.getPosition(), employee.getHourlyRate());
                                }
                                System.out.print("Select Employee (1-" + employees.size() + "): ");
                                int assignEmployeeChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                String assignEmployeeError = (assignEmployeeChoice < 0 || assignEmployeeChoice >= employees.size()) ? 
                                    "[ERROR] Invalid employee selection!" : "";
                                if (!assignEmployeeError.isEmpty()) {
                                    System.out.println(assignEmployeeError);
                                    continue;
                                }

                                // Display assignment confirmation
                                Client selectedClient = clients.get(assignClientChoice);
                                Employee selectedEmployee = employees.get(assignEmployeeChoice);
                                System.out.println("\n[SUCCESS] Employee assigned!");
                                System.out.printf("Employee: %s%n", selectedEmployee.name);
                                System.out.printf("Assigned to: %s (%s)%n", 
                                    selectedClient.name, selectedClient.getCompanyName());
                                break;

                            case 3:
                                // -----------------------------------------------------------------------
                                // COMPUTE TOTAL COST
                                // -----------------------------------------------------------------------
                                // Validate using isEmpty method (ArrayList methods - isEmpty, Control statement - if)
                                if (serviceRequests.isEmpty()) {
                                    System.out.println("\n[ERROR] No service requests found!");
                                    System.out.println("Please create a service request first.");
                                    continue;
                                }

                                // Display all service requests for selection
                                System.out.println("\n--- Select Service Request ---");
                                for (int i = 0; i < serviceRequests.size(); i++) {
                                    ServiceRequest request = serviceRequests.get(i);
                                    System.out.printf("%d. Request #%d - %s (Cost: ₱ %.2f)%n", 
                                        i + 1, request.getRequestId(), request.getClient().name, 
                                        request.calculateTotalCost());
                                }
                                System.out.print("Choice (1-" + serviceRequests.size() + "): ");
                                int requestChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                String requestError = (requestChoice < 0 || requestChoice >= serviceRequests.size()) ? 
                                    "[ERROR] Invalid service request selection!" : "";
                                if (!requestError.isEmpty()) {
                                    System.out.println(requestError);
                                    continue;
                                }

                                // Get selected service request
                                ServiceRequest selected = serviceRequests.get(requestChoice);
                                
                                // Display cost computation options
                                System.out.println("\n--- Compute Total Cost ---");
                                System.out.println("1. Without Discount");
                                System.out.println("2. With Discount");
                                System.out.print("Enter your choice: ");
                                int costChoice = inputScanner.nextInt();
                                inputScanner.nextLine();
                                
                                // If-else if-else control statement (Control statement - if else if)
                                if (costChoice == 1) {
                                    // --- CALCULATE WITHOUT DISCOUNT ---
                                    System.out.println("\n========== Cost Summary ==========");
                                    System.out.printf("Total Cost (No Discount): ₱ %.2f%n", 
                                        selected.calculateTotalCost());
                                    System.out.println("==================================");
                                    
                                } else if (costChoice == 2) {
                                    // --- CALCULATE WITH DISCOUNT ---
                                    // Get discount percentage from user
                                    System.out.print("Enter discount percentage (1-100): ");
                                    double discountPercent = inputScanner.nextDouble();
                                    inputScanner.nextLine();
                                    
                                    // Ternary operator with relational operators (Operators - ternary, relational)
                                    String validationError = (discountPercent < 1 || discountPercent > 100) ? 
                                        "[ERROR] Invalid percentage! Must be between 1 and 100." : "";
                                    if (!validationError.isEmpty()) {
                                        System.out.println(validationError);
                                        continue;
                                    }
                                    
                                    // Arithmetic calculations (Operators - arithmetic)
                                    double originalCost = selected.calculateTotalCost();
                                    double discountAmount = (discountPercent / 100) * originalCost;
                                    // Calling overloaded method (Method overloading)
                                    double finalCost = selected.calculateTotalCost(discountAmount);
                                    
                                    System.out.println("\n========== Cost Summary ==========");
                                    System.out.printf("Original Cost:  ₱ %.2f%n", originalCost);
                                    System.out.printf("Discount:       %.0f%% (₱ %.2f)%n", 
                                        discountPercent, discountAmount);
                                    System.out.printf("Final Cost:     ₱ %.2f%n", finalCost);
                                    System.out.println("==================================");
                                    
                                } else {
                                    System.out.println("[ERROR] Invalid choice!");
                                }
                                break;

                            case 0:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("[ERROR] Invalid choice! Please try again.");
                                break;
                        }
                    } while (transactionMenuChoice != 0);
                    break;

                // ===========================================================================================
                // CASE 5: REPORTS
                // ===========================================================================================
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
                                System.out.printf("Total Revenue: ₱ %.2f%n", totalRevenue);
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

                // ===========================================================================================
                // CASE 0: EXIT PROGRAM
                // ===========================================================================================
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
    
    // ===========================================================================================
    // FILE SAVING METHODS
    // ===========================================================================================
    
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

    // ===========================================================================================
    // FILE LOADING METHODS
    // ===========================================================================================
    
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