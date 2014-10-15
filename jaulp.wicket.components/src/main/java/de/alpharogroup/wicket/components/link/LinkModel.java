package de.alpharogroup.wicket.components.link;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;
import net.sourceforge.jaulp.locale.ResourceBundleKey;

import org.apache.wicket.Page;
@Getter 
@Setter 
@EqualsAndHashCode 
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Class<? extends Page> pageClass; 
	@NonNull
	private ResourceBundleKey resourceModelKey;
	
	private String url;
}
