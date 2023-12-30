package com.shop.domain.entity.item;

import com.shop.domain.BaseTimeEntity;
import com.shop.domain.entity.item.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemImg extends BaseTimeEntity{

    @Id
    @Column(name="item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; //이미지 파일명
    private String origFileName; //원본 이미지 파일명
    private String filePath; //이미지 파일 경로
    private Long fileSize; //이미지 파일 용량
    private String mainImgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemImg(String origFileName, String imgName, String filePath, Long fileSize, String mainImgYn) {
        this.origFileName = origFileName;
        this.imgName = imgName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mainImgYn = mainImgYn;
    }
    
}