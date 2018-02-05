package shared;

public class ExperienceDTO {

    private String name;

    private double years;

    public ExperienceDTO(String name, double years) {
        this.name = name;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getYears() {
        return years;
    }

    public void setYears(float years) {
        this.years = years;
    }
}
