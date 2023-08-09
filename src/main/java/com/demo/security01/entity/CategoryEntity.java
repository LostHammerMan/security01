package com.demo.security01.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "CATEGORIES")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_IDX")
    private Long categoryIdx;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentCategory")
    private CategoryEntity parentCategory;

    @Column(name = "CATEGORY_PATH")
    private String categoryPath;


    @Column(name = "DEPTH") // 계층 의미
    private Integer depth;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<CategoryEntity> subCategory = new ArrayList<>();

    @Builder
    public CategoryEntity(Long categoryIdx, String categoryName, CategoryEntity parentCategory, Integer depth) {
        this.categoryIdx = categoryIdx;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.depth = depth;
    }
}
