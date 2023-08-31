package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ���������� ��� ó�� �Լ��� �迭�� ����. (�ν��Ͻ� �޼��� ����)
 * "��"(0), "���"(1), "��"(2) �� ���ڷ� �����Ǳ� ������ ����.
 */
public class RPS_v06 {
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
	public static final String[] result = { "��", "���", "��" };

	public static final MatchCounter counter = new MatchCounter();
	public static final Runnable[] fns = new Runnable[] { counter::defeat, counter::draw, counter::win };

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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

					int match = (input - com + 4) % 3;

					sb.append(" / ");
					sb.append(result[match]);
					fns[match].run();
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
