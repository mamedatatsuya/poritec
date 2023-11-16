package admin;

import java.io.File;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.PropertyBean;
import dao.PropertyDAO;
import tool.Action;

public class AdminPropertyInsertAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PropertyBean property = new PropertyBean();
		property.setProperty_name(request.getParameter("property_name"));
		property.setProperty_content(request.getParameter("property_content"));
		property.setLayout(request.getParameter("layout"));
		int price = Integer.parseInt(request.getParameter("price"));
        property.setPrice(price);
		boolean pet =Boolean.valueOf(request.getParameter("pet"));
        property.setPet(pet);


        Part part = request.getPart("image");
        String filename;
        if(part.getSize() == 0) {
        	filename = "sample.jpg";
        }
        else {
        	filename = part.getSubmittedFileName();
        	String path = request.getServletContext().getRealPath("/image");

	    	File file = new File(path);
	        File files[] = file.listFiles();
	        int filenum = files.length + 1;
	        String extension = filename.substring(filename.lastIndexOf("."));

	        File addfile = new File(path + "/"  + filename);

	        if(Arrays.asList(files).contains(addfile)) {
	        	//同名ファイルが存在するときの処理
	        } else {
	        	//同名ファイルがないときの処理
	        	 filename = "img" + filenum + extension;
	        	 path = path + "/" + filename;
	        	 part.write(path);
	        }

        }
		property.setImage(filename);


		PropertyDAO dao = new PropertyDAO();
		if (dao.propertyInsert(property)) {
			return "admin_property_insert_out.jsp";//登録成功
		}

		request.setAttribute("error", "物件の登録に失敗しました。");
		return "../tool/admin_error.jsp";//登録失敗

	}
}
