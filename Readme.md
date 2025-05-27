# Gym Management System

A modern, user-friendly desktop application for managing gym members built with Java Swing.

## 🏋️ Features

- **Member Management**: Add, update, delete, and search gym members
- **Modern UI**: Glass panel design with smooth gradients and transparency effects
- **Data Persistence**: Automatic saving and loading of member data
- **Search Functionality**: Quick search through member names, phone numbers, and addresses
- **Custom Fonts & Icons**: Professional typography and iconography
- **Responsive Design**: Clean layout that adapts to window resizing

## 📋 Member Information

Each member profile includes:
- Unique ID (auto-generated)
- Full Name
- Phone Number
- Age
- Gender
- Address

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- No external dependencies required

### Running the Application
1. Clone or download the project
2. Ensure all resource files (fonts, icons) are in the `resources/` folder
3. Compile and run the `Start.java` file:
   ```bash
   javac Start.java
   java Start
   ```

## 📸 Screenshots

### Main Application Interface
![Main Interface](![alt text](image.png))
*The main application window showing the member form and data table*

```

## 🏗️ Architecture

The application follows a clean, modular architecture:

- **`Member.java`** - Data model for gym members
- **`MemberDAO.java`** - Data access layer with file persistence
- **`MainFrame.java`** - Main application window and UI layout
- **`MemberActionListener.java`** - Event handling and business logic
- **`BackgroundPanel.java`** - Custom panel with background image support
- **`GlassPanel.java`** - Transparent panel with glass effect
- **`FontManager.java`** - Font loading and management utility
- **`IconManager.java`** - Icon loading and scaling utility
- **`Start.java`** - Application entry point

## 💾 Data Storage

Member data is automatically saved to `members.dat` file using Java serialization. The application loads existing data on startup and saves changes in real-time.

## 🎨 Design Features

- **Glass Effect Panels**: Semi-transparent panels with rounded corners
- **Custom Background**: Support for background images with gradient fallback
- **Professional Typography**: Custom font loading with multiple sizes
- **Smooth Icons**: Vector-quality icons with automatic scaling
- **Consistent Styling**: Unified color scheme and spacing throughout

## 🔧 Customization

- Replace icons in the `resources/` folder to customize button appearance
- Modify color schemes in the panel classes for different themes
- Add background images by placing them in the `resources/` folder
- Install custom fonts and update `FontManager.java` for different typography

## 🛠️ Technical Features

- **Object-Oriented Design**: Clean separation of concerns with proper encapsulation
- **Event-Driven Architecture**: Responsive UI with proper event handling
- **Data Persistence**: Reliable file-based storage with error handling
- **Resource Management**: Efficient loading and management of fonts and icons
- **Cross-Platform**: Runs on Windows, macOS, and Linux

## 📝 License

This project is open source and available under the MIT License.

---

*Built with VS CODE and CODE BLOCKS using Java Swing*
