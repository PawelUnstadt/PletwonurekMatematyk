import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OknoMenu extends JFrame {
    public OknoMenu() {
        this.setLayout(new FlowLayout());
        this.setTitle("PŁETWONUREK MATEMATYK");
        this.setSize(1024, 768);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton nowaGra = new JButton("NOWA GRA");
        nowaGra.setFont(new Font("Arial", Font.BOLD, 20));
        nowaGra.setBounds(590, 325, 175, 80);
        getContentPane().setLayout(null);
        getContentPane().add(nowaGra);
        nowaGra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OknoGry oknoGry = new OknoGry();

            }
        });

        JButton sredni = new JButton("ŚREDNI");
        sredni.setFont(new Font("Arial", Font.BOLD, 20));
        sredni.setBounds(590, 425, 175, 80);
        getContentPane().setLayout(null);
        getContentPane().add(sredni);

        JButton latwy = new JButton("ŁATWY");
        latwy.setFont(new Font("Arial", Font.BOLD, 20));
        latwy.setBounds(390, 425, 175, 80);
        getContentPane().setLayout(null);
        getContentPane().add(latwy);

        JButton trudny = new JButton("TRUDNY");
        trudny.setFont(new Font("Arial", Font.BOLD, 20));
        trudny.setBounds(790, 425, 175, 80);
        getContentPane().setLayout(null);
        getContentPane().add(trudny);

        JButton wyjscie = new JButton("WYJDŹ Z GRY");
        wyjscie.setFont(new Font("Arial", Font.BOLD, 20));
        wyjscie.setBounds(590, 525, 175, 80);
        getContentPane().setLayout(null);
        getContentPane().add(wyjscie);
        wyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int odpowiedz = JOptionPane.showConfirmDialog(null, "Czy chcesz wyjść z gry?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
                if (odpowiedz == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        ImageIcon tlo = new ImageIcon("menu_gry.png");
        JLabel background = new JLabel("", tlo, JLabel.CENTER);
        background.setBounds(0, 0, 1024, 768);
        add(background);
    }
    public void pokazMenu(){setVisible(true);}
}
