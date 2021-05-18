import java.util.Objects;

public class Plane {

    private final String planeModel;
    private final String serialNumber;
    private final int numberOfSeats;

    public Plane(String planeModel, String serialNumber, int numberOfSeats) {
        this.planeModel = planeModel;
        this.serialNumber = serialNumber;
        this.numberOfSeats = numberOfSeats;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return numberOfSeats == plane.numberOfSeats && planeModel.equals(plane.planeModel) && serialNumber.equals(plane.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planeModel, serialNumber, numberOfSeats);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "planeModel='" + planeModel + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
