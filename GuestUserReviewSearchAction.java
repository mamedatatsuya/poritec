package guest_user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PropertyBean;
import bean.ReviewBean;
import bean.UserBean;
import dao.ReviewDAO;
import dao.UserDAO;
import tool.Action;
public class GuestUserReviewSearchAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		//JSPにて入力された値の受け入れ
		int property_code=Integer.parseInt(request.getParameter("property_code"));
		int number = Integer.parseInt(request.getParameter("number"));

		session.setAttribute("number",number);

		//インスタンスを生成し、受け入れた値をセット
		ReviewBean review=new ReviewBean();
		review.setProperty_code(property_code);

		//検索結果を表示するためのインスタンスの生成
		ReviewDAO dao = new ReviewDAO();
		List<ReviewBean> reviewList= dao.reviewSearchByProperty(property_code);

		session.setAttribute("reviewList", reviewList);

		List<PropertyBean> list=(List<PropertyBean>)session.getAttribute("list");
		PropertyBean property =list.get(number);

		session.setAttribute("property",property);

		//全ユーザー取得
			UserDAO userdao = new UserDAO();
			List<UserBean> users;
			users= userdao.userSearchAll();

			session.setAttribute("users", users);

		return "guestuser_property_detail.jsp";
	}

}
