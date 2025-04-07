import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class FireAlarmImpl extends UnicastRemoteObject implements FireAlarmService {

    protected FireAlarmImpl() throws RemoteException {
        super();
    }

    @Override
    public void sendAlert(String sensorName, int floor, int room, int smokeLevel, int coLevel, String timestamp) throws RemoteException {
        System.out.println("🚨 ALERT Triggered!");
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Sensor: " + sensorName + ", Floor: " + floor + ", Room: " + room);
        System.out.println("Smoke Level: " + smokeLevel + ", CO Level: " + coLevel);
        System.out.println("Status: 🔴 CRITICAL — Notify Staff Immediately!\n");
    }
}
