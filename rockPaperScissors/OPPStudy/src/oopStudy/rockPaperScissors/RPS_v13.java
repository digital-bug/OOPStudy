package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ���������� �������̽� ����.
 * �� Ŭ������ match �Լ��� �������� �ۼ�.
 * ���÷����� �̿��� ��� ��ü�� match �Լ� ȣ��.
 */
public class RPS_v13 {
	public static class MatchCounter {
		int matchCnt = 0;
		int drawCnt = 0;
		int winCnt = 0;
		int defeatCnt = 0;

		static MatchCounter obj;

		private MatchCounter() {
		}

		public static MatchCounter getInstance() {
			if (obj == null)
				obj = new MatchCounter();
			return obj;
		}

		@Override
		public String toString() {
			return "[" + matchCnt + "]���� : " + winCnt + "�� / " + drawCnt + "�� / " + defeatCnt + "��";
		}

		public String draw() {
			matchCnt++;
			drawCnt++;
			return "���";
		}

		public String defeat() {
			matchCnt++;
			defeatCnt++;
			return "��";
		}

		public String win() {
			matchCnt++;
			winCnt++;
			return "��";
		}
	}

	public static class Scissor {
		@Override
		public String toString() {
			return "����";
		}

		public String match(Object obj) {
			if (obj instanceof Scissor) {
				return MatchCounter.getInstance().draw();
			} else if (obj instanceof Paper) {
				return MatchCounter.getInstance().win();
			}
			return MatchCounter.getInstance().defeat();
		}
	}

	public static class Rock {
		@Override
		public String toString() {
			return "����";
		}

		public String match(Object obj) {
			if (obj instanceof Rock) {
				return MatchCounter.getInstance().draw();
			} else if (obj instanceof Scissor) {
				return MatchCounter.getInstance().win();
			}
			return MatchCounter.getInstance().defeat();
		}
	}

	public static class Paper {
		@Override
		public String toString() {
			return "��  ";
		}

		public String match(Object obj) {
			if (obj instanceof Paper) {
				return MatchCounter.getInstance().draw();
			} else if (obj instanceof Rock) {
				return MatchCounter.getInstance().win();
			}
			return MatchCounter.getInstance().defeat();
		}
	}

	public static final Object[] names = { null, new Scissor(), new Rock(), new Paper() };

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			MatchCounter counter = MatchCounter.getInstance();
			while (true) {
				try {
					System.out.println("[[ ���������� - 1:���� / 2:���� / 3:�� / 0:Exit ]]");
					int input = Integer.parseInt(br.readLine());
					if (input == 0) {
						break;
					}
					if (input < 0 || input > 3) {
						System.out.println("�Է� Ȯ��");
						continue;
					}
					int com = (int) (Math.random() * 3) + 1;
					StringBuilder sb = new StringBuilder();
					sb.append("Player : ");
					sb.append(names[input]);
					sb.append(" / Com : ");
					sb.append(names[com]);
					sb.append(" / ");
					Method fnMatch = names[input].getClass().getMethod("match", Object.class);
					sb.append(fnMatch.invoke(names[input], names[com]));
					System.out.println(sb.toString());
				} catch (NumberFormatException e) {
				} catch (NoSuchMethodException | SecurityException e) {
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				}
			}
			System.out.println(counter.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
