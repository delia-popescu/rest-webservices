package com.examples.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// field1, field2
	@GetMapping(path = "/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("field1", "field2", "field3");
		return getObjectMappingValue(someBean, "field1", "field2");
	}

	// field1, field3
	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue filteringList() {
		List<SomeBean> beanList = Arrays.asList(new SomeBean("field1", "field2", "field3"),
				new SomeBean("field1", "field2", "field3"));
		return getObjectMappingValue(beanList, "field1", "field3");
	}

	private MappingJacksonValue getObjectMappingValue(Object obj, String... fieldsToBeIncluded) {
		PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToBeIncluded);
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapper = new MappingJacksonValue(obj);
		mapper.setFilters(filterProvider);

		return mapper;
	}

}
