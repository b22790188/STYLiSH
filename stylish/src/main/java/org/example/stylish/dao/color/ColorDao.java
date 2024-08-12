package org.example.stylish.dao.color;

import org.example.stylish.dto.ColorDto;
import org.example.stylish.model.Color;

public interface ColorDao {

    Color getColor(String colorCode);

    Integer insertColor(ColorDto colorDto);
}
