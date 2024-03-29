import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoMenu extends JFrame{

    public OknoMenu() {
        setTitle("PŁETWONUREK MATEMATYK");
        setSize(1024, 768);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon logo = new ImageIcon("nurek_z_tlem.png");
        setIconImage(logo.getImage());
        JButton newGame = new JButton("NOWA GRA");
        newGame.setFont(new Font("Arial", Font.BOLD, 20));
        newGame.setBounds(590, 325, 175, 80);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OknoGry oknoGry = new OknoGry();
                oknoGry.pokazOknoGry(); // Włączenie okna gry
                dispose(); // Zamknięcie okna menu
            }
        });

        JButton easy = new JButton("ŁATWY");
        easy.setFont(new Font("Arial", Font.BOLD, 20));
        easy.setBounds(390, 425, 175, 80);
        easy.addActionListener(new ActionListener() {
            @Override                                   //Zmiana limitu tlenu po wciśnięciu przycisku easy, analogiczne działanie w przypadku medium i hard
            public void actionPerformed(ActionEvent e) {
                OknoGry.oxygenLimit = 5;
                JOptionPane.showMessageDialog(null, "Wybrano poziom łatwy");
            }
        });

        JButton medium = new JButton("ŚREDNI");
        medium.setFont(new Font("Arial", Font.BOLD, 20));
        medium.setBounds(590, 425, 175, 80);
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OknoGry.oxygenLimit = 10;
                JOptionPane.showMessageDialog(null, "Wybrano poziom średni");
            }
        });


        JButton hard = new JButton("TRUDNY");
        hard.setFont(new Font("Arial", Font.BOLD, 20));
        hard.setBounds(790, 425, 175, 80);
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OknoGry.oxygenLimit = 20;
                JOptionPane.showMessageDialog(null, "Wybrano poziom trudny");
            }
        });

        JButton exit = new JButton("WYJDŹ Z GRY");
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.setBounds(590, 525, 175, 80);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ImageIcon tlo = new ImageIcon("menu_gry.png");
        JLabel background = new JLabel("", tlo, JLabel.CENTER);
        background.setBounds(0, 0, 1024, 768);

        setLayout(null);
        add(newGame);
        add(medium);
        add(easy);
        add(hard);
        add(exit);
        add(background);
    }

    public void pokazOknoMenu() {
        setVisible(true);
    }
}
