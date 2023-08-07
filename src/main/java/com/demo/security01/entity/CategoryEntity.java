package com.demo.security01.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "CATEGORIES")
@Builder
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


    @Column(name = "DEPTH") // 계층 의미
    private Long depth;

    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryEntity> subCategory = new ArrayList<>();
}
