package Data;

import java.util.ArrayList;

public class PhoneDB {
    public ArrayList<Phone> phones = new ArrayList<Phone>();

    public void fillDB() {
        phones.add(new Phone("1", "Иван", "Иванов", "Иванович", "г. Москва, ул. Ленина, д. 1", "1111 1111 1111 1111", 100, 0, 40, 10));
        phones.add(new Phone("2", "Петр", "Петров", "Петрович", "г. Москва, ул. Ленина, д. 2", "2222 2222 2222 2222", 200, 0, 10, 0));
        phones.add(new Phone("3", "Сидор", "Сидоров", "Сидорович", "г. Москва, ул. Ленина, д. 3", "3333 3333 3333 3333", 300, 0, 100, 0));
        phones.add(new Phone("4", "Андрей", "Андреев", "Андреевич", "г. Москва, ул. Ленина, д. 4", "4444 4444 4444 4444", 400, 0, 0, 20));
        phones.add(new Phone("5", "Алексей", "Алексеев", "Алексеевич", "г. Москва, ул. Ленина, д. 5", "5555 5555 5555 5555", 500, 0, 70, 0));
        phones.add(new Phone("6", "Александр", "Александров", "Александрович", "г. Москва, ул. Ленина, д. 6", "6666 6666 6666 6666", 600, 0, 50, 0));
    }

    public ArrayList<Phone> getPhonesWithExceededCityCalls(int limit) {
        ArrayList<Phone> result = new ArrayList<Phone>();
        for (Phone phone : phones) {
            if (phone.getCityCalls() > limit) {
                result.add(phone);
            }
        }
        return result;
    }

    public ArrayList<Phone>  getPhonesUsedInternationalCalls() {
        ArrayList<Phone> result = new ArrayList<Phone>();
        for (Phone phone : phones) {
            if (phone.getInternationalCalls() > 0) {
                result.add(phone);
            }
        }
        return result;
    }

    public ArrayList<Phone> getPhonesSortedBySurname() {
        ArrayList<Phone> result = new ArrayList<Phone>(phones);
        result.sort((Phone p1, Phone p2) -> p1.getSurname().compareTo(p2.getSurname()));
        return result;
    }

}
