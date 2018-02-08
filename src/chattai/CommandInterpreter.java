
package chattai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;

public class CommandInterpreter implements Observer, Runnable {

    private InputStream in;
    private PrintStream out;

    private String nick = "default";

    private Users users;
    private ChatHistory history;
    private Scanner sc;

    CommandInterpreter(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        sc = new Scanner(in);
        history = ChatHistory.getInstance();
        users = Users.getInstance();
    }

    @Override
    public void run() {
        BufferedReader br = null;
        history.register(this);

        //br = new BufferedReader(new InputStreamReader(in,"utf8"));
        String input = "";

        out.print("### enter nickname");

        nick = sc.nextLine();
        //nick(input);
        users.insert(nick);
        out.print("### Welcome "+nick+"!");

        while (true) {

            //out.print("> ");
            input = sc.nextLine();
            //String input = sc.nextLine();

            //checks if input matches format of "/nick [space] [string]"
            if (input.matches("(/nick).*")) {
                nick(input);

            } else if ("/list".equals(input)) {
                list();

            } else if (input.equals("/history")) {
                history();

            } else if ("/quit".equals(input)) {
                users.remove(nick);
                history.deregister(this);
                out.println("Goodbye.");

            } else if (input.indexOf(0) != '/') {
                String mes = (nick + ":" + System.currentTimeMillis() + ":" + input);
                ChatMessage message = new ChatMessage(mes);
                history.insert(message);
            } else {
                out.println("no can do");
            }

        }

    }

    private void nick(String input) { //todo: make it errorproof
        if (input.equals("/nick")) {
            out.println("### Nickname is " + nick);

        } else {
            users.remove(nick);
            //String[] s = input.split(" ");

            //cuts command part from the input when applying name change
            String tmpnick = input.substring(6);
            if (!users.exists(tmpnick)) {
                nick = tmpnick;
                out.println("### Nick changed to " + nick);
                users.insert(nick);
            } else {
                out.println("### nick in use.");
            }

        }
    }

    private void list() {
        out.print(users);
    }

    private void history() {
        out.print(history);
    }

    @Override
    public void update(ChatMessage m) {
        out.print(m);
    }
}
