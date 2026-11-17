package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * bonus()-ийн тестүүд. Мөрийн дугаар = DataFlowTargets.java.
 * ШАТ 1: ALL-DEFS хангах хамгийн цөөн тест (2).
 */
class BonusTest {

    @Test
    @DisplayName("T1 ([0,1,1,1,1,1], 6) = 3: present<threshold (50F), best=5 (53T); score 49->54, 54->56, streak 46->41")
    void t1_below() {
        assertEquals(3, DataFlowTargets.bonus(new int[] {0, 1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("T2 ([1], 1) = 5: present>=threshold (50T), best<5 (53F); score 51->56, streak 36->41")
    void t2_single() {
        assertEquals(5, DataFlowTargets.bonus(new int[] {1}, 1));
    }
}
