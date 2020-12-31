package net.sandrohc.tsuu.api.model.mapper;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface Mapper<O, D> {

	@NonNull
	D toDTO(@NonNull O obj);

	@NonNull
	O fromDTO(@NonNull D dto);

}
