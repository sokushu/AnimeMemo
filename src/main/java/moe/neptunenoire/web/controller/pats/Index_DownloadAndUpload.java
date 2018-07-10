package moe.neptunenoire.web.controller.pats;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import moe.neptunenoire.MainRun;
import moe.neptunenoire.MainRun.FileType;

public class Index_DownloadAndUpload {

	public boolean Fileupload(MultipartFile file) {
		try {
			StringBuilder builder = new StringBuilder();
			// 得到文件
			String OriginalFilename = file.getOriginalFilename();
			String filePath = builder.append(UUID.randomUUID().toString()).append(file.getOriginalFilename()).toString();
			File saveFile = MainRun.getFilePath(filePath, FileType.IMG);
			file.transferTo(saveFile);
			
			return true;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}
