package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 기본 로직으로 구현한 가위바위보.
 */
public class RPS_v01 {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int matchCnt = 0;
			int drawCnt = 0;
			int winCnt = 0;
			int defeatCnt = 0;
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
					matchCnt++;
					StringBuilder sb = new StringBuilder();
					if (input == 1) { // 가위
						sb.append("Player : 가위");
						if (com == 1) { // 가위
							sb.append(" / Com : 가위");
							sb.append(" / 비김");
							drawCnt++;
						} else if (com == 2) { // 바위
							sb.append(" / Com : 바위");
							sb.append(" / 패");
							defeatCnt++;
						} else { // 보
							sb.append(" / Com : 보");
							sb.append(" / 승");
							winCnt++;
						}
					} else if (input == 2) { // 바위
						sb.append("Player : 바위");
						if (com == 1) { // 가위
							sb.append(" / Com : 가위");
							sb.append(" / 승");
							winCnt++;
						} else if (com == 2) { // 바위
							sb.append(" / Com : 바위");
							sb.append(" / 비김");
							drawCnt++;
						} else { // 보
							sb.append(" / Com : 보");
							sb.append(" / 패");
							defeatCnt++;
						}
					} else { // 보
						sb.append("Player : 보");
						if (com == 1) { // 가위
							sb.append(" / Com : 가위");
							sb.append(" / 패");
							defeatCnt++;
						} else if (com == 2) { // 바위
							sb.append(" / Com : 바위");
							sb.append(" / 승");
							winCnt++;
						} else { // 보
							sb.append(" / Com : 보");
							sb.append(" / 비김");
							drawCnt++;
						}
					}
					System.out.println(sb.toString());
				} catch (NumberFormatException e) {
				}
			}
			System.out.println("[" + matchCnt + "]게임 : " + winCnt + "승 / " + drawCnt + "무 / " + defeatCnt + "패");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
