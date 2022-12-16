package Data;

import java.io.Serializable;

public class Phone implements Serializable {
    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private String address;
    private String cardNumber;
    private int debit;
    private int credit;
    private int cityCalls;
    private int internationalCalls;

    public Phone(String id, String name, String surname, String patronymic, String address, String cardNumber, int debit, int credit, int cityCalls, int internationalCalls) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = address;
        this.cardNumber = cardNumber;
        this.debit = debit;
        this.credit = credit;
        this.cityCalls = cityCalls;
        this.internationalCalls = internationalCalls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCityCalls() {
        return cityCalls;
    }

    public void setCityCalls(int cityCalls) {
        this.cityCalls = cityCalls;
    }

    public int getInternationalCalls() {
        return internationalCalls;
    }

    public void setInternationalCalls(int internationalCalls) {
        this.internationalCalls = internationalCalls;
    }

    public String toString() {
        return "Phone [id=" + id + ", name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", address=" + address + ", cardNumber=" + cardNumber + ", debit=" + debit + ", credit=" + credit + ", cityCalls=" + cityCalls + ", internationalCalls=" + internationalCalls + "]";
    }
}
