package edu.hsai.regexfsm;

import edu.hsai.regexfsm.finitestatemachine.State;
import edu.hsai.regexfsm.finitestatemachine.Edge;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

// ArrayList<Characters> is used over String or char[] or etc. due to
// potential ability to make these implemented classes generic.
public class Generator {
    private static State currentState;
    private static final Random random = new Random(/*System.currentTimeMillis()*/);

    public static String generate(RegexFsm automata) {
        currentState = automata.getEntryState();

        ArrayList<Character> chars = new ArrayList<>();

        Edge nextEdge = nextEdge();
        while (nextEdge != Edge.nil) {
            int randomSignalIndex = random.nextInt(nextEdge.signals.size());

            chars.add(nextEdge.signals.stream().toList().get(randomSignalIndex));

            currentState = nextEdge.getDst();
            nextEdge = nextEdge();
        }

        return chars.stream().map(Object::toString).collect(Collectors.joining());
    }

    // !random.nextBoolean() gives 50\50 chance to leave
    // final state that is connected to itself
    private static Edge nextEdge() {
        if (currentState.isFinal()) {
            if (currentState.edges.size() == 0 || !random.nextBoolean()) {
                return Edge.nil;
            }
        }

        int nextEdgeIndex = random.nextInt(currentState.edges.size());
        return currentState.edges.get(nextEdgeIndex);
    }
}
