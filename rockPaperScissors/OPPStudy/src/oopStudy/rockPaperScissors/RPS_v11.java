package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * "승", "패", "비김" 문자열을 MatchCounter의 각 함수에서 반환.
 * 클래스 또는 열거형 대신 인터페이스를 이용해 가위바위보 구현.
 * 인터페이스를 구현하여 가위 클래스, 바위 클래스, 보 클래스를 각각 구현.
 */
public class RPS_v11 {
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

	public static interface RPS {
		public String match(RPS obj);
	}

	public static class Scissor implements RPS {
		@Override
		public String toString() {
			return "가위";
		}

		@Override
		public String match(RPS obj) {
			if (obj instanceof Scissor) {
				return MatchCounter.getInstance().draw();
			} else if (obj instanceof Paper) {
				return MatchCounter.getInstance().win();
			}
			return MatchCounter.getInstance().defeat();
		}
	}

	public static class Rock implements RPS {
		@Override
		public String toString() {
			return "바위";
		}

		@Override
		public String match(RPS obj) {
			if (obj instanceof Rock) {
				return MatchCounter.getInstance().draw();
			} else if (obj instanceof Scissor) {
				return MatchCounter.getInstance().win();
			}
			return MatchCounter.getInstance().defeat();
		}
	}

	public static class Paper implements RPS {
		@Override
		public String toString() {
			return "보  ";
		}

		@Override
		public String match(RPS obj) {
			if (obj instanceof Paper) {
				return MatchCounter.getInstance().draw();
			} else if (obj instanceof Rock) {
				return MatchCounter.getInstance().win();
			}
			return MatchCounter.getInstance().defeat();
		}
	}

	public static final RPS[] names = { null, new Scissor(), new Rock(), new Paper() };

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
					sb.append(names[input].match(names[com]));
					System.out.println(sb.toString());
				} catch (NumberFormatException e) {
				}
			}
			System.out.println(counter.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
