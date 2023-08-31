package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 가위, 바위, 보 각 클래스에 match 메서드 변경.
 * 각각 가위와 매치(match1), 바위와 매치(match2), 보와 매치(match3)를 분리함.
 * 리플렉션을 이용해 각 함수를 호출함. 
 */
public class RPS_v14 {
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

	public static class Scissor {
		@Override
		public String toString() {
			return "가위";
		}

		public String match1() {
			return MatchCounter.getInstance().draw();
		}

		public String match2() {
			return MatchCounter.getInstance().defeat();
		}

		public String match3() {
			return MatchCounter.getInstance().win();
		}
	}

	public static class Rock {
		@Override
		public String toString() {
			return "바위";
		}

		public String match1() {
			return MatchCounter.getInstance().win();
		}

		public String match2() {
			return MatchCounter.getInstance().draw();
		}

		public String match3() {
			return MatchCounter.getInstance().defeat();
		}
	}

	public static class Paper {
		@Override
		public String toString() {
			return "보  ";
		}

		public String match1() {
			return MatchCounter.getInstance().defeat();
		}

		public String match2() {
			return MatchCounter.getInstance().win();
		}

		public String match3() {
			return MatchCounter.getInstance().draw();
		}
	}

	public static final Object[] names = { null, new Scissor(), new Rock(), new Paper() };

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
					Method fnMatch = names[input].getClass().getMethod("match" + com);
					sb.append(fnMatch.invoke(names[input]));
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
