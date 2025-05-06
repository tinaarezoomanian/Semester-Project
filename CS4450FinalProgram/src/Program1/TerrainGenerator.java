package Program1;

import org.lwjgl.opengl.GL11;

public class TerrainGenerator {
    // Dimensions for the terrain grid.
    public static final int WIDTH = 30;
    public static final int DEPTH = 30;
    public static final int MAX_HEIGHT = 10;
    
    // Precomputed top texture for each column (to avoid flashing).
    private int[][] topTextureChoice = new int[WIDTH][DEPTH];
    
    // Load textures using your custom texture loader.
    // These paths assume your texture files (grass.png, sand.png, water.png,
    // dirt.png, stone.png, bedrock.png) are in the /textures/ folder on your classpath.
    private int grassTexture   = MyTextureLoader.loadTexture("/textures/grass.png");
    private int sandTexture    = MyTextureLoader.loadTexture("/textures/sand.png");
    private int waterTexture   = MyTextureLoader.loadTexture("/textures/water.png");
    private int dirtTexture    = MyTextureLoader.loadTexture("/textures/dirt.png");
    private int stoneTexture   = MyTextureLoader.loadTexture("/textures/stone.png");
    private int bedrockTexture = MyTextureLoader.loadTexture("/textures/bedrock.png");
    private int lavaTexture = MyTextureLoader.loadTexture("/textures/lava.png");
    
    // method - TerrainGenerator  
    // purpose - sets random top textures for each column in terrain
    public TerrainGenerator() {
            // Constructor: precompute the top-layer texture choice for each column.
        for (int x = 0; x < WIDTH; x++) {
            for (int z = 0; z < DEPTH; z++) {
                float r = (float) Math.random();
                if (r < 0.33f) {
                    topTextureChoice[x][z] = grassTexture;
                } else if (r < 0.66f) {
                    topTextureChoice[x][z] = sandTexture;
                } else {
                    topTextureChoice[x][z] = waterTexture;
                }
            }
        }
    }
    
    // method - drawTerrain  
    // purpose - draws terrain cubes based on noise height and selected textures
    public void drawTerrain(boolean isHellMode) {
        for (int x = 0; x < WIDTH; x++) {
            for (int z = 0; z < DEPTH; z++) {
                // Generate a noise-based height value for the current column.
                float noiseValue = SimplexNoise.noise(x * 0.1f, z * 0.1f);
                int columnHeight = (int) (((noiseValue + 1) / 2) * MAX_HEIGHT);
                if (columnHeight < 1) {
                    columnHeight = 1;
                }
                
                for (int y = 0; y < columnHeight; y++) {
                    int textureToUse;
                    
                    if (isHellMode) {
                        textureToUse = lavaTexture;
                    } else {
                        if (y == columnHeight - 1) {
                            // Top layer: use the precomputed top texture for this column.
                            textureToUse = topTextureChoice[x][z];
                        } else if (y == 0) {
                            // Bottom layer: always use bedrock.
                            textureToUse = bedrockTexture;
                        } else {
                            // Middle layers: use dirt for the lower half and stone for the upper half.
                            textureToUse = (y < (columnHeight / 2)) ? dirtTexture : stoneTexture;
                        }
                    }
                    
                    // Set the texture for the Cube drawing routine.
                    Cube.currentTextureID = textureToUse;
                    
                    GL11.glPushMatrix();
                    // Translate the cube based on its grid position.
                    // (Multiplying by 2 assumes each cube is 2 units wide.)
                    GL11.glTranslatef(x * 2, y * 2, z * 2);
                    Cube.drawTexturedCube();
                    GL11.glPopMatrix();
                }
            }
        }
    }
}
