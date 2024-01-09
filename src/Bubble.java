import javax.swing.*;
import java.awt.*;

public class Bubble extends JPanel {
    String answer;

    public Bubble() {
        setPreferredSize(new Dimension(120, 120));    //Obiekt jest prostokątem o wymiarach 120x120
        setBackground(new Color(0, 168, 190));
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public void paint(Graphics g) {
        super.paint(g);
        if (answer != null) {
            int diameter = Math.min(getWidth(), getHeight());
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;


            Font font = new Font("Arial", Font.BOLD, 40);
            g.setFont(font);
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(answer);
            int textHeight = fm.getHeight();
            int textX = (getWidth() - textWidth) / 2;
            int textY = (getHeight() - textHeight) / 2 + fm.getAscent();
            g.drawOval(x,y,diameter,diameter);      //Rysowanie kształtu bąbla
            g.drawString(answer, textX, textY);     //Wyświetlanie odpowiedzi w środku bąbla
        }
    }
}