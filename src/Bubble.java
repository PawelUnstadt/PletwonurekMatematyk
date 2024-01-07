import javax.swing.*;
import java.awt.*;

public class Bubble extends JPanel {

    String answer;
    ImageIcon bubbleImage;
    public Bubble() {
        setPreferredSize(new Dimension(120, 120));
        setBackground(new Color(0, 168, 190));
        bubbleImage = new ImageIcon("bubble.png");
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public void paint(Graphics g) {
        super.paint(g);
        if (answer != null) {
            int diameter = Math.min(getWidth(), getHeight());       //Rysowanie kształtu bąbla
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;

            // Wyświetlanie odpowiedzi w środku bąbla
            Font font = new Font("Arial", Font.BOLD, 40);
            g.setFont(font);
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(answer);
            int textHeight = fm.getHeight();
            int textX = (getWidth() - textWidth) / 2;
            int textY = (getHeight() - textHeight) / 2 + fm.getAscent();
            g.drawOval(x,y,diameter,diameter);
            g.drawString(answer, textX, textY);
        }
    }
}