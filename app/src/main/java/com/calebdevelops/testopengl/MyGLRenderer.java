package com.calebdevelops.testopengl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

/**
 *  OpenGL Custom renderer used with GLSurfaceView
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {
    Context context;   // Application's context

    //Triangle triangle;     // ( NEW )
    Square quad;           // ( NEW )
    Pyramid pyramid; // ( YES )

    Cube cube; // ( YESS )
    Cube2 cube2;

    private static float angleCube = 0;
    private static float speedCube = 2.0f;

    private static float anglePyramid = 0; // Rotational angle in degree for pyramid (NEW_3)
    private static float speedPyramid = 2.0f; // Rotational speed for pyramid (NEW_3)

    // Rotational angle and speed (NEW)
    private float angleTriangle = 0.0f; // (NEW_2)
    private float angleQuad = 0.0f;     // (NEW_2)
    private float speedTriangle = 0.5f; // (NEW_2)
    private float speedQuad = -0.4f;    // (NEW_2)

    // Constructor with global application context
    public MyGLRenderer(Context context) {
        this.context = context;
        pyramid = new Pyramid();   // (NEW_3)
        cube = new Cube(); // (YESSSs)
        cube2 = new Cube2();
        //triangle = new Triangle();   // ( NEW )
        quad = new Square();         // ( NEW )
    }

    // Call back when the surface is first created or re-created
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance

        // You OpenGL|ES initialization code here
        // ......
    }

    // Call back after onSurfaceCreated() or whenever the window's size changes
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float)width / height;

        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset

        // You OpenGL|ES display re-sizing code here
        // ......
    }

    // Call back to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers using clear-value set earlier
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // You OpenGL|ES rendering code here
        gl.glLoadIdentity();     // Reset model-view matrix ( NEW )
        // -- load pyramid
        gl.glTranslatef(-1.5f, 0.0f, -6.0f); // Translate left and into the screen ( NEW )
        gl.glRotatef(anglePyramid, 1.0f, 0.0f, 0.0f); // Rotate (NEW) // was 0.1f, 1.0f, -1.0f
        pyramid.draw(gl);                              // Draw the pyramid (NEW)


        gl.glLoadIdentity();                // Reset the model-view matrix
        gl.glTranslatef(1.5f, 0.0f, -6.0f); // Translate right and into the screen
        gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // rotate about the axis (1,1,1) (NEW)
        cube2.draw(gl);



        anglePyramid += speedPyramid;   // (NEW_3)
        angleCube += speedCube;
        //angleQuad += speedQuad;         // (NEW_2)
    }
}
