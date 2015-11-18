package woody.demo.com.example;

/**
 * @author houwenchang
 *         <p/>
 *         2014/12/12.
 */
public class Model {
    public final static int TYPE_A = 0;
    public final static int TYPE_B = 1;
    public final static int TYPE_C = 2;
    public final static int TYPE_D = 3;
    public final static int TYPE_E = 4;
    private int type;
    private int index;

    public Model(int type, int index) {
        this.type = type;
        this.index = index;
    }

    public int getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

}
