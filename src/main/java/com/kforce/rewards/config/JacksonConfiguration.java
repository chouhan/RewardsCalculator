package com.kforce.rewards.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kforce.rewards.util.DomainFilter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JacksonConfiguration {

    ObjectMapper objectMapper = null;

    public JacksonConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
    }

    public void mapBeanFilterAndSerialize(String fields, DomainFilter domainFilter) {
        String regExp = "[,\\s]+";
        Set<String> columnList = new HashSet<>();
        FilterProvider filters = new SimpleFilterProvider().addFilter(domainFilter.toString(),
                SimpleBeanPropertyFilter.serializeAllExcept(columnList));
        if (!StringUtils.isEmpty(fields)) {
            columnList = new HashSet<>(Arrays.asList(fields.split(regExp)));
            filters = new SimpleFilterProvider().addFilter(domainFilter.toString(),
                    SimpleBeanPropertyFilter.filterOutAllExcept(columnList));
        }
        this.objectMapper.setFilterProvider(filters);
    }
}