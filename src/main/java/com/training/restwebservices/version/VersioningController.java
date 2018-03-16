package com.training.restwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {
	
	@GetMapping(path="/person/v1")
	public PersonV1 uriV1() {
		return new PersonV1(new Name("Popescu", "Robert"));
	}

	@GetMapping(path="/person/v2")
	public PersonV2 uriV2() {
		return new PersonV2("Popescu Robert");
	}
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 paramsV1() {
		return new PersonV1(new Name("Popescu", "Robert"));
	}

	@GetMapping(path="/person", params="version=2")
	public PersonV2 paramsV2() {
		return new PersonV2("Popescu Robert");
	}

	@GetMapping(path="/person", headers="X-API-VERSION=1")
	public PersonV1 headersV1() {
		return new PersonV1(new Name("Popescu", "Robert"));
	}

	@GetMapping(path="/person", headers="X-API-VERSION=2")
	public PersonV2 headersV2() {
		return new PersonV2("Popescu Robert");
	}

	@GetMapping(path="/person", produces="application/api-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1(new Name("Popescu", "Robert"));
	}

	@GetMapping(path="/person", produces="application/api-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2("Popescu Robert");
	}
}
