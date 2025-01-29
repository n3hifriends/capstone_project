package com.scaler.capstone.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.SortedMapType;

@Getter
@Setter
public class SortParam {
    private SortType sortType;
    private String sortCriteria;

}
