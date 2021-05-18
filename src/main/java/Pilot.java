import java.util.Objects;

public class Pilot {

    private final String name;
    private final int age;
    private final String planeModel;

    public Pilot(String name, int age, String planeModel) {
        this.name = name;
        this.age = age;
        this.planeModel = planeModel;
    }

    public String getName() {
        return name;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pilot pilot = (Pilot) o;
        return age == pilot.age && name.equals(pilot.name) && planeModel.equals(pilot.planeModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, planeModel);
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", planeModel='" + planeModel + '\'' +
                '}';
    }
}
