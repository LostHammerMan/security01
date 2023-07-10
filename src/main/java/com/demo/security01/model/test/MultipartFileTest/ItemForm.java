package com.demo.security01.model.test.MultipartFileTest;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long itemId;
//    private String itemName;
    private MultipartFile profileImg;
}
