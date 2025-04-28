package com.comcast.stringinator.controller;

import com.comcast.stringinator.exception.ValidationException;
import com.comcast.stringinator.model.StatsResult;
import com.comcast.stringinator.model.StringinatorInput;
import com.comcast.stringinator.model.StringinatorResult;
import com.comcast.stringinator.service.StringinatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringinatorController {

    @Autowired
    private StringinatorService stringinatorService;
    private static final Logger logger = LoggerFactory.getLogger(StringinatorController.class);

    @GetMapping("/")
	public String index() {
		return "<pre>\n" +
		"Welcome to the Stringinator 3000 for all of your string manipulation needs.\n" +
		"GET / - You're already here! \n" +
		"POST /stringinate - Get all of the info you've ever wanted about a string. Takes JSON of the following form: {\"input\":\"your-string-goes-here\"}\n" +
		"GET /stats - Get statistics about all strings the server has seen, including the longest and most popular strings.\n" +
		"</pre>";
	}

    @GetMapping(path = "/stringinate", produces = "application/json")
    public StringinatorResult stringinateGet(@RequestParam(name = "input", required = true) String input) {
        logger.info("Received GET request for /stringinate with input: {}", input);
        return stringinatorService.stringinate(new StringinatorInput(input));
    }

	@PostMapping(path = "/stringinate", consumes = "application/json", produces = "application/json")
    public StringinatorResult stringinate(@RequestBody StringinatorInput input) {
        logger.info("Received POST request for /stringinate with input: {}", input.getInput());

        if (input.getInput() == null || input.getInput().isEmpty()) {
            logger.error("Validation failed: Input is null or empty");
            throw new ValidationException("Input cannot be null or empty");
        }
        if (input.getInput().length() > 1000) {
            logger.error("Validation failed: Input exceeds maximum length of 1000 characters");
            throw new ValidationException("Input string exceeds the maximum allowed length of 1000 characters");
        }

        return stringinatorService.stringinate(input);
    }

    @GetMapping(path = "/stats")
    public StatsResult stats() {
        logger.info("Serving stats request");
        StatsResult result = stringinatorService.stats();
        logger.info("Stats result: {}", result);
        return result;
    }
}
