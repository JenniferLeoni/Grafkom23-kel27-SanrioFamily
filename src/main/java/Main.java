import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
                    (1280, 720, "Sanrio Family");
    private ArrayList<Object> kuromi
            = new ArrayList<>();
    private ArrayList<Object> kuromiCopy
            = new ArrayList<>();
    private ArrayList<Object> badtzmaru
            = new ArrayList<>();
    private ArrayList<Object> pompompurin
            = new ArrayList<>();
    private ArrayList<Object> cinna
            = new ArrayList<>();
    private ArrayList<Object> environment
            = new ArrayList<>();
    private ArrayList<Object> cam = new ArrayList<>();
    private ArrayList<Vector3f> curveVertices = new ArrayList<>();

    private MouseInput mouseInput;

    //untuk kuromi
    int countDegreeWalk = 46;
    int countDegreeHead = 16;
    int countDegreeTail = 10;
    int countDegreeEye = 0;
    int countAnimation = 0;
    float countCamMundur = 0.7f;
    float countCamBawah = 0.3f;

    //untuk badtz
    int timer1 = 0;
    int timer2 = 0;
    int timer4 = 0;
    int timer3 = 0;

    //untuk pompom
    int countDegree2, countDegreeWalkFront2, countDegreeWalkBack2, countDegreeBelly2, countDegreeGa2, countDegreeGa22, countDegreeEye2 = 0;

    //untuk cinna
    int jumphands1 = 0;
    int blink = 0;
    int jump = 0;
    int fly = 0;
    int flap = 0;
    int lean = 0;
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    Camera camera = new Camera();

    public void init() {
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        camera.setPosition(0, 0.8f, 3.5f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.0f));
        //code

        cam.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.1f,
                0.1f,
                0.1f
        ));
        cam.get(0).translateObject(0.0f, 0.8f, 3.5f);

    }

    public void initEnvironment() {
        //titik tengah platform (kenapa ada 2? karen antah napa box e dewe error kek g kegambar 1 sisi e :D
        environment.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.005f,
                0.005f,
                0.005f
        ));
        //platfrom merah 1
        environment.get(0).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                3.0f,
                0.2f,
                0.9f
        ));
        //platfrom merah 2
        environment.get(0).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                3.0f,
                0.2f,
                0.9f
        ));
        environment.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(180), 0.0f, 1.0f, 0.0f);

        environment.get(0).translateObject(0.0f, 1.1f, 0.0f);

        //titik tengah tanah
        environment.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.005f,
                0.005f,
                0.005f
        ));
        //tanah 1
        environment.get(1).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 1.0f, 0.55f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                6.0f,
                0.2f,
                7.0f
        ));
        //tanah 2
        environment.get(1).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 1.0f, 0.55f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                7.0f,
                0.2f,
                7.0f
        ));
        environment.get(1).getChildObject().get(1).rotateObject((float) Math.toRadians(180), 0.0f, 1.0f, 0.0f);

        environment.get(1).translateObject(0.0f, -0.5f, 0.0f);

        environment.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.1f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(2).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.22f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(2).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(2).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.11f, 0.11f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(2).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(-0.10f, 0.10f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(2).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f, 1.0f, 0.2f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.04f,
                0.5f,
                0.04f
        ));
        environment.get(2).translateObject(-1.4f, 0.0f, 0.5f);

        environment.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.1f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.22f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.11f, 0.11f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(-0.10f, 0.10f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(3).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f, 1.0f, 0.2f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.04f,
                0.5f,
                0.04f
        ));
        environment.get(3).translateObject(1.4f, 0.0f, -1.0f);

        environment.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.1f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(4).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.22f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(4).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(4).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.11f, 0.11f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(4).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(-0.10f, 0.10f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(4).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f, 1.0f, 0.2f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.04f,
                0.5f,
                0.04f
        ));
        environment.get(4).translateObject(-1.7f, 0.0f, -2.0f);

        environment.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.1f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(5).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.22f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(5).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(5).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(0.11f, 0.11f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(5).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 0.5f, 0.0f),
                Arrays.asList(-0.10f, 0.10f, 0.5f),
                0.1f,
                0.1f,
                0.03f
        ));
        environment.get(5).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.2f, 1.0f, 0.2f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.5f),
                0.04f,
                0.5f,
                0.04f
        ));
        environment.get(5).translateObject(1.15f, 0.0f, 0.4f);

    }

    public void initKuromi() {

        //titik tengah
        kuromi.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.001f,
                0.001f,
                0.001f
        ));
        kuromi.get(0).translateObject(0.0f, 0.0f, 0.0f);
        kuromi.get(0).scaleObject(0.1f, 0.1f, 0.1f);

        //badan, child 0
        kuromi.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).scaleObject(1.25f, 1.4f, 1.2f);
        kuromi.get(0).translateObject(0.0f, 0.1f, 0.0f);

        //pundak kanan, child 1
        kuromi.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(1).scaleObject(0.4f, 0.4f, 0.4f);
        kuromi.get(0).getChildObject().get(1).translateObject(0.145f, 0.197f, 0.0f);

//        lengan kanan, child 1, child 0
        kuromi.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(0.4f, 1.1f, 0.4f);
        kuromi.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(0.215f, 0.04f, 0.0f);
        kuromi.get(0).getChildObject().get(1).getChildObject().get(0).rotateObject((float) Math.toRadians(16f), 0.0f, 0.0f, 1.0f);

        //pundak kiri, child 2
        kuromi.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(2).scaleObject(0.4f, 0.4f, 0.4f);
        kuromi.get(0).getChildObject().get(2).translateObject(-0.145f, 0.197f, 0.0f);

        //lengan kiri, child 2, child 0
        kuromi.get(0).getChildObject().get(2).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(2).getChildObject().get(0).scaleObject(0.4f, 1.1f, 0.4f);
        kuromi.get(0).getChildObject().get(2).getChildObject().get(0).translateObject(-0.215f, 0.04f, 0.0f);
        kuromi.get(0).getChildObject().get(2).getChildObject().get(0).rotateObject((float) Math.toRadians(-16f), 0.0f, 0.0f, 1.0f);

        //pundak, child 3
        kuromi.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).scaleObject(1.35f, 0.55f, 0.6f);
        kuromi.get(0).getChildObject().get(3).translateObject(0.0f, 0.22f, 0.0f);

        //engsel kaki kanan, child 4
        kuromi.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(4).scaleObject(0.3f, 0.3f, 0.3f);
        kuromi.get(0).getChildObject().get(4).translateObject(0.085f, 0.01f, 0.0f);

        //kaki kanan, child 4 0
        kuromi.get(0).getChildObject().get(4).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(4).getChildObject().get(0).scaleObject(0.5f, 1.1f, 0.5f);
        kuromi.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(0.09f, -0.03f, 0.0f);
        kuromi.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(-8f), 0.0f, 0.0f, 1.0f);

        //engsel kaki kiri, child 5
        kuromi.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(5).scaleObject(0.3f, 0.3f, 0.3f);
        kuromi.get(0).getChildObject().get(5).translateObject(-0.085f, 0.01f, 0.0f);

        //kaki kiri, child 5 0
        kuromi.get(0).getChildObject().get(5).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(5).getChildObject().get(0).scaleObject(0.5f, 1.1f, 0.5f);
        kuromi.get(0).getChildObject().get(5).getChildObject().get(0).translateObject(-0.09f, -0.03f, 0.0f);
        kuromi.get(0).getChildObject().get(5).getChildObject().get(0).rotateObject((float) Math.toRadians(8f), 0.0f, 0.0f, 1.0f);

        //tangan kanan
        kuromi.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(1).getChildObject().get(1).scaleObject(0.38f, 0.38f, 0.4f);
        kuromi.get(0).getChildObject().get(1).getChildObject().get(1).translateObject(0.23f, 0.0f, 0.0f);

        //tangan kiri
        kuromi.get(0).getChildObject().get(2).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(2).getChildObject().get(1).scaleObject(0.38f, 0.38f, 0.4f);
        kuromi.get(0).getChildObject().get(2).getChildObject().get(1).translateObject(-0.23f, 0.0f, 0.0f);

        //telapak kaki kanan, kaki kanan, child 4 0
        kuromi.get(0).getChildObject().get(4).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(4).getChildObject().get(1).scaleObject(0.4f, 0.35f, 0.5f);
        kuromi.get(0).getChildObject().get(4).getChildObject().get(1).translateObject(0.08f, -0.15f, 0.02f);

        //telapak kaki kiri, kaki kiri, child 5 0
        kuromi.get(0).getChildObject().get(5).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(5).getChildObject().get(1).scaleObject(0.4f, 0.35f, 0.5f);
        kuromi.get(0).getChildObject().get(5).getChildObject().get(1).translateObject(-0.08f, -0.15f, 0.02f);

        //segitiga1 child 0
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);
        //scale ukuran oui
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).scaleObject(0.17f, 0.34f, 0.05f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(-49f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(12f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(0).translateObject(0.06f, 0.26f, 0.06f);

        //segitiga2 childnya badan
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);
        //scale ukuran oui
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).scaleObject(0.17f, 0.34f, 0.05f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-20f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-49f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-12f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(1).translateObject(-0.06f, 0.26f, 0.06f);

        //segitiga3blkng child 0
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);
        //scale ukuran oui
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).scaleObject(0.17f, 0.34f, 0.05f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(49f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-12f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(2).translateObject(0.06f, 0.26f, -0.06f);

        //segitiga4blkng childnya badan
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);

        //scale ukuran oui
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).scaleObject(0.17f, 0.34f, 0.05f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-20f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(49f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(12f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(3).translateObject(-0.06f, 0.26f, -0.06f);

        //segitiga5kanan childnya badan
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);
        //scale ukuran oui
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).scaleObject(0.17f, 0.34f, 0.05f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(79f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(4).translateObject(0.11f, 0.268f, 0.0f);

        //segitiga6kiri childnya badan
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);
        //scale ukuran oui
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).scaleObject(0.17f, 0.34f, 0.05f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(270f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-79f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(5).translateObject(-0.11f, 0.268f, 0.0f);

        //kepala putih child nya shoulder 3
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 1.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(0).scaleObject(1.65f, 1.35f, 1.2f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(0.0f, 0.40f, 0.01f);

        //kepala hitem child nya shoulder 3
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(1).scaleObject(2.1f, 1.7f, 1.4f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(1).translateObject(0.0f, 0.44f, -0.03f);

        //segitiga item di kepala
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(2).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(2).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(2).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);
        //rotate oui
        kuromi.get(0).getChildObject().get(3).getChildObject().get(2).scaleObject(0.2f, 0.2f, 0.05f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(2).rotateObject((float) Math.toRadians(-25f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(2).translateObject(0.0f, 0.5f, 0.127f);

        //telinga kanan
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(3).rotateObject((float) Math.toRadians(270f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(3).scaleObject(0.6f, 0.7f, 0.2f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(3).rotateObject((float) Math.toRadians(-15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(3).rotateObject((float) Math.toRadians(-35f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(3).translateObject(0.29f, 0.81f, -0.1f);

        //telinga kanan bawah
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(4).scaleObject(0.6f, 0.7f, 0.2f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(4).rotateObject((float) Math.toRadians(-15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(4).rotateObject((float) Math.toRadians(-35f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(4).translateObject(0.0505f, 0.466f, 0.01f);

        //telinga kiri
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(5).rotateObject((float) Math.toRadians(270f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(5).scaleObject(0.6f, 0.7f, 0.2f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(5).rotateObject((float) Math.toRadians(-15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(5).rotateObject((float) Math.toRadians(35f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(5).translateObject(-0.29f, 0.81f, -0.1f);

        //telinga kiri bawah
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(6).scaleObject(0.6f, 0.7f, 0.2f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(6).rotateObject((float) Math.toRadians(-15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(6).rotateObject((float) Math.toRadians(35f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(6).translateObject(-0.0505f, 0.466f, 0.01f);

        //titik telinga kanan
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(7).scaleObject(0.35f, 0.35f, 0.12f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(7).rotateObject((float) Math.toRadians(-15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(7).translateObject(0.31f, 0.82f, -0.095f);

        //titik telinga kiri
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(8).scaleObject(0.35f, 0.35f, 0.12f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(8).rotateObject((float) Math.toRadians(-15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(8).translateObject(-0.31f, 0.82f, -0.095f);

        //titik1
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 1.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(6).scaleObject(0.2f, 0.2f, 0.2f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(6).translateObject(0.14f, 0.16f, 0.16f);

        //titik2
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 1.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(7).scaleObject(0.2f, 0.2f, 0.2f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(7).translateObject(-0.14f, 0.16f, 0.16f);

        //titik3
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 1.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(8).scaleObject(0.2f, 0.2f, 0.2f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(8).translateObject(0.14f, 0.16f, -0.16f);

        //titik4
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 1.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(9).scaleObject(0.2f, 0.2f, 0.2f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(9).translateObject(-0.14f, 0.16f, -0.16f);

        //titik5
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 1.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(10).scaleObject(0.2f, 0.2f, 0.2f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(10).translateObject(-0.26f, 0.24f, -0.0f);

        //titik6
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 1.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(11).scaleObject(0.2f, 0.2f, 0.2f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(11).translateObject(0.26f, 0.24f, -0.0f);


        //hidung
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.0f, 1.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(9).scaleObject(0.15f, 0.1f, 0.13f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(9).translateObject(0.0f, 0.33f, 0.14f);

        //mata kanan
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(10).scaleObject(0.2f, 0.3f, 0.13f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(10).rotateObject((float) Math.toRadians(15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(10).translateObject(0.09f, 0.37f, 0.14f);

        //mata kiri
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(3).getChildObject().get(11).scaleObject(0.2f, 0.3f, 0.13f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(11).rotateObject((float) Math.toRadians(15f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(11).translateObject(-0.09f, 0.37f, 0.14f);

        //mulut object ke 12
        kuromi.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.0001f,
                0.0001f,
                0.0001f
        ));

        List<Vector3f> points = new ArrayList<>();
        points.add(new Vector3f(-0.022f, 0.0f, 0.0f));
        points.add(new Vector3f(0.0f, 0.04f, 0.0f));
        points.add(new Vector3f(0.022f, 0.0f, 0.0f));
        drawCurve(points);
        for (int i = 0; i < curveVertices.size(); i++) {
            Vector3f pointXY = curveVertices.get(i);
            kuromi.get(0).getChildObject().get(3).getChildObject().get(12).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                    Arrays.asList(pointXY.x, pointXY.y, 0.0f),
                    0.005f,
                    0.005f,
                    0.005f
            ));
        }
        kuromi.get(0).getChildObject().get(3).getChildObject().get(12).rotateObject((float) Math.toRadians(210f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(3).getChildObject().get(12).translateObject(0.0f, 0.309f, 0.135f);

        //ekor
        kuromi.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.0001f,
                0.0001f,
                0.0001f
        ));
        points.clear();
        points.add(new Vector3f(0.0f, 0.0f, 0.0f));
        points.add(new Vector3f(0.1f, 0.2f, 0.0f));
        points.add(new Vector3f(0.18f, 0.0f, 0.0f));
        points.add(new Vector3f(0.21f, 0.1f, 0.0f));
        drawCurve(points);
        for (int i = 0; i < curveVertices.size(); i++) {
            Vector3f pointXY = curveVertices.get(i);
            kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                    Arrays.asList(pointXY.x, pointXY.y, 0.0f),
                    0.019f,
                    0.019f,
                    0.019f
            ));
        }
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(-19f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).translateObject(0.0f, 0.004f, -0.09f);

        //segitiga ekor
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().add(new TriangularPrism(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().get(curveVertices.size()).scaleObject(2.0f, 5.0f, 5.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().get(curveVertices.size()).rotateObject((float) Math.toRadians(90f), 0.0f, 1.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().get(curveVertices.size()).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().get(curveVertices.size()).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
        //scale ukuran oui
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().get(curveVertices.size()).scaleObject(0.15f, 0.3f, 0.15f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().get(curveVertices.size()).rotateObject((float) Math.toRadians(150f), 1.0f, 0.0f, 0.0f);
        kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getChildObject().get(curveVertices.size()).translateObject(0.0f, 0.026f, -0.317f);

        kuromi.get(0).translateObject(0.87f, -0.15f, 0.0f);

    }

    public void initBadtz() {
        //titik tengah
        badtzmaru.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).translateObject(0.0f, 0.0f, 0.0f);
        badtzmaru.get(0).scaleObject(0.0f, 0.0f, 0.0f);

        //badan1
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(0).scaleObject(1.6f, 2.45f, 1.5f);
        badtzmaru.get(0).getChildObject().get(0).translateObject(0.08f, -0.05f, 0.0f);
        badtzmaru.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, 1.0f);

        //badan2
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(1).scaleObject(1.6f, 2.45f, 1.5f);
        badtzmaru.get(0).getChildObject().get(1).translateObject(-0.08f, -0.05f, 0.0f);
        badtzmaru.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, -1.0f);

        //badan3
        badtzmaru.get(0).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(2).scaleObject(1.0f, 1.0f, 1.3f);
        badtzmaru.get(0).getChildObject().get(2).translateObject(0.0f, -0.2f, 0.0f);

        //lengan kanan
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(3).scaleObject(2.5f, 0.6f, 0.6f);
        badtzmaru.get(0).getChildObject().get(3).translateObject(0.1f, 0.2f, 0.0f);
        badtzmaru.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(22f), 0.0f, 0.0f, -1.0f);

        //lengan kiri
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(4).scaleObject(2.5f, 0.6f, 0.6f);
        badtzmaru.get(0).getChildObject().get(4).translateObject(-0.1f, 0.2f, 0.0f);
        badtzmaru.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(22f), 0.0f, 0.0f, 1.0f);

        //kaki kanan
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.9f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(5).scaleObject(0.8f, 0.55f, 0.8f);
        badtzmaru.get(0).getChildObject().get(5).translateObject(0.18f, -0.3f, 0.05f);

        //kaki kiri
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.9f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(6).scaleObject(0.8f, 0.55f, 0.8f);
        badtzmaru.get(0).getChildObject().get(6).translateObject(-0.18f, -0.3f, 0.05f);

        //Kepala
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(7).scaleObject(3.2f, 2.2f, 2.5f);
        badtzmaru.get(0).getChildObject().get(7).translateObject(0.0f, 0.4f, 0.0f);

        //rambut1
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(8).scaleObject(0.7f, 1.4f, 0.9f);
        badtzmaru.get(0).getChildObject().get(8).translateObject(-0.03f, 0.56f, 0.0f);
        badtzmaru.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, 1.4f);

        //rambut2
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(9).scaleObject(0.8f, 1.8f, 0.9f);
        badtzmaru.get(0).getChildObject().get(9).translateObject(0.0f, 0.62f, 0.0f);
        badtzmaru.get(0).getChildObject().get(9).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, 0.5f);

        //rambut3
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(10).scaleObject(0.8f, 1.8f, 0.9f);
        badtzmaru.get(0).getChildObject().get(10).translateObject(0.0f, 0.62f, 0.0f);
        badtzmaru.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, -0.5f);

        //rambut4
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(11).scaleObject(0.7f, 1.4f, 0.9f);
        badtzmaru.get(0).getChildObject().get(11).translateObject(0.03f, 0.56f, 0.0f);
        badtzmaru.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, -1.4f);

        //paruh
        badtzmaru.get(0).getChildObject().add(new Limas(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.9f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(12).scaleObject(3.0f, 1.6f, 1.9f);
        badtzmaru.get(0).getChildObject().get(12).translateObject(0.0f, 0.2f, -0.42f);
        badtzmaru.get(0).getChildObject().get(12).rotateObject(1.85f, 0.8f, 0.0f, 0.0f);

        //ekor
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(13).scaleObject(0.2f, 0.3f, 0.9f);
        badtzmaru.get(0).getChildObject().get(13).translateObject(0.0f, -0.18f, -0.12f);
        badtzmaru.get(0).getChildObject().get(13).rotateObject((float) Math.toRadians(20f), 1.0f, 0.0f, 0.0f);

        //mata kanan
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(14).scaleObject(0.85f, 0.6f, 0.6f);
        badtzmaru.get(0).getChildObject().get(14).translateObject(0.14f, 0.35f, 0.258f);

        //mata kiri
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(15).scaleObject(0.85f, 0.6f, 0.6f);
        badtzmaru.get(0).getChildObject().get(15).translateObject(-0.14f, 0.35f, 0.258f);

        //mata kanan2
        badtzmaru.get(0).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(16).scaleObject(1.48f, 0.5f, 0.54f);
        badtzmaru.get(0).getChildObject().get(16).translateObject(0.145f, 0.38f, 0.29f);

        //mata kiri2
        badtzmaru.get(0).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(17).scaleObject(1.48f, 0.5f, 0.54f);
        badtzmaru.get(0).getChildObject().get(17).translateObject(-0.145f, 0.38f, 0.29f);

        //bola mata kanan
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(18).scaleObject(0.2f, 0.2f, 0.1f);
        badtzmaru.get(0).getChildObject().get(18).translateObject(0.12f, 0.4f, 0.322f);

        //bola mata kiri
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(19).scaleObject(0.2f, 0.2f, 0.1f);
        badtzmaru.get(0).getChildObject().get(19).translateObject(-0.12f, 0.4f, 0.322f);

        //kantong
        badtzmaru.get(0).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(20).scaleObject(0.7f, 0.9f, 0.18f);
        badtzmaru.get(0).getChildObject().get(20).translateObject(-0.08f, 0.0f, 0.18f);
        badtzmaru.get(0).getChildObject().get(20).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, -1.0f);
        badtzmaru.get(0).getChildObject().get(20).rotateObject((float) Math.toRadians(10f), 1.0f, 0.0f, 0.0f);

        //dokter thing
        badtzmaru.get(0).getChildObject().add(new Cylinder(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(21).scaleObject(3.3f, 2.5f, 0.3f);
        badtzmaru.get(0).getChildObject().get(21).translateObject(0.003f, 0.05f, -0.48f);
        badtzmaru.get(0).getChildObject().get(21).rotateObject((float) Math.toRadians(85f), 1.0f, 0.0f, 0.0f);

        //dokter thing2
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.8f, 0.8f, 0.8f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(22).scaleObject(0.7f, 0.7f, 0.1f);
        badtzmaru.get(0).getChildObject().get(22).translateObject(0.0f, 0.55f, 0.315f);

        //dokter thing3
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(23).scaleObject(0.2f, 0.2f, 0.1f);
        badtzmaru.get(0).getChildObject().get(23).translateObject(0.0f, 0.545f, 0.33f);

        //walky talkie
        badtzmaru.get(0).getChildObject().get(4).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(4).getChildObject().get(0).scaleObject(0.5f, 1.1f, 0.2f);
        badtzmaru.get(0).getChildObject().get(4).getChildObject().get(0).translateObject(-0.4f, 0.1f, 0.07f);
        badtzmaru.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, 0.5f);

        //walky talkie2
        badtzmaru.get(0).getChildObject().get(4).getChildObject().add(new Box(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        badtzmaru.get(0).getChildObject().get(4).getChildObject().get(1).scaleObject(0.1f, 0.3f, 0.1f);
        badtzmaru.get(0).getChildObject().get(4).getChildObject().get(1).translateObject(-0.4f, 0.185f, 0.07f);
        badtzmaru.get(0).getChildObject().get(4).getChildObject().get(1).rotateObject((float) Math.toRadians(20f), 0.0f, 0.0f, 0.5f);

        //kurva
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 1.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.00f,
                0.00f,
                0.00f
        ));

        List<Vector3f> points = new ArrayList<>();
        points.add(new Vector3f(0.08f, 0.0f, 0.0f));
        points.add(new Vector3f(0.45f, 0.6f, 0.0f));
        points.add(new Vector3f(0.82f, 0.0f, 0.0f));
        drawCurve(points);

        for (int i = 0; i < curveVertices.size(); i++) {
            Vector3f pointXY = curveVertices.get(i);
            badtzmaru.get(0).getChildObject().get(24).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.9f, 0.9f, 0.9f, 0.0f),
                    Arrays.asList(pointXY.x, pointXY.y, 0.0f),
                    0.01f,
                    0.01f,
                    0.01f
            ));
        }
        badtzmaru.get(0).getChildObject().get(24).rotateObject((float) Math.toRadians(150f), 1.0f, 0.0f, 0.0f);
        badtzmaru.get(0).getChildObject().get(24).translateObject(-0.45f, 0.35f, 0.1f);

        //kurva2
        badtzmaru.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 1.0f, 0.0f, 0.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.00f,
                0.00f,
                0.00f
        ));

        List<Vector3f> point = new ArrayList<>();
        point.add(new Vector3f(0.3f, 0.0f, 0.0f));
        point.add(new Vector3f(0.45f, -0.45f, 0.0f));
        point.add(new Vector3f(0.5f, -0.05f, 0.0f));
        drawCurve(point);

        for (int i = 0; i < curveVertices.size(); i++) {
            Vector3f pointXY = curveVertices.get(i);
            badtzmaru.get(0).getChildObject().get(25).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.9f, 0.9f, 0.9f, 0.0f),
                    Arrays.asList(pointXY.x, pointXY.y, 0.0f),
                    0.01f,
                    0.01f,
                    0.01f
            ));
        }
        badtzmaru.get(0).getChildObject().get(25).translateObject(-0.3f, 0.08f, 0.25f);

    }

    public void initPomPom() {
        //titik tengah (parent)
        pompompurin.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).scaleObject(0.1f, 0.1f, 0.1f);

        //badan (child 0)
        pompompurin.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(0).scaleObject(2.2f, 2.35f, 2.2f);

        //pita kanan (child 0 of child 0)
        pompompurin.get(0).getChildObject().get(0).getChildObject().add(new Cone(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.1f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(0).scaleObject(0.2f, 0.16f, 0.1f);
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(-30f), 1.0f, 0.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(0).translateObject(-0.01f, 0.13f, 0.25f);

        //pita kiri (child 1 of child 0)
        pompompurin.get(0).getChildObject().get(0).getChildObject().add(new Cone(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.1f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(1).scaleObject(0.2f, 0.16f, 0.1f);
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-30f), 1.0f, 0.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(1).translateObject(0.01f, 0.13f, 0.25f);

        //ekor (child 2 of child 0)
        pompompurin.get(0).getChildObject().get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(2).scaleObject(0.4f, 1.0f, 0.4f);
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-50f), 1.0f, 0.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(0).getChildObject().get(2).translateObject(0.0f, -0.1f, -0.25f);

        //kepala (child 1)
        pompompurin.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).scaleObject(1.8f, 1.75f, 1.8f);
        pompompurin.get(0).getChildObject().get(1).translateObject(0.0f, 0.27f, 0.0f);

        //mata kanan (child 0 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(0.08f, 0.08f, 0.08f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(0.095f, 0.32f, 0.205f);

        //mata kiri (child 1 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(1).scaleObject(0.08f, 0.08f, 0.08f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(1).translateObject(-0.095f, 0.32f, 0.205f);

        //blush kanan (child 2 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.57f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(2).scaleObject(0.25f, 0.15f, 0.05f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(2).rotateObject((float) Math.toRadians(15f), 0.0f, 1.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(2).translateObject(0.15f, 0.27f, 0.19f);

        //blush kiri (child 3 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.57f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(3).scaleObject(0.25f, 0.15f, 0.05f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(3).rotateObject((float) Math.toRadians(-15f), 0.0f, 1.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(3).translateObject(-0.15f, 0.27f, 0.19f);

        //hidung (child 4 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Limas(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(4).scaleObject(0.25f, 0.2f, 0.1f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(4).rotateObject((float) Math.toRadians(180f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(4).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(4).translateObject(0.0f, 0.28f, 0.23f);

        //telinga kanan atas (child 5 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Cone(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(5).scaleObject(0.7f, 0.15f, 0.15f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(5).rotateObject((float) Math.toRadians(-45f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(5).translateObject(0.1f, 0.475f, 0.0f);

        //telinga kanan bawah (child 6 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(6).scaleObject(0.2f, 0.3f, 0.3f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(6).rotateObject((float) Math.toRadians(-225f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(6).translateObject(0.335f, 0.24f, 0.0f);

        //telinga kiri atas (child 7 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Cone(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(7).scaleObject(0.7f, 0.15f, 0.15f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(-45f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(7).translateObject(-0.1f, 0.475f, 0.0f);

        //telinga kiri bawah (child 8 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(8).scaleObject(0.2f, 0.3f, 0.3f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(8).rotateObject((float) Math.toRadians(-225f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(8).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(8).translateObject(-0.335f, 0.24f, 0.0f);

        //topi (child 9 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.37f, 0.22f, 0.086f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(9).scaleObject(0.18f, 0.7f, 0.7f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(9).rotateObject((float) Math.toRadians(-90f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(9).translateObject(0.0f, 0.507f, 0.0f);

        //muncung topi (child 10 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.37f, 0.22f, 0.086f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(10).scaleObject(0.2f, 0.2f, 0.2f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(10).rotateObject((float) Math.toRadians(-90f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(1).getChildObject().get(10).translateObject(0.0f, 0.53f, 0.0f);

        //mulut kiri (child 11 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.05f
        ));

        List<Vector3f> points = new ArrayList<>();
        points.add(new Vector3f(-0.055f, 0.265f, 0.0f));
        points.add(new Vector3f(-0.035f, 0.22f, 0.0f));
        points.add(new Vector3f(0.0f, 0.28f, 0.0f));
        drawCurve(points);
        for (int i = 0; i < curveVertices.size(); i++) {
            Vector3f pointXY = curveVertices.get(i);
            pompompurin.get(0).getChildObject().get(1).getChildObject().get(11).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                    Arrays.asList(pointXY.x, pointXY.y, 0.0f),
                    0.005f,
                    0.005f,
                    0.005f
            ));
            pompompurin.get(0).getChildObject().get(1).getChildObject().get(11).getChildObject().get(i).translateObject(0.0f, 0.0f, 0.225f);
        }

        //mulut kanan (child 12 of child 1)
        pompompurin.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.05f,
                0.05f,
                0.05f
        ));

        points.clear();
        points.add(new Vector3f(0.0f, 0.28f, 0.0f));
        points.add(new Vector3f(0.035f, 0.22f, 0.0f));
        points.add(new Vector3f(0.055f, 0.265f, 0.0f));
        drawCurve(points);
        for (int i = 0; i < curveVertices.size(); i++) {
            Vector3f pointXY = curveVertices.get(i);
            pompompurin.get(0).getChildObject().get(1).getChildObject().get(12).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                    Arrays.asList(pointXY.x, pointXY.y, 0.0f),
                    0.005f,
                    0.005f,
                    0.005f
            ));
            pompompurin.get(0).getChildObject().get(1).getChildObject().get(12).getChildObject().get(i).translateObject(0.0f, 0.0f, 0.225f);
        }

        //engsel lengan kanan (child 2)
        pompompurin.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(2).scaleObject(0.3f, 0.3f, 0.3f);
        pompompurin.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(40f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(2).translateObject(0.18f, 0.22f, 0.0f);

        //lengan kanan (child 0 of  child 2)
        pompompurin.get(0).getChildObject().get(2).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(2).getChildObject().get(0).scaleObject(0.6f, 1.8f, 0.6f);
        pompompurin.get(0).getChildObject().get(2).getChildObject().get(0).rotateObject((float) Math.toRadians(40f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(2).getChildObject().get(0).translateObject(0.2f, 0.15f, 0.0f);

        //engsel lengan kiri (child 3)
        pompompurin.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(3).scaleObject(0.3f, 0.3f, 0.3f);
        pompompurin.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(40f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(3).translateObject(-0.18f, 0.22f, 0.0f);

        //lengan kiri (child 0 of child 3)
        pompompurin.get(0).getChildObject().get(3).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(3).getChildObject().get(0).scaleObject(0.6f, 1.8f, 0.6f);
        pompompurin.get(0).getChildObject().get(3).getChildObject().get(0).rotateObject((float) Math.toRadians(40f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(3).getChildObject().get(0).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
        pompompurin.get(0).getChildObject().get(3).getChildObject().get(0).translateObject(-0.2f, 0.15f, 0.0f);

        //kaki kanan (child 4)
        pompompurin.get(0).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(4).scaleObject(0.65f, 0.5f, 0.65f);
        pompompurin.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(110f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(4).translateObject(0.19f, -0.32f, 0.0f);

        //kaki kiri (child 5)
        pompompurin.get(0).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.91f, 0.6f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        pompompurin.get(0).getChildObject().get(5).scaleObject(0.65f, 0.5f, 0.65f);
        pompompurin.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(70f), 0.0f, 0.0f, 1.0f);
        pompompurin.get(0).getChildObject().get(5).translateObject(-0.19f, -0.32f, 0.0f);

        pompompurin.get(0).translateObject(-0.9f, 0.0f, 0.f);


    }

    public void initCinna() {
        //titik tengah
        cinna.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).translateObject(0.0f, 0.0f, 0.0f);
        cinna.get(0).scaleObject(0.0f, 0.0f, 0.0f);

        //badan
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(0).scaleObject(1.55f, 1.3f, 1.2f);

        //kepala
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(1).scaleObject(1.8f, 1.1f, 1.8f);
        cinna.get(0).getChildObject().get(1).translateObject(0.0f, 0.25f, 0.0f);

        //lengan kanan
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(2).scaleObject(1.0f, 0.38f, 0.38f);
        cinna.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(145f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(2).translateObject(0.21f, 0.03f, 0.0f);

        //lengan kiri
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(3).scaleObject(1.0f, 0.38f, 0.38f);
//        cinna.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(45f),0.0f,0.0f,1.0f);
        cinna.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(145f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(180f), 0.0f, 1.0f, 0.0f);
        cinna.get(0).getChildObject().get(3).translateObject(-0.21f, 0.03f, 0.0f);

        //kaki kanan
        cinna.get(0).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(4).scaleObject(0.43f, 0.3f, 0.3f);
        cinna.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(4).translateObject(0.07f, -0.24f, 0.0f);

        //kaki kiri
        cinna.get(0).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(5).scaleObject(0.43f, 0.3f, 0.3f);
        cinna.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(5).translateObject(-0.07f, -0.24f, 0.0f);

        //telinga kanan
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(6).scaleObject(0.6f, 1.75f, 0.58f);
        cinna.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(125f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(6).translateObject(0.24f, 0.33f, 0.0f);

        //telinga kiri
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(7).scaleObject(0.6f, 1.75f, 0.58f);
        cinna.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-125f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(7).translateObject(-0.24f, 0.33f, 0.0f);

        //mata kanan
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.254f, 0.933f, 0.949f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(8).scaleObject(0.15f, 0.25f, 0.1f);
        cinna.get(0).getChildObject().get(8).translateObject(0.08f, 0.25f, 0.21f);

        //mata kiri
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.254f, 0.933f, 0.949f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(9).scaleObject(0.15f, 0.25f, 0.1f);
        cinna.get(0).getChildObject().get(9).translateObject(-0.08f, 0.25f, 0.21f);

        //blush kanan
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.764f, 0.882f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(10).scaleObject(0.25f, 0.15f, 0.2f);
        cinna.get(0).getChildObject().get(10).translateObject(0.11f, 0.18f, 0.17f);

        //blush kiri
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.94f, 0.764f, 0.882f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(11).scaleObject(0.25f, 0.15f, 0.2f);
        cinna.get(0).getChildObject().get(11).translateObject(-0.11f, 0.18f, 0.17f);

        //syal
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.192f, 0.423f, 0.878f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(12).scaleObject(1.47f, 0.36f, 1.47f);
        cinna.get(0).getChildObject().get(12).translateObject(0.0f, 0.14f, 0.0f);

        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.192f, 0.423f, 0.878f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(13).scaleObject(0.2f, 0.2f, 0.2f);
        cinna.get(0).getChildObject().get(13).translateObject(0.0f, 0.132f, 0.173f);

        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.192f, 0.423f, 0.878f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(14).scaleObject(0.15f, 0.4f, 0.1f);
        cinna.get(0).getChildObject().get(14).translateObject(-0.095f, 0.046f, 0.173f);
        cinna.get(0).getChildObject().get(14).rotateObject((float) Math.toRadians(-45f), 0.0f, 0.0f, 1.0f);

        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.192f, 0.423f, 0.878f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(15).scaleObject(0.15f, 0.4f, 0.1f);
        cinna.get(0).getChildObject().get(15).translateObject(0.095f, 0.046f, 0.173f);
        cinna.get(0).getChildObject().get(15).rotateObject((float) Math.toRadians(45f), 0.0f, 0.0f, 1.0f);

        //topi
        cinna.get(0).getChildObject().add(new EllipticParaboloid(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.258f, 0.564f, 0.967f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(16).scaleObject(0.4f, 0.8f, 0.53f);
        cinna.get(0).getChildObject().get(16).rotateObject((float) Math.toRadians(-110f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(16).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(16).translateObject(0.06f, 0.43f, 0.1f);

        cinna.get(0).getChildObject().add(new Cylinder(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.258f, 0.564f, 0.967f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(17).scaleObject(0.35f, 0.2f, 0.2f);
        cinna.get(0).getChildObject().get(17).rotateObject((float) Math.toRadians(-110f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(17).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(17).translateObject(0.06f, 0.415f, 0.1f);
        //mulut buka
        cinna.get(0).getChildObject().add(new Cone(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.949f, 0.807f, 0.96f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.125f,
                0.125f,
                0.125f
        ));
        cinna.get(0).getChildObject().get(18).scaleObject(0.13f, 0.12f, 0.13f);
        cinna.get(0).getChildObject().get(18).rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(18).rotateObject((float) Math.toRadians(22f), 1.0f, 0.0f, 0.0f);
        cinna.get(0).getChildObject().get(18).translateObject(0.0f, 0.165f, 0.168f);

        //Ekor
        cinna.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 2.0f),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.001f,
                0.001f,
                0.001f
        ));


        List<Vector3f> points = new ArrayList<>();
        points.add(new Vector3f(0.0f, 0.0f, 0.0f));
        points.add(new Vector3f(0.16f, 0.0f, 0.0f));
        points.add(new Vector3f(0.2f, 0.1f, 0.0f));
        points.add(new Vector3f(0.26f, -0.2f, 0.0f));
        points.add(new Vector3f(0.3f, -0.24f, 0.0f));
        points.add(new Vector3f(0.0f, -0.15f, 0.0f));
        points.add(new Vector3f(0.1f, -0.12f, 0.0f));
        points.add(new Vector3f(0.1f, -0.07f, 0.0f));
        drawCurve(points);
        for (int i = 0; i < curveVertices.size(); i++) {
            Vector3f pointXY = curveVertices.get(i);
            cinna.get(0).getChildObject().get(19).getChildObject().add(new Sphere(
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(2.0f, 2.0f, 2.0f, 2.0f),
                    Arrays.asList(pointXY.x, pointXY.y, 0.0f),
                    0.04f,
                    0.04f,
                    0.04f
            ));
        }
        cinna.get(0).getChildObject().get(19).rotateObject((float) Math.toRadians(180f), 0.0f, 0.0f, 1.0f);
        cinna.get(0).getChildObject().get(19).rotateObject((float) Math.toRadians(-25f), 1.0f, 0.0f, 0.0f);
        cinna.get(0).getChildObject().get(19).translateObject(0.0f, -0.07f, -0.120f);

        cinna.get(0).translateObject(0.0f, 1.45f, 0.0f);
    }

    public void drawCurve(List<Vector3f> points) {
        if (points.size() < 3) {
            return;
        }
        curveVertices.clear();
        curveVertices.add(points.get(0));

        double interval = 0.02;
        for (double i = 0; i <= 1; i += interval) {
            curveVertices.add(new Vector3f(calculateBezierPoint((float) i, points)));
        }
        curveVertices.add(points.get(points.size() - 1));
    }

    public static Vector3f calculateBezierPoint(float t, List<Vector3f> points) {
        int n = points.size() - 1;
        float x = 0, y = 0;

        for (int i = 0; i <= n; i++) {
            double coefficient = calculateCoefficient(n, i, t);
            x += coefficient * points.get(i).x;
            y += coefficient * points.get(i).y;
        }
        return new Vector3f(x, y, 0.0f);
    }

    private static double calculateCoefficient(int n, int i, double t) {
        return binomialCoefficient(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
    }

    private static int binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
        }
    }

    public void input() {
        if (window.isKeyPressed(GLFW_KEY_1)) {
            kuromi.clear();
            initKuromi();
        }
        if (window.isKeyPressed(GLFW_KEY_2)) {
            badtzmaru.clear();
            initBadtz();
        }
        if (window.isKeyPressed(GLFW_KEY_3)) {
            pompompurin.clear();
            initPomPom();
        }
        if (window.isKeyPressed(GLFW_KEY_4)) {
            cinna.clear();
            initCinna();
        }

        if (window.isKeyPressed(GLFW_KEY_D)) {
            cam.get(0).rotateObject((float) Math.toRadians(2.0f), 0.0f, 0.8f, 0.0f);
            List<Float> temp = new ArrayList<>(cam.get(0).getCenterPoint());
            camera.setPosition(temp.get(0), temp.get(1), temp.get(2));
            float depan = temp.get(0);
            float samping = temp.get(2);
            float rotDegree = (float) Math.atan(depan / samping);
            rotDegree = Math.abs(rotDegree);
            float rotDegreeDeg = (float) Math.toDegrees(rotDegree);
            if (temp.get(0) > 0 && temp.get(2) > 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(-(rotDegreeDeg)));
            } else if (temp.get(0) > 0 && temp.get(2) < 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(-(180.0f - rotDegreeDeg)));
            } else if (temp.get(0) < 0 && temp.get(2) < 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(-(180.0f + rotDegreeDeg)));
            } else if (temp.get(0) < 0 && temp.get(2) > 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(-(360.0f - rotDegreeDeg)));
            }
        }

        if (window.isKeyPressed(GLFW_KEY_A)) {
            cam.get(0).rotateObject((float) Math.toRadians(-2.0f), 0.0f, 0.8f, 0.0f);
            List<Float> temp = new ArrayList<>(cam.get(0).getCenterPoint());
            camera.setPosition(temp.get(0), temp.get(1), temp.get(2));
            float depan = temp.get(0);
            float samping = temp.get(2);
            float rotDegree = (float) Math.atan(depan / samping);
            rotDegree = Math.abs(rotDegree);
            float rotDegreeDeg = (float) Math.toDegrees(rotDegree);
            if (temp.get(0) < 0 && temp.get(2) > 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians((rotDegreeDeg)));
            } else if (temp.get(0) < 0 && temp.get(2) < 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians((180.0f - rotDegreeDeg)));
            } else if (temp.get(0) > 0 && temp.get(2) < 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians((180.0f + rotDegreeDeg)));
            } else if (temp.get(0) > 0 && temp.get(2) > 0) {
                camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians((360.0f - rotDegreeDeg)));
            }
        }

        //lompat badz
        if (window.isKeyPressed(GLFW_KEY_P) && timer1 <= 0 || timer1 > 0) {

            if (timer1 <= 0) {
                timer1 = 16;
            }
            if (timer1 > 8) {
                badtzmaru.get(0).translateObject(0.0f, 0.02f, 0.0f);
            } else if (timer1 <= 8) {
                badtzmaru.get(0).translateObject(0.0f, -0.02f, 0.0f);
            }
            timer1 -= 1;
        }

        //sayap badz
        if (window.isKeyPressed(GLFW_KEY_O) && timer2 <= 0 || timer2 > 0) {

            if (timer2 <= 0) {
                timer2 = 10;
            }
            if (timer2 > 5) {
                badtzmaru.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, 0.5f);
                badtzmaru.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, -0.5f);
            } else if (timer2 <= 5) {
                badtzmaru.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, -0.5f);
                badtzmaru.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 0.0f, 0.0f, 0.5f);
            }
            timer2 -= 1;
        }

        //jumping jack badz
        if (window.isKeyPressed(GLFW_KEY_I) && timer4 <= 0 || timer4 > 0) {

            if (timer4 <= 0) {
                timer4 = 16;
            }
            if (timer4 > 8) {
                badtzmaru.get(0).translateObject(0.0f, 0.02f, 0.0f);
                badtzmaru.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(2.0f), 0.0f, 0.0f, 0.5f);
                badtzmaru.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(2.0f), 0.0f, 0.0f, -0.5f);
            } else if (timer4 <= 8) {
                badtzmaru.get(0).translateObject(0.0f, -0.02f, 0.0f);
                badtzmaru.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(2.0f), 0.0f, 0.0f, -0.5f);
                badtzmaru.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(2.0f), 0.0f, 0.0f, 0.5f);
                badtzmaru.get(0).getChildObject().get(3).translateObject(0.0008f, 0.0f, 0.0f);
                badtzmaru.get(0).getChildObject().get(4).translateObject(-0.0008f, 0.0f, 0.0f);
            }
            timer4 -= 1;
        }

        //jalan badz
        if (window.isKeyPressed(GLFW_KEY_U) && timer3 <= 0 || timer3 > 0) {

            if (timer3 <= 0) {
                timer3 = 20;
            }
            if (timer3 > 10) {
                badtzmaru.get(0).getChildObject().get(5).translateObject(0.0f, 0.0f, 0.01f);
                badtzmaru.get(0).getChildObject().get(6).translateObject(0.0f, 0.0f, -0.01f);
                badtzmaru.get(0).translateObject(0.0f, 0.0f, 0.01f);
            } else if (timer3 <= 10) {
                badtzmaru.get(0).getChildObject().get(5).translateObject(0.0f, 0.0f, -0.01f);
                badtzmaru.get(0).getChildObject().get(6).translateObject(0.0f, 0.0f, 0.01f);
                badtzmaru.get(0).translateObject(0.0f, 0.0f, 0.01f);
            }
            timer3 -= 1;
        }


        //kuromi jalan maju
        if (window.isKeyPressed(GLFW_KEY_L)) {
            countDegreeWalk++;
            List<Float> temp = new ArrayList<>(kuromi.get(0).getChildObject().get(1).getCenterPoint());
            List<Float> temp2 = new ArrayList<>(kuromi.get(0).getChildObject().get(2).getCenterPoint());
            List<Float> temp3 = new ArrayList<>(kuromi.get(0).getChildObject().get(4).getCenterPoint());
            List<Float> temp4 = new ArrayList<>(kuromi.get(0).getChildObject().get(5).getCenterPoint());
            List<Float> tengah = new ArrayList<>(kuromi.get(0).getCenterPoint());

            kuromi.get(0).translateObject(0.0f, 0.0f, 0.003f);
            kuromi.get(0).getChildObject().get(1).translateObject(temp.get(0) * -1, temp.get(1) * -1, temp.get(2) * -1);
            kuromi.get(0).getChildObject().get(2).translateObject(temp2.get(0) * -1, temp2.get(1) * -1, temp2.get(2) * -1);
            kuromi.get(0).getChildObject().get(4).translateObject(temp3.get(0) * -1, temp3.get(1) * -1, temp3.get(2) * -1);
            kuromi.get(0).getChildObject().get(5).translateObject(temp4.get(0) * -1, temp4.get(1) * -1, temp4.get(2) * -1);
            if (countDegreeWalk <= 45) {
                kuromi.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
            } else {
                kuromi.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                if (countDegreeWalk >= 90) {
                    countDegreeWalk = 0;
                }
            }
            kuromi.get(0).getChildObject().get(1).translateObject(temp.get(0) * 1, temp.get(1) * 1, temp.get(2) * 1);
            kuromi.get(0).getChildObject().get(2).translateObject(temp2.get(0) * 1, temp2.get(1) * 1, temp2.get(2) * 1);
            kuromi.get(0).getChildObject().get(4).translateObject(temp3.get(0) * 1, temp3.get(1) * 1, temp3.get(2) * 1);
            kuromi.get(0).getChildObject().get(5).translateObject(temp4.get(0) * 1, temp4.get(1) * 1, temp4.get(2) * 1);
        }

        //kuromi jalan mundur
        if (window.isKeyPressed(GLFW_KEY_K)) {
            countDegreeWalk++;
            List<Float> temp = new ArrayList<>(kuromi.get(0).getChildObject().get(1).getCenterPoint());
            List<Float> temp2 = new ArrayList<>(kuromi.get(0).getChildObject().get(2).getCenterPoint());
            List<Float> temp3 = new ArrayList<>(kuromi.get(0).getChildObject().get(4).getCenterPoint());
            List<Float> temp4 = new ArrayList<>(kuromi.get(0).getChildObject().get(5).getCenterPoint());
            List<Float> tengah = new ArrayList<>(kuromi.get(0).getCenterPoint());
            //rotasi terhadap sumbu masing-masing planet
            kuromi.get(0).translateObject(0.0f, 0.0f, -0.003f);
            kuromi.get(0).getChildObject().get(1).translateObject(temp.get(0) * -1, temp.get(1) * -1, temp.get(2) * -1);
            kuromi.get(0).getChildObject().get(2).translateObject(temp2.get(0) * -1, temp2.get(1) * -1, temp2.get(2) * -1);
            kuromi.get(0).getChildObject().get(4).translateObject(temp3.get(0) * -1, temp3.get(1) * -1, temp3.get(2) * -1);
            kuromi.get(0).getChildObject().get(5).translateObject(temp4.get(0) * -1, temp4.get(1) * -1, temp4.get(2) * -1);
            if (countDegreeWalk <= 45) {
                kuromi.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
            } else {
                kuromi.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                kuromi.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                if (countDegreeWalk >= 90) {
                    countDegreeWalk = 0;
                }
            }
            kuromi.get(0).getChildObject().get(1).translateObject(temp.get(0) * 1, temp.get(1) * 1, temp.get(2) * 1);
            kuromi.get(0).getChildObject().get(2).translateObject(temp2.get(0) * 1, temp2.get(1) * 1, temp2.get(2) * 1);
            kuromi.get(0).getChildObject().get(4).translateObject(temp3.get(0) * 1, temp3.get(1) * 1, temp3.get(2) * 1);
            kuromi.get(0).getChildObject().get(5).translateObject(temp4.get(0) * 1, temp4.get(1) * 1, temp4.get(2) * 1);
        }

        //kuromi kepala ngangguk
        if (window.isKeyPressed(GLFW_KEY_H)) {
            countDegreeHead++;
            List<Float> temp = new ArrayList<>(kuromi.get(0).getChildObject().get(3).getCenterPoint());
            //rotasi terhadap sumbu masing-masing planet
            kuromi.get(0).getChildObject().get(3).translateObject(temp.get(0) * -1, temp.get(1) * -1, temp.get(2) * -1);
            if (countDegreeHead <= 15) {
                kuromi.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-2.0f), 1.0f, 0.0f, 0.0f);
            } else {
                kuromi.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(2.0f), 1.0f, 0.0f, 0.0f);
                if (countDegreeHead >= 30) {
                    countDegreeHead = 0;
                }
            }
            kuromi.get(0).getChildObject().get(3).translateObject(temp.get(0) * 1, temp.get(1) * 1, temp.get(2) * 1);

        }

        //kuromi ekor
        if (window.isKeyPressed(GLFW_KEY_J)) {
            countDegreeTail++;
            List<Float> temp = new ArrayList<>(kuromi.get(0).getChildObject().get(0).getChildObject().get(12).getCenterPoint());
            //rotasi terhadap sumbu masing-masing planet
            kuromi.get(0).getChildObject().get(0).getChildObject().get(12).translateObject(temp.get(0) * -1, temp.get(1) * -1, temp.get(2) * -1);
            if (countDegreeTail <= 11) {
                kuromi.get(0).getChildObject().get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(-0.5f), 1.0f, 0.0f, 0.0f);
            } else {
                kuromi.get(0).getChildObject().get(0).getChildObject().get(12).rotateObject((float) Math.toRadians(0.5f), 1.0f, 0.0f, 0.0f);
                if (countDegreeTail >= 19) {
                    countDegreeTail = 0;
                }
            }
            kuromi.get(0).getChildObject().get(0).getChildObject().get(12).translateObject(temp.get(0) * 1, temp.get(1) * 1, temp.get(2) * 1);

        }

        //kuromi mata kedip
        if (window.isKeyPressed(GLFW_KEY_G)) {
            List<Float> temp = new ArrayList<>(kuromi.get(0).getChildObject().get(3).getChildObject().get(10).getCenterPoint());
            List<Float> temp2 = new ArrayList<>(kuromi.get(0).getChildObject().get(3).getChildObject().get(11).getCenterPoint());
            //rotasi terhadap sumbu masing-masing planet
            kuromi.get(0).getChildObject().get(3).getChildObject().get(10).translateObject(temp.get(0) * -1, temp.get(1) * -1, temp.get(2) * -1);
            kuromi.get(0).getChildObject().get(3).getChildObject().get(11).translateObject(temp2.get(0) * -1, temp2.get(1) * -1, temp2.get(2) * -1);
            if (countDegreeEye <= 0) {
                countDegreeEye = 20;
            }
            if (countDegreeEye > 10) {
                kuromi.get(0).getChildObject().get(3).getChildObject().get(10).scaleObject(1.0f, 0.8f, 1.0f);
                kuromi.get(0).getChildObject().get(3).getChildObject().get(11).scaleObject(1.0f, 0.8f, 1.0f);
            } else if (countDegreeEye <= 10) {
                kuromi.get(0).getChildObject().get(3).getChildObject().get(10).scaleObject(1.0f, 1.25f, 1.0f);
                kuromi.get(0).getChildObject().get(3).getChildObject().get(11).scaleObject(1.0f, 1.25f, 1.0f);
            }
            kuromi.get(0).getChildObject().get(3).getChildObject().get(10).translateObject(temp.get(0) * 1, temp.get(1) * 1, temp.get(2) * 1);
            kuromi.get(0).getChildObject().get(3).getChildObject().get(11).translateObject(temp2.get(0) * 1, temp2.get(1) * 1, temp2.get(2) * 1);

            countDegreeEye--;
        }


        //pompom jalan maju
        if (window.isKeyPressed(GLFW_KEY_M)) {
            countDegreeWalkFront2++;
            List<Float> armRight = new ArrayList<>(pompompurin.get(0).getChildObject().get(2).getCenterPoint());
            List<Float> armLeft = new ArrayList<>(pompompurin.get(0).getChildObject().get(3).getCenterPoint());
            List<Float> legRight = new ArrayList<>(pompompurin.get(0).getChildObject().get(4).getCenterPoint());
            List<Float> legLeft = new ArrayList<>(pompompurin.get(0).getChildObject().get(5).getCenterPoint());
            List<Float> all = new ArrayList<>(pompompurin.get(0).getCenterPoint());

            pompompurin.get(0).translateObject(0.0f, 0.0f, 0.003f);
            pompompurin.get(0).getChildObject().get(2).translateObject(armRight.get(0) * -1, armRight.get(1) * -1, armRight.get(2) * -1);
            pompompurin.get(0).getChildObject().get(3).translateObject(armLeft.get(0) * -1, armLeft.get(1) * -1, armLeft.get(2) * -1);
            pompompurin.get(0).getChildObject().get(2).translateObject(legRight.get(0) * -1, legRight.get(1) * -1, legRight.get(2) * -1);
            pompompurin.get(0).getChildObject().get(3).translateObject(legLeft.get(0) * -1, legLeft.get(1) * -1, legLeft.get(2) * -1);
            pompompurin.get(0).translateObject(all.get(0) * -1, all.get(1) * -1, all.get(2) * -1);
            if (countDegreeWalkFront2 <= 5) {
                pompompurin.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).rotateObject((float) Math.toRadians(0.2f), 0.0f, 0.0f, 1.0f);
            } else {
                pompompurin.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).rotateObject((float) Math.toRadians(-0.2f), 0.0f, 0.0f, 1.0f);
                if (countDegreeWalkFront2 >= 10) {
                    countDegreeWalkFront2 = 0;
                }
            }
            pompompurin.get(0).getChildObject().get(2).translateObject(armRight.get(0), armRight.get(1), armRight.get(2));
            pompompurin.get(0).getChildObject().get(3).translateObject(armLeft.get(0), armLeft.get(1), armLeft.get(2));
            pompompurin.get(0).getChildObject().get(2).translateObject(legRight.get(0), legRight.get(1), legRight.get(2));
            pompompurin.get(0).getChildObject().get(3).translateObject(legLeft.get(0), legLeft.get(1), legLeft.get(2));
            pompompurin.get(0).translateObject(all.get(0), all.get(1), all.get(2));
        }

        //pompom jalan mundur
        if (window.isKeyPressed(GLFW_KEY_N)) {
            countDegreeWalkBack2++;
            List<Float> armRight = new ArrayList<>(pompompurin.get(0).getChildObject().get(2).getCenterPoint());
            List<Float> armLeft = new ArrayList<>(pompompurin.get(0).getChildObject().get(3).getCenterPoint());
            List<Float> legRight = new ArrayList<>(pompompurin.get(0).getChildObject().get(4).getCenterPoint());
            List<Float> legLeft = new ArrayList<>(pompompurin.get(0).getChildObject().get(5).getCenterPoint());
            List<Float> all = new ArrayList<>(pompompurin.get(0).getCenterPoint());

            pompompurin.get(0).translateObject(0.0f, 0.0f, -0.003f);
            pompompurin.get(0).getChildObject().get(2).translateObject(armRight.get(0) * -1, armRight.get(1) * -1, armRight.get(2) * -1);
            pompompurin.get(0).getChildObject().get(3).translateObject(armLeft.get(0) * -1, armLeft.get(1) * -1, armLeft.get(2) * -1);
            pompompurin.get(0).getChildObject().get(2).translateObject(legRight.get(0) * -1, legRight.get(1) * -1, legRight.get(2) * -1);
            pompompurin.get(0).getChildObject().get(3).translateObject(legLeft.get(0) * -1, legLeft.get(1) * -1, legLeft.get(2) * -1);
            pompompurin.get(0).translateObject(all.get(0) * -1, all.get(1) * -1, all.get(2) * -1);
            if (countDegreeWalkBack2 <= 5) {
                pompompurin.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(-2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).rotateObject((float) Math.toRadians(-0.2f), 0.0f, 0.0f, 1.0f);
            } else {
                pompompurin.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.5f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(2.0f), 1.0f, 0.0f, 0.0f);
                pompompurin.get(0).rotateObject((float) Math.toRadians(0.2f), 0.0f, 0.0f, 1.0f);
                if (countDegreeWalkBack2 >= 10) {
                    countDegreeWalkBack2 = 0;
                }
            }
            pompompurin.get(0).getChildObject().get(2).translateObject(armRight.get(0), armRight.get(1), armRight.get(2));
            pompompurin.get(0).getChildObject().get(3).translateObject(armLeft.get(0), armLeft.get(1), armLeft.get(2));
            pompompurin.get(0).getChildObject().get(2).translateObject(legRight.get(0), legRight.get(1), legRight.get(2));
            pompompurin.get(0).getChildObject().get(3).translateObject(legLeft.get(0), legLeft.get(1), legLeft.get(2));
            pompompurin.get(0).translateObject(all.get(0), all.get(1), all.get(2));
        }

        //pompom belly drum or dance :3
        if (window.isKeyPressed(GLFW_KEY_B)) {
            countDegreeBelly2++;
            List<Float> armRight = new ArrayList<>(pompompurin.get(0).getChildObject().get(2).getCenterPoint());
            List<Float> armLeft = new ArrayList<>(pompompurin.get(0).getChildObject().get(3).getCenterPoint());
            List<Float> belly = new ArrayList<>(pompompurin.get(0).getChildObject().get(0).getCenterPoint());

            pompompurin.get(0).getChildObject().get(2).translateObject(armRight.get(0) * -1, armRight.get(1) * -1, armRight.get(2) * -1);
            pompompurin.get(0).getChildObject().get(3).translateObject(armLeft.get(0) * -1, armLeft.get(1) * -1, armLeft.get(2) * -1);
            pompompurin.get(0).translateObject(belly.get(0) * -1, belly.get(1) * -1, belly.get(2) * -1);
            if (countDegreeBelly2 <= 5) {
                pompompurin.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-0.3f), 1.0f, 1.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0.3f), 1.0f, 1.0f, 0.0f);
                pompompurin.get(0).rotateObject((float) Math.toRadians(0.75f), 0.0f, 1.0f, 0.0f);
//                pompompurin.get(0).getChildObject().get(0).translateObject(0.001f,0.0f,0.0f);

            } else {
                pompompurin.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0.3f), 1.0f, 1.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-0.3f), 1.0f, 1.0f, 0.0f);
                pompompurin.get(0).rotateObject((float) Math.toRadians(-0.75f), 0.0f, 1.0f, 0.0f);
//                pompompurin.get(0).getChildObject().get(0).translateObject(-0.001f,0.0f,0.0f);
                if (countDegreeBelly2 >= 10) {
                    countDegreeBelly2 = 0;
                }
            }
            pompompurin.get(0).getChildObject().get(2).translateObject(armRight.get(0), armRight.get(1), armRight.get(2));
            pompompurin.get(0).getChildObject().get(3).translateObject(armLeft.get(0), armLeft.get(1), armLeft.get(2));
            pompompurin.get(0).translateObject(belly.get(0), belly.get(1), belly.get(2));
        }

        //pompom geleng2
        if (window.isKeyPressed(GLFW_KEY_V)) {
            countDegreeGa2++;
            List<Float> head = new ArrayList<>(pompompurin.get(0).getChildObject().get(1).getCenterPoint());

            pompompurin.get(0).getChildObject().get(1).translateObject(head.get(0) * -1, head.get(1) * -1, head.get(2) * -1);
            if (countDegreeGa2 <= 15) {
                pompompurin.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);

            } else {
                pompompurin.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                if (countDegreeGa2 >= 30) {
                    countDegreeGa2 = 0;
                }
            }
            pompompurin.get(0).getChildObject().get(1).translateObject(head.get(0), head.get(1), head.get(2));
        }

        //pompom geleng2 yang bener2 serius
        if (window.isKeyPressed(GLFW_KEY_C)) {
            countDegreeGa22++;
            List<Float> head = new ArrayList<>(pompompurin.get(0).getChildObject().get(1).getCenterPoint());
            List<Float> body = new ArrayList<>(pompompurin.get(0).getChildObject().get(0).getCenterPoint());

            pompompurin.get(0).getChildObject().get(1).translateObject(head.get(0) * -1, head.get(1) * -1, head.get(2) * -1);
            pompompurin.get(0).getChildObject().get(0).translateObject(body.get(0) * -1, body.get(1) * -1, body.get(2) * -1);
            if (countDegreeGa22 <= 15) {
                pompompurin.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(1.0f), 0.0f, 1.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(0.5f), 0.0f, 1.0f, 0.0f);

            } else {
                pompompurin.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(-1.0f), 0.0f, 1.0f, 0.0f);
                pompompurin.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(-0.5f), 0.0f, 1.0f, 0.0f);
                if (countDegreeGa22 >= 30) {
                    countDegreeGa22 = 0;
                }
            }
            pompompurin.get(0).getChildObject().get(1).translateObject(head.get(0), head.get(1), head.get(2));
            pompompurin.get(0).getChildObject().get(0).translateObject(body.get(0), body.get(1), body.get(2));
        }

        //pompom mata kedip
        if (window.isKeyPressed(GLFW_KEY_X)) {
            countDegreeEye2++;
            List<Float> eyeRight = new ArrayList<>(pompompurin.get(0).getChildObject().get(1).getChildObject().get(0).getCenterPoint());
            List<Float> eyeLeft = new ArrayList<>(pompompurin.get(0).getChildObject().get(1).getChildObject().get(1).getCenterPoint());

            pompompurin.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(eyeRight.get(0) * -1, eyeRight.get(1) * -1, eyeRight.get(2) * -1);
            pompompurin.get(0).getChildObject().get(1).getChildObject().get(1).translateObject(eyeLeft.get(0) * -1, eyeLeft.get(1) * -1, eyeLeft.get(2) * -1);
            if (countDegreeEye2 <= 10) {
                pompompurin.get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(1.0f, 0.8f, 1.0f);
                pompompurin.get(0).getChildObject().get(1).getChildObject().get(1).scaleObject(1.0f, 0.8f, 1.0f);
            } else {
                pompompurin.get(0).getChildObject().get(1).getChildObject().get(0).scaleObject(1.0f, 1.25f, 1.0f);
                pompompurin.get(0).getChildObject().get(1).getChildObject().get(1).scaleObject(1.0f, 1.25f, 1.0f);
                if (countDegreeEye2 >= 20) {
                    countDegreeEye2 = 0;
                }
            }
            pompompurin.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(eyeRight.get(0), eyeRight.get(1), eyeRight.get(2));
            pompompurin.get(0).getChildObject().get(1).getChildObject().get(1).translateObject(eyeLeft.get(0), eyeLeft.get(1), eyeLeft.get(2));
        }


        //animasi lompat
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            jumphands1++;

            List<Float> right = new ArrayList<>(cinna.get(0).getChildObject().get(2).getCenterPoint());
            List<Float> left = new ArrayList<>(cinna.get(0).getChildObject().get(3).getCenterPoint());

            cinna.get(0).getChildObject().get(2).translateObject(right.get(0) * -1, right.get(1) * -1, right.get(2) * -1);
            cinna.get(0).getChildObject().get(3).translateObject(left.get(0) * -1, left.get(1) * -1, left.get(2) * -1);

            //movements (tangan loncat)
            if (jumphands1 <= 10) {
                cinna.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(6.0f), 0.0f, 0.0f, 1.0f);
                cinna.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(-6.0f), 0.0f, 0.0f, 1.0f);
            } else {
                cinna.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(-6.0f), 0.0f, 0.0f, 1.0f);
                cinna.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(6.0f), 0.0f, 0.0f, 1.0f);
                if (jumphands1 >= 20) {
                    jumphands1 = 0;
                }
            }

            cinna.get(0).getChildObject().get(2).translateObject(right.get(0), right.get(1) + 0.0001f, right.get(2));
            cinna.get(0).getChildObject().get(3).translateObject(left.get(0), left.get(1) + 0.0001f, left.get(2));

            //jumping height
            jump++;
            if (jump <= 10) {
                cinna.get(0).translateObject(0.0f, 0.021f, 0.0f);
            } else {
                cinna.get(0).translateObject(0.0f, -0.021f, 0.0f);
                if (jump >= 20) {
                    jump = 0;
                }
            }
        }

        //fly forward / float
        if (window.isKeyPressed(GLFW_KEY_F)) {
            flap++;

            List<Float> right = new ArrayList<>(cinna.get(0).getChildObject().get(6).getCenterPoint());
            List<Float> left = new ArrayList<>(cinna.get(0).getChildObject().get(7).getCenterPoint());

            cinna.get(0).getChildObject().get(6).translateObject(right.get(0) * -1, right.get(1) * -1, right.get(2) * -1 + 0.1f);
            cinna.get(0).getChildObject().get(7).translateObject(left.get(0) * -1, left.get(1) * -1, left.get(2) * -1 - 0.1f);

            //movements (telinga flap)
            if (flap <= 10) {
                cinna.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(-4.0f), 0.0f, 0.0f, 1.0f);
                cinna.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(4.0f), 0.0f, 0.0f, 1.0f);
            } else {
                cinna.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(4.0f), 0.0f, 0.0f, 1.0f);
                cinna.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(-4.0f), 0.0f, 0.0f, 1.0f);
                if (flap >= 20) {
                    flap = 0;
                }
            }

            cinna.get(0).getChildObject().get(6).translateObject(right.get(0), right.get(1), right.get(2) - 0.1f);
            cinna.get(0).getChildObject().get(7).translateObject(left.get(0), left.get(1), left.get(2) + 0.1f);

            //float
            fly++;
            if (fly <= 15) {
                cinna.get(0).translateObject(0.0f, 0.021f, 0.0f);
            } else {
                cinna.get(0).translateObject(0.0f, -0.021f, 0.0f);
                if (fly >= 30) {
                    fly = 0;
                }
            }

            //forward
            if (window.isKeyPressed(GLFW_KEY_Q)) {
                lean++;
                cinna.get(0).translateObject(0.0f, 0.0f, 0.01f);
                cinna.get(0).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                for (Object child : cinna.get(0).getChildObject()) {
                    child.translateObject(0.0f, 0.0f, 0.01f);
                    child.rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                }
                if (lean >= 10) {
                    cinna.get(0).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                    for (Object child : cinna.get(0).getChildObject()) {
                        child.rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                    }
                    lean = 10;
                }
            }
        }

        //tegak
        if (lean >= 0) {
            if (window.isKeyPressed(GLFW_KEY_E)) {
                lean--;
                cinna.get(0).rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                for (Object child : cinna.get(0).getChildObject()) {
                    child.rotateObject((float) Math.toRadians(-1.0f), 1.0f, 0.0f, 0.0f);
                }
                if (lean <= 0) {
                    cinna.get(0).rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                    for (Object child : cinna.get(0).getChildObject()) {
                        child.rotateObject((float) Math.toRadians(1.0f), 1.0f, 0.0f, 0.0f);
                    }
                    lean = 0;
                }
            }
        }

        //mata kedip
        if (window.isKeyPressed(GLFW_KEY_S)) {
            blink++;
            List<Float> temp = new ArrayList<>(cinna.get(0).getChildObject().get(8).getCenterPoint());
            List<Float> temp2 = new ArrayList<>(cinna.get(0).getChildObject().get(9).getCenterPoint());

            cinna.get(0).getChildObject().get(8).translateObject(temp.get(0) * -1, temp.get(1) * -1, temp.get(2) * -1);
            cinna.get(0).getChildObject().get(9).translateObject(temp2.get(0) * -1, temp2.get(1) * -1, temp2.get(2) * -1);
            if (blink <= 8) {
                cinna.get(0).getChildObject().get(8).scaleObject(1.0f, 0.8f, 1.0f);
                cinna.get(0).getChildObject().get(9).scaleObject(1.0f, 0.8f, 1.0f);
            } else {
                cinna.get(0).getChildObject().get(8).scaleObject(1.0f, 1.25f, 1.0f);
                cinna.get(0).getChildObject().get(9).scaleObject(1.0f, 1.25f, 1.0f);
                if (blink >= 16) {
                    blink = 0;
                }
            }
            cinna.get(0).getChildObject().get(8).translateObject(temp.get(0) * 1, temp.get(1) * 1, temp.get(2) * 1);
            cinna.get(0).getChildObject().get(9).translateObject(temp2.get(0) * 1, temp2.get(1) * 1, temp2.get(2) * 1);
        }

    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.6f,
                    0.65f, 1.0f,
                    0.0f);
            GL.createCapabilities();
            input();

            for(Object object: environment){
                object.draw(camera,projection);
            }
            for(Object object: kuromi){
                object.draw(camera,projection);
            }
            for(Object object: badtzmaru){
                object.draw(camera,projection);
            }
            for(Object object: pompompurin){
                object.draw(camera,projection);
            }
            for(Object object: cinna){
                object.draw(camera,projection);
            }

            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void run() {

        init();
        initEnvironment();
        initKuromi();
        initBadtz();
        initPomPom();
        initCinna();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}