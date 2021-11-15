package model;


public class State {

    protected boolean acceptState = false;
    protected int []transitions;

    /**
     * Constructor of the state
     */
    public State(int lengthOfEdges) {

        transitions = new int[lengthOfEdges];
        for (int i = 0; i < lengthOfEdges; ++i) {
            transitions[i] = -1;
        }
    }

    /**
     * Accept state setter
     */
    public void setAcceptState() {
        acceptState = true;
    }

    /**
     * Transaction setter
     * @param index, pos to be setted
     * @param transitionTo, where is the transaction to
     */
    public void setTransitions(int index, int transitionTo) {
        transitions[index] = transitionTo;
    }

    /**
     * Transaction getter
     * @param index, position to be given
     * @return the transaction
     */
    public int getTransition(int index) {

        return transitions[index];
    }

    /**
     * Accept state getter
     * @return, if is accepted or not
     */
    public boolean isAcceptState() {
        return acceptState;
    }

}
