package com.kforce.rewards.mapper;

import com.kforce.rewards.response.Metadata;

public interface BaseMapper {
    Metadata metadataDTOToMetadata(Metadata metadata);
}
