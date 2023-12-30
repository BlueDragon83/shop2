package com.shop.service;

import com.shop.domain.entity.item.ItemImg;
import com.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final FileService fileService;
    private static final ItemImgRepository itemImgRepository = null;

    // 상품 이미지 저장
    public static void saveItemImg(ItemImg itemimg) throws IOException {
        if (itemimg != null) {
            // 상품 이미지 정보 저장
            itemImgRepository.save(itemimg);
        }
    }

    // 상품 이미지 수정
    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws IOException {

        // 상품 이미지를 수정했다면
        if (!itemImgFile.isEmpty()) {
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);

            // 기존 이미지 파일이 존재한다면 삭제
            if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation + "/" + savedItemImg);
            }
            String origFileName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, origFileName, itemImgFile.getBytes());
            ItemImg itemImg = savedItemImg.builder().origFileName(origFileName).imgName(imgName).filePath(savedItemImg.getFilePath()).fileSize(itemImgFile.getSize()).build();
            itemImgRepository.save(itemImg);

        }
    }
}