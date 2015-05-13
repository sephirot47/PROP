package Domini;
 
import java.util.ArrayList;
 
/**
 * @author pau
 */
public class DJSets {
 
    private ArrayList<Integer> arr;
 
    public DJSets(int nElements) {
        arr = new ArrayList<Integer>();
        for (int i = 0; i < nElements; ++i) arr.add(-1);
    }
 
    public int size() {
        return arr.size();
    }
 
    // Pre: r1 != r2 and they are roots of their respective sets
    public int union(int r1, int r2) {
        if (arr.get(r2) < arr.get(r1)) {
            arr.set(r2, arr.get(r1) + arr.get(r2));
            arr.set(r1, r2);
            return -1*arr.get(r2);
        }
        else {
            arr.set(r1, arr.get(r1) + arr.get(r2));
            arr.set(r2, r1);
            return -1*arr.get(r1);
        }
    }
 
    public int find(int x) throws IllegalArgumentException {
        if (x >= arr.size()) throw new IllegalArgumentException("Trying to find an unexisting position.");
        if (arr.get(x) < 0) return x;
        else {
            arr.set(x, find(arr.get(x)));
            return arr.get(x);
        }
    }
 
    public int add() {
        arr.add(-1);
        return arr.size() - 1;
    }

    }
