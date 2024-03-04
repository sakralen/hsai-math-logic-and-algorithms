package edu.hsai.regexfsm.finitestatemachine;

import java.util.HashSet;
import java.util.Set;

public class Edge {
    private State src;
    private State dst;
    public Set<Character> signals;
    public static Edge nil = buildEdge().setSrc(State.nil).setDst(State.nil).setSignals(new HashSet<>()).build();

    private Edge() {
    }

    public static Builder buildEdge() {
        return new Edge().new Builder();
    }

    public State getDst() {
        return dst;
    }

    @Override
    public String toString() {
        return String.format("src: %s; dst: %s; signals: %s", src, dst, signals.toString());
    }

    public class Builder {
        private Builder() {
        }

        public Builder setSrc(State src) {
            Edge.this.src = src;
            src.addEdge(Edge.this);
            return this;
        }

        public Builder setDst(State dst) {
            Edge.this.dst = dst;
            return this;
        }

        public Builder setSignals(Set<Character> signals) {
            Edge.this.signals = signals;
            return this;
        }

        public Builder setSignals(char signal) {
            Edge.this.signals = Set.of(signal);
            return this;
        }

        public Edge build() {
            return Edge.this;
        }
    }
}
