
package chattai;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage {

    private String message;
    private String name;
    private String timestamp;
    private String text;
    public ChatMessage(String message) {
        this.message = message;
        
        String tmp[] = message.split(":", 3);
            name = tmp[0];
            timestamp = (tmp[1]);
            text = tmp[2];
    }
    private String getDate(long millis) {
        Date date = new Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }
    
    public String toHistory() {
        return getDate(Long.parseLong(timestamp)) +"  "+name +": "+text; 
    }

    public String toString() {
        return message;
    }
}
