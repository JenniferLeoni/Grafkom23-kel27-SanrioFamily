package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Cylinder extends Circle {
    float radiusZ;
    int stackCount = 380;
    int sectorCount = 160;

    public Cylinder(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
//        this.stackCount = stackCount;
//        this.sectorCount = sectorCount;
//        createBox();
        createCylinder();
        setupVAOVBO();
    }

    public void createCylinder() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (double i = 0; i < 360; i += 0.1) {
            float x = centerPoint.get(0) + radiusX * (float) Math.cos(Math.toRadians(i));
            float y = centerPoint.get(1) + radiusY * (float) Math.sin(Math.toRadians(i));

            temp.add(new Vector3f((float) x, (float) y, 0.0f));
            temp.add(new Vector3f((float) x, (float) y, -radiusZ));

            temp.add(new Vector3f((float) x, (float) y, 0.0f));
        }
        vertices = temp;
    }
}
