package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.PropertyBean;

public class PropertyDAO extends DAO{

//物件登録
	public boolean propertyInsert(PropertyBean property) throws Exception {
		boolean success = false;//フラグをfalseに設定
		try(
		Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(
				"insert into property values(null, ?, ?, ?, ?, ?, ?)");
				){
//			con.setAutoCommit(false);

				st.setString(1, property.getProperty_name());
				st.setString(2, property.getProperty_content());
				st.setString(3, property.getLayout());
				st.setInt(4, property.getPrice());
				st.setBoolean(5, property.getPet());
				st.setString(6, property.getImage());
				int line = st.executeUpdate();
				st.close();

					if (line == 1) {
//						con.rollback();
//						con.setAutoCommit(true);
//						con.close();
						success = true;//登録成功
				}

//					con.commit();
//					con.setAutoCommit(true);
//					con.close();


		} catch (Exception e) {
			System.out.println("propertyInsertメソッドの実行に失敗しました。");
			throw e;
		}
		return success;
	}

//物件削除
	public boolean propertyDelete(int property_code) throws Exception {
		boolean success = false;//フラグをfalseに設定
		try(
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement("delete from property where property_code = ?");
			){
			//SQLに検索条件を追加
			st.setInt(1, property_code);

				int line = st.executeUpdate();

				if(line==1) {
					success = true;//削除処理成功
				}
			}
			catch(Exception e) {
				System.out.println("物件削除エラー");;
			}
		return success;

	}

//物件検索
	public List<PropertyBean> propertySearch(PropertyBean property)throws Exception {
		List<PropertyBean> list = new ArrayList<>();
		String[] layout = property.getLayouts();
		int price_lower = property.getPriceLower();
		int price_upper = property.getPriceUpper();
		Boolean pet = property.getPet();
		System.out.println(price_lower);
		int sCount = 0;//検索条件の数を記録

		//SQL文の変化しない部分を変数に格納
		StringBuilder queryHead = new StringBuilder("select * from property");

		if(layout!=null) {
			sCount++;
			queryHead.append(" where layout IN (");
			int count =0;
			for(String s:layout) {
				queryHead.append("?");
				 if ( count< layout.length - 1) {
		                queryHead.append(", ");
				 }
				 count++;
			}
			queryHead.append(")");
		}

		if(price_upper != 0 && price_lower > price_upper) {//下限値＞上限値の場合
			//何もしない
		} else if (price_lower != 0 && price_upper ==0) {//下限あり上限なし
			if (sCount == 0) {//はじめての検索条件の追加か確認
				queryHead.append(" where ");
			} else {
				queryHead.append(" and ");
			}
			queryHead.append("price >= ?");
			sCount++;

		} else if (price_lower != 0 && price_upper !=0) {//下限あり上限あり
			if (sCount == 0) {//はじめての検索条件の追加か確認
				queryHead.append(" where ");
			} else {
				queryHead.append(" and ");
			}
			queryHead.append("price between ? and ?");
			sCount++;

		} else if (price_lower == 0 && price_upper !=0) {//下限なし上限あり
			if (sCount == 0) {//はじめての検索条件の追加か確認
				queryHead.append(" where ");
			} else {
				queryHead.append(" and ");
			}
			queryHead.append("price <= ?");
			sCount++;

		}

		//SQL文のlayout以外の検索条件を追加
		if (pet != null) {
			if (sCount == 0) {//はじめての検索条件の追加か確認
				queryHead.append(" where ");
			} else {
				queryHead.append(" and ");
			}
			queryHead.append("pet = ?");
			sCount++;
		}
		//sql文確認
		System.out.println(queryHead.toString());
		try (
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(queryHead.toString());
				){
		        //stに作成したSQL文を格納
	            //チェックボックスにチェックした分だけlayoutをSQLで取得
	            int index =1;
	            if(layout!=null) {
	                for(int i=0;i<layout.length;i++) {
	                	st.setString(i+1, layout[i]);
	                	index++;
	                }
	            }

	            if (!(price_upper != 0 && price_lower > price_upper)) {//下限と上限が正しいかどうか
	            	if(price_lower !=0) {st.setInt(index++, price_lower); }//下限なしの場合はSQLの ? が1個減る為
					if(price_upper !=0) {st.setInt(index++, price_upper); }//上限なしの場合はSQLの ? が1個減る為
	            }
				if(pet != null) {st.setBoolean(index++, pet);}

				ResultSet rs = st.executeQuery();

			while(rs.next()) {
				//検索結果をリストへ
				PropertyBean p = new PropertyBean();
				p.setProperty_code(rs.getInt("property_code"));
				p.setProperty_name(rs.getString("property_name"));
				p.setProperty_content(rs.getString("property_content"));
				p.setLayout(rs.getString("layout"));
				p.setPrice(rs.getInt("price"));
				p.setPet(rs.getBoolean("pet"));
				p.setImage(rs.getString("image"));
				list.add(p);
			}

			}catch(Exception e){
				System.out.println("propertySearchメソッドの実行に失敗しました。");
				throw e;
			}
			return list;
		}

//物件編集
	public boolean propertyEdit(PropertyBean property) throws Exception {
		boolean success = false;//フラグをfalseに設定
		String property_name = property.getProperty_name();
		String property_content = property.getProperty_content();
		String layout = property.getLayout();
		int price = property.getPrice();
		Boolean pet = property.getPet();
		if (property.getFlag() == null) {
			pet = property.getPet();
		}
		String image = property.getImage();
		int sCount = 0;//編集条件の数を記録

		//SQL文の変化しない部分を変数に格納
		StringBuilder queryHead = new StringBuilder("update property ");
		if (property_name != null) {
			queryHead.append("set property_name = ?");
			sCount++;
		}

		if (property_content != null) {
			if (sCount == 0) {
				queryHead.append("set property_content = ?");
			} else {
				queryHead.append(", property_content = ?");
			}
			sCount++;
		}

		if (layout != null) {
			if (sCount == 0) {
				queryHead.append("set layout = ?");
			} else {
				queryHead.append(", layout = ?");
			}
			sCount++;
		}

		if (price != 0) {
			if (sCount == 0) {
				queryHead.append("set price = ?");
			} else {
				queryHead.append(", price = ?");
			}
			sCount++;
		}

		if (pet != null) {
			if (sCount == 0) {
				queryHead.append("set pet = ?");
			} else {
				queryHead.append(", pet = ?");
			}
			sCount++;
		}

		if ( image != null) {
			if (sCount == 0) {
				queryHead.append("set  image = ?");
			} else {
				queryHead.append(", image = ?");
			}
			sCount++;
		}

		queryHead.append(" where property_code = ?");
		System.out.println(queryHead.toString());

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(queryHead.toString());
			){

			int index =1;
			if (property_name != null) {st.setString(index++, property_name);}
			if (property_content != null) {st.setString(index++, property_content);}
			if (layout != null) {st.setString(index++, layout);}
			if (price != 0) {st.setInt(index++, price);}
			if (pet != null) {st.setBoolean(index++, pet);}
			if (image != null) {st.setString(index++, image);}
			st.setInt(index++, property.getProperty_code());
			int line = st.executeUpdate();

			if (line == 1) {
				success = true;//編集成功
				}

		} catch (Exception e) {
			System.out.println("propertyEditメソッドの実行に失敗しました。");
			throw e;
		}
				return success;
	}
}