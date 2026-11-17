package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * bonus()-ийн тестүүд. Мөрийн дугаар = DataFlowTargets.java.
 * ШАТ 1 (ALL-DEFS): T1, T2.
 * ШАТ 2 (ALL-DU-PAIRS): T3..T8 нэмсэн. D1..D35 = README.md-ийн DU хүснэгтийн хосын ID.
 */
class BonusTest {

    // ---- ШАТ 1: ALL-DEFS ----

    @Test
    @DisplayName("T1 ([0,1,1,1,1,1], 6) = 3: эхний 0 -> streak 46->41; present<threshold -> score 49->54, 54->56 (D17 D29 D14)")
    void t1_below() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {0, 1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("T2 ([1], 1) = 5: present==threshold; best<5 -> score 51->56, streak 36->41 (D15 D28 D32)")
    void t2_single() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1}, 1));
    }

    // ---- ШАТ 2: ALL-DU-PAIRS ----

    @Test
    @DisplayName("T3 (null, 3) -> IAE: attendance(31->32a T) (D1)")
    void t3_null() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 3));
    }

    @Test
    @DisplayName("T4 ([1], -1) -> IAE: threshold(31->32b T) (D5)")
    void t4_negThr() {
        assertThrows(IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[] {1}, -1));
    }

    @Test
    @DisplayName("T5 ([], 0) = 5: хоосон массив, threshold=0; present(35->50T) (D10 D24 D32)")
    void t5_empty() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {}, 0));
    }

    @Test
    @DisplayName("T6 ([0,0,0], 1) = 0: бүгд тасалсан; present(35->50F), score(49->56) (D11 D30 D24)")
    void t6_allAbsent() {
        assertEquals(0, DataFlowTargets.bonus(new int[] {0, 0, 0}, 1));
    }

    @Test
    @DisplayName("T7 ([1x6,0,1,1], 8) = 8: 5-аас урт цуваа дунд тасрах, яг threshold; streak(41->42F), best(43->42F), score(51->54) (D19 D26 D31)")
    void t7_longBroken() {
        assertEquals(8, DataFlowTargets.bonus(new int[] {1, 1, 1, 1, 1, 1, 0, 1, 1}, 8));
    }

    @Test
    @DisplayName("T8 ([1,1,1,1], 4) = 5: цуваа 4 (5-ын хил дээр бонусгүй), яг threshold (D28 D32)")
    void t8_streak4() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1, 1, 1, 1}, 4));
    }
}
