import java.io.Serializable;

public class Member implements Serializable // Instances of the Member class can be saved to a file or database to retrieved later on.
{
    private static final long serialVersionUID = 1L;  // this number helps java recognize our member class when loading saved data.
    private int id;
    private String name;
    private String phone;
    private int age;
    private String gender;
    private String address;

    public Member() {}
    // public Member(int id, String name, String phone, int age, String gender, String address)
    // {
    //     this.id = id;
    //     this.name = name;
    //     this.phone = phone;
    //     this.age = age;
    //     this.gender = gender;
    //     this.address = address;
    // }

    // better and faster approach
    public Member(int id, String name, String phone, int age, String gender, String address)
	{
		setId(id);
		setName(name);
		setPhone(phone);
		setAge(age);
		setGender(gender);
		setAddress(address);
	}

	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setAge(int age) { this.age = age; }
	public void setGender(String gender) { this.gender = gender; }
	public void setAddress(String address) { this.address = address; }

	public int getId() { return id; }
	public String getName() { return name; }
	public String getPhone() { return phone; }
	public int getAge() { return age; }
	public String getGender() { return gender; }
	public String getAddress() { return address; }

    @Override // implement a method from an interface
    public String toString()
    {
        return "Member [id=" + id + ", name=" + name + ", phone=" + phone + ", age=" + age + ", gender=" + gender + ", address=" + address + "]";
    }
}
