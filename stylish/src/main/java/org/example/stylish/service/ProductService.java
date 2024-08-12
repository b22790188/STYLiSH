package org.example.stylish.service;

import lombok.extern.log4j.Log4j2;
import org.example.stylish.dao.campaign.CampaignDao;
import org.example.stylish.dao.color.ColorDao;
import org.example.stylish.dao.image.ImageDao;
import org.example.stylish.dao.junction.JunctionDao;
import org.example.stylish.dao.product.ProductDao;
import org.example.stylish.dao.size.SizeDao;
import org.example.stylish.dao.variant.VariantDao;
import org.example.stylish.dto.ColorDto;
import org.example.stylish.dto.VariantDto;
import org.example.stylish.dto.productDto.CreateProductDto;
import org.example.stylish.dto.productDto.ProductInfoDto;
import org.example.stylish.dto.productDto.ProductInfoListDto;
import org.example.stylish.mapper.ProductMapper;
import org.example.stylish.model.Color;
import org.example.stylish.model.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Log4j2
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private SizeDao sizeDao;
    @Autowired
    private JunctionDao junctionDao;
    @Autowired
    private VariantDao variantDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private CampaignDao campaignDao;

    public String createProduct(CreateProductDto requestProductDto, String mainImageUrl, String[] imageUrls) {

        long productId = 0;
        long colorId = 0;
        long sizeId = 0;

        productId = productDao.insertProduct(requestProductDto, mainImageUrl);


        for (ColorDto colorDto : requestProductDto.getColors()) {

            Color color = colorDao.getColor(colorDto.getCode());
            if (color != null) {
                colorId = color.getId();
            } else {
                colorId = colorDao.insertColor(colorDto);
            }
            //insert productId and colorId to junctionTable
            junctionDao.insertProductIdAndColorId(productId, colorId);
        }

        // insert size table
        for (String size : requestProductDto.getSizes()) {
            Size productSize = sizeDao.getSize(size);
            if (productSize != null) {
                sizeId = productSize.getId();
            } else {
                sizeId = sizeDao.insertSize(size);
            }
            //insert productId and sizeId to junctionTable
            junctionDao.insertProductIdAndSizeId(productId, sizeId);
        }

        //initialize productId, colorId, sizeId for insert into variant table
        for (VariantDto variantDto : requestProductDto.getVariants()) {
            colorId = colorDao.getColor(variantDto.getColorCode()).getId();
            sizeId = sizeDao.getSize(variantDto.getSize()).getId();
            variantDao.insertVariant(productId, colorId, sizeId, variantDto.getStock());
        }

        //insert image table
        for (String imageUrl : imageUrls) {
            imageDao.insertImageUrl(productId, imageUrl);
        }

        return "Create product successfully!";
    }

    public ProductInfoListDto getAllProducts() {
        List<Map<String, Object>> products = productDao.getAllProducts();
        return getProductInfoListDto(0, products, true);
    }

    public ProductInfoListDto getAllProducts(Integer paging) {

        List<Map<String, Object>> products = productDao.getAllProducts(paging);
        return getProductInfoListDto(paging, products, false);
    }

    public ProductInfoListDto getProductsByCategory(Integer paging, String category) {
        List<Map<String, Object>> products = productDao.getProductsByCategory(paging, category);
        return getProductInfoListDto(paging, products, false);
    }

    public ProductInfoListDto getProductsBySearch(Integer paging, String category) {
        List<Map<String, Object>> products = productDao.getProductsBySearch(paging, category);
        return getProductInfoListDto(paging, products, false);
    }

    public ProductInfoDto getProductsById(long id) {
        Map<String, Object> product = productDao.getProductById(id);
        long productId = ((BigInteger) product.get("id")).longValue();
        List<ColorDto> colors = junctionDao.getColorsByProductId(productId);
        List<String> sizes = junctionDao.getSizesByProductId(productId);
        List<VariantDto> variants = variantDao.getVariantsByProductId(productId);
        List<String> images = imageDao.getImagesByProductId(productId);

        return ProductMapper.mapToProductInfoDto(product, colors, sizes, variants, images);
    }


    //method extracted from duplicated code
    private ProductInfoListDto getProductInfoListDto(Integer paging, List<Map<String, Object>> products, boolean getAll) {
        List<ProductInfoDto> productInfoDtos = new ArrayList<>();
        ProductInfoListDto productInfoListDto;
        Integer nextPaging;
        int subIndex;

        for (Map<String, Object> product : products) {
            long productId = ((BigInteger) product.get("id")).longValue();
            List<ColorDto> colors = junctionDao.getColorsByProductId(productId);
            List<String> sizes = junctionDao.getSizesByProductId(productId);
            List<VariantDto> variants = variantDao.getVariantsByProductId(productId);
            List<String> images = imageDao.getImagesByProductId(productId);

            ProductInfoDto productInfoDto = ProductMapper.mapToProductInfoDto(product, colors, sizes, variants, images);
            productInfoDtos.add(productInfoDto);
        }

        productInfoListDto = new ProductInfoListDto();

        if (getAll) {
            productInfoListDto.setNextPaging(null);
            productInfoListDto.setData(productInfoDtos);
        } else {
            nextPaging = (productInfoDtos.size() == 7) ? paging + 1 : null;
            subIndex = (productInfoDtos.size() == 7) ? 6 : productInfoDtos.size();
            productInfoListDto.setNextPaging(nextPaging);
            productInfoListDto.setData(productInfoDtos.subList(0, subIndex));
        }

        return productInfoListDto;
    }
}
