package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Lab 10, Part B: bonus() data-flow tests. D-numbers = README DU table (file line numbers).
 * Stage 1: ALL-DEFS (2 tests).
 */
class BonusTest {

    @Test
    @DisplayName("t01 ALL-DEFS: streak 5, reset, present again; score 5+3 [defs 31,35,36,37,38,40,41,43,46,51,54]")
    void t01_streakReset() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 0, 1}, 3));
    }

    @Test
    @DisplayName("t02 ALL-DEFS: all absent, below threshold; score 0 [def 49 -> 56]")
    void t02_allAbsent() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {0, 0, 0}, 1));
    }
}
