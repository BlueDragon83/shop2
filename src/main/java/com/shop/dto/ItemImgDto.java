package com.shop.dto;

import com.shop.domain.entity.item.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto {

    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String mainImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    // Entity -> DTO
    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg,ItemImgDto.class);
    }

    // DTO -> Entity 없는 이유는 regImgYn 값을 직접 설정해서 변환해야함

}
