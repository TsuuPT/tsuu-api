package net.sandrohc.tsuu.api.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Release {

	private @Id Long id;
	private Long fansubId;
	private Long mediaId;
	private String description;
	private String descriptionHTML;
	private String classifier;
	private LocalDateTime timestamp;

}
