// Here MemberDAO associated Member class
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO
{
    private List<Member> members; // Association // Created a list containing only object of Member class
    private static final String DATA_FILE = "members.dat"; // File path will always stay constant for final

    public MemberDAO()
    {
        members = new ArrayList<>();
        loadSampleData();  // A function to load the sample data's
    }

    private void loadSampleData()
    {
        // Sample data
		// Constructor of Member (int id; String name, String phone, int age, String gender, String address)
        members.add(new Member(1, "John Doe", "1234567890", 30, "Male", "123 Main St, New York"));
        members.add(new Member(2, "Jane Smith", "0987654321", 25, "Female", "456 Oak Ave, Los Angeles"));
        members.add(new Member(3, "Robert Johnson", "5551234567", 40, "Male", "789 Pine Rd, Chicago"));
        members.add(new Member(4, "Emily Davis", "7778889999", 22, "Female", "321 Elm Blvd, Houston"));
        members.add(new Member(5, "Michael Brown", "4445556666", 35, "Male", "654 Cedar Ln, Phoenix"));
    }

    public List<Member> getAllMembers() // returns a copy of all members list not the original list
    {
        return new ArrayList<>(members); // created a brand new ArrayList containing all the same Member objects.
    }
    // It's like giving someone a photocopy of an important document instead of the original.
    // They can view all the same information, but any marks they make won't affect your original document.

    public void addMember(Member member)
    {
        int newId = members.isEmpty() ? 1 : // if list empty then set id = 1. otherwise add 1 with the highest existing id.
                    members.stream().mapToInt(Member::getId).max().getAsInt() + 1; // find the maximum id value then add 1
        member.setId(newId);
        members.add(member); // adds the member to internal list
        saveData();  // writes all members to the data file so they're saved permanently.
    }

    public void updateMember(Member member)
    {
        for (int i = 0; i < members.size(); i++) // loop through all members
        {
            if (members.get(i).getId() == member.getId()) // find matching member by id
            {
                members.set(i, member); // replace the updated information
                saveData();
                return;
            }
        }
    }

    public void deleteMember(int id)
    {
        members.removeIf(m -> m.getId() == id);
        saveData();
        // m -> m.getId() == id this is a lambda expression.
		// m represents each member in the list one by one.
        // by -> it checks every member.
        // if id matches then it deletes that id.
    }

    public List<Member> searchMembers(String query)
    {
        if (query == null || query.trim().isEmpty())
        {
            return getAllMembers();
        }

        String lowercaseQuery = query.toLowerCase();
        List<Member> result = new ArrayList<>(); // this will store the members that match then start in a new arraylist

        for (Member member : members)
        {
            if (member.getName().toLowerCase().contains(lowercaseQuery) ||
                member.getPhone().contains(lowercaseQuery) ||
                member.getAddress().toLowerCase().contains(lowercaseQuery))
            {
                result.add(member);
            }
        }

        return result;
    }

    private void saveData()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE)))
        {
            oos.writeObject(members); // writes the members list in a file by converting it into bites. This is Serialization process
			// Member implements serialization thats why it works
        }

        catch (IOException e)
        {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // unchecked means Compiler cannot verify type safety, so it's potentially unsafe
    // but this code does not have that type of harming things because I have proper error handlings and serialization
    // so @SuppressWarnings to surpass that warning message.

    private void loadData()
    {
        File file = new File(DATA_FILE); // create a file object pointing to members.dat then check if exists, below code
        if (file.exists())
        {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
            {
                members = (List<Member>) ois.readObject(); // this is a list of members
            }
            catch (IOException | ClassNotFoundException e)
            {
                System.err.println("Error loading data: " + e.getMessage());
                members = new ArrayList<>(); // instead of crashing this will create an empty list
            }
        }
    }

    public Member getMember(int id)
    {
        for (Member member : members)
        {
            if (member.getId() == id)
            {
                return member;
            }
        }
        return null;
    }
}
