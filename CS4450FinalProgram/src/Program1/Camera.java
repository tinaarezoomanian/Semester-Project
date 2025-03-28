/***************************************************************
* file: Camera.java
* author: Kenneth Chau, Tina Arezoomanians, Kelly Lwin
* class: CS 4450.01 (S25-Regular) Computer Graphics
*
* assignment: Final Program Checkpoint 1
* date last modified: 3/27/2025
*
* purpose: The program sets up and updates camera position and orientation based on user input. 
* note: The program contains code that is assisted but not generated by AI.
****************************************************************/

package Program1;

import org.lwjgl.input.Mouse; 
import org.lwjgl.util.vector.Vector3f;

public class Camera {
    // instance variables
    // camera position and orientation 
    public float x, y, z; 
    public float yaw, pitch;
    
    // sensitivity and movement speed
    public float mouseSensitivity = 0.1f;
    public float movementSpeed = 0.1f;

    // method: Camera constructor
    // purpose: Sets up camera position and orientation.
    public Camera(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = 0;
        this.pitch = 0;
    }
    
    // method: update
    // purpose: Updates orientation based on mouse input and position.
    public void update() {
        // update orientation from mouse movement
        yaw += Mouse.getDX() * mouseSensitivity;
        pitch -= Mouse.getDY() * mouseSensitivity;
        if (pitch > 90)  pitch = 90;
        if (pitch < -90) pitch = -90;

        // update position based on keyboard input
        // use the current yaw for horizontal movement
        Vector3f delta = PlayerMovement.getMovementDelta(movementSpeed, yaw);
        x += delta.x;
        y += delta.y;
        z += delta.z;
    }
    
    // method: applyView
    // purpose: Applies camera transformation to current OpenGL modelview matrix.
    public void applyView() {
        // apply rotations first, then inverse translation
        org.lwjgl.opengl.GL11.glRotatef(pitch, 1, 0, 0);
        org.lwjgl.opengl.GL11.glRotatef(yaw, 0, 1, 0);
        org.lwjgl.opengl.GL11.glTranslatef(-x, -y, -z);
    }
}