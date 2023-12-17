package tu.sofia;


import org.lwjgl.glfw.GLFW;
import tu.sofia.core.Input;
import tu.sofia.core.Window;

public class Main implements Runnable{

    public Thread game;
    public static Window window;

    public static final int WIDTH = 1280, HEIGHT = 760;

    public void start(){
        game = new Thread(this,"game");
        game.start();
    }
    private void render() {
        window.swapBuffers();
    }

    private void update() {
        window.update();
        if(Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
            System.out.println("X: " + Input.getMouseX() + " Y: " + Input.getMouseY());
    }

    private static void init() {
        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor(1.0f, 0.0f, 0.0f);
        window.create();
    }
    public void run(){
        init();
        while(!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_F11))
                window.setFullScreen(!window.isFullScreen());
        }
        window.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}