package com.buildsoft.plantatree.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryForm {
	@NotBlank
    @Size(min=3, max = 60)
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
