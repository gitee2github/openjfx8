/*
 * Copyright (c) 2011, 2020, Oracle and/or its affiliates. All rights reserved.
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

package javafx.beans.property.adapter;

import com.sun.javafx.property.adapter.ReadOnlyJavaBeanPropertyBuilderHelper;
import com.sun.javafx.property.adapter.ReadOnlyPropertyDescriptor;

import java.lang.reflect.Method;

/**
 * A {@code ReadOnlyJavaBeanFloatPropertyBuilder} can be used to create
 * {@link ReadOnlyJavaBeanFloatProperty ReadOnlyJavaBeanFloatProperties}. To create
 * a {@code ReadOnlyJavaBeanFloatProperty} one first has to call {@link #create()}
 * to generate a builder, set the required properties, and then one can
 * call {@link #build()} to generate the property.
 * <p>
 * Not all properties of a builder have to specified, there are several
 * combinations possible. As a minimum the {@link #name(java.lang.String)} of
 * the property and the {@link #bean(java.lang.Object)} have to be specified.
 * If the name of the getter follows the conventions, this is sufficient.
 * Otherwise it is possible to specify an alternative name for the getter
 * ({@link #getter(java.lang.String)}) or
 * the getter {@code Methods} directly ({@link #getter(java.lang.reflect.Method)}).
 * <p>
 * All methods to change properties return a reference to this builder, to enable
 * method chaining.
 * <p>
 * If you have to generate adapters for the same property of several instances
 * of the same class, you can reuse a {@code ReadOnlyJavaBeanFloatPropertyBuilder}.
 * by switching the Java Bean instance (with {@link #bean(java.lang.Object)} and
 * calling {@link #build()}.
 *
 * @see ReadOnlyJavaBeanFloatProperty
 * @since JavaFX 2.1
 */
public final class ReadOnlyJavaBeanFloatPropertyBuilder {

    private final ReadOnlyJavaBeanPropertyBuilderHelper helper = new ReadOnlyJavaBeanPropertyBuilderHelper();

    /**
     * Create a new instance of {@code ReadOnlyJavaBeanFloatPropertyBuilder}
     *
     * @return the new {@code ReadOnlyJavaBeanFloatPropertyBuilder}
     */
    public static ReadOnlyJavaBeanFloatPropertyBuilder create() {
        return new ReadOnlyJavaBeanFloatPropertyBuilder();
    }

    /**
     * Generate a new {@link ReadOnlyJavaBeanFloatProperty} with the current settings.
     *
     * @return the new {@code ReadOnlyJavaBeanFloatProperty}
     * @throws NoSuchMethodException if the settings were not sufficient to find
     * the getter of the Java Bean property
     * @throws IllegalArgumentException if the Java Bean property is not of type
     * {@code float} or {@code Float}
     */
    public ReadOnlyJavaBeanFloatProperty build() throws NoSuchMethodException {
        final ReadOnlyPropertyDescriptor descriptor = helper.getDescriptor();
        if (!float.class.equals(descriptor.getType()) && !Number.class.isAssignableFrom(descriptor.getType())) {
            throw new IllegalArgumentException("Not a float property");
        }
        return new ReadOnlyJavaBeanFloatProperty(descriptor, helper.getBean());
    }

    /**
     * Set the name of the property
     *
     * @param name the name of the property
     * @return a reference to this builder to enable method chaining
     */
    public ReadOnlyJavaBeanFloatPropertyBuilder name(String name) {
        helper.name(name);
        return this;
    }

    /**
     * Set the Java Bean instance the adapter should connect to
     *
     * @param bean the Java Bean instance
     * @return a reference to this builder to enable method chaining
     */
    public ReadOnlyJavaBeanFloatPropertyBuilder bean(Object bean) {
        helper.bean(bean);
        return this;
    }

    /**
     * Set the Java Bean class in which the getter should be searched.
     * This can be useful, if the builder should generate adapters for several
     * Java Beans of different types.
     *
     * @param beanClass the Java Bean class
     * @return a reference to this builder to enable method chaining
     */
    public ReadOnlyJavaBeanFloatPropertyBuilder beanClass(Class<?> beanClass) {
        helper.beanClass(beanClass);
        return this;
    }

    /**
     * Set an alternative name for the getter. This can be omitted, if the
     * name of the getter follows Java Bean naming conventions.
     *
     * @param getter the alternative name of the getter
     * @return a reference to this builder to enable method chaining
     */
    public ReadOnlyJavaBeanFloatPropertyBuilder getter(String getter) {
        helper.getterName(getter);
        return this;
    }

    /**
     * Set the getter method directly. This can be omitted, if the
     * name of the getter follows Java Bean naming conventions.
     *
     * @param getter the getter
     * @return a reference to this builder to enable method chaining
     */
    public ReadOnlyJavaBeanFloatPropertyBuilder getter(Method getter) {
        helper.getter(getter);
        return this;
    }
}
