package project.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "sessionScopedValuesBean")
public class SessionScopedValuesBean {
	private static String codeToEditRecord, codeToRestoreRecord;
	private static boolean succeeded;
	public static String getCodeToEditRecord() {
		return codeToEditRecord;
	}
	public static void setCodeToEditRecord(String codeToEditRecord) {
		SessionScopedValuesBean.codeToEditRecord = codeToEditRecord;
	}
	public static String getCodeToRestoreRecord() {
		return codeToRestoreRecord;
	}
	public static void setCodeToRestoreRecord(String codeToRestoreRecord) {
		SessionScopedValuesBean.codeToRestoreRecord = codeToRestoreRecord;
	}
	public boolean isSucceeded() {
		return succeeded;
	}
	public static void setSucceeded(boolean succeeded) {
		SessionScopedValuesBean.succeeded = succeeded;
	}
}
