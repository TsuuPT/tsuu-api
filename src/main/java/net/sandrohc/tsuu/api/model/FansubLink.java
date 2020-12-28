package net.sandrohc.tsuu.api.model;

import lombok.*;

import net.sandrohc.tsuu.api.model.enums.LinkType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FansubLink {

	@ToString.Include
	@EqualsAndHashCode.Include
	private String link;

	private LinkType type;

}
