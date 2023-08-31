package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * �⺻ �������� ������ ����������.
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
					matchCnt++;
					StringBuilder sb = new StringBuilder();
					if (input == 1) { // ����
						sb.append("Player : ����");
						if (com == 1) { // ����
							sb.append(" / Com : ����");
							sb.append(" / ���");
							drawCnt++;
						} else if (com == 2) { // ����
							sb.append(" / Com : ����");
							sb.append(" / ��");
							defeatCnt++;
						} else { // ��
							sb.append(" / Com : ��");
							sb.append(" / ��");
							winCnt++;
						}
					} else if (input == 2) { // ����
						sb.append("Player : ����");
						if (com == 1) { // ����
							sb.append(" / Com : ����");
							sb.append(" / ��");
							winCnt++;
						} else if (com == 2) { // ����
							sb.append(" / Com : ����");
							sb.append(" / ���");
							drawCnt++;
						} else { // ��
							sb.append(" / Com : ��");
							sb.append(" / ��");
							defeatCnt++;
						}
					} else { // ��
						sb.append("Player : ��");
						if (com == 1) { // ����
							sb.append(" / Com : ����");
							sb.append(" / ��");
							defeatCnt++;
						} else if (com == 2) { // ����
							sb.append(" / Com : ����");
							sb.append(" / ��");
							winCnt++;
						} else { // ��
							sb.append(" / Com : ��");
							sb.append(" / ���");
							drawCnt++;
						}
					}
					System.out.println(sb.toString());
				} catch (NumberFormatException e) {
				}
			}
			System.out.println("[" + matchCnt + "]���� : " + winCnt + "�� / " + drawCnt + "�� / " + defeatCnt + "��");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
