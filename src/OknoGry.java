import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OknoGry {

    JFrame frame;
    JLabel player;
    Timer timer;
    int playerSpeed = 10; // Ustaw prędkość postaci
    int deltaX = 0;
    int deltaY = 0;

    OknoGry() {

        frame = new JFrame("PŁETWONUREK MATEMATYK");
        frame.setLayout(new FlowLayout());
        frame.setTitle("PŁETWONUREK MATEMATYK");
        frame.setLayout(null);
        frame.setSize(1024, 768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon gracz = new ImageIcon("nurek.png");

        player = new JLabel("", gracz, JLabel.CENTER);
        player.setBounds(100, 100, 120, 146);
        player.setOpaque(true);

        frame.add(player);

        setupKeyBindings();

        timer = new Timer(16, new ActionListener() { // Timer z aktualizacją co 16 ms (ok. 60 FPS)
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aktualizuj położenie postaci
                updatePlayerPosition();
            }
        });
        timer.start();

        frame.setVisible(true);
    }

    private void setupKeyBindings() {
        InputMap inputMap = player.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = player.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("W"), "upPressed");
        actionMap.put("upPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaY = -playerSpeed;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("released W"), "upReleased");
        actionMap.put("upReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaY = 0;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("S"), "downPressed");
        actionMap.put("downPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaY = playerSpeed;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("released S"), "downReleased");
        actionMap.put("downReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaY = 0;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("A"), "leftPressed");
        actionMap.put("leftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaX = -playerSpeed;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("released A"), "leftReleased");
        actionMap.put("leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaX = 0;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("D"), "rightPressed");
        actionMap.put("rightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaX = playerSpeed;
            }
        });
        inputMap.put(KeyStroke.getKeyStroke("released D"), "rightReleased");
        actionMap.put("rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deltaX = 0;
            }
        });
    }

    private void updatePlayerPosition() {
        // Aktualizuj położenie postaci
        int newX = player.getX() + deltaX;
        int newY = player.getY() + deltaY;

        // Dodaj logikę kolizji z lewą krawędzią okna
        if (newX < 0) {
            newX = 0;
        }

        // Dodaj logikę kolizji z górną krawędzią okna
        if (newY < 0) {
            newY = 0;
        }

        // Dodaj logikę kolizji z prawą krawędzią okna
        if (newX + player.getWidth() > frame.getWidth()) {
            newX = frame.getWidth() - player.getWidth();
        }

        // Dodaj logikę kolizji z dolną krawędzią okna
        if (newY + player.getHeight() > frame.getHeight()) {
            newY = frame.getHeight() - player.getHeight();
        }

        // Ustaw nowe położenie postaci
        player.setLocation(newX, newY);
    }


}
