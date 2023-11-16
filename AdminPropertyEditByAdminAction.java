package admin;

import java.io.File;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;//画像アップロードの為のクラス

import bean.PropertyBean;
import dao.PropertyDAO;
import tool.Action;

public class AdminPropertyEditByAdminAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String URL ="../tool/admin_error.jsp";
		PropertyBean property = new PropertyBean();
		int editnum = 0;


		int property_code=Integer.parseInt(request.getParameter("property_code"));
		property.setProperty_code(property_code);

		String property_name = request.getParameter("property_name");
    	if (property_name == null || property_name.equals("")) {
    		property.setProperty_name(null);
		} else {
			property.setProperty_name(property_name);
			editnum++;
		}

    	String property_content = request.getParameter("property_content");
    	if (property_content == null || property_content.equals("")) {
    		property.setProperty_content(null);
		} else {
			property.setProperty_content(property_content);
			editnum++;
		}

    	String price = request.getParameter("price");
    	if (price == null || price.equals("")) {
    		property.setPrice(0);
		} else {
			property.setPrice(Integer.parseInt(price));
			editnum++;
		}

    	String layout = request.getParameter("layout");
    	if (layout == null) {
    		property.setLayout(null);
		} else {
			property.setLayout(layout);
			editnum++;
		}

    	String pet = request.getParameter("pet");
    	if (pet == null) {
    		property.setPet(null);
		}  else {
			property.setPet(Boolean.valueOf(pet));
			editnum++;
		}

    	Part part = request.getPart("image");
    	String filename;
    	if(part == null || part.getSize() == 0) {
    		property.setImage(null);
    	} else {
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
	        property.setImage(filename);
	        editnum++;
        }

    	if (editnum > 0) {
    		PropertyDAO dao = new PropertyDAO();
    		if(dao.propertyEdit(property)) {
    			URL="admin_property_edit_out.jsp";
    		}
		} else {
			URL = "admin_property_list.jsp";
		}

		return URL;
	}

}
