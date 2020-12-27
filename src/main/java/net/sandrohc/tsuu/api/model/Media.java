package net.sandrohc.tsuu.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import net.sandrohc.tsuu.api.model.enums.MediaType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {

	private @Id Long id;
	private String slug;
	private String titlePortuguese;
	private String titleEnglish;
	private String titleRomanji;
	private String titleOriginal;
	private MediaType type;
	private String coverSmall;
	private String coverMedium;
	private String coverLarge;
	private String description;
	private String descriptionHTML;

}
