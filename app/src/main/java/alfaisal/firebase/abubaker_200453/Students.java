package alfaisal.firebase.abubaker_200453;

public class Students {
    private String id;
    private String name;
    private String surname;
    private String natID;
    private String dob;
    private String gender;

    public Students() {
    }

    public Students(String id, String name, String surname, String natID, String dob, String gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.natID = natID;
        this.dob = dob;
        this.gender = gender;
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

    public String getNatID() {
        return natID;
    }

    public void setNatID(String natID) {
        this.natID = natID;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
