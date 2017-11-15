package sample;

public class Candidate {

    private int id;
    private String firstName;
    private String lastName;
    private String aspirantTo;

    public Candidate(int id, String firstName, String lastName, String aspirantTo) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setAspirantTo(aspirantTo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAspirantTo() {
        return aspirantTo;
    }

    public void setAspirantTo(String aspirantTo) {
        this.aspirantTo = aspirantTo;
    }
}
