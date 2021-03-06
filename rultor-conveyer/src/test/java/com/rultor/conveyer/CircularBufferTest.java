/**
 * Copyright (c) 2009-2013, rultor.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the rultor.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.rultor.conveyer;

import com.jcabi.aspects.Tv;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link CircularBuffer}.
 * @author Yegor Bugayenko (yegor@tpc2.com)
 * @version $Id$
 * @checkstyle ClassDataAbstractionCoupling (500 lines)
 */
public final class CircularBufferTest {

    /**
     * CircularBuffer can write and read.
     * @throws Exception If some problem inside
     */
    @Test
    public void writesAndReads() throws Exception {
        final CircularBuffer buf = new CircularBuffer(Tv.FIVE);
        buf.write((byte) 1);
        buf.write((byte) 2);
        MatcherAssert.assertThat(buf.read(), Matchers.equalTo((byte) 1));
        MatcherAssert.assertThat(buf.isEmpty(), Matchers.equalTo(false));
        MatcherAssert.assertThat(buf.read(), Matchers.equalTo((byte) 2));
        MatcherAssert.assertThat(buf.isEmpty(), Matchers.equalTo(true));
    }

    /**
     * CircularBuffer can write and read with overflow.
     * @throws Exception If some problem inside
     */
    @Test
    public void writesAndReadsWithOverflow() throws Exception {
        final CircularBuffer buf = new CircularBuffer(Tv.THREE);
        final byte data = 1;
        for (int idx = 0; idx < Tv.TEN; ++idx) {
            buf.write(data);
        }
        MatcherAssert.assertThat(buf.isEmpty(), Matchers.equalTo(false));
        for (int idx = 0; idx < 2; ++idx) {
            MatcherAssert.assertThat(buf.read(), Matchers.equalTo(data));
        }
        MatcherAssert.assertThat(buf.isEmpty(), Matchers.equalTo(true));
    }

}
