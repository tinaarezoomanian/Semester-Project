package Program1;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;

public class MyTextureLoader {
    
    // method - loadTexture  
    // purpose - loads image from path and returns opengl texture id  
    public static int loadTexture(String path) {
        try {
            // Read the image file from the resource path
            InputStream in = MyTextureLoader.class.getResourceAsStream(path);
            if(in == null){
                System.err.println("Could not find resource: " + path);
                return -1;
            }
            BufferedImage image = ImageIO.read(in);
            
            int width = image.getWidth();
            int height = image.getHeight();
            int[] pixels_raw = new int[width * height];
            image.getRGB(0, 0, width, height, pixels_raw, 0, width);
            
            // Create a ByteBuffer to hold pixel data in RGBA format
            ByteBuffer pixels = ByteBuffer.allocateDirect(width * height * 4);
            pixels.order(ByteOrder.nativeOrder());

            // Convert image data (Java uses ARGB)
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels_raw[y * width + x];
                    // Extract individual components (mask & shift)
                    byte a = (byte)((pixel >> 24) & 0xFF);
                    byte r = (byte)((pixel >> 16) & 0xFF);
                    byte g = (byte)((pixel >> 8) & 0xFF);
                    byte b = (byte)(pixel & 0xFF);
                    
                    // Put them in RGBA order
                    pixels.put(r);
                    pixels.put(g);
                    pixels.put(b);
                    pixels.put(a);
                }
            }
            pixels.flip();

            // Generate a new OpenGL texture ID and bind it
            int textureID = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            
            // Set texture parameters
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            
            // Upload the texture data
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height,
                    0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixels);
            
            return textureID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
