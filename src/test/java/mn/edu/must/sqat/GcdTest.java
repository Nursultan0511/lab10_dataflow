package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * gcd-ийн all-DU-pairs хамралт. Мөрийн дугаар = DataFlowTargets.java.
 * Хос: хувьсагч(def мөр -> use мөр). 15T/15F = while нөхцлийн үнэн/худал салаа.
 */
class GcdTest {

    @Test
    @DisplayName("T1 gcd(12,8)=4: 2 iterations; x(13->16) x(17->16) x(17->20) y(13->15T) y(13->16) y(13->17) y(18->15T) y(18->15F) y(18->16) y(18->17) tmp(16->18)")
    void t1_twoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("T2 gcd(7,0)=7: zero iterations; x(13->20) y(13->15F) ONLY")
    void t2_zeroIterations() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("T3 gcd(0,5)=5: one iteration; x(13->16) x(17->20) y(13->15T) y(13->16) y(13->17) y(18->15F) tmp(16->18)")
    void t3_oneIteration() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
