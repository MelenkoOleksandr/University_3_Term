package A;

public class Account {
    public String name;
    public String surname;
    public String phone;
    public Account( String name, String surname, String phone ) {
        this.name = name;
        this.surname =surname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return surname + " " + name + " - " + phone;
    }
}
