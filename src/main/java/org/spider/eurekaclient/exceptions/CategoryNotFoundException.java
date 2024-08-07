package org.spider.eurekaclient.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryNotFoundException extends RuntimeException{
    private Long id;
    private final String exceptionName = "Category not Found exception";

    public CategoryNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
