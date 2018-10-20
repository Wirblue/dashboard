package com.bassintag.dashboard.dto.country;

import lombok.Data;

/**
 * CountryDataDto.java create for dashboard
 *
 * @author Louis
 * @version 1.0
 * @since 20/10/2018
 */
@Data
public class CountryDataDto {

    private String name;

    private String[] topLevelDomain;

    private String alpha2Code;

    private String alpha3Code;

    private String[] callingCodes;

    private String capital;

    private String region;

    private String subregion;

    private String nativeName;

    private int population;

    private int[] latlng;

    private int area;

    private String[] borders;

    private String flag;
}
