import java.util.*;
public class Question {
    int q;
    Random rng = new Random();
    HashMap <Integer, String> questions = new HashMap<>();
    HashMap <Integer, String> answers = new HashMap<>();

    Question(){
        questions.put(1, "2+2");            //Dodawanie
        answers.put(1,"4");

        questions.put(2, "14+8");
        answers.put(2,"22");

        questions.put(3, "27+13");
        answers.put(3,"40");

        questions.put(4, "36+9");
        answers.put(4,"45");

        questions.put(5, "19+6");
        answers.put(5,"25");

        questions.put(6, "42+13");
        answers.put(6,"55");

        questions.put(7, "11+17");
        answers.put(7,"28");

        questions.put(8, "23+15");
        answers.put(8,"38");

        questions.put(9, "9+14");
        answers.put(9,"23");

        questions.put(10, "18+12");
        answers.put(10,"30");

        questions.put(11, "42-19");          //Odejmowanie
        answers.put(11,"23");

        questions.put(12, "64-28");
        answers.put(12,"36");

        questions.put(13, "45-15");
        answers.put(13,"30");

        questions.put(14, "27-8");
        answers.put(14,"19");

        questions.put(15, "39-13");
        answers.put(15,"26");

        questions.put(16, "56-23");
        answers.put(16,"33");

        questions.put(17, "34-17");
        answers.put(17,"17");

        questions.put(18, "52-14");
        answers.put(18,"38");

        questions.put(19, "77-39");
        answers.put(19,"38");

        questions.put(20, "43-12");
        answers.put(20,"21");

        questions.put(21, "5*6");             //Mnożenie
        answers.put(21,"30");

        questions.put(22, "8*3");
        answers.put(22,"24");

        questions.put(23, "7*4");
        answers.put(23,"28");

        questions.put(24, "4*9");
        answers.put(24,"36");

        questions.put(25, "6*2");
        answers.put(25,"12");

        questions.put(26, "3*7");
        answers.put(26, "21");

        questions.put(27, "9*2");
        answers.put(27,"18");

        questions.put(28, "5*4");
        answers.put(28,"20");

        questions.put(29, "6*5");
        answers.put(29,"30");

        questions.put(30, "2*8");
        answers.put(30, "16");

        questions.put(31, "64/8");           //Dzielenie
        answers.put(31, "8");

        questions.put(32,"36/6");
        answers.put(32, "6");

        questions.put(33, "45/9");
        answers.put(33, "5");

        questions.put(34, "56/7");
        answers.put(34, "8");

        questions.put(35, "45/5");
        answers.put(35, "9");

        questions.put(36, "81/9");
        answers.put(36, "9");

        questions.put(37, "98/14");
        answers.put(37, "7");

        questions.put(38, "120/15");
        answers.put(38, "8");

        questions.put(39, "42/7");
        answers.put(39, "6");

        questions.put(40, "110/11");
        answers.put(40, "10");
    }

    String generateQuestion(){                          //Funkcja losująca pytanie
        q = rng.nextInt(40-1)+1;
        return questions.get(q);
    }
    String generateAnswer(){
        return answers.get(q);
    }
}