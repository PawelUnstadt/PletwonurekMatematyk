import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OknoGry extends JPanel{

    JFrame frame;
    JLabel player;
    Timer timer;
    int playerSpeed = 10;
    int deltaX = 0;
    int deltaY = 0;
    JProgressBar progressBar;
    int progressBarMaxValue = 100;
    int progressBarCurrentValue = progressBarMaxValue;

    OknoGry() {
        frame = new JFrame("PŁETWONUREK MATEMATYK");
        frame.setLayout(null);
        frame.setSize(1024, 768);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);

        JButton close = new JButton("Wyjście");
        close.setFont(new Font("Arial", Font.BOLD, 20));
        close.setBounds(870, 640, 120, 60);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                OknoMenu oknoMenu = new OknoMenu();
                oknoMenu.pokazOknoMenu();
            }
        });

        ImageIcon gracz = new ImageIcon("nurek.png");

        player = new JLabel("", gracz, JLabel.CENTER);
        player.setBounds(100, 100, 120, 146);
        player.setOpaque(true);
        player.setBackground(new Color(0, 168, 190));
        frame.add(player);

        frame.add(close);

        progressBar = new JProgressBar(0, progressBarMaxValue);
        progressBar.setBounds(10, 10, 200, 20);
        progressBar.setValue(progressBarMaxValue);
        progressBar.setForeground(Color.GREEN);
        frame.add(progressBar);

        setupKeyBindings();


        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayerPosition();
                updateProgressBar();

                if (progressBarCurrentValue <= 0) {
                    showGameOverDialog();
                    timer.stop();
                }
            }
        });
        timer.start();

        Timer progressBarTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBarCurrentValue -= 10;
                progressBar.setValue(progressBarCurrentValue);

                if (progressBarCurrentValue <= 0) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        progressBarTimer.setInitialDelay(0);
        progressBarTimer.start();
    }



    private void updatePlayerPosition() {
        int newX = player.getX() + deltaX;
        int newY = player.getY() + deltaY;

        if (newX < 0) {
            newX = 0;
        }

        if (newY < 0) {
            newY = 0;
        }

        if (newX + player.getWidth() > frame.getWidth()) {
            newX = frame.getWidth() - player.getWidth();
        }

        if (newY + player.getHeight() > frame.getHeight()) {
            newY = frame.getHeight() - player.getHeight();
        }

        player.setLocation(newX, newY);
    }

    private void updateProgressBar() {
        progressBar.setValue(progressBarCurrentValue);
    }

    private void showGameOverDialog() {
        Object[] options = {"WRÓĆ DO MENU"};
        int choice = JOptionPane.showOptionDialog(frame,
                "NIESTETY PRZEGRAŁEŚ :(",
                "KONIEC GRY",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            frame.dispose();
            OknoMenu oknoMenu = new OknoMenu();
            oknoMenu.pokazOknoMenu();
        }
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

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "escapePressed");
        actionMap.put("escapePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                OknoMenu oknoMenu = new OknoMenu();
                oknoMenu.pokazOknoMenu();
            }
        });
    }

    public void pokazOknoGry() {
        frame.setVisible(true);
    }
}
