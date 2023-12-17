package tu.sofia.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import javax.management.monitor.Monitor;

public class Window {
    private int width;
    private int height;
    private String title;
    private long window;
    public int frames;
    public static long time;
    public Input input;
    private float backgroundR, backgroundG, backgroundB;
    private GLFWWindowSizeCallback windowSizeCallback;
    private boolean isResized, isFullScreen = false;
    private int[] windowX = new int[1], windowY = new int[1];


    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create(){
        if(!GLFW.glfwInit()) {
            System.out.println("Error init of GLFW");
            return;
        }
        input = new Input();
        window = GLFW.glfwCreateWindow(width, height, title, isFullScreen ? GLFW.glfwGetPrimaryMonitor(): 0, 0);
        if(window == 0){
            System.out.println("Error rendering window");
            return;
        }

        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        windowX[0] = (vidMode.width() - width)/2;
        windowY[0] = (vidMode.height() - height)/2;
        GLFW.glfwSetWindowPos(window, windowX[0], windowY[0]);
        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        createCallBacks();

        GLFW.glfwShowWindow(window);

        GLFW.glfwSwapInterval(1);
        time = System.currentTimeMillis();
    }
    private void createCallBacks(){
        windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                Window.this.width = width;
                Window.this.height = height;
                isResized = true;
            }
        };

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetScrollCallback(window, input.getMouseScroll());
        GLFW.glfwSetWindowSizeCallback(window, windowSizeCallback);
    }
    public void update(){
        if(isResized){
            GL11.glViewport(0,0,width,height);
            isResized = false;
        }
        GL11.glClearColor(backgroundR, backgroundG, backgroundB, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
        frames++;
        if(System.currentTimeMillis() > time + 1000){
            GLFW.glfwSetWindowTitle(window, title + "| FPS:" + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers(){
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose(){
        return GLFW.glfwWindowShouldClose(window);
    }
    public void setBackgroundColor(float r, float g, float b){
        backgroundR = r;
        backgroundG = g;
        backgroundB = b;
    }
    public void destroy(){
        input.destroy();
        windowSizeCallback.free();
        GLFW.glfwSetWindowShouldClose(window, true);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
        isResized = true;
        if(isFullScreen) {
            GLFW.glfwGetWindowPos(window, windowX, windowY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
        }
        else{
            GLFW.glfwSetWindowMonitor(window, 0, windowX[0], windowY[0], width, height, 0);
        }
    }
}
