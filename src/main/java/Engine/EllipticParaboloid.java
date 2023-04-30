package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class EllipticParaboloid extends Circle{
    float radiusZ;
    int stackCount = 180;
    int sectorCount = 180;

    public EllipticParaboloid(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        createEllipticParaboloid();

        setupVAOVBO();
    }

    public void createEllipticParaboloid() {
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * StackAngle;
            y = radiusY * StackAngle;
            z = radiusZ * StackAngle * StackAngle;

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.z = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.x = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }
}
