package org.example.stylish.dao.image;

import java.util.List;

public interface ImageDao {
    String insertImageUrl(long productId, String imageUrl);

    List<String> getImagesByProductId(long productId);
}
