package net.sandrohc.tsuu.api.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class HelloController {

	@Value("${server.port}")
	private int serverPort;


	@RequestMapping
	public String hello() throws UnknownHostException {
		return "HELLO FROM " + InetAddress.getLocalHost() + ":" + serverPort;
	}

}
