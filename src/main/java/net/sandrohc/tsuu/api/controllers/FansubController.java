package net.sandrohc.tsuu.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import net.sandrohc.tsuu.api.model.Fansub;
import net.sandrohc.tsuu.api.services.FansubService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class FansubController {

	private final FansubService fansubService;


//	@GetMapping
//	public Flux<Fansub> getAll() {
//		return fansubService.getAll();
//	}

	@RequestMapping
	public String hello() {
		return "HELLO";
	}

}
