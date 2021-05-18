import org.postgresql.ds.PGSimpleDataSource;

public class Main {
    public static void main(String[] args) {
        String URL = "jdbc:postgresql://localhost:5432/airport";
        String USER = args[0];
        String PASSWORD = args[1];

        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setURL(URL);
        pgSimpleDataSource.setUser(USER);
        pgSimpleDataSource.setPassword(PASSWORD);
        Airport airport = new Airport(pgSimpleDataSource);
        System.out.printf("Count of possible seats: %s",airport.clientCount());
    }
}
