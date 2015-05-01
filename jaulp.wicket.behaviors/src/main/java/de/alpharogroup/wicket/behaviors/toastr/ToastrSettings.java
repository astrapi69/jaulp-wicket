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
package de.alpharogroup.wicket.behaviors.toastr;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class encapsulates various settings for the toastr jquery plugin. See the documentation for
 * the jquery toastr plugin for further information.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ToastrSettings implements Serializable
{

	/** The serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Shows a close button if true; defaults to false;. */
	private boolean closeButton = false;

	/** Shows a debug messages if true; defaults to false;. */
	private boolean debug = false;

	/** Shows the newest notification if true; defaults to false;. */
	private boolean newestOnTop = false;

	/** Shows a progress bar if true; defaults to false;. */
	private boolean progressBar = false;

	/** The position where to show the notifications; defaults to Position.TOP_CENTER; */
	private Position positionClass = Position.TOP_CENTER;

	/** If true duplicate notifications will be prevent; defaults to false;. */
	private boolean preventDuplicates = false;

	/** Document please; defaults to false;. */
	private boolean tapToDismiss = false;

	private String onclick = null;

	/**
	 * The duration to show. (defaults to 300)
	 */
	private int showDuration = 300;

	/**
	 * The duration to hide. (defaults to 1000)
	 */
	private int hideDuration = 1000;

	/**
	 * The timeOut. (defaults to 5000)
	 */
	private int timeOut = 5000;

	/**
	 * The extended timeOut. (defaults to 1000)
	 */
	private int extendedTimeOut = 1000;

	private Easing showEasing = Easing.SWING;

	private Easing hideEasing = Easing.LINEAR;

	private ShowMethod showMethod = ShowMethod.FADE_IN;

	private HideMethod hideMethod = HideMethod.FADE_OUT;

	private ToastrType toastrType = ToastrType.SUCCESS;

	private String notificationContent = null;

	private String notificationTitle = null;

}
