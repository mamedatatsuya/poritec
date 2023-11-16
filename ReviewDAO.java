package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ReviewBean;

public class ReviewDAO extends DAO{

//クチコミ登録
	public boolean reviewInsert(ReviewBean review) throws Exception {
		boolean success = false;//フラグをfalseに設定
		try (
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"insert into review values(null, ?, ?, ?,cast(now() as date))");
				){


			st.setInt(1, review.getProperty_code());
			st.setInt(2, review.getAccount_code());
			st.setString(3, review.getReview_content());
			int line = st.executeUpdate();


			if (line == 1) {
//				con.rollback();
//				con.setAutoCommit(true);
//				con.close();
				success = true;//登録成功
			}

//			con.commit();
//			con.setAutoCommit(true);
//			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
				return success;

	}
//クチコミ削除
	public boolean reviewDelete(ReviewBean review) throws Exception {
		boolean success = false;//フラグをfalseに設定
		try (
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"Delete from review where review_code = ? ");
				){
			st.setInt(1, review.getReview_code());
			int line = st.executeUpdate();
//			st.close();

			if (line == 1) {
//				con.rollback();
//				con.setAutoCommit(true);
//				con.close();
				success = true;//削除成功
			}

//			con.commit();
//			con.setAutoCommit(true);
//			con.close();
//			con.setAutoCommit(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
				return success;

	}
//クチコミ編集
	public boolean reviewEdit(ReviewBean review) throws Exception {
		boolean success = false;//フラグをfalseに設定
		try (
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"Update review set Review_content = ? where review_code = ?");
				){
			st.setString(1, review.getReview_content());
			st.setInt(2, review.getReview_code());
			int line = st.executeUpdate();
			st.close();

			if (line == 1) {
//				con.rollback();
//				con.setAutoCommit(true);
//				con.close();
				success = true;//編集成功
			}

//			con.commit();
//			con.setAutoCommit(true);
//			con.close();
//			con.setAutoCommit(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
				return success;

	}
//User画面でクチコミ検索
	public List<ReviewBean>reviewSearchByUser(int account_code) throws Exception {

	    List<ReviewBean> list = new ArrayList<>();

	    try (Connection con = getConnection();
	         PreparedStatement st = con.prepareStatement("select * from review where account_code = ?")) {
	        st.setInt(1, account_code);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            ReviewBean review = new ReviewBean();
	            review.setReview_code(rs.getInt("review_code"));
	            review.setProperty_code(rs.getInt("property_code"));
	            review.setAccount_code(rs.getInt("account_code"));
	            review.setReview_content(rs.getString("review_content"));
	            review.setInsert_date(rs.getDate("insert_date"));
	            list.add(review);
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	    return list;
	}

//物件詳細画面でクチコミ一覧表示
	public List<ReviewBean> reviewSearchByProperty(int propety_code)throws Exception {
		List<ReviewBean> list = new ArrayList<>();

		try (
				Connection con =getConnection();
				PreparedStatement st = con.prepareStatement("select * from review where property_code = ?");
				) {
			st.setInt(1, propety_code);
			ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            ReviewBean review = new ReviewBean();
	            review.setReview_code(rs.getInt("review_code"));
	            review.setProperty_code(rs.getInt("property_code"));
	            review.setAccount_code(rs.getInt("account_code"));
	            review.setReview_content(rs.getString("review_content"));
	            review.setInsert_date(rs.getDate("insert_date"));
	            list.add(review);
	        }
//			st.close();
//			rs.close();

		}catch(Exception e){
			throw e;
		}
		return list;
	}



//全件検索

public List<ReviewBean>reviewSearch(ReviewBean review) throws Exception {

    List<ReviewBean> list = new ArrayList<>();

    String selectAll=review.getFlag();




    try (Connection con = getConnection();){


         PreparedStatement st = con.prepareStatement("select * from review");
         ResultSet rs = st.executeQuery();

        while (rs.next()) {
            ReviewBean r = new ReviewBean();
            r.setReview_code(rs.getInt("review_code"));
            r.setProperty_code(rs.getInt("property_code"));
            r.setAccount_code(rs.getInt("account_code"));
            r.setReview_content(rs.getString("review_content"));
            list.add(r);
        }
        st.close();
		rs.close();

    } catch (Exception e) {
        throw e;
    }
    return list;
 }

}
