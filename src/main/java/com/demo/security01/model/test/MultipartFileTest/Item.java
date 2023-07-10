package com.demo.security01.model.test.MultipartFileTest;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private UploadFile profileImg;
}
