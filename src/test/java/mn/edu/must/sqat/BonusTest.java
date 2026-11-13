package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * bonus() - data flow tests.
 * Line numbers refer to DataFlowTargets.java (31-56).
 * Pair notation: var(def -> use); T / F = true / false branch of a p-use.
 */
class BonusTest {

    // ---------- Stage 1: ALL-DEFS (minimum = 2 tests) ----------

    @Test
    @DisplayName("AD1 bonus({1,0,1},1)=5: present(35->40,40->40,40->50T), streak(36->41,46->41,41->42T,41->42F,41->43), best(37->42T,43->42F,43->53F), a(38->39T,38->39F), score(51->56)")
    void ad1_presentAbsentPresent() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 0, 1}, 1));
    }

    @Test
    @DisplayName("AD2 bonus({1,1,1,1,1},6)=3: score(49->54,54->56), streak(41->41), best(43->42T,43->53T), present(40->50F), threshold(31->50F)")
    void ad2_fiveStreakBelowThreshold() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 6));
    }

    // ---------- Stage 2: ALL-DU-PAIRS (pairs not reached by AD1, AD2) ----------

    @Test
    @DisplayName("DU1 bonus(null,1) throws: attendance(31->32T)")
    void du1_nullArrayThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 1));
    }

    @Test
    @DisplayName("DU2 bonus({1},-1) throws: threshold(31->32T)")
    void du2_negativeThresholdThrows() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("DU3 bonus({},0)=5: present(35->50T), best(37->53F), attendance(31->38F with zero iterations)")
    void du3_emptyArrayThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("DU4 bonus({0,0,0},1)=0: present(35->50F), score(49->56), best(37->53F), 46 kills streak 36 and 46")
    void du4_allAbsent() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {0, 0, 0}, 1));
    }

    @Test
    @DisplayName("DU5 bonus({1,1,1,1,1,1},6)=8: score(51->54), present(40->50T), best(43->53T)")
    void du5_sixStreakExactThreshold() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 1}, 6));
    }

    // ---------- Part C: kills mutant M2 (best = present) ----------

    @Test
    @DisplayName("DU6 bonus({1,1,1,0,1,1},5)=5: present=5 but best=3, so the values at best(43->53F) differ from present; streak(46->41), best(43->42F)")
    void du6_streakBrokenInMiddle() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 1, 0, 1, 1}, 5));
    }
}
