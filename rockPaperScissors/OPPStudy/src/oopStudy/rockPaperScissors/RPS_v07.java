package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * ������������ ������ �� �ִ� Ŭ������ ����.
 * ����, ����, ���� ���� ��ü�� ����.
 * �̱� �� �ִ� ����� �� ��ü�� ����Ͽ� ����� ������ ���� �Ǵ�.
 */
public class RPS_v07 {
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

	public static final RPS scissor = new RPS("����");
	public static final RPS rock = new RPS("����");
	public static final RPS paper = new RPS("��  ");
	static {
		scissor.winFor(paper);
		rock.winFor(scissor);
		paper.winFor(rock);
	}

	public static final RPS[] names = { null, scissor, rock, paper };
	public static final String[] result = { "��", "���", "��" };

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
