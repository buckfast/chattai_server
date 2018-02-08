
package chattai;

import java.io.IOException;


public class Chattai {

    public static void main(String[] args) throws IOException {

        //CommandInterpreter ci = new CommandInterpreter(System.in, System.out);
        //ci.run();
        Chatserver cs = new Chatserver();
        cs.serve();
    }
    

}
