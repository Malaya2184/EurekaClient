package org.spider.eurekaclient.controllers;

import org.spider.eurekaclient.dtos.exceptionDto.FakeStoreSpecificExceptionDto;
import org.spider.eurekaclient.exceptions.ProductNotFoundException;
import org.spider.eurekaclient.exceptions.specificexcrption.FakeStoreSpecificException;
import org.spider.eurekaclient.models.FakeStoreProduct;
import org.spider.eurekaclient.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakestore/products")
public class FakeStoreProductController implements ProductController<FakeStoreProduct> {
    private final ProductService productService;

    @Autowired
    public FakeStoreProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FakeStoreProduct> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        FakeStoreProduct product = (FakeStoreProduct) productService.getProductByid(id);
//        throw new RuntimeException("Throwing run time exception");
//        try {
//            throw new RuntimeException("Exception thrown from try catch block");
//        }catch (RuntimeException e){
//            System.out.println("Exception caught and handled in try catch block");
//        }
/*         There might be several type of exceptions we need to handle, so we can't handle all
           the exceptions insid econtrollers otherwise th redability will be affected, so we can use controller advice to handle all the excepptions throwen by controller
*/
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        throw new ProductNotFoundException("FakeStoreProduct  Not found for the product id : "+id, id);
    }

    @GetMapping
    public List<FakeStoreProduct> getAllProducts(){

        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<FakeStoreProduct> createProduct(@RequestBody FakeStoreProduct product){

        FakeStoreProduct productResponse = (FakeStoreProduct) productService.createProduct(product);
        if(productResponse != null){
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/{id}")
    public FakeStoreProduct updateProduct(@PathVariable("id") Long id, @RequestBody FakeStoreProduct product){
        return (FakeStoreProduct) productService.updateProduct(id, product);
    }

    // Specific Exception for this controller so made it private
    @ExceptionHandler(FakeStoreSpecificException.class)
    private ResponseEntity<FakeStoreSpecificExceptionDto> handelSpecificException(FakeStoreSpecificException e){
        FakeStoreSpecificExceptionDto fakeStoreSpecificExceptionDto = new FakeStoreSpecificExceptionDto();
        fakeStoreSpecificExceptionDto.setMessage(e.getMessage());
        fakeStoreSpecificExceptionDto.setName(e.getName());
        return new ResponseEntity<>(fakeStoreSpecificExceptionDto, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public FakeStoreProduct replaceProduct(@PathVariable("id") Long id, @RequestBody FakeStoreProduct product) throws FakeStoreSpecificException{
         FakeStoreProduct response = (FakeStoreProduct) productService.replaceProduct(id, product);
//         make rsponse null to check wheather specific exception is working or not
//         response = null;
         if(response != null){
             return response;
         }
        throw new FakeStoreSpecificException("FakeStoreProduct not found , So can't replace it", "FakeStoreProduct replacement failed exception");
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/througharithemeticexception")
    public void throughArithmeticException(){
//        the message will send from here automatically catch by the Controller advice
            int x = 10/0;
    }
}
