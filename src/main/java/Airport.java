import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Airport {

    private final PGSimpleDataSource simpleDataSource;

    public Airport(PGSimpleDataSource simpleDataSource) {
        this.simpleDataSource = simpleDataSource;
    }

    private List<Pilot> getPilotList() {
        ArrayList<Pilot> pilotsList = new ArrayList<>();
        try (Connection connection = simpleDataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from pilots");
            while (resultSet.next()) {
                Pilot pilot = new Pilot(
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("plane_model")
                );
                pilotsList.add(pilot);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return pilotsList;
    }

    private List<Plane> getPlaneList() {
        ArrayList<Plane> planesList = new ArrayList<>();
        try (Connection connection = simpleDataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from plane");
            while (resultSet.next()) {
                Plane plane = new Plane(
                        resultSet.getString("plane_model"),
                        resultSet.getString("serial_number"),
                        resultSet.getInt("number_of_seats")
                );
                planesList.add(plane);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return planesList;
    }

    private List<Plane> getUsedPlane(Map<String, List<Plane>> planeMap, Map<String, List<Pilot>> pilotMap) {
        ArrayList<Plane> usedPlanes = new ArrayList<>();
        ArrayList<Pilot> usedPilot = new ArrayList<>();
        for (Map.Entry<String, List<Pilot>> pilotEntry : pilotMap.entrySet()) {
            String modelPlane = pilotEntry.getKey();
            List<Pilot> pilots = pilotEntry.getValue();
            pilots.removeAll(usedPilot);
            int numFreePlanes = (pilots.size() % 2 == 0) ? pilots.size() / 2 : (pilots.size() - 1) / 2;
            for (int i = 0; i < numFreePlanes * 2; i++) {
                usedPilot.add(pilots.get(i));
            }
            for (Map.Entry<String, List<Plane>> planeEntry : planeMap.entrySet()) {
                if (planeEntry.getKey().equals(modelPlane)) {
                    List<Plane> planes = planeEntry.getValue();
                    planes.removeAll(usedPlanes);
                    for (Plane plane : planes) {
                        if (plane.getPlaneModel().equals(modelPlane)) {
                            if (numFreePlanes == 0) {
                                break;
                            }
                            usedPlanes.add(plane);
                            numFreePlanes--;
                        }
                    }
                }
            }
        }
        return usedPlanes;
    }

    public int clientCount() {
        List<Pilot> pilotList = getPilotList();
        List<Plane> planeList = getPlaneList();
        Map<String, List<Pilot>> collectPilot = pilotList.stream().collect(Collectors.groupingBy(Pilot::getPlaneModel));
        Map<String, List<Plane>> collectPlane = planeList.stream().collect(Collectors.groupingBy(Plane::getPlaneModel));
        int clientCount = 0;
        List<Plane> usedPlane = getUsedPlane(collectPlane, collectPilot);
        for (Plane plane : usedPlane) {
            clientCount += plane.getNumberOfSeats();
        }
        return clientCount;
    }
}
