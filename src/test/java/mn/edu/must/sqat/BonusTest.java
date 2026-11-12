package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("AD1 bonus({1,0,1},1)=5: present(35->40,40->40,40->50T), streak(36->41,46->41,41->42,41->43), best(37->42T,43->42F,43->53F), a(38->39), score(51->56)")
    void ad1_presentAbsentPresent_thresholdMet_score51to56() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 0, 1}, 1));
    }

    @Test
    @DisplayName("AD2 bonus({1,1,1,1,1},6)=3: score(49->54,54->56), streak(41->41), best(43->42T,43->53T), present(40->50F)")
    void ad2_fiveStreak_thresholdNotMet_score49to54_score54to56() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 6));
    }
}
