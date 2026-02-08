import java.util.*;

abstract class Person {
    protected int id;
    protected String name;
    protected String email;

    public Person(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public abstract void displayInfo();
}

class Client extends Person {
    private String companyName;

    public Client(int id, String name, String email, String companyName) {
        super(id, name, email);
        this.companyName = companyName;
}
    public void displayInfo() {
        System.out.println("Client ID: " + id);
        System.out.println(" | Name: " + name);
        System.out.println(" | Email: " + email);
        System.out.println(" | Company: " + companyName);
    }
}

class Employee extends Person {
    private String position;
    private double hourlyRate;

    public Employee(int id, String name, String email, String position, double hourlyRate) {
        super(id, name, email);
        this.position = position;
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void displayInfo() {
        System.out.println("Employee ID: " + id);
        System.out.println(" | Name: " + name);
        System.out.println(" | Email: " + email);
        System.out.println(" | Position: " + position);
        System.out.println(" | Rate: ₱" + hourlyRate + "/hr");
    }
}

class Service {
    private int serviceId;
    private String serviceName;
    private double price;

    public Service(int serviceId, String serviceName, double price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void displayInfo() {
        System.out.println("Service ID: " + serviceId);
        System.out.println(" | Service: " + serviceName);
        System.out.println(" | Price: ₱" + price);
    }
}

class ServiceRequest { // Everything is subject to change, this is just a placeholder for the service request class, including variable names and types
    private int requestId; 
    private Client client; 
    private Service service; 
    private Employee employee; 
    private double hoursWorked; 

    public ServiceRequest(int requestId, Client client, Service service, Employee employee, double hoursWorked) {
        this.requestId = requestId;
        this.client = client;
        this.service = service;
        this.employee = employee;
        this.hoursWorked = hoursWorked;
    }

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

    public double calculateTotalCost() {
        return (service.getPrice() + employee.getHourlyRate()) * hoursWorked;
    }

    public double calculateTotalCost(double discount) {
        return calculateTotalCost() - discount;
    }

}

public class MainApp {

    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Service> services = new ArrayList<>();
    static ArrayList<ServiceRequest> serviceRequests = new ArrayList<>();

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        int mainMenuChoice;
        do {
            System.out.println("IT Service Management System");
            System.out.println("1. Manage Clients");
            System.out.println("2. Manage Employees");
            System.out.println("3. Manage Services");
            System.out.println("4. Service Transactions");
            System.out.println("5. Reports");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            mainMenuChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline

            switch (mainMenuChoice) {
                case 1:
                    int clientChoice;
                    do {
                        System.out.println("\n--- Manage Clients ---");
                        System.out.println("1. Add Client");
                        System.out.println("2. View Clients");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        clientChoice = inputScanner.nextInt();
                        inputScanner.nextLine();

                        switch (clientChoice) {
                            case 1:
                                System.out.print("Client ID: ");
                                int clientId = inputScanner.nextInt();
                                inputScanner.nextLine();

                                System.out.print("Name: ");
                                String clientName = inputScanner.nextLine();

                                System.out.print("Email: ");
                                String clientEmail = inputScanner.nextLine();

                                System.out.print("Company Name: ");
                                String clientCompany = inputScanner.nextLine();

                                clients.add(new Client(clientId, clientName, clientEmail, clientCompany));
                                System.out.println("Client added successfully!");
                                break;

                            case 2:
                                if (clients.isEmpty()) {
                                    System.out.println("No clients found.");
                                } else {
                                    for (Client client : clients) {
                                        client.displayInfo();
                                    }
                                }
                                break;

                            case 0:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                        }

                    } while (clientChoice != 0);
                    break;

                case 2:
                    int employeeChoice;
                    do {
                        System.out.println("\n--- Manage Employees ---");
                        System.out.println("1. Add Employee");
                        System.out.println("2. View Employees");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        employeeChoice = inputScanner.nextInt();
                        inputScanner.nextLine();

                        switch (employeeChoice) {
                            case 1:
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
                                
                                employees.add(new Employee(empId, empName, empEmail, empPosition, empRate));
                                System.out.println("Employee added successfully!");
                                break;

                            case 2:
                                if (employees.isEmpty()) {
                                    System.out.println("No employees found.");
                                } else {
                                    for (Employee employee : employees) {
                                        employee.displayInfo();
                                    }
                                }
                                break;

                            case 0:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                        }

                    } while (employeeChoice != 0);
                    break;

                case 3:
                    int serviceChoice;
                	    do {
                	        System.out.println("\n--- Manage Services ---");
                	        System.out.println("1. Add Service");
                	        System.out.println("2. View Services");
                	        System.out.println("0. Back");
                	        System.out.print("Enter choice: ");
                	        serviceChoice = inputScanner.nextInt();
                	        inputScanner.nextLine();

                	        switch (serviceChoice) {
                	            case 1:
                	                System.out.print("Service ID: ");
                	                int serId = inputScanner.nextInt();
                	                inputScanner.nextLine();

                	                System.out.print("Service Name: ");
                	                String serName = inputScanner.nextLine();

                	                System.out.print("Service Price: ");
                	                double serPrice = inputScanner.nextDouble();

                	                services.add(new Service(serId, serName, serPrice));
                	                System.out.println("Service added successfully!");
                	                break;

                	            case 2:
                	                if (services.isEmpty()) {
                	                    System.out.println("No services found.");
                	                } else {
                	                    for (Service service : services) {
                	                        service.displayInfo();
                	                    }
                	                }
                	                break;

                	            case 0:
                	                System.out.println("Returning to main menu...");
                	                break;

                	            default:
                	                System.out.println("Invalid choice.");
                	        }

                	    } while (serviceChoice != 0);
                    break;

                case 4:
                    int transactionMenuChoice;
                    do { 
                        System.out.println("\nManage Service Transactions");
                        System.out.println("1. Create Service Request");
                        System.out.println("2. Assign Employee to Client");
                        System.out.println("3. Calculate Total Cost");
                        System.out.println("0. Back");

                        System.out.print("\nEnter your choice: ");
                        transactionMenuChoice = inputScanner.nextInt();
                        inputScanner.nextLine(); // Consume newline

                        switch (transactionMenuChoice) {
                            case 1:
                                if (clients.isEmpty() || employees.isEmpty() || services.isEmpty()) {
                                    System.out.println("\n[ERROR] Cannot create service request!");
                                    System.out.println("Please ensure there are clients, employees, and services.");
                                    continue;
                                }

                                // Step 1: Client Selection
                                System.out.println("\n--- Step 1/4: Select Client ---");
                                for (int i = 0; i < clients.size(); i++) {
                                    Client c = clients.get(i);
                                    System.out.printf("%d. %s (ID: %d, Company: %s)%n", 
                                        i + 1, c.name, c.id, c.getCompanyName());
                                }
                                System.out.print("Select Client (1-" + clients.size() + "): ");
                                int clientChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                if (clientChoice < 0 || clientChoice >= clients.size()) {
                                    System.out.println("[ERROR] Invalid client selection!");
                                    continue;
                                }

                                                                // Step 2: Service Selection
                                System.out.println("\n--- Step 2/4: Select Service ---");
                                for (int i = 0; i < services.size(); i++) {
                                    Service s = services.get(i);
                                    System.out.printf("%d. %s (ID: %d, Base Price: PHP %.2f)%n", 
                                        i + 1, s.getServiceName(), s.getServiceId(), s.getPrice());
                                }
                                System.out.print("Select Service (1-" + services.size() + "): ");
                                int serviceChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                if (serviceChoice < 0 || serviceChoice >= services.size()) {
                                    System.out.println("[ERROR] Invalid service selection!");
                                    continue;
                                }

                                                                // Step 3: Employee Selection
                                System.out.println("\n--- Step 3/4: Select Employee ---");
                                for (int i = 0; i < employees.size(); i++) {
                                    Employee e = employees.get(i);
                                    System.out.printf("%d. %s (ID: %d, Position: %s, Rate: PHP %.2f/hr)%n", 
                                        i + 1, e.name, e.id, e.getPosition(), e.getHourlyRate());
                                }
                                System.out.print("Select Employee (1-" + employees.size() + "): ");
                                int employeeChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                if (employeeChoice < 0 || employeeChoice >= employees.size()) {
                                    System.out.println("[ERROR] Invalid employee selection!");
                                    continue;
                                }

                                                                // Step 4: Hours Worked Input
                                System.out.println("\n--- Step 4/4: Enter Hours ---");
                                System.out.print("Hours Worked: ");
                                double hoursWorked = inputScanner.nextDouble();
                                inputScanner.nextLine();

                                break;

                            case 2:
                                break;

                            case 3:
                                break;

                            case 0:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }

                    } while (transactionMenuChoice != 0);
                    break;

                case 5:
                    //Axel
                    break;

                case 0:
                    System.out.println("Exiting... Goodbye!"); //Sample
                    break;

                default:
                    System.out.println("Invalid choice. Please try again."); //HUI
                    break;
            }

        } while (mainMenuChoice != 0);
        inputScanner.close();
    } 

}





