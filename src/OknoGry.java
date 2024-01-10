import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;



public class OknoGry extends JPanel implements ActionListener{

    JFrame frame;
    JLabel player;
    JButton close;
    Bubble bubble1;
    Bubble bubble2;
    Bubble bubble3;
    Bubble correctAnswerBubble;
    JLabel QuestionPanel;
    JLabel PointCounter;
    int points = 0;
    Question currentQuestion;
    Timer timer;
    int playerSpeed = 10;
    int deltaX = 0;
    int deltaY = 0;
    JProgressBar progressBar;
    int progressBarMaxValue = 100;
    int progressBarCurrentValue = progressBarMaxValue;
    static int oxygenLimit = 10;


    OknoGry() {
        frame = new JFrame("PŁETWONUREK MATEMATYK");
        frame.setSize(1024, 768);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0, 168, 190));
        frame.add(this);
        frame.setVisible(true);

        JButton close = new JButton("Wyjście");
        close.setFont(new Font("Arial", Font.BOLD, 20));
        close.setBounds(0, 670, 120, 60);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                frame.dispose();
                OknoMenu oknoMenu = new OknoMenu();
                oknoMenu.pokazOknoMenu();
            }
        });

        frame.add(close);

        ImageIcon play = new ImageIcon("nurek.png");
        player = new JLabel("", play, JLabel.CENTER);
        player.setBounds(100, 375, 120, 120);
        player.setOpaque(true);
        frame.add(player);

        progressBar = new JProgressBar(0, progressBarMaxValue);
        progressBar.setBounds(10, 10, 200, 20);
        progressBar.setValue(progressBarMaxValue);
        progressBar.setForeground(Color.GREEN);
        frame.add(progressBar);

        bubble1 = new Bubble();
        bubble1.setBounds(1150,375,120,120);
        frame.add(bubble1);

        bubble2 = new Bubble();
        bubble2.setBounds(1150,175,120,120);
        frame.add(bubble2);

        bubble3 = new Bubble();
        bubble3.setBounds(1150,575,120,120);
        frame.add(bubble3);

        QuestionPanel = new JLabel();
        QuestionPanel.setBounds(400,0,250,50);
        QuestionPanel.setFont(new Font("Arial",Font.BOLD,50));
        QuestionPanel.setForeground(Color.white);
        frame.add(QuestionPanel);

        PointCounter = new JLabel();
        PointCounter.setBounds(810,0,200,50);
        PointCounter.setFont(new Font("Arial",Font.BOLD,38));
        PointCounter.setText("WYNIK:" + points);
        PointCounter.setForeground(Color.white);
        frame.add(PointCounter);


        timer = new Timer(16, this);
        timer.start();

        Timer progressBarTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBarCurrentValue -= oxygenLimit;  //Co 1000 ms progressBar zmniejsza się o wartość oxygenLimit

                if (progressBarCurrentValue <= 0) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        progressBarTimer.setInitialDelay(0);
        progressBarTimer.start();
        setupKeyBindings();
    }

    public void pokazOknoGry(){
        frame.setVisible(true);
    }
    private void showQuestion() {
        if (currentQuestion == null) {
            currentQuestion = new Question();
            String generatedQuestion = currentQuestion.generateQuestion();
            QuestionPanel.setText(generatedQuestion + " = ?");

            // Ustawienie poprawnej odpowiedzi w losowym bąblu
            int correctAnswerBubbleIndex = (int) (Math.random() * 3) + 1;
            switch (correctAnswerBubbleIndex) {
                case 1:
                    bubble1.setAnswer(currentQuestion.generateAnswer());
                    correctAnswerBubble = bubble1;
                    break;
                case 2:
                    bubble2.setAnswer(currentQuestion.generateAnswer());
                    correctAnswerBubble = bubble2;
                    break;
                case 3:
                    bubble3.setAnswer(currentQuestion.generateAnswer());
                    correctAnswerBubble = bubble3;
                    break;
            }


            int incorrectAnswer1 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));
            int incorrectAnswer2 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));

            //Pętla, która nie pozwala na wylosowanie dwóch tych samych błędnych odpowiedzi

            while (incorrectAnswer1 == incorrectAnswer2 || incorrectAnswer1 == Integer.parseInt(currentQuestion.generateAnswer())
                    || incorrectAnswer2 == Integer.parseInt(currentQuestion.generateAnswer())) {
                incorrectAnswer1 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));
                incorrectAnswer2 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));
            }

            switch (correctAnswerBubbleIndex) {                   //Przypisanie dwóm pozostałym bąblom nieprawidłowych odpowiedzi
                case 1:
                    bubble2.setAnswer(String.valueOf(incorrectAnswer1));
                    bubble3.setAnswer(String.valueOf(incorrectAnswer2));
                    break;
                case 2:
                    bubble1.setAnswer(String.valueOf(incorrectAnswer1));
                    bubble3.setAnswer(String.valueOf(incorrectAnswer2));
                    break;
                case 3:
                    bubble1.setAnswer(String.valueOf(incorrectAnswer1));
                    bubble2.setAnswer(String.valueOf(incorrectAnswer2));
                    break;
            }
        }
    }

    private int generateIncorrectAnswer(int correctAnswer) {
        Random random = new Random();
        int randomize = random.nextInt(correctAnswer) + 1;           // Zakres generowanych błędnych odpowiedzi
        return correctAnswer + randomize;
    }


    private void updatePlayerPosition() {                           //Metoda aktualizuje pozycję gracza na planszy
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

    private void moveBubbles() {
        bubble1.setLocation(bubble1.getX() - 7, bubble1.getY());
        bubble2.setLocation(bubble2.getX() - 7, bubble2.getY());
        bubble3.setLocation(bubble3.getX() - 7, bubble3.getY());
    }

    private void collisionDetection() {             //Metoda sprawdza kolizję z obiektami
        Rectangle playerBounds = player.getBounds();

        if (playerBounds.intersects(bubble1.getBounds())) {
            collisionCheck(bubble1);
        }

        if (playerBounds.intersects(bubble2.getBounds())) {
            collisionCheck(bubble2);
        }

        if (playerBounds.intersects(bubble3.getBounds())) {
            collisionCheck(bubble3);
        }
    }

    private void collisionCheck(Bubble bubble){     //Metoda sprawdza, z którym konkretnie obiektem zetknął się gracz
        if (bubble == correctAnswerBubble) {
            nextStage();
            progressBarCurrentValue = 100;
            points++;
            PointCounter.setText("WYNIK: " + points);
        } else {
            nextStage();
            progressBarCurrentValue -= 1;
        }
    }


    private void updateProgressBar() {
        progressBar.setValue(progressBarCurrentValue);
    }

    private void showGameOverDialog() {         //Metoda pokazująca napis na ekranie w przypadku przegranej
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

    private void showGameWonDialog(){               //Metoda pokazująca napis na ekranie w przypadku wygranej
        Object[] options = {"WRÓĆ DO MENU"};
        int choice = JOptionPane.showOptionDialog(frame,
                "GRATULACJE, WYGRAŁEŚ :)",
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
    private void nextStage(){                       //Metoda pokazująca nowe pytanie i generująca nową falę lecących obiektów
        currentQuestion = new Question();
        String generatedQuestion = currentQuestion.generateQuestion();
        QuestionPanel.setText(generatedQuestion + " = ?");

        // Ustawienie poprawnej odpowiedzi w losowym bąblu
        int correctAnswerBubbleIndex = (int) (Math.random() * 3) + 1;
        switch (correctAnswerBubbleIndex) {
            case 1:
                bubble1.setAnswer(currentQuestion.generateAnswer());
                correctAnswerBubble = bubble1;
                break;
            case 2:
                bubble2.setAnswer(currentQuestion.generateAnswer());
                correctAnswerBubble = bubble2;
                break;
            case 3:
                bubble3.setAnswer(currentQuestion.generateAnswer());
                correctAnswerBubble = bubble3;
                break;
        }


        int incorrectAnswer1 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));
        int incorrectAnswer2 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));

        //Pętla, która nie pozwala na wylosowanie dwóch tych samych błędnych odpowiedzi

        while (incorrectAnswer1 == incorrectAnswer2 || incorrectAnswer1 == Integer.parseInt(currentQuestion.generateAnswer())
                || incorrectAnswer2 == Integer.parseInt(currentQuestion.generateAnswer())) {

            incorrectAnswer1 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));
            incorrectAnswer2 = generateIncorrectAnswer(Integer.parseInt(currentQuestion.generateAnswer()));
        }

        switch (correctAnswerBubbleIndex) {                         //Przypisanie dwóm pozostałym bąblom nieprawidłowych odpowiedzi
            case 1:
                bubble2.setAnswer(String.valueOf(incorrectAnswer1));
                bubble3.setAnswer(String.valueOf(incorrectAnswer2));
                break;
            case 2:
                bubble1.setAnswer(String.valueOf(incorrectAnswer1));
                bubble3.setAnswer(String.valueOf(incorrectAnswer2));
                break;
            case 3:
                bubble1.setAnswer(String.valueOf(incorrectAnswer1));
                bubble2.setAnswer(String.valueOf(incorrectAnswer2));
                break;
        }



        bubble1.setLocation(1000, 375);                                 // Ustawienie bąbli na swoje początkowe położenie
        bubble2.setLocation(1000, 175);
        bubble3.setLocation(1000, 575);
    }


    private void setupKeyBindings() {               //Metoda przypisuje funkcję poruszania się do klawiszy WASD
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

    @Override
    public void actionPerformed(ActionEvent e) {      //Przebieg gry
        updatePlayerPosition();
        updateProgressBar();
        showQuestion();
        moveBubbles();
        collisionDetection();

        if (progressBarCurrentValue <= 0) {
            showGameOverDialog();
            timer.stop();
        }
        if(points == 20){
            showGameWonDialog();
            timer.stop();
        }
    }
}