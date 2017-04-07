package com.milin.loveth.service;

import static com.google.common.collect.Lists.newArrayList;

import com.milin.loveth.helper.ImageFilesHelper;
import com.milin.loveth.models.Img;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Changjun Liao on 2016/10/11.
 */
@Service
public class ImagesService {

    private static final String webPrefix = "pics/";
    private static final int DEFAULT_SIZE = 12;

    /**
     * @param size default is 12
     */
    public List<Img> loadRandomImageLists(Integer size) {
        List<Img> allImgs = ImageFilesHelper.allImageFiles();
        int resultSize = allImgs.size();
        if (size == null) {
            size = DEFAULT_SIZE;
        } else if (size == -1) {
            size = resultSize;
        }
        List<Img> resultList = newArrayList();
        Collections.shuffle(allImgs);
        if (resultSize <= size) {
            for (int sIndex = 0; sIndex < size; sIndex++) {
                resultList.add(allImgs.get(sIndex % resultSize));
            }
        } else {
            resultList.addAll(allImgs.stream().filter(img -> img.getName().startsWith("p_")).collect(Collectors.toList()));
            allImgs.removeAll(resultList);
            int needCount = size - resultList.size();
            resultList.addAll(allImgs.subList(0, needCount));
        }
        return resultList.stream().sorted((o1, o2) -> {
            if (ImageFilesHelper.isPrior(o1.getName())) {
                return -1;
            }
            if (ImageFilesHelper.isPrior(o2.getName())) {
                return 1;
            }
            return 0;
        }).map(f -> {
            f.setName(webPrefix + f.getName());
            return f;
        }).collect(Collectors.toList());
    }

    public void deleteImg(String imgName) {
        String fileName = imgName;
        if (fileName.startsWith(webPrefix)) {
            fileName = fileName.replace(webPrefix, "");
        }
        ImageFilesHelper.deleteImg(fileName);
    }

    public void saveImg(MultipartFile file, Long fileLen) throws IOException {
        long size = file.getSize();
        if (size != fileLen) {
            throw new IllegalArgumentException("size is not matched!");
        }
        ImageFilesHelper.saveNewImg(file.getInputStream(), file.getOriginalFilename());
    }
}
