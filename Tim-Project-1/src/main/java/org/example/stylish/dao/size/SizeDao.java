package org.example.stylish.dao.size;

import org.example.stylish.model.Size;

public interface SizeDao {
    Size getSize(String size);
    Integer insertSize(String size);
}
