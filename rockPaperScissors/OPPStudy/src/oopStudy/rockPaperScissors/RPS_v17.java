package oopStudy.rockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import oopStudy.rockPaperScissors.RPS_v17.RPS.Objs;

/**
 * 가위바위보 룰 관련 메서드(RPC annotation)을 보관하는 클래스 annotation (RPSHolder) 추가.
 * RPSHolder annotation을 이용해 메서드 검색 및 RPC annotation를 이용해 호출.
 */
public class RPS_v17 {
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

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface RPS {
		public static enum Objs {
			SCISSOR(1), ROCK(2), PAPER(3);

			private int cd;

			private Objs(int cd) {
				this.cd = cd;
			}

			public int getCd() {
				return cd;
			}
		}

		Objs challenger();

		Objs defender();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface RPSHolder {
	}

	@RPSHolder
	public static class Matcher {
		@RPS(challenger = Objs.SCISSOR, defender = Objs.SCISSOR)
		public static String match1_1() {
			return MatchCounter.getInstance().draw();
		}

		@RPS(challenger = Objs.SCISSOR, defender = Objs.ROCK)
		public static String match1_2() {
			return MatchCounter.getInstance().defeat();
		}

		@RPS(challenger = Objs.SCISSOR, defender = Objs.PAPER)
		public static String match1_3() {
			return MatchCounter.getInstance().win();
		}

		@RPS(challenger = Objs.ROCK, defender = Objs.SCISSOR)
		public static String match2_1() {
			return MatchCounter.getInstance().win();
		}

		@RPS(challenger = Objs.ROCK, defender = Objs.ROCK)
		public static String match2_2() {
			return MatchCounter.getInstance().draw();
		}

		@RPS(challenger = Objs.ROCK, defender = Objs.PAPER)
		public static String match2_3() {
			return MatchCounter.getInstance().defeat();
		}

		@RPS(challenger = Objs.PAPER, defender = Objs.SCISSOR)
		public static String match3_1() {
			return MatchCounter.getInstance().defeat();
		}

		@RPS(challenger = Objs.PAPER, defender = Objs.ROCK)
		public static String match3_2() {
			return MatchCounter.getInstance().win();
		}

		@RPS(challenger = Objs.PAPER, defender = Objs.PAPER)
		public static String match3_3() {
			return MatchCounter.getInstance().draw();
		}
	}

	public static final String[] names = { null, "가위", "바위", "보" };

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
					for (Class<?> declaredClasse : RPS_v17.class.getDeclaredClasses()) {
						if (declaredClasse.getAnnotation(RPSHolder.class) == null) {
							continue;
						}
						boolean done = false;
						for (Method m : declaredClasse.getMethods()) {
							RPS anno = m.getAnnotation(RPS.class);
							if (anno != null && anno.challenger().getCd() == input && anno.defender().getCd() == com) {
								sb.append(m.invoke(null));
								done = true;
								break;
							}
						}
						if (done) {
							break;
						}
					}
					System.out.println(sb.toString());
				} catch (NumberFormatException e) {
				} catch (SecurityException e) {
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				}
			}
			System.out.println(counter.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
