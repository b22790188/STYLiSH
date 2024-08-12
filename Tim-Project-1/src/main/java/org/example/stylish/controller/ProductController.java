package org.example.stylish.controller;

import org.example.stylish.dto.ErrorResponseDto;
import org.example.stylish.dto.ResponseDto;
import org.example.stylish.dto.productDto.ProductInfoDto;
import org.example.stylish.dto.productDto.ProductInfoListDto;
import org.example.stylish.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(@RequestParam(required = false, defaultValue = "0") Integer paging,
                                            @RequestParam(required = false, defaultValue = "false") Boolean getAll) {
        try {
            ProductInfoListDto productInfoListDto;
            if (getAll) {
                productInfoListDto = productService.getAllProducts();
            } else {
                productInfoListDto = productService.getAllProducts(paging);
            }
            return new ResponseEntity<>(productInfoListDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category, @RequestParam(required = false, defaultValue = "0") Integer paging) {
        try {
            ProductInfoListDto productInfoListDto = productService.getProductsByCategory(paging, category);
            return new ResponseEntity<>(productInfoListDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsBySearch(@RequestParam String keyword, @RequestParam(required = false, defaultValue = "0") Integer paging) {
        try {
            ProductInfoListDto productInfoListDto = productService.getProductsBySearch(paging, keyword);
            return new ResponseEntity<>(productInfoListDto, HttpStatus.OK);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(new ErrorResponseDto("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto("Bad request"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<?> getProductDetails(@RequestParam long id) {
        try {
            ProductInfoDto productInfoDto = productService.getProductsById(id);
            ResponseDto<ProductInfoDto> responseDto = new ResponseDto<>(productInfoDto);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (DataAccessException dae) {
            return new ResponseEntity<>(new ErrorResponseDto("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto("Bad request"), HttpStatus.BAD_REQUEST);
        }

    }
}
