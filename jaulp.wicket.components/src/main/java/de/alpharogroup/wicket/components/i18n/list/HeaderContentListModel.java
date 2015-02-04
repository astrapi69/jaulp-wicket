package de.alpharogroup.wicket.components.i18n.list;

import java.io.Serializable;
import java.util.List;

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
public class HeaderContentListModel implements Serializable
{

	private static final long serialVersionUID = 1L;
	@NonNull
	private ResourceBundleKey headerResourceKey;
	@NonNull
	private List<ResourceBundleKey> contentResourceKeys;

}
