package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean filtering() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<MappingJacksonValue> filteringList() {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanListFilter", filter);
        MappingJacksonValue mappingJacksonValue1 = new MappingJacksonValue(new SomeBean("value1", "value2", "value3"));
        mappingJacksonValue1.setFilters(filterProvider);
        MappingJacksonValue mappingJacksonValue2 = new MappingJacksonValue(new SomeBean("value4", "value5", "value6"));
        mappingJacksonValue2.setFilters(filterProvider);
        return Arrays.asList(mappingJacksonValue1, mappingJacksonValue2);
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

}
