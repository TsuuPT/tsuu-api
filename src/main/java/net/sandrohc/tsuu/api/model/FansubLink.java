package net.sandrohc.tsuu.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import net.sandrohc.tsuu.api.model.enums.LinkType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FansubLink {

	private @Id Long id;
	private Long fansubId;
	private String link;
	private LinkType linkType;

}
