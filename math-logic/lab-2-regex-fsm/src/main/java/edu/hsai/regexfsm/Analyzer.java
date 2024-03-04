package edu.hsai.regexfsm;

import edu.hsai.regexfsm.finitestatemachine.Edge;
import edu.hsai.regexfsm.finitestatemachine.State;

import java.util.ArrayList;
import java.util.stream.Collectors;

// ArrayList<Characters> is used over String or char[] or etc. due to
// potential ability to make these implemented classes generic.
public class Analyzer {
    private static State currentState;

    public static Result analyze(RegexFsm automata, String word) {
        currentState = automata.getEntryState();
        ArrayList<Character> chars = word.chars().mapToObj(e -> (char) e)
                .collect(Collectors.toCollection(ArrayList::new));

        for (Character letter : chars) {
            if (!automata.getAlphabet().contains(letter)) {
                return Result.NOT_IN_ALPHABET;
            }

            currentState = next(letter);
            if (currentState == State.nil) {
                return Result.NOT_MATCH;
            }
        }

        if (currentState.isFinal()) {
            return Result.MATCH;
        }
        return Result.NOT_MATCH;
    }

    private static State next(Character letter) {
        for (Edge edge : currentState.edges) {
            if (edge.signals.contains(letter)) {
                return edge.getDst();
            }
        }

        return State.nil;
    }

    public enum Result {
        MATCH,
        NOT_MATCH,
        NOT_IN_ALPHABET
    }
}
