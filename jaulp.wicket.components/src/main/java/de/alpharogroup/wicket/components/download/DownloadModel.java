package de.alpharogroup.wicket.components.download;

import java.io.Serializable;

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
public class DownloadModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@NonNull 
	private String filename;
	@NonNull 
	private String path; 
	@NonNull 
	private String contentType;
}
