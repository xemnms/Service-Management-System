# IT Service Management System

## 📋 System Description

The **IT Service Management System** is a comprehensive Java-based console application designed to streamline and manage IT service operations for service providers. Built using Object-Oriented Programming principles, this system provides an integrated platform for managing clients, employees, services, and service transactions.

The application operates through an interactive menu-driven interface that allows users to perform various operations such as adding and viewing records, creating service requests, assigning employees to clients, calculating costs, and generating business reports. All data is automatically persisted to text files, ensuring information is preserved between sessions.

The system implements a complete business workflow where:
- **Clients** represent customers or companies requiring IT services
- **Employees** are the service providers with specific skills and hourly rates
- **Services** are the IT offerings with base pricing
- **Service Requests** link clients, employees, and services to create billable transactions

The architecture uses inheritance and abstraction through a base `Person` class, which is extended by `Client` and `Employee` classes, demonstrating proper OOP design patterns. The system maintains data integrity through validation and relationship management using Stream API for filtering and matching records.

## ✨ Features

### 1. Client Management
- **Add New Clients**: Register clients with unique ID, name, email, and company information
- **View All Clients**: Display a complete list of all registered clients with their details
- **Data Validation**: Ensures client information is properly formatted before storage
- **Company Tracking**: Associates each client with their respective company name

### 2. Employee Management
- **Add New Employees**: Register employees with ID, name, email, position, and hourly billing rate
- **View All Employees**: Display comprehensive employee directory with position and rate information
- **Position Tracking**: Maintains employee role and specialization details
- **Rate Management**: Stores hourly billing rates for accurate cost calculations

### 3. Service Catalog Management
- **Add Services**: Create IT service offerings with unique ID, descriptive name, and base price
- **View Services**: Browse all available services with pricing information
- **Service Pricing**: Maintain consistent pricing structure for all service offerings
- **Service Portfolio**: Build and manage a complete catalog of IT services

### 4. Service Transaction Management
- **Create Service Request**: 
  - Select client, service, and employee from existing records
  - Input hours worked for the service
  - Automatically generate unique request ID
  - Calculate and display total cost immediately
  - Validate that all required entities exist before creation
  
- **Assign Employee to Client**: 
  - Create employee-client relationships
  - Track which employees are assigned to specific clients
  - Facilitate service delivery coordination
  
- **Compute Total Cost**: 
  - Calculate costs for existing service requests
  - Support for discount application (percentage-based)
  - Display detailed cost breakdown showing original cost, discount amount, and final cost
  - Formula: (Service Price + Employee Hourly Rate) × Hours Worked
  - Option to compute with or without discount

### 5. Business Reporting System
- **Total Clients Report**: View the total number of registered clients in the system
- **Total Services Rendered**: Track the total number of completed service requests
- **Total Revenue Report**: Calculate cumulative revenue from all service requests
- **Real-time Analytics**: Reports reflect current data state instantly

### 6. Data Persistence & File Management
- **Automatic Data Loading**: On startup, loads all existing data from text files
- **Automatic Data Saving**: On exit, saves all current data to text files
- **File-Based Storage**: Uses CSV-formatted text files for each entity type
- **Data Integrity**: Validates file data and skips corrupted records with error reporting
- **Relationship Preservation**: Maintains links between service requests and their related entities

### 7. User Interface Features
- **Menu-Driven Navigation**: Intuitive hierarchical menu system
- **Input Validation**: Validates user input with clear error messages
- **Ternary Operator Validation**: Quick validation checks for lists and selections
- **Success/Error Feedback**: Clear confirmation messages for all operations
- **Step-by-Step Wizards**: Guided multi-step processes for complex operations
- **Data Display**: Formatted output showing all relevant information

### 8. Error Handling & Validation
- **Empty List Detection**: Warns users when attempting operations on empty datasets
- **Range Validation**: Ensures selections are within valid ranges
- **File Error Handling**: Gracefully handles missing or corrupted data files
- **Exception Management**: Try-catch blocks for all file operations
- **Data Format Validation**: Checks data integrity when loading from files
- **Reference Validation**: Ensures foreign key relationships are valid

### 9. Cost Calculation Features
- **Base Cost Calculation**: Combines service price and employee rate
- **Hours Multiplier**: Accounts for time spent on service delivery
- **Discount Support**: Method overloading for discounted cost calculation
- **Percentage-Based Discounts**: Flexible discount system (1-100%)
- **Cost Breakdown Display**: Detailed summary showing all cost components

### 10. Data Management
- **Dynamic Collections**: Uses ArrayList for flexible data storage
- **CRUD Operations**: Create and Read operations fully implemented
- **Search Functionality**: Stream API filtering for finding related records
- **Data Relationships**: Maintains referential integrity between entities
- **In-Memory Operations**: Fast data manipulation during runtime

## 🛠️ Technologies Used

- **Language**: Java
- **File I/O**: BufferedReader, BufferedWriter, FileReader, FileWriter
- **Data Structures**: ArrayList for dynamic data management
- **Input Handling**: Scanner for user input
- **Design Patterns**: Object-Oriented Programming principles

## 📁 Project Structure

```
Service Management System/
│
├── src/
│   └── MainApp.java          # Main application file with all classes
│
├── bin/                       # Compiled .class files
│
├── lib/                       # External libraries (if any)
│
├── clients.txt                # Persistent storage for client data
├── employees.txt              # Persistent storage for employee data
├── services.txt               # Persistent storage for service data
├── service_requests.txt       # Persistent storage for service requests
│
└── README.md                  # Project documentation
```

## 🎯 Java Concepts Demonstrated

This project showcases a wide range of Java programming concepts:

### Object-Oriented Programming
- **Abstraction**: Abstract `Person` class with abstract methods
- **Inheritance**: `Client` and `Employee` classes extend `Person`
- **Encapsulation**: Private fields with public getter methods
- **Polymorphism**: Method overriding and runtime polymorphism

### Core Java Features
- **Data Types**: Both primitive (int, double) and non-primitive (String, custom objects)
- **Variables**: Local, static, and instance variables with shadowing examples
- **Methods**: Static, instance, and abstract methods
- **Access Modifiers**: public, private, and protected
- **Method Overloading**: Multiple `calculateTotalCost()` methods
- **Method Overriding**: `displayInfo()` method in subclasses

### Control Flow
- **Conditionals**: if, if-else, if-else-if, nested if, switch statements
- **Loops**: for, while, do-while, and for-each loops
- **Loop Control**: break, continue, and return statements
- **Ternary Operators**: Conditional expressions for validation

### Advanced Features
- **File Handling**: Reading from and writing to text files
- **Collections**: ArrayList with methods (add, get, size, isEmpty)
- **Exception Handling**: try-catch blocks, multiple catch clauses
- **Lambda Expressions**: Stream API for filtering data
- **Operators**: Arithmetic, relational, logical, assignment, and ternary operators

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line terminal or IDE (IntelliJ IDEA, Eclipse, VS Code)

### Compilation and Execution

#### Using Command Line:
```bash
# Navigate to the project directory
cd "Service Management System"

# Compile the Java file
javac -d bin src/MainApp.java

# Run the application
java -cp bin MainApp
```

#### Using IDE:
1. Open the project in your preferred Java IDE
2. Navigate to `src/MainApp.java`
3. Run the file (usually F5 or Run button)

## 📖 Usage Guide

### Main Menu Options

1. **Manage Clients**
   - Add new clients with ID, name, email, and company information
   - View all registered clients

2. **Manage Employees**
   - Add employees with ID, name, email, position, and hourly rate
   - View all registered employees

3. **Manage Services**
   - Add IT services with unique ID, service name, and price
   - View all available services

4. **Service Transactions**
   - **Create Service Request**: Link a client, service, and employee to create a billable transaction
   - **Assign Employee to Client**: Create employee-client relationships
   - **Compute Total Cost**: Calculate costs with optional discount percentages

5. **Reports**
   - View total number of clients
   - View total services rendered
   - Calculate total revenue from all service requests

6. **Exit**
   - Saves all data to files and exits the application

### Data Flow

1. On startup, the system automatically loads existing data from text files
2. Users interact with the menu-driven interface
3. All changes are stored in memory during the session
4. Upon exit, all data is automatically saved to text files for persistence

## 💾 Data Files Format

### clients.txt
```
clientID,name,email,companyName
```

### employees.txt
```
employeeID,name,email,position,hourlyRate
```

### services.txt
```
serviceID,serviceName,price
```

### service_requests.txt
```
requestID,clientID,serviceID,employeeID,hoursWorked
```

## 🎓 Educational Value

This project serves as an excellent learning resource for:
- Understanding OOP principles in a real-world context
- Implementing CRUD operations (Create, Read, Update, Delete)
- File I/O operations and data persistence
- Menu-driven console applications
- Error handling and input validation
- Code organization and documentation

## 🔮 Future Enhancements

Potential improvements for future versions:
- Database integration (MySQL, PostgreSQL) for better data management
- GUI implementation using JavaFX or Swing
- User authentication and role-based access control
- Advanced reporting with data visualization
- Search and filter functionality
- Edit and delete operations for all entities
- Invoice generation and PDF export
- Email notification system
- Backup and restore functionality

## 👨‍💻 Author

**Axel and Clisha**

## 📝 License

This project is created for educational purposes.

## 🤝 Contributing

This is an educational project. Feel free to fork and modify for your learning purposes.

---
