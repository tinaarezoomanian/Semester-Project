/***************************************************************
* file: Basic.java
* author: Kenneth Chau, Tina Arezoomanians, Kelly Lwin
* class: CS 4450.01 (S25-Regular) Computer Graphics
*
* assignment: Final Program Checkpoint 1
* date last modified: 3/25/2025
*
* purpose:
* note:
****************************************************************/

package Program1;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Basic {

    // instance variables
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 480;
    private static final int FRAME_RATE = 60;
    private static final String WINDOW_TITLE = "Final Program Checkpoint 1";

    // method: start
    // purpose: Sets up the graphics and begins rendering.
    public void start() {
        try {
            createWindow();
            initGL();
            render();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    // method: createWindow
    // purpose: Creates and initializes an OpenGL window.
    private void createWindow() throws Exception {
        Display.setFullscreen(false);
        Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
        Display.setTitle(WINDOW_TITLE);
        Display.create();
    }

    // method: initGL
    // purpose: Initializes OpenGL settings and sets the background color.
    private void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WINDOW_WIDTH, 0, WINDOW_HEIGHT, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }

    // method: render
    // purpose: Main rendering loop, handles user input and draws graphics.
    private void render() {
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            try {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glLoadIdentity();
                glColor3f(1.0f, 1.0f, 0.0f);
                glPointSize(10);

                Display.update();
                Display.sync(FRAME_RATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Display.destroy();
    }

    // method: main
    // purpose: Creates an instance of Basic and starts program execution.
    public static void main(String[] args) {
        Basic basic = new Basic();
        basic.start();
    }
}