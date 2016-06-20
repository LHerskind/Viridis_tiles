package dtu.tilecolor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AI {
    private ArrayList<Integer> solution = new ArrayList<Integer>();
    private int mapWidth = 5;

    public AI(int[] initial) {
        isSolvable(initial);

    }

    final PriorityQueue<State> queue = new PriorityQueue<State>(100, new Comparator<State>() {
        @Override
        // De forskellige states bliver sammenlignet i forhold til deres
        // prioritet, dvs. deres værdi af f.
        public int compare(State a, State b) {
            return a.priority() - b.priority();
        }
    });
    // // Her defineres et puzzle-State
    class State {
        int[] tiles;
        int player;
        int g; // Antallet af steps fra initial State.
        int h; // Den estimerede, heuristiske værdi til målet (Antallet af røde tiles)
        State previous; // Forrige state i vejen til løsningen

        // A* prioritetsfunktionen (f=g+h).
        int priority() {
            return g + h;
        }

        // Her sættes start state
        State(int[] initial) {
            this.tiles = initial;
            player = index(tiles, -1);
            g = 0;
            h = heuristic(tiles, player);
            previous = null;
        }

        // Her defineres de senere states, hvor spilleren rykker plads
        State(State previous, int slideFromIndex) {
            // Set the tiles to the previous ones
            tiles = Arrays.copyOf(previous.tiles, previous.tiles.length);
            player = slideFromIndex;
            tiles[player]--;

            g = previous.g + 1;
            h = heuristic(tiles, player);
            this.previous = previous;
        }

        boolean isGoal() {
            if(h != 0) return false;
            else return true;

        }

        State moveDown() {
            if (player + mapWidth >= tiles.length || tiles[player + mapWidth] == 0) {
                return null;
            } else {
                return new State(this, player + mapWidth);
            }
        }

        State moveUp() {
            if (player - mapWidth < 0  ||  tiles[player-mapWidth] == 0) {
                return null;
            } else {
                return new State(this, player - mapWidth);
            }
        }

        State moveRight() {
            if (player + 1 >= tiles.length || player % 5 == 4 || tiles[player+1] == 0) {
                return null;
            } else {
                return new State(this, player + 1);
            }
        }

        State moveLeft() {
            if (player <= 0 || player % 5 == 0 || tiles[player-1] == 0) {
                return null;
            } else {
                return new State(this, player - 1);
            }
        }

        public void toArray() {
            if (previous != null) {
                solution.add(player);
                previous.toArray();
            }
        }

    }

    void addNextState(State nextState) {
        if (nextState != null)
            queue.add(nextState);
    }

    // Run the solver.
    public void solve(int[] initial) {

        // Her resettes de to lister
        queue.clear();

        // Start staten tilføjes til queuen.
        queue.add(new State(initial));

        while (!queue.isEmpty()) {

            // Hent den første state i queuen
            State state = queue.poll();

            // Hvis dette er målet, er vi færdige, og state.toArray holder styr
            // på routen derhen
            if (state.isGoal()) {
                state.toArray();
                return;
            }



            // Her tilføjer vi om muligt 4 nye states til vores queue
            addNextState(state.moveRight());
            addNextState(state.moveDown());
            addNextState(state.moveLeft());
            addNextState(state.moveUp());
        }
    }

    // Bruges til at finde spilleren i start-staten
    static int index(int[] tiles, int val) {
        for (int i = 0; i < tiles.length; i++) {

            if (tiles[i] == val) {
                tiles[i] = 1;
                return i;
            }
        }
        return -1;
    }
    // Den ene heuristiske metode, som hjælper med at finde en løsning hurtigst muligt, altså ikke
    //med kortest mulige steps
    static int heuristic(int[] tiles, int player) {
        int h = 0;
        for (int i = 0; i < tiles.length; i++) {

            if (tiles[i] == 2) {
                h++;
                /*
                if (Math.abs((player % 5) - (i % 5)) + Math.abs((player/5) - (i / 5)) <= 2) {
                    h += Math.abs((player % 5) - (i % 5)) + Math.abs((player/5) - (i / 5));
                }
                */
                 h += Math.abs((player % 5) - (i % 5)) + Math.abs((player/5) - (i / 5));
            }
        }
        return h;
    }
    // Den anden heuristiske metode, som hjælper med at finde løsningen med kortest mulige trin (Anvendes ikke her)
    static int heuristic(int[] tiles) {
        int h = 0;
        for (int i = 0; i < tiles.length; i++) {

            if (tiles[i] == 2) {
                h++;
            }
        }
        return h;
    }
    // Indeholder løsningen
    public ArrayList<Integer> getSolution() {
        Collections.reverse(solution);

        return solution;
    }
    // Returnerer true hvis banen kan løses, false ellers (Hvis solution-arraylisten er tom)
    public boolean isSolvable(int[] map) {
        solve(map);
        if(getSolution() == null) {
            return false;
        }
        else return true;
    }
}