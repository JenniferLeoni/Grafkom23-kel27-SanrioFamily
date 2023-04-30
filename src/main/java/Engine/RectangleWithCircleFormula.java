package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class RectangleWithCircleFormula extends Object {

    float x, y;
    Vector3f centerpoint;
    Vector3f radius;

    public RectangleWithCircleFormula(List<ShaderProgram.ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices, Vector3f centerpoint, Vector3f radius, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        this.centerpoint = centerpoint;
        this.radius = radius;
        createRectangle();
        setupVAOVBO();
    }

    public void createRectangle() {
        vertices.clear();
        for (double i = 45; i < 360; i += 90) {
            x = (float) (centerpoint.x + radius.x * Math.cos(Math.toRadians(i)));
            y = (float) (centerpoint.y + radius.y * Math.sin(Math.toRadians(i)));
            vertices.add(new Vector3f(x, y, 0.0f));
        }
    }

    @Override
    public void draw(Camera camera, Projection projection) {
        super.draw(camera, projection);
        drawSetup();
        // Draw the vertices
        //optional
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        glDrawArrays(GL_LINE_LOOP,
                0,
                vertices.size());
        for(Object child:childObject){
            child.draw(camera,projection);
        }
    }

    public void drawSetup(){
        bind();
        uniformsMap.setUniform(
                "uni_color", color);
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

    }

}