package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * �и��Ǿ� �ִ� ����, ����, �� Ŭ������ �ϳ��� Matcher Ŭ������ ����.
 * match(����� �Է� ����)_(��ǻ�� ���� ����) ���·� static �޼��� �ۼ�.
 */
public class RPS_v15 {
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

	public static class Matcher {
		public static String match1_1() {
			return MatchCounter.getInstance().draw();
		}

		public static String match1_2() {
			return MatchCounter.getInstance().defeat();
		}

		public static String match1_3() {
			return MatchCounter.getInstance().win();
		}

		public static String match2_1() {
			return MatchCounter.getInstance().win();
		}

		public static String match2_2() {
			return MatchCounter.getInstance().draw();
		}

		public static String match2_3() {
			return MatchCounter.getInstance().defeat();
		}

		public static String match3_1() {
			return MatchCounter.getInstance().defeat();
		}

		public static String match3_2() {
			return MatchCounter.getInstance().win();
		}

		public static String match3_3() {
			return MatchCounter.getInstance().draw();
		}
	}

	public static final String[] names = { null, "����", "����", "��" };

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
					Method fnMatch = Matcher.class.getMethod("match" + input + "_" + com);
					sb.append(fnMatch.invoke(null));
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
