import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

public class MissedCalls {
    Map<String, String> missedCalls = new TreeMap<>();

    public void addMissedCall(String contact){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        missedCalls.put(now.format(formatter), contact);
    }

    public boolean clearMissedCalls() {
        if (missedCalls.isEmpty()) {
            System.out.println("Missed calls list is already empty");
            return false;
        }
        missedCalls.clear();
        return true;
    }

    @Override
    public String toString() {
        if (missedCalls.isEmpty()) {
            return "Missed call list is empty";
        }
        StringBuilder missedCallsList = new StringBuilder();
        for (Map.Entry<String, String> entry : missedCalls.entrySet()){
            missedCallsList.append(entry.getKey());
            missedCallsList.append(" ");
            missedCallsList.append(entry.getValue());
            missedCallsList.append("\n");
        }
        return missedCallsList.toString();
    }
}