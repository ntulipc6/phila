/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortestpath;

import java.io.*;
import java.util.*;

/**
 *
 * @author user
 */
public class ShortestPath {

    /**
     * @param args the command line arguments
     */
    static int M = 0;
    static int N = 0;

    final static String START = "S";
    final static String END = "E";
    final static String WALL = "W";

    String[][] minDistance(String grid[][]) {
        QItem source = new QItem(0, 0, 0);

        // To keep track of visited QItems. Marking
        // blocked cells as visited.
        boolean[][] visited = new boolean[M][N];

        String[][] resGrid = grid;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = WALL.equalsIgnoreCase(grid[i][j]);

                // Finding source
                if (START.equalsIgnoreCase(grid[i][j])) {
                    source.setRow(i);
                    source.setCol(j);
                }
            }
        }

        // applying BFS on matrix cells starting from source
        Queue<QItem> q = new LinkedList<>();
        q.add(source);

        visited[source.getRow()][source.getCol()] = true;
        while (!q.isEmpty()) {
            QItem p = q.remove();

            // Destination found;
            if (END.equalsIgnoreCase(grid[p.getRow()][p.getCol()])) {
                System.out.println("distance: " + p.getDist());
                return resGrid;
            }

            if (p.getCol() - 1 >= 0 // moving left
                    && visited[p.getRow()][p.getCol() - 1] == false) {
                q.add(new QItem(p.getRow(), p.getCol() - 1, p.getDist() + 1));
                visited[p.getRow()][p.getCol() - 1] = true;
                if (!END.equalsIgnoreCase(resGrid[p.getRow()][p.getCol() - 1])) {
                    resGrid[p.getRow()][p.getCol() - 1] = "*";
                }
            }

            // moving right
            if (p.getCol() + 1 < N
                    && visited[p.getRow()][p.getCol() + 1] == false) {
                q.add(new QItem(p.getRow(), p.getCol() + 1, p.getDist() + 1));
                visited[p.getRow()][p.getCol() + 1] = true;

                if (!END.equalsIgnoreCase(resGrid[p.getRow()][p.getCol() + 1])) {
                    resGrid[p.getRow()][p.getCol() + 1] = "*";
                }
            }

            // moving up
            if (p.getRow() - 1 >= 0
                    && visited[p.getRow() - 1][p.getCol()] == false) {
                q.add(new QItem(p.getRow() - 1, p.getCol(), p.getDist() + 1));
                visited[p.getRow() - 1][p.getCol()] = true;
                if (!END.equalsIgnoreCase(resGrid[p.getRow() - 1][p.getCol()])) {
                    resGrid[p.getRow() - 1][p.getCol()] = "*";
                }
            }

            // moving down
            if (p.getRow() + 1 < M
                    && visited[p.getRow() + 1][p.getCol()] == false) {
                q.add(new QItem(p.getRow() + 1, p.getCol(), p.getDist() + 1));
                visited[p.getRow() + 1][p.getCol()] = true;
                if (!END.equalsIgnoreCase(resGrid[p.getRow() + 1][p.getCol()])) {
                    resGrid[p.getRow() + 1][p.getCol()] = "*";
                }
            }
        }
        return resGrid;

    }

    public static void main(String[] args) throws FileNotFoundException {

        // read in the data
        ArrayList<ArrayList<String>> a = new ArrayList<>();
        Scanner input = new Scanner(new File("src/map.txt"));
        while (input.hasNextLine()) {
            M++;
            Scanner colReader = new Scanner(input.nextLine());
            ArrayList<String> col = new ArrayList();

            while (colReader.hasNext()) {
                col.add(colReader.next());
            }
            if (M == 1) {
                N = col.size();
            }
            a.add(col);
        }

        String[][] grid = new String[a.size()][];
        for (int i = 0; i < a.size(); i++) {
            ArrayList<String> row = a.get(i);
            grid[i] = row.toArray(new String[row.size()]);
        }

        for (String[] re : grid) {
            for (int j = 0; j < N; j++) {
                System.out.print(re[j] + "  ");
            }
            System.out.println();
        }

        String[][] resGrid = new ShortestPath().minDistance(grid);

        for (String[] re : resGrid) {
            for (int j = 0; j < N; j++) {
                System.out.print(re[j] + "  ");
            }
            System.out.println();
        }
    }
}
