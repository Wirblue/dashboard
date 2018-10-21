package com.bassintag.dashboard.dto.twitch;

import lombok.Data;
import me.philippheuer.twitch4j.model.Stream;

@Data
public class StreamContainerDto {

    private int _total;

    private Stream[] streams;
}
