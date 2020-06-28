package dao_Control;

import vo_Entity.User;

public interface EmailDAO {
	public void sendAEmail(User user) throws Exception;
}
