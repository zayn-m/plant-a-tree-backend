package com.buildsoft.plantatree.dto;

public class PlantDto extends PagingRequest {
	public static PlantDto defaultFilter() {
        return (PlantDto) PagingRequest.defaultFilter();
    }
}
