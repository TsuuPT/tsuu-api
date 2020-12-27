package net.sandrohc.tsuu.api.graphql.types;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class MediaTitleType {

	@Getter(AccessLevel.NONE) String _default;
	String portuguese;
	String english;
	String romanji;
	String original;

	public String getDefault() {
		return _default;
	}
}
