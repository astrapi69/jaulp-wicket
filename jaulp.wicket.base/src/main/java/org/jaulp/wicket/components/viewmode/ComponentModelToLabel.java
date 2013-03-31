package org.jaulp.wicket.components.viewmode;

import java.io.Serializable;

public interface ComponentModelToLabel<T> extends Serializable {

    String convertToLabel(T modelObject);
}