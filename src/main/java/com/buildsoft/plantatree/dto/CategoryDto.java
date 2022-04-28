package com.buildsoft.plantatree.dto;

public class CategoryDto extends PagingRequest {
	public static CategoryDto defaultFilter() {
        return (CategoryDto) PagingRequest.defaultFilter();
    }
}
