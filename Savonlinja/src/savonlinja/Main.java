package savonlinja;

import java.io.IOException;

/**
 *
 * @author markokos
 */
public class Main {

    /**
     * Launches the application.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        TicketFinder finder = new TicketFinder();

        Gui gui = new Gui(finder);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);

    }

}
