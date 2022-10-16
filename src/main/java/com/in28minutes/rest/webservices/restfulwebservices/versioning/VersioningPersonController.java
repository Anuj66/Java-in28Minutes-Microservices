package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new Name("Anuj", "Saraogi"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getPersonV1RP() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getPersonV2RP() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1RH() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2RH() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
