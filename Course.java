package model;

public class Course {
    private String code;
    private String name;
    private int creditHours;

    public Course(String code, String name, int creditHours) {
        this.code = code;
        this.name = name;
        this.creditHours = creditHours;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCreditHours() {
        return creditHours;
    }

    @Override
    public String toString() {
        return code + ": " + name + " (" + creditHours + " credits)";
    }
}


