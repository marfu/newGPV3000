package ci.prosumagpv.web.bean.util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocumentParser {

	public DocumentParser() {
	}

	public static File createFileFromByteArray(byte[] content)
			throws IOException {

		String name = System.currentTimeMillis() + "";
		File f = new File(name);
		if (!f.exists()) {
			File.createTempFile(name, ".tmp");
			f.deleteOnExit();
		}

		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			bos.write(content);
			bos.flush();

		} finally {
			bos.close();
			fos.close();

		}

		return f;
	}

}