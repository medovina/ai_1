package connectfour;

import java.util.*;
import minimax.*;

public class ConnectFourGame implements HeuristicGame<ConnectFour, Integer> {
    @Override
    public ConnectFour initialState() { return new ConnectFour(); }

    @Override
    public ConnectFour clone(ConnectFour s) { return s.clone(); }

    @Override
    public int player(ConnectFour s) { return s.turn(); }

    @Override
    public List<Integer> actions(ConnectFour s) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        for (int x = 0 ; x < s.width() ; ++x)
            if (s.at(x, 0) == 0)
                moves.add(x);
                
        return moves;
    }

    @Override
    public void apply(ConnectFour s, Integer action) {
        if (!s.move(action))
            throw new Error("illegal move");
    }

    @Override
    public boolean isDone(ConnectFour s) {
        return s.winner() >= 0;
    }

    static double[] outcomeMap = { 0.5, 1.0, 0.0 };

    @Override
    public double outcome(ConnectFour s) {
        return outcomeMap[s.winner()];
    }

    @Override
    public double evaluate(ConnectFour state) {
        return HeuristicStrategy.evaluate(state);
    }
}
