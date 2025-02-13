package helpers;

import java.awt.*;

public class GraphicHelper {
    private static final Color APPLE_COLOR = Color.RED;
    private static final Color SNAKE_HEAD_COLOR = Color.GREEN;
    private static final Color TAIL_COLOR = Color.RED;

    public static void drawObjects(Graphics g, int appleX, int appleY, int dots, int dotSize, int[] x, int[] y) {
        g.setColor(APPLE_COLOR); //Nastavení barvy pro jablko.
        g.fillRect(appleX, appleY, dotSize, dotSize); //Kreslení jablka.

        for (int z = 0; z < dots; z++) {
            if (z == 0) {
                g.setColor(SNAKE_HEAD_COLOR); //Hlava hada je zelená.
            } else {
                g.setColor(TAIL_COLOR); //Ostatní buňky jsou bílé.
            }
            g.fillRect(x[z], y[z], dotSize, dotSize); //Kreslení buňky hada.
        }

        Toolkit.getDefaultToolkit().sync(); //Synchronizace grafiky pro plynulé zobrazení.
    }
}
