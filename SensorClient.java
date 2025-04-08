import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SensorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2000);
            FireAlarmService stub = (FireAlarmService) registry.lookup("FireAlarmService");

            BufferedReader br = new BufferedReader(new FileReader("fire_alarm_sensors_70.csv"));
            String line = br.readLine(); // Skip header

            Random rand = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Track last alert time per room (room_number as key)
            Map<String, LocalDateTime> lastAlertTime = new HashMap<>();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String sensorName = parts[1];
                int floor = Integer.parseInt(parts[2]);
                int room = Integer.parseInt(parts[3]);
                String roomKey = floor + "-" + room;

                int smokeLevel = rand.nextInt(10) + 1; // 1 to 10
                int coLevel = rand.nextInt(10) + 1;    // 1 to 10

                LocalDateTime now = LocalDateTime.now();
                String timestamp = now.format(formatter);

                // Only alert if both levels > 8
                if (smokeLevel > 8 && coLevel > 8) {
                    // Check if last alert was more than a minute ago
                    if (!lastAlertTime.containsKey(roomKey) ||
                            Duration.between(lastAlertTime.get(roomKey), now).toMinutes() >= 1) {

                        stub.sendAlert(sensorName, floor, room, smokeLevel, coLevel, timestamp);
                        writeAlertToFile(sensorName, floor, room, timestamp);
                        lastAlertTime.put(roomKey, now);
                    }
                }

                // Optional: Sleep briefly between checks (to simulate time)
                Thread.sleep(500); // 0.5 sec
            }

            br.close();
        } catch (Exception e) {
            System.err.println("Client error: " + e.toString());
            e.printStackTrace();
        }
    }


    public static void writeAlertToFile(String sensorName, int floor, int room, String timestamp) {
        try {
            String json = String.format("{\"sensor\":\"%s\", \"floor\":%d, \"room\":%d, \"timestamp\":\"%s\"}",
                    sensorName, floor, room, timestamp);
            java.io.FileWriter file = new java.io.FileWriter("alerts.json");
            file.write(json);
            file.close();
        } catch (Exception e) {
            System.err.println("File write error: " + e);
        }
    }
    
}
