package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 가위바위보를 통합할 수 있는 클래스를 만듦.
 * 가위, 바위, 보는 각각 객체로 만듦.
 * 이길 수 있는 대상을 각 객체에 등록하여 저장된 값으로 승패 판단.
 */
public class RPS_v07 {
	public static class MatchCounter {
		int matchCnt = 0;
		int drawCnt = 0;
		int winCnt = 0;
		int defeatCnt = 0;

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

		public int match(RPS obj) {
			if (this.equals(obj)) {
				return 1;
			} else if (winForObjs.contains(obj)) {
				return 2;
			}
			return 0;
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
	public static final String[] result = { "패", "비김", "승" };

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			MatchCounter counter = new MatchCounter();
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

					int match = names[input].match(names[com]);

					sb.append(" / ");
					sb.append(result[match]);
					switch (match) {
					case 0:
						counter.defeat();
						break;
					case 1:
						counter.draw();
						break;
					case 2:
						counter.win();
						break;
					}
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
