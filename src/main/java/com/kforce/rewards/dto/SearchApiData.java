package com.kforce.rewards.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchApiData {

    private List<SearchData> search = new ArrayList<SearchData>();

}
