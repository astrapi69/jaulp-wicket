package de.alpharogroup.wicket.components.report;

import java.io.Serializable;

import org.apache.wicket.request.component.IRequestablePage;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

@Getter 
@Setter 
@EqualsAndHashCode 
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Instantiates a new report throwable model that is used to report an exception
 * or error an a description from the affected user.
 */
public class ReportThrowableModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The affected username. */
	private String affectedUsername;

	/** The root username. */
	private String rootUsername;

	/** The stack trace. */
	private String stackTrace;

	/** The stack trace. */
	private String description;

	/** The response page. */
	private Class<? extends IRequestablePage> responsePage;

}
