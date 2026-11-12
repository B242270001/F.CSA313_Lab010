package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * gcd() - all-DU-pairs coverage.
 * Line numbers refer to DataFlowTargets.java (13 = params, 15 = while, 16, 17, 18, 20 = return).
 * Pair notation: var(def -> use); 15T / 15F = true / false branch of the p-use on line 15.
 */
class GcdTest {

    @Test
    @DisplayName("G1 gcd(7,0)=7, zero iterations: x(13->20), y(13->15F)")
    void g1_zeroIterations_x13to20_y13to15F() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G2 gcd(0,5)=5, one iteration: x(13->16), x(17->20), y(13->15T), y(13->16), y(13->17), y(18->15F), tmp(16->18)")
    void g2_oneIteration_x13to16_x17to20_y13to15T_y18to15F_tmp16to18() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }

    @Test
    @DisplayName("G3 gcd(12,8)=4, two iterations: x(17->16), y(18->15T), y(18->16), y(18->17) + all pairs of G2")
    void g3_twoIterations_x17to16_y18to15T_y18to16_y18to17() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }
}
