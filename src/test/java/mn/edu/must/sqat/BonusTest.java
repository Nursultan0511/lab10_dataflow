package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Lab 10, Part B: bonus() data-flow tests. D-numbers = README DU table (file line numbers).
 * Stage 1 (ALL-DEFS): t01, t02.  Stage 2 (ALL-DU-PAIRS): t03..t10.
 * D22 (best 37->42 F) and D23 (best 37->53 T) are infeasible, so no test covers them.
 */
class BonusTest {

    // ---------------- Stage 1: ALL-DEFS ----------------

    @Test
    @DisplayName("t01 ALL-DEFS: streak 5, reset, present again; score 5+3 [D2 D3 D4 D6 D7 D9 D12 D13 D15 D16 D17 D18 D19 D20 D21 D25 D26 D27 D29 D30 D33 D35]")
    void t01_streakReset() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 0, 1}, 3));
    }

    @Test
    @DisplayName("t02 ALL-DEFS: all absent, below threshold; score 0 [D2 D3 D4 D6 D8 D11 D24 D30 D32]")
    void t02_allAbsent() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {0, 0, 0}, 1));
    }

    // ---------------- Stage 2: ALL-DU-PAIRS ----------------

    @Test
    @DisplayName("t03 null array -> IAE [D1 attendance 31->32 p-T]")
    void t03_nullArray() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 3));
    }

    @Test
    @DisplayName("t04 negative threshold -> IAE [D5 threshold 31->32 p-T]")
    void t04_negThreshold() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("t05 empty array, threshold 0; score 5 [D10 present 35->50 p-T, D24 best 37->53 p-F, D34 score 51->56]")
    void t05_emptyArray() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("t06 streak broken in the middle; score 5 [D18 streak 41->42 p-F, D26 best 43->42 p-F, D28 best 43->53 p-F, D34]")
    void t06_midBreak() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 0, 1}, 2));
    }

    @Test
    @DisplayName("t07 streak 5 but below threshold; score 3 [D8 threshold 31->50 p-F, D14 present 40->50 p-F, D31 score 49->54]")
    void t07_belowThr() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("t08 present == threshold, streak 5; score 8 [D7 D13 p-T at exact threshold, D33 D35]")
    void t08_exactThr() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1}, 5));
    }

    @Test
    @DisplayName("t09 streak longer than 5; score 8 [D12 D16 D25 D27 D33 D35]")
    void t09_longStreak() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 1, 1}, 3));
    }

    @Test
    @DisplayName("t10 threshold 0, absent then present; score 5 [D20 streak 46->41, D28 best 43->53 p-F, D13]")
    void t10_thrZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {0, 1}, 0));
    }
}
