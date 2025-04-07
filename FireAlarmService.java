import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FireAlarmService extends Remote {
    void sendAlert(String sensorName, int floor, int room, int smokeLevel, int coLevel, String timestamp) throws RemoteException;
}
