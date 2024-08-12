package org.example.stylish.controller;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dto.CreateResponseDto;
import org.example.stylish.dto.ErrorResponseDto;
import org.example.stylish.dto.campaignDto.CreateCampaignDto;
import org.example.stylish.dto.productDto.CreateProductDto;
import org.example.stylish.service.FileService;
import org.example.stylish.service.MarketingService;
import org.example.stylish.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Log4j2
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    MarketingService marketingService;

    @Autowired
    FileService fileService;

    private static final Path UPLOADDIR = Paths.get("uploads/");
    private static final String SERVER = "http://52.69.33.14/";

    // todo: refactor upload logic to service and modify return type in the future
    @PostMapping("/product")
    public String handleProductUpload(CreateProductDto requestProductDto,
                                      @RequestParam("main_image") MultipartFile mainImage,
                                      @RequestParam("images") List<MultipartFile> images

    ) {
        String mainImageUrl;
        String imageUrl;

        if (!Files.exists(UPLOADDIR)) {
            try {
                Files.createDirectories(UPLOADDIR);
                System.out.println("create dir successfully" + UPLOADDIR);
            } catch (IOException e) {
                System.out.println("create dir failed" + UPLOADDIR);
            }
        }

        try {
            String[] imagesUrl = new String[images.size()];
            int imagesIndex = 0;

            // Save the main image
            if (!mainImage.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                String originalFileName = mainImage.getOriginalFilename();
                String newFileName = uuid + "-" + originalFileName;

                byte[] bytes = mainImage.getBytes();

                Path path = UPLOADDIR.resolve(newFileName);

                Files.write(path, bytes);
                mainImageUrl = SERVER + UPLOADDIR + "/" + newFileName;
            } else {
                mainImageUrl = null;
            }


            // Save the additional images and url
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    String uuid = UUID.randomUUID().toString();

                    String originalFileName = image.getOriginalFilename();
                    String newFileName = uuid + "-" + originalFileName;

                    byte[] bytes = image.getBytes();
                    Path path = UPLOADDIR.resolve(newFileName);

                    imageUrl = SERVER + UPLOADDIR + "/" + newFileName;
                    imagesUrl[imagesIndex] = imageUrl;
                    Files.write(path, bytes);


                }
                imagesIndex++;
            }

            // Log the ProductDTO fields
            System.out.println("Product: " + requestProductDto);

            //return response productDto
            return productService.createProduct(requestProductDto, mainImageUrl, imagesUrl);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/campaign")
    public ResponseEntity<?> createCampaign(CreateCampaignDto createCampaignDto,
                                            @RequestPart("picture") MultipartFile picture) {
        try {
            String pictureUrl = fileService.uploadFile(picture);
            marketingService.createCampaign(createCampaignDto, pictureUrl);
            return new ResponseEntity<>(new CreateResponseDto("Create campaign successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponseDto("Create campaign failed, product already exist"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
