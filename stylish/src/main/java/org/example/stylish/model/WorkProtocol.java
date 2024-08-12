package org.example.stylish.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkProtocol {
    private String stream;
    private Integer type;
}
