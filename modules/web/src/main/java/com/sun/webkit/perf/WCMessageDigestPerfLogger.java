/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.webkit.perf;

import java.util.logging.Logger;
import com.sun.webkit.security.WCMessageDigest;
import java.nio.ByteBuffer;

public class WCMessageDigestPerfLogger extends WCMessageDigest {

    private final static Logger log =
            Logger.getLogger(WCMessageDigestPerfLogger.class.getName());

    private static final PerfLogger logger = PerfLogger.getLogger(log);

    final private WCMessageDigest digest;

    public WCMessageDigestPerfLogger(WCMessageDigest digest) {
        this.digest = digest;
    }

    public synchronized static boolean isEnabled() {
        return logger.isEnabled();
    }

    @Override
    public void addBytes(ByteBuffer input) {
        logger.resumeCount("ADDBYTES");
        digest.addBytes(input);
        logger.suspendCount("ADDBYTES");
    }

    @Override
    public byte[] computeHash() {
        logger.resumeCount("COMPUTEHASH");
        byte[] result = digest.computeHash();
        logger.suspendCount("COMPUTEHASH");
        return result;
    }
}
