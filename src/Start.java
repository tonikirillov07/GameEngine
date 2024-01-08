import engine.Window;

public class Start {
    public static void main(String[] args) {
        new Thread(() -> new Window().run()).start();
    }
}