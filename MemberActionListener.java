import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MemberActionListener
{
    private final MainFrame mainFrame;
    private final MemberDAO memberDAO;
    private int currentMemberId = -1;

    public MemberActionListener(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        this.memberDAO = new MemberDAO();
    }

    public MemberDAO getDAO()
    {
        return memberDAO;
    }

    public class AddButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (!validateInputs())
            {
                return;
            }

            Member newMember = createMemberFromInputs();
            memberDAO.addMember(newMember);

            showNotification("Member added successfully!");
            clearFields();
            updateTable(memberDAO.getAllMembers());
        }
    }

    public class UpdateButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (currentMemberId == -1 || !validateInputs())
            {
                return;
            }

            Member member = createMemberFromInputs();
            member.setId(currentMemberId);
            memberDAO.updateMember(member);

            showNotification("Member updated successfully!");
            clearFields();
            updateTable(memberDAO.getAllMembers());
        }
    }

    public class DeleteButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (currentMemberId == -1)
            {
                showError("Please select a member to delete.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(mainFrame,
                    "Are you sure you want to delete this member?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION)
            {
                memberDAO.deleteMember(currentMemberId);
                showNotification("Member deleted successfully!");
                clearFields();
                updateTable(memberDAO.getAllMembers());
            }
        }
    }

    public class ClearButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            clearFields();
        }
    }

    public class SearchListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String query = mainFrame.getSearchField().getText().trim();
            List<Member> results = memberDAO.searchMembers(query);
            updateTable(results);
        }
    }

    public class TableSelectionListener implements ActionListener
    {
        private int memberId;

        public TableSelectionListener(int memberId)
        {
            this.memberId = memberId;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Member member = memberDAO.getMember(memberId);
            if (member != null)
            {
                populateFields(member);
            }
        }
    }

    public void initializeTable()
    {
        updateTable(memberDAO.getAllMembers());
    }

    private Member createMemberFromInputs()
    {
        String name = mainFrame.getNameField().getText().trim();
        String phone = mainFrame.getPhoneField().getText().trim();
        int age = Integer.parseInt(mainFrame.getAgeField().getText().trim());
        String gender = (String) mainFrame.getGenderComboBox().getSelectedItem();
        String address = mainFrame.getAddressField().getText().trim();

        return new Member(0, name, phone, age, gender, address);
    }

    private boolean validateInputs()
    {
        String name = mainFrame.getNameField().getText().trim();
        if (name.isEmpty())
        {
            showError("Please enter a name.");
            return false;
        }

        String phone = mainFrame.getPhoneField().getText().trim();
        if (phone.isEmpty())
        {
            showError("Please enter a phone number.");
            return false;
        }

        String ageText = mainFrame.getAgeField().getText().trim();
        if (ageText.isEmpty())
        {
            showError("Please enter an age.");
            return false;
        }

        try
        {
            int age = Integer.parseInt(ageText);
            if (age <= 0) {
                showError("Please enter a valid age.");
                return false;
            }
        }

        catch (NumberFormatException e)
        {
            showError("Age must be a number.");
            return false;
        }

        if (mainFrame.getGenderComboBox().getSelectedIndex() == 0)
        {
            showError("Please select a gender.");
            return false;
        }

        String address = mainFrame.getAddressField().getText().trim();
        if (address.isEmpty())
        {
            showError("Please enter an address.");
            return false;
        }

        return true;
    }

    public void populateFields(Member member)
    {
        currentMemberId = member.getId();
        mainFrame.getNameField().setText(member.getName());
        mainFrame.getPhoneField().setText(member.getPhone());
        mainFrame.getAgeField().setText(String.valueOf(member.getAge()));
        mainFrame.getGenderComboBox().setSelectedItem(member.getGender());
        mainFrame.getAddressField().setText(member.getAddress());

        // Show update and delete buttons
        mainFrame.getAddButton().setEnabled(false);
        mainFrame.getUpdateButton().setEnabled(true);
        mainFrame.getDeleteButton().setEnabled(true);
    }

    private void clearFields()
    {
        currentMemberId = -1;
        mainFrame.getNameField().setText("");
        mainFrame.getPhoneField().setText("");
        mainFrame.getAgeField().setText("");
        mainFrame.getGenderComboBox().setSelectedIndex(0);
        mainFrame.getAddressField().setText("");

        // Reset buttons
        mainFrame.getAddButton().setEnabled(true);
        mainFrame.getUpdateButton().setEnabled(false);
        mainFrame.getDeleteButton().setEnabled(false);
    }

    private void updateTable(List<Member> members)
    {
        DefaultTableModel model = (DefaultTableModel) mainFrame.getMemberTable().getModel();
        model.setRowCount(0); // Clear table

        for (Member member : members) {
            model.addRow(new Object[]{
                member.getId(),
                member.getName(),
                member.getPhone(),
                member.getAge(),
                member.getGender(),
                member.getAddress()
            });
        }

        mainFrame.updateStatusLabel(members.size());
    }

    private void showError(String message)
    {
        JOptionPane.showMessageDialog(mainFrame, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showNotification(String message)
    {
        JOptionPane.showMessageDialog(mainFrame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
