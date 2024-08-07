package org.spider.eurekaclient.dtos.exceptionDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreSpecificExceptionDto {
    private String name;
    private String message;
}
