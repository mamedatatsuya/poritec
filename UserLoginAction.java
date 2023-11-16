package guest_user;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import dao.UserDAO;
import tool.Action;

public class UserLoginAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		HttpSession session = request.getSession();
		//入力された値の取得
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		UserDAO dao = new UserDAO();
		UserBean user = dao.userLogin(login_id);


		if (user != null) {
			//パスワードの確認
			if (user.getUsable()) {

				if (password.equals(user.getPassword())) {

					//パスワードが合っている時
					session.setAttribute("user", user);
					return "user_login_out.jsp";

				} else {

					//パスワードが間違っている時
					request.setAttribute("passError", "パスワードが間違っています!");
					request.setAttribute("user_id", login_id);
					return "user_login_in.jsp";

				}
			}
		}
		//入力されたログインIDがデータベースになかった時
		request.setAttribute("idError", "ログインIDが間違っています!");
		return "user_login_in.jsp";
	}

}
