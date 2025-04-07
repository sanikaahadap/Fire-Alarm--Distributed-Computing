import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FireAlarmServer {
    public static void main(String[] args) {
        try {
            FireAlarmImpl obj = new FireAlarmImpl();
            Registry registry = LocateRegistry.createRegistry(2000); // Port 2000
            registry.rebind("FireAlarmService", obj);
            System.out.println("✅ Fire Alarm Service running on port 2000...");
        } catch (Exception e) {
            System.err.println("❌ Server error: " + e.toString());
            e.printStackTrace();
        }
    }
}
