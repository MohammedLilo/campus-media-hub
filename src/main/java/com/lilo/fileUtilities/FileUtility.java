package com.lilo.fileUtilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lilo.constants.SupportedImageExtensions;

public class FileUtility {

	public static void saveImage(MultipartFile imageFile, String filePath) {
		String fileExtension = FilenameUtils.getExtension(imageFile.getOriginalFilename());
		File file = new File(filePath + "." + fileExtension);

		deleteImageFiles(filePath);
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());
			ImageIO.write(bufferedImage, fileExtension, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteImageFiles(String path) {
		for (String extension : SupportedImageExtensions.SUPPORTED_IMAGE_FILES) {
			File file = new File(path + "." + extension);
			file.delete();
		}
	}

}
