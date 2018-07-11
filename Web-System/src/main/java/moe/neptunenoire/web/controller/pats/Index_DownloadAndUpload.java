package moe.neptunenoire.web.controller.pats;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import moe.neptunenoire.MainRun;
import moe.neptunenoire.MainRun.FileType;
import moe.neptunenoire.web.database.ReoKissMai;

public class Index_DownloadAndUpload {

	private ReoKissMai reoKissMai;

	public Index_DownloadAndUpload(ReoKissMai reoKissMai) {
		this.reoKissMai = reoKissMai;
	}

	public boolean Fileupload(MultipartFile file) {
		try {
			// 得到文件
			String OriginalFilename = file.getOriginalFilename();
			String filePath = UUID.randomUUID().toString();
			File saveFile = MainRun.getFilePath(filePath, FileType.IMG);
			file.transferTo(saveFile);

			reoKissMai.setImgMapDB(filePath, OriginalFilename);

			return true;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
