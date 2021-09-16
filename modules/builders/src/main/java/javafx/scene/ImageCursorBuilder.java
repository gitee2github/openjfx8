/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
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

package javafx.scene;

/**
Builder class for javafx.scene.ImageCursor
@see javafx.scene.ImageCursor
@deprecated This class is deprecated and will be removed in the next version
* @since JavaFX 2.0
*/
@javax.annotation.Generated("Generated by javafx.builder.processor.BuilderProcessor")
@Deprecated
public class ImageCursorBuilder<B extends javafx.scene.ImageCursorBuilder<B>> implements javafx.util.Builder<javafx.scene.ImageCursor> {
    protected ImageCursorBuilder() {
    }

    /** Creates a new instance of ImageCursorBuilder. */
    @SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
    public static javafx.scene.ImageCursorBuilder<?> create() {
        return new javafx.scene.ImageCursorBuilder();
    }

    private double hotspotX;
    /**
    Set the value of the {@link javafx.scene.ImageCursor#getHotspotX() hotspotX} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B hotspotX(double x) {
        this.hotspotX = x;
        return (B) this;
    }

    private double hotspotY;
    /**
    Set the value of the {@link javafx.scene.ImageCursor#getHotspotY() hotspotY} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B hotspotY(double x) {
        this.hotspotY = x;
        return (B) this;
    }

    private javafx.scene.image.Image image;
    /**
    Set the value of the {@link javafx.scene.ImageCursor#getImage() image} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B image(javafx.scene.image.Image x) {
        this.image = x;
        return (B) this;
    }

    /**
    Make an instance of {@link javafx.scene.ImageCursor} based on the properties set on this builder.
    */
    public javafx.scene.ImageCursor build() {
        javafx.scene.ImageCursor x = new javafx.scene.ImageCursor(this.image, this.hotspotX, this.hotspotY);
        return x;
    }
}
