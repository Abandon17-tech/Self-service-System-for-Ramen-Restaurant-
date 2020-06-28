package faceID_Control;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/** 
 * This class is used to set face recognition property
 * @author Wendi Zhu
 * @version 1.5
 * 
 */
public class FaceSearch {
	
	 public static byte[] getImageFromLocalByUrl(String strUrl) {  
	        try {  
	            File imageFile = new File(strUrl);  
	            InputStream inStream = new FileInputStream(imageFile);  
	            byte[] btImg = readInputStream(inStream);
	            return btImg;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
	 private static byte[] readInputStream(InputStream inStream) throws IOException {  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        byte[] buffer = new byte[10240];  
	        int len = 0;  
	        while ((len = inStream.read(buffer)) != -1) {  
	            outStream.write(buffer, 0, len);  
	        }  
	        inStream.close();  
	        return outStream.toByteArray();  
	  
	    } 
	 
	 
	public static String faceSearch() {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        String img = Base64Util.encode(FaceSearch.getImageFromLocalByUrl("src/face_images/1.png"));
        String str = "";
		try {
			str = new String(img.getBytes("UTF-8"),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", str);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "face_data");
            map.put("image_type", "BASE64");
            map.put("quality_control", "NONE");

            String param = GsonUtils.toJson(map);

            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
