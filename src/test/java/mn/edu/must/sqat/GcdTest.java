package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * gcd-ийн all-DU-pairs хамралт. Мөрийн дугаар = DataFlowTargets.java.
 * Хос: хувьсагч(def мөр -> use мөр). 15T/15F = while нөхцлийн үнэн/худал салаа.
 * Хосын ID (P1..P13) нь README.md-ийн хүснэгттэй ижил.
 */
class GcdTest {

    @Test
    @DisplayName("T1 gcd(12,8)=4: 2 iterations; covers P1 x(13->16) P3 x(17->16) P4 x(17->20) P5 y(13->15T) P7 y(13->16) P8 y(13->17) P9 y(18->15T) P10 y(18->15F) P11 y(18->16) P12 y(18->17) P13 tmp(16->18)")
    void t1_twoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("T2 gcd(7,0)=7: zero iterations; covers ONLY P2 x(13->20) P6 y(13->15F)")
    void t2_zeroIterations() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("T3 gcd(0,5)=5: one iteration (extra); covers P1 x(13->16) P4 x(17->20) P5 y(13->15T) P7 y(13->16) P8 y(13->17) P10 y(18->15F) P13 tmp(16->18)")
    void t3_oneIteration() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
