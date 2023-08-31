package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 가위바위보 인터페이스 삭제.
 * 각 클래스에 match 함수를 공통으로 작성.
 * 리플렉션을 이용해 대상 객체의 match 함수 호출.
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
			return "바위";
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
			return "보  ";
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
