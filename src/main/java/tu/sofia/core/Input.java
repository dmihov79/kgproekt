package tu.sofia.core;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

public class Input {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

    public static boolean isKeyDown(int key){ return keys[key]; }
    public static boolean isMouseButtonDown(int mouseButton){ return buttons[mouseButton]; }
    private static double mouseX, mouseY;
    private static double scrollX, scrollY;
    private GLFWKeyCallback keyboard;
    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseButtons;
    private GLFWScrollCallback mouseScroll;

    public Input() {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };
        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };
        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double sx, double sy) {
                scrollX += sx;
                scrollY += sy;
            }
        };
    }
    public void destroy(){
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }
    public static double getMouseX() {
        return mouseX;
    }
    public static double getMouseY() {
        return mouseY;
    }

    public static double getScrollX() {
        return scrollX;
    }

    public static double getScrollY() {
        return scrollY;
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }
    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }
    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public GLFWScrollCallback getMouseScroll() {
        return mouseScroll;
    }
}
