package org.example.stylish.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    T data;
    @JsonProperty("next_paging")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer nextPage;

    public ResponseDto(T data) {
        this.data = data;
    }
}
