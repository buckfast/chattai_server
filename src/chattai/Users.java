
package chattai;

import java.util.HashSet;
import java.util.Set;

public class Users {

    static Set<String> userlist;
    static Users instance = null;

    private Users() {
        userlist = new HashSet<>();
    }

    public synchronized static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public synchronized boolean exists(String username) {
        return userlist.contains(username);
    }

    public synchronized void insert(String username) {
        userlist.add(username);
    }

    public synchronized void remove(String username) {
        userlist.remove(username);
    }

    @Override
    public synchronized String toString() {
        String users = "### users: ";

        for (String u : userlist) {
            users += u + " | ";

        }
        return users;
    }
}
