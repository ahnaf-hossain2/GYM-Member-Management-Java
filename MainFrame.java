import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;  // Add this line
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame
{
    // UI Components
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JTextArea addressField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JTextField searchField;
    private JTable memberTable;
    private JLabel statusLabel;

    // Controller
    private MemberActionListener actionListeners;

    public MainFrame()
    {
        setTitle("Gym Member Management");
        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 600));

        // Add this line to set the frame icon
        setIconImage(IconManager.getIcon(IconManager.APP_ICON, 32, 32).getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the main content panel with background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("resources/background.jpg");
        backgroundPanel.setLayout(new BorderLayout(10, 10));
        backgroundPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(backgroundPanel);

        // Initialize action listeners
        actionListeners = new MemberActionListener(this);

        // Create and add components
        JPanel headerPanel = createHeaderPanel();
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);

        // Create a split pane for form and table
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(350); // Adjust this value to control division
        splitPane.setResizeWeight(0.5); // Equal resize behavior
        splitPane.setDividerSize(5);
        splitPane.setBorder(null); // Remove border
        splitPane.setOpaque(false);

        GlassPanel formPanel = createFormPanel();
        splitPane.setTopComponent(formPanel);

        GlassPanel tablePanel = createTablePanel();
        // Add vertical space at the top of the table panel
        tablePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        splitPane.setBottomComponent(tablePanel);

        // Add split pane to background panel
        backgroundPanel.add(splitPane, BorderLayout.CENTER);

        // Initialize the table data
        actionListeners.initializeTable();
    }

    private JPanel createHeaderPanel()
    {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setOpaque(false);

        // Left side - App title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        titlePanel.setOpaque(false);

        JLabel iconLabel = new JLabel(IconManager.getIcon(IconManager.MEMBER_ICON, 32, 32));
        titlePanel.add(iconLabel);

        JLabel titleLabel = new JLabel("Member Management");
        titleLabel.setFont(FontManager.getPrimaryBold(FontManager.EXTRA_LARGE));
        titleLabel.setForeground(new Color(75, 0, 130));
        titlePanel.add(titleLabel);

        headerPanel.add(titlePanel, BorderLayout.WEST);

        // Right side - Profile icon
        JLabel profileLabel = new JLabel(IconManager.getIcon(IconManager.PROFILE_ICON, 40, 40));
        headerPanel.add(profileLabel, BorderLayout.EAST);

        return headerPanel;
    }

    private GlassPanel createFormPanel()
    {
        GlassPanel formPanel = new GlassPanel();
        formPanel.setLayout(new BorderLayout(0, 10));

        // Form title
        JLabel formTitleLabel = new JLabel("Member Information");
        formTitleLabel.setFont(FontManager.getPrimaryBold(FontManager.LARGE));
        formTitleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        formPanel.add(formTitleLabel, BorderLayout.NORTH);

        // Main form panel with proper spacing
        JPanel mainFormPanel = new JPanel();
        mainFormPanel.setOpaque(false);
        mainFormPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Member Name (left column)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        JPanel namePanel = new JPanel(new BorderLayout(0, 5));
        namePanel.setOpaque(false);
        JLabel nameLabel = new JLabel("Member Name");
        namePanel.add(nameLabel, BorderLayout.NORTH);
        nameField = createStyledTextField("Enter member name");
        namePanel.add(nameField, BorderLayout.CENTER);
        mainFormPanel.add(namePanel, gbc);

        // Phone Number (right column)
        gbc.gridx = 1;
        gbc.gridy = 0;
        JPanel phonePanel = new JPanel(new BorderLayout(0, 5));
        phonePanel.setOpaque(false);
        JLabel phoneLabel = new JLabel("Phone Number");
        phonePanel.add(phoneLabel, BorderLayout.NORTH);
        phoneField = createStyledTextField("Enter phone number");
        phonePanel.add(phoneField, BorderLayout.CENTER);
        mainFormPanel.add(phonePanel, gbc);

        // Age (left column)
        gbc.gridx = 0;
        gbc.gridy = 1;
        JPanel agePanel = new JPanel(new BorderLayout(0, 5));
        agePanel.setOpaque(false);
        JLabel ageLabel = new JLabel("Age");
        agePanel.add(ageLabel, BorderLayout.NORTH);
        ageField = createStyledTextField("Enter age");
        agePanel.add(ageField, BorderLayout.CENTER);
        mainFormPanel.add(agePanel, gbc);

        // Gender (right column)
        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel genderPanel = new JPanel(new BorderLayout(0, 5));
        genderPanel.setOpaque(false);
        JLabel genderLabel = new JLabel("Gender");
        genderPanel.add(genderLabel, BorderLayout.NORTH);
        genderComboBox = new JComboBox<>(new String[]{"Select gender", "Male", "Female"});
        genderComboBox.setPreferredSize(new Dimension(0, 30));
        genderPanel.add(genderComboBox, BorderLayout.CENTER);
        mainFormPanel.add(genderPanel, gbc);

        // Address (full width)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across both columns
        JPanel addressPanel = new JPanel(new BorderLayout(0, 5));
        addressPanel.setOpaque(false);
        JLabel addressLabel = new JLabel("Address");
        addressPanel.add(addressLabel, BorderLayout.NORTH);
        addressField = new JTextArea(2, 20);
        addressField.setLineWrap(true);
        addressField.setWrapStyleWord(true);
        JScrollPane addressScrollPane = new JScrollPane(addressField);
        addressScrollPane.setPreferredSize(new Dimension(0, 60));
        addressPanel.add(addressScrollPane, BorderLayout.CENTER);
        mainFormPanel.add(addressPanel, gbc);

        // Add some vertical spacing before buttons
        gbc.gridy = 3;
        gbc.weighty = 0.1;  // Reduced weight
        mainFormPanel.add(Box.createVerticalStrut(10), gbc); // Smaller strut

        formPanel.add(mainFormPanel, BorderLayout.CENTER);

        // Buttons panel at the bottom
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.setOpaque(false);

        addButton = createStyledButton("Add Member", IconManager.getIcon(IconManager.ADD_ICON, 16, 16), new Color(75, 0, 130), Color.WHITE);
        addButton.addActionListener(actionListeners.new AddButtonListener());
        buttonsPanel.add(addButton);

        updateButton = createStyledButton("Update Member", IconManager.getIcon(IconManager.UPDATE_ICON, 16, 16), new Color(255, 165, 0), Color.WHITE);
        updateButton.addActionListener(actionListeners.new UpdateButtonListener());
        updateButton.setEnabled(false);
        buttonsPanel.add(updateButton);

        deleteButton = createStyledButton("Delete Member", IconManager.getIcon(IconManager.DELETE_ICON, 16, 16), new Color(220, 20, 60), Color.WHITE);
        deleteButton.addActionListener(actionListeners.new DeleteButtonListener());
        deleteButton.setEnabled(false);
        buttonsPanel.add(deleteButton);

        clearButton = createStyledButton("Clear Fields", IconManager.getIcon(IconManager.CLEAR_ICON, 16, 16), new Color(100, 100, 100), Color.WHITE);
        clearButton.addActionListener(actionListeners.new ClearButtonListener());
        buttonsPanel.add(clearButton);

        formPanel.add(buttonsPanel, BorderLayout.SOUTH);

        return formPanel;
    }

    private GlassPanel createTablePanel()
    {
        GlassPanel tablePanel = new GlassPanel();
        tablePanel.setLayout(new BorderLayout(0, 10));

        // Table header panel with title and search
        JPanel tableHeaderPanel = new JPanel(new BorderLayout());
        tableHeaderPanel.setOpaque(false);

        JLabel tableTitleLabel = new JLabel("Member Records");
        tableTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        tableHeaderPanel.add(tableTitleLabel, BorderLayout.WEST);

        // Search field
        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchPanel.setOpaque(false);
        searchField = new JTextField(15);
        searchField.addActionListener(actionListeners.new SearchListener());
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(actionListeners.new SearchListener());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        tableHeaderPanel.add(searchPanel, BorderLayout.EAST);

        tablePanel.add(tableHeaderPanel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"ID", "Name", "Phone", "Age", "Gender", "Address", "Actions"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return column == 6; // Only actions column is editable
            }
        };

        memberTable = new JTable(model);
        memberTable.setRowHeight(30);
        memberTable.setFillsViewportHeight(true);
        memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add action buttons to the table
        memberTable.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        memberTable.getColumn("Actions").setCellEditor(new ButtonEditor());

        // Customize column widths
        memberTable.getColumnModel().getColumn(0).setPreferredWidth(30);  // ID
        memberTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        memberTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Phone
        memberTable.getColumnModel().getColumn(3).setPreferredWidth(40);  // Age
        memberTable.getColumnModel().getColumn(4).setPreferredWidth(70);  // Gender
        memberTable.getColumnModel().getColumn(5).setPreferredWidth(200); // Address

        JScrollPane scrollPane = new JScrollPane(memberTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Status panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setOpaque(false);
        statusLabel = new JLabel("Showing 0 members");
        statusPanel.add(statusLabel, BorderLayout.WEST);
        tablePanel.add(statusPanel, BorderLayout.SOUTH);

        tablePanel.setPreferredSize(new Dimension(800, 250));
        tablePanel.setMinimumSize(new Dimension(600, 200));

        return tablePanel;
    }

    // Create a method to style text fields
    private JTextField createStyledTextField(String placeholder)
    {
        JTextField textField = new JTextField()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                // Add placeholder text if empty
                if (getText().isEmpty() && !hasFocus())
                {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(new Color(150, 150, 150));
                    g2.setFont(FontManager.getPrimaryRegular(FontManager.SMALL));
                    g2.drawString(placeholder, 8, getHeight() / 2 + 4);
                    g2.dispose();
                }
            }
        };

        // Rounded border
        textField.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10))
        );

        textField.setPreferredSize(new Dimension(0, 30));
        return textField;
    }

    // Custom rounded border class
    class RoundedBorder extends AbstractBorder
    {
        private int radius;
        private Color color;

        public RoundedBorder(int radius, Color color)
        {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
        {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c)
        {
            return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
        }
    }

    private JButton createStyledButton(String text, Icon icon, Color bgColor, Color fgColor)
    {
        JButton button = new JButton(text, icon);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(FontManager.getPrimaryBold(FontManager.SMALL));
        button.setIconTextGap(8);

        // Rounded corners using border
        button.setBorder(new EmptyBorder(8, 15, 8, 15));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    // Custom button renderer for table
    private class ButtonRenderer extends JPanel implements javax.swing.table.TableCellRenderer
    {
        private JButton editButton;
        private JButton deleteButton;

        public ButtonRenderer()
        {
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            setOpaque(true);

            editButton = new JButton("Edit");
            editButton.setPreferredSize(new Dimension(60, 25));
            editButton.setBackground(new Color(70, 130, 180));
            editButton.setForeground(Color.WHITE);
            editButton.setBorderPainted(false);

            deleteButton = new JButton("Del");
            deleteButton.setPreferredSize(new Dimension(60, 25));
            deleteButton.setBackground(new Color(220, 20, 60));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBorderPainted(false);

            add(editButton);
            add(deleteButton);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column)
        {
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }

    // Custom button editor for table
    private class ButtonEditor extends DefaultCellEditor
    {
        private JPanel panel;
        private JButton editButton;
        private JButton deleteButton;
        private int currentRow;

        public ButtonEditor()
        {
            super(new JTextField());

            panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));

            editButton = new JButton("Edit");
            editButton.setPreferredSize(new Dimension(60, 25));
            editButton.setBackground(new Color(70, 130, 180));
            editButton.setForeground(Color.WHITE);
            editButton.setBorderPainted(false);
            editButton.addActionListener(e -> {
                int memberId = (int) memberTable.getValueAt(currentRow, 0);
                Member member = actionListeners.getDAO().getMember(memberId);
                if (member != null) {
                    actionListeners.populateFields(member);
                }
                fireEditingStopped();
            });

            deleteButton = new JButton("Del");
            deleteButton.setPreferredSize(new Dimension(60, 25));
            deleteButton.setBackground(new Color(220, 20, 60));
            deleteButton.setForeground(Color.WHITE);
            deleteButton.setBorderPainted(false);
            deleteButton.addActionListener(e -> {
                int memberId = (int) memberTable.getValueAt(currentRow, 0);
                int confirm = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Are you sure you want to delete this member?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION)
                {
                    actionListeners.getDAO().deleteMember(memberId);
                    actionListeners.initializeTable();
                }
                fireEditingStopped();
            });

            panel.add(editButton);
            panel.add(deleteButton);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column)
        {
            currentRow = row;
            panel.setBackground(table.getSelectionBackground());
            return panel;
        }

        @Override
        public Object getCellEditorValue()
        {
            return "";
        }
    }

    public void updateStatusLabel(int count)
    {
        statusLabel.setText("Showing " + count + " members");
    }

    // Getters for UI components
    public JTextField getNameField()
    {
        return nameField;
    }

    public JTextField getPhoneField()
    {
        return phoneField;
    }

    public JTextField getAgeField()
    {
        return ageField;
    }

    public JComboBox<String> getGenderComboBox()
    {
        return genderComboBox;
    }

    public JTextArea getAddressField()
    {
        return addressField;
    }

    public JButton getAddButton()
    {
        return addButton;
    }

    public JButton getUpdateButton()
    {
        return updateButton;
    }

    public JButton getDeleteButton()
    {
        return deleteButton;
    }

    public JTextField getSearchField()
    {
        return searchField;
    }

    public JTable getMemberTable()
    {
        return memberTable;
    }
}
