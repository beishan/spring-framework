/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.expression.spel.support;

import java.lang.reflect.Method;

/**
 * A {@link org.springframework.expression.PropertyAccessor} variant for data binding
 * purposes, using reflection to access properties for reading and possibly writing.
 *
 * <p>A property can be accessed through a public getter method (when being read)
 * or a public setter method (when being written), and also as a public field.
 *
 * <p>This accessor is explicitly designed for user-level property evaluation
 * and does not resolve technical properties on {@code java.lang.Object}.
 * For more resolution power, choose {@link ReflectivePropertyAccessor} instead.
 *
 * @author Juergen Hoeller
 * @since 4.3.15
 * @see #forReadOnlyAccess()
 * @see #forReadWriteAccess()
 * @see SimpleEvaluationContext
 * @see StandardEvaluationContext
 * @see ReflectivePropertyAccessor
 */
public class DataBindingPropertyAccessor extends ReflectivePropertyAccessor {

	/**
	 * Create a new property accessor for reading and possibly also writing.
	 * @param allowWrite whether to also allow for write operations
	 * @see #canWrite
	 */
	private DataBindingPropertyAccessor(boolean allowWrite) {
		super(allowWrite);
	}

	@Override
	protected boolean isCandidateForProperty(Method method) {
		return (method.getDeclaringClass() != Object.class);
	}


	/**
	 * Create a new data-binding property accessor for read-only access.
	 */
	public static DataBindingPropertyAccessor forReadOnlyAccess() {
		return new DataBindingPropertyAccessor(false);
	}

	/**
	 * Create a new data-binding property accessor for read-write access.
	 */
	public static DataBindingPropertyAccessor forReadWriteAccess() {
		return new DataBindingPropertyAccessor(true);
	}

}
