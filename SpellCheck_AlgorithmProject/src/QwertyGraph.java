import java.util.*;

class Vertex {

    public char value;
    public boolean visited;

    public Vertex(char val) {
        value = val;
        visited = false;
    }
}
//The class in which we write the Graph methods.
public class QwertyGraph {

    public int MAX;
    public Vertex[] vertexes;
    public int[][] edges;
    public char[] neighbours;
    public int numberOfElements;

    static class Array_list {

        public Integer integer;
        public Character character;

        public Array_list(Integer integer, Character character) {
            this.integer = integer;
            this.character = character;
        }

    }

    public static ArrayList<Array_list> levelChar;

    public QwertyGraph(int max) {
        MAX = max;
        vertexes = new Vertex[max];
        edges = new int[max][max];
        numberOfElements = 0;
        neighbours = new char[max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                edges[i][j] = 0;
            }
        }
    }

    public int returnIndex(char val) {
        for (int i = 0; i < numberOfElements; i++) {
            if (vertexes[i].value == val) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(char v1, char v2) {
        int ind1 = returnIndex(v1);
        int ind2 = returnIndex(v2);

        if (ind1 != -1 && ind2 != -1) {
            edges[ind1][ind2] = 1;
            edges[ind2][ind1] = 1;
        }
    }

    public void addVertex(char val) {
        vertexes[numberOfElements++] = new Vertex(val);
    }

    public int getAdjacentVertex(int i) {
        for (int j = 0; j < numberOfElements; j++) {
            if (edges[i][j] == 1 && vertexes[j].visited == false) {
                return j;
            }
        }
        return -1;
    }

    public void bfs(char charValue) {
        Queue<Vertex> queue1 = new LinkedList<>();
        Queue<Vertex> queue2 = new LinkedList<>();
        int index = returnIndex(charValue);
        queue1.add(vertexes[index]);
        vertexes[index].visited = true;
        int counterLevel = 0;
        int number = 0;
        levelChar = new ArrayList();
        while (!queue1.isEmpty() || !queue2.isEmpty()) {

            counterLevel++;

            while (!queue1.isEmpty()) {
                int adjacent = getAdjacentVertex(returnIndex(queue1.peek().value));

                if (adjacent == -1) {
                    queue1.remove();
                } else {
                    vertexes[adjacent].visited = true;
                    char temp = vertexes[adjacent].value;
                    neighbours[number] = temp;
                    number = number + 1;
                    levelChar.add(new Array_list(counterLevel, vertexes[adjacent].value));
                    //We keep the neighborhood degrees of each letter from its close neighbors to its far neighbors.
                    queue2.add(vertexes[adjacent]);
                }
            }

            counterLevel++;
            while (!queue2.isEmpty()) {
                int adjacent = getAdjacentVertex(returnIndex(queue2.peek().value));
                if (adjacent == -1) {
                    queue2.remove();
                } else {
                    vertexes[adjacent].visited = true;
                    char temp = vertexes[adjacent].value;
                    neighbours[number] = temp;

                    number = number + 1;
                    levelChar.add(new Array_list(counterLevel, vertexes[adjacent].value));

                    queue1.add(vertexes[adjacent]);
                }
            }

        }

    }

}