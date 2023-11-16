package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.UserBean;

public class UserDAO extends DAO {

	//アカウント登録 登録成功！
	public boolean userInsert(UserBean account) throws Exception{
		boolean success = false;//フラグをfalseに設定

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("insert into user values(null, ?, ?, ?, cast(now() as date), 1)");
			){
			//SQLに検索条件を追加
			st.setString(1, account.getLogin_id());
			st.setString(2, account.getPassword());
			st.setString(3, account.getNickname());

				int line = st.executeUpdate();

				if(line==1) {
					success = true;//登録成功
				}
			}
			catch(Exception e) {
				System.out.println("登録エラー");;
			}
		return success;
	}

	//ニックネーム編集 編集成功！
	public boolean userNicknameEdit(UserBean account) {
		boolean success = false;//フラグをfalseに設定

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("update user set nickname = ? where binary account_code = ?");
			){
			//SQLに検索条件を追加
			st.setString(1, account.getNickname());
			st.setInt(2, account.getAccount_code());

				int line = st.executeUpdate();

				if(line==1) {
					success = true;//編集成功
				}
			}
			catch(Exception e) {
				System.out.println("ニックネーム編集エラー");;
			}
		return success;
	}

	//パスワード編集 編集成功！
	public boolean userPasswordEdit(UserBean account) {
		boolean success = false;//フラグをfalseに設定

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("update user set Password = ? where binary account_code = ?");
			){
			//SQLに検索条件を追加
			st.setString(1, account.getPassword());
			st.setInt(2, account.getAccount_code());

				int line = st.executeUpdate();

				if(line==1) {
					success = true;//編集成功
				}
			}
			catch(Exception e) {
				System.out.println("パスワード編集エラー");;
			}
		return success;
	}

	//ログインID編集
//	public boolean userLogin_idEdit(UserBean account) {  ID変更を仕様に入れる場合は有効にしてください。
//		boolean success = false;//フラグをfalseに設定
//
//		try(
//				Connection con = getConnection();
//				PreparedStatement st = con.prepareStatement("update user set Login_id = ? where binary account_code = ?");
//			){
//			//SQLに検索条件を追加
//			st.setString(1, account.getLogin_id());
//			st.setInt(2, account.getAccount_code());
//
//				int line = st.executeUpdate();
//
//				if(line==1) {
//					success = true;//編集成功
//				}
//			}
//			catch(Exception e) {
//				System.out.println("ログインID編集エラー");;
//			}
//		return success;
//	}

	//アカウント退会処理 処理成功
	public boolean userQuit(int account_code) {
		boolean success = false;//フラグをfalseに設定

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("update user set usable = 0 where binary account_code = ?");
			){
			//SQLに検索条件を追加
			st.setInt(1, account_code);

				int line = st.executeUpdate();

				if(line==1) {
					success = true;//退会処理成功
				}
			}
			catch(Exception e) {
				System.out.println("退会処理エラー");;
			}
		return success;
	}

	//アカウント復活処理
	public boolean userRevival(int account_code) {
		boolean success = false;//フラグをfalseに設定

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("update user set usable = 1 where binary account_code = ?");
			){
			//SQLに検索条件を追加
			st.setInt(1, account_code);

				int line = st.executeUpdate();

				if(line==1) {
					success = true;//復活処理成功
				}
			}
			catch(Exception e) {
				System.out.println("復活処理エラー");;
			}
		return success;
	}

	//アカウント削除 削除成功！
	public boolean userDelete(int account_code) {
		boolean success = false;//フラグをfalseに設定
		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("delete from user where binary account_code = ?");
			){
			//SQLに検索条件を追加
			st.setInt(1, account_code);

				int line = st.executeUpdate();

				if(line==1) {
					success = true;//削除処理成功
				}
			}
			catch(Exception e) {
				System.out.println("アカウント削除エラー");;
			}
		return success;

	}

	//ユーザーログイン ログイン成功！
	public UserBean userLogin(String login_id)throws Exception {
		UserBean user = null;//初期値をnullに設定

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("select * from user where binary login_id = ?");
			){
			//SQLに検索条件を追加
			st.setString(1, login_id);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					user = new UserBean();//検索結果をUserBeanに追加
					user.setAccount_code(rs.getInt("account_code"));
					user.setLogin_id(rs.getString("login_id"));
					user.setPassword(rs.getString("password"));
					user.setNickname(rs.getString("nickname"));
					user.setInsertdate(rs.getDate("insert_date"));
					user.setUsable(rs.getBoolean("usable"));
				}
			}
			catch(Exception e) {
				System.out.println("Userログインエラー");;
			}
		return user;
	}

	//ユーザー検索
	public UserBean userSearch(int account_code)throws Exception {
		UserBean user = null;//検索結果をUserBeanに追加

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("select * from user where binary account_code = ?");
			){
			st.setInt(1, account_code);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					user = new UserBean();
					user.setAccount_code(rs.getInt("account_code"));
					user.setLogin_id(rs.getString("login_id"));
					user.setNickname(rs.getString("nickname"));
					user.setUsable(rs.getBoolean("usable"));

                 }
			}
			catch(Exception e) {
				System.out.println("User検索エラー");
			}
		return user;
	}

	//全件検索
	public List<UserBean> userSearchAll()throws Exception {
		List<UserBean> list = new ArrayList<UserBean>();

		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("select * from user");
			){
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					UserBean user = new UserBean();//検索結果をUserBeanに追加
					user.setAccount_code(rs.getInt("account_code"));
					user.setLogin_id(rs.getString("login_id"));
					user.setPassword(rs.getString("password"));
					user.setNickname(rs.getString("nickname"));
					user.setUsable(rs.getBoolean("usable"));
					list.add(user);
                }
			}
			catch(Exception e) {
				System.out.println("User全件検索エラー");
		}
		return list;
	}
}
