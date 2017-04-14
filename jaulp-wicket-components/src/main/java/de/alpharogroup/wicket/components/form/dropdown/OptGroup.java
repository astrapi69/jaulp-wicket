/**
 * Copyright (C) 2010 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.wicket.components.form.dropdown;

/**
 * The Interface {@link OptGroup} that have to implement objects that wants to be rendered in the
 * {@link OptGroupDropDownChoice}.
 */
public interface OptGroup
{
    /**
     * Gets the label of the optgroup.
     *
     * @return the label of the optgroup.
     */
    public String getLabel();
}