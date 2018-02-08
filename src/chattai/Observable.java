
package chattai;


public interface Observable {
    public void register(CommandInterpreter ci);
    public void deregister(CommandInterpreter ci);
    void notifyObservers(ChatMessage m);
}
