package PersonManagement;

class Person {
    protected String Name;
    protected String PhoneNumber;
    protected String Address;
    protected String Gender;
    protected String AnswerOfSecurityQuestion;

    public Person(String name, String phoneNumber, String address, String gender, String answerOfSecurityQuestion) {
        Name = name;
        PhoneNumber = phoneNumber;
        Address = address;
        Gender = gender;
        AnswerOfSecurityQuestion = answerOfSecurityQuestion;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAnswerOfSecurityQuestion() {
        return AnswerOfSecurityQuestion;
    }

    public void setAnswerOfSecurityQuestion(String answerOfSecurityQuestion) {
        AnswerOfSecurityQuestion = answerOfSecurityQuestion;
    }
}
