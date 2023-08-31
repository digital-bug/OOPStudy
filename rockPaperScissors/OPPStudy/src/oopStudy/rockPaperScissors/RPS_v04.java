package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * �迭�� ����ϸ鼭 �ݺ��Ǵ� �ڵ� ����.
 */
public class RPS_v04 {
	public static class MatchCounter {
		int matchCnt = 0;
		int drawCnt = 0;
		int winCnt = 0;
		int defeatCnt = 0;

		@Override
		public String toString() {
			return "[" + matchCnt + "]���� : " + winCnt + "�� / " + drawCnt + "�� / " + defeatCnt + "��";
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

	public static final String[] names = { "", "����", "����", "��  " };

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			MatchCounter counter = new MatchCounter();
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
					if (input == 1) { // ����
						if (com == 1) { // ����
							sb.append(" / ���");
							counter.draw();
						} else if (com == 2) { // ����
							sb.append(" / ��");
							counter.defeat();
						} else { // ��
							sb.append(" / ��");
							counter.win();
						}
					} else if (input == 2) { // ����
						if (com == 1) { // ����
							sb.append(" / ��");
							counter.win();
						} else if (com == 2) { // ����
							sb.append(" / ���");
							counter.draw();
						} else { // ��
							sb.append(" / ��");
							counter.defeat();
						}
					} else { // ��
						if (com == 1) { // ����
							sb.append(" / ��");
							counter.defeat();
						} else if (com == 2) { // ����
							sb.append(" / ��");
							counter.win();
						} else { // ��
							sb.append(" / ���");
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
