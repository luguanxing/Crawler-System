package crawler.utils;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import crawler.entity.BaoTuContent_ES;
import crawler.entity.JiSuContent_ES;
import crawler.entity.SheTuContent_ES;

public class MyExcelUtil {

	public static Workbook softWb = new HSSFWorkbook();
	public static Sheet softSheet = softWb.createSheet("数据总览");
	public static Integer softRowIndex = 0;

	public static Workbook picSoftWb = new HSSFWorkbook();
	public static Sheet picSoftSheet = picSoftWb.createSheet("数据总览");
	public static Integer picRowIndex = 0;

	public static Workbook musicSoftWb = new HSSFWorkbook();
	public static Sheet musicSoftSheet = musicSoftWb.createSheet("数据总览");
	public static Integer musicRowIndex = 0;

	public static void addJiSuToEs(JiSuContent_ES esEntity) throws Exception {
		if (softRowIndex == 0) {
			Row row = softSheet.createRow(0);
			row.createCell(0).setCellValue("id");
			row.createCell(1).setCellValue("软件名称");
			row.createCell(2).setCellValue("大小(MB)");
			row.createCell(3).setCellValue("更新时间");
			row.createCell(4).setCellValue("软件类别");
			row.createCell(5).setCellValue("软件类型");
			row.createCell(6).setCellValue("下载次数");
			row.createCell(7).setCellValue("评分");
			row.createCell(8).setCellValue("语言");
			softRowIndex++;
		}
		Row row = softSheet.createRow(softRowIndex);
		String softId = esEntity.getSoftId();
		String softName = esEntity.getSoftName();
		Float softSize = esEntity.getSoftSize();
		Long softUpdateTime = esEntity.getSoftUpdateTime();
		String softCategory = esEntity.getSoftCategory();
		String softType = esEntity.getSoftType();
		Integer softDownloadTimes = esEntity.getSoftDownloadTimes();
		Integer softRate = esEntity.getSoftRate();
		String language = esEntity.getSoftLanguage();
		row.createCell(0).setCellValue(softId);
		row.createCell(1).setCellValue(softName);
		row.createCell(2).setCellValue(String.format("%.2f", softSize));
		row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date(softUpdateTime)));
		row.createCell(4).setCellValue(softCategory);
		row.createCell(5).setCellValue(softType);
		row.createCell(6).setCellValue(softDownloadTimes);
		row.createCell(7).setCellValue(softRate);
		row.createCell(8).setCellValue(language);
		softRowIndex++;
		FileOutputStream fileOut = new FileOutputStream("e:\\软件数据.xls");
		softWb.write(fileOut);
	}

	public static void addBaotuToEs(BaoTuContent_ES esEntity) throws Exception {
		if (picRowIndex == 0) {
			Row row = picSoftSheet.createRow(0);
			row.createCell(0).setCellValue("id");
			row.createCell(1).setCellValue("图片名称");
			row.createCell(2).setCellValue("图片大小(MB)");
			row.createCell(3).setCellValue("上传时间");
			row.createCell(4).setCellValue("颜色模式");
			row.createCell(5).setCellValue("图片尺寸");
			row.createCell(6).setCellValue("图片格式");
			row.createCell(7).setCellValue("推荐软件");
			picRowIndex++;
		}
		Row row = picSoftSheet.createRow(picRowIndex);
		String picId = esEntity.getPicId();
		String picName = esEntity.getPicName();
		Float picSize = esEntity.getPicSize();
		Long picUploadTime = esEntity.getPicUploadTime();
		String picColorMode = esEntity.getPicColorMode();
		String picPixel = esEntity.getPicPixel();
		String picFormat = esEntity.getPicFormat();
		String picSoftware = esEntity.getPicSoftware();
		row.createCell(0).setCellValue(picId);
		row.createCell(1).setCellValue(picName);
		row.createCell(2).setCellValue(String.format("%.2f", picSize));
		row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date(picUploadTime)));
		row.createCell(4).setCellValue(picColorMode);
		row.createCell(5).setCellValue(picPixel);
		row.createCell(6).setCellValue(picFormat);
		row.createCell(7).setCellValue(picSoftware);
		picRowIndex++;
		FileOutputStream fileOut = new FileOutputStream("e:\\图片数据.xls");
		picSoftWb.write(fileOut);
	}

	public static void addShetuToEs(SheTuContent_ES esEntity) throws Exception {
		if (musicRowIndex == 0) {
			Row row = musicSoftSheet.createRow(0);
			row.createCell(0).setCellValue("id");
			row.createCell(1).setCellValue("音乐名称");
			row.createCell(2).setCellValue("音乐类型");
			row.createCell(3).setCellValue("音乐大小(MB)");
			row.createCell(4).setCellValue("音乐格式");
			row.createCell(5).setCellValue("音乐时长(秒)");
			row.createCell(6).setCellValue("推荐软件");
			row.createCell(7).setCellValue("上传者");
			musicRowIndex++;
		}
		Row row = musicSoftSheet.createRow(musicRowIndex);
		String musicId = esEntity.getMusicId();
		String musicName = esEntity.getMusicName();
		String musicType = esEntity.getMusicType();
		Float musicSize = esEntity.getMusicSize();
		String musicFormat = esEntity.getMusicFormat();
		Integer musicLong = esEntity.getMusicLong();
		String musicSoftware = esEntity.getMusicSoftware();
		String musicUploader = esEntity.getMusicUploader();
		row.createCell(0).setCellValue(musicId);
		row.createCell(1).setCellValue(musicName);
		row.createCell(2).setCellValue(musicType);
		row.createCell(3).setCellValue(String.format("%.2f", musicSize));
		row.createCell(4).setCellValue(musicFormat);
		row.createCell(5).setCellValue(musicLong);
		row.createCell(6).setCellValue(musicSoftware);
		row.createCell(7).setCellValue(musicUploader);
		musicRowIndex++;
		FileOutputStream fileOut = new FileOutputStream("e:\\音频数据.xls");
		musicSoftWb.write(fileOut);
	}

}
