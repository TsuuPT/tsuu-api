package net.sandrohc.tsuu.api.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fansub {

	private @Id Long id;
	private String slug;
	private String name;
	private String url;
	private String iconSmall;
	private String iconMedium;
	private String iconLarge;
	private String bannerSmall;
	private String bannerMedium;
	private String bannerLarge;
	private String founder;
	private LocalDate foundedDate;

}
