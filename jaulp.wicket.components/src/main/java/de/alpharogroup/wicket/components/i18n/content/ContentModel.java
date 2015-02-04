package de.alpharogroup.wicket.components.i18n.content;

import java.io.Serializable;

import net.sourceforge.jaulp.locale.ResourceBundleKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
public class ContentModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	@NonNull
	private ResourceBundleKey headerResourceKey;
	@NonNull
	private ResourceBundleKey contentResourceKey;

}
