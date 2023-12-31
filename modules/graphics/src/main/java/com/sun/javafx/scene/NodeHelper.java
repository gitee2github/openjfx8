/*
 * Copyright (c) 2013, 2014, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.javafx.scene;

import com.sun.glass.ui.Accessible;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.text.Font;

/**
 * Used to access internal methods of Node.
 */
public class NodeHelper {
    private static NodeAccessor nodeAccessor;

    static {
        forceInit(Node.class);
    }

    private NodeHelper() {
    }

    public static void layoutNodeForPrinting(Node node) {
        nodeAccessor.layoutNodeForPrinting(node);
    }

    public static boolean isDerivedDepthTest(Node node) {
        return nodeAccessor.isDerivedDepthTest(node);
    };

    public static SubScene getSubScene(Node node) {
        return nodeAccessor.getSubScene(node);
    };

    public static Accessible getAccessible(Node node) {
        return nodeAccessor.getAccessible(node);
    };

    public static void recalculateRelativeSizeProperties(Node node, Font fontForRelativeSizes) {
        nodeAccessor.recalculateRelativeSizeProperties(node, fontForRelativeSizes);
    }

    public static void setNodeAccessor(final NodeAccessor newAccessor) {
        if (nodeAccessor != null) {
            throw new IllegalStateException();
        }

        nodeAccessor = newAccessor;
    }

    public static NodeAccessor getNodeAccessor() {
        if (nodeAccessor == null) {
            throw new IllegalStateException();
        }

        return nodeAccessor;
    }

    public interface NodeAccessor {
        void layoutNodeForPrinting(Node node);
        boolean isDerivedDepthTest(Node node);
        SubScene getSubScene(Node node);
        void setLabeledBy(Node node, Node labeledBy);
        Accessible getAccessible(Node node);
        void recalculateRelativeSizeProperties(Node node, Font fontForRelativeSizes);
    }

    private static void forceInit(final Class<?> classToInit) {
        try {
            Class.forName(classToInit.getName(), true,
                          classToInit.getClassLoader());
        } catch (final ClassNotFoundException e) {
            throw new AssertionError(e);  // Can't happen
        }
    }
}
