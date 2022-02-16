package lambda;

import java.util.*;

class PhoneBook {
    private final Map<String, Collection<PhoneNumber>> nameToPhoneNumbersMap = new HashMap<>();

    public void addNewPhoneNumbers(String name, Collection<PhoneNumber> numbers) {
        nameToPhoneNumbersMap.compute(name,(persName,numbs) ->
                Objects.nonNull(numbs) ? new ArrayList<>() { {
                    addAll(numbs); addAll(numbers);
        } } : numbers);
    }

    public void printPhoneBook() {
        nameToPhoneNumbersMap.forEach((person,numbs)->{
            System.out.println(person);
            numbs.forEach(numb-> System.out.println(numb.getType()+": "+numb.getNumber()));
        });
    }
}

enum PhoneNumberType {
    MOBILE, HOME, WORK,
}

class PhoneNumber {

    private final PhoneNumberType type;
    private final String number;

    public PhoneNumber(PhoneNumberType type, String number) {
        this.type = type;
        this.number = number;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }
}