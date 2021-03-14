package mvc.action;

public class ActionFactory {
	private static ActionFactory factory;
	private ActionFactory() {

	}
	// 방문 잠그기 동기화 < 싱글톤 적용 >
	public static synchronized ActionFactory getInstance() {
		if(factory == null) {
			factory = new ActionFactory();
		}
		return factory;
	}
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("index")) {
			action = new IndexAction();
		}
		return action;
	}
}
