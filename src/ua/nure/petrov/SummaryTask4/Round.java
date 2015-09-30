package ua.nure.petrov.SummaryTask4;

public class Round {

	public static double roundResult(double d, int precise) {
		precise = (int) Math.pow(10, precise);
		d = d * precise;
		int i = (int) Math.round(d);
		return (double) i / precise;
	}
}
