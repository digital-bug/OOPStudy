package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ������ "����", "����", "��" ���ڿ��� �迭�� �и�.
 */
public class RPS_v03 {
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
					if (input == 1) { // ����
						sb.append("Player : ");
						sb.append(names[input]);
						if (com == 1) { // ����
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ���");
							counter.draw();
						} else if (com == 2) { // ����
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ��");
							counter.defeat();
						} else { // ��
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ��");
							counter.win();
						}
					} else if (input == 2) { // ����
						sb.append("Player : ");
						sb.append(names[input]);
						if (com == 1) { // ����
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ��");
							counter.win();
						} else if (com == 2) { // ����
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ���");
							counter.draw();
						} else { // ��
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ��");
							counter.defeat();
						}
					} else { // ��
						sb.append("Player : ");
						sb.append(names[input]);
						if (com == 1) { // ����
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ��");
							counter.defeat();
						} else if (com == 2) { // ����
							sb.append(" / Com : ");
							sb.append(names[com]);
							sb.append(" / ��");
							counter.win();
						} else { // ��
							sb.append(" / Com : ");
							sb.append(names[com]);
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