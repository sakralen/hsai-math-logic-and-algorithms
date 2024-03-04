package edu.hsai.regexfsm.finitestatemachine;

import java.util.ArrayList;
import java.util.Set;

public class State {
    public final ArrayList<Edge> edges = new ArrayList<>();
    private String id;
    private boolean isFinal = false;
    public static State nil = buildState().setId("nil").build();

    private State() {
    }

    public static Builder buildState() {
        return new State().new Builder();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public String getId() {
        return id;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public String toString() {
        return (!isFinal) ? id : (id + " (final)");
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(String id) {
            State.this.id = id;
            return this;
        }

        public Builder makeFinal() {
            State.this.isFinal = true;
            return this;
        }

        public Builder connectTo(State dst, Set<Character> signals) {
            Edge.buildEdge().setSrc(State.this).setDst(dst).setSignals(signals).build();
            return this;
        }

        public Builder connectTo(State dst, char signal) {
            Edge.buildEdge().setSrc(State.this).setDst(dst).setSignals(signal).build();
            return this;
        }

        public Builder connectToItself(Set<Character> signals) {
            Edge.buildEdge().setSrc(State.this).setDst(State.this).setSignals(signals).build();
            return this;
        }

        public Builder connectToItself(char signal) {
            Edge.buildEdge().setSrc(State.this).setDst(State.this).setSignals(signal).build();
            return this;
        }

        public State build() {
            return State.this;
        }
    }
}
