package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 승패 결과를 집계하는 MatchCounter 분리.
 */
public class RPS_v02 {
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
					if (input == 1) { // 가위
						sb.append("Player : 가위");
						if (com == 1) { // 가위
							sb.append(" / Com : 가위");
							sb.append(" / 비김");
							counter.draw();
						} else if (com == 2) { // 바위
							sb.append(" / Com : 바위");
							sb.append(" / 패");
							counter.defeat();
						} else { // 보
							sb.append(" / Com : 보");
							sb.append(" / 승");
							counter.win();
						}
					} else if (input == 2) { // 바위
						sb.append("Player : 바위");
						if (com == 1) { // 가위
							sb.append(" / Com : 가위");
							sb.append(" / 승");
							counter.win();
						} else if (com == 2) { // 바위
							sb.append(" / Com : 바위");
							sb.append(" / 비김");
							counter.draw();
						} else { // 보
							sb.append(" / Com : 보");
							sb.append(" / 패");
							counter.defeat();
						}
					} else { // 보
						sb.append("Player : 보");
						if (com == 1) { // 가위
							sb.append(" / Com : 가위");
							sb.append(" / 패");
							counter.defeat();
						} else if (com == 2) { // 바위
							sb.append(" / Com : 바위");
							sb.append(" / 승");
							counter.win();
						} else { // 보
							sb.append(" / Com : 보");
							sb.append(" / 비김");
							counter.draw();
						}
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
