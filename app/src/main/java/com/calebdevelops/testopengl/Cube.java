package com.calebdevelops.testopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by CXA0589 on 7/1/2014.
 */
public class Cube {

    private FloatBuffer vertexBuffer;  // Buffer for vertex-array
    private FloatBuffer colorBuffer;   // Buffer for color-array
    private ByteBuffer indexBuffer;    // Buffer for index-array

    private float[] vertices = {  // 5 vertices of the pyramid in (x,y,z)
            -1.0f, -1.0f, -1.0f,  // 0. bottom back left
             1.0f, -1.0f, -1.0f,  // 1. bottom back right
             1.0f, -1.0f,  1.0f,  // 2. bottom front right
            -1.0f, -1.0f,  1.0f,  // 3. bottom front left
            -1.0f,  1.0f, -1.0f,  // 4. top back left
             1.0f,  1.0f, -1.0f,  // 5. top back right
             1.0f,  1.0f,  1.0f,  // 6. top front right
            -1.0f,  1.0f,  1.0f,  // 7. top front left
    };

    private float[][] colors = {       // Colors of the 6 faces
            {1.0f, 0.5f, 0.0f, 1.0f},  // 0. orange
            {1.0f, 0.0f, 1.0f, 1.0f},  // 1. violet
            {0.0f, 1.0f, 0.0f, 1.0f},  // 2. green
            {0.0f, 0.0f, 1.0f, 1.0f},  // 3. blue
            {1.0f, 0.0f, 0.0f, 1.0f},  // 4. red
            {1.0f, 1.0f, 0.0f, 1.0f}   // 5. yellow
    };

    private float[] colorsGradient = {  // Colors of the 5 vertices in RGBA
            0.0f, 0.0f, 1.0f, 1.0f,  // 0. blue
            0.0f, 1.0f, 0.0f, 1.0f,  // 1. green
            0.0f, 0.0f, 1.0f, 1.0f,  // 2. blue
            0.0f, 1.0f, 0.0f, 1.0f,  // 3. green
            1.0f, 0.0f, 0.0f, 1.0f,  // 4. red
            1.0f, 0.5f, 0.0f, 1.0f   // 5. orange
    };

    private byte[] indices = { // Vertex indices of the 12 Triangles
            0, 1, 2,   // bottom 1/2
            0, 2, 3,   // bottom 2/2
            4, 6, 5,   // top    1/2
            4, 7, 6,   // top    2/2
            1, 5, 6,   // right  1/2
            1, 6, 2,   // right  2/2
            2, 6, 7,   // front  1/2
            2, 7, 3,   // front  2/2
            7, 4, 0,   // left   1/2
            7, 0, 3,   // left   2/2
            4, 5, 1,   // back   1/2
            4, 1, 0    // back   2/2
    };

    // Constructor - Set up the buffers
    public Cube() {

        // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder()); // Use native byte order
        vertexBuffer = vbb.asFloatBuffer(); // Convert from byte to float
        vertexBuffer.put(vertices);         // Copy data into buffer
        vertexBuffer.position(0);           // Rewind

        // Setup color-array buffer. Colors in float. An float has 4 bytes
        ByteBuffer cbb = ByteBuffer.allocateDirect(colorsGradient.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colorsGradient);
        colorBuffer.position(0);

        // Setup index-array buffer. Indices in byte.
        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    // Draw the shape
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);  // Front face in counter-clockwise orientation

        // Enable arrays and define their buffers
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE,
                indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

}
