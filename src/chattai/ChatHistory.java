
package chattai;

import java.util.ArrayList;

import java.util.List;

public class ChatHistory implements Observable{
    
    static ChatHistory instance = null;
    static List<ChatMessage> messages;
    static List<Observer> observers;
    
    private ChatHistory() {
        messages = new ArrayList<>();
        observers = new ArrayList<>();
    }
    
    public synchronized void insert(ChatMessage message) {
        messages.add(message);
        notifyObservers(message);
    }
    
    public synchronized static ChatHistory getInstance() {
        if(instance == null) {
         instance = new ChatHistory();
         
      }
      return instance;
        //return ChatHistoryHolder.INSTANCE;
    }



    @Override
    public synchronized void register(CommandInterpreter ci) {
        observers.add(ci);
    }

    @Override
    public void deregister(CommandInterpreter ci) {
       observers.remove(ci);
    }

    @Override
    public synchronized void notifyObservers(ChatMessage m) {
        for(Observer o: this.observers) {
            o.update(m);
        }
    }
    
    private static class ChatHistoryHolder {

        private static final ChatHistory INSTANCE = new ChatHistory();
    }
    
    @Override
    public synchronized String toString() {
        String messageList = "### history: ";
        
        for (ChatMessage cm : messages){
            messageList += cm.toHistory()+ "  | ";
        }
        return messageList;
    }
}
