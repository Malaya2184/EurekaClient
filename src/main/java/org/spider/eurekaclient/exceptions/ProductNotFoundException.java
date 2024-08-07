package org.spider.eurekaclient.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException{
    private Long id;
    private final String exceptionName = "Product Not Fount Custom Exception";
//    here this mssage will be used for setting exception details
    public ProductNotFoundException(String message, Long productId){
        super(message);
        this.id = productId;
    }
}
