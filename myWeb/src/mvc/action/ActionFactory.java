package mvc.action;

public class ActionFactory {
	private static ActionFactory factory;
	private ActionFactory() {

	}
	// �湮 ��ױ� ����ȭ < �̱��� ���� >
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
