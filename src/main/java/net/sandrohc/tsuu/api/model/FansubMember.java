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
public class FansubMember {

	@ToString.Include
	@EqualsAndHashCode.Include
	private String name;

	@ToString.Include
	private String role;

	private String contact;
	private LinkType contactType;

}
