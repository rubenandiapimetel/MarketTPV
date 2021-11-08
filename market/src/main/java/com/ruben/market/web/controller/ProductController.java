package com.ruben.market.web.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.ruben.market.domain.Product;
import com.ruben.market.domain.service.ProductService;

/*
Esta clase tiene comunicacion directa con la API ya
@getmapping sirve para mapear desde la api mediante el http/ y los {} para sistituirlos por el parametro
ResponseEntity sirve para proporcionar una informacion mas detallada de del estado de nuestra peticion como
el numero del error que manualmente lo tenemos que configurar.
 */

@Component
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code = 200, message = "OK")

    public ResponseEntity<List<Product>> getAll() {

        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")

    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {

        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //quantity devuelve todos los productos que queden menores a esa cantidad -1
    @GetMapping("/quantity/{quantity}")
    public ResponseEntity<List<Product>> getScaseProduct(@PathVariable("quantity") int quantity) {
        if(productService.getScaseProduct(quantity).get().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return
               new ResponseEntity(productService.getScaseProduct(quantity), HttpStatus.OK);
    }


    //muy interesante, en los optionals<objeto> que no sean listas si que sirve la funcion .map.orelse como sustitucion de un if else
    //sin embargo en los list hay que usar la fucnion anterion de get.isempty con su correspondiente ifelse

    @GetMapping("/{productId}")
    // apioperation y apiresponse sirve para usarlo con swagger
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found"),
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7")@PathVariable("productId") int productId) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //en este caso product forma parte del cuerpo de la peticion y no como parametro como los otros
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity delete(@PathVariable("productId") int productId) {

        if (productService.delete(productId) == true)
            return new ResponseEntity<>(HttpStatus.OK);
            else
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
}
