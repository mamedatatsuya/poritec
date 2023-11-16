package guest_user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PropertyBean;
import dao.PropertyDAO;
import tool.Action;

public class GuestUserPropertySearchAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//JSPにて入力された値の受け入れ
		String[] layoutValues = request.getParameterValues("layout");
		Boolean pet;
		String petable = request.getParameter("pet");
		if (petable.equals("どちらでも")) {
			pet = null;
		} else {
			pet =Boolean.valueOf(petable);
		}

		int price_lower=Integer.parseInt(request.getParameter("price_lower"));
		int price_upper=Integer.parseInt(request.getParameter("price_upper"));

		//インスタンスを生成し、受け入れた値をセット
		PropertyBean property = new PropertyBean();
		property.setProperty_code(property.getProperty_code());
		property.setLayouts(layoutValues);
		property.setPet(pet);
		property.setPriceLower(price_lower);
		property.setPriceUpper(price_upper);

		//検索結果を表示するためのインスタンスの生成
		PropertyDAO dao = new PropertyDAO();
		List<PropertyBean> list;
		list= dao.propertySearch(property);

		//フォワード先のJSPで使えるようにsessionに引き渡す
		session.setAttribute("list",list);

		return "guestuser_property_search_result.jsp";
	}


}
