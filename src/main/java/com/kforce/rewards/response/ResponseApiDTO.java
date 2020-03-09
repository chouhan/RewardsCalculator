package com.kforce.rewards.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseApiDTO<T> implements Serializable {

    private static final long serialVersionUID = -3016256894889290713L;

    private Metadata metadata;

    @JsonProperty(value = "data")
    private List<T> result;

    @JsonProperty(value = "error")
    private Object[] error = {};

    @JsonProperty(value = "additional_data")
    private Object[] additionalData = {};

}
