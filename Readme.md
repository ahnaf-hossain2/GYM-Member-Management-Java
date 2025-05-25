Gym Management System
A Java Swing desktop application for managing gym members with a modern, user-friendly interface.

Overview
The Gym Management System allows gym administrators to efficiently manage member information. With an intuitive user interface featuring glass-effect panels and a responsive design, users can easily add, update, delete, and search for member records.

Features
Member Management: Add, update, and delete member information
Search Functionality: Quickly find members using the search feature
Modern UI: Sleek design with glass panels and stylized components
Data Persistence: Member information is stored locally between sessions
Responsive Layout: Adapts to different window sizes
Screenshots
[Add screenshots of your application here]

Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher
Any operating system that supports Java
Installation
Clone the repository or download the source code:

Navigate to the project directory:

Compile the Java files:

Run the application:

Creating Required Resources
Create a resources folder in the project directory
Add your background image to this folder (e.g., background.jpg)
Add icons for the application:
member_icon.png
add_icon.png
update_icon.png
delete_icon.png
clear_icon.png
profile_icon.png
Usage
Adding a Member
Fill in the member details in the form (name, phone, age, gender, address)
Click the "Add Member" button
The member will appear in the table below
Updating a Member
Click the "Edit" button next to a member in the table
Modify the member details in the form
Click the "Update Member" button
Deleting a Member
Either:
Select a member and click the "Delete Member" button, or
Click the "Del" button next to a member in the table
Confirm the deletion when prompted
Searching for Members
Enter a search term in the search field above the table
Press Enter or click the "Search" button
The table will update to show only matching members
Project Structure
Start.java: Entry point of the application
Member.java: Data model representing a gym member
MemberDAO.java: Data Access Object for CRUD operations
BackgroundPanel.java: Creates a panel with background image
GlassPanel.java: Custom panel with glass effect
IconManager.java: Manages application icons
MainFrame.java: Main application window
MemberActionListener.java: Controller for UI interactions
Technologies Used
Java SE
Swing GUI Framework
Object-Oriented Programming
MVC Architecture Patternd
Future Improvements
Add membership plan management
Implement attendance tracking
Add payment processing functionality
Create reports and analytics
Implement user authentication
Add dark mode option
Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

License
This project is licensed under the MIT License - see the LICENSE file for details.

┌───────────────────────────┐
                         │           Start           │
                         │                           │
                         │ +main(String[]): void     │
                         └────────────┬──────────────┘
                                      │ creates
                                      ▼
┌─────────────────┐  uses  ┌─────────────────────────────────────────┐
│  ThemeManager   │◄───────┤               MainFrame                 │
│                 │        │ extends JFrame                          │
│ -darkModeEnabled│        │                                         │
│ +LIGHT_COLORS   │        │ -nameField: JTextField                  │
│ +DARK_COLORS    │        │ -phoneField: JTextField                 │
│                 │        │ -ageField: JTextField                   │
│ +toggleTheme()  │        │ -genderComboBox: JComboBox<String>      │
└─────────────────┘        │ -addressField: JTextArea                │
                           │ -addButton: JButton                     │
                           │ -updateButton: JButton                  │
                           │ -deleteButton: JButton                  │
                           │ -clearButton: JButton                   │
                           │ -searchField: JTextField                │
                           │ -memberTable: JTable                    │
                           │ -statusLabel: JLabel                    │
                           │ -actionListeners: MemberActionListener  │
                           │                                         │
                           │ +createHeaderPanel(): void              │
                           │ +createFormPanel(): void                │
                           │ +createTablePanel(): void               │
                           └───────┬───────────────────┬─────────────┘
                                   │                   │
                                   │ contains          │ contains
                                   ▼                   ▼
                   ┌───────────────────────┐  ┌──────────────────────┐
                   │      GlassPanel       │  │   BackgroundPanel    │
                   │ extends JPanel        │  │  extends JPanel      │
                   │                       │  │                      │
                   │ +paintComponent()     │  │ -backgroundImage     │
                   └───────────────────────┘  │ -darkBackgroundImage │
                                              │                      │
                                              │ +paintComponent()    │
                                              └──────────────────────┘


┌─────────────────────────────────────────┐  uses   ┌──────────────────────┐
│         MemberActionListener            │◄────────┤       Member         │
│                                         │         │                      │
│ -mainFrame: MainFrame                   │         │ -id: int             │
│ -memberDAO: MemberDAO                   │         │ -name: String        │
│ -currentMemberId: int                   │   manages│ -phone: String      │
│                                         │◄─────────┤ -age: int           │
│ +class AddButtonListener                │         │ -gender: String      │
│ +class UpdateButtonListener             │         │ -address: String     │
│ +class DeleteButtonListener             │         │                      │
│ +class ClearButtonListener              │         │ +getId(): int        │
│ +class SearchListener                   │         │ +getName(): String   │
│ +class TableSelectionListener           │         │ +getPhone(): String  │
│ +populateFields(Member): void           │         │ +getAge(): int       │
│ +initializeTable(): void                │         │ +getGender(): String │
└────────────────┬────────────────────────┘         │ +getAddress(): String│
                 │                                  └──────────────────────┘
                 │ uses
                 ▼
┌────────────────────────────────┐
│          MemberDAO             │
│                                │
│ -members: List<Member>         │
│ -DATA_FILE: String             │
│                                │
│ +getAllMembers(): List<Member> │
│ +addMember(Member): void       │
│ +updateMember(Member): void    │
│ +deleteMember(int): void       │
│ +searchMembers(String): List   │
│ +getMember(int): Member        │
└────────────────────────────────┘
