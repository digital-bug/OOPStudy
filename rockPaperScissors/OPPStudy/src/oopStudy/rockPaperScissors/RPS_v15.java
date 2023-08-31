package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 분리되어 있던 가위, 바위, 보 클래스를 하나의 Matcher 클래스로 묶음.
 * match(사용자 입력 숫자)_(컴퓨터 선택 숫자) 형태로 static 메서드 작성.
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
			return "[" + matchCnt + "]게임 : " + winCnt + "승 / " + drawCnt + "무 / " + defeatCnt + "패";
		}

		public String draw() {
			matchCnt++;
			drawCnt++;
			return "비김";
		}

		public String defeat() {
			matchCnt++;
			defeatCnt++;
			return "패";
		}

		public String win() {
			matchCnt++;
			winCnt++;
			return "승";
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

	public static final String[] names = { null, "가위", "바위", "보" };

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			MatchCounter counter = MatchCounter.getInstance();
			while (true) {
				try {
					System.out.println("[[ 가위바위보 - 1:가위 / 2:바위 / 3:보 / 0:Exit ]]");
					int input = Integer.parseInt(br.readLine());
					if (input == 0) {
						break;
					}
					if (input < 0 || input > 3) {
						System.out.println("입력 확인");
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
