package ua.nure.petrov.SummaryTask4;

import java.util.Comparator;

import ua.nure.petrov.SummaryTask4.db.entity.Tariff;

public class CompareTariffs {

 public static final Comparator<Tariff> SORT_BY_ID = new Comparator<Tariff>() {

  @Override
  public int compare(Tariff o1, Tariff o2) {
   return o1.getId() - o2.getId();
  }
 };

 public static final Comparator<Tariff> SORT_BY_NAME = new Comparator<Tariff>() {
  @Override
  public int compare(Tariff o1, Tariff o2) {
   return o1.getName().compareTo(o2.getName());
  }
 };

 public static final Comparator<Tariff> SORT_BY_PRICE = new Comparator<Tariff>() {
  @Override
  public int compare(Tariff o1, Tariff o2) {
   return (int) (o1.getPrice() - o2.getPrice());
  }
 };
 
 public static final Comparator<Tariff> SORT_BY_ID_REVERSE = new Comparator<Tariff>() {

	  @Override
	  public int compare(Tariff o1, Tariff o2) {
	   return o2.getId() - o1.getId();
	  }
	 };

	 public static final Comparator<Tariff> SORT_BY_NAME_REVERSE = new Comparator<Tariff>() {
	  @Override
	  public int compare(Tariff o1, Tariff o2) {
	   return o2.getName().compareTo(o1.getName());
	  }
	 };

	 public static final Comparator<Tariff> SORT_BY_PRICE_REVERSE = new Comparator<Tariff>() {
	  @Override
	  public int compare(Tariff o1, Tariff o2) {
	   return (int) (o2.getPrice() - o1.getPrice());
	  }
	 };
}