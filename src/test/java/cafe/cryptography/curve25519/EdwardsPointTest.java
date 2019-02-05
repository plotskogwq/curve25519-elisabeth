package cafe.cryptography.curve25519;

import org.junit.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EdwardsPointTest {
    /**
     * Compressed Edwards Y form of 2*basepoint.
     */
    static final CompressedEdwardsY BASE2_CMPRSSD = new CompressedEdwardsY(
            Utils.hexToBytes("c9a3f86aae465f0e56513864510f3997561fa2c9e85ea21dc2292309f3cd6022"));

    @Test
    public void basepointDecompressionCompression() {
        CompressedEdwardsY encoded = new CompressedEdwardsY(
                Utils.hexToBytes("5866666666666666666666666666666666666666666666666666666666666666"));
        EdwardsPoint B = encoded.decompress();
        assertThat(B.compress(), is(encoded));
    }

    @Test
    public void basepointPlusBasepointVsBasepoint2Constant() {
        EdwardsPoint B2 = Constants.ED25519_BASEPOINT.add(Constants.ED25519_BASEPOINT);
        assertThat(B2.compress(), is(BASE2_CMPRSSD));
    }

    @Test
    public void basepointPlusBasepointProjectiveNielsVsBasepoint2Constant() {
        EdwardsPoint B2 = Constants.ED25519_BASEPOINT.add(Constants.ED25519_BASEPOINT.toProjectiveNiels()).toExtended();
        assertThat(B2.compress(), is(EdwardsPointTest.BASE2_CMPRSSD));
    }

    @Test
    public void basepointPlusBasepointAffineNielsVsBasepoint2Constant() {
        EdwardsPoint B2 = Constants.ED25519_BASEPOINT.add(Constants.ED25519_BASEPOINT.toAffineNiels()).toExtended();
        assertThat(B2.compress(), is(EdwardsPointTest.BASE2_CMPRSSD));
    }
}
