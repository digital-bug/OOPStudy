package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * MatchCounter를 Singleton으로 만듦.
 * 가위 바위 보 승패 연산 후, 내부적으로 MatchCounter에 기록함.
 */
public class RPS_v08 {
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

		public void draw() {
			matchCnt++;
			drawCnt++;
		}

		public void defeat() {
			matchCnt++;
			defeatCnt++;
		}

		public void win() {
			matchCnt++;
			winCnt++;
		}
	}

	public static class RPS {
		private List<RPS> winForObjs = new LinkedList<RPS>();
		private String name;

		public RPS(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

		public void winFor(RPS obj) {
			winForObjs.add(obj);
		};

		public String match(RPS obj) {
			MatchCounter counter = MatchCounter.getInstance();
			if (this.equals(obj)) {
				counter.draw();
				return "비김";
			} else if (winForObjs.contains(obj)) {
				counter.win();
				return "승";
			}
			counter.defeat();
			return "패";
		}
	}

	public static final RPS scissor = new RPS("가위");
	public static final RPS rock = new RPS("바위");
	public static final RPS paper = new RPS("보  ");
	static {
		scissor.winFor(paper);
		rock.winFor(scissor);
		paper.winFor(rock);
	}

	public static final RPS[] names = { null, scissor, rock, paper };

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
