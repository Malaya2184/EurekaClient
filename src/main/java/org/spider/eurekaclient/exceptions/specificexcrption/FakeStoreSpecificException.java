package org.spider.eurekaclient.exceptions.specificexcrption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreSpecificException extends RuntimeException{
    private String name;
    public FakeStoreSpecificException(String message, String name) {
        super(message);
        this.name = name;
    }
}
