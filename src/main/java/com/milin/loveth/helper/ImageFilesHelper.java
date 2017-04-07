package com.milin.loveth.helper;

import static com.google.common.collect.Lists.newArrayList;

import com.milin.loveth.models.Img;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/**
 * Created by Changjun Liao on 2016/10/11.
 */
public class ImageFilesHelper {

    private static final Logger logger = LoggerFactory.getLogger(ImageFilesHelper.class);
    private static final String relativePath = "/static/pics/";
    private static final String imgBackFolder = "/tmp/imgs/";

    private static final String PRIOR_PREFIX = "p_";
    private static final String DELETE_PREFIX = "d_";

    /**
     * syn imgs
     */
    static {
        File backFolder = new File(imgBackFolder);
        String imgFolder = getImageFolder();
        File folder = new File(imgFolder);
        if (!backFolder.exists()) {
            backFolder.mkdirs();
        }
        File[] bakFiles = backFolder.listFiles();
        if (bakFiles != null) {
            String[] bakFileNames = Arrays.stream(bakFiles).map(File::getName).toArray(String[]::new);
            File[] files = folder.listFiles();
            if (files != null) {
                String[] fileNames = Arrays.stream(files).map(File::getName).toArray(String[]::new);
                Arrays.stream(bakFiles).filter(f -> !ArrayUtils.contains(fileNames, f.getName())).forEach(f -> {
                    try {
                        FileCopyUtils.copy(f, new File(imgFolder + f.getName()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                Arrays.stream(files).filter(f -> !ArrayUtils.contains(bakFileNames, f.getName())).forEach(f -> {
                    try {
                        FileCopyUtils.copy(f, new File(imgBackFolder + f.getName()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    private static String getImageFolder() {
        return ImageFilesHelper.class.getResource(relativePath).getPath();
    }

    public static List<Img> allImageFiles() {
        File folder = new File(getImageFolder());
        File[] imgs = folder.listFiles();
        if (ArrayUtils.isEmpty(imgs)) {
            return newArrayList();
        }
        return Arrays.stream(imgs).filter(f -> !f.getName().startsWith(DELETE_PREFIX))
                .map(file -> {
                    try {
                        BufferedImage bi = ImageIO.read(file);
                        Img img = new Img();
                        img.setName(file.getName());
                        img.setHeight(bi.getHeight());
                        img.setWidth(bi.getWidth());
                        return img;
                    } catch (IOException e) {
                        return null;
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static boolean isPrior(String fileName) {
        return fileName.startsWith(PRIOR_PREFIX);
    }

    public static void deleteImg(String fileName) {
        String imgFolder = getImageFolder();
        File file = new File(imgFolder + fileName);
        File deletedFile = new File(imgFolder + DELETE_PREFIX + fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("fileName not exits -> " + file.getAbsolutePath());
        }
        boolean renameTo = file.renameTo(deletedFile);
        if (!renameTo) {
            throw new RuntimeException("delete " + fileName + " failed!");
        }

        //delete bak file
        File bakFile = new File(imgBackFolder + fileName);
        File deletedBakFile = new File(imgBackFolder + DELETE_PREFIX + fileName);
        if (!bakFile.exists()) {
            throw new IllegalArgumentException("fileName not exits -> " + bakFile.getAbsolutePath());
        }
        boolean renameToBak = bakFile.renameTo(deletedBakFile);
        if (!renameToBak) {
            throw new RuntimeException("delete bak file " + fileName + " failed!");
        }
    }

    public static void saveNewImg(InputStream inputStream, String fileName) throws IOException {
        fileName = System.currentTimeMillis() + fileName;
        String name = getImageFolder() + fileName;
        File file = new File(name);
        if (file.exists()) {
            String suffix = name.substring(name.lastIndexOf("."));
            file = new File(name.replace(suffix, "_" + suffix));
        }
        file.createNewFile();
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] bytes = new byte[100];
            int readCount = 0;
            while ((readCount = inputStream.read(bytes)) > 0) {
                bufferedOutputStream.write(bytes, 0, readCount);
                bufferedOutputStream.flush();
            }
        } finally {
            inputStream.close();
            File bakFile = new File(imgBackFolder + fileName);
            FileCopyUtils.copy(file, bakFile);
        }
    }
}
