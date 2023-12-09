public class Bubble {
    private int x;
    private int y;
    int bubbleSpeed = 15;
    private String answer;

    public Bubble(int x, int height, int bubbleSpeed, String answer) {
        this.x = x;
        this.y = y;
        this.bubbleSpeed = bubbleSpeed;
        this.answer = answer;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void move(){
        x -= bubbleSpeed;
    }

    public String getAnswer(){
        return answer;
    }
}
